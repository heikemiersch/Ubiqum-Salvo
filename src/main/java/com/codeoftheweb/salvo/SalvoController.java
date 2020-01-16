package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private GamePlayerRepository repositoryGamePlayer;

    @RequestMapping(path="/games")
    public List<Object> getAll() {
//        create a list of objects and put everything in there
       List<Object> games_info = new ArrayList<>();
        repo.findAll().forEach(game -> {
            Map<String, Object> GamesJson= new HashMap<>();
            GamesJson.put("game_id", game.getGame_id());
            GamesJson.put("creation_date", game.getCreationDate().toString());
            GamesJson.put("game_player", getGamePlayer(game));

            games_info.add(GamesJson);
        });
        return games_info;
    }

    public List<Object> getGamePlayer (Game game) {
        List<Object> players_in_game = new ArrayList<>();
        game.gamePlayers.forEach (GP -> {
            Map<String, Object> GamesJson= new HashMap<>();
            GamesJson.put("game_player_id", GP.getGamePlayer_id());
            GamesJson.put("player", getPlayer(GP));

            players_in_game.add(GamesJson);
        });
        return players_in_game;
    }

    public List<Object> getPlayer (GamePlayer gamePlayer){
        List<Object> player = new ArrayList<>();
        Map<String,Object> PlayerJson = new HashMap<>();
        PlayerJson.put("username", gamePlayer.getPlayer().getUserName());
        PlayerJson.put("playerID", gamePlayer.getPlayer().getId());
        PlayerJson.put("playerEmail", gamePlayer.getPlayer().getEmail());
        PlayerJson.put("playerFirstName", gamePlayer.getPlayer().getFirstName());
        PlayerJson.put("playerLastName", gamePlayer.getPlayer().getLastName());

            player.add(PlayerJson);

            return player;
    }

    @RequestMapping(path="/game_view/{gamePlayerId}")
    public List<Object> getGame(@PathVariable long gamePlayerId) {
        List<Object> game_info = new ArrayList<>();
        GamePlayer gamePlayer = repositoryGamePlayer.getOne(gamePlayerId);
            Map<String, Object> GameViewJson= new HashMap<>();
            GameViewJson.put("game_id", gamePlayer.getGame().getGame_id());
            GameViewJson.put("creation_date", gamePlayer.getGame().getCreationDate());
            GameViewJson.put("player", gamePlayer.getPlayer());
            GameViewJson.put("game_player_id", gamePlayerId);
            GameViewJson.put("ships", shipsInfo(gamePlayer));

            game_info.add(GameViewJson);

        return game_info;
    }

   List<Object> shipsInfo(GamePlayer gameplayer) {
        List<Object> ship_info = new ArrayList<>();
        gameplayer.getShips().forEach (ship -> {
            Map<String, Object> ShipTypLocJson = new HashMap<>();
            ShipTypLocJson.put("Type", ship.getType());
            ShipTypLocJson.put("Location", ship.getLocation());
            ship_info.add(ShipTypLocJson);
        });
        return ship_info;
    }

}