package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/saver")
public class RepoSaverController {

    private final RepoSaverService repoSaverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaverUser postNewUser (@RequestBody NewSaverUser newUser){
        return repoSaverService.addNewUser(newUser);
    }

    @GetMapping("find/{name}")
    public ResponseEntity<SaverUser> getUserByUsername (@PathVariable String name){
        return ResponseEntity.of(repoSaverService.findUser(name));
    }

}
