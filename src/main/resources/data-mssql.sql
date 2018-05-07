/*
使用Navicat data transfer工具导出数据
使用以下正则表达式删除数据结构
-[- \n]*?(Table|Uniques|Primary|Indexes|Foreign)[\s\S]*?\n(?=-[- \n]*?Records|\Z)
 */

/*
 Navicat Premium Data Transfer

 Source Server         : sql
 Source Server Type    : SQL Server
 Source Server Version : 14003023
 Source Host           : localhost:1433
 Source Catalog        : BookCenter
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14003023
 File Encoding         : 65001

 Date: 08/05/2018 00:17:36
*/


-- ----------------------------
-- Records of [book_center]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[book_center]  VALUES (N'1001', N'广州购书中心', N'广东省广州市天河路123号', N'1001')
GO

COMMIT
GO


-- ----------------------------
-- Records of [category]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[category]  VALUES (N'1001', N'文学', N'1001'), (N'1002', N'艺术', N'1002'), (N'1003', N'科教', N'1003'), (N'1004', N'历史', N'1004'), (N'1005', N'语言', N'1005')
GO

COMMIT
GO


-- ----------------------------
-- Records of [DATABASECHANGELOG]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[DATABASECHANGELOG]  VALUES (N'00000000000000', N'jhipster', N'config/liquibase/changelog/00000000000000_initial_schema.xml', N'2018-05-07 09:30:41.147', N'1', N'EXECUTED', N'7:a6235f40597a13436aa36c6d61db2269', N'createSequence sequenceName=hibernate_sequence', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'00000000000001', N'jhipster', N'config/liquibase/changelog/00000000000000_initial_schema.xml', N'2018-05-07 09:30:41.240', N'2', N'EXECUTED', N'7:2b289fc456772b644b6d5630227c934e', N'createTable tableName=jhi_user; createIndex indexName=idx_user_login, tableName=jhi_user; createIndex indexName=idx_user_email, tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableN...', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104933-1', N'jhipster', N'config/liquibase/changelog/20180504104933_added_entity_Book.xml', N'2018-05-07 09:30:41.250', N'3', N'EXECUTED', N'7:483e84d136d2cd4ec114309708ff8df2', N'createTable tableName=book', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104934-1', N'jhipster', N'config/liquibase/changelog/20180504104934_added_entity_BookCenter.xml', N'2018-05-07 09:30:41.257', N'4', N'EXECUTED', N'7:294e7b546109ce40de839d8676e7e106', N'createTable tableName=book_center', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104935-1', N'jhipster', N'config/liquibase/changelog/20180504104935_added_entity_Category.xml', N'2018-05-07 09:30:41.267', N'5', N'EXECUTED', N'7:48ff862dd142c71619d61b2e04fe6e76', N'createTable tableName=category', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104936-1', N'jhipster', N'config/liquibase/changelog/20180504104936_added_entity_Department.xml', N'2018-05-07 09:30:41.277', N'6', N'EXECUTED', N'7:88dc545928b4947955cbf58aa2dab989', N'createTable tableName=department', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104937-1', N'jhipster', N'config/liquibase/changelog/20180504104937_added_entity_Employee.xml', N'2018-05-07 09:30:41.283', N'7', N'EXECUTED', N'7:cb9a39a1e98155e52819bbacdcfba202', N'createTable tableName=employee', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104938-1', N'jhipster', N'config/liquibase/changelog/20180504104938_added_entity_Publisher.xml', N'2018-05-07 09:30:41.293', N'8', N'EXECUTED', N'7:ce5735835fdd0a4f969fd5745bcc92c3', N'createTable tableName=publisher', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104939-1', N'jhipster', N'config/liquibase/changelog/20180504104939_added_entity_StockItem.xml', N'2018-05-07 09:30:41.297', N'9', N'EXECUTED', N'7:dede79da55bdaa57f89813c94298e316', N'createTable tableName=stock_item', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104940-1', N'jhipster', N'config/liquibase/changelog/20180504104940_added_entity_Warehouse.xml', N'2018-05-07 09:30:41.307', N'10', N'EXECUTED', N'7:3a82cae07840ebf03dc0c2d30436e6a5', N'createTable tableName=warehouse', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104941-1', N'jhipster', N'config/liquibase/changelog/20180504104941_added_entity_PurchaseOrder.xml', N'2018-05-07 09:30:41.317', N'11', N'EXECUTED', N'7:df57973ab98a48decae9e453f377c5d1', N'createTable tableName=purchase_order', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104942-1', N'jhipster', N'config/liquibase/changelog/20180504104942_added_entity_SalesOrder.xml', N'2018-05-07 09:30:41.323', N'12', N'EXECUTED', N'7:563b3d322e5e2fb59948dbcccca94274', N'createTable tableName=sales_order', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104943-1', N'jhipster', N'config/liquibase/changelog/20180504104943_added_entity_OrderItem.xml', N'2018-05-07 09:30:41.333', N'13', N'EXECUTED', N'7:bdc86870c239074205abcfe922f4d76e', N'createTable tableName=order_item', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104933-2', N'jhipster', N'config/liquibase/changelog/20180504104933_added_entity_constraints_Book.xml', N'2018-05-07 09:30:41.340', N'14', N'EXECUTED', N'7:32c4374daa0dbccdf7515633d32e93a9', N'addForeignKeyConstraint baseTableName=book, constraintName=fk_book_publisher_id, referencedTableName=publisher; addForeignKeyConstraint baseTableName=book, constraintName=fk_book_category_id, referencedTableName=category', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104934-2', N'jhipster', N'config/liquibase/changelog/20180504104934_added_entity_constraints_BookCenter.xml', N'2018-05-07 09:30:41.350', N'15', N'EXECUTED', N'7:07bbf2a91eb89726a1d3c9c5abd8cc84', N'addForeignKeyConstraint baseTableName=book_center, constraintName=fk_book_center_general_manager_id, referencedTableName=employee', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104935-2', N'jhipster', N'config/liquibase/changelog/20180504104935_added_entity_constraints_Category.xml', N'2018-05-07 09:30:41.353', N'16', N'EXECUTED', N'7:0ebdcd79fd955e35e1f49d824ec2771c', N'addForeignKeyConstraint baseTableName=category, constraintName=fk_category_sales_department_id, referencedTableName=department', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104936-2', N'jhipster', N'config/liquibase/changelog/20180504104936_added_entity_constraints_Department.xml', N'2018-05-07 09:30:41.363', N'17', N'EXECUTED', N'7:c7c9247a4c0ab68652509593eaef77e0', N'addForeignKeyConstraint baseTableName=department, constraintName=fk_department_book_center_id, referencedTableName=book_center; addForeignKeyConstraint baseTableName=department, constraintName=fk_department_manager_id, referencedTableName=employee', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104937-2', N'jhipster', N'config/liquibase/changelog/20180504104937_added_entity_constraints_Employee.xml', N'2018-05-07 09:30:41.373', N'18', N'EXECUTED', N'7:6a485d04bac241aad489e028dfa0f980', N'addForeignKeyConstraint baseTableName=employee, constraintName=fk_employee_user_id, referencedTableName=jhi_user', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104939-2', N'jhipster', N'config/liquibase/changelog/20180504104939_added_entity_constraints_StockItem.xml', N'2018-05-07 09:30:41.377', N'19', N'EXECUTED', N'7:101f772c3375fbd781bf2d0a7a9200ab', N'addForeignKeyConstraint baseTableName=stock_item, constraintName=fk_stock_item_warehouse_id, referencedTableName=warehouse', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104941-2', N'jhipster', N'config/liquibase/changelog/20180504104941_added_entity_constraints_PurchaseOrder.xml', N'2018-05-07 09:30:41.387', N'20', N'EXECUTED', N'7:82e10341844930e2158a8eb857b7a7ab', N'addForeignKeyConstraint baseTableName=purchase_order, constraintName=fk_purchase_order_buyer_id, referencedTableName=employee', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104942-2', N'jhipster', N'config/liquibase/changelog/20180504104942_added_entity_constraints_SalesOrder.xml', N'2018-05-07 09:30:41.390', N'21', N'EXECUTED', N'7:4ccddb443b2dd720fee00596efff6948', N'addForeignKeyConstraint baseTableName=sales_order, constraintName=fk_sales_order_seller_id, referencedTableName=employee', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050'), (N'20180504104943-2', N'jhipster', N'config/liquibase/changelog/20180504104943_added_entity_constraints_OrderItem.xml', N'2018-05-07 09:30:41.403', N'22', N'EXECUTED', N'7:0a669bc18467cabc58b5ec03b41de65d', N'addForeignKeyConstraint baseTableName=order_item, constraintName=fk_order_item_purchase_order_id, referencedTableName=purchase_order; addForeignKeyConstraint baseTableName=order_item, constraintName=fk_order_item_sales_order_id, referencedTableNam...', N'', NULL, N'3.5.4', NULL, NULL, N'5685439050')
GO

COMMIT
GO


-- ----------------------------
-- Records of [DATABASECHANGELOGLOCK]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[DATABASECHANGELOGLOCK]  VALUES (N'1', N'0', NULL, NULL)
GO

COMMIT
GO


-- ----------------------------
-- Records of [department]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[department]  VALUES (N'1001', N'文学类销售部', N'中心大楼1楼', N'1001', N'1002'), (N'1002', N'艺术类销售部', N'中心大楼2楼', N'1001', N'1003'), (N'1003', N'科教类销售部', N'中心大楼2楼', N'1001', N'1004'), (N'1004', N'历史类销售部', N'中心大楼2楼', N'1001', N'1005'), (N'1005', N'语言类销售部', N'中心大楼2楼', N'1001', N'1006')
GO

COMMIT
GO


-- ----------------------------
-- Records of [employee]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[employee]  VALUES (N'1001', N'张伟', N'40', N'MALE', N'总经理', N'10000.00', N'1001'), (N'1002', N'王伟', N'39', N'MALE', N'销售经理', N'8000.00', N'1002'), (N'1003', N'王芳', N'38', N'FEMALE', N'销售经理', N'8000.00', N'1003'), (N'1004', N'李伟', N'37', N'MALE', N'销售经理', N'8000.00', N'1004'), (N'1005', N'李娜', N'36', N'FEMALE', N'销售经理', N'8000.00', N'1005'), (N'1006', N'张敏', N'35', N'FEMALE', N'销售经理', N'8000.00', N'1006'), (N'1007', N'李静', N'34', N'FEMALE', N'仓管员', N'5000.00', N'1007'), (N'1008', N'王静', N'33', N'FEMALE', N'仓管员', N'5000.00', N'1008'), (N'1009', N'刘伟', N'32', N'MALE', N'仓管员', N'5000.00', N'1009'), (N'1010', N'王秀英', N'31', N'FEMALE', N'销售员', N'3000.00', N'1010'), (N'1011', N'张丽', N'30', N'FEMALE', N'销售员', N'3000.00', N'1011'), (N'1012', N'李秀英', N'29', N'FEMALE', N'销售员', N'3000.00', N'1012'), (N'1013', N'王丽', N'28', N'FEMALE', N'销售员', N'3000.00', N'1013'), (N'1014', N'张静', N'27', N'FEMALE', N'销售员', N'3000.00', N'1014'), (N'1015', N'张秀英', N'26', N'FEMALE', N'销售员', N'3000.00', N'1015'), (N'1016', N'李强', N'25', N'MALE', N'销售员', N'3000.00', N'1016'), (N'1017', N'王敏', N'24', N'FEMALE', N'销售员', N'3000.00', N'1017'), (N'1018', N'李敏', N'23', N'FEMALE', N'销售员', N'3000.00', N'1018'), (N'1019', N'王磊', N'22', N'MALE', N'销售员', N'3000.00', N'1019'), (N'1020', N'刘洋', N'22', N'MALE', N'销售员', N'3000.00', N'1020'), (N'1021', N'王艳', N'22', N'FEMALE', N'销售员', N'3000.00', N'1021'), (N'1022', N'王勇', N'21', N'MALE', N'销售员', N'3000.00', N'1022'), (N'1023', N'李军', N'21', N'MALE', N'销售员', N'3000.00', N'1023'), (N'1024', N'张勇', N'21', N'MALE', N'销售员', N'3000.00', N'1024'), (N'1025', N'李杰', N'21', N'MALE', N'销售员', N'3000.00', N'1025'), (N'1026', N'张杰', N'20', N'MALE', N'销售员', N'3000.00', N'1026'), (N'1027', N'张磊', N'20', N'MALE', N'销售员', N'3000.00', N'1027'), (N'1028', N'王强', N'20', N'MALE', N'销售员', N'3000.00', N'1028'), (N'1029', N'李娟', N'20', N'FEMALE', N'销售员', N'3000.00', N'1029'), (N'1030', N'王军', N'20', N'MALE', N'销售员', N'3000.00', N'1030')
GO

COMMIT
GO


-- ----------------------------
-- Records of [jhi_authority]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[jhi_authority]  VALUES (N'ROLE_ADMIN'), (N'ROLE_USER')
GO

COMMIT
GO


-- ----------------------------
-- Records of [jhi_user]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[jhi_user]  VALUES (N'1', N'system', N'$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', N'System', N'System', N'system@localhost', N'', N'1', N'zh-cn', NULL, NULL, N'system', N'2018-05-07 09:30:41.183', NULL, N'system', NULL), (N'2', N'anonymoususer', N'$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', N'Anonymous', N'User', N'anonymous@localhost', N'', N'1', N'zh-cn', NULL, NULL, N'system', N'2018-05-07 09:30:41.183', NULL, N'system', NULL), (N'3', N'admin', N'$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', N'Administrator', N'Administrator', N'admin@localhost', N'', N'1', N'zh-cn', NULL, NULL, N'system', N'2018-05-07 09:30:41.183', NULL, N'system', NULL), (N'4', N'user', N'$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', N'User', N'User', N'user@localhost', N'', N'1', N'zh-cn', NULL, NULL, N'system', N'2018-05-07 09:30:41.183', NULL, N'system', NULL), (N'1001', N'zhang.wei', N'$2a$10$jvHkzkXQfU5VTe4qAIRXZuCOmAecTVPGdE9Da2N1EMjfdCvPIFxCa', N'Wei', N'Zhang', N'zhang.wei@bc.com', NULL, N'1', N'zh-cn', NULL, N'73455799188474315715', N'admin', N'2018-05-07 17:40:54.030', N'2018-05-07 17:40:53.980', N'admin', N'2018-05-07 17:42:42.623'), (N'1002', N'wang.wei', N'$2a$10$jwiJ5hUrvVNtyDsvvKeYne/gz74E3Yb6O4Rs2ZhTQivLIUVvyXY.e', N'Wei', N'Wang', N'wang.wei@bc.com', NULL, N'1', N'zh-cn', NULL, N'63001789439331364883', N'admin', N'2018-05-07 17:42:22.790', N'2018-05-07 17:42:22.786', N'admin', N'2018-05-07 17:42:22.790'), (N'1003', N'wang.fang', N'$2a$10$IcsQDFJyuhuRw761pcXR.OolN8JFjw4lcfWb0Z6RkX9hMNPU1oETa', N'Fang', N'Wang', N'wang.fang@bc.com', NULL, N'1', N'zh-cn', NULL, N'89843828800879812768', N'admin', N'2018-05-07 17:43:35.203', N'2018-05-07 17:43:35.203', N'admin', N'2018-05-07 17:43:35.203'), (N'1004', N'li.wei', N'$2a$10$3VVD0jX9u/sLFBZDwuXy0O.Q9z.XCSZpcpY6Bhs2m9bPTeZVNtlpS', N'Wei', N'Li', N'li.wei@bc.com', NULL, N'1', N'zh-cn', NULL, N'99687842868086471540', N'admin', N'2018-05-07 17:47:42.556', N'2018-05-07 17:47:42.556', N'admin', N'2018-05-07 17:47:42.556'), (N'1005', N'li.na', N'$2a$10$Cld8bFkCqBhHCMYime0ft.fPhUBBzOI2rZ/Yy3e.LWhRZ/tiP44t2', N'Na', N'Li', N'li.na@bc.com', NULL, N'1', N'zh-cn', NULL, N'11781740114956751725', N'admin', N'2018-05-07 17:48:24.683', N'2018-05-07 17:48:24.683', N'admin', N'2018-05-07 17:48:24.683'), (N'1006', N'zhang.min', N'$2a$10$lnrzzgwt3i2jM1qNxQNP5O8Fir3800qBOg1y8JlXV1uqfT4JF4BkO', N'Min', N'Zhang', N'zhang.min@bc.com', NULL, N'1', N'zh-cn', NULL, N'42601680865373166458', N'admin', N'2018-05-07 17:49:01.583', N'2018-05-07 17:49:01.583', N'admin', N'2018-05-07 17:49:01.583'), (N'1007', N'li.jing', N'$2a$10$EFoPS6wQvhCXyVlGPOJgnufvWNfg9kcxXyM/mNClbngJQq9MG/TAW', N'Jing', N'Li', N'li.jing@bc.com', NULL, N'1', N'zh-cn', NULL, N'96618540222767289290', N'admin', N'2018-05-07 17:49:27.356', N'2018-05-07 17:49:27.356', N'admin', N'2018-05-07 17:49:27.356'), (N'1008', N'wang.jing', N'$2a$10$ZTgK1ASKkDGhtSH16Jm8oOdkgvEnbewf.sOrHqcjzZgU.unK/0qX.', N'Jing', N'Wang', N'wang.jing@bc.com', NULL, N'1', N'zh-cn', NULL, N'84013793790498903457', N'admin', N'2018-05-07 17:50:07.943', N'2018-05-07 17:50:07.940', N'admin', N'2018-05-07 17:50:07.943'), (N'1009', N'liu.wei', N'$2a$10$XMdEAqw9CUZw8KU2LFbIUOhlPFVLZOU7qOmpBJ4Rh01EZgVRB8uea', N'Wei', N'Liu', N'liu.wei@bc.com', NULL, N'1', N'zh-cn', NULL, N'06241211143078621838', N'admin', N'2018-05-07 17:50:39.343', N'2018-05-07 17:50:39.340', N'admin', N'2018-05-07 17:50:39.343'), (N'1010', N'xiuying.wang', N'$2a$10$T8L.LDOXqNUb9P3szORsJ.l4impZICT7A.KEHbq/aBS3KelGBE7my', N'Xiu Ying', N'Wang', N'xiuying.wang@bc.com', NULL, N'1', N'zh-cn', NULL, N'37379886857981701724', N'admin', N'2018-05-07 17:51:16.726', N'2018-05-07 17:51:16.723', N'admin', N'2018-05-07 17:51:16.726'), (N'1011', N'zhang.li', N'$2a$10$Vr.z3O/BhhgMm2uwZF8MxOSKjWDyaaqjDk8LfuxcVNGVHg6HN0/fW', N'Li', N'Zhang', N'zhang.li@bc.com', NULL, N'1', N'zh-cn', NULL, N'78429767325366236606', N'admin', N'2018-05-07 17:51:42.163', N'2018-05-07 17:51:42.163', N'admin', N'2018-05-07 17:51:42.163'), (N'1012', N'li.xiuying', N'$2a$10$HYVO7NM08CHzWbCVooLbSOMswfFL1HEKJnFZ996wMpxgxxKAxX1xi', N'Xiu Ying', N'Li', N'li.xiuying@bc.com', NULL, N'1', N'zh-cn', NULL, N'37464997196191675478', N'admin', N'2018-05-07 17:52:29.363', N'2018-05-07 17:52:29.363', N'admin', N'2018-05-07 17:52:29.363'), (N'1013', N'wang.li', N'$2a$10$cHQMZcLXONCjB1n40igtpO2MiziQ2j4iNtgBNRR.1dWyx4dgClYXC', N'Li', N'Wang', N'wang.li@bc.com', NULL, N'1', N'zh-cn', NULL, N'28273542524283699980', N'admin', N'2018-05-07 17:52:50.406', N'2018-05-07 17:52:50.403', N'admin', N'2018-05-07 17:52:50.406'), (N'1014', N'zhang.jing', N'$2a$10$Iiq5hbEw4PcBSOPcH2X2E.ZF1nkk8HFy0ShT9KxAVudOxsKEWgcmi', N'Jing', N'Zhang', N'zhang.jing@bc.com', NULL, N'1', N'zh-cn', NULL, N'19411336893175390881', N'admin', N'2018-05-07 17:53:15.496', N'2018-05-07 17:53:15.493', N'admin', N'2018-05-07 17:53:15.496'), (N'1015', N'zhang.xiuying', N'$2a$10$IpnjmRwGGw2lM84r2.zo5.0/tGdkZt8yBlKkYIxsQFLsQf/S2UJSG', N'Xiu Ying', N'Zhang', N'zhang.xiuying@bc.com', NULL, N'1', N'zh-cn', NULL, N'31559095161257446825', N'admin', N'2018-05-07 17:53:36.710', N'2018-05-07 17:53:36.710', N'admin', N'2018-05-07 17:53:36.710'), (N'1016', N'li.qiang', N'$2a$10$Z9KTiVPeuoiOMKAb0GZU8eNTGRFyX6d9Uz33WOH89wFJ0wtSOrZiW', N'Qiang', N'Li', N'li.qiang@bc.com', NULL, N'1', N'zh-cn', NULL, N'25592767807112765440', N'admin', N'2018-05-07 17:53:57.556', N'2018-05-07 17:53:57.556', N'admin', N'2018-05-07 17:53:57.556'), (N'1017', N'wang.min', N'$2a$10$1IjWcHL20J2KzWbtkio49OsKd6PHOpPeDvIaXZZvLmWoKoTaceGbm', N'Min', N'Wang', N'wang.min@bc.com', NULL, N'1', N'zh-cn', NULL, N'65001043405238252931', N'admin', N'2018-05-07 17:54:17.906', N'2018-05-07 17:54:17.906', N'admin', N'2018-05-07 17:54:17.906'), (N'1018', N'li.min', N'$2a$10$S4hgeNsnGDCdcXMi.n9jhe.8TETmyE1BkbQWn8kpc987pZB4U0mTi', N'Min', N'Li', N'li.min@bc.com', NULL, N'1', N'zh-cn', NULL, N'99033799827509160101', N'admin', N'2018-05-07 17:54:34.106', N'2018-05-07 17:54:34.103', N'admin', N'2018-05-07 17:54:34.106'), (N'1019', N'wang.lei', N'$2a$10$lhOhRxjy2ryZ1E2eqEk71ONKnLN1gj9K.VD7bp6vuTCQ9GTOE/QaS', N'Lei', N'Wang', N'wang.lei@bc.com', NULL, N'1', N'zh-cn', NULL, N'21926759547268921255', N'admin', N'2018-05-07 17:54:53.853', N'2018-05-07 17:54:53.853', N'admin', N'2018-05-07 17:54:53.853'), (N'1020', N'liu.yang', N'$2a$10$suHz3qwjBYcxturYstqeSOO2dpEiUufpGSbOPp8cJZ8k8O3t0hkey', N'Yang', N'Liu', N'liu.yang@bc.com', NULL, N'1', N'zh-cn', NULL, N'91596102309030358467', N'admin', N'2018-05-07 17:55:52.626', N'2018-05-07 17:55:52.626', N'admin', N'2018-05-07 17:55:52.626'), (N'1021', N'wang.yan', N'$2a$10$9YhoU/71bphyLRp77NKIJeYPHqDbcPSfC87VjS.TvfTzmVZBafL/e', N'Yan', N'Wang', N'wang.yan@bc.com', NULL, N'1', N'zh-cn', NULL, N'42781666350732308199', N'admin', N'2018-05-07 17:56:33.503', N'2018-05-07 17:56:33.503', N'admin', N'2018-05-07 17:56:33.503'), (N'1022', N'wang.yong', N'$2a$10$2TsNQc/UOBdBNILIMFvG5e2x4ilmDSoE6Z0Y/br/J8ta8vaoeefou', N'Yong', N'Wang', N'wang.yong@bc.com', NULL, N'1', N'zh-cn', NULL, N'34048338305237464153', N'admin', N'2018-05-07 17:56:57.883', N'2018-05-07 17:56:57.883', N'admin', N'2018-05-07 17:56:57.883'), (N'1023', N'li.jun', N'$2a$10$Ug6k24lxg5wRxjiqWq42n.wc7TcVKjX3f.Ch71UNMluQhSfNm9Qyi', N'Jun', N'Li', N'li.jun@bc.com', NULL, N'1', N'zh-cn', NULL, N'40682962443232574413', N'admin', N'2018-05-07 17:57:14.463', N'2018-05-07 17:57:14.463', N'admin', N'2018-05-07 17:57:14.463'), (N'1024', N'zhang.yong', N'$2a$10$CL2OfqFjBf9L6hBXYvMujOdKhmp7aORQezNzeCWHePQOdpcwHdTf6', N'Yong', N'Zhang', N'zhang.yong@bc.com', NULL, N'1', N'zh-cn', NULL, N'58589799535563938503', N'admin', N'2018-05-07 17:57:51.330', N'2018-05-07 17:57:51.330', N'admin', N'2018-05-07 17:57:51.330'), (N'1025', N'li.jie', N'$2a$10$aCXc84zlM2UiwL4eTUsVOOtUpwOaXePgrAr0tqkR.xtefB5xMcm4C', N'Jie', N'Li', N'li.jie@bc.com', NULL, N'1', N'zh-cn', NULL, N'88997424205021182804', N'admin', N'2018-05-07 17:58:12.636', N'2018-05-07 17:58:12.636', N'admin', N'2018-05-07 17:58:12.636'), (N'1026', N'zhang.jie', N'$2a$10$o8g14M.TL9t9NyD.jodfreRxyZq5lkDjuCX/8zMrtctkWHE3niRc.', N'Jie', N'Zhang', N'zhang.jie@bc.com', NULL, N'1', N'zh-cn', NULL, N'86641698044501257132', N'admin', N'2018-05-07 17:58:32.796', N'2018-05-07 17:58:32.793', N'admin', N'2018-05-07 17:58:32.796'), (N'1027', N'zhang.lei', N'$2a$10$zD3kpAlHFmkcNl1NRQ4Qo.X20GMiP1Kvff.a4yJV8pzZo2EWKS21q', N'Lei', N'Zhang', N'zhang.lei@bc.com', NULL, N'1', N'zh-cn', NULL, N'53466608285455963525', N'admin', N'2018-05-07 17:58:55.910', N'2018-05-07 17:58:55.910', N'admin', N'2018-05-07 17:58:55.910'), (N'1028', N'wang.qiang', N'$2a$10$wAFZw0Og75GzWwKnpIBbFeJ7ooqbOmxqs7NK0JEaNM9T4g/yitaMK', N'Qiang', N'Wang', N'wang.qiang@bc.com', NULL, N'1', N'zh-cn', NULL, N'03274465135502177292', N'admin', N'2018-05-07 17:59:11.140', N'2018-05-07 17:59:11.136', N'admin', N'2018-05-07 17:59:11.140'), (N'1029', N'li.juan', N'$2a$10$C9V9iYCKKogPfoEItZie8.N5lUoJTfivINC0VmJYQ9L9udBfp.Uea', N'Juan', N'Li', N'li.juan@bc.com', NULL, N'1', N'zh-cn', NULL, N'32678808381933653533', N'admin', N'2018-05-07 17:59:28.780', N'2018-05-07 17:59:28.780', N'admin', N'2018-05-07 17:59:28.780'), (N'1030', N'wang.jun', N'$2a$10$tZp.zSq/tOcOC3uBixEJ3O7nIV.q4o37rtyjqKjv5ooTqViciQbcC', N'Jun', N'Wang', N'wang.jun@bc.com', NULL, N'1', N'zh-cn', NULL, N'92420963983872704618', N'admin', N'2018-05-07 17:59:50.616', N'2018-05-07 17:59:50.613', N'admin', N'2018-05-07 17:59:50.616')
GO

COMMIT
GO


-- ----------------------------
-- Records of [jhi_user_authority]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[jhi_user_authority]  VALUES (N'1', N'ROLE_ADMIN'), (N'1', N'ROLE_USER'), (N'3', N'ROLE_ADMIN'), (N'3', N'ROLE_USER'), (N'4', N'ROLE_USER'), (N'1001', N'ROLE_USER'), (N'1002', N'ROLE_USER'), (N'1003', N'ROLE_USER'), (N'1004', N'ROLE_USER'), (N'1005', N'ROLE_USER'), (N'1006', N'ROLE_USER'), (N'1007', N'ROLE_USER'), (N'1008', N'ROLE_USER'), (N'1009', N'ROLE_USER'), (N'1010', N'ROLE_USER'), (N'1011', N'ROLE_USER'), (N'1012', N'ROLE_USER'), (N'1013', N'ROLE_USER'), (N'1014', N'ROLE_USER'), (N'1015', N'ROLE_USER'), (N'1016', N'ROLE_USER'), (N'1017', N'ROLE_USER'), (N'1018', N'ROLE_USER'), (N'1019', N'ROLE_USER'), (N'1020', N'ROLE_USER'), (N'1021', N'ROLE_USER'), (N'1022', N'ROLE_USER'), (N'1023', N'ROLE_USER'), (N'1024', N'ROLE_USER'), (N'1025', N'ROLE_USER'), (N'1026', N'ROLE_USER'), (N'1027', N'ROLE_USER'), (N'1028', N'ROLE_USER'), (N'1029', N'ROLE_USER'), (N'1030', N'ROLE_USER')
GO

COMMIT
GO


-- ----------------------------
-- Records of [warehouse]
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[warehouse]  VALUES (N'1001', N'中心仓库', N'020-81111111'), (N'1002', N'东仓库', N'020-82222222'), (N'1003', N'西仓库', N'020-83333333')
GO

COMMIT
GO



