package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.GithubRepo;
import de.kittlaus.backend.reposaver.models.SaverUser;
import org.springframework.stereotype.Repository;

import javax.management.InstanceAlreadyExistsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RepoSaverRepo {

    private Map<String,SaverUser> allUsers = new HashMap<>();


    public SaverUser save(SaverUser userToAdd) {
        allUsers.put(userToAdd.getId(),userToAdd);
        return userToAdd;
    }

    public Optional<SaverUser> findByUsername(String searchedUsername) {
        return allUsers.values().stream().filter(user -> user.getUsername().equals(searchedUsername)).findFirst();
    }
}
