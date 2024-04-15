# gft
The code is a simple server application that uses the MuServer library to handle HTTP requests. This app allows customers to make reservations at a restaurant and the restaurant owner to view all reservations for a particular day.

1.-A static bookings map is created to store the reservations. The key is a string representing the date of the reservation and the value is a list of strings representing the IP addresses of customers who have made a reservation for that date.

2.-An HTTP server is started with MuServerBuilder.httpServer().

3.-A handler for POST requests is added to the "/book" route. When a client makes a request to this route, the application reads the reservation date from the request body, adds the client's IP address to the list of reservations for that date in the bookings map, and sends a response to the client confirming the reserve.

4.-A handler for GET requests is added to the "/bookings/:date" route. When the restaurant owner makes a request to this route, the application obtains the date of the route, searches for all reservations for that date on the bookings map, and sends a response to the restaurant owner with the list of reservations.

Finally, the server is started and the server URI is printed to the console.

To add the io.muserver.* package to your Java project, you need to add the corresponding dependency to your Maven or Gradle configuration file.

If you are using Maven, add the following dependency to your pom.xml file:

```
<dependencies>
    <dependency>
        <groupId>io.muserver</groupId>
        <artifactId>mu-server</artifactId>
        <version>1.0.8</version>
    </dependency>
</dependencies>
```
If you are using Gradle, add the following dependency to your build.gradle file:
```
dependencies {
    implementation 'io.muserver:mu-server:1.0.8'
}
```
After adding the dependency, run your project build command (mvn install for Maven or gradle build for Gradle) to download and install the library in your project.

To test your restaurant reservation app, you can use a unit testing library like JUnit and an HTTP request library like OkHttp.
