package de.lobo.binancebot;

import de.lobo.binancebot.service.BinanceComposingService;
import de.lobo.binancebot.service.BinanceFetcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private BinanceComposingService binanceComposingService;

    public static void main (String [] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        binanceComposingService.analyzeCoinPriceForPeriodWithInterval("XRPUSDT");
    }
}
