# Weather app - Spring Boot Application

A Spring Boot application that provides a REST API for searching cities from the OpenWeatherMap API by their first letter. The application fetches city data, processes it, and provides endpoints for querying cities that start with a specific letter.

## Features

REST API for searching cities by starting letter
REST API to get all cities
Caching mechanism for improved performance
Comprehensive error handling
Robust logging

## Prerequisites

Java 24
Maven 3.9.9
Internet connection to access the OpenWeatherMap API

## Project Structure
The project is structured with the following components:

Controller: Handles HTTP requests and responses
Service: Contains business logic for city filtering
Client: Manages API communication with OpenWeatherMap
Model: Data objects representing API responses
DTO: Data Transfer Objects for API responses
Exception: Custom exceptions and global error handling

## Building the Project

Clone the repository
Navigate to the project directory
Build the project using Maven:

`bash
mvn clean install
`


## Running the Application

After building the project, you can run it using:
`bash
mvn spring-boot:run
`

Or run the JAR file directly:
`bash
 java -jar target/cityfinder-0.0.1-SNAPSHOT.jar
`
The application will start on port 8080 by default.


## API Endpoints

Get Cities by Starting Letter
`GET /api/cities/by-letter/{letter}`
Returns a list of cities that start with the specified letter and their count.
Path Parameters:
letter: A single alphabetic character (case-insensitive)

Example Response:
`json{
  "count": 3,
  "cities": ["Zawiya", "Zuwarah", "Zlitan"]
}`


Get All Cities
`GET /api/cities/all`
Returns all available cities and their count.
Example Response:
`json{
  "count": 15,
  "cities": ["Yafran", "Zuwarah", ...]
}`
