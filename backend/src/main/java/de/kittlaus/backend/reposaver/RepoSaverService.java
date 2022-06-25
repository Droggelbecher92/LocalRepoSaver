package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.GithubRepo;
import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepoSaverService {

    private final RepoSaverRepo repoSaverRepo;

    public Optional<SaverUser> addNewUser(NewSaverUser userToAdd) {
        if (checkIfUserExits(userToAdd.getUsername())){
            SaverUser newUser = new SaverUser(userToAdd.getUsername(),new ArrayList<>());
            return Optional.of(repoSaverRepo.save(newUser));
        }
        return Optional.empty();
    }

    public Optional<SaverUser> findUser(String searchedUsername) {
        return repoSaverRepo.findByUsername(searchedUsername);
    }

    public Optional<SaverUser> addRepoToUser(String username, GithubRepo repoToAdd) throws InstanceAlreadyExistsException {
        Optional<SaverUser> optUser = findUser(username);
        if (optUser.isEmpty()){
            return optUser;
        }
        SaverUser user = optUser.get();
        if (user.getSavedRepos().contains(repoToAdd)){
            throw new InstanceAlreadyExistsException();
        }
        user.getSavedRepos().add(repoToAdd);
        return Optional.of(repoSaverRepo.save(user));
    }

    public Optional<SaverUser> removeRepoFromUser(String username, GithubRepo repoToRemove) {
        Optional<SaverUser> optUser = findUser(username);
        if (optUser.isEmpty()){
            return optUser;
        }
        SaverUser user = optUser.get();
        if (!user.getSavedRepos().contains(repoToRemove)){
            throw new NoSuchElementException();
        }
        user.getSavedRepos().remove(repoToRemove);
        return Optional.of(repoSaverRepo.save(user));
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
