package de.lobo.binancebot;

import de.lobo.binancebot.client.BinanceAuthClient;
import de.lobo.binancebot.service.ComposerService;
import de.lobo.binancebot.service.MacdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    @Value("${symbol:BTCUSDT}")
    private String symbol;

    @Value("${operation.mode}")
    private String mode;

    @Autowired
    private ComposerService binanceComposingService;

    @Autowired
    private BinanceAuthClient binanceAuthClient;

    @Autowired
    private MacdService macdService;

    public static void main (String [] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        if (StringUtils.isEmpty(mode)) {
            log.info("starting analyze {} - logging only", symbol);
            binanceComposingService.logCoinPriceMovements(symbol);
        }

        if (mode.equalsIgnoreCase("trademacd")) {
            log.info("starting trading {} based on MACD", symbol);
            binanceComposingService.tradeBasedOnMacd("XLMUSDT", "5m");
        }
    }
}
