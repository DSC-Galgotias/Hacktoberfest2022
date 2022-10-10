import random
randNumber = random.randint(1, 100)
userGuess = None
guesses = 0

while(userGuess != randNumber):
    userGuess = int(input("Enter your guess(1-100): "))
    guesses += 1
    if(userGuess==randNumber):
        print("You guessed it right!\n")
    else:
        if(userGuess>randNumber):
            print("You guessed it wrong! Enter a smaller number")
        else:
            print("You guessed it wrong! Enter a larger number")

print(f"You guessed the number in {guesses} guesses")

with open("Number Guessing Game/Score.txt", "r") as f:
    hiscore = int(f.read())

if(guesses<hiscore):
    print("You have just broken the high score!")
    with open("Number Guessing Game/Score.txt", "w") as f:
        f.truncate(0)
        f.write(str(guesses))
        f.close()
