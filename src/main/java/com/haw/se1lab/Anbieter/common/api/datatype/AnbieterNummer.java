package com.haw.se1lab.Anbieter.common.api.datatype;

import javax.persistence.Embeddable;

@Embeddable
public class AnbieterNummer {

    private final int anbieternummer;


    public AnbieterNummer(int anbieternummer) {
        this.anbieternummer = anbieternummer;
    }

    public int getAnbieternummer() {
        return anbieternummer;
    }
}
