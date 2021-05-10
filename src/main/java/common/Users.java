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
	public ArrayList<Users> usersDecathlon = new ArrayList<>();
	public ArrayList<Users> usersHeptathlon = new ArrayList<>();
	private boolean valid;
	private boolean empty;
	public boolean maxReached;

	public Users(String name, String e) {

		id = idNr;
		username = name;
		result = 0;
		event = e;
		currentDiscipline = null;

		idNr++;
	}

	public String addUser(String name, String e) {
		valid = false;
		maxReached = false;
		empty = false;
		event = e;

		if (name.isEmpty()) {
			message = "Please enter a name.";
			System.out.println("message is " + message);
			empty = true;
		} else {
			valid = isValid(name, event);
		}

		maxReached = maxNrReached(event);

		if (valid && !maxReached) {
			if (!isAlreadyRegistered(name)) {
				System.out.println("event for " + name + " should be " + event);
				if (event.equals("Decathlon")) {
					Users u = new Users(name, event);
					usersHeptathlon.add(u);
					System.out.println("name: " + name + ", id: " + u.id);
					message = "Registration successful. You're now participating in Decathlon.";
				} else if (event.equals("Heptathlon")) {
/*					Users u = new Users(name, event);
					usersHeptathlon.add(u);
					System.out.println("name: " + name + ", id: " + u.id);*/
					message = "Registration successful. You're now participating in Heptathlon.";

				} else {
					message = "In order to register, you must choose which event to participate in.";
				}
			} else {
				message = "Name already exists. Note that you can only register once.";
			}
		} else if (!empty && !valid) {
			message = "Invalid name, only letters and space are allowed.";
		} else if (maxReached) {
			message = "Registration failed. The maximum nr of participants is reached.";
		}

		return message;
	}

	public boolean isValid(String s, String event) {
		boolean valid = true;
		String a = "abcdefghijklmnopqrstuvwxyzåäöü ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖÜ";

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			for (int n = 0; n < a.length(); n++) {
				if (!a.contains(String.valueOf(c))) {
					valid = false;
					break;
				}
			}
		}

		return valid;
	}

	public boolean isAlreadyRegistered(String name) {

		for (Users u : usersDecathlon) {
			if (u.getUsername().equals(name)) {
				return true;
			}
		}
		for (Users u : usersHeptathlon) {
			if (u.getUsername().equals(name)) {
				return true;
			}
		}

		return false;
	}

	private String getUsername() {
		return username;
	}

	public boolean maxNrReached(String event) {

		boolean reached = false;

		if (event.equals("Decathlon") && (usersDecathlon.size() > 19)) {
			reached = true;
		} else if (event.equals("Heptathlon") && (usersHeptathlon.size() > 19)) {
			reached = true;
		}

		return reached;
	}

	public void setEvent(String userEvent) {
		event = userEvent;
	}

	public String getEvent() {
		return event;
	}

/*	public void insertCopies(String name, String e) {
		if (name.equals("copy")) {
			if (e.equals("Decathlon")) {
				usersDecathlon.add(new Users(name, e));
				System.out.println(name + " added");
			} else if (e.equals("Heptathlon")) {
				usersHeptathlon.add(new Users(name, e));
				System.out.println(name + " added");
			}
		}
	}*/

	public static void storeUsers(String event, ArrayList<Users> al, int nrOfContestants) {

		for (int n = 0; n < nrOfContestants; n++) {
			String name = "Magnus";
			Users user = new Users(name+n, event);
			//String name = "participant" + n;
			al.add(user);
		}
	}

}
