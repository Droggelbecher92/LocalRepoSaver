package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RepoSaverIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void userShouldBeAbleToRegisterAndBeFound(){
        ResponseEntity<SaverUser> postResponse = testRestTemplate.postForEntity("/api/saver", new NewSaverUser("Droggelbecher92"), SaverUser.class);
        assertEquals(HttpStatus.CREATED,postResponse.getStatusCode());
    }

}