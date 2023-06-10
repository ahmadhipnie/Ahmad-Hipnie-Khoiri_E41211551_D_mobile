package com.example.tosepatu.model;

    public class MetodePengiriman {
        private int id;
        private String nama_pengiriman;

        public MetodePengiriman(int id, String nama_pengiriman) {
            this.id = id;
            this.nama_pengiriman = nama_pengiriman;
        }

        public int getId() {
            return id;
        }

        public String getNamaPengiriman() {
            return nama_pengiriman;
        }
    }
