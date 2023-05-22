package com.haw.se1lab.Anbieter.dataaccess.api.entity;
import com.haw.se1lab.Anbieter.common.api.datatype.AnbieterNummer;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Anbieter {

    @Id
    @GeneratedValue
    private Long id;

    private String firmenname;
    private String standorte;

    @Embedded
    @NotNull
    @Column(unique = true)
    private AnbieterNummer anbieternummer;

    @OneToMany
    private final List <Quest> quest = new ArrayList<>();

    @OneToMany
    private final List<Praemien> praemien = new ArrayList<>();

    public Anbieter() {

    }


    public Anbieter(String firmenname, String standorte, AnbieterNummer anbieternummer) {
        this.firmenname = firmenname;
        this.standorte = standorte;
        this.anbieternummer = anbieternummer;

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Quest> getQuest(){
       return quest;
    }

    public List<Praemien> getPraemien() {
        return praemien;
    }

    public String getFirmenname() {
        return firmenname;
    }

    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    public String getStandorte() {
        return standorte;
    }

    public void setStandorte(String standorte) {
        this.standorte = standorte;
    }

    public AnbieterNummer getAnbieternummer() {
        return anbieternummer;
    }


}
