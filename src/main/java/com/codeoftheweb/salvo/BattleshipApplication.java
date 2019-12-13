package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BattleshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleshipApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repositoryPlayer, GameRepository repositoryGame, GamePlayerRepository repositoryGamePlayer) {
		return (args) -> {
			// save a couple of players
			Player heike = new Player("Heisel", "Heike", "Miersch", "heike@erde.com");
			Player hans = new Player("Hansmann", "Hans", "Wurst", "hans@erde.com");
			Player helga = new Player("Helgaland", "Helga", "Honig", "helga@erde.com");

			Game game1 = new Game(new Date());
			Game game2 = new Game(new Date());
			Game game3 = new Game(new Date());

			GamePlayer gamePlayer1 = new GamePlayer(heike, game1);
			GamePlayer gamePlayer2 = new GamePlayer(hans, game1);
			GamePlayer gamePlayer3 = new GamePlayer(helga, game2);
			GamePlayer gamePlayer4 = new GamePlayer(hans, game2);
			GamePlayer gamePlayer5 = new GamePlayer(heike, game3);
			GamePlayer gamePlayer6 = new GamePlayer(helga, game3);

			repositoryGame.save(game1);
            repositoryPlayer.save(heike);
            repositoryPlayer.save(hans);

            repositoryGame.save(game2);
			repositoryPlayer.save(helga);
			repositoryPlayer.save(hans);

			repositoryGame.save(game3);
			repositoryPlayer.save(heike);
			repositoryPlayer.save(helga);

			repositoryGamePlayer.save(gamePlayer1);
			repositoryGamePlayer.save(gamePlayer2);
			repositoryGamePlayer.save(gamePlayer3);
			repositoryGamePlayer.save(gamePlayer4);
			repositoryGamePlayer.save(gamePlayer5);
			repositoryGamePlayer.save(gamePlayer6);
		};
	}
}

