# gft
El código es una aplicación de servidor simple que utiliza la biblioteca MuServer para manejar las solicitudes HTTP. Esta aplicación permite a los clientes hacer reservas en un restaurante y al propietario del restaurante ver todas las reservas para un día en particular.

1.-Se crea un mapa estático bookings para almacenar las reservas. La clave es una cadena que representa la fecha de la reserva y el valor es una lista de cadenas que representan las direcciones IP de los clientes que han hecho una reserva para esa fecha.

2.-Se inicia un servidor HTTP con MuServerBuilder.httpServer().

3.-Se añade un manejador para las solicitudes POST a la ruta "/book". Cuando un cliente hace una solicitud a esta ruta, la aplicación lee la fecha de la reserva del cuerpo de la solicitud, añade la dirección IP del cliente a la lista de reservas para esa fecha en el mapa bookings, y envía una respuesta al cliente confirmando la reserva.

4.-Se añade un manejador para las solicitudes GET a la ruta "/bookings/:date". Cuando el propietario del restaurante hace una solicitud a esta ruta, la aplicación obtiene la fecha de la ruta, busca todas las reservas para esa fecha en el mapa bookings, y envía una respuesta al propietario del restaurante con la lista de reservas.

Finalmente, se inicia el servidor y se imprime la URI del servidor en la consola.

Para agregar el paquete io.muserver.* al proyecto Java, necesitas agregar la dependencia correspondiente a tu archivo de configuración de Maven o Gradle.

Si estás utilizando Maven, agrega la siguiente dependencia a tu archivo pom.xml:

<dependencies>
    <dependency>
        <groupId>io.muserver</groupId>
        <artifactId>mu-server</artifactId>
        <version>1.0.8</version>
    </dependency>
</dependencies>

Si estás utilizando Gradle, agrega la siguiente dependencia a tu archivo build.gradle:

dependencies {
    implementation 'io.muserver:mu-server:1.0.8'
}

Después de agregar la dependencia, ejecuta el comando de construcción de tu proyecto (mvn install para Maven o gradle build para Gradle) para descargar e instalar la biblioteca en tu proyecto.

Para probar la aplicación de reserva de restaurantes, puedes usar una biblioteca de pruebas unitarias como JUnit y una biblioteca para hacer solicitudes HTTP como OkHttp.
