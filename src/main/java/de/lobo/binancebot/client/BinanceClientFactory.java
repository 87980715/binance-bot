package de.lobo.binancebot.client;

import de.lobo.binancebot.client.auth.SigningRequestInterceptor;
import de.lobo.binancebot.config.BinanceConfig;
import feign.Feign;
import feign.Logger;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BinanceClientFactory {

    @Autowired
    private BinanceConfig binanceConfig;

    @Autowired
    private SigningRequestInterceptor signingRequestInterceptor;

    @Bean
    public BinanceClient createBinanceClient() {
        return Feign
                .builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .target(BinanceClient.class, binanceConfig.getBaseUrl());
    }

    @Bean
    public BinanceAuthClient createBinanceAuthClient() {
        return Feign
                .builder()
                .encoder(new FormEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(signingRequestInterceptor)
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(BinanceAuthClient.class, binanceConfig.getBaseUrl());
    }
}
