console.log("we are connected");

fetch("http://localhost:8080/api/games", {
    method: "GET"
  })
  .then(function (response) {
    console.log(response);
    return response.json();  
  })
  .then(function (response) {
       let listInformation = response;
       createList(listInformation);       
  })

  .catch(function (error) {
    console.log(error, "<-- error!");
  });


//   create one list for each game object in the json

 function createList (listInformation) {
    let list = document.getElementById("listOfGames");
    list.innerHTML = "";

    console.log(listInformation);
    console.log(listInformation[0].game_id);
    console.log(listInformation[0].creation_date);
    console.log(listInformation[0].game_player[0].game_player_id);
    console.log(listInformation[0].game_player[0].player[0].playerFirstName);
    console.log(listInformation[0].game_player[0].player[0].playerLastName);
    console.log(listInformation[0].game_player[0].player[0].playerEmail);
    console.log(listInformation[0].game_player[0].player[0].username);

    // let list1 = listInformation.map();
    // console.log(list1);

    for (let i = 0; i < listInformation.length; i++) {
    let listGame1 = document.createElement("li");
    listGame1.innerHTML(listInformation[i]);
    
 }
    
 }
