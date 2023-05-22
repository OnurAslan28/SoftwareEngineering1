package com.haw.se1lab.Quest.logic.impl.usecase;

import com.haw.se1lab.Anbieter.dataaccess.api.entity.Anbieter;
import com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;
import com.haw.se1lab.Quest.dataaccess.api.repo.QuestRepository;
import com.haw.se1lab.Quest.logic.api.usecase.QuestUseCase;
import com.haw.se1lab.Quest.strategy.QuestSortingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // causes Spring to automatically create a Spring bean for this class which can then be used using @Autowired
public class QuestUseCaseImpl implements QuestUseCase {

    @Autowired // automatically initializes the field with a Spring bean of a matching type
    private QuestRepository questRepository;

    @Override
    public List<Quest> getAllQuestFromNutzer( Nutzer nutzer){
        return nutzer.getQuests();
    }

    @Override
    public List<Quest> getAllQuestFromAnbieter(Anbieter anbieter){
        return anbieter.getQuest();
    }

    @Override
    public List<Quest> findAllQuest() {
        return questRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public List<Quest> findAllQuestById(long id){
        List<Quest> list = findAllQuest();
        QuestSortingStrategy sortingStrategy = QuestSortingUtil.getSortingStrategy(list);
        return sortingStrategy.sort(list);
    }

    @Override
    public Optional<Quest> getQuest(long id){
        return questRepository.findById(id);
    }

    @Override
    public void deleteQuestFromNutzer(Quest quest, Nutzer nutzer){
        getAllQuestFromNutzer(nutzer).remove(quest);

    }

    @Override
    public void erfahrungspunkteHinzufuegen(Quest quest, int erfahrungspunkte){
        quest.setErfahrungspunkte(quest.getErfahrungspunkte() + erfahrungspunkte);
    }

    @Override
    public Quest createQuest(String questname,String queststatus, int erfahrungspunkte,Anbieter anbieter) throws QuestStatusEingabeException {

        Quest newQuest = new Quest(questname,queststatus,erfahrungspunkte);
        for(Quest e: findAllQuest()){
            if (!e.getQuestname().contains(questname)){
                anbieter.getQuest().add(newQuest);
            }
        }
        //return and save in db
        return questRepository.save(newQuest);
    }

    @Override
    public void questStatusVeraendern(Quest quest, String status) throws QuestStatusEingabeException {
        if (status.length() > 20) {
            throw new QuestStatusEingabeException("Eingabe zu Lang");
        } else
            status = status.toUpperCase();
        for (int i = 0; i < status.length(); i++) {
            if (status.charAt(i) < (char) 65 || status.charAt(i) > 90) {
                throw new QuestStatusEingabeException("Status darf keine Sonderzeichen enthalten!");
            }
        }
        quest.setQueststatus(status);
    }




}
