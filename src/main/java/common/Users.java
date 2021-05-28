package common;

import java.util.ArrayList;

public class Users {

	public int id;
	public static int idNr = 0;
	public String message;
	public String event;
	public String username;
	public String currentDiscipline;
	public int result;
	public int score;
	public ArrayList<Object[][]> results;
	public Object[][] decathlonResultArray = {
			{"100m",0,0},
			{"Long jump",0,0},
			{"Shot put",0,0},
			{"High jump",0,0},
			{"400m",0,0},
			{"110m hurdles",0,0},
			{"Discus throw",0,0},
			{"Pole vault",0,0},
			{"Javelin throw",0,0},
			{"1500m",0,0}};
	public Object[][] heptathlonResultArray = {
			{"100m hurdles",0,0},
			{"High jump",0,0},
			{"Shot put",0,0},
			{"200m",0,0},
			{"Long jump",0,0},
			{"Javelin throw",0,0},
			{"800m",0,0}};
	public Object[][] resultArray;

	public Users(String name, Event e) {

		idNr++;
		id = idNr;
		username = name;
		result = 0; //Should be "merged" to be a matrix
		score = 0; //
		event = e.getName();
		currentDiscipline = null;

		if(e.getName().equals("Heptathlon")) { resultArray = heptathlonResultArray; }
		else if(e.getName().equals("Decathlon")) { resultArray = decathlonResultArray; }
	}

	public String getUsername() {
		return username;
	}

	public void setEvent(String userEvent) {
		event = userEvent;
	}

	public String getEvent() {
		return event;
	}

	public int getResult() { return result; }

}
