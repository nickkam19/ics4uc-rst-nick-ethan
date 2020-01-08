package rst;

public class Player {

	String name;
	String time;
	
	public Player(String nameReceived, String timeReceived) {
		name = nameReceived;
		time = timeReceived;
	}

	public int getScore() {
		return Integer.parseInt(time)*10250;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		
		return name + "\t\t\t\t\t\tScore :" + String.valueOf((Integer.parseInt(time))*10250);
	}
	
	
	
}
