package me.potato.controlchangeaxon.command.spec.events;

public record ProductDetailOptionChangedEvent(String orderId, String productId, String comment) {
}
