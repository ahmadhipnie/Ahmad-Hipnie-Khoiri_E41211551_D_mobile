package com.example.tosepatu.model;

public class Order {
    private int id;
    private int grandTotal;
    private String statusPesanan;

    public Order(int id, int grandTotal, String statusPesanan) {
        this.id = id;
        this.grandTotal = grandTotal;
        this.statusPesanan = statusPesanan;
    }

    public int getId() {
        return id;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public String getStatusPesanan() {
        return statusPesanan;
    }
}


