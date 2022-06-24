package de.kittlaus.backend.reposaver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepos {


    private String name;
    private String html_url;
    private String language;

}
