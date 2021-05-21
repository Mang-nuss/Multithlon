package common;

import gui.MultithlonGUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Event {

    public String eventName;
    public ArrayList<Users> users;
    public Map<String, double[]> Dc = new HashMap<String, double[]>();
    public Map<String, double[]> Hc = new HashMap<String, double[]>();

    public Event(String name) {

        eventName = name;
        users = new ArrayList<>();
        addConstants(name);
    }

    public void addToUsersList(Users u) {
        users.add(u);
    }

    public void addConstants(String name) {
        if(name.equals("Decathlon")) {
            Dc.put("100m", new double[]{25.4347, 18.0, 1.81});
            Dc.put("Long jump", new double[]{.14354, 220.0, 1.4});
            Dc.put("Shot put", new double[]{51.39, 1.5, 1.05});
            Dc.put("High jump", new double[]{.8465, 75.0, 1.42});
            Dc.put("400m", new double[]{1.53775, 82.0, 1.81});
            Dc.put("110m hurdles", new double[]{5.74352, 28.5, 1.92});
            Dc.put("Discus throw", new double[]{12.91, 4.0, 1.1});
            Dc.put("Pole vault", new double[]{.2797, 100.0, 1.35});
            Dc.put("Javelin throw", new double[]{10.14, 7.0, 1.08});
            Dc.put("1500m", new double[]{.03768, 480.0, 1.85});
        }
        else if(name.equals("Heptathlon")) {
            Hc.put("100m hurdles", new double[]{9.23076, 26.7, 1.835});
            Hc.put("High jump", new double[]{1.84523, 75.0, 1.348});
            Hc.put("Shot put", new double[]{56.0211, 1.5, 1.05});
            Hc.put("200m", new double[]{4.99087, 42.5, 1.81});
            Hc.put("Long jump", new double[]{.188807, 210.0, 1.41});
            Hc.put("Javelin throw", new double[]{15.9803, 3.8, 1.04});
            Hc.put("800m", new double[]{.11193, 254.0, 1.88});
        }
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
                //System.out.println("event for " + name + " should be ");
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

    public Users(String name) {

        Users user = null;

        for (Users u : users) {
            if (u.getUsername().equals(name)) {
                user = u;
            }
        }

        return user;
    }
}
