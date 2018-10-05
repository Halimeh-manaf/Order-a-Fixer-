package com.example.manafnajdathalimah.firebase2;

public class Values_counter {


    public static void setOrder_counter(String order_counter) {
        Values_counter.order_counter = order_counter;
    }

    public static String getOrder_counter() {

        return order_counter;
    }

    public Values_counter() {

    }

    private static String order_counter;


}
