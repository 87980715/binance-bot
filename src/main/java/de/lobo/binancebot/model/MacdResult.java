package de.lobo.binancebot.model;

import lombok.Builder;
import lombok.Data;
import org.ta4j.core.Decimal;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;

/**
 * Created by denis on 21.07.18.
 */
@Data
@Builder
public class MacdResult {
    private EMAIndicator signal;
    private MACDIndicator macd;
    private EMAIndicator shortAvg;
    private EMAIndicator longAvg;
}
