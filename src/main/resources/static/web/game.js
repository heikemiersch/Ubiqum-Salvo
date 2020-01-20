
const urlParams = new URLSearchParams(window.location.search);
const myParam = urlParams.get('gp');

var app = new Vue({
    el: "#app",
    data: {
        rows: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        columns: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]  ,
        player: "",
        opponent: "",
        ships:[],
        salvoes:"",
        salvoesOpponent:""
        
    },
    methods: {  
      
        fetchData: function() {          
          fetch(`/api/game_view/${myParam}`, {
            method: "GET",
          })
          .then(response => response.json())
          .then(game => {
              this.player = game[0].player.userName;
              this.opponent = game[0].opponent;
              this.ships = game[0].ships;
              this.salvoes = game[0].salvoes;
              this.salvoes = game[0].salvoesOpponent
              ;
                            
            console.log(game);
            console.log(this.player);
            console.log(this.opponent);
            // console.log(this.ships);
            console.log(this.salvoes);
            console.log(this.salvoesOpponent
              );
          })
          .catch(function (error) {
            console.log(error, "<-- error");
          });
        }

    },

    created: function () {
      this.fetchData();
      }


})
