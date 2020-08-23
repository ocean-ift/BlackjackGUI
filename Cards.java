package Blackjack;

public class Cards {
	
	private int cardNum;
	private int num;
	
	public Cards(int val, int type) {
		cardNum = val;
		num = type;
	}
	
	public String generateCard() {
		int x = cardNum;
		String s = "";
		
		if (x == 13) s = "King";
		
		if (x == 12) s = "Queen";
		
		if (x == 11) s = "Jack";
		
		for (int i = 2; i <= 10; i++) {
			if (x == i) {
				s = "" + i;
				break;
			}
		}
		
		if (x == 1) s = "Ace";
		
		return s;
	}
	
	public String generateType() {
		String s = "";
		
		if (num == 0) s = "Hearts";
		if (num == 1) s = "Diamonds";
		if (num == 2) s = "Spades";
		if (num == 3) s = "Clubs";
		
		return s;
	}
	
	public String getCardVal() {
		return generateCard(); 
	}
	
	public String getCardType() {
		return generateType();
	}
	
}
