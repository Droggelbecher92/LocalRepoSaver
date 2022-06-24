package de.kittlaus.backend.reposaver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepos {


    private String id;
    private String name;
    private String html_url;

}
