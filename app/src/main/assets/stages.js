/*
    Font size buttons and full size image code

    1)
        This script adds functionality to show images full screen when tapped on.
        Images are rotated if that makes them bigger - this asssumes the app is
        locked to portrait mode.
        To dismiss images tap again.

        To use the script simply add the following to the end of the body
            <script src="stages.js"></script>
        and add the required styles from the end of this file to your style sheet.

        Images that have class noclick will be excluded as will images with any icon class

    2)
        The script also adds font size up / down buttons and associated code to
        remember this between loads of pages and across all pages using the script

    Script version 2.0 - Mark Dunlop 16/09/20
*/

var overlay, overlayimg, fontUpButton, fontDownButton;

function debug(...str){
    //console.log(str.join(" - "));
}

function showimg(src){
    debug();

    // Setup
    overlayimg.src = src;
    overlay.style.display="block";
    overlayimg.style.marginLeft=0;
    overlayimg.style.marginTop=0;
    overlayimg.classList.remove("rotatedImage");

    let divWidth = overlay.clientWidth;
    let divHeight = overlay.clientHeight;
    debug("Div size", divWidth, divHeight);
    let imgWidth = overlayimg.naturalWidth;
    let imgHeight = overlayimg.naturalHeight;
    let aspect = imgWidth/imgHeight;
    let rotated = imgWidth > imgHeight;
    debug("Natural Size and aspect", imgWidth, imgHeight, aspect);

    overlayimg.style.width = imgWidth;
    overlayimg.style.height = imgHeight;

    //calculate and set image size to fit
    if (rotated){
        overlayimg.classList.add("rotatedImage");
        let rotatedWidth = Math.min ( divHeight, divWidth * aspect ) *0.95;
        let rotatedHeight = rotatedWidth / aspect;
        debug("Rotated size", rotatedWidth, rotatedHeight);
        overlayimg.style.width = rotatedWidth+"px";
        overlayimg.style.height = rotatedHeight+"px";
       }
    else {
        let targetHeight = Math.min( divHeight, divWidth / aspect)
        overlayimg.style.height = targetHeight+"px";
        overlayimg.style.width = (targetHeight * aspect)+"px";
        }

    //calculate and set margins to centre new image
    imgWidth = overlayimg.clientWidth;
    imgHeight = overlayimg.clientHeight;
    if (rotated){
        overlayimg.style.marginLeft =  (divWidth-imgHeight)/2+"px" ;
        overlayimg.style.marginTop = (divHeight-imgWidth)/2+"px" ;
    } else {
        overlayimg.style.marginLeft =  (divWidth-imgWidth)/2+"px" ;
        overlayimg.style.marginTop = (divHeight-imgHeight)/2+"px" ;
    }

    //add close callbacks
    overlay.addEventListener('click', function() { overlay.style.display = "none"; });
    overlayimg.addEventListener('click', function() { overlay.style.display = "none"; });
}

function fontUp(){
    let style = window.getComputedStyle(document.body);
    let size = parseFloat(style.fontSize,10) * 1.1;
    debug("Font Up", size);
    document.getElementsByTagName( "html" )[0].style.fontSize = size  + "px";
    document.body.style.fontSize = size  + "px";

    if ( localStorage ){
        localStorage.fontSizeLastUsed = size  + "px";
    }
}
function fontDown(){
    let style = window.getComputedStyle(document.body);
    let size = Math.max(9,parseFloat(style.fontSize,10) / 1.1);
    debug("Font Down", size);
    document.getElementsByTagName( "html" )[0].style.fontSize = size  + "px";
    document.body.style.fontSize = size  + "px";

    if ( localStorage ){
        localStorage.fontSizeLastUsed = size  + "px";
    }
}


function init(){
    // --- font buttons ---
    //Add div for font buttons
    let buttonDiv = document.createElement('div');
    buttonDiv.classList.add('fontbuttonsdiv');
    document.body.appendChild(buttonDiv);

    //Add font buttons
    fontUpButton = document.createElement("button");
    fontUpButton.classList.add('fontbutton');
    fontUpButton.innerHTML = "A⇧";
    fontUpButton.addEventListener("click",fontUp);
    buttonDiv.appendChild(fontUpButton);

    fontDownButton = document.createElement("button");
    fontDownButton.classList.add('fontbutton');
    fontDownButton.innerHTML = "A⇩";
    fontDownButton.addEventListener("click",fontDown);
    buttonDiv.appendChild(fontDownButton);

    if (localStorage) {
        debug("fontsizelastused", localStorage.fontSizeLastUsed);
        if (localStorage.fontSizeLastUsed){
           document.getElementsByTagName( "html" )[0].style.fontSize = localStorage.fontSizeLastUsed;
           document.body.style.fontSize = localStorage.fontSizeLastUsed;
        }
    } else {
        debug("No local storage")
    };

    // --- image overlay ---
    //Add the overlay DIV for fullscreen images
    overlay = document.createElement('div');
    overlay.classList.add('imgoverlaydiv');
    document.body.appendChild(overlay);

    //Add the image to the fullscreen div
    overlayimg = document.createElement("img");
    overlayimg.setAttribute("src", "");
    overlayimg.setAttribute("height", "100");
    overlayimg.setAttribute("width", "100");
    overlayimg.classList.add('imgoverlayimg');
    overlay.appendChild(overlayimg);

    //Inject the onClick listeners
    let imgs = document.getElementsByTagName("img");
    for (let i = 0; i < imgs.length; i++) {
        if (!imgs[i].className.includes("noclick") && !imgs[i].className.includes("icon")){
            imgs[i].addEventListener("click",function(e){
                showimg(imgs[i].src);
            });
        }
    }


}

window.addEventListener('load', init);


/*
Required styles

.fontbuttonsdiv{
    position: fixed;
    bottom: 2vmin;
    right: 2vmin;
    width: 10vmin;
    height: 20vmin;
    background-color: #3335;
    margin: 0;
    padding: 0;
}

.fontbutton{
    background-color: #3335;
    color: #fff5;
    margin: 0;
    padding: 0;
    border: none;
    font-size: 7vmin;
    padding: 1vmin;
}

.fontbutton:focus  {
    outline: none;
    border: none;
}

.imgoverlaydiv {
    display:none;position: fixed;top: 0;left: 0;width: 100%;height: 100%;background-color: #333D;margin: 0;padding: 0;
}
.imgoverlayimg {
    width: 100;height: 100;padding: 0;margin: 0;transform-origin: top left;
}
.rotatedImage {
    transform: rotate(90deg) translateY(-100%);
}

*/