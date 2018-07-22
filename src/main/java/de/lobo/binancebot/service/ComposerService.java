package de.lobo.binancebot.service;

import de.lobo.binancebot.client.model.CandleStick;
import de.lobo.binancebot.client.model.OrderResponse;
import de.lobo.binancebot.model.MacdResult;
import de.lobo.binancebot.model.OrderSide;
import de.lobo.binancebot.model.OrderType;
import de.lobo.binancebot.service.util.MacdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ta4j.core.Decimal;

import java.util.List;

/**
 * Composes several operations to complexer "business-functions"
 * Created by denis on 12.07.18.
 */
@Service
public class ComposerService {
    private final Logger log = LoggerFactory.getLogger(ComposerService.class);
    @Autowired
    private BinanceService fetcherService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MacdService macdService;

    @Value("${quantity}")
    private double quantity;

    public void logCoinPriceMovements(String symbol) throws InterruptedException {
        while (true) {
            log.info("price-movement for symbol {}: {}", symbol, fetcherService.fetchCoinPrice(symbol));
            Thread.sleep(2000L);
        }
    }

    public void tradeBasedOnMacd(String symbol, String interval) throws InterruptedException {
        log.debug("start trading based on MACD: {} at {} interval", symbol, interval);
        boolean orderActive = false;

        while (true) {
            List<CandleStick> candleSticks = fetcherService.fetchCandleStick(symbol, interval);
            MacdResult result = macdService.calculateMacd(candleSticks, symbol);
            if (MacdUtil.isStrongBuySignal(result, Decimal.valueOf(0.0001)) && !orderActive) {
                OrderResponse response = orderService.addMarketOrder(symbol, OrderSide.BUY, OrderType.MARKET, quantity);
                orderActive = true;
            }
            if (!MacdUtil.isStrongSellSignal(result, Decimal.valueOf(0.0001)) && orderActive) {
                OrderResponse response = orderService.addMarketOrder(symbol, OrderSide.SELL, OrderType.MARKET, quantity);
                orderActive = false;
            }
            Thread.sleep(2000L);
        }
    }
}
