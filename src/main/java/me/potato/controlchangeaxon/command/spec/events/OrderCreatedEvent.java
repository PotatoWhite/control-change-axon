package me.potato.controlchangeaxon.command.spec.events;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public record OrderCreatedEvent(String orderId) {
}
