package stepDefinitions;

import common.Calculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculationDefinitions {
	
	public String event;
	public double result;
	public double[] values;
	public Calculator calculator = new Calculator();
	
	@Given("I am a registered user in {string}")
	public void i_am_a_registered_user_in_event(String e) {
		event = e;
		System.out.println("You're registered in " + event); 
	}

	@When("I choose a relevant {string}")
	public void i_choose_a_relevant_discipline(String discipline) {
		calculator.pickDisciplineFromMap(event, discipline);
		System.out.println("values = " + calculator.getValues());
	}
	
	@When("I insert correct {double} input")
	public void i_insert_correct_input(Double result) {
		calculator.setResultInput(result);
		System.out.println("result = " + calculator.getResult());
	}

	@Then("I can see a correct  based on respective input")
	public void i_can_see_a_correct_based_on_respective_input() {
		System.out.println("in then line");
		result = calculator.getResult();
		calculator.calculateScore(result, values);
	}
}
