package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class SalvoController {
    @Autowired
    private GameRepository repo;

    @RequestMapping(path="/games")
    public List<Object> getAll() {
//        so kreiere ich listen von objekten, in diese kommt alles rein, was ich brauche, daher "info"
       List<Object> games_info = new ArrayList<>();
        repo.findAll().forEach(game -> {
            Map<String, Object> MyJson= new HashMap<>();
            MyJson.put("game_id", game.getGame_id());
            MyJson.put("creation_date", game.getCreationDate().toString());
            MyJson.put("game_player", getGamePlayer(game));

            games_info.add(MyJson);
        });
        return games_info;
    }

    public List<Object> getGamePlayer (Game game) {
        List<Object> players_in_game = new ArrayList<>();
        game.gamePlayers.forEach (GP -> {
            Map<String, Object> MyOtherJson= new HashMap<>();
            MyOtherJson.put("game_player_id", GP.getGamePlayer_id());
            MyOtherJson.put("player", zeigIchDir(GP));

            players_in_game.add(MyOtherJson);
        });
        return players_in_game;
    }

    public List<Object> zeigIchDir (GamePlayer gamePlayer){
        List<Object> hohohaha =new ArrayList<>();
        Map<String,Object> Gehtgleich = new HashMap<>();
            Gehtgleich.put("username", gamePlayer.getPlayer().getUserName());
            Gehtgleich.put("playerID", gamePlayer.getPlayer().getId());
            Gehtgleich.put("playerEmail", gamePlayer.getPlayer().getEmail());
            Gehtgleich.put("playerFirstName", gamePlayer.getPlayer().getFirstName());
            Gehtgleich.put("playerLastName", gamePlayer.getPlayer().getLastName());
            hohohaha.add(Gehtgleich);

            return hohohaha;
    }
}
