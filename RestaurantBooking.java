/*
The task is to create a simple restaurant booking application in Java and implement the two user stories below.

 User Story 1: As a customer I want to be able to request a booking at this restaurant.

 User Story 2: As the restaurant owner I want to be able to see all bookings for a particular day.

Assume each booking has a customer name, table size, date and time. Assume time slots are for 2 hours.

The only technical requirement is to use https://muserver.io/ to implement a RESTful API. (You may not be familiar with this library, but that is part of the challenge).
*/

import io.muserver.*;
import java.util.*;

public class RestaurantBooking {
    // Store reservations on a map, with the date as the key and a list of reservations as the value
    private static Map<String, List<String>> bookings = new HashMap<>();

    public static void main(String[] args) {
        MuServer server = MuServerBuilder.httpServer()
            .addHandler(Method.POST, "/book", (request, response, pathParams) -> {
                // The customer sends the date of the reservation in the body of the request
                String date = request.readBodyAsString();
                // Add the reservation to the reservation list for that date
                bookings.computeIfAbsent(date, k -> new ArrayList<>()).add(request.remoteAddress());
                response.write("Booking confirmed for " + date);
            })
            .addHandler(Method.GET, "/bookings/:date", (request, response, pathParams) -> {
                // The restaurant owner requests reservations for a specific date
                String date = pathParams.get("date");
                List<String> dateBookings = bookings.getOrDefault(date, Collections.emptyList());
                response.write("Bookings for " + date + ": " + dateBookings);
            })
            .start();
        System.out.println("Started server at " + server.uri());
    }
}
