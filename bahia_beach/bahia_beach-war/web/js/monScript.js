var url = "ws://PLTZ851:8080/bahia_beach-war/cuisine";
var socket = new WebSocket(url);
window.onload = init;

function init() {
    var elt = document.getElementById("commande");
    var Commande = {
        action: "add",
        id: elt.innerText || elt.textContent
    };
    window.alert(Commande);
    socket.send(JSON.stringify(Commande));
}