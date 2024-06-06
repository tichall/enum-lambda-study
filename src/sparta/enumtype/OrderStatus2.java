package sparta.enumtype;

import java.util.Collections;
import java.util.List;

public enum OrderStatus2 {
    PREPARE ("상품주문"),
    RECEIVED ("주문접수"),
    SHIPPED ("상품발송"),
    DELIVERED ("배송완료"),
    CONFIRMED ("구매결정"),
    CANCELED ("주문취소"),
    REQUEST_EXCHANGE ("교환"),
    RESHIPPED("재배송"),
    REQUEST_RETURN ("반품"),
    REFUNDED ("환불")
    ;

    private final String desc;
    private List<OrderStatus2> availableNextStatus;

    OrderStatus2(String desc) {
        this.desc = desc;
    }

    static {
        PREPARE.setNext(List.of(RECEIVED));
        RECEIVED.setNext(List.of(SHIPPED, CANCELED));
        SHIPPED.setNext(List.of(DELIVERED));
        DELIVERED.setNext(List.of(REQUEST_EXCHANGE, REQUEST_RETURN));
        CONFIRMED.setNext(Collections.emptyList());
        CANCELED.setNext(Collections.emptyList());
        REQUEST_EXCHANGE.setNext(List.of(RECEIVED));
        RESHIPPED.setNext(Collections.emptyList());
        REQUEST_RETURN.setNext(List.of(REFUNDED));
        REFUNDED.setNext(Collections.emptyList());
    }

    private void setNext(List<OrderStatus2> availableNextStatus) {
        this.availableNextStatus = availableNextStatus;
    }

    public boolean canNext(OrderStatus2 nextStatus) {
        return this.availableNextStatus.contains(nextStatus);
    }
}
