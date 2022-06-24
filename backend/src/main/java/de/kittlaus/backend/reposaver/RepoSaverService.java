package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepoSaverService {

    private final RepoSaverRepo repoSaverRepo;

    public SaverUser addNewUser(NewSaverUser userToAdd) {
        SaverUser newUser = new SaverUser(userToAdd.getUsername(), checkIfUserExits(userToAdd.getUsername()),new ArrayList<>());
        return repoSaverRepo.addNewUser(newUser);
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


    public Optional<SaverUser> findUser(String searchedUsername) {
        return repoSaverRepo.findByName(searchedUsername);
    }
}
