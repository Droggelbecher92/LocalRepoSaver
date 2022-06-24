package de.kittlaus.backend.reposaver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepoSaverService {

    private final RepoSaverRepo repoSaverRepo;

}
