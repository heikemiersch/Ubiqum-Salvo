package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")

public class SalvoController {
    @Autowired
    private GameRepository repo;

    @Autowired
    private GamePlayerRepository repositoryGamePlayer;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(path="/games")
    public List<Object> getAll() {
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
        Game game = gamePlayer.getGame();
        List<Object> player = new ArrayList<>();
        Map<String,Object> PlayerJson = new HashMap<>();
        PlayerJson.put("username", gamePlayer.getPlayer().getUserName());
        PlayerJson.put("playerID", gamePlayer.getPlayer().getId());
        PlayerJson.put("scores", gamePlayer.getPlayer().getCurrentScore(game));

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
        GameViewJson.put("opponent", gamePlayer.getOpponent(gamePlayer).getPlayer().getUserName());
        GameViewJson.put("game_player_id", gamePlayerId);
        GameViewJson.put("ships", shipsInfo(gamePlayer));
        GameViewJson.put("salvoes", salvoesInfo(gamePlayer));
        GameViewJson.put("salvoesOpponent", salvoesInfo(gamePlayer.getOpponent(gamePlayer)));
        GameViewJson.put("scores", gamePlayer.getPlayer().getCurrentScore(gamePlayer.getGame()));

        game_info.add(GameViewJson);

        return game_info;
    }

   List<Object> shipsInfo(GamePlayer gameplayer) {
        List<Object> ship_info = new ArrayList<>();
        gameplayer.getShips().forEach (ship -> {
            Map<String, Object> ShipTypLocJson = new HashMap<>();
            ShipTypLocJson.put("type", ship.getType());
            ShipTypLocJson.put("shipLocation", ship.getShipLocation());

            ship_info.add(ShipTypLocJson);
        });
        return ship_info;
    }

    List<Object> salvoesInfo(GamePlayer gamePlayer) {
        List<Object> salvo_info = new ArrayList<>();
        int turn = 1;
        Set<Salvo> mySalvo = gamePlayer.getSalvoes();
        for (Salvo salvo : mySalvo) {
            Map<String, Object> SalvoTurnLocJson = new HashMap<>();
            SalvoTurnLocJson.put("turn", turn);
            SalvoTurnLocJson.put("salvoLocation", salvo.getSalvoLocation());
            turn += 1;
            salvo_info.add(SalvoTurnLocJson);
        }
        return salvo_info;
    }

    @RequestMapping(path="/leaderboard")
    public List<Object> getLeaderboard() {
        List<Object> leaderboard_info = new ArrayList<>();
        playerRepository.findAll().forEach(playerForLeaderboard -> {
            Map<String, Object> LeaderboardJson = new HashMap<>();
            LeaderboardJson.put("player", playerForLeaderboard.getUserName());
            LeaderboardJson.put("wins", playerForLeaderboard.getWins());
            LeaderboardJson.put("losses", playerForLeaderboard.getLosses());
            LeaderboardJson.put("ties", playerForLeaderboard.getTies());
            LeaderboardJson.put("total", playerForLeaderboard.getTotal());

            leaderboard_info.add(LeaderboardJson);
        });
        return leaderboard_info;
    }
    @Bean
    public PasswordEncoder passwordEncoder2() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String userName, @RequestParam String password) {
        if (userName.isEmpty() ||  password.isEmpty()) {
            return new ResponseEntity<>("something's missing", HttpStatus.FORBIDDEN);
        }
        if (playerRepository.findByUserName(userName) !=  null) {
            System.out.println(playerRepository.findByUserName(userName));
            return new ResponseEntity<>("name already in use", HttpStatus.CONFLICT);
        }
        playerRepository.save(new Player(userName, passwordEncoder2().encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}