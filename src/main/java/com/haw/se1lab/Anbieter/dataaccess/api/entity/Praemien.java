package com.haw.se1lab.Anbieter.dataaccess.api.entity;

import com.haw.se1lab.Anbieter.common.api.datatype.Praemiennummer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Praemien {

    @Id
    @GeneratedValue
    private Long id;

    private String wert;
    private String praemienname;
    private String praemienart;
    private int anzahlErfahrungspunkte;
    private boolean praemienverfuegbar;

    @Embedded
    @NotNull
    @Column(unique = true)
    private Praemiennummer praemiennummer;

    public Praemien(String wert, String praemienname, String praemienart, int anzahlErfahrungspunkte, boolean praemienverfuegbar, Praemiennummer praemiennummer) {
        this.wert = wert;
        this.praemienname = praemienname;
        this.praemienart = praemienart;
        this.anzahlErfahrungspunkte = anzahlErfahrungspunkte;
        this.praemienverfuegbar = praemienverfuegbar;
        this.praemiennummer = praemiennummer;
    }

    public Praemien() {

    }



    public String getWert() {
        return wert;
    }

    @Override
    public String toString() {
        return "Praemien{" +
                "wert='" + wert + '\'' +
                ", praemienname='" + praemienname + '\'' +
                ", praemienart='" + praemienart + '\'' +
                ", anzahlErfahrungspunkte=" + anzahlErfahrungspunkte +
                ", praemienverfuegbar=" + praemienverfuegbar +
                ", praemiennummer=" + praemiennummer +
                '}';
    }

    public void setWert(String wert) {
        this.wert = wert;
    }

    public String getPraemienname() {
        return praemienname;
    }

    public void setPraemienname(String praemienname) {
        this.praemienname = praemienname;
    }

    public String getPraemienart() {
        return praemienart;
    }

    public void setPraemienart(String praemienart) {
        this.praemienart = praemienart;
    }

    public int getAnzahlErfahrungspunkte() {
        return anzahlErfahrungspunkte;
    }

    public void setAnzahlErfahrungspunkte(int anzahlErfahrungspunkte) {
        this.anzahlErfahrungspunkte = anzahlErfahrungspunkte;
    }

    public boolean isPraemienverfuegbar() {
        return praemienverfuegbar;
    }

    public void setPraemienverfuegbar(boolean praemienverfuegbar) {
        this.praemienverfuegbar = praemienverfuegbar;
    }

    public Praemiennummer getPraemiennummer() {
        return praemiennummer;
    }

    public void setPraemiennummer(Praemiennummer praemiennummer) {
        this.praemiennummer = praemiennummer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
