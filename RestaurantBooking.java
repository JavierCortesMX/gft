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
    // Almacenar las reservas en un mapa, con la fecha como clave y una lista de reservas como valor
    private static Map<String, List<String>> bookings = new HashMap<>();

    public static void main(String[] args) {
        MuServer server = MuServerBuilder.httpServer()
            .addHandler(Method.POST, "/book", (request, response, pathParams) -> {
                // El cliente envía la fecha de la reserva en el cuerpo de la solicitud
                String date = request.readBodyAsString();
                // Añadir la reserva a la lista de reservas para esa fecha
                bookings.computeIfAbsent(date, k -> new ArrayList<>()).add(request.remoteAddress());
                response.write("Booking confirmed for " + date);
            })
            .addHandler(Method.GET, "/bookings/:date", (request, response, pathParams) -> {
                // El propietario del restaurante solicita las reservas para una fecha específica
                String date = pathParams.get("date");
                List<String> dateBookings = bookings.getOrDefault(date, Collections.emptyList());
                response.write("Bookings for " + date + ": " + dateBookings);
            })
            .start();
        System.out.println("Started server at " + server.uri());
    }
}
