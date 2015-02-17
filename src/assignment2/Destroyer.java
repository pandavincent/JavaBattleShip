package assignment2;

public class Destroyer extends Ship {
	private int track = 0;
	
	public Destroyer (int loc1, int loc2) {
		shipList.add(loc1);
		shipList.add(loc2);
	}
	
	public void getAttack() {
		track++;
		System.out.println ("You hit a Destroyer!");
	}
	
	public boolean getDestroied() {
		if (track == 2)
			return true;
		else 
			return false;
	}
	
/*	public boolean containLoc(int location) {
		if (this.shipList.contains(location))
			return true;
		else
			return false;
	}*/

}
