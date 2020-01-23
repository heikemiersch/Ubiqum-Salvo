package com.codeoftheweb.salvo;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Double currentScore;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<Score> scores = new HashSet<>();



/*    @JsonIgnore
    public Set<Score> getScores() {
        return scores;
    }*/


    public Double getCurrentScore(Game game){
        Double currentScore=0.0;
        //System.out.println(game);
        for(Score score: scores){
            //System.out.println(score.getScore());
           if(game==score.getGame()){
               currentScore=score.getScore();
             //  return currentScore;
           }else{currentScore=0.0;}
        }
        //System.out.println(currentScore);
        return currentScore;
    }


    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    public Player() { }

    public Player(String name, String firstName, String lastName, String email, String password) {
        this.userName = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName;}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* @JsonIgnore
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }*/

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public String toString() {
      return firstName + " " + lastName + " with the " + userName + ", " + email;    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

    // hier kommt später addScore rein

     public void addScore(Score score){
        System.out.println(score);
        score.setPlayer(this);
        scores.add(score);
    }

    public int getWins() {
        int wins = 0;
        for (Score score: scores) {
            if(score.getScore() == 1.0) {
                wins += 1;
            }
        } return wins;
    }

    public int getLosses() {
        int losses = 0;
        for (Score score: scores) {
            if(score.getScore() == 0.0) {
                losses =+ 1;
            }
        } return losses;
    }

    public int getTies() {
        int ties = 0;
        for(Score score: scores){
           if(score.getScore() == 0.5){
             ties += 1;
           }
        } return ties;
    }

    public double getTotal() {
        double total = 0.0;
        for (Score score: scores)
        { total += score.getScore();
        } return total;
    }
}
