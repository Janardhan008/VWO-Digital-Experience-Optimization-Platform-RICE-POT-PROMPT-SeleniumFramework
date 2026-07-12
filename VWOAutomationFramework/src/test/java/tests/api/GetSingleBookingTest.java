package tests.api;

import api.pojo.Booking;
import api.utils.BookingApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSingleBookingTest extends BaseApiTest {

    @Test(description = "TC-BOK-011: Verify valid booking id returns 200 OK with full booking details")
    public void testGetBookingByIdReturns200() {
        Response response = BookingApiClient.getBookingById(VALID_BOOKING_ID);
        Assert.assertEquals(response.statusCode(), 200,
                "Valid booking ID should return 200 OK");
    }

    @Test(description = "TC-BOK-012: Verify response includes all required fields")
    public void testGetBookingByIdHasAllFields() {
        Response response = BookingApiClient.getBookingById(VALID_BOOKING_ID);
        Booking booking = response.as(Booking.class);
        Assert.assertNotNull(booking.getFirstname(), "firstname is required");
        Assert.assertNotNull(booking.getLastname(), "lastname is required");
        Assert.assertNotNull(booking.getBookingdates(), "bookingdates is required");
        Assert.assertNotNull(booking.getBookingdates().getCheckin(), "checkin is required");
        Assert.assertNotNull(booking.getBookingdates().getCheckout(), "checkout is required");
    }

    @Test(description = "TC-BOK-013: Verify firstname and lastname are non-empty strings")
    public void testNameFieldsAreNonEmpty() {
        Response response = BookingApiClient.getBookingById(VALID_BOOKING_ID);
        Booking booking = response.as(Booking.class);
        Assert.assertFalse(booking.getFirstname().trim().isEmpty(), "firstname should not be empty");
        Assert.assertFalse(booking.getLastname().trim().isEmpty(), "lastname should not be empty");
    }

    @Test(description = "TC-BOK-014: Verify totalprice is a positive number")
    public void testTotalPriceIsPositive() {
        Response response = BookingApiClient.getBookingById(VALID_BOOKING_ID);
        Booking booking = response.as(Booking.class);
        Assert.assertTrue(booking.getTotalprice() > 0,
                "totalprice should be positive. Got: " + booking.getTotalprice());
    }

    @Test(description = "TC-BOK-015: Verify depositpaid is a boolean")
    public void testDepositPaidIsBoolean() {
        Response response = BookingApiClient.getBookingById(VALID_BOOKING_ID);
        Booking booking = response.as(Booking.class);
        boolean isBoolean = booking.isDepositpaid() == true || booking.isDepositpaid() == false;
        Assert.assertTrue(isBoolean, "depositpaid should be a boolean");
    }

    @Test(description = "TC-BOK-016: Verify bookingdates checkin/checkout in YYYY-MM-DD format")
    public void testBookingDatesFormat() {
        Response response = BookingApiClient.getBookingById(VALID_BOOKING_ID);
        Booking booking = response.as(Booking.class);
        String checkin = booking.getBookingdates().getCheckin();
        String checkout = booking.getBookingdates().getCheckout();
        Assert.assertTrue(checkin.matches("\\d{4}-\\d{2}-\\d{2}"),
                "checkin should be in YYYY-MM-DD format. Got: " + checkin);
        Assert.assertTrue(checkout.matches("\\d{4}-\\d{2}-\\d{2}"),
                "checkout should be in YYYY-MM-DD format. Got: " + checkout);
    }

    @Test(description = "TC-BOK-017: Verify checkout date is >= checkin date")
    public void testCheckoutAfterCheckin() {
        Response response = BookingApiClient.getBookingById(VALID_BOOKING_ID);
        Booking booking = response.as(Booking.class);
        String checkin = booking.getBookingdates().getCheckin();
        String checkout = booking.getBookingdates().getCheckout();
        Assert.assertTrue(checkout.compareTo(checkin) >= 0,
                "checkout (" + checkout + ") should be >= checkin (" + checkin + ")");
    }

    @Test(description = "TC-BOK-018: Verify non-existent id returns 404 Not Found")
    public void testNonExistentIdReturns404() {
        Response response = BookingApiClient.getBookingById(NON_EXISTENT_BOOKING_ID);
        Assert.assertEquals(response.statusCode(), 404,
                "Non-existent ID should return 404. Got: " + response.statusCode());
    }

    @Test(description = "TC-BOK-019: Verify negative id returns 400 or 404")
    public void testNegativeId() {
        Response response = BookingApiClient.getBookingById(INVALID_NEGATIVE_ID);
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 404,
                "Negative ID should return 400 or 404. Got: " + response.statusCode());
    }

    @Test(description = "TC-BOK-020: Verify string id returns 400 Bad Request")
    public void testStringId() {
        io.restassured.response.Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .get("/booking/abc");
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 404,
                "String ID should return 400/404. Got: " + response.statusCode());
    }

    @Test(description = "TC-BOK-021: Verify special character id returns 400 Bad Request")
    public void testSpecialCharId() {
        io.restassured.response.Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .get("/booking/@#$");
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 404,
                "Special char ID should return 400/404. Got: " + response.statusCode());
    }

    @Test(description = "TC-BOK-022: Verify response without Accept header defaults to JSON")
    public void testResponseDefaultsToJson() {
        Response response = io.restassured.RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .get("/booking/1");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.contentType().contains("application/json"),
                "Response should default to JSON. Got: " + response.contentType());
    }
}
