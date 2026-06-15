package com.example.ecsite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecsite.entity.ShippingAddress;
import com.example.ecsite.form.ShippingAddressForm;
import com.example.ecsite.repository.ShippingAddressRepository;

@Service
public class ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressService(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public List<ShippingAddress> findByUserId(Long userId) {
        return shippingAddressRepository.findByUserId(userId);
    }

    public ShippingAddress findOwnedById(Long id, Long userId) {
        return shippingAddressRepository.findByIdAndUserId(id, userId);
    }

    public void create(Long userId, ShippingAddressForm form) {
        ShippingAddress addr = new ShippingAddress();
        addr.setUserId(userId);
        addr.setName(form.getName());
        addr.setPostalCode(form.getPostalCode());
        addr.setPrefecture(form.getPrefecture());
        addr.setAddress1(form.getAddress1());
        addr.setAddress2(form.getAddress2());
        addr.setTelno(form.getTelno());
        shippingAddressRepository.insert(addr);
    }

    public void update(Long id, Long userId, ShippingAddressForm form) {
        ShippingAddress addr = new ShippingAddress();
        addr.setShippingAddressId(id);
        addr.setUserId(userId);
        addr.setName(form.getName());
        addr.setPostalCode(form.getPostalCode());
        addr.setPrefecture(form.getPrefecture());
        addr.setAddress1(form.getAddress1());
        addr.setAddress2(form.getAddress2());
        addr.setTelno(form.getTelno());
        shippingAddressRepository.update(addr);
    }

    public void delete(Long id, Long userId) {
        shippingAddressRepository.deleteByIdAndUserId(id, userId);
    }
}
