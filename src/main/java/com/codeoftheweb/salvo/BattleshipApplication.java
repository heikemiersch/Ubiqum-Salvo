package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;

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

			Game game1 = new Game(new Date());

			/*Game game2 = new Game(new Date());

			Game game3 = new Game(new Date());*/
			repositoryGame.save(game1);
			repositoryPlayer.save(heike);

			GamePlayer gamePlayer1 = new GamePlayer(heike, game1);
            repositoryGamePlayer.save(gamePlayer1);

		/*	heike.addGamePlayer(gamePlayer1);
			game1.addGamePlayer(gamePlayer1);
*/
            repositoryPlayer.save(heike);
            repositoryPlayer.save(hans);
            repositoryGame.save(game1);
         /*   repositoryGame.save(game2);
			repositoryGame.save(game3);*/

		};
	}
}

