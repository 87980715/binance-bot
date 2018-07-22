package de.lobo.binancebot.client;

import de.lobo.binancebot.client.model.CandleStick;
import de.lobo.binancebot.client.model.CoinPrice;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.util.List;

public interface BinanceClient {

    @RequestLine("GET /api/v1/exchangeInfo")
    Response getExchangeInfo();

    @RequestLine("GET /api/v3/ticker/price?symbol={symbol}")
    CoinPrice getCoinPrice(@Param("symbol") String symbol);

    @RequestLine("GET /api/v1/klines?symbol={symbol}&interval={interval}")
    List<CandleStick> getCandleStickData(@Param("symbol") String symbol, @Param("interval") String interval);



}
