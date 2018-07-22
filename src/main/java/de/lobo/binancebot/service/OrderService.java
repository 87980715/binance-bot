package de.lobo.binancebot.service;

import de.lobo.binancebot.client.BinanceAuthClient;
import de.lobo.binancebot.client.model.OrderResponse;
import de.lobo.binancebot.model.OrderSide;
import de.lobo.binancebot.model.OrderType;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by denis on 15.07.18.
 */
@Service
public class OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private BinanceAuthClient binanceAuthClient;

    public OrderResponse addMarketOrder(String symbol, OrderSide side, OrderType type, Double quantity) {
        OrderResponse response = binanceAuthClient.addOrder(symbol, side.name(), type.name(), null, String.valueOf(quantity), null);
        log.info("placed order @binance: ", response);
        return response;
    }
}
