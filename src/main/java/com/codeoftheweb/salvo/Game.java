package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Game<Person> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    public Date creationDate = new Date();

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Game.class, mappedBy = "Game")
    @JoinColumn(name="player_id")
    private Person Player;


    public Game() { }

    public Game(Date date) {
        this.creationDate = new Date();
    }

}
