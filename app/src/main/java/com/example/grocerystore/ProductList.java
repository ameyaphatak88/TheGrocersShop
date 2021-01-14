package com.example.grocerystore;

public class ProductList {

    public String productName;
    public int productQty;
    public String status;
    public int image;

    public ProductList(String productName, int productQty, int image) {
        this.productName = productName;
        this.productQty = productQty;
        this.status = status;
    }

    public String getName() {
        return this.productName;
    }

    public int getQty() {
        return this.productQty;
    }

    public int getImage() {
        return this.image;
    }

}
