package com.viva.ecommerce.service;

import com.viva.ecommerce.dto.Purchase;
import com.viva.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
