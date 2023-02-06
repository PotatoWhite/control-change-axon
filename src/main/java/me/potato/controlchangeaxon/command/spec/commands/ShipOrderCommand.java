package me.potato.controlchangeaxon.command.spec.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ShipOrderCommand(@TargetAggregateIdentifier String orderId, String address) {
}
