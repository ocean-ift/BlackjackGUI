package Blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	public int score1 = 0;
	public int score2 = 0;	
	
	public ArrayList<Cards> hand = new ArrayList<Cards>();
	public ArrayList<Cards> cHand = new ArrayList<Cards>();
	
	public String rules() {
		return "You will start with two cards, and you will get a card everytime you choose to hit. The goal is get as close to 21 as possible without going over. If you go over, you bust. Try to get closer than your opponent.";
	}
	
	//creates first two cards
	public int startingHand() {
		
		for (int i = 1; i < 3; i++) {
			
			Random cardVal = new Random();
			//gens number from 0-12 then adds 1
			int ranIntVal = cardVal.nextInt(13) + 1;
			Random cardType = new Random();
			//gens num from 0-3
			int ranIntType = cardType.nextInt(4);
			Cards card = new Cards(ranIntVal, ranIntType);
			hand.add(card);
			if (i == 2 
					&& card.getCardType().equals(hand.get(0).getCardType()) 
					&& card.getCardVal().equals(hand.get(0).getCardVal())) {
				i--;
				hand.remove(1);
			}
			if (score1 > 21) {
				hand.remove(1);
				i--;
			}
			
			score1 += ranIntVal;
		}
		
		int x = score1;
		return x;
	}
	
	//shows all cards in your hand
	public String displayHand(ArrayList<Cards> hand) {
		String s = "Hand: ";
		for (int i = 0; i < hand.size(); i++) {
			if (i == hand.size() - 1) {
				s += "\n" + hand.get(i).getCardVal() + " of " + hand.get(i).getCardType() + " ";
			}
			else {
				s += "\n" + hand.get(i).getCardVal() + " of " + hand.get(i).getCardType() + ", ";
			}
		}
		
		return s;
	}
	
	//adds a card if you hit
	public void addCardToHand() {
		for (int i = 0; i < 1; i++) {
			Random cardVal = new Random();
			//gens number from 0-12 then adds 1
			int ranIntVal = cardVal.nextInt(13) + 1;
			Random cardType = new Random();
			//gens num from 0-3
			int ranIntType = cardType.nextInt(4);
			Cards card = new Cards(ranIntVal, ranIntType);
			//adds the card to hand then checks if the card is already in your hand
			hand.add(card);
			for (int z = 0; z < hand.size() - 1; z++) {
				//if the card is in your hand, it remove it then draws another card since you can't have two of the same card in a deck
				if (card.getCardType().equals(hand.get(z).getCardType()) 
					&& card.getCardVal().equals(hand.get(z).getCardVal())) {
					hand.remove(hand.size()-1);
					i--;
				}
			}
			score1 += ranIntVal;
		}
	}
	
	/*first gets two cards, then is prompted "Hit or Stop"
	* give card if hit, break if stop
	* if you go over 21, break
	* move to player two
	*/
	
	//conditional statements to decide who won
	public String decideWinner(int score1, int score2) {
		
		String s = "";
		//both under, player wins
		if (score1 > score2 && score1 <= 21 && score2 <= 21) s = "YOU WON!";
		//both under, computer wins
		if (score2 > score1 && score2 <= 21 && score1 <= 21) s = "HA YOU LOSE!";
		//player busts, computer wins
		if (score2 <= 21 && score1 > 21) s = "HA YOU LOSE";
		//player wins, computer busts
		if (score1 <= 21 && score2 > 21) s = "YOU WON!";
		//both bust, or both get same score - tie
		if ((score1 > 21 && score2 > 21) || (score1 == score2)) s = "TIE! YOU BOTH LOSE!";
		
		return s;
		
	}
		
	//computer plays, sets the final score2 which is the computer's score
	public void computer() {
		for (int i = 1; i < 3; i++) {
			Random cardVal = new Random();
			//gens number from 0-12 then adds 1
			int ranIntVal = cardVal.nextInt(13) + 1;
			Random cardType = new Random();
			//gens num from 0-3
			int ranIntType = cardType.nextInt(4);
			Cards card = new Cards(ranIntVal, ranIntType);
			cHand.add(card);
			if (i == 2 
					&& card.getCardType().equals(cHand.get(0).getCardType()) 
					&& card.getCardVal().equals(cHand.get(0).getCardVal())) {
				i--;
				cHand.remove(1);
			}
			if (score2 > 21) {
				cHand.remove(1);
				i--;
			}
			
			score2 += ranIntVal;
		}
		
		for (int i = 0; i > -1; i++) {
			if (score2 < 17) {
				for (int i1 = 0; i1 < 1; i1++) {
					Random cardVal1 = new Random();
					//gens number from 0-12 then adds 1
					int ranIntVal1 = cardVal1.nextInt(13) + 1;
					Random cardType1 = new Random();
					//gens num from 0-3
					int ranIntType1 = cardType1.nextInt(4);
					Cards card1 = new Cards(ranIntVal1, ranIntType1);
					cHand.add(card1);
					for (int z = 0; z < cHand.size() - 1; z++) {
						if (card1.getCardType().equals(cHand.get(z).getCardType()) 
							&& card1.getCardVal().equals(cHand.get(z).getCardVal())) {
							cHand.remove(cHand.size()-1);
							i--;
						}
					}
					score2 += ranIntVal1;
				}
			}
			else {
				break;
			}
			
		}
		
	}
	
	//returns player score
	public String getScore1() {
		return "" + score1;
	}
		
	//returns computer score
	public String getScore2() {
		return "" + score2;
	}
	
	//creates the GUI
	public static void main(String[] args) {
		new BJGUI();
	}
}
