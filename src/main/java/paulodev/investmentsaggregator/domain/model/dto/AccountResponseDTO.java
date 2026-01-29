package paulodev.investmentsaggregator.domain.model.dto;

import paulodev.investmentsaggregator.domain.model.entity.Account;

public record AccountResponseDTO(String accountId, String description) {
    public AccountResponseDTO(Account account) {
        this(account.getAccountId().toString(), account.getDescription());
    }
}
