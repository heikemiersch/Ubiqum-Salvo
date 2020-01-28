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
    let listItemUsername = document.createElement("li");
    // listItemUsername = "Username: " + gamesList[i].game_player[j].player[0].username;

    list.appendChild(listItemGameId);
    list.appendChild(listItemCreationDate);
    // list.appendChild(listItemUsername);

    for (let j = 0; j < gamesList[i].game_player.length; j++) {

      let listItemPlayerUsername = document.createElement("li");
      listItemPlayerUsername.innerHTML = "Username: " + gamesList[i].game_player[j].player[0].username;

      list.appendChild(listItemPlayerUsername);

    }
  }
}