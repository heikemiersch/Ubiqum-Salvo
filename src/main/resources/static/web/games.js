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

 function createList (gamesList) {
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

      let listItemPlayerFirstName = document.createElement("li");
      listItemPlayerFirstName.innerHTML = "First Name: " + gamesList[i].game_player[j].player[0].playerFirstName;
      let listItemPlayerLastName = document.createElement("li");
      listItemPlayerLastName.innerHTML = "Last Name: " + gamesList[i].game_player[j].player[0].playerLastName;
      let listItemPlayerEmail = document.createElement("li");
      listItemPlayerEmail.innerHTML = "Email: " + gamesList[i].game_player[j].player[0].playerEmail;

    // console.log(gamesList);
    // console.log(gamesList[0].game_id);
    // console.log(gamesList[0].creation_date);
    // console.log(gamesList[0].game_player[0].game_player_id);
    // console.log(gamesList[0].game_player[0].player[0].playerFirstName);
    // console.log(gamesList[0].game_player[0].player[0].playerLastName);
    // console.log(gamesList[0].game_player[0].player[0].playerEmail);
    // console.log(gamesList[0].game_player[0].player[0].username);

    // console.log("got it");

    list.appendChild(listItemPlayerFirstName);
    list.appendChild(listItemPlayerLastName);
    list.appendChild(listItemPlayerEmail);
    }

    }
  
  }

    // so all these things should be list item in the ordered list in the html

    // let list1 = listInformation.map();
    // console.log(list1);

  //     for (let i = 0; i < listInformation.length; i++) {
  //     let listGame1 = document.createElement("li");
  //     listGame1.innerHTML(listInformation[i]);
    
  //  }
 
  
