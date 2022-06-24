package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.GithubRepos;
import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepoSaverService {

    private final RepoSaverRepo repoSaverRepo;

    public Optional<SaverUser> addNewUser(NewSaverUser userToAdd) {
        if (checkIfUserExits(userToAdd.getUsername())){
            SaverUser newUser = new SaverUser(userToAdd.getUsername(),new ArrayList<>());
            return Optional.of(repoSaverRepo.addNewUser(newUser));
        }
        return Optional.empty();
    }

    public Optional<SaverUser> findUser(String searchedUsername) {
        return repoSaverRepo.findByName(searchedUsername);
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

    public Optional<SaverUser> addRepoToUser(String username, GithubRepos repoToAdd) {
        return repoSaverRepo.addRepo(username,repoToAdd);
    }
}
