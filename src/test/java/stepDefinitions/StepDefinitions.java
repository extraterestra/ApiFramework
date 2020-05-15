package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import resources.APIResources;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class StepDefinitions extends Utils {

    RequestSpecification requestSpec;
    ResponseSpecification responceSpec;
    Response response;
    APIResources resourceAPI;

    //Scenario 1 and 3
    @Given("^Latest Rates API URL with (.+) is available$")
    public void latest_rates_api_url_with_is_available(String validitystatus) throws Throwable {
        requestSpec=given().spec(requestSpecification());
        resourceAPI=APIResources.valueOf(validitystatus);
    }

    @When("^The Latest API is called by GET method$")
    public void the_latest_api_is_called_by_get_method() throws Throwable {
        response =requestSpec.when().get(resourceAPI.getResource());
    }

    @Then("^Success status of the response returned is (.+)$")
    public void success_status_of_the_response_returned_is(int responcecode) throws Throwable {
        Assert.assertEquals(response.getStatusCode(), responcecode);
    }


    @Given("^Latest Rates API URL is available with (.+) and (.+)$")
    public void latest_rates_api_url_is_available_with_and(String paramname, String paramvalue) throws Throwable {
        requestSpec=given().spec(requestSpecification().queryParam(paramname,paramvalue));
        resourceAPI=APIResources.valueOf("getLatestRatings");
    }

    @When("^ Latest Rates API is called by GET method$")
    public void latest_rates_api_is_called_by_get_method() throws Throwable {
        response =requestSpec.when().get(resourceAPI.getResource());
    }

    @Then("^ Responce base is (.+)$")
    public void responce_base_is(String basevalue) throws Throwable {
        System.out.println("Api called");
    }

    @And("^  Responce ratings available is/are (.+)$")
    public void responce_ratings_available_isare(String ratingslist) throws Throwable {
        System.out.println("Responce received");
    }
}