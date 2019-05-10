# cinema-tickets
Ticket Booking App - TouK recruitment task in Spring Boot

### What's in use
- JDK 11.0.2
- PostgreSQL database

### Build and run with Gradle
After cloning or downloading project, it can be built with:
#### Linux
``` ./gradlew build ```
#### Windows
``` ./gradlew.bat build ```

As the build is finished, run the project with:  
```java -jar build/libs/cinema-tickets-0.0.1-SNAPSHOT.jar```

Hibernate exceptions may appear due to ```spring.jpa.hibernate.ddl-auto=create-drop``` setting, but the application in running correctly

### Demo
Project contains demo script. It can be run with:
#### Linux
```sh demo/ticket-booking```
#### Windows (Powershell)
```.\demo\ticket-booking.bat```