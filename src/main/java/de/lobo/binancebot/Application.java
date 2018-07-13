package de.lobo.binancebot;

import de.lobo.binancebot.client.BinanceAuthClient;
import de.lobo.binancebot.client.model.OrderResponse;
import de.lobo.binancebot.service.BinanceComposingService;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    @Value("${symbol:BTCUSDT}")
    private String symbol;

    @Autowired
    private BinanceComposingService binanceComposingService;

    @Autowired
    private BinanceAuthClient binanceAuthClient;

    public static void main (String [] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        binanceComposingService.analyzeCoinPriceForPeriodWithInterval(symbol);
//       OrderResponse resp =  binanceAuthClient.addOrder("BTCUSDT", "SELL", "LIMIT", "GTC", "0.002", "6444.44");
//       log.info("binance-response: {}", resp);
    }
}
