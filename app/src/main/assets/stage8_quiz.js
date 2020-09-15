
function init(){
    let revealButton = document.getElementById("reveal");
    let answerText = document.getElementById("answer");
    revealButton.addEventListener("click", () => {
        revealButton.style.visibility = "hidden";
        answerText.style.visibility = "visible";
    });
}

window.addEventListener("load", init);