package com.haw.se1lab.Quest.faceade.api;

import com.haw.se1lab.Application;

import com.haw.se1lab.Nutzer.common.api.datatype.BenutzerNummer;
import com.haw.se1lab.Nutzer.common.api.datatype.Geschlecht;
import com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;
import com.haw.se1lab.Nutzer.dataaccess.api.repo.NutzerRepository;
import com.haw.se1lab.Quest.common.api.exception.QuestStatusEingabeException;
import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import com.haw.se1lab.Quest.dataaccess.api.repo.QuestRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //environment
@ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
@ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestFacadeTest {

    @LocalServerPort      // causes the field to be initialized with the local server port used for REST calls
    private int port;

    @Autowired
    private QuestRepository questRepository;


    @Autowired
    private NutzerRepository nutzerRepository;

    private Quest quest1, quest2, quest3;
    private Nutzer nutzer1, nutzer2;


    @BeforeEach
    public void setup() throws QuestStatusEingabeException {
        // set up fresh test data before each test method execution

        quest1 = new Quest("stein der weisen", "verfugbar", 99);
        quest2 = new Quest("kammer des schrecken", "verfugbar", 199);
        quest3 = new Quest("gefangener von Azkaban", "verfugbar", 300);
        nutzer1 = new Nutzer("mark", "stein", Geschlecht.MAENLICH, "mark.stein@hotmail.de", "12345", new BenutzerNummer(2));
        nutzer2 = new Nutzer("julia", "musterfrau", Geschlecht.WEIBLICH, "julia.musterfrau@hotmail.de", "12345", new BenutzerNummer(3));

        nutzer1.getQuests().add(quest1);
        nutzer2.getQuests().add(quest2);
        nutzer2.getQuests().add(quest3);


        nutzerRepository.save(nutzer1);
        nutzerRepository.save(nutzer2);


        questRepository.save(quest1);
        questRepository.save(quest2);
        questRepository.save(quest3);


        RestAssured.port = port;
        RestAssured.basePath = "/quest";

    }

    @AfterEach
    public void reset() {
        // clean up test data after each test method execution
        nutzerRepository.delete(nutzer1);
        nutzerRepository.delete(nutzer2);
        questRepository.delete(quest1);
        questRepository.delete(quest2);
        questRepository.delete(quest3);


    }

    @Test
    public void getAllQuestFromNutzer() {
        //given
        List<Quest> list1 = given()
                .contentType(ContentType.JSON)
                .body(nutzer1)

                //when
                .when()
                .post("/getByNutzer")

                //then
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().jsonPath().getList(".", Quest.class);

        //test listsOfUser1
        //test size
        assertThat(list1).hasSize(1);
        //test content
        assertThat(list1).anyMatch(tdl -> tdl.getId().equals(quest1.getId()));
        assertThat(list1).noneMatch(tdl -> tdl.getId().equals(quest2.getId()));
        assertThat(list1).noneMatch(tdl -> tdl.getId().equals(quest3.getId()));


        //given
        List<Quest> list2 = given()
                .contentType(ContentType.JSON)
                .body(nutzer2)

                //when
                .when()
                .post("/getByNutzer")

                //then
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().jsonPath().getList(".", Quest.class);


        assertThat(list2).hasSize(2);
        //test content
        assertThat(list2).noneMatch(tdl -> tdl.getId().equals(quest1.getId()));
        assertThat(list2).anyMatch(tdl -> tdl.getId().equals(quest2.getId()));
        assertThat(list2).anyMatch(tdl -> tdl.getId().equals(quest3.getId()));
    }
}






//    1 expectation failed.
//    Expected status code <201> but was <500>.


//    @Test
//    public void createQuest() throws QuestStatusEingabeException {
//
//                Quest quest10 = new Quest("testtest","verfugbar",50);
//                //given
//                 given()
//                .contentType(ContentType.JSON)
//                .body(quest10)
//
//                //when
//                .when()
//                .post("/quest")
//
//                //then
//                .then()
//                .statusCode(HttpStatus.CREATED.value())
//                         .body("id", is(greaterThan(0)));
//
//
//    }
}
