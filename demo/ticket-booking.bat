@echo off
echo TICKET BOOKING APP DEMO by KRZYSZTOF WILK
echo GET SCREENINGS IN BETWEEN 25.05.2019 20:00 AND 27.05.2019 10:00
curl "http://localhost:8080/api/screenings?start=2019-05-25T20:00&end=2019-05-27T10:00"

echo GET LIST OF MOVIES
curl "http://localhost:8080/api/movies"

echo GET LIST OF ROOMS
curl "http://localhost:8080/api/rooms"

echo SCREENING WITH ID=3 LOOKS INTERESTING
echo GET AVAILABLE SEATS LIST OF THIS SCREENING
curl "http://localhost:8080/api/screenings/3/seats"

echo WHAT ABOUT TICKETS? GET TICKET TYPES INFO
curl "http://localhost:8080/api/ticket-types"

echo LET'S BOOK FEW SEATS FOR 2 ADULTS AND 1 STUDENT
curl -H "Content-Type: application/json" http://localhost:8080/api/reservations -d @demo/reservation-data.json

echo CHECKING IN DATABASE (ENDPOINT FOR TESTING PURPOSES OF COURSE)
curl "http://localhost:8080/api/reservations"

echo NOW CHECKING VALIDATION
echo LET'S RESERVE SEAT #21 IN THE SAME SCREENING, SO THE SEAT #20 WOULD BE BETWEEN TWO RESERVED PLACES
curl -H "Content-Type: application/json" http://localhost:8080/api/reservations -d @demo/reservation-invalid-data1.json

echo LET'S TRY TO RESERVE SEATS THAT ARE ALREADY RESERVED
curl -H "Content-Type: application/json" http://localhost:8080/api/reservations -d @demo/reservation-invalid-data2.json

echo LET'S RESERVE NO SEAT GIVING INVALID NAME AND SURNAME
curl -H "Content-Type: application/json" http://localhost:8080/api/reservations -d @demo/reservation-invalid-data3.json


