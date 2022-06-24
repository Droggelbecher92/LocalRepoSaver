package de.kittlaus.backend.reposaver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/saver")
public class RepoSaverController {

    private final RepoSaverService repoSaverService;

}
