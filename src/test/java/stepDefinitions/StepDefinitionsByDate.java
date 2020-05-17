package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.ResponceLatestUsdGbp;
import resources.APIResources;
import resources.Utils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinitionsByDate extends Utils {

    RequestSpecification requestSpec;
    Response response;
    APIResources resourceAPI;
    ResponceLatestUsdGbp ratesPojo;

    @Given("^Rates API URL with (.+) is available$")
    public void rates_api_url_with_is_available(String resource) throws Throwable {
        requestSpec = given().spec(requestSpecification());
        resourceAPI = APIResources.valueOf(resource);
    }

    @When("^The API is called by GET method for specified (.+)$")
    public void the_api_is_called_by_get_method_for_specified(String specifieddate) throws Throwable {
        response = requestSpec.when().log().all().get(resourceAPI.getResource() + "/" + specifieddate);
        ratesPojo = response.getBody().as(ResponceLatestUsdGbp.class);
    }

    @Then("^Responce status of the response returned is (.+)$")
    public void responce_status_of_the_response_returned_is(int responcecode) throws Throwable {
        Assert.assertEquals(response.getStatusCode(), responcecode);
    }

    @And("^Responce data corresponds to (.+)$")
    public void responce_data_corresponds_to(String expecteddate) throws Throwable {
        Assert.assertEquals(dateToString(ratesPojo.getDate()), expecteddate);
    }
}
