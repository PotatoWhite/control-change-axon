package me.potato.controlchangeaxon.command;


import me.potato.controlchangeaxon.command.exeptions.OrderAlreadyConfirmedException;
import me.potato.controlchangeaxon.command.spec.OrderLineCommandSpec;
import me.potato.controlchangeaxon.command.spec.OrderLineEventSpec;
import me.potato.controlchangeaxon.command.spec.commands.ChangeProductCountCommand;
import me.potato.controlchangeaxon.command.spec.commands.ChangeProductDetailOptionCommand;
import me.potato.controlchangeaxon.command.spec.events.OrderConfirmedEvent;
import me.potato.controlchangeaxon.command.spec.events.ProductCountChangedEvent;
import me.potato.controlchangeaxon.command.spec.events.ProductDetailOptionChangedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

// order line is specific the details of the order
public class OrderLine implements OrderLineCommandSpec, OrderLineEventSpec {
    @EntityId
    private final String  productId;
    private       boolean confirm;   // 실제 모델에 없는 필드로, 이 필드가 true면 이미 주문이 확정된 상태이므로, 주문을 변경할 수 없다.


    public OrderLine(String productId, int count) {
        this.productId = productId;
        this.confirm   = false;
    }

    @Override
    @CommandHandler
    public void handle(ChangeProductCountCommand command) {
        if (confirm)
            throw new OrderAlreadyConfirmedException(command.orderId());

        AggregateLifecycle.apply(new ProductCountChangedEvent(command.orderId(), command.productId(), command.count()));
    }

    @Override
    @CommandHandler
    public void handle(ChangeProductDetailOptionCommand command) {
        if (confirm)
            throw new OrderAlreadyConfirmedException(command.orderId());

        AggregateLifecycle.apply(new ProductDetailOptionChangedEvent(command.orderId(), command.productId(), command.comment()));
    }


    @Override
    @EventSourcingHandler
    public void on(ProductCountChangedEvent event) {
    }

    @Override
    @EventSourcingHandler
    public void on(ProductDetailOptionChangedEvent event) {
    }

    @Override
    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        this.confirm = true;
    }
}
