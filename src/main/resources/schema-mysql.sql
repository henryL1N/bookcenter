CREATE TABLE `employee` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`guid` bigint(11) NOT NULL,
`name` varchar(255) NOT NULL,
`age` int NOT NULL,
`gender` enum('MALE','FEMALE') NOT NULL,
`position` varchar(255) NOT NULL,
`salary` decimal(10,2) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `department` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`manager_employee_id` bigint(11) NOT NULL,
`address` varchar(255) NOT NULL,
`phone` varchar(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `book` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`specification` varchar(255) NOT NULL,
`publisher_id` bigint(255) NOT NULL,
`category_id` bigint(255) NOT NULL,
`cost` decimal(10,2) NOT NULL,
`retail_price` decimal(10,2) NOT NULL,
`wholesale_price` decimal(10,2) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `publisher` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`phone` varchar(255) NOT NULL,
`address` varchar(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `bookcenter` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`general_manager_employee_id` bigint(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `warehouse` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`manager_employee_id` int(11) NOT NULL,
`phone` varchar(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `stock_item` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`book_id` bigint(11) NOT NULL,
`quantity` int(255) NOT NULL,
`warehouse_id` bigint(255) NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `catagory` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`sales_department_id` bigint(255) NOT NULL,
PRIMARY KEY (`id`) 
);

