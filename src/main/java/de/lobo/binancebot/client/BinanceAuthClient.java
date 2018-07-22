package de.lobo.binancebot.client;

import de.lobo.binancebot.client.model.OrderResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface BinanceAuthClient {

    @RequestLine("POST /api/v3/order")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    OrderResponse addOrder(@Param("symbol") String symbol,
                           @Param("side") String side,
                           @Param("type") String type,
                           @Param("timeInForce") String timeInForce,
                           @Param("quantity") String quantity,
                           @Param("price") String price);
}
