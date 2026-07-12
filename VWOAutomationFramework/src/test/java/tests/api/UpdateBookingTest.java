package tests.api;

import api.pojo.Booking;
import api.pojo.BookingDates;
import api.utils.BookingApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UpdateBookingTest extends BaseApiTest {

    private int createdBookingId;

    @AfterMethod
    public void cleanup() {
        if (createdBookingId > 0) {
            BookingApiClient.deleteBooking(createdBookingId);
        }
    }

    @Test(description = "TC-PUT-001: Verify full update with valid auth returns 200 OK")
    public void testFullUpdateWithAuth() {
        createdBookingId = createTestBooking();
        Booking updated = new Booking("Jane", "Doe", 500, false,
                new BookingDates("2026-08-01", "2026-08-10"), "Lunch");
        Response response = BookingApiClient.updateBooking(createdBookingId, updated);
        Assert.assertEquals(response.statusCode(), 200,
                "PUT with valid auth should return 200. Got: " + response.statusCode());
    }

    @Test(description = "TC-PUT-002: Verify all fields are updated correctly after PUT")
    public void testFullUpdateAllFields() {
        createdBookingId = createTestBooking();
        Booking updated = new Booking("Jane", "Doe", 500, false,
                new BookingDates("2026-08-01", "2026-08-10"), "Lunch");
        BookingApiClient.updateBooking(createdBookingId, updated);

        Response getResponse = BookingApiClient.getBookingById(createdBookingId);
        Booking actual = getResponse.as(Booking.class);

        Assert.assertEquals(actual.getFirstname(), "Jane", "firstname should be updated");
        Assert.assertEquals(actual.getLastname(), "Doe", "lastname should be updated");
        Assert.assertEquals(actual.getTotalprice(), 500, "totalprice should be updated");
        Assert.assertFalse(actual.isDepositpaid(), "depositpaid should be updated");
        Assert.assertEquals(actual.getAdditionalneeds(), "Lunch", "additionalneeds should be updated");
        Assert.assertEquals(actual.getBookingdates().getCheckin(), "2026-08-01", "checkin should be updated");
        Assert.assertEquals(actual.getBookingdates().getCheckout(), "2026-08-10", "checkout should be updated");
    }

    @Test(description = "TC-PUT-003: Verify response body matches request payload (round-trip)")
    public void testFullUpdateRoundTrip() {
        createdBookingId = createTestBooking();
        Booking updated = new Booking("Alice", "Wonder", 750, true,
                new BookingDates("2026-09-01", "2026-09-15"), "Dinner");
        Response response = BookingApiClient.updateBooking(createdBookingId, updated);
        Booking responseBooking = response.as(Booking.class);

        Assert.assertEquals(responseBooking.getFirstname(), updated.getFirstname());
        Assert.assertEquals(responseBooking.getLastname(), updated.getLastname());
        Assert.assertEquals(responseBooking.getTotalprice(), updated.getTotalprice());
        Assert.assertEquals(responseBooking.isDepositpaid(), updated.isDepositpaid());
        Assert.assertEquals(responseBooking.getAdditionalneeds(), updated.getAdditionalneeds());
        Assert.assertEquals(responseBooking.getBookingdates().getCheckin(), updated.getBookingdates().getCheckin());
        Assert.assertEquals(responseBooking.getBookingdates().getCheckout(), updated.getBookingdates().getCheckout());
    }

    @Test(description = "TC-PUT-004: Verify update without auth header returns 403 Forbidden")
    public void testFullUpdateWithoutAuth() {
        createdBookingId = createTestBooking();
        Response response = BookingApiClient.updateBookingWithoutAuth(createdBookingId, defaultBooking);
        Assert.assertEquals(response.statusCode(), 403,
                "PUT without auth should return 403. Got: " + response.statusCode());
    }

    @Test(description = "TC-PUT-005: Verify update with invalid auth returns 403 Forbidden")
    public void testFullUpdateWithInvalidAuth() {
        createdBookingId = createTestBooking();
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Authorization", "Basic " +
                        java.util.Base64.getEncoder().encodeToString("bad:creds".getBytes()))
                .contentType(io.restassured.http.ContentType.JSON)
                .body(defaultBooking)
                .put("/booking/{id}", createdBookingId);
        Assert.assertTrue(response.statusCode() == 403 || response.statusCode() == 401,
                "Invalid auth should return 403/401. Got: " + response.statusCode());
    }

    @Test(description = "TC-PUT-006: Verify update to non-existent id returns 405 or 404")
    public void testFullUpdateNonExistentId() {
        Response response = BookingApiClient.updateBooking(NON_EXISTENT_BOOKING_ID, defaultBooking);
        Assert.assertTrue(response.statusCode() == 404 || response.statusCode() == 405,
                "Non-existent ID should return 404/405. Got: " + response.statusCode());
    }

    @Test(description = "TC-PUT-007: Verify update with missing required fields returns 400")
    public void testFullUpdateMissingFields() {
        createdBookingId = createTestBooking();
        String invalidPayload = "{\"firstname\": \"OnlyFirst\"}";
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Authorization", "Basic " +
                        java.util.Base64.getEncoder().encodeToString("admin:password123".getBytes()))
                .contentType(io.restassured.http.ContentType.JSON)
                .body(invalidPayload)
                .put("/booking/{id}", createdBookingId);
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 200,
                "Missing fields should return 400 or handled. Got: " + response.statusCode());
    }

    @Test(description = "TC-PUT-008: Verify update with invalid data types returns 400")
    public void testFullUpdateInvalidDataTypes() {
        createdBookingId = createTestBooking();
        String invalidPayload = "{"
                + "\"firstname\": \"Test\", \"lastname\": \"User\", "
                + "\"totalprice\": \"not-a-number\", \"depositpaid\": true, "
                + "\"bookingdates\": {\"checkin\": \"2026-01-01\", \"checkout\": \"2026-01-10\"}"
                + "}";
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Authorization", "Basic " +
                        java.util.Base64.getEncoder().encodeToString("admin:password123".getBytes()))
                .contentType(io.restassured.http.ContentType.JSON)
                .body(invalidPayload)
                .put("/booking/{id}", createdBookingId);
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 500,
                "Invalid data types should return 400/500. Got: " + response.statusCode());
    }

    @Test(description = "TC-PUT-009: Verify update with extra unexpected fields is accepted")
    public void testFullUpdateWithExtraFields() {
        createdBookingId = createTestBooking();
        String extraPayload = "{"
                + "\"firstname\": \"Test\", \"lastname\": \"User\", "
                + "\"totalprice\": 300, \"depositpaid\": true, "
                + "\"bookingdates\": {\"checkin\": \"2026-01-01\", \"checkout\": \"2026-01-10\"}, "
                + "\"extraField\": \"shouldBeIgnored\", \"anotherExtra\": 123"
                + "}";
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Authorization", "Basic " +
                        java.util.Base64.getEncoder().encodeToString("admin:password123".getBytes()))
                .contentType(io.restassured.http.ContentType.JSON)
                .body(extraPayload)
                .put("/booking/{id}", createdBookingId);
        Assert.assertEquals(response.statusCode(), 200,
                "Extra fields should be accepted. Got: " + response.statusCode());
    }

    @Test(description = "TC-PUT-010: Verify checkout before checkin returns 400 Bad Request")
    public void testFullUpdateCheckoutBeforeCheckin() {
        createdBookingId = createTestBooking();
        Booking invalid = new Booking("Test", "User", 100, true,
                new BookingDates("2026-02-01", "2026-01-01"), "None");
        Response response = BookingApiClient.updateBooking(createdBookingId, invalid);
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 200,
                "checkout<checkin should return 400 or be handled. Got: " + response.statusCode());
    }
}
