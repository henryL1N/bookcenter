

DROP TABLE if exists `employee` CASCADE;
DROP TABLE if exists `department` CASCADE;
DROP TABLE if exists `book` CASCADE;
DROP TABLE if exists `publisher` CASCADE;
DROP TABLE if exists `bookcenter` CASCADE;
DROP TABLE if exists `warehouse` CASCADE;
DROP TABLE if exists `stock_item` CASCADE;
DROP TABLE if exists `catagory` CASCADE;

CREATE TABLE `employee` (
`guid` bigint(11) NOT NULL,
`employee_id` int(11) NOT NULL,
`name` varchar(255) NOT NULL,
`age` int NOT NULL,
`gender` enum('MALE','FEMALE') NOT NULL,
`position` varchar(255) NOT NULL,
`salary` decimal(10,2) NOT NULL,
PRIMARY KEY (`guid`) 
);

CREATE TABLE `department` (
`id` bigint(11) NOT NULL,
`name` varchar(255) NOT NULL,
`manager` int(11) NOT NULL,
`address` varchar(255) NOT NULL,
`phone` varchar(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `book` (
`id` bigint(11) NOT NULL,
`name` varchar(255) NOT NULL,
`specification` varchar(255) NOT NULL,
`publisher` bigint(255) NOT NULL,
`catagory` bigint(255) NOT NULL,
`cost` decimal(10,2) NOT NULL,
`retail_price` decimal(10,2) NOT NULL,
`wholesale_price` decimal(10,2) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `publisher` (
`id` bigint(11) NOT NULL,
`name` varchar(255) NOT NULL,
`phone` varchar(255) NOT NULL,
`address` varchar(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `bookcenter` (
`id` bigint(11) NOT NULL,
`name` varchar(255) NOT NULL,
`general_manager` bigint(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `warehouse` (
`id` bigint(11) NOT NULL,
`name` varchar(255) NOT NULL,
`manager` int(11) NOT NULL,
`phone` varchar(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `stock_item` (
`id` bigint(11) NOT NULL,
`book` bigint(11) NOT NULL,
`quantity` int(255) NOT NULL,
`warehouse` bigint(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `catagory` (
`id` bigint(11) NOT NULL,
`name` varchar(255) NOT NULL,
`sales_department` bigint(255) NOT NULL,
PRIMARY KEY (`id`) 
);

