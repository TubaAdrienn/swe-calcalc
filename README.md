## Calorie Calculator

Simple application that allows the user to count and save daily calorie intake by choosing the consumed food. It also provides a BMI calculator of which value can also be logged.

### Usage
First you have to connect to Generic H2 Server database.

Then execute the following commands in the main directory:

    mvn compile
    mvn exec:java
    
or

    mvn package
    java -jar target/CaloriesCalc-1.0.jar
    
### Requirements

* H2 Console
* JDK 11 or above
* Maven 3.0 or above