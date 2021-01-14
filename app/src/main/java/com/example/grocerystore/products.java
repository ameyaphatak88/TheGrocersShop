package com.example.grocerystore;

public class products {
    public String userid;
    public String productName;
    public int productQty;
    public String status;

    public products() {

    }

    public products(String userid, String productName, int productQty, String status) {
        this.userid = userid;
        this.productName = productName;
        this.productQty = productQty;
        this.status = status;
    }

}
