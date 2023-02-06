package me.potato.controlchangeaxon.command;

import lombok.NoArgsConstructor;
import me.potato.controlchangeaxon.command.exeptions.OrderRejectedException;
import me.potato.controlchangeaxon.command.exeptions.UnconfirmedOrderException;
import me.potato.controlchangeaxon.command.spec.OrderCommandSpec;
import me.potato.controlchangeaxon.command.spec.OrderEventSpec;
import me.potato.controlchangeaxon.command.spec.commands.*;
import me.potato.controlchangeaxon.command.spec.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.Map;

// aggregate 를 구현할 때는 CommandHandler 와 EventSourcingHandler 를 구현해야 한다.
// CommandHandler 는 Command 를 받아서 Event 를 발생시키는 역할을 한다.
// EventSourcingHandler 는 Event 를 받아서 Aggregate 상태를 변경하는 역할을 한다.


@NoArgsConstructor
@Aggregate
public class OrderAggregate implements OrderCommandSpec, OrderEventSpec {
    @AggregateIdentifier
    private String  orderId;
    private boolean confirmed;  // 주문 완료 여부로 사용, 모델에는 없음, aggregate에서만 사용

    @AggregateMember
    private Map<String, OrderLine> orderLines;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        initialize(command);
    }

    @Override
    public void initialize(CreateOrderCommand command) {
        AggregateLifecycle.apply(new OrderCreatedEvent(command.orderId()));
    }

    @Override
    @CommandHandler
    public void handle(AddProductCommand command) {
        if (confirmed) {
            // 주문이 완료된 Order에는 Product를 추가할 수 없다.
            // CommandHandler 에서 Exception 을 발생시키면 Command 는 실패하고, Aggregate 상태는 변경되지 않는다.
            // 별도의 Event 로 처리하지 않는 이유는 Aggregate 상태가 변경되지 않기 때문이다.
            throw new UnconfirmedOrderException("Order is already confirmed");
        }

        AggregateLifecycle.apply(new ProductAddedEvent(command.orderId(), command.productId(), command.count(), command.detailOption()));
    }

    @Override
    @CommandHandler
    public void handle(RemoveProductCommand command) {
        if (confirmed) {
            // 주문이 완료된 Order에는 Product를 제거할 수 없다.
            // CommandHandler 에서 Exception 을 발생시키면 Command 는 실패하고, Aggregate 상태는 변경되지 않는다.
            // 별도의 Event 로 처리하지 않는 이유는 Aggregate 상태가 변경되지 않기 때문이다.
            throw new UnconfirmedOrderException("Order is already confirmed");
        }

        AggregateLifecycle.apply(new ProductRemovedEvent(command.orderId(), command.productId(), command.detailOption()));
    }

    @Override
    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        if (orderLines.isEmpty()) {
            // 주문내역이 비어있는 Order는 Confirm 할 수 없다.
            // CommandHandler 에서 Exception 을 발생시키면 Command 는 실패하고, Aggregate 상태는 변경되지 않는다.
            // 별도의 Event 로 처리하지 않는 이유는 Aggregate 상태가 변경되지 않기 때문이다.
            throw new OrderRejectedException("Order is empty");
        }

        if (!confirmed) {
            AggregateLifecycle.apply(new OrderConfirmedEvent(command.orderId()));
        }
    }


    @Override
    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (!confirmed)
            throw new UnconfirmedOrderException("Order is not confirmed");

        AggregateLifecycle.apply(new OrderShippedEvent(command.orderId(), command.address()));
    }

    @Override
    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId    = event.orderId();
        this.confirmed  = false;
        this.orderLines = new HashMap<>();
    }

    @Override
    @EventSourcingHandler
    public void on(ProductAddedEvent event) {
        orderLines.put(event.productId(), new OrderLine(event.productId(), event.count()));
    }

    @Override
    @EventSourcingHandler
    public void on(ProductRemovedEvent event) {
        orderLines.remove(event.productId());
    }

    @Override
    public void on(OrderConfirmedEvent event) {

    }

    @Override
    public void on(OrderShippedEvent event) {
        this.confirmed = true;
    }
}
