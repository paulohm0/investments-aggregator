package paulodev.investmentsaggregator.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulodev.investmentsaggregator.application.service.AccountService;
import paulodev.investmentsaggregator.domain.model.dto.AccountStockResponseDTO;
import paulodev.investmentsaggregator.domain.model.dto.AssociateAccountStockDTO;
import paulodev.investmentsaggregator.domain.model.dto.CreateAccountDTO;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId, @RequestBody AssociateAccountStockDTO associateAccountStockDTO) {
        accountService.associateStock(accountId, associateAccountStockDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDTO>> getListStocks(@PathVariable("accountId") String accountId) {
        var stocksList = accountService.getListStocks(accountId);
        return ResponseEntity.ok(stocksList);
    }
}
