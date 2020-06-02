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
import java.util.Arrays;
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
        Assert.assertEquals(response.getStatusCode(), responcecode,
                "Actual responce code is different form expected");
    }

    @And("^Responce date corresponds to (.+)$")
    public void responce_data_corresponds_to(String expecteddate) throws Throwable {
        Assert.assertTrue(verifyExpectedDate(expecteddate, ratesPojo.getDate()),
                "Actual date is different form expected");
    }

    @Given("^Rates API URL is available with (.+) and (.+)$")
    public void rates_api_url_is_available_with_and(String requestparamname, String requestparamvalue) throws Throwable {
        List<String> paramValues = new ArrayList<>();
        String[] items = requestparamvalue.split(",");
        paramValues = Arrays. asList(items);

        requestSpec = given().spec(requestSpecification())
                .queryParam(requestparamname, paramValues);
        resourceAPI = APIResources.valueOf("getRatingsApiByDate");
    }

    @When("^API is called by GET method for (.+)$")
    public void the_api_is_called_by_get_method_for(String specifieddate) throws Throwable {
        response = requestSpec.when().log().all().get(resourceAPI.getResource() + "/" + specifieddate);
        ratesPojo = response.getBody().as(ResponceLatestUsdGbp.class);
    }

    @Then("^Responce base provided is (.+)$")
    public void responce_base_provided_is(String responcebasevalue) throws Throwable {
        Assert.assertEquals(ratesPojo.getBase(), responcebasevalue,
                "Actual Base value is different from expected");
    }

    @And("^Responce ratings available with (.+)$")
    public void responce_ratings_available_with(String responceratingslist) throws Throwable {
        List<String> paramValues = new ArrayList<>();
        String[] items = responceratingslist.split(",");
        paramValues = Arrays. asList(items);

        if (!paramValues.get(0).equals("ALL")) {
            Assert.assertEquals(paramValues.size(), ratesPojo.getRates().size(),
                    "Number of rates is different from expected");
            paramValues.forEach(item -> Assert.assertTrue(ratesPojo.getRates().containsKey(item),
                        "Rates returned different from expected"));
        } else {
            Assert.assertFalse(ratesPojo.getRates().isEmpty());
        }
    }

    @And("^Data in responce corresponds to (.+)$")
    public void data_in_responce_corresponds_to(String expecteddate) throws Throwable {
        Assert.assertTrue(verifyExpectedDate(expecteddate, ratesPojo.getDate()));
        System.out.println();
    }
}
