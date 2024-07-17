# Card Service Project

This project provides a Card Service that enables users to manage their cards and perform various card-related operations.

## Overview

The Card Service project is a Java-based application that provides a range of services for managing cards, including card issuance, card requests, and user management. The project uses a microservices architecture and integrates with various third-party services.

## Technologies Used

* **Java**: The project is built using Java as the programming language.
* **JUnit**: The project uses JUnit for unit testing.
* **Mockito**: The project uses Mockito for mocking dependencies in unit tests.
* **Maven**: The project uses Maven as the build tool, with the Maven Compiler Plugin for compiling Java code.

## Project Structure

The project directory structure is as follows:
## Model and Services

The project includes the following models and services:

* **Model**: The project includes a range of models, including `Account`, `Card`, `CardRequest`, and `User`.
* **Services**: The project includes a range of services, including `CardService`, `CompassPlusService`, `EVPService`, `KafkaService`, `MariaDBService`, `MokejimaiService`, `NotifierService`, and `TemporalService`.

## Testing

The project includes a range of tests to ensure the services are working correctly. These tests can be found in the `test` directory.

* **Test Runner**: The test runner is implemented using JUnit, and the tests are structured and easily readable. Each test contains comments explaining the steps and expected results.

* **Tests**: The project includes a range of tests, including 
* `CardIssuanceSuccessTest` covering positive scenario
* `CardIssuanceFailureTest` covering negative scenarios

## Getting Started

To get started with the project, 
* Clone the GitHub repository.
* Compile the project using Maven.
* Run the tests using JUnit.

## Submission Format
The project is submitted in the following format:
* Google Docs: Description of the test automation project.
* GitHub: Code repository containing the test automation framework.

## Contributing

Contributions to the project are welcome! If you'd like to contribute, please fork the repository and submit a pull request.