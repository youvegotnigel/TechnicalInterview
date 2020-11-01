package restAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import static org.hamcrest.Matchers.*;

public class RestAssured {

    @Test
    public void test01() {

        Response response = get("https://reqres.in/api/users?page=2");

        System.out.println(response.getStatusCode());
        //System.out.println(response.asString()); // print json

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200); // verify status code is 200

        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id", hasItems(4,5,6)); // verify items 4,5,6 exists
    }

    @Test
    public void test02(){

        JSONObject request = new JSONObject();
        request.put("name","abc");
        request.put("job","leader");

        given().body(request.toJSONString()).when().post("https://reqres.in/api/users").then().statusCode(201); // verify status code

        given().header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().
                body("name", equalTo("abc")).  // verify name
                body("job", equalTo("leader")).  // verify job
                statusCode(201).log().all();        //extract id
    }

    @Test
    public void test03(){

        JSONObject request = new JSONObject();
        request.put("name","cde");
        request.put("job","leader");


        given().header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("https://reqres.in/api/users/969").  //id was extracted = 969
                then().
                body("name", equalTo("cde")).  // verify name
                body("job", equalTo("leader")).  // verify job
                statusCode(200).log().all();
    }
}
