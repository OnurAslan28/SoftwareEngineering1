package com.haw.se1lab.Smoke;

import com.haw.se1lab.Application;
import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import com.haw.se1lab.Quest.dataaccess.api.repo.QuestRepository;
import com.haw.se1lab.Quest.facade.api.QuestFacade;
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


public class QueststatusSmokeTest {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private QuestFacade questFacade;

    private Quest quest1, quest2, quest3;

    @BeforeEach
    public void setup() throws QuestStatusEingabeException {

        //erzeuge 3 Quest Instanzen

        quest1 = new Quest("stein der weisen","verfugbar",99);
        quest2 = new Quest("kammer des schrecken", "verfugbar", 199);
        quest3 = new Quest("gefangener von Azkaban","verfugbar",300);

        //save to db

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
    public void questVerwaltenScenario_Success(){
        System.out.println("");
        System.out.println(
                "####################################################################################################");
        System.out.println("Smoke Test - UC-1.3: Quests verwalten");
        System.out.println(
                "####################################################################################################");
        System.out.println("");

        // [GIVEN]
        // Vorbedingungen:
        // - Der Benutzer ist in der App angemeldet/registriert
        // - Der Benutzer befindet sich in dem Reiter
        // - Anbieter haben Quests angeboten


        // [WHEN]
        // Erfolgsszenario

        // 1. Der Benutzer tippt auf den Reiter Quest
        System.out.println("<Klick auf Reiter Quest>");

        // 2. Das System lädt Quest aus der Datenbank
        List<Quest> liste1 = questFacade.getAllQuest();
        assertThat(liste1).hasSize(3);

            //test content
        assertThat(liste1).anyMatch(q -> q.getId().equals(quest1.getId()));
        assertThat(liste1).anyMatch(q -> q.getId().equals(quest2.getId()));
        assertThat(liste1).anyMatch(q -> q.getId().equals(quest3.getId()));


            //zeigt alle Quest
        for (Quest e : liste1){
            System.out.println(e.toString());
        }

        // 3. Der Benutzer tippt auf die ausgewählte Quest
        System.out.println("<Klick auf eine Quest>");

        liste1 = questRepository.findQuestsById(quest2.getId());
        assertThat(liste1).hasSize(1);

            //test content
        assertThat(liste1).noneMatch(q -> q.getId().equals(quest1.getId()));
        assertThat(liste1).anyMatch(q -> q.getId().equals(quest2.getId()));
        assertThat(liste1).noneMatch(q -> q.getId().equals(quest3.getId()));

            // ausgewählte Quest
        for (Quest e : liste1){
            System.out.println(e.toString());
        }

        // 4. Das System zeigt die Option Quest verwalten

        // 5. Der Benutzer tippt auf den Button Quest verwalten
        System.out.println("<Klick auf Quest verwalten>");

        // 6. Das System zeigt verfügbare Funktionen zu der ausgewählten Quest

        // 7. Der Benutzer tippt auf den Button Queststatus verändern
        System.out.println("<Klick auf Queststatus verändern>");

        // 8. System zeigt das Einabefeld für den Text des Queststatus an

        // 9. Der Benutzer tippt auf das Eingabefeld
        System.out.println("<Klick auf Eingabefeld>");

        // 10. Das System lädt die Einschränkungsregeln für die Eingabe

        // 11. Der Benutzer gibt seinen aktuellen Status des Quest an
       try {

           questFacade.questStatusVeraendern(quest2,"belegt");
        questRepository.save(quest2);
       } catch (QuestStatusEingabeException e) {
           e.printStackTrace();
       }
        liste1 = questFacade.getAllQuest();

        assertThat(liste1).anyMatch(q -> q.getQueststatus().equals(quest2.getQueststatus()));

        // 12. Das System prüft ob der Benutzer den Einschränkungsregeln der Eingabe entsprechen

        // 13. Der Benutzer tippt auf Veränderung speichern
        System.out.println("<Klick auf Veränderung speichern>");

        // 14. Das System speicher diese Veränderung in der Datenbank

        // 15. Der Benutzer wischt zurück zum Reiter Quest
        System.out.println("<Wisch zurück zum Reiter Quest>");

        // 16. Das System lädt Quests aus der Datenbank
        liste1 = questFacade.getAllQuest();

        // 17. Der Benutzer sieht seine Quest mit dem neuen Status
        assertThat(liste1).hasSize(3);

            //test content
        assertThat(liste1).anyMatch(q -> q.getId().equals(quest1.getId()));
        assertThat(liste1).anyMatch(q -> q.getId().equals(quest2.getId()));
        assertThat(liste1).anyMatch(q -> q.getId().equals(quest3.getId()));

        assertThat(liste1).anyMatch(q -> q.getQueststatus().equals(quest2.getQueststatus()));

            //zeigt aktuelle quests an
        for (Quest e : liste1){
            System.out.println(e.toString());
        }
    }
}
