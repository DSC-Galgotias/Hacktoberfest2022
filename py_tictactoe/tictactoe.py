import os
import time

# tic tac toe between two players
board = [" ", " ", " ", " ", " ", " ", " ", " ", " ", " "]
turn = 1


Win = 1
Draw = -1
Running = 0
Stop = 1
Game = 0
Mark = "X"
try:
    
    
                
    def forboard():
        print(f" {board[1]} | {board[2]} | {board[3]} ")
        print(f"___|___|___")
        print(f" {board[4]} | {board[5]} | {board[6]} ")
        print(f"___|___|___")
        print(f" {board[7]} | {board[8]} | {board[9]} ")
        print(f"   |   |   ")


    
    def position(x):
        if board[x] == " ":
            
            return 1
            
        else:
        
            print(" Invalid, a mark already exists in that spot")
            time.sleep(1)


   
    def CheckWin():
        global Game
        
        if board[1] == board[2] and board[2] == board[3] and board[1] != " ":
            Game = Win
        elif board[4] == board[5] and board[5] == board[6] and board[4] != " ":
            Game = Win
        elif board[7] == board[8] and board[8] == board[9] and board[7] != " ":
            Game = Win

        elif board[1] == board[4] and board[4] == board[7] and board[1] != " ":
            Game = Win
        elif board[2] == board[5] and board[5] == board[8] and board[2] != " ":
            Game = Win
        elif board[3] == board[6] and board[6] == board[9] and board[3] != " ":
            Game = Win

        elif board[1] == board[5] and board[5] == board[9] and board[5] != " ":
            Game = Win
        elif board[3] == board[5] and board[5] == board[7] and board[5] != " ":
            Game = Win
    
        elif (
            board[1] != " "
            and board[2] != " "
            and board[3] != " "
            and board[4] != " "
            and board[5] != " "
            and board[6] != " "
            and board[7] != " "
            and board[8] != " "
            and board[9] != " "
        ):
            Game = Draw
        else:
            Game = Running




    while Game == Running:
        os.system("cls")
        print("Player 1 [X] <--> Player 2 [O]",end="\n\n\n")
        
        
        
        forboard()
        if turn % 2 != 0:
            print("\nPlayer 1's chance:")
            Mark = "X"
        else:
            print("Player 2's chance:")
            Mark = "O"
        choice = int(input("Enter between (1-9) blocks where you want to mark : "))
        if position(choice):
            board[choice] = Mark
            turn =turn+ 1
            CheckWin()



        os.system("cls")
        forboard()
        if Game == Draw:
            print("Game Draw")
        elif Game == Win:
            turn =turn- 1
            if turn % 2 != 0:
                print("Player 1 Won")
            else:
                print("Player 2 Won")
            

except ValueError:
    print("error in value")
    
    
