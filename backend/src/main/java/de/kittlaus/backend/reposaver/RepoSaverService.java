package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.SaverUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class RepoSaverService {

    private final RepoSaverRepo repoSaverRepo;

    public SaverUser addNewUser(SaverUser userToAdd) {
        userToAdd.setSavedRepos(new ArrayList<>());
        userToAdd.setExistingGithubUser(checkIfUserExits(userToAdd.getUsername()));
        return repoSaverRepo.addNewUser(userToAdd);
    }

    private boolean checkIfUserExits(String username) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("https://api.github.com/users/" + username, Object.class);
        }catch (HttpClientErrorException err){
            return false;
        }
        return true;
    }
}
