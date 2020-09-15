/*
    Loads the file 7-spoken-test.txt and plays it when the sayit button is clicked

    v0.1 basic play of file 15/09/20
*/

function getTextAndSpeak(){
    // read text from URL location
    var request = new XMLHttpRequest();
    request.open('GET', '7-spoken.txt', true);
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            var type = request.getResponseHeader('Content-Type');
            if (type.indexOf("text") !== 1) {
                buildPhrases(request.responseText);
                sayPhrases();
                }
            }
        };
}

let phrases = null;
function buildPhrases(text){
    if (!phrases){
        phrases = text.split("\n");
    }
}

var to_speak=null;
var to_speak_paused=false;

function sayPhrases(){
    let txt = "";
    phrases.forEach((phrase)=>{
        phrase = phrase.trim();
        if (phrase.length>0){
            txt += phrase + ". ";
        } else {
            txt += " - . ! , . ! , . ! . ! . ! . ! - . ! , . ! , . ! . - ! . ! . ! . ! , . ! , . ! . ! . ! . - !  . ! , . ! , . ! . ! . ! . ! . ! , . - ! , . ! . ! . ! . ! . ! , . ! , . ! . ! . ! . ! ";
        }
    });
    to_speak  = new SpeechSynthesisUtterance(txt);
    to_speak.rate = 0.5;
    to_speak_paused=false;
    speechSynthesis.speak(to_speak);
}

function sayIt(){
    if (!to_speak){
        getTextAndSpeak();
    } else {
        if (to_speak_paused){
            to_speak_paused=false;
            speechSynthesis.resume();
        } else {
            to_speak_paused=true;
            speechSynthesis.pause();
        }
    }
}

document.getElementById("sayit").addEventListener("click", sayIt);
