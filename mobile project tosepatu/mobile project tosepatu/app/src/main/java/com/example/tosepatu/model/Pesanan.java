package com.example.tosepatu.model;
public class Pesanan {
    private int id;
    private String namaPengiriman;
    private String catatan;
    private int grandTotal;
    private String alamat;

    public Pesanan(int id, String namaPengiriman, String catatan, int grandTotal, String alamat) {
        this.id = id;
        this.namaPengiriman = namaPengiriman;
        this.catatan = catatan;
        this.grandTotal = grandTotal;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public String getNamaPengiriman() {
        return namaPengiriman;
    }

    public String getCatatan() {
        return catatan;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public String getAlamat() {
        return alamat;
    }
}



