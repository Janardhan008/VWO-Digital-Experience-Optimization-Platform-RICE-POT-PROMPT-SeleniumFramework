package tests.api;

import api.utils.BookingApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HealthCheckTest extends BaseApiTest {

    @Test(description = "TC-PNG-001: Verify health check returns 201 Created with no body")
    public void testHealthCheckReturns201() {
        Response response = BookingApiClient.healthCheck();
        Assert.assertEquals(response.statusCode(), 201,
                "Health check should return 201 Created");
        Assert.assertTrue(response.body().asString().isEmpty() || response.body().asString() == null,
                "Health check body should be empty");
    }

    @Test(description = "TC-PNG-002: Verify response time is under 500ms")
    public void testHealthCheckResponseTime() {
        Response response = BookingApiClient.healthCheck();
        long time = response.timeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(time < 500,
                "Health check response should be under 500ms. Actual: " + time + "ms");
    }

    @Test(description = "TC-PNG-003: Verify endpoint responds without authentication")
    public void testHealthCheckNoAuth() {
        Response response = BookingApiClient.healthCheck();
        Assert.assertEquals(response.statusCode(), 201,
                "Health check should work without auth headers");
    }

    @Test(description = "TC-PNG-004: Verify endpoint does not accept POST/PUT/DELETE")
    public void testHealthCheckRejectsUnsupportedMethods() {
        String pingUrl = "https://restful-booker.herokuapp.com/ping";

        Response post = io.restassured.RestAssured.given().post(pingUrl);
        Assert.assertEquals(post.statusCode(), 405,
                "POST /ping should return 405. Got: " + post.statusCode());

        Response put = io.restassured.RestAssured.given().put(pingUrl);
        Assert.assertEquals(put.statusCode(), 405,
                "PUT /ping should return 405. Got: " + put.statusCode());

        Response delete = io.restassured.RestAssured.given().delete(pingUrl);
        Assert.assertEquals(delete.statusCode(), 405,
                "DELETE /ping should return 405. Got: " + delete.statusCode());
    }

    @Test(description = "TC-PNG-005: Verify health check after consecutive 10 rapid calls")
    public void testHealthCheckConsecutiveCalls() {
        for (int i = 0; i < 10; i++) {
            Response response = BookingApiClient.healthCheck();
            Assert.assertEquals(response.statusCode(), 201,
                    "Call " + (i + 1) + " should return 201");
        }
    }

    @Test(description = "TC-PNG-006: Verify response headers are present")
    public void testHealthCheckResponseHeaders() {
        Response response = BookingApiClient.healthCheck();
        Assert.assertNotNull(response.header("Content-Type"), "Content-Type header should be present");
        Assert.assertNotNull(response.header("Server"), "Server header should be present");
        Assert.assertNotNull(response.header("Date"), "Date header should be present");
    }
}
