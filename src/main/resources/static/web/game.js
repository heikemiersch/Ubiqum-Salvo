
function createGridOne() {
    let grid = document.getElementById("grid-one");    
     let gridOne = "";
 
     let rows = 10;
     let cols = 10;
     // let rows = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
     // let cols = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];
 
     for (r = 0; r < rows; r++) {
         gridOne += "<tr>";
             for (c = 1; c <= cols; c++) {
                 gridOne += "<td>"; 
                 gridOne += "</td>";}          
         gridOne += "</tr>";
     }
     
     grid.innerHTML = gridOne;
     
     console.log(gridOne);
 }
 createGridOne();