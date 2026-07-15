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

    public Ukm() {}
    public Ukm(String nama, String kontakWa) { this.nama = nama; this.kontakWa = kontakWa; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getKontakWa() { return kontakWa; }
    public void setKontakWa(String kontakWa) { this.kontakWa = kontakWa; }
}