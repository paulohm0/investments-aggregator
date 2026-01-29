package paulodev.investmentsaggregator.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import paulodev.investmentsaggregator.infra.client.model.dto.BrapiResponseDTO;

@FeignClient(
        name = "BrapiClient",
        url = "https://brapi.dev"
)
public interface BrapiClient {

    @GetMapping(value = "/api/quote/{stockId}")
    BrapiResponseDTO getQuote(@RequestParam("apiToken") String apiToken, @PathVariable("stockId") String stockId);
}
