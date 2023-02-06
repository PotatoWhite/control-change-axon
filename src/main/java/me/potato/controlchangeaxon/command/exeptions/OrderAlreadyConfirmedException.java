package me.potato.controlchangeaxon.command.exeptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderAlreadyConfirmedException extends RuntimeException {
    private final String orderId;
}
