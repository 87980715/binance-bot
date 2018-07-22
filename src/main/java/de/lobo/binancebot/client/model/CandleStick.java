package de.lobo.binancebot.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

/**
 * Created by denis on 21.07.18.
 */
@Data
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder()
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandleStick {
    private Long openTime;

    private String open;

    private String high;

    private String low;

    private String close;

    private String volume;

    private Long closeTime;

    private String quoteAssetVolume;

    private Long numberOfTrades;

    private String takerBuyBaseAssetVolume;

    private String takerBuyQuoteAssetVolume;
}
