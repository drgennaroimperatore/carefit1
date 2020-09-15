/*
    This script adds functionality to show images full screen when tapped on.
    Images are rotated if that makes them bigger - this asssumes the app is
    locked to portrait mode.
    To dismiss images tap again.

    To use the script simply add the following to the end of the body
        <script src="fullscreenimage.js"></script>

    Images that have class noclick will be excluded as will images with any icon class

    Script version 1.2 - Mark Dunlop 15/09/20
*/

var overlay, overlayimg;

function showimg(src){
    // Setup
    overlayimg.src = src;
    overlay.style.display="block";
    overlayimg.style.marginLeft=0;
    overlayimg.style.marginTop=0;
    overlayimg.classList.remove("rotatedImage");

    let divWidth = overlay.clientWidth;
    let divHeight = overlay.clientHeight;
    let imgWidth = overlayimg.naturalWidth;
    let imgHeight = overlayimg.naturalHeight;
    let aspect = imgWidth/imgHeight;
    let rotated = imgWidth > imgHeight;

    overlayimg.style.width = imgWidth;
    overlayimg.style.height = imgHeight;

    //calculate and set image size to fit
    if (rotated){
        overlayimg.className  = "rotatedImage";
        let rotatedWidth = Math.min ( divHeight, divWidth * aspect ) *0.95;
        let rotatedHeight = rotatedWidth / aspect;
        overlayimg.style.width = rotatedWidth;
        overlayimg.style.height = rotatedHeight;
       }
    else {
        let targetHeight = Math.min( divHeight, divWidth / aspect)
        overlayimg.style.height = targetHeight;
        overlayimg.style.width = targetHeight * aspect;
        }

    //calculate and set margins to centre new image
    imgWidth = overlayimg.clientWidth;
    imgHeight = overlayimg.clientHeight;
    if (rotated){
        overlayimg.style.marginLeft =  (divWidth-imgHeight)/2 ;
        overlayimg.style.marginTop = (divHeight-imgWidth)/2 ;
    } else {
        overlayimg.style.marginLeft =  (divWidth-imgWidth)/2 ;
        overlayimg.style.marginTop = (divHeight-imgHeight)/2 ;
    }

    //add close callbacks
    overlay.addEventListener('click', function() { overlay.style.display = "none"; });
    overlayimg.addEventListener('click', function() { overlay.style.display = "none"; });
    }

function init(){
    //Add the overlay DIV for fullscreen images
    overlay = document.createElement('div');
    overlay.style.cssText = 'display:none;position: fixed;top: 0;left: 0;width: 100%;height: 100%;background-color: #333B;margin: 0;padding: 0;';
    document.body.appendChild(overlay);

    //Add the image to the fullscreen div
    overlayimg = document.createElement("img");
    overlayimg.setAttribute("src", "");
    overlayimg.setAttribute("height", "100");
    overlayimg.setAttribute("width", "100");
    overlayimg.style.cssText = 'width: 100;height: 100;padding: 0;margin: 0;transform-origin: top left;';
    overlay.appendChild(overlayimg);

    //Add required style for rotated image
    let style = document.createElement('style');
    style.type = 'text/css';
    style.innerHTML = '.rotatedImage { transform: rotate(90deg) translateY(-100%); }';
    document.getElementsByTagName('head')[0].appendChild(style);

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
