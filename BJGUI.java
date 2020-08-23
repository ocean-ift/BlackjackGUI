package Blackjack;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BJGUI{

	public JFrame frame;
	public JLabel label, label2, label3, score, bust, end, compScore, winner;
	public JPanel panel, panel2, panel3, panelStart, scoreP, endP;

	public BJGUI() {
		
		Game game = new Game();
		
		frame = new JFrame();
		
		//label is for the rules, label2 for hand
		label = new JLabel(game.rules());
		label2 = new JLabel(game.displayHand(game.hand));
		label3 = new JLabel(game.displayHand(game.cHand));
		score = new JLabel("Score: " + game.score1);
		bust = new JLabel("Bust! Click stop.");
		
		//buttons
		//draws a card and adds the value of the card to your score
		JButton button = new JButton("Hit!");
		button.addActionListener( new ActionListener() {
			//addCardToHand to draw Card
			public void actionPerformed(ActionEvent e)
		      {
				game.addCardToHand();
				score.setText("Score: " + game.score1);
				label2.setText(game.displayHand(game.hand));
				if (game.score1 > 21) {
					panel2.add(bust);
					panel2.remove(button);
				}
				frame.setVisible(true);
		      }
		});
		//Starts the game, draws two cards and displays them and shows the current score
		JButton start = new JButton("Start");
		start.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(panelStart);
				int x = game.startingHand();
				score.setText("Score: " + game.score1);
				label2.setText(game.displayHand(game.hand));
				frame.add(panel3, BorderLayout.PAGE_END);
				frame.add(panel2, BorderLayout.CENTER);
				if (x > 21) {
					panel2.add(bust);
				}
				frame.setVisible(true);
			}
		});
		//ends the game, displays your score and the computer's score, shows who won
		JButton button2 = new JButton("Stop!");
		button2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				end = new JLabel(game.getScore2());
				game.computer();
				compScore = new JLabel("Computer Score: " + game.score2);
				frame.remove(panel);
				frame.remove(panel2);
				frame.remove(panel3);
				frame.remove(scoreP);
				scoreP.add(label2);
				scoreP.add(compScore);
				label3.setText(game.displayHand(game.cHand));
				scoreP.add(label3);
				int p = game.score1;
				int c = game.score2;
				winner = new JLabel(game.decideWinner(p, c));
				scoreP.add(winner);
				frame.add(scoreP, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
		
		//panel for the rules
		panel = new JPanel();
		//                           (left space, top space, bottom space, right space)
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,20,20));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		
		//panel for the start button
		panelStart = new JPanel();
		panelStart.setBorder(BorderFactory.createEmptyBorder(30,30,20,20));
		panelStart.add(start);
		
		//panel for the hit and stop buttons
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel2.setLayout(new GridLayout(0,1));
		panel2.add(button);
		panel2.add(button2);
		
		//panel for showing the cards in your hand
		panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createEmptyBorder(30,30,20,30));
		panel3.setLayout(new GridLayout(0,1));
		panel3.add(label2);	
		
		//panel for showing the player score, eventually the winner and computer score after you hit stop
		scoreP = new JPanel();
		scoreP.setBorder(BorderFactory.createEmptyBorder(30,30,20,30));
		scoreP.setLayout(new GridLayout(0,1));
		scoreP.add(score);
		
		//sets up the frame
		frame.add(scoreP, BorderLayout.PAGE_START);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(panelStart, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Our GUI");
		frame.pack();
		frame.setVisible(true);
		
	}
}
