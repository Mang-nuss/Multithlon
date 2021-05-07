package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculationDefinitions {
	
	public String event;
	
	@Given("I am a registered user in {String}")
	public void i_am_a_registered_user_in_event(String e) {
		event = e;
		System.out.println("You're registered in " + event); 
	}

	@When("I choose a relevant {String}")
	public void i_choose_a_relevant_discipline() {
	   
	}
	
	@When("I insert correct {double} input")
	public void i_insert_correct_input(Double double1) {

	}
	@Then("I can see a correct  based on respective input")
	public void i_can_see_a_correct_based_on_respective_input() {

	}
}
