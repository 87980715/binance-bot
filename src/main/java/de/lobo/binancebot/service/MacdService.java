package de.lobo.binancebot.service;

import de.lobo.binancebot.client.model.CandleStick;
import de.lobo.binancebot.model.MacdResult;
import de.lobo.binancebot.service.util.MacdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.Decimal;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by denis on 21.07.18.
 */
@Service
public class MacdService {
    private final static Logger log = LoggerFactory.getLogger(MacdService.class);

    public MacdResult calculateMacd(List<CandleStick> candleSticks, String symbol) {
        TimeSeries series = getTimeSeries(symbol, candleSticks);

        ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
        EMAIndicator shortEma = new EMAIndicator(closePriceIndicator, 9);
        EMAIndicator longEma = new EMAIndicator(closePriceIndicator, 26);
        MACDIndicator macd = new MACDIndicator(closePriceIndicator);
        EMAIndicator signal = new EMAIndicator(macd, 9);

        MacdResult macdResult = MacdResult.builder().macd(macd)
                .longAvg(longEma)
                .shortAvg(shortEma)
                .signal(signal)
                .build();
        logMacdEval(macdResult);

        return macdResult;
    }

    private void logMacdEval(MacdResult result) {
        int endIndex = result.getMacd().getTimeSeries().getEndIndex();
        log.info("MACD-Evaluation: buy?/strongbuy?: {}/{}, MACD: {}, signal: {}",
                isPositive(result.getMacd(), result.getSignal()),
                MacdUtil.isStrongBuySignal(result, Decimal.valueOf("0.0001")),
                result.getMacd().getValue(endIndex),
                result.getSignal().getValue(endIndex));
    }

    private boolean isPositive(CachedIndicator<Decimal> first, CachedIndicator<Decimal> second) {
        return first.getValue(first.getTimeSeries().getEndIndex()).isGreaterThan(
                second.getValue(second.getTimeSeries().getEndIndex())
        );
    }

    private TimeSeries getTimeSeries(String symbol, List<CandleStick> candles) {
        TimeSeries series = new BaseTimeSeries(symbol+"-series");
        candles.forEach(candle -> {
            series.addBar(new BaseBar(Instant.ofEpochMilli(candle.getCloseTime()).atZone(ZoneId.systemDefault()),
                    candle.getOpen(),
                    candle.getHigh(),
                    candle.getLow(),
                    candle.getClose(),
                    candle.getVolume()));
        });
        return series;
    }
}
