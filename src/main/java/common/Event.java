package common;

import gui.MultithlonGUI;

import java.util.ArrayList;

public class Event {

    public String eventName;
    public ArrayList<Users> users;

    public Event(String name) {

        eventName = name;
        users = new ArrayList<>();
    }

    public void addToUsersList(Users u) {
        users.add(u);
    }

    public String getName() { return eventName; }

    public String addUser(String name, Event evt) {
        boolean valid = false;
        //boolean maxReached = false;
        boolean empty = false;
        //String event = e;

        String message = null;
        if (name.isEmpty()) {
            message = "Please enter a name.";
            System.out.println("message is " + message);
            empty = true;
        } else {
            valid = isValid(name); //Does it consist of valid characters?
        }

        //maxReached = maxNrReached(evt);

        if (valid && !maxNrReached(evt)) {
            if (!isAlreadyRegistered(name)) {
                System.out.println("event for " + name + " should be ");
                if (evt.getName().equals("Decathlon") || evt.getName().equals("Heptathlon")) {
                    Users u = new Users(name, evt);
                    evt.users.add(u);
                    System.out.println("name: " + name + ", id: " + u.id);
                    message = "Registration successful. You're now participating in " + evt.getName() + ".";
                } else {
                    message = "In order to register, you must choose which event to participate in.";
                }
            } else {
                message = "Name already exists. Note that you can only register once.";
            }
        } else if (!empty && !valid) {
            message = "Invalid name, only letters and space are allowed.";
        } else if (maxNrReached(evt)) {
            message = "Registration failed. The maximum nr of participants is reached.";
        }

        return message;
    }

    public boolean maxNrReached(Event evt) {

        boolean reached = false;

        if (evt.getName().equals("Decathlon") && (evt.users.size() > 19)) {
            reached = true;
        } else if (evt.getName().equals("Heptathlon") && (evt.users.size() > 19)) {
            reached = true;
        }

        return reached;
    }

    public boolean isValid(String s) {
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

        for (Users u : users) {
            if (u.getUsername().equals(name)) {
                return true;
            }
        }

        return false;
    }
}
