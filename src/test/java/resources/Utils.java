package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Utils {

    public static RequestSpecification request;
    public static String pathToGlobalProp = "\\src\\test\\java\\resources\\global.properties";
    public static Properties prop;

    public RequestSpecification requestSpecification() throws IOException {
        if (request == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return request;
        }
        return request;
    }

    public static String getGlobalValue(String key) throws IOException {
        prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + pathToGlobalProp);
        prop.load(file);

        return prop.getProperty(key);
    }
    public Response getResponce (String url) throws IOException {
        return  given().spec(requestSpecification()).get(url);
    }
    /**
     * The methods converts Date in to String type with "yyyy-MM-dd" pattern
     *
     * @param d Date format, cannot be null
     * @return Sting type
     */
    public String dateToString(Date d) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(d);
    }

    public String getLastWorkingDate ()
    {
        LocalDate date = getPreviousWorkingDay(LocalDate.now());
        return date.toString();
    }

    /**
     * The methods calculates the previous working day. It only recognize Saturday
     * and Sunday as non -working days.
     *
     * @param date
     *          Date as starting point for the calculation, cannot be null
     * @return The previous working day
     */
    public static LocalDate getPreviousWorkingDay(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        switch (dayOfWeek) {
            case MONDAY:
                return date.minus(3, ChronoUnit.DAYS);
            case SUNDAY:
                return date.minus(2, ChronoUnit.DAYS);
            default:
                return date.minus(1, ChronoUnit.DAYS);
        }
    }

    public boolean verifyExpectedDate (String expectedDate, Date actualDate){
        if (expectedDate.equals("LAST WORKING DAY")) {
            return dateToString(actualDate).equals(getLastWorkingDate());
        } else {
            return dateToString(actualDate).equals(expectedDate);
        }
    }
}
