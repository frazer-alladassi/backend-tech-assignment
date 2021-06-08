#SM360 Backend Tech Assignment

This project was build using java, spring boot framework and postgres database.

To run this project you need to install following tools :

java version 8 or higher

maven version 3 or higher

postgresql database version or higher

Go to resources folder and update application.properties file with database name, username and password 

Run a command : mvn clean install package to build a project

Run a command : java -jar target/backend-tech-assignment-1.0-SNAPSHOT.jar to launch on port 3000

See below some examples to test api using curl :

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