package de.kittlaus.backend.reposaver;

import de.kittlaus.backend.reposaver.models.NewSaverUser;
import de.kittlaus.backend.reposaver.models.SaverUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/saver")
public class RepoSaverController {

    private final RepoSaverService repoSaverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SaverUser> postNewUser (@RequestBody NewSaverUser newUser){
        Optional<SaverUser> answer = repoSaverService.addNewUser(newUser);
        if (answer.isEmpty()){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(answer.get(),HttpStatus.CREATED);
        }
    }

    @GetMapping("find/{name}")
    public ResponseEntity<SaverUser> getUserByUsername (@PathVariable String name){
        return ResponseEntity.of(repoSaverService.findUser(name));
    }

}
