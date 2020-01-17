
const urlParams = new URLSearchParams(window.location.search);
const myParam = urlParams.get('gp');

var app = new Vue({
    el: "#app",
    data: {
        rows: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        columns: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]  ,
        player: "",
        opponent: ""
        
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
                            
            console.log(game);
            console.log(this.player);
            console.log(this.opponent);
                  
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
