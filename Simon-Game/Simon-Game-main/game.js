
var buttonColours= ["red", "blue", "green", "yellow"];
var gamePattern=[];
var userClickedPattern=[];
var level=0;

$(document).one("keypress",nextSequence);

function nextSequence(){

  var randomNumber=Math.floor((Math.random()*4));
  var randomChosenColour=buttonColours[randomNumber];
  gamePattern.push(randomChosenColour);

  $("#"+randomChosenColour).fadeOut(100).fadeIn(100);

  playSound(randomChosenColour);

  $("h1").text("Level "+ level);
  level++;

  $(".start-button").addClass("hide");

}

function playSound(variable){

  var audio= new Audio("sounds/"+ variable +".mp3");
  audio.play();

}

$(".btn").click(function(){

  var userChosenColour= $(this).attr("id");

  userClickedPattern.push(userChosenColour);

  animatePress(userChosenColour);

  playSound(userChosenColour);

  checkAnswer(userClickedPattern.length - 1);

});

function animatePress(currentColour){
  $("#"+currentColour).addClass("pressed");

  setTimeout(function () {
    $("#"+currentColour).removeClass("pressed");
  }, 100);
}

function checkAnswer(currentLevel){
  if(userClickedPattern[currentLevel]=== gamePattern[currentLevel]){

    if(userClickedPattern.length===gamePattern.length){
      setTimeout(function(){
        nextSequence();
        userClickedPattern=[];
      }, 1000);
    }
  }
  else{
    playSound("wrong");
    $("body").addClass("game-over");
    setTimeout(function () {
      $("body").removeClass("game-over");
    }, 200);
    $("h1").text("Game Over, Press Any Key to Restart");
    startOver();
  }


}


function startOver(){
  level=0;
  gamePattern=[];
  userClickedPattern=[];
  $(document).one("keypress",nextSequence);
  $(".start-button").removeClass("hide");
}

$(".start-button").click(nextSequence);
