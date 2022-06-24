package de.kittlaus.backend.reposaver.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewSaverUser {

    private String username;

    public NewSaverUser(String username) {
        this.username = username;
    }
}
