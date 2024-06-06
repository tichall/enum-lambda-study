import sparta.SampleUsers;
import sparta.User;
import sparta.enumtype.DiscountEvent;
import sparta.enumtype.OrderStatus;
import sparta.model.Order;
import sparta.service.Coupon;
import sparta.service.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        lambdaTest();
        enumTest();
        testChangeStatus();
    }

    private static void testChangeStatus() {
        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("--------------     배송상태 변경 테스트     ---------------------------------");
        System.out.println("------------------------------------------------------------------------");

        final Order iphone = new Order("아이폰135 Super Ultra Pro Max", OrderStatus.SHIPPED);
        System.out.println("상품발송 --> 상품주문 = " + (iphone.isChangeable(OrderStatus.PREPARE)));
        System.out.println("상품발송 --> 재배송 = " + (iphone.isChangeable(OrderStatus.RESHIPPED)));
        System.out.println("상품발송 --> 배송완료 = " + (iphone.isChangeable(OrderStatus.DELIVERED)));

        final Order maratang = new Order("마라탕", OrderStatus.CONFIRMED);
        System.out.println("구매결정 --> 배송완료 = " + (maratang.isChangeable(OrderStatus.DELIVERED)));
        System.out.println("구매결정 --> 구매결정 = " + (maratang.isChangeable(OrderStatus.CONFIRMED)));
    }

    private static void enumTest() {
        final Coupon coupon = new Coupon("특가쿠폰", 5000);
        final Product product = new Product("아이폰135 Super Ultra Pro Max", 20_000_000);

        final int couponPrice = coupon.calcPrice(DiscountEvent.SUMMER);
        final int productPrice = product.calcPrice(DiscountEvent.WINTER);

        System.out.println("couponPrice = " + couponPrice);
        System.out.println("productPrice = " + productPrice);
    }

    private static void lambdaTest() {
        final SampleUsers users = new SampleUsers();
        users.init();

        List<User> users1 = users.ageGreaterThan(30);
        List<User> users2 = users.ageEquals(30);
        List<User> users3 = users.ageBetween(10, 20);
        List<User> users4 = users.genderEquals("여성");
        List<User> users5 = users.burgerEquals("맥도날드");

        List<User> lUser1 = users.getUsersBy((user) -> user.getAge() > 30);
        List<User> lUser2 = users.getUsersBy((user) -> user.getAge() == 30);
        List<User> lUser3 = users.getUsersBy((user) -> user.getAge() >= 10 && user.getAge() <= 20);
        List<User> lUser4 = users.getUsersBy((user) -> "여성".equalsIgnoreCase(user.getGender()));
        List<User> lUser5 = users.getUsersBy((user) -> "맥도날드".equalsIgnoreCase(user.getBurger()));

        // 30세 초과
        System.out.println("#####   30세 초과   #####");
        System.out.println("01: " + users1);
        System.out.println("L1: " + lUser1);
        System.out.println();

        // 30세
        System.out.println("#####   30세   #####");
        System.out.println("02: " + users2);
        System.out.println("L2: " + lUser2);
        System.out.println();

        // 10 ~ 20세 사이
        System.out.println("#####   10 ~ 20세 사이   #####");
        System.out.println("03: " + users3);
        System.out.println("L3: " + lUser3);
        System.out.println();

        // 여성
        System.out.println("#####   여성   #####");
        System.out.println("04: " + users4);
        System.out.println("L4: " + lUser4);
        System.out.println();

        // 맥도날드
        System.out.println("#####   맥도날드   #####");
        System.out.println("04: " + users5);
        System.out.println("L4: " + lUser5);
        System.out.println();

        /*
        // 국적
        System.out.println("#####   국적   #####");
        */

        /*
        // 반민초(이단)
        System.out.println("#####   이단   #####");*/
    }
}