//$(function() {

    // display text in the output area
   function showOutput(text) {
   
   $("#output").text(text);
   
   } 
   
   // load and display JSON sent by server for /players 
   function loadData() {
   
   $.get("/players")
   .done(function(data) {
   
   showOutput(JSON.stringify(data, null, 2));
   
   })
   
   .fail(function( jqXHR, textStatus ) {
   showOutput( "Failed: " + textStatus );
   
   });
   
   } 
   
   // handler for when user clicks add person 
   
   function addPlayer() {
   console.log("test")
   var name = $("#email").val();
   if (name) {
   
   postPlayer(name);
   
   }
   
   } 
   
   // code to post a new player using AJAX
   // on success, reload and display the updated data from the server 
   
   function postPlayer(userName) {
       console.log(userName);
       $.post("/players",JSON.stringify({firstName: userName, lastName: "hiugahre", password: "jiuhgare"}))

//    $.post({
   
//    headers: {
   
//    'Content-Type': 'application/json'
   
//    },
   
//    dataType: "text",
//    url: "http://localhost:8080/players",
//    data: JSON.stringify({ firstName: "hi" })
   
//    })
   
//    .done(function( ) {
   
   //showOutput( "Saved – reloading");
  // loadData();
   
//    })
   
//    .fail(function( jqXHR, textStatus ) {
   
//    showOutput( "Failed: " + textStatus );
   
//    });
   
   } 


   document.getElementById("add_player").addEventListener("click", addPlayer); 
   loadData();
   
//   }
   //);