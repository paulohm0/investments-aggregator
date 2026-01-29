package paulodev.investmentsaggregator.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paulodev.investmentsaggregator.domain.model.entity.AccountStock;
import paulodev.investmentsaggregator.domain.model.valueobject.AccountStockId;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> { }
