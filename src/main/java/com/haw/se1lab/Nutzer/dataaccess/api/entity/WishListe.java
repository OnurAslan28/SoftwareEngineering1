package com.haw.se1lab.Nutzer.dataaccess.api.entity;

 import com.haw.se1lab.Anbieter.dataaccess.api.entity.Praemien;
 import com.haw.se1lab.Nutzer.common.api.datatype.HintergrundFarbe;

 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import java.util.ArrayList;
 import java.util.List;

 @Entity
public class WishListe {

     @Id
     @GeneratedValue
     private Long id;

    private String status;
    private String beschreibung;
    private HintergrundFarbe hintergrundfarbe;

    @OneToMany
    private final List<Praemien> praemien = new ArrayList<>();

    public WishListe(String status, String beschreibung, HintergrundFarbe hintergrundfarbe) {
        this.status = status;
        this.beschreibung = beschreibung;
        this.hintergrundfarbe = hintergrundfarbe;
    }

     public WishListe() {

     }

     public List<Praemien> getPraemien(){
        return praemien;
   }

    @Override
    public String toString() {
        return "WishListe{" +
                "status='" + status + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", hintergrundfarbe=" + hintergrundfarbe +
                ", praemien=" + praemien +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public HintergrundFarbe getHintergrundfarbe() {
        return hintergrundfarbe;
    }

    public void setHintergrundfarbe(HintergrundFarbe hintergrundfarbe) {
        this.hintergrundfarbe = hintergrundfarbe;
    }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }
 }


