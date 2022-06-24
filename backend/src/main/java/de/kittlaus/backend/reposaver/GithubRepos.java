package de.kittlaus.backend.reposaver;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GithubRepos {


    private String name;
    private String html_url;
    private String language;

}
