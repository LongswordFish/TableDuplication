package ca.wonderfish.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFlux
@ComponentScan
public class WebFluxConfig implements WebFluxConfigurer
{

    @Bean
    public WebClient getWebClient()
    {
        return WebClient.builder()
                .baseUrl("https://api.placekey.io/v1/placekey")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}