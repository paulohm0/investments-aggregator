package paulodev.investmentsaggregator.infra.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:api-token.properties")
public class TokenConfig {

    @Value("${api.token}")
    private String token;

    @Bean
    public String apiToken() {
        return token;
    }
}
