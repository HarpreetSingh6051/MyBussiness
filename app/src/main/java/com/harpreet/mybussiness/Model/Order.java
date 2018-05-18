package com.harpreet.mybussiness.Model;

import android.content.Intent;

public class Order {

    public int NOCloth ;
    public int WeightCloth;
    public String Address;
    public double Phone;

    public Order(){

    }
    public Order(int NOCloth, int weightCloth, String address, double phone) {
        this.NOCloth = NOCloth;
        this.WeightCloth = weightCloth;
        this.Address = address;
        this.Phone = phone;
    }

}
