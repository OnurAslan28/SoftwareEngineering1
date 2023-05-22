package com.haw.se1lab.Anbieter.common.api.datatype;


import javax.persistence.Embeddable;

@Embeddable
public class Praemiennummer {
    private final int praemiennummer;

    public Praemiennummer(int praemiennummer) {
        this.praemiennummer = praemiennummer;
    }


    public int getPraemiennummer() {
        return praemiennummer;
    }
}
