package me.potato.controlchangeaxon.command.spec.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddProductCommand(@TargetAggregateIdentifier String orderId, String productId, int count, String detailOption) {
}
