package com.haw.se1lab.Quest.facade.impl;

import com.haw.se1lab.Anbieter.dataaccess.api.entity.Anbieter;
import com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;
import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import com.haw.se1lab.Quest.facade.api.QuestFacade;
import com.haw.se1lab.Quest.logic.api.usecase.QuestUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/quest")
public class QuestFacadeImpl implements QuestFacade {

    @Autowired
    private QuestUseCase questUseCase;

    @Override
    public List<Quest> getAllQuest(){
        return questUseCase.findAllQuest();
    }


    @PostMapping("/getByNutzer")
    @Override
    public List<Quest> getAllQuestFromNutzer(@RequestBody Nutzer nutzer){
        return questUseCase.getAllQuestFromNutzer(nutzer);
    }

    @Override
    public List<Quest> getAllQuestFromAnbieter(Anbieter anbieter){
        return questUseCase.getAllQuestFromAnbieter(anbieter);
    }



    @Override
    public Quest createQuest(String questname, String queststatus, int erfahrungspunkte, Anbieter anbieter) throws QuestStatusEingabeException {
        return questUseCase.createQuest(questname,queststatus,erfahrungspunkte,anbieter);}

    @Override
    public List<Quest> getQuests(Long id){
        return questUseCase.findAllQuestById(id);
    }

    @GetMapping(value = "/{id:[\\d]+}")
    @Override
    public Optional<Quest> getQuest(@PathVariable("id")Long id){
        return questUseCase.getQuest(id);
    }


    @Override
    public void deleteQuestFromNutzer(Quest quest, Nutzer nutzer){
        questUseCase.deleteQuestFromNutzer(quest,nutzer);

    }



    @Override
    public void erfahrungspunkteHinzufuegen(Quest quest, int erfahrungspunkte){
        questUseCase.erfahrungspunkteHinzufuegen(quest,erfahrungspunkte);
    }

    @Override
    public void questStatusVeraendern(Quest quest, String status) throws QuestStatusEingabeException{
        questUseCase.questStatusVeraendern(quest,status);
    }

}
