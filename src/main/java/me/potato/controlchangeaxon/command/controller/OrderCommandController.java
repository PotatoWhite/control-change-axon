package me.potato.controlchangeaxon.command.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.potato.controlchangeaxon.command.spec.commands.ChangeProductCountCommand;
import me.potato.controlchangeaxon.command.spec.commands.CreateOrderCommand;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderCommandController {
    private final ReactorCommandGateway commandGateway;

    @PostMapping
    public Mono<Object> createOrder() {
        var orderId = UUID.randomUUID().toString();
        return commandGateway.send(new CreateOrderCommand(orderId));
    }

    @PutMapping("{orderId}/products/{productId}")
    public Mono<Object> changeProduct(@PathVariable String orderId, @PathVariable String productId, @RequestParam int count) {
        return commandGateway.send(new ChangeProductCountCommand(orderId, productId, count));
    }
}
