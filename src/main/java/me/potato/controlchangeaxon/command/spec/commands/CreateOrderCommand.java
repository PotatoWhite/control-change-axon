package me.potato.controlchangeaxon.command.spec.commands;

import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateOrderCommand(@TargetAggregateIdentifier String orderId) {
}