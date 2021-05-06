package common;

import java.util.ArrayList;

public class Users {

	public String message;
	public String event;
	public ArrayList<String> usersDecathlon = new ArrayList<String>();
	public ArrayList<String> usersHeptathlon = new ArrayList<String>();
	private boolean invalid;
	private boolean valid;
	private boolean empty;
	public boolean notReached;

	public String addUser(String name, String e) {
		invalid = false;
		valid = false;
		notReached = false;
		empty = false;
/*		usersDecathlon.add(name);
		usersHeptathlon.add(name);*/
		event = e;

		//name = setTestData(name);
		//System.out.println("the name: " + name);

		insertCopies(name); //only for 'already existing' test cases

		if (name.isEmpty()) {
			message = "Please enter a name.";
			System.out.println("message is " + message);
			empty = true;
		}
		else { valid = isValid(name, event); }

		notReached = maximumNrReached();

		if (valid && notReached) {
			if(!isAlreadyRegistered(name)) {
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
			}
			else { message = "Name already exists. Note that you can only register once.";
			}
		}
		else {
			if(!empty) { message = "Invalid name, only letters and space are allowed."; }
		}

		return message;
	}
	
	public boolean isValid(String s, String event) {
	    boolean valid = true;
	    String a = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   
		 for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			for(int n = 0; n < a.length(); n++) {
				if (!a.contains(String.valueOf(c))) {
					valid = false;
					break;
				}
			}
		 }
	   
	    return valid;
	}

	public boolean isAlreadyRegistered(String name) {

		for(String n : usersDecathlon) {
			if(name.equals(n)) { return true; }
		}
		for(String n : usersHeptathlon) {
			if(name.equals(n)) { return true; }
		}

		return false;
	}

	public boolean maximumNumberOfParticipantsIsReached(ArrayList<String> al) {

		if(al.size() > 19) { return true; }
		else { return false; }
	}

	public boolean maximumNrReached() {

		boolean reached = false;

		if (event.equals("Decathlon")) {
			if (maximumNumberOfParticipantsIsReached(usersDecathlon)) {
				reached = true;
				message = "Registration failed. The maximum nr of participants is reached.";
			}
		}
		else if (event.equals("Heptathlon")) {
			if (maximumNumberOfParticipantsIsReached(usersHeptathlon)) {
				reached = true;
				message = "Registration failed. The maximum nr of participants is reached.";
			}
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
		if(name.equals("copy")) {
			if(event.equals("Decathlon")) {
				usersDecathlon.add(name);
				System.out.println(name + " added");
			}
			else if(event.equals("Heptathlon")) {
				usersHeptathlon.add(name);
				System.out.println(name + " added");
			}
		}
	}

	public void storeUsers(int nrOfContestants, ArrayList<String> al) {

		for(int n = 0; n < nrOfContestants; n++) {
			String name = "participant" + n;
			al.add(name);
		}
	}

}
