package com.e_moradi.testproject.model;

/**
 * This class is representative of Customer
 *
 * @author EsmaeeilMoradi
 */
public class Customer {


    public String
            customerId,
            customerCode,
            customerName,
            customerTel,
            customerMobile,
            customerEmail,
            customerDesc,
            customerAddress;


    public Customer() {
    }

    /**
     * Only constructor of Customer class(Private). all fields are created in constructor
     *
     * @param customerId      Id number of constructor.
     * @param customerCode    Code number of constructor.
     * @param customerName    Name of constructor.
     * @param customerTel     Tel of constructor.
     * @param customerAddress Address of constructor.
     */

    public Customer(String customerId, String customerCode, String customerName, String customerTel, String customerAddress) {
        this.customerId = customerId;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerTel = customerTel;
        this.customerAddress = customerAddress;

    }

    public Customer( String customerCode, String customerName, String customerTel,
                    String customerMobile, String customerEmail, String customerDesc, String customerAddress) {
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerTel = customerTel;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.customerDesc = customerDesc;
        this.customerAddress = customerAddress;
    }

}
