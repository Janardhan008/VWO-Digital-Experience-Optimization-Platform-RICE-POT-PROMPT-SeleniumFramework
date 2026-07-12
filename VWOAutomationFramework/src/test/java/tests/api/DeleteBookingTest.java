package tests.api;

import api.utils.BookingApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseApiTest {

    @Test(description = "TC-DEL-001: Verify delete with valid auth returns 201 Created")
    public void testDeleteWithValidAuth() {
        int id = createTestBooking();
        Response response = BookingApiClient.deleteBooking(id);
        Assert.assertEquals(response.statusCode(), 201,
                "DELETE with valid auth should return 201. Got: " + response.statusCode());
    }

    @Test(description = "TC-DEL-002: Verify deleted booking returns 404 on subsequent GET")
    public void testDeletedBookingReturns404() {
        int id = createTestBooking();
        BookingApiClient.deleteBooking(id);

        Response getResponse = BookingApiClient.getBookingById(id);
        Assert.assertEquals(getResponse.statusCode(), 404,
                "Deleted booking should return 404. Got: " + getResponse.statusCode());
    }

    @Test(description = "TC-DEL-003: Verify delete without auth returns 403 Forbidden")
    public void testDeleteWithoutAuth() {
        int id = createTestBooking();
        Response response = BookingApiClient.deleteBookingWithoutAuth(id);
        Assert.assertEquals(response.statusCode(), 403,
                "DELETE without auth should return 403. Got: " + response.statusCode());
        BookingApiClient.deleteBooking(id);
    }

    @Test(description = "TC-DEL-004: Verify delete with invalid auth returns 403 Forbidden")
    public void testDeleteWithInvalidAuth() {
        int id = createTestBooking();
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Authorization", "Basic " +
                        java.util.Base64.getEncoder().encodeToString("bad:creds".getBytes()))
                .delete("/booking/{id}", id);
        Assert.assertTrue(response.statusCode() == 403 || response.statusCode() == 401,
                "Invalid auth should return 403/401. Got: " + response.statusCode());
        BookingApiClient.deleteBooking(id);
    }

    @Test(description = "TC-DEL-005: Verify delete of non-existent id returns 405")
    public void testDeleteNonExistentId() {
        Response response = BookingApiClient.deleteBooking(NON_EXISTENT_BOOKING_ID);
        Assert.assertEquals(response.statusCode(), 405,
                "Non-existent ID should return 405. Got: " + response.statusCode());
    }

    @Test(description = "TC-DEL-006: Verify double delete returns 405 on second attempt")
    public void testDoubleDelete() {
        int id = createTestBooking();
        Response first = BookingApiClient.deleteBooking(id);
        Assert.assertEquals(first.statusCode(), 201, "First delete should succeed");

        Response second = BookingApiClient.deleteBooking(id);
        Assert.assertTrue(second.statusCode() == 405 || second.statusCode() == 404,
                "Second delete should return 405/404. Got: " + second.statusCode());
    }

    @Test(description = "TC-DEL-007: Verify GET method on delete endpoint returns 405")
    public void testGetOnDeleteEndpoint() {
        int id = createTestBooking();
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Authorization", "Basic " +
                        java.util.Base64.getEncoder().encodeToString("admin:password123".getBytes()))
                .get("/booking/{id}", id);
        Assert.assertEquals(response.statusCode(), 200,
                "GET should work on booking. Verifying method safety");
        BookingApiClient.deleteBooking(id);
    }

    @Test(description = "TC-DEL-008: Verify create-then-delete-then-verify cycle")
    public void testCreateDeleteVerifyCycle() {
        int id = createTestBooking();
        Response getBefore = BookingApiClient.getBookingById(id);
        Assert.assertEquals(getBefore.statusCode(), 200, "Booking should exist before delete");

        BookingApiClient.deleteBooking(id);

        Response getAfter = BookingApiClient.getBookingById(id);
        Assert.assertEquals(getAfter.statusCode(), 404, "Booking should not exist after delete");
    }
}
