package com.viva.ecommerce.service;

import com.viva.ecommerce.dao.CustomerRepository;
import com.viva.ecommerce.dto.Purchase;
import com.viva.ecommerce.dto.PurchaseResponse;
import com.viva.ecommerce.entity.Customer;
import com.viva.ecommerce.entity.Order;
import com.viva.ecommerce.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //dto'dan sipariş bilgilerini al
        Order order = purchase.getOrder();

        //takip numarası oluştur
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //siparişi orderItems ile doldur
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        //siparişi billingAddress ve shippingAddress ile doldur
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //customer'ı sipariş ile doldur
        Customer customer = purchase.getCustomer();
        customer.add(order);

        //database'e kaydet
        customerRepository.save(customer);

        //bir response döndür
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //random bir UUID numarası oluştur. (UUID version-4)
        return UUID.randomUUID().toString();
    }
}











