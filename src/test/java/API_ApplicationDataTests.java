import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class API_ApplicationDataTests {
    private String token;
    @BeforeMethod
    public void setUpAPI() {
        token = loginAndGetToken();
    }
    private String loginAndGetToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 200, "Login request failed");

        String token = response.jsonPath().getString("token");
        assertNotNull(token, "Token is null or empty");
        assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");

        return token;
    }
    @Test
    public void testGetApplicationData() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Authorization", "Bearer " + token)
                .get("/api/data");
        assertEquals(response.getStatusCode(), 200, "Get app data, status code is not 200");
        //assertTrue(response.getBody().asString().contains("artists"), "Body does not contain 'artists'");
    }
    @Test
    public void testGetApplicationDataWrongMethod() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Authorization", "Bearer " + token)
                .post("/api/data");
        assertEquals(response.getStatusCode(), 405, "Get app data with wrong method, status code is not 405");
        assertTrue(response.getBody().asString().contains("Something is broken. Please let us know what you were doing when this error occurred."));
    }

    @Test
    public void testGetApplicationDataWithExpiredToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Authorization", "Bearer " + API_TestConfig.tokenExpired)
                .get("/api/data");
        assertEquals(response.getStatusCode(), 401, "Get app data with expired token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testGetApplicationDataWithIncorrectToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Authorization", "Bearer " + API_TestConfig.tokenIncorrect)
                .get("/api/data");
        assertEquals(response.getStatusCode(), 401, "Get app data with incorrect token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testGetApplicationDataWithLongToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Authorization", "Bearer " + API_TestConfig.tokenLong)
                .get("/api/data");
        assertEquals(response.getStatusCode(), 401, "Get app data with long token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testGetApplicationDataWithShortToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Authorization", "Bearer " + API_TestConfig.tokenShort)
                .get("/api/data");
        assertEquals(response.getStatusCode(), 401, "Get app data with short token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testGetApplicationDataNoToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .get("/api/data");
        assertEquals(response.getStatusCode(), 401, "Get app data with no token, status code is not 401");
        assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
}
