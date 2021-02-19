package restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class OneAppAuthTests {

    @Test
    public void testAuth(){

        RestAssured.baseURI = "https://oneapp.ncinga.com";

        given()
                //.contentType(ContentType.URLENC.withCharset("UTF-8"))
                .contentType("x-www-form-urlencoded")
                .header("Content-Type","application/x-www-form-urlencoded")
                .formParam("username,","qc1")
                .formParam("password","123")
                .pathParam("client","DirectFormClient")
                .when()
                .post("/auth_services/login?client_name={client}")
                .then()
                //.statusCode(200)
                .log().all();

    }


    @Test
    public void submitForm(){

        RestAssured.baseURI = "https://www.example.com";
        given().urlEncodingEnabled(true)
                .param("username", "user@site.com")
                .param("password", "Pas54321")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/login")
                .then().statusCode(200).log().all();

    }


    @Test
    public void testAuth2(){

        given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/x-www-form-urlencoded", ContentType.JSON)))
                .formParam("username,","qc1")
                .formParam("password","123")
                .when()
                .post("https://oneapp.ncinga.com/auth_services/login?client_name=DirectFormClient")
                .then()
                //.statusCode(200)
                .log().all();

    }

    @Test
    public void testAuth3(){

        RestAssured.baseURI = "https://oneapp.ncinga.com";

        Response response = RestAssured
                .given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .formParam("username,","qc1")
                .formParam("password","123")
                .request()
                .post("/auth_services/login?client_name=DirectFormClient");

        System.out.println(response.statusCode());
        System.out.println(response.contentType());
        System.out.println(response.prettyPrint());
    }

}
