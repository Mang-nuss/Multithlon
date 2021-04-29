package common;

import java.util.ArrayList;

public class Users {

	private String event;
	private String message;
	private ArrayList<String> usersDecathlon = new ArrayList<String>();
	private ArrayList<String> usersHeptathlon = new ArrayList<String>();

	
	public String addUser(String name, String event) {

		if (name == null) {
			message = "Please enter a name";
		}
		
//		if (usersDecathlon.contains(name) || usersHeptathlon.contains(name)) {
//			message = "Name already exists. Note that you can only register once.";
//		}
	
		for (int i = 0; i < name.length(); i++) {
			if (!isLetter(name.charAt(i))) {
				message = "Invalid name, only letters and space are allowed.";
			}
		}

		if (event.equals("Decathlon")) {
			usersDecathlon.add(name);
			message = "Registration successful. You're now participating in Decathlon.";
		} else if (event.equals("Heptathlon")) {
			usersHeptathlon.add(name);
			message = "Registration successful. You're now participating in Heptathlon.";
		}

		else {
			message = "In order to register, you must choose which event to participate in.";
		}
		
		return message;
	}

	public boolean isLetter(char sign) {
		boolean check = false;

		if ((sign >= 'a' && sign <= 'z') || (sign >= 'A' && sign <= 'Z'))
			check = true;

		return check;
	}

	public String chooseEvent(String userEvent) {
		event = userEvent;
		return userEvent;
	}

}
