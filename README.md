# full-stack-credit-card-managment-web-application
This is a full-stack Web Application for managing a Credit Card System.

## Technologies

Front-end:
- Javascript (EcmaScript 6)
- React
- Axios
- CSS
- react-testing-library

Back-end:
- Java 8
- Spring Boot (DI, MVC, JdbcTemplate)
- H2 in-memory Database
- Junit
- Maven

## Getting started

To get the Front-end App running locally:

- Clone the repo
- `npm install` to install all required dependencies
- `npm start` to start the local server 

This project uses create-react-app.
The Application should run on `http://localhost:3000/`.

- For unit test cases use `npm run test`
- For unit test cases with code coverage use `npm run test -- --coverage`

To get the Back-end App running locally:

- Clone/Download the Java Spring/Maven project
- You can import the project on your favorite IDE:
   - On Eclipse: File -> Import -> Existing Maven Project
   - Inside the directory go to com.sapient package.
   - Right click CreditCardAppBackendApplication.java and run as java application. 

Java Application should run on `http://localhost:8080/`.

## Functionality overview

This application allows to perform Create/Read operations.

Users can add new Credit Cards to the System and each credit card has 3 attributes: User Name, Card Number, withdrawal Limit.
The User will then be able to view the added credit cards as a list in a table.

The Application allows to insert 16 to 19 digit card numbers and also allows users to write such numbers in full or by writing the digits separated by spaces or dashes. It will be then the App to elaborate and format the card's numbers.
All the Card Numbers must work against the Luhn 10 algorithm, otherwise validation errors will be displayed.

The Luhn 10 algorithm has been implemented on back-end to provide a more secure application.

## Technology overview

Spring MVC has ben used to expose Rest Web Services (POST and GET in these case).

All the data are stored into H2, which is an in-memory Database.

React was used to create a single-page dynamic UI.

Axios to handle the REST requests from front-end to back-end.
