package com.haw.se1lab.Anbieter.dataaccess.api.repo;

import com.haw.se1lab.Anbieter.common.api.datatype.AnbieterNummer;
import com.haw.se1lab.Anbieter.dataaccess.api.entity.Anbieter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Represents a repository for the management of {@link Anbieter} entities in a database.
 *
 * @author Kevin Zawieja,Onur Aslan
 */

public interface AnbieterRepository extends JpaRepository<Anbieter,Long> {


    /**
     * löscht Anbieter durch die Anbieternummer
     *
     *
     * @param anbieterNummer
     */
    @Modifying
    @Transactional
    @Query("delete from Anbieter a where a.anbieternummer = :anbieternummer")
    void deleteByFirmenname(@Param("anbieternummer")AnbieterNummer anbieterNummer);


    /**
     *
     * löscht Anbieter durch den Firmennamen
     *
     * @param firmenname
     */
    @Query("delete from Anbieter a where a.firmenname = :firmenname")
    void deleteByFirmenname(@Param("firmenname")String firmenname);

}
