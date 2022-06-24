package de.kittlaus.backend.reposaver.models;

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

    private String username;
    private List<GithubRepos> savedRepos;

}
