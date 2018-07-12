package de.lobo.binancebot.client.model;

import lombok.Data;

@Data
public class CoinPrice {
    private String symbol;
    private String price;
}
