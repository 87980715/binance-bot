package de.lobo.binancebot.client;

import de.lobo.binancebot.client.model.CoinPrice;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface BinanceClient {

    @RequestLine("GET /api/v1/exchangeInfo")
    Response getExchangeInfo();

    @RequestLine("GET /api/v3/ticker/price?symbol={symbol}")
    CoinPrice getCoinPrice(@Param("symbol") String symbol);


}
