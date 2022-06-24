package de.kittlaus.backend.reposaver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RepoSaverRepo {

    private Map<SaverUser,String> allUsers = new HashMap<SaverUser, String>();


}
