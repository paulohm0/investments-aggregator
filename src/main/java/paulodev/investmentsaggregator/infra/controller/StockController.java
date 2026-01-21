package paulodev.investmentsaggregator.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paulodev.investmentsaggregator.application.service.StockService;
import paulodev.investmentsaggregator.domain.model.dto.CreateStockDTO;
import paulodev.investmentsaggregator.domain.model.dto.CreateUserDTO;
import paulodev.investmentsaggregator.domain.model.entity.User;

import java.net.URI;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO createStockDTO) {
        stockService.createStock(createStockDTO);
        return ResponseEntity.ok().build();
    }
}
