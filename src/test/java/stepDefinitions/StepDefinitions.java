package stepDefinitions;

import static org.junit.Assert.assertEquals;

import common.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	
	private Users user = new Users();
	private String actualMessage;
	
	@Given("I choose {string}")
	public void i_choose_event(String userEvent) {
		user.setEvent(userEvent);
	}
	
	@When("I write a {string} and press Create")
    public void i_write_a_name_and_press_Create(String username) {
        actualMessage = user.addUser(username, user.getEvent());
        System.out.println();
    }

    @Then("I get a status {string}")
    public void i_get_a_status_message(String message) {
    	assertEquals(message, actualMessage);
    }

    @Given("I have chosen an {string}")
    public void iHaveChosenAnEvent(String event) {
        System.out.println(event);
    }

    @Given("There are {int} of participants reached for {string}")
    public void thereAreMaximumNumberOfParticipantsReachedForEvent(int nr, String event) {
	    user.setEvent(event);
        if(event.equals("Decathlon")) {
            user.storeUsers(nr, user.usersDecathlon);
            System.out.println(user.usersDecathlon.size());
        }
        else if(event.equals("Heptathlon")) {
            user.storeUsers(nr, user.usersHeptathlon);
            System.out.println(user.usersHeptathlon.size());
        }

    }

    @When("I try to register another {string}")
    public void iTryToRegisterAnotherParticipant(String name) {
	    user.addUser(name, user.getEvent());
	    System.out.println("when statement done");

    }

    @Then("The {string} is displayed")
    public void theMessageIsDisplayed(String assumedMessage) {
	    assertEquals(assumedMessage, user.message);
    }
}
