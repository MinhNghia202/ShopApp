package com.project.shopapp.models;

import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class OrderStatus {
    public static String PENDING = "pending";
    public static String PROCESSING = "processing";
    public static String SHIPPED = "shipped";
    public static String DELIVERED = "delivered";
    public static String CANCELLED = "cancelled";
}
