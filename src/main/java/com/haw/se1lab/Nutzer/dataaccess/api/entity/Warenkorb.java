package com.haw.se1lab.Nutzer.dataaccess.api.entity;

 import com.haw.se1lab.Anbieter.dataaccess.api.entity.Praemien;

 import javax.persistence.*;
 import java.util.ArrayList;
 import java.util.List;

 @Entity
public class Warenkorb {

     @Id
     @GeneratedValue
     private Long id;


    private int anzahlArtikel;
    private int artikelWert;


    @OneToMany
    private final List<Praemien> praemien = new ArrayList<>();

    public Warenkorb(int anzahlArtikel, int artikelWert) {
        this.anzahlArtikel = anzahlArtikel;
        this.artikelWert = artikelWert;

    }

     public Warenkorb() {

     }


     public List<Praemien> getPraemien(){
        return praemien;
    }




    public int getAnzahlArtikel() {
        return anzahlArtikel;
    }

    public void setAnzahlArtikel(int anzahlArtikel) {
        this.anzahlArtikel = anzahlArtikel;
    }

    public int getArtikelWert() {
        return artikelWert;
    }

    public void setArtikelWert(int artikelWert) {
        this.artikelWert = artikelWert;
    }

    @Override
    public String toString() {
        return "Warenkorb: " +
                "anzahlArtikel = " + anzahlArtikel +
                ", artikelWert = " + artikelWert;
    }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }
 }
