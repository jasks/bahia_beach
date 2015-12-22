var url = "ws://PLTZ893:8080/bahia_beach-war/cuisine";
var socket = new WebSocket(url);
window.onload = setTimeout(init,500);

function init() {
    var elt = document.getElementById("commande");
    var Commande = {
        action: "add",
        id: elt.innerText || elt.textContent
    };
    socket.send(JSON.stringify(Commande));
}