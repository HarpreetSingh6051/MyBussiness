package com.harpreet.mybussiness.Util;

public class Order {

    Integer NOCloth ;
    Integer WeightCloth;
    String Address;
    Double Phone;

    public Order(){

    }
    public Order(Integer NOCloth, Integer weightCloth, String address, Double phone) {
        this.NOCloth = NOCloth;
        WeightCloth = weightCloth;
        Address = address;
        Phone = phone;
    }

    public Integer getNOCloth() {
        return NOCloth;
    }

    public void setNOCloth(Integer NOCloth) {
        this.NOCloth = NOCloth;
    }

    public Integer getWeightCloth() {
        return WeightCloth;
    }

    public void setWeightCloth(Integer weightCloth) {
        WeightCloth = weightCloth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Double getPhone() {
        return Phone;
    }

    public void setPhone(Double phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "Order{" + "NOCloth=" + NOCloth + ", WeightCloth=" + WeightCloth + ", Address='" + Address + '\'' + ", Phone=" + Phone + '}';
    }
}
