package com.haw.se1lab.Quest.logic.api.usecase;

import com.haw.se1lab.Application;
import com.haw.se1lab.Nutzer.common.api.datatype.BenutzerNummer;
import com.haw.se1lab.Nutzer.common.api.datatype.Geschlecht;
import com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;
import com.haw.se1lab.Nutzer.dataaccess.api.repo.NutzerRepository;
import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import com.haw.se1lab.Quest.dataaccess.api.repo.QuestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


    @SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE) //environment
    @ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
    @ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class QuestUseCaseTest {

        @Autowired
        private QuestUseCase questUseCase;

        @Autowired
        private QuestRepository questRepository;





        private Quest quest1, quest2, quest3;

        @BeforeEach
        public void setup() throws QuestStatusEingabeException {


            quest1 = new Quest("test1","vefugbar",50);
            quest2 = new Quest("test2","vefugbar",70);
            quest3 = new Quest("test3","vefugbar",90);


            //save to db


            questRepository.save(quest1);
            questRepository.save(quest2);
            questRepository.save(quest3);
        }

        @AfterEach
        public void tearDown(){
            //delete from db
            questRepository.delete(quest1);
            questRepository.delete(quest2);
            questRepository.delete(quest3);


        }

        @Test
        public void findAllQuestById(){
            List<Quest> list1 = questUseCase.findAllQuestById(quest1.getId());


            //test size
            assertThat(list1).hasSize(3);
            //test sorted
            assertThat(list1).isSortedAccordingTo(Comparator.comparing(Quest::getId));
            //test entries
            assertThat(list1).anyMatch(q -> q.getId().equals(quest1.getId()));
            assertThat(list1).anyMatch(q -> q.getId().equals(quest2.getId()));
            assertThat(list1).anyMatch(q -> q.getId().equals(quest3.getId()));



            List<Quest> list2 = questUseCase.findAllQuestById(quest2.getId());

            //test list2
            //test size
            assertThat(list2).hasSize(3);
            //test sorted
            assertThat(list2).isSortedAccordingTo(Comparator.comparing(Quest::getId));
            //test entries
            assertThat(list2).anyMatch(q -> q.getId().equals(quest1.getId()));
            assertThat(list2).anyMatch(q -> q.getId().equals(quest2.getId()));
            assertThat(list2).anyMatch(q -> q.getId().equals(quest3.getId()));

        }
    }
