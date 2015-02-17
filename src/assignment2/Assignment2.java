package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;


public class Assignment2 {
	
	public static void main (String [] args) {
		//ask for file input
		String file;
		/*if (args.length > 0){
			file = args[0];
		}
		else {
			System.out.println("Please enter an input file: ");
			Scanner scan = new Scanner(System.in);
			file = scan.next();		
		}*/
		file = "testcopy";
		//file input
		String [] scoreArray = new String[10];
		String [] gridArray = new String[10];
		HashMap<Integer, String> masterMap = new HashMap<Integer, String>();
		try {			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();	
			//collect the 10 players score data		
			for (int i=0; i<10; i++) {
				scoreArray[i] = br.readLine();
			}
			//collect the grid data
			for (int i=0; i<10; i++) {
				gridArray[i] = br.readLine();
			}
			br.close();
			fr.close();		
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("FileNotFoundException: " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		}
		GUI board = new GUI(scoreArray, gridArray);
		//parsing the letters as String to 100 elements into an array
		String [] hundredGridArray = new String[100];
		int k =0;
		for (int i=0; i<10; i++) {
			String str = gridArray[i];
			for (int z=0; z<10; z++)
				hundredGridArray[k++] = "" + str.charAt(z);				
		}
		
		//create 4 arrays to store locations of the 4 types of ships
		ArrayList<Integer> al = new ArrayList<Integer>();
		ArrayList<Integer> bl = new ArrayList<Integer>();
		ArrayList<Integer> cl = new ArrayList<Integer>();
		ArrayList<Integer> dl = new ArrayList<Integer>();
		ArrayList<Integer> dl2 = new ArrayList<Integer>();
		
		//putting everything into a map
		for (int i=0; i<100; i++){
			masterMap.put(i+1, hundredGridArray[i]);
			if (hundredGridArray[i].equals("A"))
				al.add(i+1);
			if (hundredGridArray[i].equals("B"))
				bl.add(i+1);
			if (hundredGridArray[i].equals("C"))
				cl.add(i+1);
			if (hundredGridArray[i].equals("D")) {
				if(dl.size() < 2){
					dl.add(i+1);
				}
				else
					dl2.add(i+1);
			}						
		}
		
		//check number of ships and size of ships are valid
		if( (al.size() != 5) || (bl.size() != 4) || (cl.size() != 3) || (dl.size() != 2) || (dl2.size() != 2) ){
			System.out.println("Please load a valid input file (ship size error).");
			return;
		}
		
		//get the locations for 5 ships
		int a1=al.get(0); int a2=al.get(1); int a3=al.get(2); int a4=al.get(3); int a5=al.get(4);
		int b1=bl.get(0); int b2=bl.get(1); int b3=bl.get(2); int b4=bl.get(3); 
		int c1=cl.get(0); int c2=cl.get(1); int c3=cl.get(2);
		int d1=dl.get(0); int d2=dl.get(1); 
		int dd1=dl2.get(0); int dd2=dl2.get(1); 

		//check the placement of the ships are valid
		Integer a1Unit = a1%10;
		Integer b1Unit = b1%10;
		Integer c1Unit = c1%10;
		Integer d1Unit = d1%10;
		Integer dd1Unit = dd1%10;
		
		//check AirCraftCarrier
		if ( !( ((a2-a1==1)&&(a3-a2==1)&&(a4-a3==1)&&(a5-a4==1)&&a1Unit!=0&&a1Unit<7) 
				|| ((a2-a1==10)&&(a3-a2==10)&&(a4-a3==10)&&(a5-a4==10)) )) {
			System.out.println("Please load a valid input file (Aircraft Carrier placement error).");
			return;
		}
		//check Battleship
		if ( !( ((b2-b1==1)&&(b3-b2==1)&&(b4-b3==1)&&b1Unit!=0&&b1Unit<8)   
				||  ( (b2-b1==10)&&(b3-b2==10)&&(b4-b3==10)) )) {
			System.out.println("Please load a valid input file (Battleship placement error).");
			return;
		}
		
		//check Cruiser
		if ( !(   ((c2-c1==1)&&(c3-c2==1)&&c1Unit!=0&&c1Unit<9)
				|| ( (c2-c1==10)&&(c3-c2==10)) )) {
			System.out.println("Please load a valid input file (Cruiser placement error).");
			return;
		}
		
		//check Destroyer1
		if ( !( ( (d2-d1==1)&&d1Unit!=0 )  ||  (d2-d1==10) )) {
			System.out.println("Please load a valid input file (Destroyer1 placement error).");
			return;
		}
		
		//check Destroyer2
		if ( !( ( (dd2-dd1==1)&&dd1Unit!=0 )  ||  (dd2-dd1==10) )) {
			System.out.println("Please load a valid input file (Destroyer2 placement error).");
			return;
		}
		
		//instantiate the ships
		Ship aircraftcarrier = new AircraftCarrier(a1,a2,a3,a4,a5);
		Ship battleship = new Battleship(b1,b2,b3,b4);
		Ship cruiser = new Cruiser(c1,c2,c3);
		Ship destroyer1 = new Destroyer(d1,d2);
		Ship destroyer2 = new Destroyer(dd1,dd2);
			
		boolean gameOver = false;
		int turn = 1;
		while (!gameOver){
			System.out.println("Turn "+turn+" - Please enter a coordinate: ");
			Scanner scan = new Scanner(System.in);
			String input = scan.next();

			//check valid input
			String letter = input.substring(0,1);
			if ( !(letter.equals("A")||letter.equals("B")||letter.equals("C")||letter.equals("D")||letter.equals("E")||
					letter.equals("F")||letter.equals("G")||letter.equals("H")||letter.equals("I")||letter.equals("J")) ) {
				System.out.println("Please enter correct coordinate. letter error");
				continue;
			}
			String number = input.substring(1,input.length());
			if ( !(number.equals("1")||number.equals("2")||number.equals("3")||number.equals("4")||number.equals("5")||
					number.equals("6")||number.equals("7")||number.equals("8")||number.equals("9")||number.equals("10")) ) {
				System.out.println("Please enter correct coordinate. number error");
				continue;
			}
			//convert user input from String to integer
			int part2 = Integer.parseInt(number);
			int part1 = 0;
			int location = 0;
			if (letter.equals("A"))
				part1 = 0;
			if (letter.equals("B"))
				part1 = 10;
			if (letter.equals("C"))
				part1 = 20;
			if (letter.equals("D"))
				part1 = 30;
			if (letter.equals("E"))
				part1 = 40;
			if (letter.equals("F"))
				part1 = 50;
			if (letter.equals("G"))
				part1 = 60;
			if (letter.equals("H"))
				part1 = 70;
			if (letter.equals("I"))
				part1 = 80;
			if (letter.equals("J"))
				part1 = 90;
			location = part1+part2;

			//if the key is already guessed, break out the loop and keep running
			if(!masterMap.containsKey(location))
				continue;
			else {
				String guess = masterMap.get(location);
				if(guess.equals("A")){
					aircraftcarrier.getAttack();
					if(aircraftcarrier.getDestroied())
						System.out.println("You have sunken an Aircraft Carrier!");
					board.updateGUI(location, "A");
				}
				if(guess.equals("B")){
					battleship.getAttack();
					if(battleship.getDestroied())
						System.out.println("You have sunken a Battleship!");
					board.updateGUI(location, "B");		
				}
				if(guess.equals("C")){
					cruiser.getAttack();
					if(cruiser.getDestroied())
						System.out.println("You have sunken a Cruiser!");
					board.updateGUI(location, "C");						
				}
				if(guess.equals("D") && destroyer1.containLoc(location) ){
					destroyer1.getAttack();
					if(destroyer1.getDestroied())
						System.out.println("You have sunken a Destroyer!");
					board.updateGUI(location, "D");	
					
				}
				if(guess.equals("D") && destroyer2.containLoc(location) ){
					destroyer2.getAttack();
					if(destroyer2.getDestroied())
						System.out.println("You have sunken a Destroyer!");
					board.updateGUI(location, "D");	
					
				}
				else if(guess.equals("X")){
					System.out.println("You missed!");
					board.updateGUI(location, "MISS!");
				}				
				masterMap.remove(location);			
			}
			
			//check if all the ships are sunken, if so, get to the score screen
			if(aircraftcarrier.getDestroied() && battleship.getDestroied() && cruiser.getDestroied() &&
					destroyer1.getDestroied() && destroyer2.getDestroied()){
				System.out.println ("You sank all the ships!");
				System.out.println ("Please enter your name: ");
				String name = scan.next();
				board.finishGUI(turn, name, file);
				return;
			}			
			turn++;
		} 		
	}	
}
