# gft
El código es una aplicación de servidor simple que utiliza la biblioteca MuServer para manejar las solicitudes HTTP. Esta aplicación permite a los clientes hacer reservas en un restaurante y al propietario del restaurante ver todas las reservas para un día en particular.

1.-Se crea un mapa estático bookings para almacenar las reservas. La clave es una cadena que representa la fecha de la reserva y el valor es una lista de cadenas que representan las direcciones IP de los clientes que han hecho una reserva para esa fecha.

2.-Se inicia un servidor HTTP con MuServerBuilder.httpServer().

3.-Se añade un manejador para las solicitudes POST a la ruta "/book". Cuando un cliente hace una solicitud a esta ruta, la aplicación lee la fecha de la reserva del cuerpo de la solicitud, añade la dirección IP del cliente a la lista de reservas para esa fecha en el mapa bookings, y envía una respuesta al cliente confirmando la reserva.

4.-Se añade un manejador para las solicitudes GET a la ruta "/bookings/:date". Cuando el propietario del restaurante hace una solicitud a esta ruta, la aplicación obtiene la fecha de la ruta, busca todas las reservas para esa fecha en el mapa bookings, y envía una respuesta al propietario del restaurante con la lista de reservas.

Finalmente, se inicia el servidor y se imprime la URI del servidor en la consola.
