
var app = new Vue({
    el: "#app",
    data: {
        rows: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        columns: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]  ,
        player: "",
        gameObject: ""      
    },
    methods: {
        fetchData: function() {
            // var game; 
        fetch("/api/game_view/1", {})
          .then(response => response.json())
          .then(game => {
              this.player = game[0].player.userName;
        
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

