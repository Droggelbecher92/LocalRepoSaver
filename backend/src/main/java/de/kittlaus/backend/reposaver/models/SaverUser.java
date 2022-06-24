package de.kittlaus.backend.reposaver.models;

import de.kittlaus.backend.reposaver.models.GithubRepos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaverUser {

    private final String id = UUID.randomUUID().toString();

    private String githubUsername;
    private boolean isExistingGithubUser;
    private List<GithubRepos> savedRepos;

}
