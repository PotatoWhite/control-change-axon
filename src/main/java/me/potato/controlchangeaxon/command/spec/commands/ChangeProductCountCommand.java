package me.potato.controlchangeaxon.command.spec.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ChangeProductCountCommand(@TargetAggregateIdentifier String orderId, String productId, int count) {
}
