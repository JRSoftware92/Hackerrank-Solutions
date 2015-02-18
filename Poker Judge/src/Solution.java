import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	public enum HandValue{
		High(0), Pair(20), Two_Pair(40), Three_Kind(80), Straight(160), Flush(320), Full_House(640), 
		Four_Kind(6400), Straight_Flush(12800), Royal_Flush(99999);
		
		private final int baseValue;
		
		private HandValue(int value){
			baseValue = value;
		}
	}
	
	public enum Suit{
		Heart, Spade, Club, Diamond;
		
		public static Suit getValue(char c){
			switch(c){
			case 'H': return Heart;
			case 'S': return Spade;
			case 'C': return Club;
			case 'D': return Diamond;
			default: return Heart;
			}
		}
	}
	
	public enum CardValue{
		Two(0, '2'), Three(1, '3'), Four(2, '4'), Five(3, '5'), Six(4, '6'), Seven(5, '7'), 
		Eight(6, '8'), Nine(7, '9'), Ten(8, 'T'), 
		Jack(9, 'J'), Queen(10, 'Q'), King(11, 'K'), Ace(12, 'A');
		
		public final int priority;
		public final char character;
		
		private CardValue(int i, char c){
			this.priority = i;
			this.character = c;
		}
		
		public static CardValue getValue(char c){
			CardValue[] values = values();
			for(CardValue value : values){
				if(value.character == c){
					return value;
				}
			}
			return Two;
		}
	}
	
	static class Card{
		public final Suit suit;
		public final CardValue value;
		
		public Card(String card){
			suit = Suit.getValue(card.charAt(1));
			value = CardValue.getValue(card.charAt(0));
		}
	}
	
	static class Hand{
		public final Card[] cards;
		
		public Hand(Card[] arr){
			cards = arr;
		}
		
		public int getCardCount(CardValue value){
			int count = 0;
			for(Card card : cards){
				if(card.value == value){
					count++;
				}
			}
			return count;
		}
		
		public CardValue getHighCardValue(){
			Card max = null;
			
			for(Card card : cards){
				if(max == null || card.value.priority > max.value.priority){
					max = card;
				}
			}
			
			return max.value;
		}
		
		public boolean getFlush(){
			boolean flag = true;
			Suit suit = Suit.Heart;
			for(int i = 0; i < cards.length; i++){
				if(i == 0){
					suit = cards[i].suit;
				}
				else{
					if(suit != cards[i].suit){
						flag = false;
						break;
					}
				}
			}
			
			return flag;
		}
		
		public boolean getStraight(){
			PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
			boolean flag = true;
			int last = 0;
			for(Card card : cards){
				queue.add(card.value.priority);
			}
			last = queue.poll();
			while(!queue.isEmpty()){
				if(Math.abs(last - queue.poll()) < 1){
					flag = false;
					break;
				}
			}
			
			return flag;
		}
		
		public HandValue getHighHandValue(){
			HandValue value = HandValue.High;
			
			boolean straight = getStraight();
			boolean flush = getFlush();
			PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
			CardValue highCard = getHighCardValue();
			HashSet<Integer> checkedSet = new HashSet<Integer>();
			
			for(Card card : cards){
				if(!checkedSet.contains(card.value.priority)){
					checkedSet.add(card.value.priority);
					queue.add(getCardCount(card.value));
				}
			}
			Stack<Integer> stack = new Stack<Integer>();
			while(!queue.isEmpty()){
				stack.push(queue.poll());
			}
			
			int maxParity = stack.pop();
			int minParity = stack.pop();
			
			//Royal Flush
			if(straight && flush){
				if(highCard == CardValue.Ace){
					value = HandValue.Royal_Flush;
				}
				else{
					value = HandValue.Straight_Flush;
				}
			}
			else if(maxParity == 4){
				value = HandValue.Four_Kind;
			}
			else if(maxParity == 3 && minParity == 2){
				value = HandValue.Full_House;
			}
			else if(flush){
				value = HandValue.Flush;
			}
			else if(straight){
				value = HandValue.Straight;
			}
			else if(maxParity == 3){
				value = HandValue.Three_Kind;
			}
			else if(maxParity == 2){
				value = HandValue.Pair;
			}
			
			return value;
		}
		
		public int getValue(){
			CardValue high = CardValue.Two;
			HandValue value = getHighHandValue();
			if(value == HandValue.Full_House){
				for(Card card : cards){
					if(getCardCount(card.value) == 3){
						high = card.value;
						break;
					}
				}
			}
			else{
				high = getHighCardValue();
			}
			
			return value.baseValue + high.priority;
		}
	}
	
	static int getWinner(Hand playerOne, Hand playerTwo){
		int one = playerOne.getValue();
		int two = playerTwo.getValue();
		
		return two > one ? 2 : 1;
	}
	
	static void parseAndJudge(String line){
		String[] split = line.split(" ");
		Card[] one = new Card[5], two = new Card[5];
		for(int j = 0; j < split.length; j++){
			if(j < 5){
				one[j] = new Card(split[j]);
			}
			else{
				two[j - 5] = new Card(split[j]);
			}
		}
		getWinner(new Hand(one), new Hand(two));
		System.out.println("Player " + getWinner(new Hand(one), new Hand(two)));
	}
	
	public static void main(String[] args) {
        try {
			BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
			int t = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < t; i ++){
				parseAndJudge(br.readLine());
			}
		} 
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
/*
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	int t = Integer.parseInt(in.nextLine());
    	for(int i = 0; i < t; i++){
    		parseAndJudge(in.nextLine());
    	}
    }
*/
}