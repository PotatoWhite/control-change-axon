package me.potato.controlchangeaxon.command.spec.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ChangeProductDetailOptionCommand(@TargetAggregateIdentifier String orderId, String productId, String comment) {
}
