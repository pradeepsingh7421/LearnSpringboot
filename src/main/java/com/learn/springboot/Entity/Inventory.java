package com.learn.springboot.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

//@Entity
@Table(name = "inventory")
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private String id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private String price;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "location")
    private String poductLocation;

//    @OneToMany(mappedBy = "inventory", cascade = CascadeType.REMOVE)
//    @Valid
//    @Size(min = 1, message = "minimum should be 1")
//    private List<ProductWeights> productsWeight;

}
