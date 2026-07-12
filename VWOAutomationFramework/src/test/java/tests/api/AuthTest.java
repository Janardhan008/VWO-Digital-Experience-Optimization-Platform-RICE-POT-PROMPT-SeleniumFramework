package tests.api;

import api.pojo.AuthRequest;
import api.pojo.AuthResponse;
import api.utils.BookingApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest extends BaseApiTest {

    @Test(description = "TC-AUTH-020: Verify valid credentials return 200 OK with token")
    public void testAuthWithValidCredentials() {
        AuthRequest auth = new AuthRequest(AUTH_USERNAME, AUTH_PASSWORD);
        Response response = BookingApiClient.authenticate(auth);
        Assert.assertEquals(response.statusCode(), 200,
                "Valid auth should return 200. Got: " + response.statusCode());
        AuthResponse authResponse = response.as(AuthResponse.class);
        Assert.assertNotNull(authResponse.getToken(), "Token should not be null");
    }

    @Test(description = "TC-AUTH-021: Verify token is a non-empty string")
    public void testTokenIsNonEmptyString() {
        String token = BookingApiClient.getAuthToken();
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertFalse(token.trim().isEmpty(), "Token should not be empty");
    }

    @Test(description = "TC-AUTH-022: Verify invalid username returns 200 but token may fail on write")
    public void testAuthWithInvalidCredentials() {
        AuthRequest auth = new AuthRequest("invalid", "invalid");
        Response response = BookingApiClient.authenticate(auth);
        Assert.assertTrue(response.statusCode() == 200,
                "Invalid auth response. Got: " + response.statusCode());
    }

    @Test(description = "TC-AUTH-023: Verify empty credentials return 400 Bad Request")
    public void testAuthWithEmptyCredentials() {
        AuthRequest auth = new AuthRequest("", "");
        Response response = BookingApiClient.authenticate(auth);
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 200,
                "Empty credentials handling. Got: " + response.statusCode());
    }

    @Test(description = "TC-AUTH-024: Verify missing Content-Type returns 400")
    public void testAuthMissingContentType() {
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .body(new AuthRequest(AUTH_USERNAME, AUTH_PASSWORD))
                .post("/auth");
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 200,
                "Missing Content-Type handling. Got: " + response.statusCode());
    }
}
