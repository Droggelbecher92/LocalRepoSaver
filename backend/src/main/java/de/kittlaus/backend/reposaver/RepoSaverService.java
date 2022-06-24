package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.SaverUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepoSaverService {

    private final RepoSaverRepo repoSaverRepo;

    public SaverUser addNewUser(SaverUser userToAdd) {
        return repoSaverRepo.addNewUser(userToAdd);
    }
}
