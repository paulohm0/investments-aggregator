package paulodev.investmentsaggregator.infra.client.model.dto;

import java.util.List;

public record BrapiResponseDTO(List<StockDTO> results) {
}
