package com.haw.se1lab.Nutzer.common.api.datatype;

import javax.persistence.Embeddable;

@Embeddable
public class BenutzerNummer {
    private int benutzernummer;

    public BenutzerNummer(int benutzernummer) {
        this.benutzernummer = benutzernummer;
    }

    public BenutzerNummer() {

    }

    public int getBenutzernummer() {
        return benutzernummer;
    }

    public void setBenutzernummer(int benutzernummer) {
        this.benutzernummer = benutzernummer;
    }
}
