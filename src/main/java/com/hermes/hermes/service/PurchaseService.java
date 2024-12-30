package com.hermes.hermes.service;

import com.hermes.hermes.dto.Purchase;
import com.hermes.hermes.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final PurchaseMapper purchaseMapper;

    @Autowired
    public PurchaseService(PurchaseMapper purchaseMapper) {
        this.purchaseMapper = purchaseMapper;
    }

    public void savePurchase(Purchase purchase) {
        purchaseMapper.insertPurchase(purchase);
    }
}