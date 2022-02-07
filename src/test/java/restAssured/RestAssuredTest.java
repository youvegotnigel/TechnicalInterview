package restAssured;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
//import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.internal.matchers.IsCollectionContaining.hasItems;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class RestAssuredTest {

    private static String ID = "";

    @Test
    public void test01() {

        Response response = get("https://reqres.in/api/users?page=2");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200); // verify status code is 200
        Assert.assertTrue(response.jsonPath().getString("data.id").contains("7"));
        Assert.assertTrue(response.jsonPath().getString("data.id").contains("8"));
        Assert.assertTrue(response.jsonPath().getString("data.id").contains("9"));

        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id", hasItems(7,8,9)); // verify items 4,5,6 exists
    }

    @Test
    public void test02(){

        JSONObject request = new JSONObject();
        request.put("name","abc");
        request.put("job","leader");

        Response response = given().header("Content-Type","application/json")
                .body(request.toJSONString())
                .post("https://reqres.in/api/users");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "abc");
        Assert.assertEquals(response.jsonPath().getString("job"), "leader");
        ID = response.jsonPath().getString("id");
        System.out.println("ID is ::: " + ID);
    }

    @Test
    public void test03(){

        JSONObject request = new JSONObject();
        request.put("name","cde");
        request.put("job","leader");

        Response response = given().header("Content-Type","application/json")
                .body(request.toJSONString())
                .when()
                .put("https://reqres.in/api/users/{ID}", ID);

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "cde");
        Assert.assertEquals(response.jsonPath().getString("job"), "leader");
    }

    @Test
    public void test04(){

        JSONObject request = new JSONObject();
        request.put("name","stu");
        request.put("job","member");

        Response response = given().header("Content-Type","application/json")
                .body(request.toJSONString())
                .pathParam("ID",ID)
                .when()
                .put("https://reqres.in/api/users/{ID}");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "stu");
        Assert.assertEquals(response.jsonPath().getString("job"), "member");
    }
}
