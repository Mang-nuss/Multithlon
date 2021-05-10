package stepDefinitions;

import static org.junit.Assert.assertEquals;

import common.Event;
import common.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

public class StepDefinitions {
	
	private Users user;
	public String actualMessage;
	public String event;
    public ArrayList<Users> usersDecathlon = new ArrayList<>();
    public ArrayList<Users> usersHeptathlon = new ArrayList<>();
    //public Event decathlon = new Event("Decathlon");
    //public Event heptathlon = new Event("Heptathlon");
    public Event theEvent;
	
	@Given("I choose {string}")
	public void i_choose_event(String userEvent) {
		//user.setEvent(userEvent);
        if(userEvent.equals("Decathlon")) { theEvent = new Event("Decathlon"); }
        else if(userEvent.equals("Heptathlon")) { theEvent = new Event("Heptathlon"); }
        event = userEvent;
	}
	
	@When("I write a {string} and press Create")
    public void i_write_a_name_and_press_Create(String username) {
		//Users.insertCopies(username, event);
        if (username.equals("copy")) {/*
            if (theEvent.getName().equals("Decathlon")) {*/
            user = new Users(username, theEvent.getName());
            theEvent.addToUsersList(new Users(username, event));
            System.out.println(username + " added");/*
            } else if (theEvent.getName().equals("Heptathlon")) {
                theEvent.addToUsersList(new Users(username, event));
                System.out.println(username + " added");
            }*/
        }
		actualMessage = Users.addUser(username, event);
        System.out.println();
    }

    @Then("I get a status {string}")
    public void i_get_a_status_message(String message) {
    	assertEquals(message, actualMessage);
    }

    @Given("There are {int} of participants in {string}")
    public void thereAreNumberOfParticipantsInEvent(int nr, String event) {
	    user.setEvent(event);
        if(event.equals("Decathlon")) {
            Users.storeUsers(event, usersDecathlon, nr);
        }
        else if(event.equals("Heptathlon")) {
            Users.storeUsers(event, usersHeptathlon, nr);
        }

    }

    @When("I try to register another {string}")
    public void iTryToRegisterAnotherParticipant(String name) {
    	actualMessage = user.addUser(name, user.getEvent());
    }

    @Then("The {string} is displayed")
    public void theMessageIsDisplayed(String message) {
	    assertEquals(message, actualMessage);
    }
}
