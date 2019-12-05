package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BattleshipApplication {
	public static void main(String[] args) {
		SpringApplication.run(BattleshipApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository repositoryPlayer) {
		return (args) -> {
			// save a couple of players
			Player heike = new Player("Heike", "Miersch", "suseduse");
					repositoryPlayer.save(heike);
			Player hans = new Player("Hans", "Wurst", "erdelerde");
					repositoryPlayer.save(hans);

			/*repositoryPlayer.save(new Player("Heike", "Miersch", "suseduse"));
			repositoryPlayer.save(new Player("Hans", "Wurst"));
			repositoryPlayer.save(new Player("Peter", "Pan"));*/
		};
	}
}

