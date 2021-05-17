package common;

import java.util.ArrayList;

public class Users {

	public int id;
	public static int idNr = 1;
	public String message;
	public String event;
	public String username;
	public String currentDiscipline;
	public int result;
	public int score;

	public Users(String name, Event e) {

		id = idNr;
		username = name;
		result = 0;
		score = 0;
		event = e.getName();
		currentDiscipline = null;

		idNr++;
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
