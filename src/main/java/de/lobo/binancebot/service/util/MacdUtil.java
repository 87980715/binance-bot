package de.lobo.binancebot.service.util;

import de.lobo.binancebot.model.MacdResult;
import org.ta4j.core.Decimal;

/**
 * Created by denis on 21.07.18.
 */
public class MacdUtil {

    /**
     * evaluates if a buy signal can be obtained for macd values
     * follows a simple isGreatherThan semantic
     * @param macdResult
     * @return
     */
    public static boolean isSimpleBuySignal(MacdResult macdResult) {
        return macdResult.getMacd().getValue(
                macdResult.getMacd().getTimeSeries().getEndIndex()
        ).isGreaterThan(macdResult.getSignal().getValue(
                macdResult.getSignal().getTimeSeries().getEndIndex()
        ));
    }

    /**
     * evals if a strong buy signal can be obtained from macd values
     * a strong buy signal is determined, if macd > signal + dec
     * @return
     */
    public static boolean isStrongBuySignal(MacdResult macdResult, Decimal dec) {
        return macdResult.getMacd().getValue(
                macdResult.getMacd().getTimeSeries().getEndIndex()
        ).isGreaterThan(macdResult.getSignal().getValue(
                macdResult.getSignal().getTimeSeries().getEndIndex()
        ).plus(dec));
    }

    /**
     * evals if a strong sell signal can be obtained from macd values
     * a strong sell signal is determined, if macd < signal + dec
     * @return
     */
    public static boolean isStrongSellSignal(MacdResult macdResult, Decimal dec) {
        return macdResult.getMacd().getValue(
                macdResult.getMacd().getTimeSeries().getEndIndex()
        ).isLessThanOrEqual(macdResult.getSignal().getValue(
                macdResult.getSignal().getTimeSeries().getEndIndex()
        ).plus(dec));
    }

    public static boolean isSellSignal(MacdResult result) {
        return result.getMacd().getValue(
                result.getMacd().getTimeSeries().getEndIndex()
        ).isLessThanOrEqual(result.getSignal().getValue(
                result.getSignal().getTimeSeries().getEndIndex()
        ));
    }
}
