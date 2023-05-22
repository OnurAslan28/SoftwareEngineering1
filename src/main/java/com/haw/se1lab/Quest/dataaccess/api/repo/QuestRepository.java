package com.haw.se1lab.Quest.dataaccess.api.repo;

import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

/**
 * Represents a repository for the management of {@link Quest} entities in a database.
 *
 * @author Kevin Zawieja, Onur Aslan
 */


public interface QuestRepository extends JpaRepository<Quest, Long> {

    /**
     * Findet Quest durch die Anzahl der Erfahrungspunkte
     *
     * @param erfahrungspunkte
     * @return  Liste von Quest
     */
    @Query("select qst from Quest qst where qst.erfahrungspunkte = :erfahrungspunkte")
    List<Quest> findByErfahrungspunkte(@Param("erfahrungspunkte")int erfahrungspunkte);

    /**
     * Findet eine List von Quests durch die Quest id
     *
     * @param id
     * @return List von Quest
     */
    @Query("select qst from Quest qst where qst.id = :id")
    List<Quest> findQuestsById(@Param("id")Long id);

    /**
     *  Findet eine Quest durch die Quest id
     *
     * @param id
     * @return null oder Quest
     */
    @Query("select qst from Quest qst where qst.id = :id")
    Optional<Quest> findById(@Param("id")Long id);

}
