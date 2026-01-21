package paulodev.investmentsaggregator.infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulodev.investmentsaggregator.application.service.StockService;
import paulodev.investmentsaggregator.domain.model.dto.CreateStockDTO;
import paulodev.investmentsaggregator.domain.model.dto.CreateUserDTO;
import paulodev.investmentsaggregator.domain.model.entity.User;

import java.net.URI;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private String apiToken;
    private StockService stockService;

    public StockController(StockService stockService, String apiToken) {
        this.stockService = stockService;
        System.out.println("✅ TOKEN CARREGADO: " + apiToken);
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO createStockDTO) {
        stockService.createStock(createStockDTO);
        return ResponseEntity.ok().build();
    }

}
