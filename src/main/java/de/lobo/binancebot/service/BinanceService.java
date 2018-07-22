package de.lobo.binancebot.service;

import de.lobo.binancebot.client.BinanceClient;
import de.lobo.binancebot.client.model.CandleStick;
import de.lobo.binancebot.client.model.CoinPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinanceService {
    private final static Logger log = LoggerFactory.getLogger(BinanceService.class);

    @Autowired
    private BinanceClient binanceClient;

    public CoinPrice fetchCoinPrice(String symbol) {
        CoinPrice price = binanceClient.getCoinPrice(symbol);
        log.debug("fetched coin-price for symbol: {} has price {}", symbol, price);
        return price;
    }

    public List<CandleStick> fetchCandleStick(String symbol, String interval) {
        List<CandleStick> candleSticks = binanceClient.getCandleStickData(symbol, interval);
        log.debug("fetched candlesticks for symbol {}, candlesticks: {}", candleSticks);
        return candleSticks;
    }
}
