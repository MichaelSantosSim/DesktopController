var url = window.location.href;

if(url !== "http://www.aovivobrasil.com/tvamigos2/"){

	setSmallPlayer();

	setEventListener();

	removeTables();

}


function setFullScreenPlayer(){
	var jwPlayer = document.getElementsByTagName("object")[0];
	jwPlayer.className = "fullScreenPlayer";
	try{
		var button = document.getElementById("reloadButton"); 
		button.parentNode.removeChild(button);
		toggleFullscreen();
	}catch(e){

	}
}


function setSmallPlayer(){
	var jwPlayer = document.getElementsByTagName("object")[0];
	jwPlayer.className = "smallPlayer";
	setReloadButton();
}

function setReloadButton(){
	var body = document.getElementsByTagName("BODY")[0];

	var button = document.createElement("BUTTON");
	button.setAttribute("id", "reloadButton");

	button.addEventListener("click", function(){
	    location.reload();
	});

	button.innerHTML  = "Refresh";
	button.className = "myButton";

	body.className += " customBody";

	body.appendChild(button);
}


function setEventListener(){
	var jwPlayer = document.getElementsByName("flashvars")[0];


	chrome.runtime.onMessage.addListener(function (request, sender, sendResponse) {

		if (request.action == "fullwindow") {
			setFullScreenPlayer();
		}else if(request.action == "reloadbutton"){
			 setSmallPlayer();
		}else if(request.action == "reload"){
			 //location.reload();
			 alert("reloading");
		}else{
			console.log("cound not execute action " + request.action);
		}
	});
}

function removeTables(){
	var tables  = document.getElementsByTagName("table");
	while (tables[0]) tables[0].parentNode.removeChild(tables[0]);
}


function setMouseListener(){
	var x = null;
	var y = null;

	document.addEventListener('mousemove', onMouseUpdate, false);
	document.addEventListener('mouseenter', onMouseUpdate, false);

	function onMouseUpdate(e) {
	    x = e.pageX;
	    y = e.pageY;

	    console.log("X: " + x + " - Y: " + y);
	}

	function getMouseX() {
	    return x;
	}

	function getMouseY() {
	    return y;
	}
}

function toggleFullscreen(elem) {
  elem = elem || document.documentElement;
  if (!document.fullscreenElement && !document.mozFullScreenElement &&
    !document.webkitFullscreenElement && !document.msFullscreenElement) {
    if (elem.requestFullscreen) {
      elem.requestFullscreen();
    } else if (elem.msRequestFullscreen) {
      elem.msRequestFullscreen();
    } else if (elem.mozRequestFullScreen) {
      elem.mozRequestFullScreen();
    } else if (elem.webkitRequestFullscreen) {
      elem.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
    }
  } else {
    if (document.exitFullscreen) {
      document.exitFullscreen();
    } else if (document.msExitFullscreen) {
      document.msExitFullscreen();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
    } else if (document.webkitExitFullscreen) {
      document.webkitExitFullscreen();
    }
  }
}




/*	
	document.addEventListener('mouseup', function (mousePos) {
	    if (mousePos.button == 2) {
	        var p = {clientX: mousePos.clientX, clientY: mousePos.clientY};
	        var msg = {text: 'example', point: p, from: 'mouseup'};
	        chrome.runtime.sendMessage(msg, function(response) {});
	    }
	});
*/