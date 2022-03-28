helloTest:
https://www.youtube.com/watch?v=Ijj5RLSAcYw

---------------------------------------------------------------------------------------------
personTest:

# Data:
Sample example of JSON data object:
```json
{
   "id": 1,
   "date": "1985-01-01",
   "firstName": "Foo",
   "lastName": "Bar",
   "phoneNumber": 3876542098
}
```

## Requirements:
The `REST` service must expose the `/endpoint` endpoint, which allows for managing the data records in the following way:

`POST` request to `/insert` :
* creates a new weather data record
* the response code is 201 and the response body is the created record, including its unique id

`GET` request to `/select`:
* the response code is 200
* the response body is an array of matching records, ordered by their ids in increasing order

`GET` request to `/select/<id>`:
* returns a record with the given id and status code 200
* if there is no record in the database with the given id, the response code is 404

`DELETE` request to `/delete/<id>`:
* deletes the record with the given id from the database and return status code 200
* if there was no record in the database with the given id, the response code is 404

Your task is to complete the given project so that it passes all the test cases when running the provided unit tests. The project by default supports the use of the H2 database.

----------------------
Junit:
MockMvc 
