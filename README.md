## Test Assignment for Java/Spring Boot Developer

## Task
Develop a web application that performs an analysis of product shipments to federal networks, taking into account promotional campaigns and regular prices. The application should allow a manufacturing company to understand the proportion of promotional sales, the average discount, and the effectiveness of promotional campaigns.

## Description of the Business Process
A manufacturing company ships its products to federal networks. It wants to understand how much of the products were shipped as part of promotional campaigns and how much were sold at regular prices. This data will help the company assess the proportion of promotional campaigns, the average discount, and the effectiveness of promotional campaigns.

## Source Tables
You have the following tables to implement the task:

"Actuals" - facts of product shipments (contains information about delivery addresses, products, dates, and shipment prices).
"Customers" - a customer directory (contains information about addresses and network names).
"Products" - a product directory (contains information about product codes, names, categories, and brands).
"Price" - a price list (contains information about network names, products, and regular prices per unit).

## What Needs to Be Done

* Create tables for shipment facts and directories. Establish relationships between them.
* Add a column "Promo Sign" to the shipment facts table to indicate whether a shipment was made at a regular price or with a discount. You can use the values "Regular" and "Promo" for this column.
* Write a REST Controller named "Finance" that implements CRUD operations (Create, Read, Update, Delete) for the "Price" entity. This will allow managing price information for products.
*  Write a REST Controller named "Analysis" that contains the following methods:

## Note
- A method for exporting sales facts with consideration of the promo sign. This method should return information about networks, categories, months, the number of units sold at the base price, the number of units sold at the promo price, the share of promo sales, and the percentage.
- A method for exporting sales facts by days with the ability to filter by a list of network names and a list of product names.