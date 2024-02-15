package com.learn.springboot.model;

import lombok.Data;

@Data
public class InventoryRequest {

    private String productCode;

    private String productName;

    private String price;

    private String quantity;

    private String poductLocation;

}
