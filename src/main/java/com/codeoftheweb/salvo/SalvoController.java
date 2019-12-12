package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class SalvoController {
    @Autowired
    private GameRepository repo;

   /* public GameRestController(GameRepository repo) {
        this.repo = repo;*/

    @RequestMapping(path="/game_id")
    private List<Game> getAll() {
        return repo.findAll();
    }
}
