package me.potato.controlchangeaxon.command.exeptions;

public class OrderRejectedException extends RuntimeException {
    public OrderRejectedException(String orderId) {
        super("Order " + orderId + " is rejected");
    }
}
