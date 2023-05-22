package com.haw.se1lab.Quest.dataaccess.api.repo;

import com.haw.se1lab.Application;
import com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;
import com.haw.se1lab.Nutzer.dataaccess.api.repo.NutzerRepository;
import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


    @SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE) //environment
    @ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
    @ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class QuestRepositoryTest {

        @Autowired
        private QuestRepository questRepository;





        private Quest quest1, quest2, quest3;

        @BeforeEach
        public void setup() throws QuestStatusEingabeException {

            //create instances



            quest1 = new Quest("stein der weisen","verfugbar",99);
            quest2 = new Quest("kammer des schrecken", "verfugbar", 199);
            quest3 = new Quest("gefangener von Azkaban","verfugbar",300);

//            List<Quest> listequest1 = new ArrayList<>();
//            listequest1.add(quest1);
//            listequest1.add(quest2);
//            listequest1.add(quest3);

            //save to db

            //userRepository.save(nutzer1);
            //userRepository.save(nutzer2);

            questRepository.save(quest1);
            questRepository.save(quest2);
            questRepository.save(quest3);
        }

        @AfterEach
        public void reset(){
            questRepository.delete(quest1);
            questRepository.delete(quest2);
            questRepository.delete(quest3);
        }

        @Test
        public void findQuestById(){


            List<Quest> listsOfQuest1 = questRepository.findQuestsById(quest1.getId());


            //test size
            assertThat(listsOfQuest1).hasSize(1);
            //test content
            assertThat(listsOfQuest1).anyMatch(q -> q.getId().equals(quest1.getId()));
            assertThat(listsOfQuest1).noneMatch(q -> q.getId().equals(quest2.getId()));
            assertThat(listsOfQuest1).noneMatch(q -> q.getId().equals(quest3.getId()));


            List<Quest> listsOfQuest2 = questRepository.findQuestsById(quest2.getId());

            //test listsOfQuest2
            assertThat(listsOfQuest2).hasSize(1);
            //test content
            assertThat(listsOfQuest2).noneMatch(q -> q.getId().equals(quest1.getId()));
            assertThat(listsOfQuest2).anyMatch(q -> q.getId().equals(quest2.getId()));
            assertThat(listsOfQuest2).noneMatch(q -> q.getId().equals(quest3.getId()));
        }
}
