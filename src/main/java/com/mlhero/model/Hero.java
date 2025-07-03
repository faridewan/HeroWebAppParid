package com.mlhero.model;

/**
 * Model class untuk merepresentasikan data Hero Mobile Legend.
 * Digunakan sebagai data objek utama untuk operasional CRUD.
 */
public class Hero {
    private int idHero;
    private String namaHero;
    private String kategori;
    private String gender;

    // Constructor tanpa parameter (dibutuhkan oleh servlet/JSP)
    public Hero() {
    }

    // Constructor dengan semua field (untuk update dan tampil data)
    public Hero(int idHero, String namaHero, String kategori, String gender) {
        this.idHero = idHero;
        this.namaHero = namaHero;
        this.kategori = kategori;
        this.gender = gender;
    }

    // Constructor tanpa ID (untuk insert)
    public Hero(String namaHero, String kategori, String gender) {
        this.namaHero = namaHero;
        this.kategori = kategori;
        this.gender = gender;
    }

    // Getter dan Setter
    public int getIdHero() {
        return idHero;
    }

    public void setIdHero(int idHero) {
        this.idHero = idHero;
    }

    public String getNamaHero() {
        return namaHero;
    }

    public void setNamaHero(String namaHero) {
        this.namaHero = namaHero;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
