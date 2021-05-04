package common;

import java.util.ArrayList;

public class Users {

	private String event;
	private String message;
	private ArrayList<String> usersDecathlon = new ArrayList<String>();
	private ArrayList<String> usersHeptathlon = new ArrayList<String>();
	private boolean invalid;
	private boolean valid;
	private boolean empty;

	public String addUser(String name) {
		invalid = false;
		valid = false;
		empty = false;
		usersDecathlon.add("Musse Pigg");
		usersHeptathlon.add("Mimmie");

		name = setTestData(name);
		System.out.println("the name: " + name);


		if (name == "") {
			message = "Please enter a name.";
			empty = true;
		}
		else {
			valid = isValid(name);
			
		}

		System.out.print("In decathlon: ");
		for (String s : usersDecathlon) {
			System.out.print(s + ", ");
			System.out.println();
		}

		System.out.print("In heptathlon: ");
		for (String s : usersHeptathlon) {
			System.out.print(s + ", ");
			System.out.println();
		}
		
		if (usersDecathlon.contains(name) || usersHeptathlon.contains(name)) {
			message = "Name already exists. Note that you can only register once.";
			//invalid = true;
			System.out.println("invalid");
		}

		if (valid) {
			if(!isAlreadyRegistered(name)) {
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
		}
		else {
			if(!empty) { message = "Invalid name, only letters and space are allowed."; }
		}

		return message;
	}
	
	public static boolean isValid(String s) {
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

	public String chooseEvent(String userEvent) {
		event = userEvent;
		return userEvent;
	}

	public String setTestData(String name) {
		switch(name) {
		case "male name":
			name = "Kalle Anka";
			break;
		case "female name":
			name = "Kajsa Anka";
			break;
		case "already exists 1":
			name = "Musse Pigg";
			break;
		case "already exists 2":
			name = "Mimmie";
			break;
		case "empty":
			name = "";
			break;
		case "invalid name 1":
			name = "123";
			break;
		case "invalid name 2":
			name = "_?!";
			break;
		}
		
		return name;
	}

}
