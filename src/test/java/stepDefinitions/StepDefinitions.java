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
		user.chooseEvent(userEvent);
	}
	
	@When("I write a {string} and press Create")
    public void i_write_a_name_and_press_Create(String username) {
        actualMessage = user.addUser(username);
    }

    @Then("I get a status {string}")
    public void i_get_a_status_message(String message) {
    	assertEquals(message, actualMessage);
    }
}
