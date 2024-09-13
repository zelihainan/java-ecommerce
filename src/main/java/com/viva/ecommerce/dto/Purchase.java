package com.viva.ecommerce.dto;

import com.viva.ecommerce.entity.Address;
import com.viva.ecommerce.entity.Customer;
import com.viva.ecommerce.entity.Order;
import com.viva.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
