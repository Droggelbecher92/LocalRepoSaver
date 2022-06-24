package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.GithubRepo;
import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import org.junit.jupiter.api.Test;

import javax.management.InstanceAlreadyExistsException;
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
    void shouldAddNewRepoToList() throws InstanceAlreadyExistsException {
        //GIVEN
        GithubRepo repoToAdd = new GithubRepo("1","test","test");
        NewSaverUser currentUser = new NewSaverUser("mysterix5");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        testService.addNewUser(currentUser);
        //WHEN
        Optional<SaverUser> optActual = testService.addRepoToUser(currentUser.getUsername(),repoToAdd);
        //THEN
        assertTrue(optActual.isPresent());
        SaverUser actual = optActual.get();
        assertEquals(1, actual.getSavedRepos().size());
        assertEquals(actual.getSavedRepos().get(0),repoToAdd);
    }

    @Test
    void shouldNotAddNewRepoToListWithUnknownUser() throws InstanceAlreadyExistsException {
        //GIVEN
        GithubRepo repoToAdd = new GithubRepo("1","test","test");
        NewSaverUser currentUser = new NewSaverUser("mysterix5");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        //WHEN
        Optional<SaverUser> optActual = testService.addRepoToUser(currentUser.getUsername(),repoToAdd);
        //THEN
        assertTrue(optActual.isEmpty());
    }

    @Test
    void shouldNotAddDuplicateRepoToList() throws InstanceAlreadyExistsException {
        //GIVEN
        GithubRepo repoToAdd = new GithubRepo("1","test","test");
        NewSaverUser currentUser = new NewSaverUser("mysterix5");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        testService.addNewUser(currentUser);
        //WHEN
        testService.addRepoToUser(currentUser.getUsername(),repoToAdd);
        try {
            testService.addRepoToUser(currentUser.getUsername(),repoToAdd);
        } catch (InstanceAlreadyExistsException e){
            //THEN
            assertTrue(true);
        }
    }

    @Test
    void shouldDeleteRepoFromList() throws InstanceAlreadyExistsException {
        //GIVEN
        GithubRepo repoToAdd = new GithubRepo("1","test","test");
        NewSaverUser currentUser = new NewSaverUser("mysterix5");
        RepoSaverService testService = new RepoSaverService(new RepoSaverRepo());
        testService.addNewUser(currentUser);
        testService.addRepoToUser(currentUser.getUsername(),repoToAdd);
        //WHEN
        Optional<SaverUser> optActual = testService.removeRepoFromUser(currentUser.getUsername(),repoToAdd);
        //THEN
        assertTrue(optActual.isPresent());
        SaverUser actual = optActual.get();
        assertEquals(0,actual.getSavedRepos().size());
    }


}

































