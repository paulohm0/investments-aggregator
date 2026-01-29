package paulodev.investmentsaggregator.application.service;

import org.springframework.stereotype.Service;
import paulodev.investmentsaggregator.domain.exception.InvestmentsAggregatorExceptions;
import paulodev.investmentsaggregator.domain.model.dto.CreateStockDTO;
import paulodev.investmentsaggregator.domain.model.entity.Stock;
import paulodev.investmentsaggregator.domain.repository.StockRepository;

@Service
public class StockService {

    private StockRepository stockRepository;
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDTO createStockDTO) {

        if (stockRepository.existsById(createStockDTO.stockId())) {
            throw new InvestmentsAggregatorExceptions.StockAlreadyExistsException(createStockDTO.stockId());
        }
        var stock =  new Stock(
                createStockDTO.stockId(),
                createStockDTO.description());
        stockRepository.save(stock);
    }
}
