package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long game_id;
  /*  why does the game not have a name?*/
    public Date creationDate = new Date();

    @OneToMany(mappedBy = "Game", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayerSet;

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayerSet.add(gamePlayer);


    public Game(Date) {
        this.creationDate = new Date();
    }

}
