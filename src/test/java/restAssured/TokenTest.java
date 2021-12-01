package restAssured;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TokenTest {

    public static class AccessToken {

        public static String getAccessToken(String url, String clientId, String clientSecret, String username, String password){

            Response response =
                    given().auth().preemptive().basic(clientId, clientSecret)
                            .formParam("grant_type", "password")
                            .formParam("username", username)
                            .formParam("password", password)
                            .when()
                            .post(url);

            String accessToken = response.getBody().path("access_token").toString();
            String tokenType = response.getBody().path("token_type").toString();

            String token = tokenType + " " + accessToken;

            System.out.println("Oauth token ::: "+ token);
            return token;
        }

        @Test
        public void tokenTest(){
            String token = getAccessToken("https://qa.eshrewd.net/auth-service/oauth/token", "trusted", "secret", "Diego", "test");
            //System.out.println(token);
        }
    }

}
