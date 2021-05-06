package common;

import java.util.ArrayList;

public class Users {

	public String message;
	public String event;
	public ArrayList<String> usersDecathlon = new ArrayList<String>();
	public ArrayList<String> usersHeptathlon = new ArrayList<String>();
	private boolean valid;
	private boolean empty;
	public boolean maxReached;

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
					usersDecathlon.add(name);
					message = "Registration successful. You're now participating in Decathlon.";
				} else if (event.equals("Heptathlon")) {
					usersHeptathlon.add(name);
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

		for (String n : usersDecathlon) {
			if (name.equals(n)) {
				return true;
			}
		}
		for (String n : usersHeptathlon) {
			if (name.equals(n)) {
				return true;
			}
		}

		return false;
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

	public void insertCopies(String name) {
		if (name.equals("copy")) {
			if (event.equals("Decathlon")) {
				usersDecathlon.add(name);
				System.out.println(name + " added");
			} else if (event.equals("Heptathlon")) {
				usersHeptathlon.add(name);
				System.out.println(name + " added");
			}
		}
	}

	public void storeUsers(int nrOfContestants, ArrayList<String> al) {

		for (int n = 0; n < nrOfContestants; n++) {
			String name = "participant" + n;
			al.add(name);
		}
	}

}
