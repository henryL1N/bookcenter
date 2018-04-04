ALTER TABLE `bookcenter` DROP FOREIGN KEY `fk_bookcenter_bookcenter_1`;
ALTER TABLE `department` DROP FOREIGN KEY `fk_department`;
ALTER TABLE `warehouse` DROP FOREIGN KEY `fk_warehouse`;
ALTER TABLE `book` DROP FOREIGN KEY `fk_book`;
ALTER TABLE `stock_item` DROP FOREIGN KEY `fk_stock_item`;
ALTER TABLE `stock_item` DROP FOREIGN KEY `fk_stock_item_1`;
ALTER TABLE `catagory` DROP FOREIGN KEY `fk_catagory`;
ALTER TABLE `book` DROP FOREIGN KEY `fk_book_1`;

DROP TABLE `employee`;
DROP TABLE `department`;
DROP TABLE `book`;
DROP TABLE `publisher`;
DROP TABLE `bookcenter`;
DROP TABLE `warehouse`;
DROP TABLE `stock_item`;
DROP TABLE `catagory`;

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


ALTER TABLE `bookcenter` ADD CONSTRAINT `fk_bookcenter_bookcenter_1` FOREIGN KEY (`general_manager`) REFERENCES `employee` (`employee_id`);
ALTER TABLE `department` ADD CONSTRAINT `fk_department` FOREIGN KEY (`manager`) REFERENCES `employee` (`employee_id`);
ALTER TABLE `warehouse` ADD CONSTRAINT `fk_warehouse` FOREIGN KEY (`manager`) REFERENCES `employee` (`employee_id`);
ALTER TABLE `book` ADD CONSTRAINT `fk_book` FOREIGN KEY (`publisher`) REFERENCES `publisher` (`id`);
ALTER TABLE `stock_item` ADD CONSTRAINT `fk_stock_item` FOREIGN KEY (`book`) REFERENCES `book` (`id`);
ALTER TABLE `stock_item` ADD CONSTRAINT `fk_stock_item_1` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`);
ALTER TABLE `catagory` ADD CONSTRAINT `fk_catagory` FOREIGN KEY (`sales_department`) REFERENCES `department` (`id`);
ALTER TABLE `book` ADD CONSTRAINT `fk_book_1` FOREIGN KEY (`catagory`) REFERENCES `catagory` (`id`);

