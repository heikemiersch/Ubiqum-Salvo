const urlParams = new URLSearchParams(window.location.search);
const myParam = urlParams.get("gp");

var app = new Vue({
  el: "#app",
  data: {
    rows: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
    columns: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
    player: "",
    opponent: "",
    turn: null,
    ships: [],
    salvoes: [],
    salvoesOpponent: [],
    username: "",
    password: "",
    usernameSign: "",
    passwordSign: "",
    loggedIn: false,
    authenticated: false,
    gamePlayerID: ""
  },

  methods: {
    fetchData: function () {
      fetch(`/api/game_view/${myParam}`, {
          method: "GET"
        })
        .then(response => response.json())
        .then(game => {
          this.player = game[0].player.userName;
          this.opponent = game[0].opponent;

          this.ships = game[0].ships;
          this.salvoes = game[0].salvoes;
          this.salvoesOpponent = game[0].salvoesOpponent;

          // this.gamePlayerID = getParameterByName('gp');
          // console.log(gamePlayerId);

          this.displayTurn();
          this.displayShips();
          this.displaySalvoes();
          this.displaySalvoesOpponent();
          // hier eine funktion aufrufen,
          // die den fragenden player mit dem eingeloggten abgleicht

          console.log(game);
          console.log(this.turn);
        })
        .catch(function (error) {
          console.log(error, "<-- error");
        });
    },

    signup: function () {
      // console.log(this.username);
      // console.log(this.password);
      fetch("/api/players", {
        method: "POST",
        credentials: "include",
        headers: {
          Accept: "application/json",
          "Content-type": "application/x-www-form-urlencoded"
        },
        body: `userName=${this.usernameSign}&password=${this.passwordSign}`
      }).then(response => {
        console.log(response);
        if (response.status == 201) {
          this.username = this.usernameSign;
          this.password = this.passwordSign;
          this.login();
          console.log("signed up you are!");
        } else if (response.status !== 201) {
          console.log(response.status);
          alert("that username already in use. please make a new choice.");
          this.resetInput();
        }
      });
    },

    resetInput: function () {
      this.username = "";
      this.password = "";
      this.usernameSign = "";
      this.passwordSign = "";
    },

    login: function () {
      console.log(this.username);
      fetch("/api/login", {
        method: "POST",
        credentials: "include",
        headers: {
          Accept: "application/json",
          "Content-type": "application/x-www-form-urlencoded"
        },
        body: `userName=${this.username}&password=${this.password}`
      }).then(response => {
        if (response.status == 200) {
          console.log("logged in");
          this.authenticated = true;
          this.loggedIn = true;
          window.location.href = "http://localhost:8080/web/games.html?";
          // "http://localhost:8080/web/game.html?gp=${gamePlayerID}";
          //  find out who is logged in and put his or her nn at the end of href
        } else {
          alert("invalid login. please sign up first.");
          window.location.reload();
        }
      });
    },

    logout: function () {
      fetch("/api/logout", {
        method: "POST",
        credentials: "include",
        headers: {
          Accept: "application/json",
          "Content-type": "application/x-www-form-urlencoded"
        },
        body: `userName=${this.username}&password=${this.password}`
      }).then(response => {
        if (response.status == 200) {
          console.log("logout successful");
        }
      });
    },

    logout2: function () {
      this.logout();
      this.loggedIn = false;
      this.authenticated = false;
    },

    logout3: function () {
      this.logout();
      this.loggedIn = false;
      this.authenticated = false;
      window.location.href = "http://localhost:8080/web/index.html?";
    },

    createGame: function () {
      fetch("/api/games", {
          method: 'POST',
          credentials: 'include',
          headers: {
            'Accept': 'application/json',
            'Content-type': 'application/x-www-form-urlencoded'
          },
          body: `username=${this.username}`
        })
        .then(response => {
          console.log(response)

          if (response.status == 201) {
            console.log("you just created a game.")
            return response.json();
          } else {
            alert("nah, no game created. button works, though.");
          }
        }).then(function (gamePlayerID) {
          console.log(gamePlayerID)
          window.location.href = "http://localhost:8080/web/game.html?gp=" + gamePlayerID;
        })
        .catch(error => console.log(error))
    },

    joinGame: function () {
      console.log("this one doesn't do anything, yet.")
    },

    returnToGame: function () {
      console.log("this one doesn't do anything, yet.")
    },

    displayTurn() {
      this.turn = this.salvoes.length + 1;
    },

    displayShips() {
      for (i in this.ships) {
        let k = this.ships[i].shipLocation;
        for (j in k) {
          //console.log(k[j] + "p1");
          document.getElementById(k[j] + "p1").style.backgroundColor = "black";
        }
      }
    },

    displaySalvoes() {
      for (i in this.salvoes) {
        let k = this.salvoes[i].salvoLocation;
        for (j in k) {
          //console.log(k[j] + "p2");
          document.getElementById(k[j] + "p2").innerHTML = "x";
          document.getElementById(k[j] + "p2").style.color = "red";
          document.getElementById(k[j] + "p2").style.textAlign = "center";
        }
      }
    },

    displaySalvoesOpponent() {
      for (i in this.salvoesOpponent) {
        let k = this.salvoesOpponent[i].salvoLocation;
        for (j in k) {
          //console.log(k[j] + "p1");
          document.getElementById(k[j] + "p1").innerHTML = "x";
          document.getElementById(k[j] + "p1").style.color = "red";
          document.getElementById(k[j] + "p1").style.textAlign = "center";
        }
      }
    }
  },

  created: function () {
    // this.fetchData();
  }
});