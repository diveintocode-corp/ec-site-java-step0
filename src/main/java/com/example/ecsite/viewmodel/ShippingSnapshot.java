package com.example.ecsite.viewmodel;

import com.example.ecsite.entity.Profile;
import com.example.ecsite.entity.ShippingAddress;

import lombok.Data;

@Data
public class ShippingSnapshot {
    private String name;
    private String postalCode;
    private String prefecture;
    private String address1;
    private String address2;
    private String telno;

    public static ShippingSnapshot of(Profile p) {
        ShippingSnapshot s = new ShippingSnapshot();
        s.name = p.getName();
        s.postalCode = p.getPostalCode();
        s.prefecture = p.getPrefecture();
        s.address1 = p.getAddress1();
        s.address2 = p.getAddress2();
        s.telno = p.getTelno();
        return s;
    }

    public static ShippingSnapshot of(ShippingAddress a) {
        ShippingSnapshot s = new ShippingSnapshot();
        s.name = a.getName();
        s.postalCode = a.getPostalCode();
        s.prefecture = a.getPrefecture();
        s.address1 = a.getAddress1();
        s.address2 = a.getAddress2();
        s.telno = a.getTelno();
        return s;
    }
}
