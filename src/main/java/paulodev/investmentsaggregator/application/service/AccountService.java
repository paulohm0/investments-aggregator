package paulodev.investmentsaggregator.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import paulodev.investmentsaggregator.client.BrapiClient;
import paulodev.investmentsaggregator.domain.model.dto.AccountStockResponseDTO;
import paulodev.investmentsaggregator.domain.model.dto.AssociateAccountStockDTO;
import paulodev.investmentsaggregator.domain.model.entity.AccountStock;
import paulodev.investmentsaggregator.domain.model.valueobject.AccountStockId;
import paulodev.investmentsaggregator.domain.repository.AccountRepository;
import paulodev.investmentsaggregator.domain.repository.AccountStockRepository;
import paulodev.investmentsaggregator.domain.repository.StockRepository;
import paulodev.investmentsaggregator.utils.CurrencyFormat;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private String apiToken;
    private BrapiClient brapiClient;

    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }

    public void associateStock(String accountId, AssociateAccountStockDTO associateAccountStockDTO) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var stock = stockRepository.findById(associateAccountStockDTO.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                associateAccountStockDTO.quantity()
        );

        accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDTO> getListStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStockList()
                .stream()
                .map(accountStock -> new AccountStockResponseDTO(
                    accountStock.getStock().getStockId(),
                    accountStock.getQuantity(),
                        getTotal(accountStock.getQuantity(),accountStock.getStock().getStockId())
                )).toList();
    }

    private String getTotal(Integer quantity, String stockId) {
        var quote = brapiClient.getQuote(apiToken, stockId);
        var price = quote.results().stream().findFirst().get().regularMarketPrice();
        return CurrencyFormat.formactToBRL(price * quantity);
    }

}
