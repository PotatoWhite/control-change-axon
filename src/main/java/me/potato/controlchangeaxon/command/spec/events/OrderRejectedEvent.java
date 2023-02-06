package me.potato.controlchangeaxon.command.spec.events;

public record OrderRejectedEvent(String orderId, String reason) {

}
