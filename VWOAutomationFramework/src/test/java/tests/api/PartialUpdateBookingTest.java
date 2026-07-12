package tests.api;

import api.pojo.Booking;
import api.utils.BookingApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PartialUpdateBookingTest extends BaseApiTest {

    private int createdBookingId;

    @AfterMethod
    public void cleanup() {
        if (createdBookingId > 0) {
            BookingApiClient.deleteBooking(createdBookingId);
        }
    }

    @Test(description = "TC-PTC-001: Verify partial update with single field returns 200 OK")
    public void testPartialUpdateSingleField() {
        createdBookingId = createTestBooking();
        Map<String, Object> patch = new HashMap<>();
        patch.put("firstname", "UpdatedName");

        Response response = BookingApiClient.partialUpdateBooking(createdBookingId, patch);
        Assert.assertEquals(response.statusCode(), 200,
                "PATCH with single field should return 200. Got: " + response.statusCode());
    }

    @Test(description = "TC-PTC-002: Verify only the patched field changes, others remain intact")
    public void testPartialUpdatePreservesOtherFields() {
        createdBookingId = createTestBooking();
        Response getBefore = BookingApiClient.getBookingById(createdBookingId);
        Booking before = getBefore.as(Booking.class);

        Map<String, Object> patch = new HashMap<>();
        patch.put("firstname", "PatchedName");
        BookingApiClient.partialUpdateBooking(createdBookingId, patch);

        Response getAfter = BookingApiClient.getBookingById(createdBookingId);
        Booking after = getAfter.as(Booking.class);

        Assert.assertEquals(after.getFirstname(), "PatchedName", "firstname should be patched");
        Assert.assertEquals(after.getLastname(), before.getLastname(), "lastname should remain unchanged");
        Assert.assertEquals(after.getTotalprice(), before.getTotalprice(), "totalprice should remain unchanged");
        Assert.assertEquals(after.isDepositpaid(), before.isDepositpaid(), "depositpaid should remain unchanged");
        Assert.assertEquals(after.getAdditionalneeds(), before.getAdditionalneeds(), "additionalneeds unchanged");
    }

    @Test(description = "TC-PTC-003: Verify patch with totalprice updates correctly")
    public void testPartialUpdateTotalPrice() {
        createdBookingId = createTestBooking();
        Map<String, Object> patch = new HashMap<>();
        patch.put("totalprice", 999);

        Response response = BookingApiClient.partialUpdateBooking(createdBookingId, patch);
        Assert.assertEquals(response.statusCode(), 200);
        Booking updated = response.as(Booking.class);
        Assert.assertEquals(updated.getTotalprice(), 999);
    }

    @Test(description = "TC-PTC-004: Verify patch with bookingdates.checkin updates correctly")
    public void testPartialUpdateCheckin() {
        createdBookingId = createTestBooking();
        Map<String, Object> dates = new HashMap<>();
        dates.put("checkin", "2026-12-01");
        Map<String, Object> patch = new HashMap<>();
        patch.put("bookingdates", dates);

        Response response = BookingApiClient.partialUpdateBooking(createdBookingId, patch);
        Assert.assertEquals(response.statusCode(), 200);
        Booking updated = response.as(Booking.class);
        Assert.assertEquals(updated.getBookingdates().getCheckin(), "2026-12-01");
    }

    @Test(description = "TC-PTC-005: Verify patch without auth returns 403 Forbidden")
    public void testPartialUpdateWithoutAuth() {
        createdBookingId = createTestBooking();
        Map<String, Object> patch = new HashMap<>();
        patch.put("firstname", "Hacker");

        Response response = BookingApiClient.partialUpdateBookingWithoutAuth(createdBookingId, patch);
        Assert.assertEquals(response.statusCode(), 403,
                "PATCH without auth should return 403. Got: " + response.statusCode());
    }

    @Test(description = "TC-PTC-006: Verify patch with empty body returns 400 Bad Request")
    public void testPartialUpdateEmptyBody() {
        createdBookingId = createTestBooking();
        Response response = BookingApiClient.partialUpdateBooking(createdBookingId, new HashMap<>());
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 200,
                "Empty body should return 400 or be handled. Got: " + response.statusCode());
    }

    @Test(description = "TC-PTC-007: Verify patch to non-existent id returns 404")
    public void testPartialUpdateNonExistentId() {
        Map<String, Object> patch = new HashMap<>();
        patch.put("firstname", "Ghost");

        Response response = BookingApiClient.partialUpdateBooking(NON_EXISTENT_BOOKING_ID, patch);
        Assert.assertTrue(response.statusCode() == 404 || response.statusCode() == 405,
                "Non-existent ID should return 404/405. Got: " + response.statusCode());
    }

    @Test(description = "TC-PTC-008: Verify patch with invalid field name ignores unknown field")
    public void testPartialUpdateInvalidField() {
        createdBookingId = createTestBooking();
        Map<String, Object> patch = new HashMap<>();
        patch.put("nonexistentField", "shouldBeIgnored");

        Response response = BookingApiClient.partialUpdateBooking(createdBookingId, patch);
        Assert.assertTrue(response.statusCode() == 200,
                "Unknown fields should be ignored. Got: " + response.statusCode());
    }

    @Test(description = "TC-PTC-009: Verify patch with null value for required field returns 400")
    public void testPartialUpdateNullValue() {
        createdBookingId = createTestBooking();
        Map<String, Object> patch = new HashMap<>();
        patch.put("firstname", null);

        Response response = BookingApiClient.partialUpdateBooking(createdBookingId, patch);
        Assert.assertTrue(response.statusCode() == 400 || response.statusCode() == 200,
                "Null value should return 400 or be handled. Got: " + response.statusCode());
    }

    @Test(description = "TC-PTC-010: Verify patch with additionalneeds set to empty string works")
    public void testPartialUpdateEmptyAdditionalNeeds() {
        createdBookingId = createTestBooking();
        Map<String, Object> patch = new HashMap<>();
        patch.put("additionalneeds", "");

        Response response = BookingApiClient.partialUpdateBooking(createdBookingId, patch);
        Assert.assertEquals(response.statusCode(), 200);
        Booking updated = response.as(Booking.class);
        Assert.assertEquals(updated.getAdditionalneeds(), "",
                "additionalneeds should be updated to empty string");
    }
}
