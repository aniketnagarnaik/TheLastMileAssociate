package com.example.nagarnai.thelastmileassociate.model;

/**
 * Created by ashruthi on 09/03/17.
 */

public class OrderDetails {
    private String orderId;
    private String latitude;
    private String longitude;
    private Address address;
//    private String packageId;


    public OrderDetails() {

    }

    public OrderDetails(String orderId, String latitude, String longitude) {
        this.orderId = orderId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
