import string
import secrets
import time


def generate_pass(length):
    password = "".join(
        secrets.choice(string.ascii_letters + string.digits + string.punctuation)
        for x in range(length)
    )
    return password


if __name__ == "__main__":
    print(
        "\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx WELCOME TO PASSWORD GENERATOR xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
    )
    print("\t\t\t\t\t\t\t\t\t\t\t  Made by : Biswajit Mishra")
    print("Note : Press 'q' to exit anytime\n")
    while True:
        length = input("Enter the length of the password you want : ")
        if length.lower() == "q":
            break
        try:
            length = int(length)
            print("\nYour password generation is on the way\nStay Tuned", end="")
            for i in range(10):
                print(".", end="")
                time.sleep(0.2)
            print("Done !!")
            print(
                f"Your password of {length} characters is :  {generate_pass(length)}\n"
            )
        except:
            print("Please enter a valid value !!")

"""
Working of generate_pass function :-

1. First of all the variable "password" is list type and  {"".join} is the function used for joining lists
2. {string.ascii_letters + string.digits + string.punctuation} will generate a list of values which will have all ASCII letters both lower and upper case 
            
            string.ascii_letters --> abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
            string.digits --> 0123456789
            string.punctuation --> !"#$%&'()*+, -./:;<=>?@[\]^_`{|}~
            
            Hence,
                string.ascii_letters + string.digits +string.punctuation = abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!"#$%&'()*+, -./:;<=>?@[\]^_`{|}~
3. Doing secrets.choice(string.ascii_letters + string.digits) will take a random character from the above aplhanumerical list 
4. for x in range(length) --> will perform the above work {length} times 
5. we will get a list like ["4","j","X","z","o","r","3","V","h","d"] but becasue 
   we have done .join that list will be converted into a string and get stored in the variable 'password'
"""
