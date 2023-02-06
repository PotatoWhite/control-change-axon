package me.potato.controlchangeaxon.query.spec.queries;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TotalProductsShippedQuery {
    private final String productId;
}
