package paulodev.investmentsaggregator.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import paulodev.investmentsaggregator.domain.model.dto.AccountStockResponseDTO;
import paulodev.investmentsaggregator.domain.model.dto.AssociateAccountStockDTO;
import paulodev.investmentsaggregator.domain.model.entity.AccountStock;
import paulodev.investmentsaggregator.domain.model.entity.Stock;
import paulodev.investmentsaggregator.domain.model.entity.User;
import paulodev.investmentsaggregator.domain.model.valueobject.AccountStockId;
import paulodev.investmentsaggregator.domain.repository.AccountRepository;
import paulodev.investmentsaggregator.domain.repository.AccountStockRepository;
import paulodev.investmentsaggregator.domain.repository.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
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

    public List<AccountStockResponseDTO>  getListStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStockList()
                .stream()
                .map(accountStock -> new AccountStockResponseDTO(
                    accountStock.getStock().getStockId(),
                    accountStock.getQuantity(),
                    0.0))
                .toList();
    }

}
