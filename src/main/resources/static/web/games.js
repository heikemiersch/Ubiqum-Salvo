console.log("we are connected");



fetch("http://localhost:8080/api/games", {
    method: "GET"
  })
  .then(function (response) {
    console.log(response);
    return response.json();
  })
  .then(function (response) {
    let gamesList = response;
    createList(gamesList);
  })

  .catch(function (error) {
    console.log(error, "<-- error!");
  });


//   create one list for each game object in the json

function createList(gamesList) {
  let list = document.getElementById("listOfGames");
  list.innerHTML = "";

  for (let i = 0; i < gamesList.length; i++) {

    let listItemGameId = document.createElement("li");
    listItemGameId.innerHTML = "Game ID: " + gamesList[i].game_id;
    let listItemCreationDate = document.createElement("li");
    listItemCreationDate.innerHTML = "Date: " + gamesList[i].creation_date;

    list.appendChild(listItemGameId);
    list.appendChild(listItemCreationDate);

    for (let j = 0; j < gamesList[i].game_player.length; j++) {

      let listItemPlayerUsername = document.createElement("li");
      listItemPlayerUsername.innerHTML = "Username: " + gamesList[i].game_player[j].player[0].username;

      let listItemGamePlayerId = document.createElement("li");
      listItemGamePlayerId.innerHTML = "PlayerID: " + gamesList[i].game_player[j].player[0].playerID;

      list.appendChild(listItemPlayerUsername);
      list.appendChild(listItemGamePlayerId);
    }

    let joinButton = document.createElement("button");
    let buttonDiv = document.createElement("div");
    joinButton.innerHTML = "Join Game";
    joinButton.appendChild(buttonDiv);
    list.appendChild(joinButton);
  }
}