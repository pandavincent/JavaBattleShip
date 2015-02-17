package assignment2;
import java.util.ArrayList;

public abstract class Ship {
	
	ArrayList<Integer> shipList = new ArrayList<Integer>();
	
	public abstract void getAttack();	
	public abstract boolean getDestroied();
	public boolean containLoc(int location){
		if (shipList.contains(location))
			return true;
		else
			return false;
	}
}
