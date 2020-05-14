package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class StepDefinitions {

    @Given("^Latest Rates API was called$")
    public void latest_rates_api_was_called() throws Throwable {
        System.out.println("Latest API called");
    }

    @When("^The API is available$")
    public void the_api_is_available() throws Throwable {
        System.out.println("API is available");
    }

    @Then("^Success status of the response returned is 200$")
    public void success_status_of_the_response_returned_is_200() throws Throwable {
        System.out.println("Return code is 200");
    }

}