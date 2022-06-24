package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.GithubRepo;
import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    @Test
    void userWithoutGithubShouldNotBeAbleToRegisterAndNotBeFound(){
        String username = "lkjasdjfkfdsljköjfdlöjkfdljklöfdsjlköasfjöfjjklsff";
        ResponseEntity<SaverUser> postResponse = testRestTemplate.postForEntity(URL, new NewSaverUser(username), SaverUser.class);
        assertEquals(HttpStatus.BAD_REQUEST,postResponse.getStatusCode());

        ResponseEntity<SaverUser> getResponse = testRestTemplate.getForEntity(URL +"find/"+ username, SaverUser.class);
        assertEquals(HttpStatus.NOT_FOUND,getResponse.getStatusCode());
    }

    @Test
    void userCanAddRepoToListButNoDuplicates(){
        String username = "jamarob";
        ResponseEntity<SaverUser> postResponse = testRestTemplate.postForEntity(URL, new NewSaverUser(username), SaverUser.class);
        GithubRepo repoToAdd = new GithubRepo("1","test","test");

        ResponseEntity<SaverUser> putResponse = testRestTemplate.exchange(URL +"add/"+ username, HttpMethod.PUT,new HttpEntity<>(repoToAdd), SaverUser.class);
        assertEquals(HttpStatus.OK,putResponse.getStatusCode());
        SaverUser putBody = putResponse.getBody();
        assertEquals(putBody.getSavedRepos().get(0),repoToAdd);

        ResponseEntity<SaverUser> duplicateResponse = testRestTemplate.exchange(URL +"add/"+ username, HttpMethod.PUT,new HttpEntity<>(repoToAdd), SaverUser.class);
        assertEquals(HttpStatus.CONFLICT,duplicateResponse.getStatusCode());

        ResponseEntity<SaverUser> unknownResponse = testRestTemplate.exchange(URL +"add/johnDoe", HttpMethod.PUT,new HttpEntity<>(repoToAdd), SaverUser.class);
        assertEquals(HttpStatus.NOT_FOUND,unknownResponse.getStatusCode());
    }
}