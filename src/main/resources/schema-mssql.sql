CREATE TABLE [employee] (
[id] bigint NOT NULL IDENTITY(-1,-1),
[guid] bigint NOT NULL,
[name] varchar(255) NOT NULL,
[age] int NOT NULL,
[gender] varchar(255) NOT NULL,
[position] varchar(255) NOT NULL,
[salary] decimal(10,2) NOT NULL,
PRIMARY KEY ([id]) 
)
GO

CREATE TABLE [department] (
[id] bigint NOT NULL IDENTITY(-1,-1),
[name] varchar(255) NOT NULL,
[manager_employee_id] bigint NOT NULL,
[address] varchar(255) NOT NULL,
[phone] varchar(255) NOT NULL,
PRIMARY KEY ([id]) 
)
GO

CREATE TABLE [book] (
[id] bigint NOT NULL IDENTITY(-1,-1),
[name] varchar(255) NOT NULL,
[specification] varchar(255) NOT NULL,
[publisher_id] bigint NOT NULL,
[category_id] bigint NOT NULL,
[cost] decimal(10,2) NOT NULL,
[retail_price] decimal(10,2) NOT NULL,
[wholesale_price] decimal(10,2) NOT NULL,
PRIMARY KEY ([id]) 
)
GO

CREATE TABLE [publisher] (
[id] bigint NOT NULL IDENTITY(-1,-1),
[name] varchar(255) NOT NULL,
[phone] varchar(255) NOT NULL,
[address] varchar(255) NOT NULL,
PRIMARY KEY ([id]) 
)
GO

CREATE TABLE [bookcenter] (
[id] bigint NOT NULL IDENTITY(-1,-1),
[name] varchar(255) NOT NULL,
[general_manager_employee_id] bigint NOT NULL,
PRIMARY KEY ([id]) 
)
GO

CREATE TABLE [warehouse] (
[id] bigint NOT NULL IDENTITY(-1,-1),
[name] varchar(255) NOT NULL,
[manager_employee_id] int NOT NULL,
[phone] varchar(255) NOT NULL,
PRIMARY KEY ([id]) 
)
GO

CREATE TABLE [stock_item] (
[id] bigint NOT NULL IDENTITY(-1,-1),
[book_id] bigint NOT NULL,
[quantity] int NOT NULL,
[warehouse_id] bigint NOT NULL,
PRIMARY KEY ([id]) 
)
GO

CREATE TABLE [catagory] (
[id] bigint NOT NULL IDENTITY(-1,-1),
[name] varchar(255) NOT NULL,
[sales_department_id] bigint NOT NULL,
PRIMARY KEY ([id]) 
)
GO

