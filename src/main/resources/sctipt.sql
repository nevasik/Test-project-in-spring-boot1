create table Сustomer
(
    ship_to_code      bigint primary key,
    ch_3_ship_to_name varchar(255),
    chain_name        varchar(255)
);

insert into Сustomer (ship_to_code, ch_3_ship_to_name, chain_name)
values (5471687, 'Chain 1 00001', 'Chain 1'),
       (5527651, 'Chain 1 00002', 'Chain 1'),
       (5516777, 'Chain 1 00003', 'Chain 1'),
       (5520888, 'Chain 1 00004', 'Chain 1'),
       (5528925, 'Chain 1 00005', 'Chain 1'),
       (5520897, 'Chain 1 00006', 'Chain 1'),
       (5528923, 'Chain 1 00007', 'Chain 2'),
       (5517876, 'Chain 1 00008', 'Chain 2'),
       (5527476, 'Chain 1 00009', 'Chain 2'),
       (5528921, 'Chain 1 00010', 'Chain 2');


------------------------------------------------------------------------------------------------------------
create table Product
(
    material_no           bigint primary key,
    material_desc_rus     varchar(255),
    product_category_code bigint,
    product_category_name varchar(255)
);

insert into Product (material_no, material_desc_rus, product_category_code, product_category_name)
values (70148900, 'Product description 0001', 191116, 'Category 01'),
       (70163200, 'Product description 0002', 191123, 'Category 02'),
       (70168300, 'Product description 0003', 191123, 'Category 02'),
       (70168500, 'Product description 0004', 191123, 'Category 02'),
       (70168600, 'Product description 0005', 191123, 'Category 02'),
       (70173600, 'Product description 0006', 191123, 'Category 02'),
       (70211500, 'Product description 0007', 191123, 'Category 02'),
       (70240300, 'Product description 0008', 191123, 'Category 02'),
       (70240301, 'Product description 0009', 191123, 'Category 02'),
       (70240302, 'Product description 0010', 191123, 'Category 02');


------------------------------------------------------------------------------------------------------------------------
create table Price
(
    id                     bigint primary key,
    chain_name             varchar(255),
    material_no            bigint references Product (material_no),
    regular_price_per_unit float8
);

insert into Price (id, chain_name, material_no, regular_price_per_unit)
VALUES (1, 'Chain 1', 70148900, 93.79),
       (2, 'Chain 1', 70163200, 29.89),
       (3, 'Chain 1', 70168300, 78.835),
       (4, 'Chain 1', 70168500, 58.935),
       (5, 'Chain 1', 70168600, 55.05),
       (6, 'Chain 1', 70173600, 54.74),
       (7, 'Chain 1', 70211500, 49.51),
       (8, 'Chain 1', 70240300, 51.145),
       (9, 'Chain 1', 70240301, 52.125),
       (10, 'Chain 1', 70240302, 69.22);


------------------------------------------------------------------------------------------------------------------------
create table Actuals
(
    actuals_id         bigint primary key,
    date               date,
    material_no        bigint references Product (material_no),
    ship_to_code       bigint references Сustomer (ship_to_code),
    volume             varchar(255),
    units              int,
    actual_sales_value float8,
    promo_sign         varchar
);

insert into Actuals(actuals_id ,date, material_no, ship_to_code, volume, units, actual_sales_value)
values (1, '2021-02-25', 70148900, 5517876, 'Chain 1', 24, 2250.96),
       (2, '2021-03-25', 70163200, 5528923, 'Chain 1', 12, 1125.48),
       (3, '2021-04-15', 70168300, 5520897, 'Chain 1', 12, 1125.48),
       (4, '2020-12-30', 70168500, 5528925, 'Chain 1', 1056, 25618.56),
       (5, '2021-01-16', 70168600, 5520888, 'Chain 1', 4944, 89980.8),
       (6, '2020-12-28', 70173600, 5516777, 'Chain 1', 3168, 76855.68),
       (7, '2021-01-21', 70211500, 5527651, 'Chain 1', 8208, 149385.6),
       (8, '2021-01-30', 70240301, 5471687, 'Chain 1', 3168, 57657.6);

select * from actuals;
select * from price;
select * from product;
select * from customer;