package com.example.test.mapping;

import com.example.domain.model.*;
import com.example.domain.repository.order.OrderRepository;
import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/mapping/MappingTest.xml"})
public class MappingTest {

    @Inject
    OrderRepository orderRepository;


    @Test
    public void findOneBy1() {
        Order order = orderRepository.findOne(1);

        assertOrderOf1(order);

    }

    @Test
    public void findOneBy1_2() {
        Order order = orderRepository.findOne(1);

        List<Category> categories = order.getOrderItems().iterator().next().getItem().getCategories();
        System.out.println("★★★★★★★★1");
        Category category = categories.get(0);
        System.out.println("★★★★★★★★2");
        System.out.println(category.getCode());
        System.out.println("★★★★★★★★3");
//        List<Item> items = category.getItems();
        System.out.println("★★★★★★★★4");
//        Item item = items.get(0);
        System.out.println("★★★★★★★★5");
//        System.out.println(item.getCode());
        System.out.println("★★★★★★★★6");
    }

    @Test
    public void findOneBy2() {
        Order order = orderRepository.findOne(2);

        assertOrderOf2(order);

    }

    @Test
    public void findAllDiv() {
        List<Order> orders;

        orders = orderRepository.findPage(new PageRequest(0, 1));
        assertOrderOf2(orders.get(0));

        orders = orderRepository.findPage(new PageRequest(1, 1));
        assertOrderOf1(orders.get(0));

    }

    @Test
    public void findAll() {
        List<Order> orders;

        orders = orderRepository.findPage(new PageRequest(0, 10));
        assertOrderOf2(orders.get(0));
        assertOrderOf1(orders.get(1));

    }

    @Test
    public void findAllEmpty() {
        List<Order> orders;

        orders = orderRepository.findPage(new PageRequest(2, 2));

        assertThat(orders.size(), is(0));

    }

    private void assertOrderOf1(Order order) {
        OrderStatus orderStatus = order.getOrderStatus();
        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderCoupon> orderCoupons = order.getOrderCoupons();
        OrderItem orderItem;
        Item item;
        List<Category> categories;
        Category category;
        OrderCoupon orderCoupon;
        Coupon coupon;


        assertThat(order.getId(), is(1));
        assertThat(orderStatus.getCode(), is("accepted"));
        assertThat(orderStatus.getName(), is("Order accepted"));
        assertThat(orderItems.size(), is(2));
        assertThat(orderCoupons.size(), is(2));

        orderItem = orderItems.get(0);
        item = orderItem.getItem();
        categories = item.getCategories();
        assertThat(orderItem.getOrderId(), is(1));
        assertThat(orderItem.getQuantity(), is(1));
        assertThat(item.getCode(), is("ITM0000001"));
        assertThat(item.getName(), is("Orange juice"));
        assertThat(item.getPrice(), is(100));
        assertThat(categories.size(), is(1));
        category = categories.get(0);
        assertThat(category.getCode(), is("CTG0000001"));
        assertThat(category.getName(), is("Drink"));
        System.out.println(category.getItems());

        orderItem = orderItems.get(1);
        item = orderItem.getItem();
        categories = item.getCategories();
        assertThat(orderItem.getOrderId(), is(1));
        assertThat(orderItem.getQuantity(), is(2));
        assertThat(item.getCode(), is("ITM0000002"));
        assertThat(item.getName(), is("NotePC"));
        assertThat(item.getPrice(), is(100000));
        assertThat(categories.size(), is(2));
        category = categories.get(0);
        assertThat(category.getCode(), is("CTG0000002"));
        assertThat(category.getName(), is("PC"));
        category.getItems();
        category = categories.get(1);
        assertThat(category.getCode(), is("CTG0000003"));
        assertThat(category.getName(), is("Hot selling"));
        System.out.println(category.getItems());


        orderCoupon = orderCoupons.get(0);
        coupon = orderCoupon.getCoupon();
        assertThat(orderCoupon.getOrderId(), is(1));
        assertThat(coupon.getCode(), is("CPN0000001"));
        assertThat(coupon.getName(), is("Join coupon"));
        assertThat(coupon.getPrice(), is(3000));

        orderCoupon = orderCoupons.get(1);
        coupon = orderCoupon.getCoupon();
        assertThat(orderCoupon.getOrderId(), is(1));
        assertThat(coupon.getCode(), is("CPN0000002"));
        assertThat(coupon.getName(), is("PC coupon"));
        assertThat(coupon.getPrice(), is(30000));

    }

    private void assertOrderOf2(Order order) {
        OrderStatus orderStatus = order.getOrderStatus();
        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderCoupon> orderCoupons = order.getOrderCoupons();
        OrderItem orderItem;
        Item item;
        List<Category> categories;
        Category category;

        System.out.println("Assert Order ...");

        assertThat(order.getId(), is(2));
        assertThat(orderStatus.getCode(), is("checking"));
        assertThat(orderStatus.getName(), is("Stock checking"));
        assertThat(orderItems.size(), is(2));
        assertThat(orderCoupons.size(), is(0));

        System.out.println("Assert Order Item 0 ...");
        orderItem = orderItems.get(0);
        item = orderItem.getItem();
        categories = item.getCategories();
        assertThat(orderItem.getOrderId(), is(2));
        assertThat(orderItem.getQuantity(), is(3));
        assertThat(item.getCode(), is("ITM0000001"));
        assertThat(item.getName(), is("Orange juice"));
        assertThat(item.getPrice(), is(100));
        assertThat(categories.size(), is(1));
        System.out.println("Assert Category 0 ...");
        category = categories.get(0);
        assertThat(category.getCode(), is("CTG0000001"));
        assertThat(category.getName(), is("Drink"));
        System.out.println(category.getItems());

        System.out.println("Assert Order Item 1 ...");
        orderItem = orderItems.get(1);
        item = orderItem.getItem();
        categories = item.getCategories();
        assertThat(orderItem.getOrderId(), is(2));
        assertThat(orderItem.getQuantity(), is(4));
        assertThat(item.getCode(), is("ITM0000002"));
        assertThat(item.getName(), is("NotePC"));
        assertThat(item.getPrice(), is(100000));
        assertThat(categories.size(), is(2));
        System.out.println("Assert Category 0 ...");
        category = categories.get(0);
        assertThat(category.getCode(), is("CTG0000002"));
        assertThat(category.getName(), is("PC"));
        System.out.println("Assert Category 1 ...");
        category = categories.get(1);
        assertThat(category.getCode(), is("CTG0000003"));
        assertThat(category.getName(), is("Hot selling"));
        System.out.println(category.getItems().iterator().next().toString());


    }

}
