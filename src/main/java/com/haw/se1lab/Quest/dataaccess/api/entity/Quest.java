package com.haw.se1lab.Quest.dataaccess.api.entity;


import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quest {

    @Id
    @GeneratedValue
    private Long id;

    private String questname;
    private int erfahrungspunkte;
    private String queststatus;

    public Quest(String questname, String queststatus,int erfahrungspunkte) throws QuestStatusEingabeException {
        if (queststatus.length() > 20) {
            throw new QuestStatusEingabeException("Eingabe zu Lang");
        } else
        queststatus = queststatus.toUpperCase();
        for (int i = 0; i < queststatus.length(); i++) {
            if (queststatus.charAt(i) < (char) 65 || queststatus.charAt(i) > 90) {
                throw new QuestStatusEingabeException("Status darf keine Sonderzeichen enthalten!");
            }
        }
        this.questname = questname;
        this.queststatus = queststatus;
        this.erfahrungspunkte = erfahrungspunkte;
    }

    public Quest() {

    }

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", questname='" + questname + '\'' +
                ", erfahrungspunkte=" + erfahrungspunkte +
                ", queststatus='" + queststatus + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestname() {
        return questname;
    }

    public void setQuestname(String questname) {
        this.questname = questname;
    }

    public int getErfahrungspunkte() {
        return erfahrungspunkte;
    }

    public void setErfahrungspunkte(int erfahrungspunkte) {
        this.erfahrungspunkte = erfahrungspunkte;
    }

    public String getQueststatus() {
        return queststatus;
    }

    public void setQueststatus(String queststatus) {
        this.queststatus = queststatus;
    }
}
