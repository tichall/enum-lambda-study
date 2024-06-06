package sparta.enumtype;

import java.util.Collections;
import java.util.List;

public enum OrderStatus {
    PREPARE ("상품주문", Available.PREPARE.getAvailableNextStatus()),
    RECEIVED ("주문접수", Available.RECEIVED.getAvailableNextStatus()),
    SHIPPED ("상품발송", Available.SHIPPED.getAvailableNextStatus()),
    DELIVERED ("배송완료", Available.DELIVERED.getAvailableNextStatus()),
    CONFIRMED ("구매결정", Available.CONFIRMED.getAvailableNextStatus()),
    CANCELED ("주문취소", Available.CANCELED.getAvailableNextStatus()),
    REQUEST_EXCHANGE ("교환", Available.REQUEST_EXCHANGE.getAvailableNextStatus()),
    RESHIPPED("재배송", Available.RESHIPPED.getAvailableNextStatus()),
    REQUEST_RETURN ("반품", Available.REQUEST_RETURN.getAvailableNextStatus()),
    REFUNDED ("환불", Available.REFUNDED.getAvailableNextStatus());

    private final String desc;
    private final List<OrderStatus> availableNextStatus;

    OrderStatus(String desc, List<OrderStatus> availableNextStatus) {
        this.desc = desc;
        this.availableNextStatus = availableNextStatus;
    }

    public boolean canNext(OrderStatus nextStatus) {
        return this.availableNextStatus.contains(nextStatus);
    }

    private enum Available {
        PREPARE(List.of(OrderStatus.RECEIVED)),
        RECEIVED(List.of(OrderStatus.SHIPPED, OrderStatus.CANCELED)),
        SHIPPED(List.of(OrderStatus.DELIVERED)),
        DELIVERED(List.of(OrderStatus.REQUEST_EXCHANGE, OrderStatus.REQUEST_RETURN)),
        CONFIRMED(Collections.emptyList()),
        CANCELED(Collections.emptyList()),
        REQUEST_EXCHANGE(List.of(OrderStatus.RECEIVED)),
        RESHIPPED(Collections.emptyList()),
        REQUEST_RETURN(List.of(OrderStatus.REFUNDED)),
        REFUNDED(Collections.emptyList());

        private final List<OrderStatus> availableNextStatus;

        Available(List<OrderStatus> availableNextStatus) {
            this.availableNextStatus = availableNextStatus;
        }

        public List<OrderStatus> getAvailableNextStatus() {
            return availableNextStatus;
        }
    }
}
