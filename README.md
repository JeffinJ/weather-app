# Weather app - Spring Boot Application

A Spring Boot application that provides a REST API for searching cities from the OpenWeatherMap API by their first letter. The application fetches city data, processes it, and provides endpoints for querying cities that start with a specific letter.

## Features

1. REST API for searching cities by starting letter
2. REST API to get all cities
3. Caching mechanism for improved performance
4. Comprehensive error handling
5. Robust logging

## Prerequisites

1. Java 24

2. Maven 3.9.9

3. Internet connection to access the OpenWeatherMap API

## Project Structure
The project is structured with the following components:

- **Controller:** Handles HTTP requests and responses

- **Service:** Contains business logic for city filtering

- **Client:** Manages API communication with OpenWeatherMap

- **Model:** Data objects representing API responses

- **DTO:** Data Transfer Objects for API responses

- **Exception:** Custom exceptions and global error handling

## Building the Project

1. Clone the repository
2. Navigate to the project directory
3. Build the project using Maven:

```bash
mvn clean install
```


## Running the Application

After building the project, you can run it using:

```bash
mvn spring-boot:run
```

Or run the JAR file directly:

```bash
 java -jar target/cityfinder-0.0.1-SNAPSHOT.jar
```

The application will start on port 8080 by default.


## API Endpoints

### Get Cities by Starting Letter
`GET /api/cities/by-letter/{letter}`

Returns a list of cities that start with the specified letter and their count.

**Path Parameters:**
- letter: A single alphabetic character (case-insensitive)

**Example Response:**
```json
{
  "count": 3,
  "cities": ["Zawiya", "Zuwarah", "Zlitan"]
}
```


### Get All Cities
`GET /api/cities/all`

Returns all available cities and their count.

**Example Response:**
```json
{
  "count": 15,
  "cities": ["Yafran", "Zuwarah", ...]
}
```
