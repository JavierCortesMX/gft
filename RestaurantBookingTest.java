/*
setUp starts the server before each test, and tearDown stops it after each test. testBooking tests that a customer can make a reservation, and testGetBookings tests that the restaurant owner can obtain reservations for a specific date.
*/

import io.muserver.*;
import okhttp3.*;
import org.junit.*;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class RestaurantBookingTest {
    private MuServer server;
    private OkHttpClient client = new OkHttpClient();

    @Before
    public void setUp() {
        server = RestaurantBooking.startServer();
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testBooking() throws IOException {
        Request request = new Request.Builder()
            .url(server.uri().resolve("/book").toString())
            .post(RequestBody.create("2022-12-31", MediaType.parse("text/plain")))
            .build();
        try (Response response = client.newCall(request).execute()) {
            assertEquals("Booking confirmed for 2022-12-31", response.body().string());
        }
    }

    @Test
    public void testGetBookings() throws IOException {
        // First, make a reservation
        Request bookingRequest = new Request.Builder()
            .url(server.uri().resolve("/book").toString())
            .post(RequestBody.create("2022-12-31", MediaType.parse("text/plain")))
            .build();
        client.newCall(bookingRequest).execute();

        // Then get the reservations for that date
        Request getBookingsRequest = new Request.Builder()
            .url(server.uri().resolve("/bookings/2022-12-31").toString())
            .get()
            .build();
        try (Response response = client.newCall(getBookingsRequest).execute()) {
            assertTrue(response.body().string().contains("2022-12-31"));
        }
    }
}
