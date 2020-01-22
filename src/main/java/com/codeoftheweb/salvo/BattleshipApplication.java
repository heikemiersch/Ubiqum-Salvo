package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BattleshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleshipApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repositoryPlayer,
									  GameRepository repositoryGame,
									  GamePlayerRepository repositoryGamePlayer,
									  ShipRepository shipRepository,
									  SalvoRepository salvoRepository,
                                      ScoreRepository scoreRepository) {
		return (args) -> {
			// Players
			Player heike = new Player("Heisel", "Heike", "Miersch", "heike@erde.com");
			Player hans = new Player("Hänsel", "Hans", "Wurst", "hans@erde.com");
			Player grete = new Player("Gretel", "Grete", "Gaga", "grete@erde.com");

			// Games
			Game game1 = new Game(new Date());
			Game game2 = new Game(new Date());
			Game game3 = new Game(new Date());

			// GamePlayers
			GamePlayer gamePlayer1 = new GamePlayer(heike, game1);
			GamePlayer gamePlayer2 = new GamePlayer(hans, game1);
			GamePlayer gamePlayer3 = new GamePlayer(grete, game2);
			GamePlayer gamePlayer4 = new GamePlayer(hans, game2);
			GamePlayer gamePlayer5 = new GamePlayer(heike, game3);
			GamePlayer gamePlayer6 = new GamePlayer(grete, game3);

			// ShipLocations
			List<String> shipLocList1 = Arrays.asList("A1","A2","A3");
			List<String> shipLocList2 = Arrays.asList("B1","B2","B3");
			List<String> shipLocList3 = Arrays.asList("F2","F3");
			List<String> shipLocList4 = Arrays.asList("D5","D6");

			// ships
			Ship ship1 = new Ship("Destroyer", shipLocList1);
			Ship ship2 = new Ship("Submarine", shipLocList2);
			Ship ship3 = new Ship("Carrier", shipLocList3);
			Ship ship4 = new Ship("Carrier", shipLocList4);

			// add ships to gamePlayers
			gamePlayer1.addShip(ship1);
			gamePlayer2.addShip(ship2);
			gamePlayer1.addShip(ship3);
			gamePlayer2.addShip(ship4);

			List<String> salvoLocList1 = Arrays.asList("B2","B3","B4");
			List<String> salvoLocList2 = Arrays.asList("B2","B3","B4");
			List<String> salvoLocList3 = Arrays.asList("F5","F6", "F7");
			List<String> salvoLocList4 = Arrays.asList("D5","D6", "D7");

			// create salvoes
			Salvo salvo1 = new Salvo (1, salvoLocList1);
			Salvo salvo2 = new Salvo (1, salvoLocList2);
			Salvo salvo3 = new Salvo (2, salvoLocList3);
			Salvo salvo4 = new Salvo (2, salvoLocList4);

			// add salvoes to gamePlayers
			gamePlayer1.addSalvo(salvo1);
			gamePlayer2.addSalvo(salvo2);
			gamePlayer1.addSalvo(salvo3);
			gamePlayer2.addSalvo(salvo4);

			repositoryPlayer.save(heike);
			repositoryGame.save(game1);
			repositoryPlayer.save(hans);
			repositoryGame.save(game1);
			repositoryPlayer.save(grete);
			repositoryGame.save(game2);
			repositoryPlayer.save(hans);
			repositoryGame.save(game2);
			repositoryPlayer.save(heike);
			repositoryGame.save(game3);
			repositoryPlayer.save(grete);
			repositoryGame.save(game3);

			// create scores
			Score score1 = new Score(1.0, game1, heike);
			Score score2 = new Score(0.0, game1, hans);
			Score score3 = new Score (0.5, game2, grete);
			Score score4 = new Score (0.5, game2, hans);
			Score score5 = new Score (1.0, game3, heike);
			Score score6 = new Score (0.0, game3, grete);

			//add scores to players
			//heike.addScore(score1);
			//hans.addScore(score2);

			//game1.addScore(score1);
			//game1.addScore(score2);



			// save stuff to repositories

			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			scoreRepository.save(score4);
			scoreRepository.save(score5);
			scoreRepository.save(score6);

			repositoryGame.save(game1);
			repositoryPlayer.save(heike);
			repositoryPlayer.save(hans);

			repositoryGame.save(game2);
			repositoryPlayer.save(grete);
			repositoryPlayer.save(hans);

			repositoryGame.save(game3);
			repositoryPlayer.save(heike);
			repositoryPlayer.save(grete);

			repositoryGamePlayer.save(gamePlayer1);
			repositoryGamePlayer.save(gamePlayer2);
			repositoryGamePlayer.save(gamePlayer3);
			repositoryGamePlayer.save(gamePlayer4);
			repositoryGamePlayer.save(gamePlayer5);
			repositoryGamePlayer.save(gamePlayer6);

			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);

			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);

			System.out.println("I am ready.");

		};
	}
}

