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

public class StepDefinitionsLatest extends Utils {

    RequestSpecification requestSpec;
    Response response;
    APIResources resourceAPI;
    ResponceLatestUsdGbp ratesPojo;

    //Scenario 1 and 3
    @Given("^Latest Rates API URL with (.+) is available$")
    public void latest_rates_api_url_with_is_available(String validitystatus) throws Throwable {
        requestSpec = given().spec(requestSpecification());
        resourceAPI = APIResources.valueOf(validitystatus);
    }

    @When("^The Latest API is called by GET method$")
    public void the_latest_api_is_called_by_get_method() throws Throwable {
        response = requestSpec.when().get(resourceAPI.getResource());
    }

    @Then("^Success status of the response returned is (.+)$")
    public void success_status_of_the_response_returned_is(int responcecode) throws Throwable {
        Assert.assertEquals(response.getStatusCode(), responcecode);
    }

    //Scenario 2
    @Given("^Latest Rates API URL is available with (.+) and (.+)$")
    public void latest_rates_api_url_is_available_with_and(String requestparamname, String requestparamvalue) throws Throwable {

        List<String> paramValues = new ArrayList<>();
        String[] items = requestparamvalue.split(",");

        for (String currency : items) {
            paramValues.add(currency);
        }
        requestSpec = given().spec(requestSpecification())
                .queryParam(requestparamname, paramValues);
        resourceAPI = APIResources.valueOf("getLatestRatings");
    }

    @When("^Latest Rates API is called by GET method$")
    public void latest_rates_api_is_called_by_get_method() throws Throwable {
        response = requestSpec.when().log().all().get(resourceAPI.getResource());
        ratesPojo = response.getBody().as(ResponceLatestUsdGbp.class);
    }

    @Then("^Responce base is (.+)$")
    public void responce_base_is(String responcebasevalue) throws Throwable {
        Assert.assertEquals(ratesPojo.getBase(), responcebasevalue);
    }

    @And("^Responce ratings available is (.+)$")
    public void responce_ratings_available_is(String responceratingslist) throws Throwable {
        List<String> paramValues = new ArrayList<>();
        String[] items = responceratingslist.split(",");

        for (String currency : items) {
            paramValues.add(currency);
        }
        if (!paramValues.get(0).equals("ALL")) {
            Assert.assertEquals(paramValues.size(), ratesPojo.getRates().size());
            for (String item : paramValues) {
                Assert.assertTrue(ratesPojo.getRates().containsKey(item));
            }
        } else {
            Assert.assertFalse(ratesPojo.getRates().isEmpty());
        }
    }

    @And("^Date in responce corresponds to (.+)$")
    public void date_in_responce_corresponds_to(String expecteddate) throws Throwable {
        Assert.assertTrue(verifyExpectedDate(expecteddate, ratesPojo.getDate()));
    }
}