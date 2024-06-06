package sparta.model;

import sparta.enumtype.OrderStatus;

public class Order {
    private String productName;
    private OrderStatus status;

    public Order(String productName, OrderStatus status) {
        this.productName = productName;
        this.status = status;
    }

    public boolean isChangeable(OrderStatus nextStatus) {
        return status.canNext(nextStatus);
    }
}
