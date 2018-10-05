package com.example.manafnajdathalimah.firebase2;

import android.support.annotation.Keep;

@Keep
public class Values_orders {
    private   String request_user_name;
    private  String request_order_number;
    private  String request_user_id;
    private  String request_user_email;
    private  String request_user_address;
    private  String request_user_mobile_no;
    private  String request_user_preferred_time;
    private  String request_user_damage_image;
    private  String request_user_service_type;
    private String key;

    public Values_orders(String request_user_name, String request_order_number, String request_user_id, String request_user_email, String request_user_address, String request_user_mobile_no, String request_user_preferred_time, String request_user_damage_image, String request_user_service_type, String key) {
        this.request_user_name = request_user_name;
        this.request_order_number = request_order_number;
        this.request_user_id = request_user_id;
        this.request_user_email = request_user_email;
        this.request_user_address = request_user_address;
        this.request_user_mobile_no = request_user_mobile_no;
        this.request_user_preferred_time = request_user_preferred_time;
        this.request_user_damage_image = request_user_damage_image;
        this.request_user_service_type = request_user_service_type;
        this.key = key;
    }

    public Values_orders(String request_user_name, String request_user_address, String request_user_id) {
        this.request_user_name = request_user_name;
        this.request_user_address = request_user_address;
        this.request_user_mobile_no = request_user_id;
    }

    public Values_orders() {
    }

    public Values_orders(String request_user_name, String request_user_address, String request_user_id, String key) {
        this.request_user_name = request_user_name;
        this.request_user_address = request_user_address;
        this.request_user_mobile_no = request_user_id;
        this.key = key;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Values_orders(String request_user_name, String request_order_number, String request_user_id, String request_user_email, String request_user_address, String request_user_mobile_no, String request_user_preferred_time, String request_user_damage_image, String request_user_service_type) {
        this.request_user_name = request_user_name;
        this.request_order_number = request_order_number;
        this.request_user_id = request_user_id;
        this.request_user_email = request_user_email;
        this.request_user_address = request_user_address;
        this.request_user_mobile_no = request_user_mobile_no;
        this.request_user_preferred_time = request_user_preferred_time;
        this.request_user_damage_image = request_user_damage_image;
        this.request_user_service_type = request_user_service_type;
    }

    public String getRequest_user_name() {
        return request_user_name;
    }

    public void setRequest_user_name(String request_user_name) {
        this.request_user_name = request_user_name;
    }

    public String getRequest_order_number() {
        return request_order_number;
    }

    public void setRequest_order_number(String request_order_number) {
        this.request_order_number = request_order_number;
    }

    public String getRequest_user_id() {
        return request_user_id;
    }

    public void setRequest_user_id(String request_user_id) {
        this.request_user_id = request_user_id;
    }

    public String getRequest_user_email() {
        return request_user_email;
    }

    public void setRequest_user_email(String request_user_email) {
        this.request_user_email = request_user_email;
    }

    public String getRequest_user_address() {
        return request_user_address;
    }

    public void setRequest_user_address(String request_user_address) {
        this.request_user_address = request_user_address;
    }

    public String getRequest_user_mobile_no() {
        return request_user_mobile_no;
    }

    public void setRequest_user_mobile_no(String request_user_mobile_no) {
        this.request_user_mobile_no = request_user_mobile_no;
    }

    public String getRequest_user_preferred_time() {
        return request_user_preferred_time;
    }

    public void setRequest_user_preferred_time(String request_user_preferred_time) {
        this.request_user_preferred_time = request_user_preferred_time;
    }

    public String getRequest_user_damage_image() {
        return request_user_damage_image;
    }

    public void setRequest_user_damage_image(String request_user_damage_image) {
        this.request_user_damage_image = request_user_damage_image;
    }

    public String getRequest_user_service_type() {
        return request_user_service_type;
    }

    public void setRequest_user_service_type(String request_user_service_type) {
        this.request_user_service_type = request_user_service_type;
    }

}
