package me.potato.controlchangeaxon.command.spec;

import me.potato.controlchangeaxon.command.spec.events.OrderConfirmedEvent;
import me.potato.controlchangeaxon.command.spec.events.ProductCountChangedEvent;
import me.potato.controlchangeaxon.command.spec.events.ProductDetailOptionChangedEvent;

public interface OrderLineEventSpec {
    void on(ProductCountChangedEvent event);

    void on(ProductDetailOptionChangedEvent event);

    void on(OrderConfirmedEvent event);
}
