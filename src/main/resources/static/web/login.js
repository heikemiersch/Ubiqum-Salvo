// // function login(evt) {
// //     evt.preventDefault();
// //     var form = evt.target.form;
// //     $.post("/api/login", {
// //             userName: form["Heisel"].value,
// //             password: form["luppe"].value
// //             console.log("done")
// //         })
// //         .done(function () {
// //             console.log("done")
// //         })
// //         .fail();
// // }

// // function logout(evt) {
// //     evt.preventDefault();
// //     $.post("/api/logout")
// //         .done()
// //         .fail();
// // }


// var page = new Vue({
//     el: "#page",
//     data: {

//     },
//     methods: {
//         login: function () {
//             fetch("/api/login", {
//                     method: 'POST',
//                     credentials: 'include',
//                     headers: {
//                         'Accept': 'application/json',
//                         'Content-type': 'application/x-www-form-urlencoded'
//                     },
//                     body: `username="Heisel"&password="luppe"`
//                 })
//                 .then(function (response) {
//                     console.log(response);
//                     return response.json();
//                 })
//                 .then(function (response) {

//                 })

//                 .catch(function (error) {
//                     console.log(error, "<-- error!");
//                 });
//         },

//         logout() {}
//     }
// })