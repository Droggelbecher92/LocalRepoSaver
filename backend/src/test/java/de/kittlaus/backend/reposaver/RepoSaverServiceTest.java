package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.SaverUser;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

class RepoSaverServiceTest {

    @Test
    void shouldAddNewUser(){
        //GIVEN
        SaverUser testuser = SaverUser.builder()
                .githubUsername("Droggelbecher92")
                .isExistingGithubUser(true)
                .savedRepos(List.of())
                .build();
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser actual = testService.addNewUser(testuser);
        //THEN
        assertEquals(testuser.getSavedRepos(),actual.getSavedRepos());
        assertEquals(testuser.getGithubUsername(),actual.getGithubUsername());
    }

}