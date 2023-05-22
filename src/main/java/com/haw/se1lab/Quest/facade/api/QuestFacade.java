package com.haw.se1lab.Quest.facade.api;

import com.haw.se1lab.Anbieter.dataaccess.api.entity.Anbieter;
import com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;
import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;


import java.util.List;
import java.util.Optional;

public interface QuestFacade {

    /**
     *
     * returnt alle Quests als eine Liste
     * @return
     */
    List<Quest> getAllQuest();

    /**
     * returnt alle Quest vom Nutzer
     *
     * @param nutzer
     * @return
     */
    List<Quest> getAllQuestFromNutzer(Nutzer nutzer);


    /**
     *
     * returnt alle Qest vom Anbieter
     * @param anbieter
     * @return
     */
    List<Quest> getAllQuestFromAnbieter(Anbieter anbieter);

    /**
     *
     * returnt Quest
     * @param questname
     * @param queststatus
     * @param erfahrungspunkte
     * @param anbieter
     * @return
     * @throws QuestStatusEingabeException
     */
    Quest createQuest(String questname, String queststatus, int erfahrungspunkte, Anbieter anbieter) throws QuestStatusEingabeException;

    /**
     * returnt alle Quest entsprechend der id
     *
     * @param id
     * @return
     */
    List<Quest> getQuests(Long id);



    /**
     * return quest with specific id
     *
     * @param id
     * @return
     */
    Optional<Quest> getQuest(Long id);

    /**
     * löscht Quest vom Nutzer
     *
     * @param quest
     * @param nutzer
     */
    void deleteQuestFromNutzer(Quest quest, Nutzer nutzer);



    /**
     * fügt einer Quest Erfahrungspunkte hinzu
     * @param quest
     * @param erfahrungspunkte
     */
    void erfahrungspunkteHinzufuegen(Quest quest, int erfahrungspunkte);


    /**
     * queststatus verändern
     *
     * @param quest
     * @param status
     * @throws QuestStatusEingabeException
     */
    void questStatusVeraendern(Quest quest, String status) throws QuestStatusEingabeException;
}
