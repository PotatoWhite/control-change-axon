package me.potato.controlchangeaxon.command.spec;


import me.potato.controlchangeaxon.command.spec.events.*;

public interface OrderEventSpec {
    void on(OrderCreatedEvent event);

    void on(ProductAddedEvent event);

    void on(ProductRemovedEvent event);

    void on(OrderConfirmedEvent event);

    void on(OrderShippedEvent event);
}
