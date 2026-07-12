package tests.api;

import api.pojo.BookingId;
import api.utils.BookingApiClient;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllBookingTest extends BaseApiTest {

    @Test(description = "TC-BOK-001: Verify endpoint returns 200 OK with array of booking IDs")
    public void testGetAllBookingsReturns200() {
        Response response = BookingApiClient.getAllBookings();
        Assert.assertEquals(response.statusCode(), 200,
                "GET /booking should return 200 OK");
    }

    @Test(description = "TC-BOK-002: Verify response body is a non-empty JSON array")
    public void testGetAllBookingsReturnsNonEmptyArray() {
        Response response = BookingApiClient.getAllBookings();
        List<BookingId> bookings = response.as(new TypeRef<List<BookingId>>() {});
        Assert.assertFalse(bookings.isEmpty(), "Booking list should not be empty");
    }

    @Test(description = "TC-BOK-003: Verify each object has bookingid as integer")
    public void testBookingIdsAreIntegers() {
        Response response = BookingApiClient.getAllBookings();
        List<BookingId> bookings = response.as(new TypeRef<List<BookingId>>() {});
        for (BookingId id : bookings) {
            Assert.assertTrue(id.getBookingid() > 0,
                    "bookingid should be positive integer. Got: " + id.getBookingid());
        }
    }

    @Test(description = "TC-BOK-004: Verify bookingid values are unique")
    public void testBookingIdsAreUnique() {
        Response response = BookingApiClient.getAllBookings();
        List<BookingId> bookings = response.as(new TypeRef<List<BookingId>>() {});
        long uniqueCount = bookings.stream().map(BookingId::getBookingid).distinct().count();
        Assert.assertEquals(uniqueCount, bookings.size(),
                "bookingid values should be unique. Duplicates detected");
    }

    @Test(description = "TC-BOK-005: Verify filtering by firstname returns matching results")
    public void testFilterByFirstname() {
        Response response = BookingApiClient.getAllBookingsWithFilter("firstname", "John");
        Assert.assertEquals(response.statusCode(), 200);
        List<BookingId> bookings = response.as(new TypeRef<List<BookingId>>() {});
        Assert.assertFalse(bookings.isEmpty(), "Should find bookings with firstname=John");
    }

    @Test(description = "TC-BOK-006: Verify filtering by lastname returns matching results")
    public void testFilterByLastname() {
        Response response = BookingApiClient.getAllBookingsWithFilter("lastname", "Smith");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "TC-BOK-007: Verify filtering by checkin date returns correct bookings")
    public void testFilterByCheckin() {
        Response response = BookingApiClient.getAllBookingsWithFilter("checkin", "2026-01-01");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "TC-BOK-008: Verify filtering by checkout date returns correct bookings")
    public void testFilterByCheckout() {
        Response response = BookingApiClient.getAllBookingsWithFilter("checkout", "2026-02-01");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "TC-BOK-009: Verify combined filters work correctly")
    public void testCombinedFilters() {
        Response response = BookingApiClient.getAllBookingsWithFilter("firstname", "John");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "TC-BOK-010: Verify invalid filter value returns empty array")
    public void testInvalidFilterReturnsEmpty() {
        Response response = BookingApiClient.getAllBookingsWithFilter("firstname", "NonExistentName_XYZ");
        Assert.assertEquals(response.statusCode(), 200);
        List<BookingId> bookings = response.as(new TypeRef<List<BookingId>>() {});
        Assert.assertTrue(bookings.isEmpty(),
                "Non-matching filter should return empty array");
    }
}
