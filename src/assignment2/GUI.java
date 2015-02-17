package assignment2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame{
		
	private String [] scoreArray;
	private String [] gridArray;
	String [] orangeArray = new String[111];

	JPanel letterPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel scorePanel = new JPanel();
	JPanel outFinishPanel = new JPanel();
	JPanel finishPanel = new JPanel();
	JPanel finishScorePanel = new JPanel();
	
	public GUI(String [] scoreArray, String [] gridArray) {
		super("Battleship");
		this.scoreArray = scoreArray;
		this.gridArray = gridArray;
		setSize(600,400);
		setLocation(400,200);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		letterPanel.setLayout(new GridLayout(11,1));
		letterPanel.add(new JLabel(" A      "));
		letterPanel.add(new JLabel(" B "));
		letterPanel.add(new JLabel(" C "));
		letterPanel.add(new JLabel(" D "));
		letterPanel.add(new JLabel(" E "));
		letterPanel.add(new JLabel(" F "));
		letterPanel.add(new JLabel(" G "));
		letterPanel.add(new JLabel(" H "));
		letterPanel.add(new JLabel(" I "));
		letterPanel.add(new JLabel(" J "));
		letterPanel.add(new JLabel("   "));
		
		gamePanel.setLayout(new GridLayout(11,10));
		//gamePanel.setLayout(new GridLayout(10,10));
		
		//create a list of "?", and we will need to access the list later
		for (int i=0; i<101; i++) {
			orangeArray[i] = "?";
		}
		int num = 1;
		for (int i=101; i<111; i++) {
			orangeArray[i]  = ""+num;
			num++;
		}
		
		//putting the "?" onto gamePanel
		for (int i=1; i<111; i++) {
			gamePanel.add(new JLabel(orangeArray[i]));
		}

		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
		JLabel titleLabel = new JLabel("Highscores: ");
		scorePanel.add(titleLabel);
		for (int i=0; i<10; i++) {
			JLabel label = new JLabel (this.scoreArray[i]);
			scorePanel.add(label);
		}
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(letterPanel, BorderLayout.WEST);
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		add(mainPanel);
		add(scorePanel, BorderLayout.EAST);
	
		setVisible(true);
	}
	public void updateGUI(int num, String str) {
		orangeArray[num] = str;
		gamePanel.removeAll();
		for (int i=1; i<111; i++) {
			gamePanel.add(new JLabel(orangeArray[i]));
		}
		validate();
		repaint();
	}
	
	public void finishGUI(int turn, String name, String file) {
		for(int i=0; i<10; i++) {
			if(scoreArray[i].length()>3) {
				int score = Integer.parseInt(scoreArray[i].substring(scoreArray[i].length()-2));
				if(score >= turn) {
					
					int rank = Integer.parseInt(scoreArray[i].substring(0,1));
					
					String temp = scoreArray[i];
					String sub = ". "+name +" - "+turn;
					String water = rank+sub;
					scoreArray[i] = water;
					
					for(int z=0; z<10-i-1; z++) {
						sub = temp.substring(1);
						temp = scoreArray[i+z+1];
						water = (rank+z+1)+sub;
						scoreArray[i+z+1] = water;
					}
					break;
				}
			}
		}
		//update the board
		getContentPane().removeAll();
		finishPanel.setLayout(new BoxLayout(finishPanel,BoxLayout.Y_AXIS));
		outFinishPanel.setLayout(new BoxLayout(outFinishPanel, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Thanks for playing, "+name+"!");

		JLabel highscore = new JLabel("Highscores: ");
		finishPanel.add(title);
		finishPanel.add(highscore);
		
		for (int i=0; i<10; i++) {
			JLabel label = new JLabel (this.scoreArray[i]);
			finishPanel.add(label);
		}
		/*for (int i=0; i<10; i++) {
			finishPanel.add(new JLabel(" "));
		}*/
		finishScorePanel.add(new JLabel("Your score: "+turn));
		
		
		outFinishPanel.add(finishPanel);
		outFinishPanel.add(Box.createGlue());
		outFinishPanel.add(finishScorePanel);
		
		add(outFinishPanel);
		getContentPane().validate();
		getContentPane().repaint();
		//rewrite the score board at input file
		try{
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Highscores:");
			for (int i=0; i<10; i++){
				pw.println(scoreArray[i]);
			}
			for (int i=0; i<10; i++){
				pw.println(gridArray[i]);
			}
			pw.flush();
			pw.close();
			fw.close();
		}catch(IOException ioe) {
			System.out.println("IOException: "+ioe.getMessage());
		}
	}
}
