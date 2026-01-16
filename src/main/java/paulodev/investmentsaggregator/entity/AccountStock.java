package paulodev.investmentsaggregator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_accounts-stocks")
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Stock stock;

}
