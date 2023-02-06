package me.potato.controlchangeaxon.command.exeptions;

public class UnconfirmedOrderException extends RuntimeException {
    public UnconfirmedOrderException(String orderId) {
        super("Order " + orderId + " is not confirmed yet");
    }
}
