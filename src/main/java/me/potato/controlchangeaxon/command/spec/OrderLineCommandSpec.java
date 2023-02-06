package me.potato.controlchangeaxon.command.spec;

import me.potato.controlchangeaxon.command.spec.commands.ChangeProductCountCommand;
import me.potato.controlchangeaxon.command.spec.commands.ChangeProductDetailOptionCommand;

public interface OrderLineCommandSpec {
    void handle(ChangeProductCountCommand command);
    void handle(ChangeProductDetailOptionCommand command);
}
