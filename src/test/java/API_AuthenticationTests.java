import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class API_AuthenticationTests {
    private String token;
    @BeforeMethod
    public void setUpAPI() {
        RestAssured.baseURI = API_TestConfig.baseUrl;
    }
    @Test
    public void testUserLoginWithCorrectCredentials() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 200, "Login with correct creds, status code is not 401");
        String token = response.jsonPath().getString("token");

        assertNotNull(token, "Token is null or empty");
        assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");

        System.out.println("Token: " + token);
    }
    @Test
    public void testUserLoginWithWrongEmail() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"email\":\"" + API_TestConfig.wrongEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 401, "Login with wrong email, status code is not 401");
        assertTrue(response.getBody().asString().contains("Invalid credentials"), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithInvalidEmail() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"email\":\"" + API_TestConfig.invalidEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 422, "Login with invalid email, status code is not 422");
        assertTrue(response.getBody().asString().contains("The email must be a valid email address."), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithNoEmail() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"email\":\"" + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 422, "Login with no email, status code is not 422");
        assertTrue(response.getBody().asString().contains("The email field is required."), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithWrongPassword() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.wrongPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 401, "Login with wrong password, status code is not 401");
        assertTrue(response.getBody().asString().contains("Invalid credentials"), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithNoPassword() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 422, "Login with no password, status code is not 422");
        assertTrue(response.getBody().asString().contains("The password field is required."), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithExpiredToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_TestConfig.tokenExpired)
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 401, "Login with expired token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithIncorrectToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_TestConfig.tokenIncorrect)
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 401, "Login with incorrect token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithLongToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_TestConfig.tokenLong)
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 401, "Login with long token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithShortToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_TestConfig.tokenShort)
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 401, "Login with short token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithNoToken() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me");

        assertEquals(response.getStatusCode(), 401, "Login with no token, status code is not 401");
        //assertTrue(response.getBody().asString().contains("It may sound funny, but Koel requires JavaScript to sing. Please enable it."), "Body does not contain expected error message");
    }
    @Test
    public void testUserLoginWithWrongMethod() {
        Response response = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .patch("/api/me");

        assertEquals(response.getStatusCode(), 405, "Login with wrong method, status code is not 405");
        assertTrue(response.getBody().asString().contains("The PATCH method is not supported for this route. Supported methods: GET, HEAD, POST, PUT, DELETE."), "Body does not contain expected error message");
    }
}
