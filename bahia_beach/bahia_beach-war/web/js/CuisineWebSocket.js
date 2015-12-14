var socket = new WebSocket("ws://" + document.location.host + document.location.pathname + "cuisine");
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

    if (device.action === "toggle") {
        var node = document.getElementById(device.id);
        var statusText = node.children[2];
        if (device.status === "On") {
            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
        } else if (device.status === "Off") {
            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
        }
    }
}

function printPlatElement(plat) {
    var content = document.getElementById("content");
    
    var platDiv = document.createElement("div");
    platDiv.setAttribute("id", plat.id);
    content.appendChild(platDiv);
    
    var platDate = document.createElement("span");
    platDate.innerHTML = "Date: " + plat.commande.date;
    platDiv.appendChild(platDate);
    
    var platType = document.createElement("span");
    platType.innerHTML = "Nom: " + plat.produit.nomProduit;
    platDiv.appendChild(platType);
    
    var platStatus = document.createElement("span");
    if (plat.etat === 2) {
        platStatus.innerHTML = "Status: En préparation (<a href=\"#\" OnClick=toggleDevice(" + plat.id + ")>Changer Etat</a>)";
    } else if (plat.etat === 3) {
        platStatus.innerHTML = "Status: Prêt (<a href=\"#\" OnClick=toggleDevice(" + plat.id + ")>Changer Etat</a>)";
        //deviceDiv.setAttribute("class", "device off");
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



