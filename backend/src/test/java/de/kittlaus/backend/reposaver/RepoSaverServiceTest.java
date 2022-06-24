package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepoSaverServiceTest {

    @Test
    void shouldAddNewUser(){
        //GIVEN
        NewSaverUser testuser = new NewSaverUser("Droggelbecher92");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser actual = testService.addNewUser(testuser);
        //THEN
        assertEquals(new ArrayList<>(),actual.getSavedRepos());
        assertEquals(testuser.getUsername(),actual.getUsername());
        assertTrue(actual.isExistingGithubUser());
    }

    @Test
    void shouldAddNewUserWithoutGithubAcc(){
        //GIVEN
        NewSaverUser testuser = new NewSaverUser("ajdsfjkasdjkfjköasdfjkdjksaf");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser actual = testService.addNewUser(testuser);
        //THEN
        assertEquals(new ArrayList<>(),actual.getSavedRepos());
        assertEquals(testuser.getUsername(),actual.getUsername());
        assertFalse(actual.isExistingGithubUser());
    }

    @Test
    void shouldFindUserById(){
        NewSaverUser testuser = new NewSaverUser("ajdsfjkasdjkfjköasdfjkdjksaf");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser savedUser = testService.addNewUser(testuser);
        SaverUser actual = testService.findUserById(savedUser.getId());
        //THEN
        assertEquals(savedUser,actual);

    }

}