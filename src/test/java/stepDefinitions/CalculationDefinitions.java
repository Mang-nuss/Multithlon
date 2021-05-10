package stepDefinitions;

import common.Calculator;
import common.Event;
import common.Users;
import gui.MultithlonGUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculationDefinitions {

	public MultithlonGUI gui = new MultithlonGUI();
	public String event;
	public String discipline;
	public double result;
	public double[] values;
	public Calculator calculator = new Calculator();
	public Users user;
	public Event decathlon = new Event("Decathlon");
	public Event heptathlon = new Event("Heptathlon");
	public Event evt;
	
	@Given("I am a registered user in {string}")
	public void i_am_a_registered_user_in_event(String e) {
		gui.events.add(decathlon);
		gui.events.add(heptathlon);
		evt = gui.getEventByName(e);
		user = new Users("Eva", evt);
		event = e;
		System.out.println("You're registered in " + user.getEvent());
	}

	@When("I choose a relevant {string}")
	public void i_choose_a_relevant_discipline(String disc) {
		discipline = disc;
		calculator.pickDisciplineFromMap(event, disc);
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
		calculator.calculateScore(event, discipline, result, values);
	}
}
