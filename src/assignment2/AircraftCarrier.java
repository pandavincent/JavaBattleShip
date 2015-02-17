package assignment2;

public class AircraftCarrier extends Ship{
	private int track = 0;
	
	public AircraftCarrier (int loc1, int loc2, int loc3, int loc4, int loc5) {
		shipList.add(loc1);
		shipList.add(loc2);
		shipList.add(loc3);
		shipList.add(loc4);
		shipList.add(loc5);
	}
	
	public void getAttack() {
		track++;
		System.out.println ("You hit a Aircraft Carrier!");
	}
	
	public boolean getDestroied() {
		if (track == 5)
			return true;
		else 
			return false;
	}

}
