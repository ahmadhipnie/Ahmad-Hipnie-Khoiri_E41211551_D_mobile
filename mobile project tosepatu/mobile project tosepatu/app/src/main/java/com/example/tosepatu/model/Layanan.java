package com.example.tosepatu.model;

public class Layanan {
    private String id;
    private String idLayanan;
    private int hargaLayanan;
    private String fotoLayanan;
    private String description;
    private int userId;
    private String createdAt;
    private String updatedAt;

    private String NamaLayanan;
    private int WaktuPengerjaan;

    public Layanan(String id, String idLayanan, int WaktuPengerjaan,String NamaLayanan, int hargaLayanan, String fotoLayanan, String description, int userId, String createdAt, String updatedAt) {
        this.id = id;
        this.idLayanan = idLayanan;
        this.NamaLayanan = NamaLayanan;
        this.hargaLayanan = hargaLayanan;
        this.fotoLayanan = fotoLayanan;
        this.description = description;
        this.WaktuPengerjaan = WaktuPengerjaan;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getIdLayanan() {
        return idLayanan;
    }

    public String getNamaLayanan() {
        return NamaLayanan;
    }

    public int getHargaLayanan() {
        return hargaLayanan;
    }

    public String getFotoLayanan() {
        return fotoLayanan;
    }

    public String getDescription() {
        return description;
    }

    public int getWaktuPengerjaan() {
        return WaktuPengerjaan;
    }

    public int getUserId() {
        return userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
