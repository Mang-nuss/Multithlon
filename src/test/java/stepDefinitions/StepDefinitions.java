package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    @When("I write a username as {string}")
    public void iWriteAUsernameAsUsername(String username) {
        System.out.println(username);
    }

    @And("I choose one of the events as {string}")
    public void iChooseOneOfTheEventsAsEvents(String event) {
        System.out.println(event);
    }

    @Then("I can see a correct {string} based on respective input")
    public void iCanSeeACorrectMessageBasedOnRespectiveInput(String message) {
        System.out.println(message);
    }
}
