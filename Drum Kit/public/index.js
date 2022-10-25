var number = document.querySelectorAll(".drum").length;
let audio, currentDrum;

function playSound(key) {

    switch (key) {
        case 'w':
            audio = new Audio("sounds/tom-1.mp3");
            audio.play();
            break;
        case 'a':
            audio = new Audio("sounds/tom-2.mp3");
            audio.play();
            break;
        case 's':
            audio = new Audio("sounds/tom-3.mp3");
            audio.play();
            break;
        case 'd':
            audio = new Audio("sounds/tom-4.mp3");
            audio.play();
            break;
        case 'j':
            audio = new Audio("sounds/snare.mp3");
            audio.play();
            break;
        case 'k':
            audio = new Audio("sounds/crash.mp3");
            audio.play();
            break;
        case 'l':
            audio = new Audio("sounds/kick-bass.mp3");
            audio.play();
            break;

        default:
            break;
    }
}

function addAnimation(key){
    document.querySelector(`.${key}`).classList.add("pressed");
    setTimeout(() => {
        document.querySelector(`.${key}`).classList.remove("pressed");
    }, 200);

}

for (let i = 0; i < number; i++) {
    document.querySelectorAll(".drum")[i].addEventListener("click", function () {
        currentDrum = this.innerHTML;
        playSound(currentDrum);
        addAnimation(currentDrum);
    })
}

document.addEventListener("keydown", function (event) {
    playSound(event.key);
    addAnimation(event.key);
})
