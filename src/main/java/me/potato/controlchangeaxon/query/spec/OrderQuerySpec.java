package me.potato.controlchangeaxon.query.spec;

import me.potato.controlchangeaxon.query.model.Order;
import me.potato.controlchangeaxon.query.spec.queries.FindAllOrderedProductsQuery;
import me.potato.controlchangeaxon.query.spec.queries.TotalProductsShippedQuery;

import java.util.List;

public interface OrderQuerySpec {
    List<Order> handle(FindAllOrderedProductsQuery query);

    Integer handle(TotalProductsShippedQuery query);
}
