package paulodev.investmentsaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestmentsAggregatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(InvestmentsAggregatorApplication.class, args);
	}
}
