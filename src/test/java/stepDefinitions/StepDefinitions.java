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
	
	@Given("I open the application")
	public void i_open_the_application() {
	   System.out.println("Opens application");
	}
	
	@When("I write a {string} and choose {string}")
    public void i_write_a_name_and_choose_event(String username, String event) {
        actualMessage = user.addUser(username, user.chooseEvent(event));
    }

    @Then("I get a status {string}")
    public void i_get_a_status_message(String message) {
    	assertEquals(message, actualMessage);
    }
}
