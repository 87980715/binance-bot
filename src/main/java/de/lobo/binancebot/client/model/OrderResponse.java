package de.lobo.binancebot.client.model;

import lombok.Data;

@Data
public class OrderResponse {
    private String symbol;
    private String orderId;
    private String clientOrderId;
    private long transactTime;
    private double price;
    private double origQty;
    private double executedQty;
    private String status;
    private String timeInForce;
    private String type;
    private String side;
}
