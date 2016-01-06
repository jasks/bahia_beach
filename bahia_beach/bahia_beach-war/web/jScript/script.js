
        // recuperer une instance de XMLHttpRequest ou ActiveXObject
function getXhr() {
    var xhr = null;
    if (window.XMLHttpRequest) { // Firefox et autres
        xhr = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) { // Internet Explorer
        try {
            xhr = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }
    } else {
        alert("Votre navigateur ne prend pas en charge AJAX");
        xhr = false;
    }
    return xhr;
}
function actualiser(){
   // setInterval("lignesCommande()", 5000);
    setInterval("lesCommandes()",5000);
}

function lignesCommande() {
   
    var xhr = getXhr();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = "<strong>"+xhr.responseText+"</strong>";            
            var maDiv = document.getElementById("com");
             alert("hello monde"+maDiv);
            var output= document.getElementById("numCom");
            output.innerHTML = resp;
            
        }else if(xhr.readyState == 4 && xhr.status == 404){
           alert("page non trouvé");
        }
    }
    xhr.open("GET", "Controller?section=serveur&action=lignecommande&numCommande=${cm.getNumero()}&numTable=${cm.getTable().getNum()}", true);
    xhr.send(null);
          
}
function lesCommandes(){
    var xhr = getXhr();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
           
        }else if(xhr.readyState == 4 && xhr.status == 404){
           alert("else");
        }
    }
    xhr.open("GET", "Controller?section=serveur&action=voirCommande", true);
    xhr.send(null);
          
}
