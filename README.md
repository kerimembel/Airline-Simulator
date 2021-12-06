# Airline Simulator

This is a Web Application developed by Kerim Embel to simulate minor Airport operations.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven 3](https://maven.apache.org)
- [Docker](https://maven.apache.org)
- [Intellij IDEA](https://www.jetbrains.com/idea/)

## Running the application

The project folder downloaded from Github as zip or cloned is opened on Intellij IDEA.

### Running on Docker

> **Note:** To run the application with Docker, you must have Docker installed on your computer.

After running Docker, go to the directory where the project is located on Command Line.

With this command, the previously created .jar file is built with Docker.

```sh  
docker build -t task .
```  

The docker run command runs the application by running the built jar image.

```sh  
docker run -p 8080:8080 task
```  

By applying these steps sequentially, the application starts to run and can be used as specified in the API Detail.

### Running on local computer

> **Note:** To run the application on the local computer, the tools in the Requirements list must be installed on the computer.

The project folder downloaded from Github as zip or cloned is opened on Intellij IDEA.

It is added as a Maven project by right-clicking on the pom.xml file under the root module.

After the above steps are completed, the TaskApplication application under the
main module is started.

When the Java JDK version is required to run, continue by selecting **JDK 11**.

### API Detail

Project has 2 endpoints for 2 specific tasks.

#### First Endpoint
First endpoint is: **http://localhost:8080/api/flight/weights**

This endpoint expects a **GET Request** and as parameters expects **flightNumber** and **departureDate**.

departureDate parameter is optional but if we do not provide flightNumber parameter to endpoint it return a Bad Request response.

Example GET Request : http://localhost:8080/api/flight/weights?flightNumber=1166&departureDate=2019-02-05T08:39:00-03:00

Weight values are given in "kg" unit in the response given by the service. It can be changed parametrically and "lb" values can also be obtained.

Succesfull Response:

```javascript
{
    "cargoWeight": 1692,
    "baggageWeight": 3433,
    "totalWeight": 5125,
    "weightUnit": "kg",
    "detail": {
    "timestamp": "26-11-2021 12:30:06",
        "message": "Success",
        "returnCode": 200,
        "error": false
    }
}
```

Error Response:

```javascript
{
    "cargoWeight": 0,
    "baggageWeight": 0,
    "totalWeight": 0,
    "weightUnit": "kg",
    "detail": {
    "timestamp": "26-11-2021 12:24:57",
        "message": "Required request parameter 'flightNumber' for method parameter type Integer is not present",
        "returnCode": 400,
        "error": true
    }
}
```

#### Second Endpoint

Second endpoint is: **http://localhost:8080/api/flight/counts**

This endpoint expects a **GET Request** and as parameters expects **airportCode** and **departureDate**.

departureDate parameter is optional but if we do not provide airportCode parameter to endpoint it return a Bad Request response.

Example GET Request : http://localhost:8080/api/flight/counts?airportCode=lax&departureDate=2017-12-15T03:28:04-03:00

Succesfull Response:

```javascript
{
    "departingFlightCount": 1,
        "arrivingFlightCount": 0,
        "arrivingBaggageCount": 0,
        "departingBaggageCount": 3264,
        "detail": {
        "timestamp": "26-11-2021 12:31:23",
            "message": "Success",
            "returnCode": 200,
            "error": false
    }
}
```

Error Response:

```javascript
{
    "departingFlightCount": 0,
        "arrivingFlightCount": 0,
        "arrivingBaggageCount": 0,
        "departingBaggageCount": 0,
        "detail": {
        "timestamp": "26-11-2021 12:31:11",
            "message": "Required request parameter 'airportCode' for method parameter type String is not present",
            "returnCode": 400,
            "error": true
    }
}
```
