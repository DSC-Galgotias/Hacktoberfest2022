let firstcard = random()
let secondcard = random()
let hasBlackJack = false
let isAlive = false
let cards = []
let sum = 0
let message = ""
let msg = document.getElementById("message");
let sums = document.getElementById("sum")
let cardss = document.getElementById("cards")

function random(){
    return Math.floor(Math.random() * 16);
}

function start(){
    isAlive = true
    let firstCard = random()
    let secondCard = random()
    cards = [firstCard, secondCard]
    sum = firstCard + secondCard
    render()
}

function render(){
cardss.textContent="Cards: "
sums.textContent="Sum: " + sum
for( let i = 0; i<cards.length;i++){
    cardss.textContent+= +cards[i]+"  "
}
if(sum<=20){
    message="Draw a new card 🙂"
}
else if(sum===21){
    message="Congratulations!! You have Won 🥳 "
    hasBlackJack = true
}
else {
    message="You have lost Better Luck Next Time 😥 😢 😭"
    isAlive = false 
}
msg.innerText = message
}

let thirdcard = " "

function newcard(){
if (isAlive === true && hasBlackJack === false) {
thirdcard = Math.floor(Math.random()*11)
cards.push(thirdcard)
sum+=thirdcard
render()   
}
}