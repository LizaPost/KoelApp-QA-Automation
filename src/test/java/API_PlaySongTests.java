import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class API_PlaySongTests {
    private String token;

    @BeforeMethod
    public void setUpAPI() {
        token = loginAndGetToken();
    }
    private String loginAndGetToken() {
        String token = given()
                .baseUri(API_TestConfig.baseUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\"email\":\"" + API_TestConfig.validEmail + "\",\"password\":\"" + API_TestConfig.validPassword + "\"}")
                .post("/api/me")
                .then()
                .statusCode(200)
                .extract().path("token");

        assertNotNull(token, "Token is null or empty");
        assertTrue(token.matches("[a-zA-Z0-9|]+"), "Token has invalid format");

        return token;
    }
    private String getApiUrl(String songId, String token) {
        return API_TestConfig.baseUrl + "/play/" + songId + "?api_token=" + token;
    }
    @Test
    public void testPlaySongWithCorrectSongID() {
        assertPlaySong(API_TestConfig.songIdCorrect, token, 200, "It's Your Birthday!");
    }
    @Test
    public void testPlaySongWithIncorrectSongID() {
        assertPlaySong(API_TestConfig.songIdIncorrect, token, 404, "Requested song does not exist");
    }
    @Test
    public void testPlaySongWithLongSongID() {
        assertPlaySong(API_TestConfig.songIdLong, token, 404, "songId is too long");
    }
    @Test
    public void testPlaySongWithShortSongID() {
        assertPlaySong(API_TestConfig.songIdShort, token, 404, "songId is too short");
    }
    @Test
    public void testPlaySongWithNoSongID() {
        assertPlaySong("", token, 404, "songId is missed");
    }
    @Test
    public void testPlaySongWithExpiredToken() {
        assertPlaySong(API_TestConfig.songIdCorrect, API_TestConfig.tokenExpired, 401, "Token expired");
    }
    @Test
    public void testPlaySongWithIncorrectToken() {
        assertPlaySong(API_TestConfig.songIdCorrect, API_TestConfig.tokenIncorrect, 401, "Token incorrect");
    }
    @Test
    public void testPlaySongWithLongToken() {
        assertPlaySong(API_TestConfig.songIdCorrect, API_TestConfig.tokenLong, 401, "Token is too long");
    }
    @Test
    public void testPlaySongWithShortToken() {
        assertPlaySong(API_TestConfig.songIdCorrect, API_TestConfig.tokenShort, 401, "Token is too short");
    }
    @Test
    public void testPlaySongWithNoToken() {
        assertPlaySong(API_TestConfig.songIdCorrect, API_TestConfig.tokenMissed, 401, "Token is missed");
    }
    @Test
    public void testPlaySongWithIncorrectMethod() {
        assertPlaySongWithIncorrectMethod(API_TestConfig.songIdCorrect, token);
    }
    private void assertPlaySong(String songId, String token, int expectedStatusCode, String expectedResponseBody) {
        String url = getApiUrl(songId, token);
        Response response = given().get(url);

        int playSongStatusCode = response.getStatusCode();
        assertEquals(playSongStatusCode, expectedStatusCode, "Unexpected status code");

        if (expectedResponseBody != null && !expectedResponseBody.isEmpty()) {
            String playSongResponseBody = response.getBody().asString();
            assertTrue(playSongResponseBody.contains(expectedResponseBody), "Body does not contain expected content");
        }
    }
    private void assertPlaySongWithIncorrectMethod(String songId, String token) {
        String url = getApiUrl(songId, token);
        int playSongStatusCode = given().post(url).getStatusCode();
        assertEquals(playSongStatusCode, 405, "Expected status code 405 Method Not Allowed");
    }
}
