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
    loggedIn: false,
    authenticated: false
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

          this.displayTurn();
          this.displayShips();
          this.displaySalvoes();
          this.displaySalvoesOpponent();

          console.log(game);
          // console.log(this.player);
          // console.log(this.opponent);
          // console.log(this.ships);
          // console.log(this.salvoes);
          // console.log(this.salvoesOpponent);
          console.log(this.turn);
        })
        .catch(function (error) {
          console.log(error, "<-- error");
        });
    },

    signup: function () {
      console.log(this.username);
      console.log(this.password);
      fetch("/api/players", {
        method: "POST",
        credentials: "include",
        headers: {
          Accept: "application/json",
          "Content-type": "application/x-www-form-urlencoded"
        },
        body: `userName=${this.username}&password=${this.password}`
      }).then(response => {
        console.log(response);
        if (response.status === 201) {
          this.login();
          console.log("signed up you are!");
        } else if (response.status !== 201) {
          alert("something went wrong. please try again.");
        }
      });
    },

    login: function () {
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
          this.username = "";
          this.password = "";
        } else {
          alert("invalid login. have you even signed up?");
        }
      });
    },

    logout: function () {
      fetch("/api/logout", {
          method: "POST"
        })
        .then(response => console.log(response))

        .catch(function (error) {
          console.log(error, "<-- error!");
        });
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
    this.fetchData();
  }
});