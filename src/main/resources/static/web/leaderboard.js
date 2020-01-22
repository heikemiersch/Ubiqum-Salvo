console.log("we are connected");

var board = new Vue({
  el: "#board",
  data: {
    leaderboard: []
  },

  methods: {

    fetchLeaderboard: function () {

      fetch("http://localhost:8080/api/leaderboard", {
          method: "GET"
        })
        .then(function (response) {
          console.log(response);
          return response.json();
        })
        .then(leaderboard => {
          this.leaderboard = leaderboard
        })

        .catch(function (error) {
          console.log(error, "<-- error!");
        });
    },
  },

  created: function () {
    this.fetchLeaderboard();
  }

})


// function getGameResult(score1, score2) {
//   return (score1 > score2) ? 'You win!' 
//     : (score1 < score2) ? 'You lost!'
//     : 'It was a tie!';
// }