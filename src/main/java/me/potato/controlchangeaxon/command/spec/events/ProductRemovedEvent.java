package me.potato.controlchangeaxon.command.spec.events;

public record ProductRemovedEvent(String orderId, String productId, String detailOption) {
}
