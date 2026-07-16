package com.ipwija.ukm_kampus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ukm")
public class Ukm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nama;
    
    @Column(name = "kontak_wa")
    private String kontakWa;
    
    @Column(columnDefinition = "TEXT")
    private String visi;
    
    @Column(columnDefinition = "TEXT")
    private String misi;

    // Constructor kosong wajib untuk JPA/Hibernate
    public Ukm() {}

    // Constructor untuk tambah data biasa
    public Ukm(String nama, String kontakWa) { 
        this.nama = nama; 
        this.kontakWa = kontakWa; 
    }

    // Constructor lengkap (Opsional tapi sangat membantu di Controller)
    public Ukm(String nama, String kontakWa, String visi, String misi) {
        this.nama = nama;
        this.kontakWa = kontakWa;
        this.visi = visi;
        this.misi = misi;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getKontakWa() { return kontakWa; }
    public void setKontakWa(String kontakWa) { this.kontakWa = kontakWa; }
    
    public String getVisi() { return visi; }
    public void setVisi(String visi) { this.visi = visi; }
    
    public String getMisi() { return misi; }
    public void setMisi(String misi) { this.misi = misi; }
}