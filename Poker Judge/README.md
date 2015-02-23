# Project Euler 54: Poker hands
https://www.hackerrank.com/contests/projecteuler/challenges/euler054

## Problem Statement

This problem is a programming version of Problem 54 from projecteuler.net

In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:

High Card : Highest value card.
One Pair : Two cards of the same value.
Two Pairs : Two different pairs.
Three of a Kind : Three cards of the same value.
Straight : All cards are consecutive values.
Flush : All cards of the same suit.
Full House : Three of a kind and a pair.
Four of a Kind : Four cards of the same value.
Straight Flush : All cards are consecutive values of same suit.
Royal Flush : Ten, Jack, Queen, King, Ace, in same suit.
More on Poker Hands here

The cards are valued in the order:
2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.

Consider the following five hands dealt to two players: 
|**Hand**     | **Player 1**       | **Player 2**       | **Winner**       |
|:--------|:--------------:|:--------------:|:------------:|
|1        | 5H 5C 6S 7S KD | 2C 3S 8S 8D TD | Player 2     |
|         | Pair of Fives  | Pair of Eights |              |
|2        | 5D 8C 9S JS AC | 2C 5C 7D 8S QH | Player 1     |
|         | Ace High       | Queen High     |              |
|3        | 2D 9C AS AH AC | 3D 6D 7D TD QD | Player 2     |
|         | Three Aces     | Diamond Flush  |              |
|4        | 4D 6S 9H QH QC | 3D 6D 7H QD QS | Player 1     |
|         | Pair of Queens | Pair of Queens |              |
|         | Nine High      | Seven High     |              |
|5        | 2H 2D 4C 4D 4S | 3C 3D 3S 9S 9D | Player 1     |
|         | Full House     | Full House     |              |
|         | Three Fours    | Three Threes   |              |

##Input Format 
First line contains T, i.e. number of test cases. Each test case contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.

##Output Format 
Print Player 1 if player 1 is winning the hand. or print Player 2 is player 2 is winning the hand.

##Constraints 
1≤T≤1000

##Sample Input
```
5
5H 5C 6S 7S KD 2C 3S 8S 8D TD
5D 8C 9S JS AC 2C 5C 7D 8S QH
2D 9C AS AH AC 3D 6D 7D TD QD
4D 6S 9H QH QC 3D 6D 7H QD QS
2H 2D 4C 4D 4S 3C 3D 3S 9S 9D
```
##Sample Output
```
Player 2
Player 1
Player 2
Player 1
Player 1
```
