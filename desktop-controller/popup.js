function sendRequest(){
	chrome.tabs.query({active: true, currentWindow: true}, function (tabs){
	    chrome.tabs.sendMessage(tabs[0].id, {action: "reload"});
	});
}  	

$(function (){
	$('#fullwindow').click(function(){
		chrome.tabs.query({active: true, currentWindow: true}, function (tabs){
		    chrome.tabs.sendMessage(tabs[0].id, {action: "fullwindow"});
		     window.close();
		});
	});
	$('#reloadbutton').click(function(){
		chrome.tabs.query({active: true, currentWindow: true}, function (tabs){
		    chrome.tabs.sendMessage(tabs[0].id, {action: "reloadbutton"});
		});
	});
	$('#reloadFunc').click(function(){
		sendRequest();
	});

});

var muteSeconds = 0;

setInterval(function(){

	if(muteSeconds>=10){
		muteSeconds=0;
		sendRequest();
	}

	chrome.tabs.getSelected(null, function(tab){
    	if(tab.audible && muteSeconds!=0){
    		muteSeconds=0;
    	}else{
    		muteSeconds+=2;
    	}

    	console.log("MuteSeconds: " + muteSeconds);
	});

}, 2000);