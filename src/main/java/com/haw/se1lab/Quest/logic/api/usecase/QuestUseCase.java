package com.haw.se1lab.Quest.logic.api.usecase;

import com.haw.se1lab.Anbieter.dataaccess.api.entity.Anbieter;
import com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;

import java.util.List;
import java.util.Optional;

public interface QuestUseCase {

    /**
     *  findet alle Quest
     * @return returns all the list
     */
    List<Quest> findAllQuest();

    /**
     * return alle quest vom Nutzer
     *
     * @param nutzer
     * @return
     */
    List<Quest> getAllQuestFromNutzer(Nutzer nutzer);

    /**
     * returnt alle Quest vom Anbieter
     *
     * @param anbieter
     * @return
     */

    List<Quest> getAllQuestFromAnbieter(Anbieter anbieter);


    /**
     * löscht Quest vom Benutzer
     *
     * @param quest
     * @param nutzer
     */
    public void deleteQuestFromNutzer(Quest quest, Nutzer nutzer);

    /**
     * fügt Erfahrungspunkte einer Quest hinzu
     *
     * @param quest
     * @param erfahrungspunkte
     */
    public void erfahrungspunkteHinzufuegen(Quest quest, int erfahrungspunkte);



    /**
     *
     * findet alle Quest nach der id
     * @param id
     * @return returns all the list
     */
    List<Quest> findAllQuestById(long id);


    /**
     * creates Quest
     *
     * @param
     * @return
     */
    Quest createQuest(String questname, String queststatus, int erfahrungspunkte, Anbieter anbieter) throws QuestStatusEingabeException;

    /**
     * get a specific quest
     *
     * @param id
     * @return
     */
    Optional<Quest> getQuest(long id);

    /**
     * questStatusveraendern ermöglich es uns den Status zu verändern
     * @param quest
     * @param status
     * @throws QuestStatusEingabeException
     */
    void questStatusVeraendern(Quest quest,String status) throws QuestStatusEingabeException;


}
