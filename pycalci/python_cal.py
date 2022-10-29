#!/usr/bin/python
# -*- coding: utf-8 -*-


class Calculator:

    @staticmethod
    def add(num1, num2):
        return num1 + num2

    @staticmethod
    def subtract(num1, num2):
        return num1 - num2

    @staticmethod
    def multiply(num1, num2):
        return num1 * num2

    @staticmethod
    def divide(num1, num2):
        if num2 == 0:
            return 'num2 is invalid '
        return num1 / num2


loop = True
while loop:
    print '''Please select operation -
1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit'''

    select = int(input('--> '))
    if select == 5:
        loop = False
        continue
    number_loop = True
    while number_loop:
        number1 = input('Enter first number: ')
        number2 = input('Enter second number:')
        if not number1.isnumeric() or not number2.isnumeric():
            continue
        else:
            number1 = float(number1)
            number2 = float(number2)
            break

    if select == 1:
        print (number1, '+', number2, '=', Calculator.add(number1,
               number2))
    elif select == 2:
        print (number1, '-', number2, '=', Calculator.subtract(number1,
               number2))
    elif select == 3:
        print (number1, '*', number2, '=', Calculator.multiply(number1,
               number2))
    elif select == 4:
        print (number1, '/', number2, '=', Calculator.divide(number1,
               number2))
    else:
        print 'invalid choice'
