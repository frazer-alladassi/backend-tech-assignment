## SM360 Backend Tech Assignment

This project was built using Java and the Spring boot framework. It uses a postgres database for storing data and implements the following endpoints.

- /api/advertising
    - **POST** : Create a new listing given a set of information on the vehicle and dealer (See request body in examples below).
    - **PUT** : Update the information of an existing listing based on its' id.

- /api/advertising/publish
    - **PUT** : Change the status of a listing from DRAFT to PUBLISHED

- /api/advertising/unpublish
    - **PUT** : Change the status of a listing from PUBLISHED to DRAFT

In order to be able to run this project, you would require the following tools:
- Java (Version 8 or higher)
- Maven (Version 3 or higher)
- PostgreSQL database

We would need to update the application.properties file with the username, password required to access the database server along with the name of the database that you would like to connect this application to.

The next step would be to build the project using : `mvn clean install package`.
This command would build an executable .jar for the project as well as run the necessary tests added to it.

We can then run the project using `java -jar target/backend-tech-assignment-1.0-SNAPSHOT.jar` which would run the project on the port **3000**.

Below are a few sample cURL requests to test the various endpoints implemented:

```
    curl --location --request POST 'http://localhost:3000/api/advertising' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "TOYOTA",
        "brand": "",
        "description": "",
        "year": "2008",
        "model": "Avencis",
        "dimension": "",
        "speed": "",
        "weight": "",
        "firstname": "Myriam",
        "lastname": "Makeba",
        "phonenumber": "2132187652",
        "price": 3000000
    }'
```

```
    curl --location --request PUT 'http://localhost:3000/api/advertising?id=3' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "TOYOTA",
        "brand": "",
        "description": "",
        "year": "2007",
        "model": "Avencis",
        "dimension": "",
        "speed": "",
        "weight": "",
        "firstname": "Myriam",
        "lastname": "MAKEBA",
        "phonenumber": "21321876",
        "price": 3000000
    }'
```

```
    curl --location --request PUT 'http://localhost:3000/api/advertising/publish?id=3'
```

```
    curl --location --request PUT 'http://localhost:3000/api/advertising/unpublish?id=3'
```

```
    curl --location --request GET 'http://localhost:3000/api/advertising'
```