package de.lobo.binancebot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Composes several operations to complexer "business-functions"
 * Created by denis on 12.07.18.
 */
@Service
public class BinanceComposingService {
    private final Logger log = LoggerFactory.getLogger(BinanceComposingService.class);

    @Value("${observingPeriodInMinutes}")
    private long observingPeriodInMinutes;

    @Value("${fetchIntervalInSeconds}")
    private long fetchIntervalInSeconds;

    @Autowired
    private BinanceFetcherService fetcherService;

    @Autowired
    private BinanceAnalyzerService analyzerService;

    public void analyzeCoinPriceForPeriodWithInterval(String symbol) throws InterruptedException {
        log.info("starting analyze of {}, period: {}min., fetch-interval: {}s.", symbol, observingPeriodInMinutes, fetchIntervalInSeconds);
        long sleepTimeInMilli = 0;
        double initialValue = Double.MIN_VALUE, currentValue;

        while (true) {
            long start = System.currentTimeMillis();

            currentValue = Double.valueOf(fetcherService.fetchCoinPrice(symbol).getPrice());

            if (initialValue == Double.MIN_VALUE) {
                initialValue = currentValue;
            }

            Thread.sleep(TimeUnit.MILLISECONDS.convert(fetchIntervalInSeconds, TimeUnit.SECONDS));
            long end = System.currentTimeMillis();
            sleepTimeInMilli += (end - start);

            analyzerService.logPriceDiff(symbol, initialValue, currentValue);

            //reset after observingPeriod;
            if (sleepTimeInMilli > TimeUnit.MILLISECONDS.convert(observingPeriodInMinutes, TimeUnit.MINUTES)) {
                log.info("resetting initial-price from {} to most likely: {}", initialValue, currentValue);
                sleepTimeInMilli = 0;
                initialValue = Double.MIN_VALUE;
            }
        }
    }
}
