package tests.api;

import api.pojo.*;
import api.utils.BookingApiClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BaseApiTest {

    protected static final int VALID_BOOKING_ID = 1;
    protected static final int NON_EXISTENT_BOOKING_ID = 99999;
    protected static final int INVALID_NEGATIVE_ID = -1;
    protected static final String INVALID_STRING_ID = "abc";

    protected static final String AUTH_USERNAME = "admin";
    protected static final String AUTH_PASSWORD = "password123";

    protected static final BookingDates defaultDates = new BookingDates("2026-07-01", "2026-07-15");
    protected static final Booking defaultBooking = new Booking("John", "Smith", 250, true, defaultDates, "Breakfast");

    @BeforeSuite
    public void apiBeforeSuite() {
        System.out.println("========================================");
        System.out.println("  Booking API Test Suite");
        System.out.println("  Base URL: https://restful-booker.herokuapp.com");
        System.out.println("========================================");

        Response ping = BookingApiClient.healthCheck();
        System.out.println("  Health Check: " + ping.statusCode());
        System.out.println("========================================");
    }

    protected int createTestBooking() {
        Response response = BookingApiClient.createBooking(defaultBooking);
        assertTrue(response.statusCode() == 200, "Failed to create test booking");
        return response.jsonPath().getInt("bookingid");
    }

    protected void deleteTestBooking(int id) {
        BookingApiClient.deleteBooking(id);
    }

    protected void verifySuccessResponse(Response response) {
        assertTrue(response.statusCode() == 200 || response.statusCode() == 201,
                "Expected 200/201 but got " + response.statusCode());
        assertNotNull(response.body(), "Response body should not be null");
    }

    protected void verifyErrorResponse(Response response, int expectedStatusCode) {
        assertTrue(response.statusCode() == expectedStatusCode,
                "Expected " + expectedStatusCode + " but got " + response.statusCode());
    }

    protected void verifyBookingFields(Booking booking) {
        assertNotNull(booking.getFirstname(), "firstname should not be null");
        assertNotNull(booking.getLastname(), "lastname should not be null");
        assertTrue(booking.getTotalprice() > 0, "totalprice should be positive");
        assertNotNull(booking.getBookingdates(), "bookingdates should not be null");
        assertNotNull(booking.getBookingdates().getCheckin(), "checkin should not be null");
        assertNotNull(booking.getBookingdates().getCheckout(), "checkout should not be null");
    }

    protected void verifyBookingList(List<BookingId> bookings) {
        assertNotNull(bookings, "Booking list should not be null");
        assertTrue(bookings.size() > 0, "Booking list should not be empty");
        for (BookingId id : bookings) {
            assertTrue(id.getBookingid() > 0, "Each bookingid should be positive");
        }
    }
}
