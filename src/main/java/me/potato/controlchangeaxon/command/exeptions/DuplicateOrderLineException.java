package me.potato.controlchangeaxon.command.exeptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DuplicateOrderLineException extends RuntimeException {
    private final String orderId;
    private final String productId;
}
