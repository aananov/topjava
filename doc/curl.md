method get:
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/100003'

method getAll:
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/'

method delete:
curl --location --request DELETE 'http://localhost:8080/topjava/rest/meals/100003'

method createWithLocation
curl --location --request POST 'http://localhost:8080/topjava/rest/meals/' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": null,
"dateTime": "2020-02-01T18:00",
"description": "Созданный ужин",
"calories": 300
}'

method update:
curl --location --request PUT 'http://localhost:8080/topjava/rest/meals/100003' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 100003,
"dateTime": "2020-01-30T10:02",
"description": "Обновленный завтрак",
"calories": 200
}'

method getBetween:
curl --location --request
GET 'http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&endDate=2020-01-30&startTime=09:00:00&endTime=20:00:00'