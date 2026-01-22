package paulodev.investmentsaggregator.client.model.dto;

import java.util.List;

public record BrapiResponseDTO(
        List<StockDTO> results) {
}
