# SalesTaxManagement
An application for managing sales tax for different products and generating the final bill receipt.

## To create war file

Use below command for creating war file

```bash
mvn clean install -e -DskipTests
```

## Technical Stack

This application is developed using below technical stack:

```
Java 8
Junit 4
Java Design Pattern
Java Logging
JavaDoc
Maven
```

## Functionality
1. We have implemented tax management system for generating final bill receipt for given products.
2. There are two kinds of products, taxable and non-taxable.
3. Products which fall under categories like food, medical or books are non-taxable products whereas the rest of the categories or products are taxable.
4. These products can be imported from some other countries too.
5. We have different tax slabs for imported products and non-imported products.
6. For non-imported products:
    * Taxable goods: 10% of product's base price is applied on taxable goods.
    * Non-Taxable goods: No tax is applied.
7. For imported products:
    * Taxable goods: Additional 5% is added, so total tax would be 15%.
    * Non-Taxable goods: Additional 5% is added, so total tax would be 5%.

## Technicality
1. We have implemented this application using Core Java.
2. We have used various features of Java like OOPS concepts, design pattern (Template Design Pattern), graceful exception handling, logging, unit testing.
3. We have used Template Design Pattern to differentiate between non-imported tax and imported tax.
4. Application can be extended as per the requirement, we have followed some of the SOLID principles.
5. We have also put logger statements on every method to check the execution time.
6. We are reading input from a .txt file and parsing the input in our required format for further operations.
7. Sample .txt file is present inside /src/main/resources folder.
8. We have also implemented JavaDoc.
9. Logging level is currently set to 'INFO', we can change logging level in resources/logging.properties. Change below properties:

````
Current Configuration:
.level= INFO
java.util.logging.ConsoleHandler.level = INFO

Updated Configuration:
.level= OFF
java.util.logging.ConsoleHandler.level = OFF
````

##Assumptions

* To run this application we need to pass .txt file as input with sample format as:

```
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
```
* If any other format is provided then system is handled to throw InvalidDataException.
* This system accepts list of inputs which will in turn product list of output bills.
* Output format:

````
Output 1:
1 book : 12.49
1 music CD : 16.49
1 chocolate bar : 0.85
Sales Taxes: 1.50
Total: 29.83
Output 2:
1 imported box of chocolates : 10.50
1 imported bottle of perfume : 54.65
Sales Taxes: 7.65
Total: 65.15
Output 3:
1 imported bottle of perfume : 32.19
1 bottle of perfume : 20.89
1 packet of headache pills : 9.75
1 box of imported chocolates : 11.85
Sales Taxes: 6.70
Total: 74.68
````
* We can add more than 1 quantity for a particular product.
* Tax calculation for multiple products:

````
Suppose we have received input as "2 imported perfumes at 47.50" then we are calculating tax on each quantity like:
Tax on Perfume 1: (47.5 * 15) / 100 = 7.15
Tax on Perfume 2: (47.5 * 15) / 100 = 7.15
So total tax on this product would be 14.30
````
* Currently we have used ENUM to store non-taxable products (NonTaxableProducts.java).
* To make a product as non-taxable, we will have to update NonTaxableProducts.java.

##Future Scope
* As this is simple Java application, we can extend this application to create small micro-service in Spring boot.
* We can also implement swagger to expose APIs to clients.
* We can perform graceful exception handling using controller advice.
* We can store information related to non-taxable product and their respective categories in database (like Oracle, Postgre, etc.)
* We can used Spring Data JPA or Hibernate or any other ORM tool for fetching values from the database.
* We can implement Spring AOP for logging purpose.
* We can use tools like Prometheus and Grafana for monitoring APIs in much detail.
