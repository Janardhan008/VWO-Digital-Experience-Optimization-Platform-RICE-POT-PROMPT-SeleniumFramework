package api.utils;

import api.pojo.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public final class BookingApiClient {

    private static final String AUTH_USERNAME = "admin";
    private static final String AUTH_PASSWORD = "password123";

    private BookingApiClient() {}

    public static Response healthCheck() {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .when()
                .get("/ping");
    }

    public static Response getAllBookings() {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .when()
                .get("/booking");
    }

    public static Response getAllBookingsWithFilter(String paramName, String paramValue) {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .queryParam(paramName, paramValue)
                .when()
                .get("/booking");
    }

    public static Response getBookingById(int id) {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .when()
                .get("/booking/{id}", id);
    }

    public static Response createBooking(Booking booking) {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .body(booking)
                .when()
                .post("/booking");
    }

    public static Response updateBooking(int id, Booking booking) {
        return given()
                .spec(BookingApiSpec.getAuthRequestSpec(AUTH_USERNAME, AUTH_PASSWORD))
                .body(booking)
                .when()
                .put("/booking/{id}", id);
    }

    public static Response updateBookingWithoutAuth(int id, Booking booking) {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .body(booking)
                .when()
                .put("/booking/{id}", id);
    }

    public static Response partialUpdateBooking(int id, Object partialPayload) {
        return given()
                .spec(BookingApiSpec.getAuthRequestSpec(AUTH_USERNAME, AUTH_PASSWORD))
                .body(partialPayload)
                .when()
                .patch("/booking/{id}", id);
    }

    public static Response partialUpdateBookingWithoutAuth(int id, Object partialPayload) {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .body(partialPayload)
                .when()
                .patch("/booking/{id}", id);
    }

    public static Response deleteBooking(int id) {
        return given()
                .spec(BookingApiSpec.getAuthRequestSpec(AUTH_USERNAME, AUTH_PASSWORD))
                .when()
                .delete("/booking/{id}", id);
    }

    public static Response deleteBookingWithoutAuth(int id) {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .when()
                .delete("/booking/{id}", id);
    }

    public static Response authenticate(AuthRequest authRequest) {
        return given()
                .spec(BookingApiSpec.getRequestSpec())
                .body(authRequest)
                .when()
                .post("/auth");
    }

    public static String getAuthToken() {
        AuthRequest auth = new AuthRequest(AUTH_USERNAME, AUTH_PASSWORD);
        return authenticate(auth)
                .then()
                .extract()
                .path("token");
    }
}
