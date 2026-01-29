package paulodev.investmentsaggregator.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paulodev.investmentsaggregator.domain.model.entity.Stock;


@Repository
public interface StockRepository extends JpaRepository<Stock, String> { }
