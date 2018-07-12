package de.lobo.binancebot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BinanceConfig {

    @Value("${binance.api.base}")
    private String baseUrl;
}
