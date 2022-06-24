package de.kittlaus.backend.reposaver;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class SaverUser {

    private final String id = UUID.randomUUID().toString();

    private String githubUsername;
    private boolean isExistingGithubUser;
    private List<GithubRepos> savedRepos;

}
