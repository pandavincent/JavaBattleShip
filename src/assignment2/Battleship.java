package assignment2;

public class Battleship extends Ship{
	
	private int track = 0;
	
	public Battleship (int loc1, int loc2, int loc3, int loc4) {
		shipList.add(loc1);
		shipList.add(loc2);
		shipList.add(loc3);
		shipList.add(loc4);
	}
	
	public void getAttack() {
		track++;
		System.out.println ("You hit a Battleship!");
	}
	
	public boolean getDestroied() {
		if (track == 4)
			return true;
		else 
			return false;
	}
	
}
