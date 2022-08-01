## MealRestController

### curls for tests

_________

#### get meal 100003 for user:

`curl --location --request GET 'http://localhost:8080/topjava/rest/meals/100003'`

#### get all meals for user:

`curl --location --request GET 'http://localhost:8080/topjava/rest/meals/'`

#### delete meal 100003:

`curl --location --request DELETE 'http://localhost:8080/topjava/rest/meals/100003'`

#### create meal for user:

```
curl --location --request POST 'http://localhost:8080/topjava/rest/meals/' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": null,
"dateTime": "2020-02-01T18:00",
"description": "Созданный ужин",
"calories": 300
}'
```

#### update meal 100003:

```
curl --location --request PUT 'http://localhost:8080/topjava/rest/meals/100003' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 100003,
"dateTime": "2020-01-30T10:02",
"description": "Обновленный завтрак",
"calories": 200
}'
```

#### filter user meals between 2020-01-30T10:00:00 and 2021-02-03T12:15:00

```
curl --location --request
GET 'http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&endDate=2021-02-03&startTime=10:00:00&endTime=12:15:00'
```