package pojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileUpload {


    @Test
    public void uploadFile(){

        File file = new File("C:\\Users\\MSI-PS42\\Desktop\\new.png");

        Response response = given()
                .multiPart("file", file, "multipart/form-data")
                .post("https://the-internet.herokuapp.com/upload")
                .thenReturn();

        System.out.println(response.prettyPrint());

    }

    @Test
    public void downloadFile() throws IOException {

        Response response = given().log().all().when().get("https://reqres.in/api/users").andReturn();

        byte[] bytes =  response.getBody().asByteArray();

        File file = new File("C:\\Essentials\\git-projects\\PracticalTest\\writeFile.json");
        Files.write(file.toPath(), bytes);
    }


    @DataProvider(name = "factoryDataProvider")
    public static Object[][] getFactoryData() {
        return new Object[][]{

//                { "C:\\Bio Data\\git\\Newman\\flink-test-data-seeding\\gayan\\Plan_xls_without\\ind-pemp-p122.xlsx", "production_plan", "ind-pemp-p122","ind"},
//                { "C:\\Bio Data\\git\\Newman\\flink-test-data-seeding\\gayan\\Plan_xls_without\\ant-aal-aepz-qa.xlsx", "production_plan", "ant-aal-aepz",""}, //tenant is empty
//                { "C:\\Bio Data\\git\\Newman\\flink-test-data-seeding\\gayan\\Plan_xls_without\\bhg-knit.xlsx", "production_plan", "bhg-knit1","bhg"}, //invalid subject key
//                { "C:\\Bio Data\\git\\Newman\\flink-test-data-seeding\\gayan\\Plan_xls_without\\ind-para-c85.xlsx", "production_plan", "ind-para-c85","ind"}, //fileSize>25KB
                { "C:\\Bio Data\\git\\Newman\\flink-test-data-seeding\\gayan\\Plan_xls_without\\ugl-ftml-unit1.xlsx", "production_plan", "ugl-ftml-unit1","ugl"}

        };
    }

    @Test(dataProvider= "factoryDataProvider")
    public void uploadProductionPlan(String filePath, String planType, String subjectKey, String tenant){

        File file = new File(filePath);

        Response response = given()
                .pathParam("planType",planType)
                .pathParam("subjectKey",subjectKey)
                .pathParam("tenant",tenant)
                .multiPart("file", file, "multipart/form-data")
                .post("https://nqa-n-data-seeding.strawmine.com/data_seeding_services/api/v1/dataSeeding?planType={planType}&subjectKey={subjectKey}&tenant={tenant}");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200); // verify status code is 200

        //System.out.println(response.prettyPrint());

    }

    @Test(dataProvider= "factoryDataProvider")
    public void uploadProductionPlan2(String filePath, String planType, String subjectKey, String tenant){

        File file = new File(filePath);

                given()
                .pathParam("planType",planType)
                .pathParam("subjectKey",subjectKey)
                .pathParam("tenant",tenant)
                .multiPart("file", file, "multipart/form-data")
                .post("https://nqa-n-data-seeding.strawmine.com/data_seeding_services/api/v1/dataSeeding?planType={planType}&subjectKey={subjectKey}&tenant={tenant}")
                .then()
                        .body("$.size_list_mapping.insert", equalTo("success")) // verify job
                        .statusCode(200) // verify status code is 200
                //.log().all()
                ;

    }


    @Test(dataProvider= "factoryDataProvider")
    public void uploadProductionPlan3(String filePath, String planType, String subjectKey, String tenant){

        File file = new File(filePath);

        Response response = given()
                .pathParam("planType",planType)
                .pathParam("subjectKey",subjectKey)
                .pathParam("tenant",tenant)
                .multiPart("file", file, "multipart/form-data")
                .post("https://nqa-n-data-seeding.strawmine.com/data_seeding_services/api/v1/dataSeeding?planType={planType}&subjectKey={subjectKey}&tenant={tenant}");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200); // verify status code is 200

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        JsonPath jsonPathEvaluator = response.jsonPath();

        String city = jsonPathEvaluator.get("size_list_mapping");
        System.out.println(city);

        //System.out.println(response.prettyPrint());

    }
}
