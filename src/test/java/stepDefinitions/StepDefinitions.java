package stepDefinitions;

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

import static io.restassured.RestAssured.given;

public class StepDefinitions {

    String baseURI = "https://api.ratesapi.io/api/latest";
    int returnStatus;

    RequestSpecification requestSpec;
    ResponseSpecification responceSpec;
    Response response;

    @Given("^Latest Rates API was called$")
    public void latest_rates_api_was_called() throws Throwable {
        System.out.println("Latest API called");
        returnStatus = given().log().all().header("Content-Type", "application/json")
                .when().get(baseURI).getStatusCode();
    }

    @When("^The API is available$")
    public void the_api_is_available() throws Throwable {
        System.out.println("API is available");

//constructor will be called with value of resource which you pass
//            APIResources resourceAPI=APIResources.valueOf("https://api.ratesapi.io/api/latest");
//            System.out.println(resourceAPI.getResource());
//            responceSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//            response = requestSpec.when().get(resourceAPI.getResource());
    }

    @Then("^Success status of the response returned is (.+)$")
    public void success_status_of_the_response_returned_is(int errorcode) throws Throwable {
        System.out.println("Return code is " + errorcode);
//        Assert.assertEquals(response.getStatusCode(), errorcode);
        Assert.assertEquals(returnStatus, errorcode);
    }
}