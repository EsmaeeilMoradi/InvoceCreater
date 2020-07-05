package com.e_moradi.testproject.model;


/**
 * This class is representative of Product
 *
 * @author EsmaeeilMoradi
 */
public class Product {
    public String
            productId,
            productCode,
            productPrice,
            productName;

    /**
     * constructor of Customer class(Private). all fields are created in constructor
     *
     * @param productId    Id number of product.
     * @param productCode  Code number of product.
     * @param productPrice Price number of product.
     * @param productName  Name of product.
     */
    private Product(String productId, String productCode, String productPrice, String productName) {
        this.productId = productId;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productName = productName;
    }


    public Product(String productCode, String productPrice, String productName) {
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public Product() {
    }

}
