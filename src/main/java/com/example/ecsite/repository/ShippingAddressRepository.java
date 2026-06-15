package com.example.ecsite.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.ShippingAddress;
import com.example.ecsite.mapper.ShippingAddressMapper;

@Repository
public class ShippingAddressRepository {

    private final ShippingAddressMapper shippingAddressMapper;

    public ShippingAddressRepository(ShippingAddressMapper shippingAddressMapper) {
        this.shippingAddressMapper = shippingAddressMapper;
    }

    public List<ShippingAddress> findByUserId(Long userId) {
        return shippingAddressMapper.findByUserId(userId);
    }

    public ShippingAddress findByIdAndUserId(Long id, Long userId) {
        return shippingAddressMapper.findByIdAndUserId(id, userId);
    }

    public void insert(ShippingAddress addr) {
        shippingAddressMapper.insert(addr);
    }

    public void update(ShippingAddress addr) {
        shippingAddressMapper.update(addr);
    }

    public void deleteByIdAndUserId(Long id, Long userId) {
        shippingAddressMapper.deleteByIdAndUserId(id, userId);
    }
}
