package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RepoSaverIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String URL = "/api/saver/";

    @Test
    void userShouldBeAbleToRegisterAndBeFound(){
        String username = "Droggelbecher92";
        ResponseEntity<SaverUser> postResponse = testRestTemplate.postForEntity(URL, new NewSaverUser(username), SaverUser.class);
        assertEquals(HttpStatus.CREATED,postResponse.getStatusCode());
        assertTrue(postResponse.hasBody());
        SaverUser postBody = postResponse.getBody();
        assertEquals(Objects.requireNonNull(postBody).getUsername(),username);
        assertEquals(0, postBody.getSavedRepos().size());

        ResponseEntity<SaverUser> getResponse = testRestTemplate.getForEntity(URL +"find/"+ username, SaverUser.class);
        assertEquals(HttpStatus.OK,getResponse.getStatusCode());
        assertTrue(getResponse.hasBody());
        SaverUser getBody = getResponse.getBody();
        assertEquals(Objects.requireNonNull(getBody).getUsername(),username);
    }

}