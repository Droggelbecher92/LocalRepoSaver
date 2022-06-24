package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.GithubRepos;
import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepoSaverServiceTest {

    @Test
    void shouldAddNewUser(){
        //GIVEN
        NewSaverUser testuser = new NewSaverUser("Droggelbecher92");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        Optional<SaverUser> actualOpt = testService.addNewUser(testuser);
        //THEN
        assertTrue(actualOpt.isPresent());
        SaverUser actual = actualOpt.get();
        assertEquals(new ArrayList<>(),actual.getSavedRepos());
        assertEquals(testuser.getUsername(),actual.getUsername());
    }

    @Test
    void shouldAddNewUserWithoutGithubAcc(){
        //GIVEN
        NewSaverUser testuser = new NewSaverUser("ajdsfjkasdjkfjköasdfjkdjksaf");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        Optional<SaverUser> actualOpt = testService.addNewUser(testuser);
        //THEN
        assertTrue(actualOpt.isEmpty());
    }

    @Test
    void shouldFindUserByName(){
        NewSaverUser testuser = new NewSaverUser("gossie");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser savedUser = testService.addNewUser(testuser).get();
        Optional<SaverUser> actual = testService.findUser(savedUser.getUsername());
        //THEN
        assertTrue(actual.isPresent());
        assertEquals(savedUser,actual.get());

    }

    @Test
    void shouldNotFindUnknownUserByName(){
        NewSaverUser testuser = new NewSaverUser("gossie");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser savedUser = testService.addNewUser(testuser).get();
        Optional<SaverUser> actual = testService.findUser("johnDoe");
        //THEN
        assertTrue(actual.isEmpty());

    }

    @Test
    void shouldAddNewRepoToList(){
        //GIVEN
        GithubRepos repoToAdd = new GithubRepos("1","test","test");
        SaverUser currentUser = new SaverUser("mysterix5",new ArrayList<>());
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        SaverUser actual = testService.addRepoToUser(currentUser.getUsername(),repoToAdd);
        //THEN
        assertEquals(1, actual.getSavedRepos().size());
        assertEquals(actual.getSavedRepos().get(0),repoToAdd);
    }


}

































