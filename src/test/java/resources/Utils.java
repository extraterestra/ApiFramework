package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {

    public static RequestSpecification request;
    public static String pathToProp = "D:\\Docs\\Java\\Api\\src\\test\\java\\resources\\global.properties";
    public RequestSpecification requestSpecification() throws IOException
    {
        if(request==null)
        {
//            PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
            request=new RequestSpecBuilder().setBaseUri("https://api.ratesapi.io") //  getGlobalValue("baseUrl")
//                    .addFilter(RequestLoggingFilter.logRequestTo(log))
//                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return request;
        }
        return request;

    }
    public static String getGlobalValue(String key) throws IOException
    {
        Properties prop =new Properties();
        FileInputStream file =new FileInputStream(pathToProp);
        prop.load(file);
        return prop.getProperty(key);
    }
}
