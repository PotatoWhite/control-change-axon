package me.potato.controlchangeaxon.query;

import me.potato.controlchangeaxon.query.model.Order;
import me.potato.controlchangeaxon.query.spec.OrderQuerySpec;
import me.potato.controlchangeaxon.query.spec.queries.FindAllOrderedProductsQuery;
import me.potato.controlchangeaxon.query.spec.queries.TotalProductsShippedQuery;
import org.axonframework.eventhandling.EventHandler;

import java.util.List;

public class OrderQueryService implements OrderQuerySpec {
    @Override
    @EventHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        return null;
    }

    @Override
    @EventHandler
    public Integer handle(TotalProductsShippedQuery query) {
        return null;
    }
}
