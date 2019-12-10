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
	public CommandLineRunner initData(PlayerRepository repositoryPlayer, GameRepository repositoryGame) {
		return (args) -> {
			// save a couple of players
			Player heike = new Player("Heisel", "Heike", "Miersch", "heike@erde.com");
					repositoryPlayer.save(heike);
			Player hans = new Player("Hansmann", "Hans", "Wurst", "hans@erde.com");
					repositoryPlayer.save(hans);
			Game game1 = new Game();
			repositoryGame.save(game1);
			Game game2 = new Game();
			repositoryGame.save(game2);
			Game game3 = new Game();
			repositoryGame.save(game3);
		};
	}
}

