var url = "ws://PLTZ851:8080/bahia_beach-war/cuisine";
var socket = new WebSocket(url);
socket.onmessage = function (evt) {
    onMessage(evt)
};

function onMessage(event) {
    var plat = JSON.parse(event.data);
    if (plat.action === "add") {
        printPlatElement(plat);
    }

    if (plat.action === "remove") {
        document.getElementById(device.id).remove();
        //device.parentNode.removeChild(device);
    }

    if (plat.action === "toggle") {
        var node = document.getElementById(plat.id);
        var statusText = node.children[2];
        if (plat.status === 1) {
            statusText.innerHTML = "Status: En Commande (<a href=\"#\" OnClick=toggleDevice(" + plat.idProduit + ")>Changer Etat</a>)<br><hr>";
        } else if (plat.status === 2) {
            statusText.innerHTML = "Status: En Préparation (<a href=\"#\" OnClick=toggleDevice(" + plat.idProduit + ")>Changer Etat</a>)<br><hr>";
            //deviceDiv.setAttribute("class", "device off");
        } else if (plat.status === 3) {
            statusText.innerHTML = "Status: Prêt (<a href=\"#\" OnClick=toggleDevice(" + plat.idProduit + ")>Changer Etat</a>)<br><hr>";
        }
    }
}

function printPlatElement(plat) {
    var content = document.getElementById("content");

    var platDiv = document.createElement("div");
    platDiv.setAttribute("id", plat.id);
    content.appendChild(platDiv);

    var platDate = document.createElement("p");
    platDate.innerHTML = "Produit: " + plat.produit + "<br>";
    platDiv.appendChild(platDate);

    var platType = document.createElement("p");
    platType.innerHTML = "Nom: " + plat.produit + "<br>";
    platDiv.appendChild(platType);

    var platStatus = document.createElement("p");
    if (plat.status === 1) {
        platStatus.innerHTML = "Status: En Commande (<a href=\"#\" OnClick=toggleDevice(" + plat.idProduit + ")>Changer Etat</a>)<br><hr>";
    } else if (plat.status === 2) {
        platStatus.innerHTML = "Status: En Préparation (<a href=\"#\" OnClick=toggleDevice(" + plat.idProduit + ")>Changer Etat</a>)<br><hr>";
        //deviceDiv.setAttribute("class", "device off");
    } else if (plat.status === 3) {
        platStatus.innerHTML = "Status: Prêt (<a href=\"#\" OnClick=toggleDevice(" + plat.idProduit + ")>Changer Etat</a>)<br><hr>";
    }
    platDiv.appendChild(platStatus);

//    var deviceDescription = document.createElement("span");
//    deviceDescription.innerHTML = "<b>Comments:</b> " + device.description;
//    deviceDiv.appendChild(deviceDescription);
//    
//    var removeDevice = document.createElement("span");
//    removeDevice.setAttribute("class", "removeDevice");
//    removeDevice.innerHTML = "<a href=\"#\" OnClick=removeDevice(" + device.id + ")>Remove device</a>";
//    deviceDiv.appendChild(removeDevice);
}

function toggleDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "toggle",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}



