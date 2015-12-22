var url = "ws://PLTZ893:8080/bahia_beach-war/cuisine";
var socket = new WebSocket(url);
socket.onmessage = function (evt) {
    onMessage(evt)
};

function onMessage(event) {
    var plat = JSON.parse(event.data);
    if (plat.action === "add") {
        printPlatElement(plat);
    }

    if (plat.action === "toggle") {
        var node = document.getElementById(plat.id);
        var statusText = node.children[5];
        if (plat.status === 1) {
            statusText.innerHTML = "Status: En Commande";
        } else if (plat.status === 2) {
            statusText.innerHTML = "Status: En Préparation";
        } else if (plat.status === 3) {
            statusText.innerHTML = "Status: Prêt";
        }
    }
}

function printPlatElement(plat) {
    var content = document.getElementById("content");

    var platDiv = document.createElement("div");
    platDiv.setAttribute("id", plat.id);
    content.appendChild(platDiv);

    var platCommande = document.createElement("p");
    platCommande.innerHTML = "Commande: " + plat.commande;
    platDiv.appendChild(platCommande);
    
    var platDate = document.createElement("p");
    platDate.innerHTML = "Date: " + plat.date;
    platDiv.appendChild(platDate);

    var platType = document.createElement("p");
    platType.innerHTML = "Nom: " + plat.produit;
    platDiv.appendChild(platType);

    var platCommentaire = document.createElement("p");
    platCommentaire.innerHTML = "Commentaire: " + plat.commentaire;
    platDiv.appendChild(platCommentaire);
    
    var platCuisson = document.createElement("p");
    if (plat.cuisson === 1) {
        platCuisson.innerHTML = "Cuisson: Bleu";
    } else if (plat.cuisson === 2) {
        platCuisson.innerHTML = "Cuisson: Saignant";
    } else if (plat.cuisson === 3) {
        platCuisson.innerHTML = "Cuisson: A point";
    }else if (plat.cuisson === 3) {
        platCuisson.innerHTML = "Cuisson: Bien Cuit";
    }else if (plat.cuisson === 0) {
        platCuisson.innerHTML = "Cuisson:";
    }
    platDiv.appendChild(platCuisson);

    var platStatus = document.createElement("p");
    if (plat.status === 1) {
        platStatus.innerHTML = "Status: En Commande";
    } else if (plat.status === 2) {
        platStatus.innerHTML = "Status: En Préparation";
    } else if (plat.status === 3) {
        platStatus.innerHTML = "Status: Prêt";
    }
    platDiv.appendChild(platStatus);
    
        
    var platChangement = document.createElement("p");
    platChangement.innerHTML = "<button type=\"button\" class=\"btn btn-secondary\" OnClick=\"toggleDevice("+plat.id+",1); return false;\">En Préparation</button>"+
                               "<button type=\"button\" class=\"btn btn-secondary\" OnClick=\"toggleDevice("+plat.id+",2); return false;\">Prêt</button><hr>";
    platDiv.appendChild(platChangement);
    
}

function toggleDevice(element,element2) {
    var plat = {
        action: "toggle",
        id: element + "",
        c: element2 + ""
    };
    setTimeout(socket.send(JSON.stringify(plat)), 500);
}

