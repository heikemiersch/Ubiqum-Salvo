package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String type;
    //    (i.e. cruiser, destroyer or battleship)
    private String position;
    //    (i.e. a list of locations)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    public Ship() { }

    public Ship(String type, String position) {
        this.type = type;
        this.position = position;
            // position is supposed to be a list of locations
    }

    public GamePlayer getGamePlayer(){
        return this.gamePlayer;
    }

    public long getId() {
        return id;
    }

    public void setGame_id(long game_id) {
        this.id = game_id;
    }

    private String getPosition() {
        return position;
    }

    private void setPosition(String position) {
        this.position = position;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
}
