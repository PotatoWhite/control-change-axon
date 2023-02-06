package me.potato.controlchangeaxon.command.spec.events;

public record ProductAddedEvent(String orderId, String productId, Integer count, String detailOption) {
}
