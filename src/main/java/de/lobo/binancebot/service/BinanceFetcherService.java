package de.lobo.binancebot.service;

import de.lobo.binancebot.client.BinanceClient;
import de.lobo.binancebot.client.model.CoinPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BinanceFetcherService {
    private final static Logger log = LoggerFactory.getLogger(BinanceFetcherService.class);

    @Autowired
    private BinanceClient binanceClient;

    public CoinPrice fetchCoinPrice(String symbol) {
        CoinPrice price = binanceClient.getCoinPrice(symbol);
        log.debug("fetched coin-price from binance: {}", price);
        return price;
    }
}
