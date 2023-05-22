package com.haw.se1lab.Nutzer.dataaccess.api.repo;

import com.haw.se1lab.Nutzer.common.api.datatype.BenutzerNummer;
import com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Represents a repository for the management of {@link Nutzer} entities in a database.
 *
 * @author Kevin Zawieja, Onur Aslan
 */


public interface NutzerRepository extends JpaRepository<Nutzer,Long> {


    /**
     * findet Nutzer durch die Benutzernummer
     *
     * @param benutzerNummer
     * @return null oder Nutzer
     */
    @Query("select usr from Nutzer usr where usr.benutzernummer = :benutzernummer")
    Optional<Nutzer> findByBenutzernummer(@Param("benutzernummer") BenutzerNummer benutzerNummer);

    /**
     * findet Nutzer durch Passwort
     *
     * @param passwort
     * @return null oder Nutzer
     */
    @Query("select usr from Nutzer usr where usr.passwort = :passwort")
    Optional<Nutzer> findByPasswort(@Param("passwort") String passwort);



}
