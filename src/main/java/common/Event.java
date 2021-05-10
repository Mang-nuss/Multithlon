package common;

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
}
