package com.pbo.jdbc;

class barang {
    private String itemName;
    private double price;
    private int quantity;

    public barang(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }
}