package me.potato.controlchangeaxon.command.spec.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record RemoveProductCommand(@TargetAggregateIdentifier String orderId, String productId, String detailOption) {
}
