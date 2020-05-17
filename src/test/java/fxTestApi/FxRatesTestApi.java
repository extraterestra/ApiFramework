package fxTestApi;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.ResponceLatestUsdGbp;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class FxRatesTestApi extends Utils {

    @Test
    public void verifyFxLatestResponceCode() {
        //GIVEN
        RestAssured.baseURI = "https://api.ratesapi.io/api/latest";
        int status = given().log().all().header("Content-Type", "application/json")

        //WHEN
        .when().get().getStatusCode();
//		.then().assertThat().statusCode(200);

        //TNEN
        System.out.println(status);
        Assert.assertEquals(status, 200);

    }

    @Test
    public void verifyFxLatestResponceBodyContent() {

        ResponceLatestUsdGbp ratesTest = RestAssured
                .get("https://api.ratesapi.io/api/latest")
                .as(ResponceLatestUsdGbp.class);


        System.out.println(ratesTest.getBase());
        Assert.assertEquals(ratesTest.getBase(), "EUR");
    }

    @Test
    public void verifyFxLatestSymbolsUsdGbp() {
        RestAssured.baseURI = "https://api.ratesapi.io";
        ResponceLatestUsdGbp ratesTest = given().log().all().queryParam("symbols", "USD,GBP")
                .expect().defaultParser(Parser.JSON)

                .when().get("/api/latest")
                .as(ResponceLatestUsdGbp.class);


        System.out.println(ratesTest.getBase());
        System.out.println(ratesTest.getRates());
        Assert.assertEquals(ratesTest.getBase(), "EUR");
        Assert.assertEquals(ratesTest.getRates().size(),2);
        Assert.assertTrue(ratesTest.getRates().containsKey("USD"));
        Assert.assertTrue(ratesTest.getRates().containsKey("GBP"));
    }

    @Test
    public void verifyFxLatestBaseUsd() {
        RestAssured.baseURI = "https://api.ratesapi.io/api/latest";
        String responce = given().log().all().queryParam("base", "USD")
                .header("Content-Type", "application/json")

                .when().get()
                .then().assertThat().statusCode(200)
                .body("base", equalTo("USD"))
                .header("Content-Type", "application/json")
                .extract().response().asString();

        System.out.println(responce);

        JsonPath js = new JsonPath(responce);
        String base = js.getString("base");

        System.out.println("BASE = " + base);
        Assert.assertEquals(base, "USD");
    }

    @Test
    public void verifyFxLatestNegative() {
        RestAssured.baseURI = "https://api.ratesapi.io/api/";
        String responce = given().log().all().queryParam("base", "USD")
                .header("Content-Type", "application/json")

                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(404)
                .extract().response().asString();

        System.out.println(responce);
    }
}
