package me.potato.controlchangeaxon.command.spec;

import me.potato.controlchangeaxon.command.spec.commands.*;

public interface OrderCommandSpec {
    void initialize(CreateOrderCommand command);

    void handle(AddProductCommand command);

    void handle(RemoveProductCommand command);

    void handle(ConfirmOrderCommand command);

    void handle(ShipOrderCommand command);
}
