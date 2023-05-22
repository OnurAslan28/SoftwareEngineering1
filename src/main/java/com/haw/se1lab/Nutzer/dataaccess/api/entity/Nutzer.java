package com.haw.se1lab.Nutzer.dataaccess.api.entity;

 import com.haw.se1lab.Nutzer.common.api.datatype.Geschlecht;
 import com.haw.se1lab.Nutzer.common.api.datatype.BenutzerNummer;
 import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
 import org.apache.commons.collections.ArrayStack;

 import javax.persistence.*;
 import javax.validation.constraints.NotNull;


 import java.util.ArrayList;
 import java.util.List;

@Entity
public class Nutzer {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @NotNull
    @Column(unique = true)
    private BenutzerNummer benutzernummer;

    private String vorname;
    private String nachname;


    private Geschlecht geschlecht;
    private String email;
    private String passwort;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Quest> quests = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Warenkorb warenkorb = new Warenkorb(0,0);

    @OneToMany
    private List<WishListe> wishListen = new ArrayList<>();

    public Nutzer() {

    }

    public Nutzer(String vorname, String nachname, Geschlecht geschlecht, String email, String passwort, BenutzerNummer benutzernummer) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.email = email;
        this.passwort = passwort;
        this.benutzernummer = benutzernummer;
    }


    public Warenkorb getWarenkorb() {
        return warenkorb;
    }

    public List<WishListe> getWishListen() {
        return wishListen;
    }



    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Geschlecht getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(Geschlecht geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setBenutzernummer(BenutzerNummer benutzernummer) {
        this.benutzernummer = benutzernummer;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public void setWarenkorb(Warenkorb warenkorb) {
        this.warenkorb = warenkorb;
    }

    public void setWishListen(List<WishListe> wishListen) {
        this.wishListen = wishListen;
    }

    public BenutzerNummer getBenutzernummer() {
        return benutzernummer;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
