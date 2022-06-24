package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.SaverUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepoSaverServiceTest {

    @Test
    void shouldAddNewUser(){
        //GIVEN
        SaverUser testuser = SaverUser.builder()
                .username("Droggelbecher92")
                .build();
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser actual = testService.addNewUser(testuser);
        //THEN
        assertEquals(testuser.getSavedRepos(),actual.getSavedRepos());
        assertEquals(testuser.getUsername(),actual.getUsername());
        assertTrue(actual.isExistingGithubUser());
    }

    @Test
    void shouldAddNewUserWithoutGithubAcc(){
        //GIVEN
        SaverUser testuser = SaverUser.builder()
                .username("ajdsfjkasdjkfjköasdfjkdjksaf")
                .build();
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser actual = testService.addNewUser(testuser);
        //THEN
        assertEquals(testuser.getSavedRepos(),actual.getSavedRepos());
        assertEquals(testuser.getUsername(),actual.getUsername());
        assertFalse(actual.isExistingGithubUser());
    }

}