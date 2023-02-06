package me.potato.controlchangeaxon.command.spec.events;

public record ProductCountChangedEvent(String orderId, String productId, int count) {
}
