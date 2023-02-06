package me.potato.controlchangeaxon.query.model;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Order {
    private final Map<String, Integer> products = new HashMap<>();
    private final String orderId;
    private String address;
    private OrderStatus orderStatus = OrderStatus.CREATED;
}
