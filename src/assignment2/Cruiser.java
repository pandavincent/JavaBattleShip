package assignment2;

public class Cruiser extends Ship {
	private int track = 0;
	
	public Cruiser (int loc1, int loc2, int loc3) {
		shipList.add(loc1);
		shipList.add(loc2);
		shipList.add(loc3);
	}
	
	public void getAttack() {
		track++;
		System.out.println ("You hit a Battleship!");
	}
	
	public boolean getDestroied() {
		if (track == 3)
			return true;
		else 
			return false;
	}

}