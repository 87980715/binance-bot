package de.lobo.binancebot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * analyzes coin prices for specified symbol, provided as doubles and calculates, if price change exceeds given alertMargin
 * current does not do any handling, it just logs the result
 * TODO: add hook for handling/orders
 */
@Service
public class BinanceAnalyzerService {
    private final Logger log = LoggerFactory.getLogger(BinanceAnalyzerService.class);
    private final static DecimalFormat df = new DecimalFormat("##0.0000");

    @Value("${alertMargin}")
    private Double alertMargin;

    public void logPriceDiff(String symbol, double initial, double current) {
        double alertMarginAsCoinValue = initial * alertMargin;
        double diff = current - initial;
        String diffInPercent = df.format(diff/initial*100)+"%";

        if (diff > alertMarginAsCoinValue) {
            log.warn("{} value rised more than {}", symbol, diffInPercent);
        } else if (diff < -alertMarginAsCoinValue) {
            log.warn("{} value bumped more than {}", symbol, diffInPercent);
        } else {
            log.info("{} value-variation: {}, examined base-price: {} with current price {}; alert-margin: {}", symbol, diffInPercent, initial, current, df.format(alertMargin*100));
        }
    }
}
