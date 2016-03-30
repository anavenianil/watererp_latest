-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.14.04.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema watererp
--

CREATE DATABASE IF NOT EXISTS watererp;
USE watererp;

--
-- Definition of table `watererp`.`application_txn`
--

DROP TABLE IF EXISTS `watererp`.`application_txn`;
CREATE TABLE  `watererp`.`application_txn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `home_or_office_number` int(11) DEFAULT NULL,
  `regional_number` int(11) DEFAULT NULL,
  `fax_number` int(11) DEFAULT NULL,
  `plot_number` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `village_executive_office` varchar(255) DEFAULT NULL,
  `village_executive_office_number` varchar(255) DEFAULT NULL,
  `po_box` varchar(255) DEFAULT NULL,
  `requested_date` timestamp NULL DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `file_number` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `category_master_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_applicationtxn_categorymaster_id` (`category_master_id`),
  KEY `fk_applicationtxn_customer_id` (`customer_id`),
  CONSTRAINT `fk_applicationtxn_categorymaster_id` FOREIGN KEY (`category_master_id`) REFERENCES `category_master` (`id`),
  CONSTRAINT `fk_applicationtxn_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`application_txn`
--

/*!40000 ALTER TABLE `application_txn` DISABLE KEYS */;
LOCK TABLES `application_txn` WRITE;
INSERT INTO `watererp`.`application_txn` VALUES  (14,'mohib khan',147,852,6698,'465','65445','54655','5465498','5511684','654','2016-03-28 00:00:00',NULL,'file1','2016-03-28 17:51:35','2016-03-28 17:51:35',1,3,NULL),
 (15,'mohib khan',4654,6544,5464,'66554','665544','654','654','6554','dfhdfh','2016-03-28 00:00:00',NULL,'file1','2016-03-28 18:26:19','2016-03-28 18:26:19',1,3,NULL),
 (16,'34634',346,346,436,'436','346','346','346','346','34634','2016-03-29 00:00:00','','534','2016-03-29 10:31:39','2016-03-29 10:31:39',0,3,NULL),
 (17,'34634',346,346,436,'436','346','346','346','346','34634','2016-03-29 00:00:00','','534','2016-03-29 10:34:06','2016-03-29 10:34:06',0,3,NULL),
 (18,'346',345,346,346,'346','346','346','346','346','345','2016-03-29 00:00:00','','353','2016-03-29 10:56:27','2016-03-29 10:56:27',0,4,NULL),
 (19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 11:23:28','2016-03-29 11:23:28',0,NULL,NULL),
 (20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 11:27:57','2016-03-29 11:27:57',0,NULL,NULL),
 (21,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 11:35:00','2016-03-29 11:35:00',0,NULL,NULL),
 (22,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 11:40:25','2016-03-29 11:40:25',0,NULL,NULL),
 (23,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 11:43:43','2016-03-29 11:43:43',0,NULL,NULL),
 (24,'wetwe',4364,43734,473,'473','347','347','347','437','etwt','2016-03-29 00:00:00','','skfha','2016-03-29 11:55:03','2016-03-29 11:55:03',0,3,NULL),
 (25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 12:23:57','2016-03-29 12:23:57',0,NULL,NULL),
 (26,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 12:26:07','2016-03-29 12:26:07',0,NULL,NULL),
 (27,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 12:36:26','2016-03-29 12:36:26',0,NULL,NULL),
 (28,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 12:38:43','2016-03-29 12:38:43',0,NULL,NULL),
 (29,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2016-03-29 12:43:24','2016-03-29 12:43:24',0,NULL,NULL),
 (30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/api/download/30_1e83e71a0573dc7b5e0ea2b193fe5998_eclipse error.png',NULL,'2016-03-29 12:46:13','2016-03-29 12:46:13',0,NULL,NULL),
 (31,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/api/download/31_318777e0424dce2c551f16e4f6ecda5e_eclipse error.png',NULL,'2016-03-29 12:56:58','2016-03-29 12:56:58',0,NULL,NULL),
 (32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/api/download/32_3eb4e628f276b918753ec6d0031b4060_eclipse error.png',NULL,'2016-03-29 13:10:34','2016-03-29 13:10:34',0,NULL,NULL),
 (33,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/api/download/33_a702c05423d95832e21d3823f6f9127f_eclipse error.png',NULL,'2016-03-29 14:27:14','2016-03-29 14:27:14',0,NULL,NULL),
 (34,'mohib khan',13,124,215,'125','15','125','125','125','346','2016-03-29 00:00:00','',NULL,'2016-03-29 14:48:54','2016-03-29 14:48:54',0,3,NULL),
 (35,'mohib',214,124,124,'124','124','214','124','214','342','2016-03-29 00:00:00','/api/download/35_af73bd9088c892842ff2d1fc18d71b5b_eclipse error.png',NULL,'2016-03-29 14:54:31','2016-03-29 14:54:31',1,3,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `application_txn` ENABLE KEYS */;


--
-- Definition of table `watererp`.`application_type_master`
--

DROP TABLE IF EXISTS `watererp`.`application_type_master`;
CREATE TABLE  `watererp`.`application_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_type` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`application_type_master`
--

/*!40000 ALTER TABLE `application_type_master` DISABLE KEYS */;
LOCK TABLES `application_type_master` WRITE;
INSERT INTO `watererp`.`application_type_master` VALUES  (1,'Charity Institute','2016-03-03 00:00:00','2016-03-03 00:00:00','1','abc','abc'),
 (2,'Feasibility Reciept','2016-03-03 00:00:00','2016-03-03 00:00:00','1','abc','abc'),
 (3,'Filling Station','2016-03-03 00:00:00','2016-03-03 00:00:00','1','abc','abc'),
 (4,'General','2016-03-03 00:00:00','2016-03-03 00:00:00','1','abc','abc');
UNLOCK TABLES;
/*!40000 ALTER TABLE `application_type_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`approval_details`
--

DROP TABLE IF EXISTS `watererp`.`approval_details`;
CREATE TABLE  `watererp`.`approval_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remarks` varchar(255) DEFAULT NULL,
  `approved_date` timestamp NULL DEFAULT NULL,
  `approved_emp_no` varchar(255) DEFAULT NULL,
  `approved_emp_name` varchar(255) DEFAULT NULL,
  `approved_emp_desig` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `feasibility_status_id` bigint(20) DEFAULT NULL,
  `designation_master_id` bigint(20) DEFAULT NULL,
  `application_txn_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_approvaldetails_customer_id` (`customer_id`),
  KEY `fk_approvaldetails_feasibilitystatus_id` (`feasibility_status_id`),
  KEY `fk_approvaldetails_designationmaster_id` (`designation_master_id`),
  KEY `fk_approvaldetails_applicationtxn_id` (`application_txn_id`),
  CONSTRAINT `fk_approvaldetails_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_approvaldetails_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_approvaldetails_designationmaster_id` FOREIGN KEY (`designation_master_id`) REFERENCES `designation_master` (`id`),
  CONSTRAINT `fk_approvaldetails_feasibilitystatus_id` FOREIGN KEY (`feasibility_status_id`) REFERENCES `feasibility_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`approval_details`
--

/*!40000 ALTER TABLE `approval_details` DISABLE KEYS */;
LOCK TABLES `approval_details` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `approval_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`cash_book_master`
--

DROP TABLE IF EXISTS `watererp`.`cash_book_master`;
CREATE TABLE  `watererp`.`cash_book_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cash_book_entry_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`cash_book_master`
--

/*!40000 ALTER TABLE `cash_book_master` DISABLE KEYS */;
LOCK TABLES `cash_book_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `cash_book_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`category_master`
--

DROP TABLE IF EXISTS `watererp`.`category_master`;
CREATE TABLE  `watererp`.`category_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`category_master`
--

/*!40000 ALTER TABLE `category_master` DISABLE KEYS */;
LOCK TABLES `category_master` WRITE;
INSERT INTO `watererp`.`category_master` VALUES  (1,'Domestic'),
 (2,'Institutional'),
 (3,'Commercial'),
 (4,'Industrial'),
 (5,'Kiosks');
UNLOCK TABLES;
/*!40000 ALTER TABLE `category_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`category_pipe_size_mapping`
--

DROP TABLE IF EXISTS `watererp`.`category_pipe_size_mapping`;
CREATE TABLE  `watererp`.`category_pipe_size_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_master_id` bigint(20) DEFAULT NULL,
  `pipe_size_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_categorypipesizemapping_categorymaster_id` (`category_master_id`),
  KEY `fk_categorypipesizemapping_pipesizemaster_id` (`pipe_size_master_id`),
  CONSTRAINT `fk_categorypipesizemapping_categorymaster_id` FOREIGN KEY (`category_master_id`) REFERENCES `category_master` (`id`),
  CONSTRAINT `fk_categorypipesizemapping_pipesizemaster_id` FOREIGN KEY (`pipe_size_master_id`) REFERENCES `pipe_size_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`category_pipe_size_mapping`
--

/*!40000 ALTER TABLE `category_pipe_size_mapping` DISABLE KEYS */;
LOCK TABLES `category_pipe_size_mapping` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `category_pipe_size_mapping` ENABLE KEYS */;


--
-- Definition of table `watererp`.`configuration_details`
--

DROP TABLE IF EXISTS `watererp`.`configuration_details`;
CREATE TABLE  `watererp`.`configuration_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`configuration_details`
--

/*!40000 ALTER TABLE `configuration_details` DISABLE KEYS */;
LOCK TABLES `configuration_details` WRITE;
INSERT INTO `watererp`.`configuration_details` VALUES  (1,'ADMIN','97',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `configuration_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`connection_type_master`
--

DROP TABLE IF EXISTS `watererp`.`connection_type_master`;
CREATE TABLE  `watererp`.`connection_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `connection_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`connection_type_master`
--

/*!40000 ALTER TABLE `connection_type_master` DISABLE KEYS */;
LOCK TABLES `connection_type_master` WRITE;
INSERT INTO `watererp`.`connection_type_master` VALUES  (1,'Sewerage Connection'),
 (2,'Water And Sewerage');
UNLOCK TABLES;
/*!40000 ALTER TABLE `connection_type_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`customer`
--

DROP TABLE IF EXISTS `watererp`.`customer`;
CREATE TABLE  `watererp`.`customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_date` timestamp NULL DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `house_no` varchar(255) DEFAULT NULL,
  `govt_official_no` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `block` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `section` varchar(255) DEFAULT NULL,
  `constituency` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel_office` varchar(255) DEFAULT NULL,
  `tel_home` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `file_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_filenumber_id` (`file_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
LOCK TABLES `customer` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `watererp`.`databasechangelog`
--

DROP TABLE IF EXISTS `watererp`.`databasechangelog`;
CREATE TABLE  `watererp`.`databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`databasechangelog`
--

/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
LOCK TABLES `databasechangelog` WRITE;
INSERT INTO `watererp`.`databasechangelog` VALUES  ('00000000000001','jhipster','classpath:config/liquibase/changelog/00000000000000_initial_schema.xml','2016-02-24 18:37:40',1,'EXECUTED','7:e5d421759980df5ea9b5cd2ebcfd994c','createTable, createIndex (x2), createTable (x2), addPrimaryKey, createTable, addForeignKeyConstraint (x3), loadData, dropDefaultValue, loadData (x2), createTable (x2), addPrimaryKey, createIndex (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160224131058','jhipster','classpath:config/liquibase/changelog/20160224131058_added_entity_SewerSize.xml','2016-02-26 10:35:06',2,'EXECUTED','7:be40d7c60d89c57e1672aaaed4915727','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229064640','jhipster','classpath:config/liquibase/changelog/20160229064640_added_entity_ApplicationTypeMaster.xml','2016-02-29 12:31:59',3,'EXECUTED','7:c1c9f7b3be47bd9dca371ebec3968605','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160229065150','jhipster','classpath:config/liquibase/changelog/20160229065150_added_entity_ConnectionTypeMaster.xml','2016-02-29 12:31:59',4,'EXECUTED','7:1d2213aedb239d233daaff9d017fe21f','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229065305','jhipster','classpath:config/liquibase/changelog/20160229065305_added_entity_CategoryMaster.xml','2016-02-29 12:32:00',5,'EXECUTED','7:bdbad9897632fcb3b27b84614eb65631','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229065514','jhipster','classpath:config/liquibase/changelog/20160229065514_added_entity_PipeSizeMaster.xml','2016-02-29 12:32:00',6,'EXECUTED','7:5530b835ccc14b682cdd22ef99c3cd80','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229065700','jhipster','classpath:config/liquibase/changelog/20160229065700_added_entity_CategoryPipeSizeMapping.xml','2016-02-29 12:32:01',7,'EXECUTED','7:10b74427e2abf13a1e46b02177b879c5','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160229070517','jhipster','classpath:config/liquibase/changelog/20160229070517_added_entity_FileNumber.xml','2016-02-29 14:06:34',8,'EXECUTED','7:d3e42819856cbf28b67058bba741014f','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229070707','jhipster','classpath:config/liquibase/changelog/20160229070707_added_entity_TransactionTypeMaster.xml','2016-02-29 14:06:34',9,'EXECUTED','7:afd37db16070bbc1db18016656893a32','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229070800','jhipster','classpath:config/liquibase/changelog/20160229070800_added_entity_CashBookMaster.xml','2016-02-29 14:06:34',10,'EXECUTED','7:d0decf027ebc34ee609eba6744ef51c2','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229070919','jhipster','classpath:config/liquibase/changelog/20160229070919_added_entity_PaymentTypes.xml','2016-02-29 14:06:34',11,'EXECUTED','7:ef4e1a7913b69b2869492e1ec5644d0d','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229071846','jhipster','classpath:config/liquibase/changelog/20160229071846_added_entity_Customer.xml','2016-02-29 14:06:35',12,'EXECUTED','7:a21f5fbe73cee6f0762e2f39af77124a','createTable, dropDefaultValue, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229072353','jhipster','classpath:config/liquibase/changelog/20160229072353_added_entity_ManageCashPoint.xml','2016-02-29 14:06:39',13,'EXECUTED','7:063f36a456a8a13982a11343a8a8dddd','createTable, dropDefaultValue, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL),
 ('20160229073210','jhipster','classpath:config/liquibase/changelog/20160229073210_added_entity_StatusMaster.xml','2016-02-29 14:06:40',14,'EXECUTED','7:37a09476506400cb3134316685ddbca6','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229073547','jhipster','classpath:config/liquibase/changelog/20160229073547_added_entity_DesignationMaster.xml','2016-02-29 14:06:41',15,'EXECUTED','7:127eba27413f9aa0a5e653084544e03a','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229073648','jhipster','classpath:config/liquibase/changelog/20160229073648_added_entity_FeasibilityStatus.xml','2016-02-29 14:06:41',16,'EXECUTED','7:190180bbcfa1c279424b098aae93a57c','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229074219','jhipster','classpath:config/liquibase/changelog/20160229074219_added_entity_ReAllotment.xml','2016-02-29 14:06:46',18,'EXECUTED','7:eb523405877dcf8d7dd83dc9199b8ea3','createTable, addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229084044','jhipster','classpath:config/liquibase/changelog/20160229084044_added_entity_ConfigurationDetails.xml','2016-02-29 15:03:13',20,'EXECUTED','7:839bd04dd36ec11b9c3f6f6db979cc63','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229085139','jhipster','classpath:config/liquibase/changelog/20160229085139_added_entity_DesigCategoryMaster.xml','2016-02-29 15:03:14',21,'EXECUTED','7:fdd173d8f6e7a9bc336c5a494f57f052','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229085352','jhipster','classpath:config/liquibase/changelog/20160229085352_added_entity_SubDesigCategoryMaster.xml','2016-02-29 15:03:15',22,'EXECUTED','7:c1f7021bd38767b73129e94983eb54cf','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229085805','jhipster','classpath:config/liquibase/changelog/20160229085805_added_entity_GroupMaster.xml','2016-02-29 15:03:16',23,'EXECUTED','7:4c67fc449b40cce038c464ecc57d55d0','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229090028','jhipster','classpath:config/liquibase/changelog/20160229090028_added_entity_OrgHierarchy.xml','2016-02-29 15:03:17',24,'EXECUTED','7:fd82c8c6649f4fc468f75eda51211b68','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229090217','jhipster','classpath:config/liquibase/changelog/20160229090217_added_entity_DesignationMappings.xml','2016-02-29 15:03:20',25,'EXECUTED','7:a32248161fb67e22dcb376886d1f8d32','createTable, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229090442','jhipster','classpath:config/liquibase/changelog/20160229090442_added_entity_DepartmentsHierarchy.xml','2016-02-29 15:03:21',26,'EXECUTED','7:f61491f7db54a4caa099623241809053','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229090611','jhipster','classpath:config/liquibase/changelog/20160229090611_added_entity_DepartmentTypeMaster.xml','2016-02-29 15:03:23',27,'EXECUTED','7:877befbbede5aa2ae023cef1c740eda5','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229091226','jhipster','classpath:config/liquibase/changelog/20160229091226_added_entity_DepartmentsMaster.xml','2016-02-29 15:03:25',28,'EXECUTED','7:269c35f7c51323070df2bb00cb146173','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229091411','jhipster','classpath:config/liquibase/changelog/20160229091411_added_entity_OrgRoleHierarchy.xml','2016-02-29 15:03:26',29,'EXECUTED','7:2917d963a991b8d25ed698800fb6d36c','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229092048','jhipster','classpath:config/liquibase/changelog/20160229092048_added_entity_OrgRoleInstance.xml','2016-02-29 15:03:29',30,'EXECUTED','7:775abb35fbdc8d21cefae3e86deeb7e2','createTable, dropDefaultValue (x3), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229092244','jhipster','classpath:config/liquibase/changelog/20160229092244_added_entity_OrgRolesMaster.xml','2016-02-29 15:03:30',31,'EXECUTED','7:88f6b41a493861a105157b995199f4a1','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229092636','jhipster','classpath:config/liquibase/changelog/20160229092636_added_entity_WorkflowTypeMaster.xml','2016-02-29 15:03:31',32,'EXECUTED','7:c51656b7eeb13d3c2e615135ac3b1361','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229093926','jhipster','classpath:config/liquibase/changelog/20160229093926_added_entity_EmpMaster.xml','2016-02-29 15:12:23',33,'EXECUTED','7:3252e1c25b98fa713f2887568665564d','createTable, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL),
 ('20160229095205','jhipster','classpath:config/liquibase/changelog/20160229095205_added_entity_EmpRoleMapping.xml','2016-02-29 15:49:21',34,'EXECUTED','7:fc3da7a2667930eac07790ad970de8c1','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229095400','jhipster','classpath:config/liquibase/changelog/20160229095400_added_entity_WorkflowMaster.xml','2016-02-29 15:49:22',35,'EXECUTED','7:9e74b57e21636b130a51342ef513fa9b','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229095700','jhipster','classpath:config/liquibase/changelog/20160229095700_added_entity_RequestMaster.xml','2016-02-29 15:49:23',36,'EXECUTED','7:4ea16d61dc9c1631995ebcaa24172261','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229095841','jhipster','classpath:config/liquibase/changelog/20160229095841_added_entity_WorkflowStageMaster.xml','2016-02-29 15:49:24',37,'EXECUTED','7:b232f3ff1a94a47509fc8dcf9d99f1d4','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229095927','jhipster','classpath:config/liquibase/changelog/20160229095927_added_entity_WorkflowRelations.xml','2016-02-29 15:49:25',38,'EXECUTED','7:8f71bbc6064b9fe8aa41799e214bbc10','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229100042','jhipster','classpath:config/liquibase/changelog/20160229100042_added_entity_WorkflowRelationships.xml','2016-02-29 15:49:26',39,'EXECUTED','7:af9db222b3e3f53e4df472506e8d6995','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229100254','jhipster','classpath:config/liquibase/changelog/20160229100254_added_entity_ReqDesigWorkflowMapping.xml','2016-02-29 15:49:29',40,'EXECUTED','7:1e7a0c353f3ba594bbb665b568452d48','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229100431','jhipster','classpath:config/liquibase/changelog/20160229100431_added_entity_ReqOrgWorkflowMapping.xml','2016-02-29 15:49:32',41,'EXECUTED','7:f3cade643b99f3ca2eaf4c44cf764f9c','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229100821','jhipster','classpath:config/liquibase/changelog/20160229100821_added_entity_RoleWorkflowMapping.xml','2016-02-29 15:49:35',42,'EXECUTED','7:25c22c3ba0d09c89818f178cb23b9aea','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229100952','jhipster','classpath:config/liquibase/changelog/20160229100952_added_entity_RequestWorkflowMapping.xml','2016-02-29 15:49:37',43,'EXECUTED','7:e2e3affdf6fd6213512081f5a7c52acd','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229101231','jhipster','classpath:config/liquibase/changelog/20160229101231_added_entity_WorkflowTxnDetails.xml','2016-02-29 15:49:38',44,'EXECUTED','7:ffd53ef669a1b6a5fdacfdfc2c63c8d4','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229111312','jhipster','classpath:config/liquibase/changelog/20160229111312_added_entity_Workflow.xml','2016-02-29 16:56:15',45,'EXECUTED','7:aa3bbe903f8a96b777e321dc647773ab','createTable, addForeignKeyConstraint (x10)','',NULL,'3.4.2',NULL,NULL),
 ('20160229111821','jhipster','classpath:config/liquibase/changelog/20160229111821_added_entity_RequestWorkflowHistory.xml','2016-02-29 16:56:21',46,'EXECUTED','7:df037b52eebabe97bca6601174750057','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x7)','',NULL,'3.4.2',NULL,NULL),
 ('20160229074020','jhipster','classpath:config/liquibase/changelog/20160229074020_added_entity_ApprovalDetails.xml','2016-03-08 14:21:51',47,'EXECUTED','7:37e6360d74c522cda74763685e8f3e6e','createTable, dropDefaultValue, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160309103544','jhipster','classpath:config/liquibase/changelog/20160309103544_added_entity_Module.xml','2016-03-09 16:14:40',48,'EXECUTED','7:ae553e293db4d292b225cd36fb0ba43c','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160309103701','jhipster','classpath:config/liquibase/changelog/20160309103701_added_entity_MenuItem.xml','2016-03-09 16:14:41',49,'EXECUTED','7:bd9888add9c5af34bcdca93e50add4d1','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160309103829','jhipster','classpath:config/liquibase/changelog/20160309103829_added_entity_Url.xml','2016-03-09 16:14:41',50,'EXECUTED','7:1889bdedffb7df6fe11f9bc33d0efa35','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160309104001','jhipster','classpath:config/liquibase/changelog/20160309104001_added_entity_MenuItem2Url.xml','2016-03-09 16:14:43',51,'EXECUTED','7:902843a076e3bb258db90badb9e1dc9a','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160309104200','jhipster','classpath:config/liquibase/changelog/20160309104200_added_entity_Module2MenuItem.xml','2016-03-09 16:14:45',52,'EXECUTED','7:9824330082db5729d7b5d6bf940348df','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160309105304','jhipster','classpath:config/liquibase/changelog/20160309105304_added_entity_Url2Role.xml','2016-03-09 16:34:47',53,'EXECUTED','7:10331a2dfcdaf6902d65c17510f31bc4','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160311094234','jhipster','classpath:config/liquibase/changelog/20160311094234_added_entity_SchemeMaster.xml','2016-03-11 15:21:26',54,'EXECUTED','7:af80987658cadbe9d18ef393fd5559c7','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffCategoryMaster.xml','2016-03-11 15:21:26',55,'EXECUTED','7:76b2ec672aee81aa1c61119392f20b5d','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094550','jhipster','classpath:config/liquibase/changelog/20160311094550_added_entity_MakeOfPipe.xml','2016-03-11 15:21:26',56,'EXECUTED','7:0aae809bc0a38e5aa294a887f735acb8','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094820','jhipster','classpath:config/liquibase/changelog/20160311094820_added_entity_MainWaterSize.xml','2016-03-11 15:21:26',57,'EXECUTED','7:0e853a5e0c7721503d0203d88333bd91','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094904','jhipster','classpath:config/liquibase/changelog/20160311094904_added_entity_MainSewerageSize.xml','2016-03-11 15:21:27',58,'EXECUTED','7:03b9b58c058199566e8d55fd5635b97d','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311095014','jhipster','classpath:config/liquibase/changelog/20160311095014_added_entity_DocketCode.xml','2016-03-11 15:21:27',59,'EXECUTED','7:9b3b9b3180ffc95d73eadf3d1294c5e0','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311100455','jhipster','classpath:config/liquibase/changelog/20160311100455_added_entity_FeasibilityStudy.xml','2016-03-11 15:35:47',60,'EXECUTED','7:02ff1a8ab9783cbd5eaec740ffa6d9cc','createTable, addForeignKeyConstraint (x12)','',NULL,'3.4.2',NULL,NULL),
 ('20160317071301','jhipster','classpath:config/liquibase/changelog/20160317071301_added_entity_ItemDetails.xml','2016-03-17 13:06:23',62,'EXECUTED','7:8d0c73c28056a5186d39bbed2ac7f38f','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160317100956','jhipster','classpath:config/liquibase/changelog/20160317100956_added_entity_ItemRequired.xml','2016-03-17 15:41:55',63,'EXECUTED','7:94df0c0621fc50b07f19c839c8c3e99d','createTable, addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160324101153','jhipster','classpath:config/liquibase/changelog/20160324101153_added_entity_DivisionMaster.xml','2016-03-24 15:50:09',65,'EXECUTED','7:49f67b19188786accf52558a610c39c8','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160324101330','jhipster','classpath:config/liquibase/changelog/20160324101330_added_entity_ZoneMaster.xml','2016-03-24 15:50:10',66,'EXECUTED','7:7855073214c1c3e194f1b4125d049f25','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160324101502','jhipster','classpath:config/liquibase/changelog/20160324101502_added_entity_StreetMaster.xml','2016-03-24 15:50:11',67,'EXECUTED','7:7834259d37eff0b5a2feb5555c398f82','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229075018','jhipster','classpath:config/liquibase/changelog/20160229075018_added_entity_ApplicationTxn.xml','2016-03-24 17:07:52',68,'EXECUTED','7:d1c9aa67d48a51ebe93625f1a2a43eb5','createTable, dropDefaultValue (x3), addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160324105452','jhipster','classpath:config/liquibase/changelog/20160324105452_added_entity_FeasibilityStudy.xml','2016-03-28 13:17:41',71,'EXECUTED','7:aa06f800520a2b8bfef4654df0572d3d','createTable, dropDefaultValue (x6), addForeignKeyConstraint (x9)','',NULL,'3.4.2',NULL,NULL),
 ('20160328051857','jhipster','classpath:config/liquibase/changelog/20160328051857_added_entity_FileUploadMaster.xml','2016-03-28 18:56:06',72,'EXECUTED','7:9fd8cdb67e4d8cc76478621ecad2012b','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160328131639','jhipster','classpath:config/liquibase/changelog/20160328131639_added_entity_FileUploadMaster.xml','2016-03-28 18:57:23',73,'EXECUTED','7:03be8b2eae9799aec199f76ed49d55a1','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160330052554','jhipster','classpath:config/liquibase/changelog/20160330052554_added_entity_ItemCategoryMaster.xml','2016-03-30 11:28:44',74,'EXECUTED','7:360b2d16421c3564bb94f0775648580d','createTable, dropDefaultValue (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160330052808','jhipster','classpath:config/liquibase/changelog/20160330052808_added_entity_ItemSubCategoryMaster.xml','2016-03-30 11:28:45',75,'EXECUTED','7:77c40a173637ee134f58cd079c67aead','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160330053134','jhipster','classpath:config/liquibase/changelog/20160330053134_added_entity_ItemCodeMaster.xml','2016-03-30 11:28:47',76,'EXECUTED','7:a4e7f1b1ca5ebdb94b5d29dd9402b337','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330053533','jhipster','classpath:config/liquibase/changelog/20160330053533_added_entity_ItemCompanyMaster.xml','2016-03-30 11:28:47',77,'EXECUTED','7:a250630d5ce589a445e309d152c4d935','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330054006','jhipster','classpath:config/liquibase/changelog/20160330054006_added_entity_ItemSubCodeMaster.xml','2016-03-30 11:28:48',78,'EXECUTED','7:b3fbded42d2b493c86ffbd3649e87ceb','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330054928','jhipster','classpath:config/liquibase/changelog/20160330054928_added_entity_MaterialMaster.xml','2016-03-30 11:28:48',79,'EXECUTED','7:983fd1500db22988ac26254293296036','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330055745','jhipster','classpath:config/liquibase/changelog/20160330055745_added_entity_SibEntry.xml','2016-03-30 11:28:49',80,'EXECUTED','7:da7d47c69cd368ff14809fae56104b33','createTable, dropDefaultValue (x9)','',NULL,'3.4.2',NULL,NULL),
 ('20160315083447','jhipster','classpath:config/liquibase/changelog/20160315083447_added_entity_Proceedings.xml','2016-03-30 15:44:38',81,'EXECUTED','7:dd7e9c257108ab49866b8ad0020e73bb','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160330092113','jhipster','classpath:config/liquibase/changelog/20160330092113_added_entity_ItemRequired.xml','2016-03-30 15:44:41',82,'EXECUTED','7:f7c7a5118cd1b8dad31bfb61a4aecfff','createTable, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160330095504','jhipster','classpath:config/liquibase/changelog/20160330095504_added_entity_Proceedings.xml','2016-03-30 15:58:29',84,'EXECUTED','7:44905c1def8f34420554addf34bf1c51','createTable, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL),
 ('20160330093457','jhipster','classpath:config/liquibase/changelog/20160330093457_added_entity_PercentageMaster.xml','2016-03-30 19:02:55',85,'EXECUTED','7:939ffd4e62fb70f2288fd5eb2055b778','createTable','',NULL,'3.4.2',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;


--
-- Definition of table `watererp`.`databasechangeloglock`
--

DROP TABLE IF EXISTS `watererp`.`databasechangeloglock`;
CREATE TABLE  `watererp`.`databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`databasechangeloglock`
--

/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
LOCK TABLES `databasechangeloglock` WRITE;
INSERT INTO `watererp`.`databasechangeloglock` VALUES  (1,0x00,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;


--
-- Definition of table `watererp`.`department_type_master`
--

DROP TABLE IF EXISTS `watererp`.`department_type_master`;
CREATE TABLE  `watererp`.`department_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_departmenttypemaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_departmenttypemaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`department_type_master`
--

/*!40000 ALTER TABLE `department_type_master` DISABLE KEYS */;
LOCK TABLES `department_type_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `department_type_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`departments_hierarchy`
--

DROP TABLE IF EXISTS `watererp`.`departments_hierarchy`;
CREATE TABLE  `watererp`.`departments_hierarchy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_hierarchy_name` varchar(255) DEFAULT NULL,
  `parent_dept_hierarchy_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_departmentshierarchy_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_departmentshierarchy_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`departments_hierarchy`
--

/*!40000 ALTER TABLE `departments_hierarchy` DISABLE KEYS */;
LOCK TABLES `departments_hierarchy` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `departments_hierarchy` ENABLE KEYS */;


--
-- Definition of table `watererp`.`departments_master`
--

DROP TABLE IF EXISTS `watererp`.`departments_master`;
CREATE TABLE  `watererp`.`departments_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) DEFAULT NULL,
  `parent_deparment` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `departments_hierarchy_id` bigint(20) DEFAULT NULL,
  `department_type_master_id` bigint(20) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_departmentsmaster_departmentshierarchy_id` (`departments_hierarchy_id`),
  KEY `fk_departmentsmaster_departmenttypemaster_id` (`department_type_master_id`),
  KEY `fk_departmentsmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_departmentsmaster_departmentshierarchy_id` FOREIGN KEY (`departments_hierarchy_id`) REFERENCES `departments_hierarchy` (`id`),
  CONSTRAINT `fk_departmentsmaster_departmenttypemaster_id` FOREIGN KEY (`department_type_master_id`) REFERENCES `department_type_master` (`id`),
  CONSTRAINT `fk_departmentsmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`departments_master`
--

/*!40000 ALTER TABLE `departments_master` DISABLE KEYS */;
LOCK TABLES `departments_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `departments_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`desig_category_master`
--

DROP TABLE IF EXISTS `watererp`.`desig_category_master`;
CREATE TABLE  `watererp`.`desig_category_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_desigcategorymaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_desigcategorymaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`desig_category_master`
--

/*!40000 ALTER TABLE `desig_category_master` DISABLE KEYS */;
LOCK TABLES `desig_category_master` WRITE;
INSERT INTO `watererp`.`desig_category_master` VALUES  (1,'DRDS','2016-03-03 00:00:00','2016-03-03 00:00:00','Scientists','123',1,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `desig_category_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`designation_mappings`
--

DROP TABLE IF EXISTS `watererp`.`designation_mappings`;
CREATE TABLE  `watererp`.`designation_mappings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `desig_category_master_id` bigint(20) DEFAULT NULL,
  `sub_desig_category_master_id` bigint(20) DEFAULT NULL,
  `designation_master_id` bigint(20) DEFAULT NULL,
  `group_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_designationmappings_desigcategorymaster_id` (`desig_category_master_id`),
  KEY `fk_designationmappings_subdesigcategorymaster_id` (`sub_desig_category_master_id`),
  KEY `fk_designationmappings_designationmaster_id` (`designation_master_id`),
  KEY `fk_designationmappings_groupmaster_id` (`group_master_id`),
  CONSTRAINT `fk_designationmappings_desigcategorymaster_id` FOREIGN KEY (`desig_category_master_id`) REFERENCES `desig_category_master` (`id`),
  CONSTRAINT `fk_designationmappings_designationmaster_id` FOREIGN KEY (`designation_master_id`) REFERENCES `designation_master` (`id`),
  CONSTRAINT `fk_designationmappings_groupmaster_id` FOREIGN KEY (`group_master_id`) REFERENCES `group_master` (`id`),
  CONSTRAINT `fk_designationmappings_subdesigcategorymaster_id` FOREIGN KEY (`sub_desig_category_master_id`) REFERENCES `sub_desig_category_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`designation_mappings`
--

/*!40000 ALTER TABLE `designation_mappings` DISABLE KEYS */;
LOCK TABLES `designation_mappings` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `designation_mappings` ENABLE KEYS */;


--
-- Definition of table `watererp`.`designation_master`
--

DROP TABLE IF EXISTS `watererp`.`designation_master`;
CREATE TABLE  `watererp`.`designation_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `service_type` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `desigalias` varchar(255) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_designationmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_designationmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`designation_master`
--

/*!40000 ALTER TABLE `designation_master` DISABLE KEYS */;
LOCK TABLES `designation_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `designation_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`division_master`
--

DROP TABLE IF EXISTS `watererp`.`division_master`;
CREATE TABLE  `watererp`.`division_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `division_name` varchar(255) DEFAULT NULL,
  `division_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`division_master`
--

/*!40000 ALTER TABLE `division_master` DISABLE KEYS */;
LOCK TABLES `division_master` WRITE;
INSERT INTO `watererp`.`division_master` VALUES  (1,'D1','DCode1'),
 (2,'D2','DCode2'),
 (3,'D3','DCode3'),
 (4,'D4','DCode4'),
 (5,'D5','DCode5'),
 (6,'D6','DCode6'),
 (7,'D7','DCode7'),
 (8,'D8','DCode8'),
 (9,'D9','DCode9'),
 (10,'D10','DCode10');
UNLOCK TABLES;
/*!40000 ALTER TABLE `division_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`docket_code`
--

DROP TABLE IF EXISTS `watererp`.`docket_code`;
CREATE TABLE  `watererp`.`docket_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`docket_code`
--

/*!40000 ALTER TABLE `docket_code` DISABLE KEYS */;
LOCK TABLES `docket_code` WRITE;
INSERT INTO `watererp`.`docket_code` VALUES  (1,'Docket code 1'),
 (2,'Docket code 2');
UNLOCK TABLES;
/*!40000 ALTER TABLE `docket_code` ENABLE KEYS */;


--
-- Definition of table `watererp`.`emp_master`
--

DROP TABLE IF EXISTS `watererp`.`emp_master`;
CREATE TABLE  `watererp`.`emp_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `office_id_id` bigint(20) DEFAULT NULL,
  `designation_master_id` bigint(20) DEFAULT NULL,
  `directorate_id_id` bigint(20) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_empmaster_user_id` (`user_id`),
  KEY `fk_empmaster_officeid_id` (`office_id_id`),
  KEY `fk_empmaster_designationmaster_id` (`designation_master_id`),
  KEY `fk_empmaster_directorateid_id` (`directorate_id_id`),
  KEY `fk_empmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_empmaster_designationmaster_id` FOREIGN KEY (`designation_master_id`) REFERENCES `designation_master` (`id`),
  CONSTRAINT `fk_empmaster_directorateid_id` FOREIGN KEY (`directorate_id_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_empmaster_officeid_id` FOREIGN KEY (`office_id_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_empmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_empmaster_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`emp_master`
--

/*!40000 ALTER TABLE `emp_master` DISABLE KEYS */;
LOCK TABLES `emp_master` WRITE;
INSERT INTO `watererp`.`emp_master` VALUES  (1,6,1,NULL,1,2),
 (2,7,2,NULL,2,2),
 (3,8,3,NULL,3,2),
 (4,9,4,NULL,4,2),
 (5,10,5,NULL,5,2),
 (6,11,6,NULL,6,2),
 (7,12,7,NULL,7,2),
 (8,13,8,NULL,8,2),
 (9,14,9,NULL,9,2),
 (10,15,10,NULL,10,2),
 (11,16,11,NULL,11,2),
 (12,5,25,NULL,25,2),
 (13,24,19,NULL,19,2),
 (14,17,12,NULL,12,2),
 (16,18,13,NULL,13,2),
 (17,19,14,NULL,14,2),
 (18,20,15,NULL,15,2),
 (20,21,16,NULL,16,2),
 (21,22,17,NULL,17,2),
 (22,23,18,NULL,18,2),
 (23,24,19,NULL,19,2),
 (24,25,20,NULL,20,2),
 (25,26,21,NULL,21,2),
 (26,27,22,NULL,22,2),
 (27,28,23,NULL,23,2),
 (28,29,24,NULL,24,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `emp_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`emp_role_mapping`
--

DROP TABLE IF EXISTS `watererp`.`emp_role_mapping`;
CREATE TABLE  `watererp`.`emp_role_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `internal_division` varchar(255) DEFAULT NULL,
  `internal_role` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `parent_role_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `parent_user_id` bigint(20) DEFAULT NULL,
  `org_role_instance_id` bigint(20) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emprolemapping_user_id` (`user_id`),
  KEY `fk_emprolemapping_parentuser_id` (`parent_user_id`),
  KEY `fk_emprolemapping_orgroleinstance_id` (`org_role_instance_id`),
  KEY `fk_emprolemapping_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_emprolemapping_orgroleinstance_id` FOREIGN KEY (`org_role_instance_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_emprolemapping_parentuser_id` FOREIGN KEY (`parent_user_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_emprolemapping_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_emprolemapping_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`emp_role_mapping`
--

/*!40000 ALTER TABLE `emp_role_mapping` DISABLE KEYS */;
LOCK TABLES `emp_role_mapping` WRITE;
INSERT INTO `watererp`.`emp_role_mapping` VALUES  (1,'','','2016-03-18 00:00:00','2016-03-18 00:00:00',0,6,NULL,1,2),
 (2,'','','2016-03-18 00:00:00','2016-03-18 00:00:00',0,7,NULL,2,2),
 (3,NULL,NULL,NULL,NULL,NULL,8,NULL,3,2),
 (4,NULL,NULL,NULL,NULL,NULL,9,NULL,4,2),
 (5,NULL,NULL,NULL,NULL,NULL,10,NULL,5,2),
 (6,NULL,NULL,NULL,NULL,NULL,11,NULL,6,2),
 (7,NULL,NULL,NULL,NULL,NULL,12,NULL,7,2),
 (8,NULL,NULL,NULL,NULL,NULL,13,NULL,8,2),
 (9,NULL,NULL,NULL,NULL,NULL,14,NULL,9,2),
 (10,NULL,NULL,NULL,NULL,NULL,15,NULL,10,2),
 (11,NULL,NULL,NULL,NULL,NULL,16,NULL,11,2),
 (12,NULL,NULL,NULL,NULL,NULL,17,NULL,12,2),
 (13,NULL,NULL,NULL,NULL,NULL,18,NULL,13,2),
 (14,NULL,NULL,NULL,NULL,NULL,19,NULL,14,2),
 (15,NULL,NULL,NULL,NULL,NULL,20,NULL,15,2),
 (16,NULL,NULL,NULL,NULL,NULL,21,NULL,16,2),
 (17,NULL,NULL,NULL,NULL,NULL,22,NULL,17,2),
 (18,NULL,NULL,NULL,NULL,NULL,23,NULL,18,2),
 (19,NULL,NULL,NULL,NULL,NULL,24,NULL,19,2),
 (20,NULL,NULL,NULL,NULL,NULL,25,NULL,20,2),
 (22,NULL,NULL,NULL,NULL,NULL,26,NULL,21,2),
 (23,NULL,NULL,NULL,NULL,NULL,27,NULL,22,2),
 (25,NULL,NULL,NULL,NULL,NULL,28,NULL,23,2),
 (26,'',NULL,'2016-03-18 00:00:00',NULL,NULL,29,NULL,24,2),
 (27,NULL,NULL,NULL,NULL,NULL,5,NULL,25,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `emp_role_mapping` ENABLE KEYS */;


--
-- Definition of table `watererp`.`feasibility_status`
--

DROP TABLE IF EXISTS `watererp`.`feasibility_status`;
CREATE TABLE  `watererp`.`feasibility_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`feasibility_status`
--

/*!40000 ALTER TABLE `feasibility_status` DISABLE KEYS */;
LOCK TABLES `feasibility_status` WRITE;
INSERT INTO `watererp`.`feasibility_status` VALUES  (1,'Application Accepted');
UNLOCK TABLES;
/*!40000 ALTER TABLE `feasibility_status` ENABLE KEYS */;


--
-- Definition of table `watererp`.`feasibility_study`
--

DROP TABLE IF EXISTS `watererp`.`feasibility_study`;
CREATE TABLE  `watererp`.`feasibility_study` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` timestamp NULL,
  `modified_date` timestamp NULL,
  `prepared_date` timestamp NULL,
  `zonal_head_approval_date` timestamp NULL,
  `dept_head_inspected_date` timestamp NULL,
  `operation_mangrapprove_date` timestamp NULL,
  `status` int(11) DEFAULT NULL,
  `division_master_id` bigint(20) DEFAULT NULL,
  `zone_master_id` bigint(20) DEFAULT NULL,
  `street_master_id` bigint(20) DEFAULT NULL,
  `application_txn_id` bigint(20) DEFAULT NULL,
  `prepared_by_id` bigint(20) DEFAULT NULL,
  `approved_by_zonal_head_id` bigint(20) DEFAULT NULL,
  `inspection_by_department_head_id` bigint(20) DEFAULT NULL,
  `approved_by_operation_manager_id` bigint(20) DEFAULT NULL,
  `category_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feasibilitystudy_divisionmaster_id` (`division_master_id`),
  KEY `fk_feasibilitystudy_zonemaster_id` (`zone_master_id`),
  KEY `fk_feasibilitystudy_streetmaster_id` (`street_master_id`),
  KEY `fk_feasibilitystudy_applicationtxn_id` (`application_txn_id`),
  KEY `fk_feasibilitystudy_preparedby_id` (`prepared_by_id`),
  KEY `fk_feasibilitystudy_approvedbyzonalhead_id` (`approved_by_zonal_head_id`),
  KEY `fk_feasibilitystudy_inspectionbydepartmenthead_id` (`inspection_by_department_head_id`),
  KEY `fk_feasibilitystudy_approvedbyoperationmanager_id` (`approved_by_operation_manager_id`),
  KEY `fk_feasibilitystudy_categorymaster_id` (`category_master_id`),
  CONSTRAINT `fk_feasibilitystudy_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_feasibilitystudy_approvedbyoperationmanager_id` FOREIGN KEY (`approved_by_operation_manager_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_feasibilitystudy_approvedbyzonalhead_id` FOREIGN KEY (`approved_by_zonal_head_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_feasibilitystudy_categorymaster_id` FOREIGN KEY (`category_master_id`) REFERENCES `category_master` (`id`),
  CONSTRAINT `fk_feasibilitystudy_divisionmaster_id` FOREIGN KEY (`division_master_id`) REFERENCES `division_master` (`id`),
  CONSTRAINT `fk_feasibilitystudy_inspectionbydepartmenthead_id` FOREIGN KEY (`inspection_by_department_head_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_feasibilitystudy_preparedby_id` FOREIGN KEY (`prepared_by_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_feasibilitystudy_streetmaster_id` FOREIGN KEY (`street_master_id`) REFERENCES `street_master` (`id`),
  CONSTRAINT `fk_feasibilitystudy_zonemaster_id` FOREIGN KEY (`zone_master_id`) REFERENCES `zone_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`feasibility_study`
--

/*!40000 ALTER TABLE `feasibility_study` DISABLE KEYS */;
LOCK TABLES `feasibility_study` WRITE;
INSERT INTO `watererp`.`feasibility_study` VALUES  (1,'2016-03-29 18:26:45','2016-03-29 18:26:45','2016-03-29 00:00:00','2016-03-30 00:00:00','2016-03-31 00:00:00','2016-04-01 00:00:00',0,2,4,7,35,9,13,15,16,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `feasibility_study` ENABLE KEYS */;


--
-- Definition of table `watererp`.`file_number`
--

DROP TABLE IF EXISTS `watererp`.`file_number`;
CREATE TABLE  `watererp`.`file_number` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_no` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`file_number`
--

/*!40000 ALTER TABLE `file_number` DISABLE KEYS */;
LOCK TABLES `file_number` WRITE;
INSERT INTO `watererp`.`file_number` VALUES  (1,'F_101'),
 (2,'F_102'),
 (3,'F_103'),
 (4,'F_104'),
 (5,'F_105');
UNLOCK TABLES;
/*!40000 ALTER TABLE `file_number` ENABLE KEYS */;


--
-- Definition of table `watererp`.`file_upload_master`
--

DROP TABLE IF EXISTS `watererp`.`file_upload_master`;
CREATE TABLE  `watererp`.`file_upload_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `photo` blob,
  `photo_content_type` varchar(50) NOT NULL,
  `text_file` longtext,
  `binary_file` blob,
  `binary_file_content_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`file_upload_master`
--

/*!40000 ALTER TABLE `file_upload_master` DISABLE KEYS */;
LOCK TABLES `file_upload_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `file_upload_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`group_master`
--

DROP TABLE IF EXISTS `watererp`.`group_master`;
CREATE TABLE  `watererp`.`group_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_groupmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_groupmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`group_master`
--

/*!40000 ALTER TABLE `group_master` DISABLE KEYS */;
LOCK TABLES `group_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `group_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`item_category_master`
--

DROP TABLE IF EXISTS `watererp`.`item_category_master`;
CREATE TABLE  `watererp`.`item_category_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL,
  `last_modified_date` timestamp NULL,
  `category_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_category_master`
--

/*!40000 ALTER TABLE `item_category_master` DISABLE KEYS */;
LOCK TABLES `item_category_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `item_category_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`item_code_master`
--

DROP TABLE IF EXISTS `watererp`.`item_code_master`;
CREATE TABLE  `watererp`.`item_code_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL,
  `last_modified_date` timestamp NULL,
  `item_category_master_id` bigint(20) DEFAULT NULL,
  `item_sub_category_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_itemcodemaster_itemcategorymaster_id` (`item_category_master_id`),
  KEY `fk_itemcodemaster_itemsubcategorymaster_id` (`item_sub_category_master_id`),
  CONSTRAINT `fk_itemcodemaster_itemcategorymaster_id` FOREIGN KEY (`item_category_master_id`) REFERENCES `item_category_master` (`id`),
  CONSTRAINT `fk_itemcodemaster_itemsubcategorymaster_id` FOREIGN KEY (`item_sub_category_master_id`) REFERENCES `item_sub_category_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_code_master`
--

/*!40000 ALTER TABLE `item_code_master` DISABLE KEYS */;
LOCK TABLES `item_code_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `item_code_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`item_company_master`
--

DROP TABLE IF EXISTS `watererp`.`item_company_master`;
CREATE TABLE  `watererp`.`item_company_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL,
  `last_modified_date` timestamp NULL,
  `company_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_company_master`
--

/*!40000 ALTER TABLE `item_company_master` DISABLE KEYS */;
LOCK TABLES `item_company_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `item_company_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`item_details`
--

DROP TABLE IF EXISTS `watererp`.`item_details`;
CREATE TABLE  `watererp`.`item_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `item_description` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `item_quantity` int(11) DEFAULT NULL,
  `unit_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_details`
--

/*!40000 ALTER TABLE `item_details` DISABLE KEYS */;
LOCK TABLES `item_details` WRITE;
INSERT INTO `watererp`.`item_details` VALUES  (1,NULL,'Pipe','1m','1',1,10),
 (2,NULL,'Pipe 2.5','2.5m','2',1,20),
 (3,NULL,'Threading tape',NULL,'10',1,15),
 (4,NULL,'G. S. Pipe',NULL,'3',1,25);
UNLOCK TABLES;
/*!40000 ALTER TABLE `item_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`item_required`
--

DROP TABLE IF EXISTS `watererp`.`item_required`;
CREATE TABLE  `watererp`.`item_required` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `rate_per_shs` decimal(10,2) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `material_master_id` bigint(20) DEFAULT NULL,
  `application_txn_id` bigint(20) DEFAULT NULL,
  `feasibility_study_id` bigint(20) DEFAULT NULL,
  `proceedings_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_itemrequired_materialmaster_id` (`material_master_id`),
  KEY `fk_itemrequired_applicationtxn_id` (`application_txn_id`),
  KEY `fk_itemrequired_feasibilitystudy_id` (`feasibility_study_id`),
  KEY `fk_itemrequired_proceedings_id` (`proceedings_id`),
  CONSTRAINT `fk_itemrequired_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_itemrequired_feasibilitystudy_id` FOREIGN KEY (`feasibility_study_id`) REFERENCES `feasibility_study` (`id`),
  CONSTRAINT `fk_itemrequired_materialmaster_id` FOREIGN KEY (`material_master_id`) REFERENCES `material_master` (`id`),
  CONSTRAINT `fk_itemrequired_proceedings_id` FOREIGN KEY (`proceedings_id`) REFERENCES `proceedings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_required`
--

/*!40000 ALTER TABLE `item_required` DISABLE KEYS */;
LOCK TABLES `item_required` WRITE;
INSERT INTO `watererp`.`item_required` VALUES  (1,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,11),
 (2,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,11);
UNLOCK TABLES;
/*!40000 ALTER TABLE `item_required` ENABLE KEYS */;


--
-- Definition of table `watererp`.`item_sub_category_master`
--

DROP TABLE IF EXISTS `watererp`.`item_sub_category_master`;
CREATE TABLE  `watererp`.`item_sub_category_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_sub_category_code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL,
  `last_modified_date` timestamp NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_code` varchar(255) DEFAULT NULL,
  `item_category_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_itemsubcategorymaster_itemcategorymaster_id` (`item_category_master_id`),
  CONSTRAINT `fk_itemsubcategorymaster_itemcategorymaster_id` FOREIGN KEY (`item_category_master_id`) REFERENCES `item_category_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_sub_category_master`
--

/*!40000 ALTER TABLE `item_sub_category_master` DISABLE KEYS */;
LOCK TABLES `item_sub_category_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `item_sub_category_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`item_sub_code_master`
--

DROP TABLE IF EXISTS `watererp`.`item_sub_code_master`;
CREATE TABLE  `watererp`.`item_sub_code_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code_id` bigint(20) DEFAULT NULL,
  `item_sub_code` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL,
  `last_modified_date` timestamp NULL,
  `item_ccode_id` bigint(20) DEFAULT NULL,
  `item_category_id` bigint(20) NOT NULL,
  `item_sub_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_sub_code_master`
--

/*!40000 ALTER TABLE `item_sub_code_master` DISABLE KEYS */;
LOCK TABLES `item_sub_code_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `item_sub_code_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`jhi_authority`
--

DROP TABLE IF EXISTS `watererp`.`jhi_authority`;
CREATE TABLE  `watererp`.`jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_authority`
--

/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
LOCK TABLES `jhi_authority` WRITE;
INSERT INTO `watererp`.`jhi_authority` VALUES  ('ROLE_ADMIN'),
 ('ROLE_CUSTOMER'),
 ('ROLE_EMPLOYEE'),
 ('ROLE_USER');
UNLOCK TABLES;
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;


--
-- Definition of table `watererp`.`jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `watererp`.`jhi_persistent_audit_event`;
CREATE TABLE  `watererp`.`jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(255) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_persistent_audit_event`
--

/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
LOCK TABLES `jhi_persistent_audit_event` WRITE;
INSERT INTO `watererp`.`jhi_persistent_audit_event` VALUES  (1,'admin','2016-02-24 18:38:04','AUTHENTICATION_SUCCESS'),
 (2,'admin','2016-02-26 10:35:34','AUTHENTICATION_SUCCESS'),
 (3,'admin','2016-02-29 12:03:56','AUTHENTICATION_SUCCESS'),
 (4,'admin','2016-03-01 10:34:54','AUTHENTICATION_SUCCESS'),
 (5,'admin','2016-03-01 10:43:37','AUTHENTICATION_SUCCESS'),
 (6,'admin','2016-03-02 09:41:51','AUTHENTICATION_SUCCESS'),
 (7,'admin','2016-03-02 11:59:59','AUTHENTICATION_SUCCESS'),
 (8,'admin','2016-03-03 09:53:21','AUTHENTICATION_SUCCESS'),
 (9,'sf0220','2016-03-03 14:25:28','AUTHENTICATION_FAILURE'),
 (10,'admin','2016-03-03 14:25:34','AUTHENTICATION_SUCCESS'),
 (11,'admin','2016-03-03 18:00:35','AUTHENTICATION_SUCCESS'),
 (12,'admin','2016-03-04 10:23:26','AUTHENTICATION_SUCCESS'),
 (13,'admin','2016-03-04 12:02:35','AUTHENTICATION_SUCCESS'),
 (14,'user','2016-03-04 18:27:50','AUTHENTICATION_SUCCESS'),
 (15,'admin','2016-03-07 09:41:18','AUTHENTICATION_SUCCESS'),
 (16,'admin','2016-03-07 09:48:13','AUTHENTICATION_SUCCESS'),
 (17,'customer','2016-03-07 09:51:56','AUTHENTICATION_SUCCESS'),
 (18,'admin','2016-03-07 09:52:22','AUTHENTICATION_SUCCESS'),
 (19,'admin','2016-03-07 13:21:35','AUTHENTICATION_SUCCESS'),
 (20,'customer','2016-03-07 14:58:29','AUTHENTICATION_SUCCESS'),
 (21,'admin','2016-03-07 15:00:55','AUTHENTICATION_SUCCESS'),
 (22,'customer','2016-03-07 15:01:30','AUTHENTICATION_SUCCESS'),
 (23,'admin','2016-03-07 15:06:12','AUTHENTICATION_SUCCESS'),
 (24,'customer','2016-03-07 15:06:39','AUTHENTICATION_SUCCESS'),
 (25,'admin','2016-03-07 15:08:57','AUTHENTICATION_SUCCESS'),
 (26,'admin','2016-03-07 15:20:03','AUTHENTICATION_SUCCESS'),
 (27,'admin','2016-03-07 15:24:23','AUTHENTICATION_FAILURE'),
 (28,'admin','2016-03-07 15:24:29','AUTHENTICATION_FAILURE'),
 (29,'admin','2016-03-07 15:24:56','AUTHENTICATION_SUCCESS'),
 (30,'customer','2016-03-07 15:33:02','AUTHENTICATION_SUCCESS'),
 (31,'admin','2016-03-07 16:08:33','AUTHENTICATION_SUCCESS'),
 (32,'customer','2016-03-07 16:41:12','AUTHENTICATION_SUCCESS'),
 (33,'customer','2016-03-07 16:48:23','AUTHENTICATION_SUCCESS'),
 (34,'admin','2016-03-07 16:48:42','AUTHENTICATION_SUCCESS'),
 (35,'admin','2016-03-07 17:12:43','AUTHENTICATION_SUCCESS'),
 (36,'admin','2016-03-08 09:47:44','AUTHENTICATION_SUCCESS'),
 (37,'admin','2016-03-08 09:59:25','AUTHENTICATION_SUCCESS'),
 (38,'customer','2016-03-08 11:07:03','AUTHENTICATION_SUCCESS'),
 (39,'admin','2016-03-08 11:08:45','AUTHENTICATION_SUCCESS'),
 (40,'admin','2016-03-08 14:30:25','AUTHENTICATION_SUCCESS'),
 (41,'admin','2016-03-08 17:47:58','AUTHENTICATION_SUCCESS'),
 (42,'customer','2016-03-08 17:48:10','AUTHENTICATION_SUCCESS'),
 (43,'admin','2016-03-08 17:48:48','AUTHENTICATION_SUCCESS'),
 (44,'admin','2016-03-09 09:59:57','AUTHENTICATION_SUCCESS'),
 (45,'admin','2016-03-09 10:00:32','AUTHENTICATION_SUCCESS'),
 (46,'admin','2016-03-09 10:05:34','AUTHENTICATION_SUCCESS'),
 (47,'customer','2016-03-09 10:06:01','AUTHENTICATION_SUCCESS'),
 (48,'admin','2016-03-09 10:06:53','AUTHENTICATION_SUCCESS'),
 (49,'customer','2016-03-09 10:07:14','AUTHENTICATION_SUCCESS'),
 (50,'admin','2016-03-09 10:11:42','AUTHENTICATION_SUCCESS'),
 (51,'customer','2016-03-09 13:08:54','AUTHENTICATION_SUCCESS'),
 (52,'admin','2016-03-09 13:09:19','AUTHENTICATION_SUCCESS'),
 (53,'customer','2016-03-09 14:08:02','AUTHENTICATION_SUCCESS'),
 (54,'admin','2016-03-09 14:46:38','AUTHENTICATION_SUCCESS'),
 (55,'admin','2016-03-09 15:07:35','AUTHENTICATION_SUCCESS'),
 (56,'customer','2016-03-09 15:39:11','AUTHENTICATION_SUCCESS'),
 (57,'admin','2016-03-09 15:40:32','AUTHENTICATION_SUCCESS'),
 (58,'customer','2016-03-09 18:53:35','AUTHENTICATION_SUCCESS'),
 (59,'user','2016-03-09 18:53:56','AUTHENTICATION_SUCCESS'),
 (60,'admin','2016-03-09 18:54:42','AUTHENTICATION_SUCCESS'),
 (61,'admin','2016-03-10 10:03:13','AUTHENTICATION_SUCCESS'),
 (62,'admin','2016-03-10 10:22:36','AUTHENTICATION_SUCCESS'),
 (63,'admin','2016-03-10 10:33:05','AUTHENTICATION_SUCCESS'),
 (64,'customer','2016-03-10 10:33:58','AUTHENTICATION_SUCCESS'),
 (65,'admin','2016-03-10 10:36:26','AUTHENTICATION_SUCCESS'),
 (66,'customer','2016-03-10 10:36:41','AUTHENTICATION_SUCCESS'),
 (67,'admin','2016-03-10 10:47:17','AUTHENTICATION_SUCCESS'),
 (68,'admin','2016-03-10 10:48:02','AUTHENTICATION_SUCCESS'),
 (69,'admin','2016-03-10 11:13:18','AUTHENTICATION_SUCCESS'),
 (70,'customer','2016-03-10 11:13:31','AUTHENTICATION_SUCCESS'),
 (71,'admin','2016-03-10 11:16:55','AUTHENTICATION_SUCCESS'),
 (72,'admin','2016-03-10 11:17:02','AUTHENTICATION_SUCCESS'),
 (73,'customer','2016-03-10 11:30:43','AUTHENTICATION_SUCCESS'),
 (74,'admin','2016-03-10 11:30:56','AUTHENTICATION_SUCCESS'),
 (75,'admin','2016-03-10 12:43:31','AUTHENTICATION_SUCCESS'),
 (76,'customer','2016-03-10 14:28:13','AUTHENTICATION_SUCCESS'),
 (77,'user','2016-03-10 14:29:52','AUTHENTICATION_SUCCESS'),
 (78,'admin','2016-03-10 14:42:31','AUTHENTICATION_SUCCESS'),
 (79,'admin','2016-03-10 15:18:32','AUTHENTICATION_SUCCESS'),
 (80,'customer','2016-03-10 15:18:53','AUTHENTICATION_SUCCESS'),
 (81,'admin','2016-03-10 15:53:24','AUTHENTICATION_SUCCESS'),
 (82,'customer','2016-03-10 17:21:19','AUTHENTICATION_SUCCESS'),
 (83,'admin','2016-03-11 10:14:38','AUTHENTICATION_SUCCESS'),
 (84,'admin','2016-03-11 17:16:38','AUTHENTICATION_SUCCESS'),
 (85,'admin','2016-03-14 09:53:55','AUTHENTICATION_SUCCESS'),
 (86,'admin','2016-03-14 10:03:43','AUTHENTICATION_SUCCESS'),
 (87,'admin','2016-03-14 10:09:25','AUTHENTICATION_SUCCESS'),
 (88,'admin','2016-03-15 10:28:56','AUTHENTICATION_SUCCESS'),
 (89,'admin','2016-03-16 09:58:42','AUTHENTICATION_SUCCESS'),
 (90,'admin','2016-03-16 13:56:44','AUTHENTICATION_SUCCESS'),
 (91,'admin','2016-03-16 13:57:10','AUTHENTICATION_SUCCESS'),
 (92,'admin','2016-03-16 15:36:44','AUTHENTICATION_SUCCESS'),
 (93,'admin','2016-03-17 10:05:50','AUTHENTICATION_SUCCESS'),
 (94,'admin','2016-03-17 12:32:18','AUTHENTICATION_SUCCESS'),
 (95,'admin','2016-03-17 12:58:43','AUTHENTICATION_SUCCESS'),
 (96,'admin','2016-03-18 10:14:10','AUTHENTICATION_SUCCESS'),
 (97,'admin','2016-03-18 10:28:22','AUTHENTICATION_SUCCESS'),
 (98,'admin','2016-03-18 11:13:39','AUTHENTICATION_SUCCESS'),
 (99,'admin','2016-03-18 11:14:08','AUTHENTICATION_SUCCESS'),
 (100,'admin','2016-03-18 12:36:54','AUTHENTICATION_SUCCESS'),
 (101,'admin','2016-03-18 12:53:42','AUTHENTICATION_SUCCESS'),
 (102,'sf0006','2016-03-18 12:55:06','AUTHENTICATION_SUCCESS'),
 (103,'admin','2016-03-18 12:55:51','AUTHENTICATION_SUCCESS'),
 (104,'admin','2016-03-21 09:54:47','AUTHENTICATION_SUCCESS'),
 (105,'admin','2016-03-21 11:23:39','AUTHENTICATION_SUCCESS'),
 (106,'admin','2016-03-21 12:15:54','AUTHENTICATION_SUCCESS'),
 (107,'admin','2016-03-21 12:16:06','AUTHENTICATION_SUCCESS'),
 (108,'admin','2016-03-21 13:16:46','AUTHENTICATION_SUCCESS'),
 (109,'admin','2016-03-21 14:42:34','AUTHENTICATION_SUCCESS'),
 (110,'customer','2016-03-21 14:42:55','AUTHENTICATION_SUCCESS'),
 (111,'admin','2016-03-21 16:20:14','AUTHENTICATION_SUCCESS'),
 (112,'customer','2016-03-21 16:21:14','AUTHENTICATION_SUCCESS'),
 (113,'admin','2016-03-21 16:48:01','AUTHENTICATION_SUCCESS'),
 (114,'customer','2016-03-21 17:12:21','AUTHENTICATION_SUCCESS'),
 (115,'customer','2016-03-21 17:31:11','AUTHENTICATION_SUCCESS'),
 (116,'sf0015','2016-03-21 18:44:17','AUTHENTICATION_SUCCESS'),
 (117,'admin','2016-03-21 18:45:09','AUTHENTICATION_SUCCESS'),
 (118,'sf0015','2016-03-21 18:50:12','AUTHENTICATION_SUCCESS'),
 (119,'sf0015','2016-03-22 09:46:11','AUTHENTICATION_SUCCESS'),
 (120,'admin','2016-03-22 09:49:49','AUTHENTICATION_SUCCESS'),
 (121,'sf0015','2016-03-22 09:59:10','AUTHENTICATION_SUCCESS'),
 (122,'sf0015','2016-03-22 11:56:14','AUTHENTICATION_SUCCESS'),
 (123,'sf0024','2016-03-22 12:38:58','AUTHENTICATION_SUCCESS'),
 (124,'customer','2016-03-22 13:06:10','AUTHENTICATION_SUCCESS'),
 (125,'sf0015','2016-03-22 13:10:05','AUTHENTICATION_SUCCESS'),
 (126,'sf0029','2016-03-22 13:20:44','AUTHENTICATION_SUCCESS'),
 (127,'admin','2016-03-22 14:20:10','AUTHENTICATION_SUCCESS'),
 (128,'sf0020','2016-03-22 14:22:49','AUTHENTICATION_SUCCESS'),
 (129,'sf0015','2016-03-22 14:38:47','AUTHENTICATION_SUCCESS'),
 (130,'sf0027','2016-03-22 14:46:46','AUTHENTICATION_SUCCESS'),
 (131,'sf0014','2016-03-22 14:48:51','AUTHENTICATION_SUCCESS'),
 (132,'admin','2016-03-22 15:04:49','AUTHENTICATION_SUCCESS'),
 (133,'admin','2016-03-22 15:42:51','AUTHENTICATION_SUCCESS'),
 (134,'admin','2016-03-22 15:44:53','AUTHENTICATION_SUCCESS'),
 (135,'customer','2016-03-22 16:36:01','AUTHENTICATION_SUCCESS'),
 (136,'admin','2016-03-22 16:38:24','AUTHENTICATION_SUCCESS'),
 (137,'customer','2016-03-22 16:40:04','AUTHENTICATION_SUCCESS'),
 (138,'sf0015','2016-03-22 17:46:55','AUTHENTICATION_SUCCESS'),
 (139,'admin','2016-03-23 09:59:46','AUTHENTICATION_SUCCESS'),
 (140,'admin','2016-03-23 10:29:24','AUTHENTICATION_SUCCESS'),
 (141,'admin','2016-03-23 10:38:47','AUTHENTICATION_SUCCESS'),
 (142,'sf0015','2016-03-23 13:17:51','AUTHENTICATION_SUCCESS'),
 (143,'customer','2016-03-23 14:41:07','AUTHENTICATION_SUCCESS'),
 (144,'sf0015','2016-03-23 15:03:56','AUTHENTICATION_SUCCESS'),
 (145,'sf0029','2016-03-23 15:36:37','AUTHENTICATION_SUCCESS'),
 (146,'sf0020','2016-03-23 16:09:50','AUTHENTICATION_SUCCESS'),
 (147,'sf0015','2016-03-23 16:12:29','AUTHENTICATION_SUCCESS'),
 (148,'sf0027','2016-03-23 16:14:32','AUTHENTICATION_SUCCESS'),
 (149,'sf0014','2016-03-23 16:17:53','AUTHENTICATION_SUCCESS'),
 (150,'sf0015','2016-03-23 16:24:16','AUTHENTICATION_SUCCESS'),
 (151,'customer','2016-03-23 16:29:55','AUTHENTICATION_SUCCESS'),
 (152,'sf0015','2016-03-23 16:33:48','AUTHENTICATION_SUCCESS'),
 (153,'customer','2016-03-23 17:26:04','AUTHENTICATION_SUCCESS'),
 (154,'sf0015','2016-03-23 17:27:00','AUTHENTICATION_SUCCESS'),
 (155,'sf0029','2016-03-23 17:32:45','AUTHENTICATION_SUCCESS'),
 (156,'admin','2016-03-23 17:44:12','AUTHENTICATION_SUCCESS'),
 (157,'sf0029','2016-03-23 18:19:54','AUTHENTICATION_SUCCESS'),
 (158,'sf0020','2016-03-24 09:46:53','AUTHENTICATION_SUCCESS'),
 (159,'customer','2016-03-24 11:26:49','AUTHENTICATION_SUCCESS'),
 (160,'admin','2016-03-24 11:35:15','AUTHENTICATION_SUCCESS'),
 (161,'sf0015','2016-03-24 12:15:10','AUTHENTICATION_SUCCESS'),
 (162,'customer','2016-03-24 12:24:03','AUTHENTICATION_SUCCESS'),
 (163,'sf0015','2016-03-24 12:27:24','AUTHENTICATION_SUCCESS'),
 (164,'customer','2016-03-24 14:21:54','AUTHENTICATION_SUCCESS'),
 (165,'customer','2016-03-24 14:34:11','AUTHENTICATION_SUCCESS'),
 (166,'ssf0015','2016-03-24 14:35:37','AUTHENTICATION_FAILURE'),
 (167,'sf0015','2016-03-24 14:35:47','AUTHENTICATION_SUCCESS'),
 (168,'sf0029','2016-03-24 14:39:43','AUTHENTICATION_FAILURE'),
 (169,'sf0029','2016-03-24 14:39:51','AUTHENTICATION_SUCCESS'),
 (170,'admin','2016-03-24 15:33:32','AUTHENTICATION_SUCCESS'),
 (171,'admin','2016-03-24 16:29:29','AUTHENTICATION_SUCCESS'),
 (172,'admin','2016-03-24 16:50:38','AUTHENTICATION_SUCCESS'),
 (173,'admin','2016-03-24 16:53:51','AUTHENTICATION_SUCCESS'),
 (174,'admin','2016-03-24 17:03:17','AUTHENTICATION_SUCCESS'),
 (175,'admin','2016-03-25 09:52:32','AUTHENTICATION_SUCCESS'),
 (176,'sf0015','2016-03-25 10:27:55','AUTHENTICATION_SUCCESS'),
 (177,'customer','2016-03-25 15:36:43','AUTHENTICATION_SUCCESS'),
 (178,'sf0015','2016-03-25 15:37:43','AUTHENTICATION_SUCCESS'),
 (179,'customer','2016-03-25 17:16:12','AUTHENTICATION_SUCCESS'),
 (180,'sf0015','2016-03-25 17:16:47','AUTHENTICATION_SUCCESS'),
 (181,'sf0015','2016-03-25 17:49:09','AUTHENTICATION_FAILURE'),
 (182,'sf0015','2016-03-25 17:49:20','AUTHENTICATION_SUCCESS'),
 (183,'admin','2016-03-26 10:07:09','AUTHENTICATION_SUCCESS'),
 (184,'sf0015','2016-03-26 10:08:12','AUTHENTICATION_SUCCESS'),
 (185,'admin','2016-03-26 12:13:00','AUTHENTICATION_SUCCESS'),
 (186,'admin','2016-03-26 12:16:04','AUTHENTICATION_SUCCESS'),
 (187,'sf0015','2016-03-26 12:38:24','AUTHENTICATION_SUCCESS'),
 (188,'admin','2016-03-26 12:43:18','AUTHENTICATION_SUCCESS'),
 (189,'customer','2016-03-26 13:04:18','AUTHENTICATION_SUCCESS'),
 (190,'admin','2016-03-26 13:15:53','AUTHENTICATION_SUCCESS'),
 (191,'customer','2016-03-26 14:26:20','AUTHENTICATION_SUCCESS'),
 (192,'admin','2016-03-26 15:04:07','AUTHENTICATION_SUCCESS'),
 (193,'customer','2016-03-26 15:06:03','AUTHENTICATION_SUCCESS'),
 (194,'sf0015','2016-03-26 15:13:19','AUTHENTICATION_SUCCESS'),
 (195,'customer','2016-03-26 15:54:24','AUTHENTICATION_FAILURE'),
 (196,'customer','2016-03-26 15:54:31','AUTHENTICATION_SUCCESS'),
 (197,'sf0015','2016-03-26 15:55:50','AUTHENTICATION_SUCCESS'),
 (198,'sf0015','2016-03-28 09:57:59','AUTHENTICATION_SUCCESS'),
 (199,'sf0015','2016-03-28 10:01:48','AUTHENTICATION_SUCCESS'),
 (200,'admin','2016-03-28 12:43:06','AUTHENTICATION_SUCCESS'),
 (201,'sf0006','2016-03-28 12:44:05','AUTHENTICATION_FAILURE'),
 (202,'sf0006','2016-03-28 12:44:20','AUTHENTICATION_SUCCESS'),
 (203,'sf0009','2016-03-28 12:44:48','AUTHENTICATION_SUCCESS'),
 (204,'sf0010','2016-03-28 12:45:25','AUTHENTICATION_SUCCESS'),
 (205,'sf0011','2016-03-28 12:46:19','AUTHENTICATION_SUCCESS'),
 (206,'sf0013','2016-03-28 12:46:59','AUTHENTICATION_SUCCESS'),
 (207,'sf0014','2016-03-28 12:47:44','AUTHENTICATION_SUCCESS'),
 (208,'sf0015','2016-03-28 12:48:18','AUTHENTICATION_SUCCESS'),
 (209,'sf0016','2016-03-28 12:48:43','AUTHENTICATION_SUCCESS'),
 (210,'sf0017','2016-03-28 12:49:28','AUTHENTICATION_SUCCESS'),
 (211,'sf0018','2016-03-28 12:50:15','AUTHENTICATION_SUCCESS'),
 (212,'sf0019','2016-03-28 12:50:59','AUTHENTICATION_SUCCESS'),
 (213,'sf0021','2016-03-28 12:51:34','AUTHENTICATION_SUCCESS'),
 (214,'sf0022','2016-03-28 12:52:15','AUTHENTICATION_SUCCESS'),
 (215,'sf0023','2016-03-28 12:53:30','AUTHENTICATION_SUCCESS'),
 (216,'sf0027','2016-03-28 12:57:19','AUTHENTICATION_SUCCESS'),
 (217,'sf0028','2016-03-28 12:57:55','AUTHENTICATION_SUCCESS'),
 (218,'sf0015','2016-03-28 12:58:26','AUTHENTICATION_SUCCESS'),
 (219,'sf0015','2016-03-28 14:05:33','AUTHENTICATION_SUCCESS'),
 (220,'sf0029','2016-03-28 14:19:02','AUTHENTICATION_SUCCESS'),
 (221,'customer','2016-03-28 16:44:03','AUTHENTICATION_SUCCESS'),
 (222,'sf0006','2016-03-28 16:53:02','AUTHENTICATION_SUCCESS'),
 (223,'sf0029','2016-03-28 16:53:29','AUTHENTICATION_SUCCESS'),
 (224,'sf0015','2016-03-28 16:53:45','AUTHENTICATION_SUCCESS'),
 (225,'customer','2016-03-28 17:11:07','AUTHENTICATION_SUCCESS'),
 (226,'sf0015','2016-03-28 17:19:20','AUTHENTICATION_SUCCESS'),
 (227,'sf0015','2016-03-28 17:34:50','AUTHENTICATION_SUCCESS'),
 (228,'customer','2016-03-28 17:39:35','AUTHENTICATION_SUCCESS'),
 (229,'sf0015','2016-03-28 17:41:04','AUTHENTICATION_SUCCESS'),
 (230,'sf0015','2016-03-28 17:49:39','AUTHENTICATION_SUCCESS'),
 (231,'customer','2016-03-28 17:50:54','AUTHENTICATION_SUCCESS'),
 (232,'sf0015','2016-03-28 17:51:50','AUTHENTICATION_SUCCESS'),
 (233,'sf0029','2016-03-28 18:15:50','AUTHENTICATION_SUCCESS'),
 (234,'customer','2016-03-28 18:25:52','AUTHENTICATION_SUCCESS'),
 (235,'sf0015','2016-03-28 18:26:37','AUTHENTICATION_SUCCESS'),
 (236,'admin','2016-03-28 18:39:20','AUTHENTICATION_SUCCESS'),
 (237,'sf0029','2016-03-28 18:40:58','AUTHENTICATION_SUCCESS'),
 (238,'admin','2016-03-28 18:42:41','AUTHENTICATION_SUCCESS'),
 (239,'customer','2016-03-29 10:30:34','AUTHENTICATION_SUCCESS'),
 (240,'sf0015','2016-03-29 11:47:54','AUTHENTICATION_SUCCESS'),
 (241,'customer','2016-03-29 12:23:39','AUTHENTICATION_SUCCESS'),
 (242,'customer','2016-03-29 12:36:12','AUTHENTICATION_SUCCESS'),
 (243,'sf0015','2016-03-29 12:49:09','AUTHENTICATION_SUCCESS'),
 (244,'customer','2016-03-29 14:48:31','AUTHENTICATION_SUCCESS'),
 (245,'sf0015','2016-03-29 14:56:45','AUTHENTICATION_SUCCESS'),
 (246,'sf0029','2016-03-29 19:06:19','AUTHENTICATION_SUCCESS'),
 (247,'admin','2016-03-30 11:30:02','AUTHENTICATION_SUCCESS'),
 (248,'admin','2016-03-30 11:48:07','AUTHENTICATION_SUCCESS'),
 (249,'admin','2016-03-30 14:00:46','AUTHENTICATION_SUCCESS'),
 (250,'admin','2016-03-30 19:04:51','AUTHENTICATION_SUCCESS');
UNLOCK TABLES;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;


--
-- Definition of table `watererp`.`jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `watererp`.`jhi_persistent_audit_evt_data`;
CREATE TABLE  `watererp`.`jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_persistent_audit_evt_data`
--

/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
INSERT INTO `watererp`.`jhi_persistent_audit_evt_data` VALUES  (1,'remoteAddress','0:0:0:0:0:0:0:1'),
 (1,'sessionId','6F890CBF95F5F123CC8DF5E9B004A5F1'),
 (2,'remoteAddress','0:0:0:0:0:0:0:1'),
 (2,'sessionId','B068299E1CB4C17A606253222924B3AC'),
 (3,'remoteAddress','0:0:0:0:0:0:0:1'),
 (3,'sessionId','03A2AFAE6E95260A6AE3F989DCC08B23'),
 (4,'remoteAddress','0:0:0:0:0:0:0:1'),
 (4,'sessionId','76042EEF04F4A767371EF1E1BD5EEAD8'),
 (5,'remoteAddress','0:0:0:0:0:0:0:1'),
 (5,'sessionId','1BB83CECB0C3B4BA516B426670EC0D20'),
 (6,'remoteAddress','127.0.0.1'),
 (6,'sessionId','1BB6A32CAFCFD13A61B85007BD141463'),
 (7,'remoteAddress','127.0.0.1'),
 (7,'sessionId','BEBC97D19669B0D9ECF918B5754C3C5A'),
 (8,'remoteAddress','0:0:0:0:0:0:0:1'),
 (8,'sessionId','C23BDF1F41B371C49DDA9F0F0E7A7FFA'),
 (9,'message','Bad credentials'),
 (9,'type','org.springframework.security.authentication.BadCredentialsException'),
 (10,'remoteAddress','0:0:0:0:0:0:0:1'),
 (10,'sessionId','491BE886F6E34F243C186FE6D2630E78'),
 (11,'remoteAddress','0:0:0:0:0:0:0:1'),
 (11,'sessionId','250BADCE291D76A685E33179265E360D'),
 (12,'remoteAddress','0:0:0:0:0:0:0:1'),
 (12,'sessionId','CA8D96614251DBE00E51E6DD56ECE9AF'),
 (13,'remoteAddress','0:0:0:0:0:0:0:1'),
 (13,'sessionId','EBD106262524565745CB7146D2B745EB'),
 (14,'remoteAddress','0:0:0:0:0:0:0:1'),
 (14,'sessionId','809E3416E65FB7C11CAA7C061955966F'),
 (15,'remoteAddress','0:0:0:0:0:0:0:1'),
 (15,'sessionId','9C03B4C35266BDCECF02B778B0FC4F72'),
 (16,'remoteAddress','0:0:0:0:0:0:0:1'),
 (16,'sessionId','8CEF419E1B3858FA63F74E9482170AF0'),
 (17,'remoteAddress','0:0:0:0:0:0:0:1'),
 (17,'sessionId','A467E1AE3FD34D5859D01AFAA2534F9B'),
 (18,'remoteAddress','0:0:0:0:0:0:0:1'),
 (18,'sessionId','EC126A4853CECD862267DBE3B5FCAC9E'),
 (19,'remoteAddress','0:0:0:0:0:0:0:1'),
 (19,'sessionId','79340425106DB7F0BE1C6E4A90EEDBCB'),
 (20,'remoteAddress','0:0:0:0:0:0:0:1'),
 (20,'sessionId','8C5668A864F4CBBDACEABFB00314019D'),
 (21,'remoteAddress','0:0:0:0:0:0:0:1'),
 (21,'sessionId','3920A42E28C08EB421079A0E7B38657E'),
 (22,'remoteAddress','0:0:0:0:0:0:0:1'),
 (22,'sessionId','A99946E833C49EA8965FE9F072C81323'),
 (23,'remoteAddress','0:0:0:0:0:0:0:1'),
 (23,'sessionId','B92450261AACE33F5657A01EB9C73053'),
 (24,'remoteAddress','0:0:0:0:0:0:0:1'),
 (24,'sessionId','458FAD783227981F1C3474484F682389'),
 (25,'remoteAddress','0:0:0:0:0:0:0:1'),
 (25,'sessionId','9A433ED0BF3A557882C495A11CB8CB33'),
 (26,'remoteAddress','0:0:0:0:0:0:0:1'),
 (26,'sessionId','6980EF8BB02C744B266B87CA1FE7AE58'),
 (27,'message','Bad credentials'),
 (27,'type','org.springframework.security.authentication.BadCredentialsException'),
 (28,'message','Bad credentials'),
 (28,'type','org.springframework.security.authentication.BadCredentialsException'),
 (29,'remoteAddress','0:0:0:0:0:0:0:1'),
 (29,'sessionId','F72209EF261A0E35214148D2BC522A74'),
 (30,'remoteAddress','0:0:0:0:0:0:0:1'),
 (30,'sessionId','545FE19B112EDDF4E0FA4734662BFD57'),
 (31,'remoteAddress','0:0:0:0:0:0:0:1'),
 (31,'sessionId','84BCBFA069FDF19CF9FEB6F7D2EC3B07'),
 (32,'remoteAddress','0:0:0:0:0:0:0:1'),
 (32,'sessionId','111404142BA7B3F3FBD6E3D27537294F'),
 (33,'remoteAddress','0:0:0:0:0:0:0:1'),
 (33,'sessionId','056D62A2D89AEBDC355C7D67CFCF34A7'),
 (34,'remoteAddress','0:0:0:0:0:0:0:1'),
 (34,'sessionId','D7A504C817EE1FD556C80623F273D443'),
 (35,'remoteAddress','0:0:0:0:0:0:0:1'),
 (35,'sessionId','267EABD2581C40A7672196F6AF3F8210'),
 (36,'remoteAddress','0:0:0:0:0:0:0:1'),
 (36,'sessionId','C60B514EE4073365654ADDB01B349AA1'),
 (37,'remoteAddress','0:0:0:0:0:0:0:1'),
 (37,'sessionId','66D5C6CBA82A02751C957E0024165FB4'),
 (38,'remoteAddress','0:0:0:0:0:0:0:1'),
 (38,'sessionId','F6A3E4F92C24533A5D6AE597B888D51F'),
 (39,'remoteAddress','0:0:0:0:0:0:0:1'),
 (39,'sessionId','7A24B123F746332765C31984385D71B2'),
 (40,'remoteAddress','0:0:0:0:0:0:0:1'),
 (40,'sessionId','41107A30F94BAC53D6C011D86DC15A9F'),
 (41,'remoteAddress','0:0:0:0:0:0:0:1'),
 (41,'sessionId','BEE96CF118E13CF4A8025BBB4741895F'),
 (42,'remoteAddress','0:0:0:0:0:0:0:1'),
 (42,'sessionId','D97DD800B1B36D3235E59AA4B32C0102'),
 (43,'remoteAddress','0:0:0:0:0:0:0:1'),
 (43,'sessionId','8BFF6D36267B81547951056C9252AB35'),
 (44,'remoteAddress','0:0:0:0:0:0:0:1'),
 (44,'sessionId','95B941658AD98AEEE6ACD8ABF6D11105'),
 (45,'remoteAddress','0:0:0:0:0:0:0:1'),
 (45,'sessionId','F6F74CD29B48CFFB36C30ADFFFA4F38A'),
 (46,'remoteAddress','0:0:0:0:0:0:0:1'),
 (46,'sessionId','86927B7E7C644C1E6C059620BCDBE742'),
 (47,'remoteAddress','0:0:0:0:0:0:0:1'),
 (47,'sessionId','B8F7DF684DB88CCDB0C463609BFE4787'),
 (48,'remoteAddress','0:0:0:0:0:0:0:1'),
 (48,'sessionId','6B4362B7376705B6D993E2AB1E19D73F'),
 (49,'remoteAddress','0:0:0:0:0:0:0:1'),
 (49,'sessionId','5229BF1D7A2CCED28CEB1C403A96D38C'),
 (50,'remoteAddress','0:0:0:0:0:0:0:1'),
 (50,'sessionId','0E821D21F320832DD509A134A13E7596'),
 (51,'remoteAddress','0:0:0:0:0:0:0:1'),
 (51,'sessionId','B2EB105033DC73EEC02422C5B5FEA8C2'),
 (52,'remoteAddress','0:0:0:0:0:0:0:1'),
 (52,'sessionId','2B98554668A1AEF6EFD7E36CCAB1FBC9'),
 (53,'remoteAddress','0:0:0:0:0:0:0:1'),
 (53,'sessionId','9C0E8AAC9EFA3798A8B7BC00FAC3BF5C'),
 (54,'remoteAddress','0:0:0:0:0:0:0:1'),
 (54,'sessionId','B25C8EE0BE50B511A5609B38030E0CBF'),
 (55,'remoteAddress','0:0:0:0:0:0:0:1'),
 (55,'sessionId','4BF3F6E2CD0E1369E6194DF9F306019A'),
 (56,'remoteAddress','0:0:0:0:0:0:0:1'),
 (56,'sessionId','02E1F8ABFFA2DD2D26BCB29009A332A5'),
 (57,'remoteAddress','0:0:0:0:0:0:0:1'),
 (57,'sessionId','F25A29A95A2BD4E469FE93B58EBB7561'),
 (58,'remoteAddress','0:0:0:0:0:0:0:1'),
 (58,'sessionId','1F5DDB26102B7F9AC4F0F85D9A340BAB'),
 (59,'remoteAddress','0:0:0:0:0:0:0:1'),
 (59,'sessionId','19FE66C7408768ED084765C2BAFB3702'),
 (60,'remoteAddress','0:0:0:0:0:0:0:1'),
 (60,'sessionId','D6B0A4BF1C1B85E2819719DEDCED7C04'),
 (61,'remoteAddress','0:0:0:0:0:0:0:1'),
 (61,'sessionId','582E96CAF16036CDD9EE254A24B386AA'),
 (62,'remoteAddress','0:0:0:0:0:0:0:1'),
 (62,'sessionId','AA364EEFA3DA0D11E131D11AF8DC2E51'),
 (63,'remoteAddress','0:0:0:0:0:0:0:1'),
 (63,'sessionId','D011E866AB5B63D7ECC9F5A5207D50E8'),
 (64,'remoteAddress','0:0:0:0:0:0:0:1'),
 (64,'sessionId','C42145F2026B716088BC5CD850D2F64D'),
 (65,'remoteAddress','0:0:0:0:0:0:0:1'),
 (65,'sessionId','34B08927179B069697E507D02F17ACA0'),
 (66,'remoteAddress','0:0:0:0:0:0:0:1'),
 (66,'sessionId','19C51896970E1AC23C64B447E968D045'),
 (67,'remoteAddress','0:0:0:0:0:0:0:1'),
 (67,'sessionId','793322D4D88D6C400B535FF37005A45A'),
 (68,'remoteAddress','0:0:0:0:0:0:0:1'),
 (68,'sessionId','83D19C5B183CA9F3162877241A4C3521'),
 (69,'remoteAddress','0:0:0:0:0:0:0:1'),
 (69,'sessionId','1C80D441C0DF54D9D5E55CA0AB18FD97'),
 (70,'remoteAddress','0:0:0:0:0:0:0:1'),
 (70,'sessionId','1BDF1493744552C20780B29DCD789B01'),
 (71,'remoteAddress','0:0:0:0:0:0:0:1'),
 (71,'sessionId','BA03C80DB8A446B297623CBAD45A867B'),
 (72,'remoteAddress','0:0:0:0:0:0:0:1'),
 (72,'sessionId','23700EC0B8913A95B9972823060F101C'),
 (73,'remoteAddress','0:0:0:0:0:0:0:1'),
 (73,'sessionId','95D6D9211BE063E997A41AED5A0916E8'),
 (74,'remoteAddress','0:0:0:0:0:0:0:1'),
 (74,'sessionId','F3F6D2B32E002A18D2C1221CC77811A0'),
 (75,'remoteAddress','0:0:0:0:0:0:0:1'),
 (75,'sessionId','DD3D6AA9F863C3ADFD38CDB34B8C6093'),
 (76,'remoteAddress','0:0:0:0:0:0:0:1'),
 (76,'sessionId','76D79E7F85861D8CFD7D282B2455C6F4'),
 (77,'remoteAddress','0:0:0:0:0:0:0:1'),
 (77,'sessionId','24ED36069A949503BB2564318959D83A'),
 (78,'remoteAddress','0:0:0:0:0:0:0:1'),
 (78,'sessionId','30EBE0AA431B1299F0F033244D5337B4'),
 (79,'remoteAddress','0:0:0:0:0:0:0:1'),
 (79,'sessionId','56137CAA7BD8695FF9FC96C132A12711'),
 (80,'remoteAddress','0:0:0:0:0:0:0:1'),
 (80,'sessionId','934A65CD40F50495EE7E0650CB56B078'),
 (81,'remoteAddress','0:0:0:0:0:0:0:1'),
 (81,'sessionId','BCED3D6A56F0E1B1A76C6C6FED089660'),
 (82,'remoteAddress','0:0:0:0:0:0:0:1'),
 (82,'sessionId','AE3CB5BA4F06E1DE4D928BA6D9D5F0A1'),
 (83,'remoteAddress','0:0:0:0:0:0:0:1'),
 (83,'sessionId','83E1E00936C3C20ECBB5F8AE48543108'),
 (84,'remoteAddress','0:0:0:0:0:0:0:1'),
 (84,'sessionId','7CECE0DB5AD1E96A7E4F60C9A0D686CF'),
 (85,'remoteAddress','0:0:0:0:0:0:0:1'),
 (85,'sessionId','CD2F4970E49B8F6C4451BDF6431B4145'),
 (86,'remoteAddress','0:0:0:0:0:0:0:1'),
 (86,'sessionId','9B16A22E5C0EDD4E1A7483946F39691F'),
 (87,'remoteAddress','0:0:0:0:0:0:0:1'),
 (87,'sessionId','0CE2E094F9B45795E8B04F95C006AEC8'),
 (88,'remoteAddress','0:0:0:0:0:0:0:1'),
 (88,'sessionId','61AA31C4C66F9DEBAFC44B0382C82597'),
 (89,'remoteAddress','127.0.0.1'),
 (89,'sessionId','3C242942DFF1338B56B9AB2D6FB43269'),
 (90,'remoteAddress','127.0.0.1'),
 (90,'sessionId','1008AEE2EB9BBADA670A4A101FD249AD'),
 (91,'remoteAddress','127.0.0.1'),
 (91,'sessionId','F71798009EEC8CB0F7B221B5D15408F7'),
 (92,'remoteAddress','0:0:0:0:0:0:0:1'),
 (92,'sessionId','8552A734A2992D59D32844D3065A6076'),
 (93,'remoteAddress','0:0:0:0:0:0:0:1'),
 (93,'sessionId','7616A1BDEDB326323AC86B94BADC7D2A'),
 (94,'remoteAddress','0:0:0:0:0:0:0:1'),
 (94,'sessionId','3BC6F911B4E0B4AEA0379F2E6FBD3E2B'),
 (95,'remoteAddress','127.0.0.1'),
 (95,'sessionId','A0373D62E98C8E60A9103470DCB635B5'),
 (96,'remoteAddress','0:0:0:0:0:0:0:1'),
 (96,'sessionId','CBF5C55C82D791F43DE1D387CFDE527E'),
 (97,'remoteAddress','0:0:0:0:0:0:0:1'),
 (97,'sessionId','5A1395E23DE9BF4ED724263C349AEF8F'),
 (98,'remoteAddress','0:0:0:0:0:0:0:1'),
 (98,'sessionId','581C09B9D3D91103D921C2801A9F136B'),
 (99,'remoteAddress','0:0:0:0:0:0:0:1'),
 (99,'sessionId','31E42D4D22C4D3CA51C461DA553B8403'),
 (100,'remoteAddress','0:0:0:0:0:0:0:1'),
 (100,'sessionId','C907C219FF7D15EC83DDBEA0432E2244'),
 (101,'remoteAddress','0:0:0:0:0:0:0:1'),
 (101,'sessionId','FD5FF7116242772CA11C6A8643EFA40E'),
 (102,'remoteAddress','0:0:0:0:0:0:0:1'),
 (102,'sessionId','0745897569F61B4610E2DE7654929527'),
 (103,'remoteAddress','0:0:0:0:0:0:0:1'),
 (103,'sessionId','8E1C9F6EFCDF37B92C4DB6E4C602E577'),
 (104,'remoteAddress','0:0:0:0:0:0:0:1'),
 (104,'sessionId','89DA41220D832DC9B7828B6E482EEBDD'),
 (105,'remoteAddress','0:0:0:0:0:0:0:1'),
 (105,'sessionId','22BE16F6FAD83591D83EDDE8744B0FF2'),
 (106,'remoteAddress','0:0:0:0:0:0:0:1'),
 (106,'sessionId','99AEAC3CF8F89EBC0FF2E65DCF8F7526'),
 (107,'remoteAddress','0:0:0:0:0:0:0:1'),
 (107,'sessionId','85F575990663218847E302B5375FBC45'),
 (108,'remoteAddress','0:0:0:0:0:0:0:1'),
 (108,'sessionId','D5FFB7EC82DAEAC95C6D1A08C40E8794'),
 (109,'remoteAddress','0:0:0:0:0:0:0:1'),
 (109,'sessionId','C1EE596A60A0717FF0D9CB700939522E'),
 (110,'remoteAddress','0:0:0:0:0:0:0:1'),
 (110,'sessionId','FE0AF23260B78DB714F72E94ED02D80B'),
 (111,'remoteAddress','0:0:0:0:0:0:0:1'),
 (111,'sessionId','7FB19CC55BCF7DA3849F51DCC486FB58'),
 (112,'remoteAddress','0:0:0:0:0:0:0:1'),
 (112,'sessionId','67B8CB93917320C9B05B85C4E15AB8C8'),
 (113,'remoteAddress','0:0:0:0:0:0:0:1'),
 (113,'sessionId','FD58C2D3B1EA51FF58DF3B72CD135333'),
 (114,'remoteAddress','0:0:0:0:0:0:0:1'),
 (114,'sessionId','FD3D6E03C2DCE05A467B10B80CF43941'),
 (115,'remoteAddress','0:0:0:0:0:0:0:1'),
 (115,'sessionId','D667FA7E6618EA8474BFCA1C1AA1D6E6'),
 (116,'remoteAddress','0:0:0:0:0:0:0:1'),
 (116,'sessionId','02636F23D3E302282775B7B23E19CAB7'),
 (117,'remoteAddress','0:0:0:0:0:0:0:1'),
 (117,'sessionId','8831BC08BC19A306DB87DA6CC727E00D'),
 (118,'remoteAddress','0:0:0:0:0:0:0:1'),
 (118,'sessionId','D83A16755768F711C8C87DAC2ACE30E2'),
 (119,'remoteAddress','0:0:0:0:0:0:0:1'),
 (119,'sessionId','43A954BA2A7B98834FB9FD96F608BC8A'),
 (120,'remoteAddress','0:0:0:0:0:0:0:1'),
 (120,'sessionId','D4445A948BEAE115F4294BE278AEC54A'),
 (121,'remoteAddress','0:0:0:0:0:0:0:1'),
 (121,'sessionId','8BAE1D6E04661F3C2C403472E2EA7D98'),
 (122,'remoteAddress','0:0:0:0:0:0:0:1'),
 (122,'sessionId','FC976096F4790A31D9B3DE575007EEB8'),
 (123,'remoteAddress','0:0:0:0:0:0:0:1'),
 (123,'sessionId','47A6668786DC311E15BAF3EF8E304E9E'),
 (124,'remoteAddress','0:0:0:0:0:0:0:1'),
 (124,'sessionId','66C95DC4AEA9C48BB59F0BF0789B0363'),
 (125,'remoteAddress','0:0:0:0:0:0:0:1'),
 (125,'sessionId','5F26804A553C3D416C902CA5C032A469'),
 (126,'remoteAddress','0:0:0:0:0:0:0:1'),
 (126,'sessionId','652B95FEFF966EC624410723695C7631'),
 (127,'remoteAddress','0:0:0:0:0:0:0:1'),
 (127,'sessionId','FF08610E6496FC0AA6042937C287B640'),
 (128,'remoteAddress','0:0:0:0:0:0:0:1'),
 (128,'sessionId','FB90E66390D29CE353B55D863FF7857F'),
 (129,'remoteAddress','0:0:0:0:0:0:0:1'),
 (129,'sessionId','DAF75DC4FC76DF0678DC8ADF39756687'),
 (130,'remoteAddress','0:0:0:0:0:0:0:1'),
 (130,'sessionId','73DE279E96FBDF414A498837E3ACAFE3'),
 (131,'remoteAddress','0:0:0:0:0:0:0:1'),
 (131,'sessionId','952D720EBCA836976B09470279DD82BE'),
 (132,'remoteAddress','0:0:0:0:0:0:0:1'),
 (132,'sessionId','1214FAF7BE1C60F4324DCEF002749D30'),
 (133,'remoteAddress','0:0:0:0:0:0:0:1'),
 (133,'sessionId','4F11439D128FDB9547989E09E73348AA'),
 (134,'remoteAddress','0:0:0:0:0:0:0:1'),
 (134,'sessionId','A11392ABBCB217BE77ABBCA2F37DE0BA'),
 (135,'remoteAddress','0:0:0:0:0:0:0:1'),
 (135,'sessionId','A947BA3A694816F8CFB21F44BDFDF4AA'),
 (136,'remoteAddress','0:0:0:0:0:0:0:1'),
 (136,'sessionId','0BD72C69B50CF2115A0A69D5809E7170'),
 (137,'remoteAddress','0:0:0:0:0:0:0:1'),
 (137,'sessionId','9132F59F1CFB22EB0345243FAEEFE892'),
 (138,'remoteAddress','0:0:0:0:0:0:0:1'),
 (138,'sessionId','30F9484417C790D66F16747CA40F5269'),
 (139,'remoteAddress','0:0:0:0:0:0:0:1'),
 (139,'sessionId','A20387E733470D855CE1078A6462EAFB'),
 (140,'remoteAddress','0:0:0:0:0:0:0:1'),
 (140,'sessionId','213943BFFA109E16F196DB2C44C49302'),
 (141,'remoteAddress','0:0:0:0:0:0:0:1'),
 (141,'sessionId','132577821F9F93E0DE5078E0B0AC59A8'),
 (142,'remoteAddress','0:0:0:0:0:0:0:1'),
 (142,'sessionId','4A0060A76FF738DE8F8DEC20F5CA6A31'),
 (143,'remoteAddress','0:0:0:0:0:0:0:1'),
 (143,'sessionId','FF8233928A7E17A2A28C0A6CFECA3606'),
 (144,'remoteAddress','0:0:0:0:0:0:0:1'),
 (144,'sessionId','59C7AFC80FA7EC018A7A07C04C9E4857'),
 (145,'remoteAddress','0:0:0:0:0:0:0:1'),
 (145,'sessionId','170204BF43F8E5AE826302859886A556'),
 (146,'remoteAddress','0:0:0:0:0:0:0:1'),
 (146,'sessionId','93ED8B1C1DF797F72D4ADD08402886A9'),
 (147,'remoteAddress','0:0:0:0:0:0:0:1'),
 (147,'sessionId','4CE79AA4F85BBD4B1F908FA1ECB3745B'),
 (148,'remoteAddress','0:0:0:0:0:0:0:1'),
 (148,'sessionId','C0EBB376727D607FB3AA7EB64E416226'),
 (149,'remoteAddress','0:0:0:0:0:0:0:1'),
 (149,'sessionId','3CCA57419CFECF7E4EA86B8E2F0C2387'),
 (150,'remoteAddress','0:0:0:0:0:0:0:1'),
 (150,'sessionId','C1CA7F4981859B6E9215A53F39E13CFB'),
 (151,'remoteAddress','0:0:0:0:0:0:0:1'),
 (151,'sessionId','50E60BFADB1C2CFC0F5CD0FDB45D553A'),
 (152,'remoteAddress','0:0:0:0:0:0:0:1'),
 (152,'sessionId','9CD81C7D9542490BF8E2C4854AE2502D'),
 (153,'remoteAddress','0:0:0:0:0:0:0:1'),
 (153,'sessionId','65E8FEDFFD43892E9FB80C0A51FB8E5A'),
 (154,'remoteAddress','0:0:0:0:0:0:0:1'),
 (154,'sessionId','DECEB535AF424A04F16D1C4216FF24EF'),
 (155,'remoteAddress','0:0:0:0:0:0:0:1'),
 (155,'sessionId','6655EFEC43F2FD481E1C227BD65D0840'),
 (156,'remoteAddress','0:0:0:0:0:0:0:1'),
 (156,'sessionId','E660110AB4E5226A928A3F85D6B19BEE'),
 (157,'remoteAddress','0:0:0:0:0:0:0:1'),
 (157,'sessionId','8D22C0FF7B6CAA22F55BDCA1429965B9'),
 (158,'remoteAddress','0:0:0:0:0:0:0:1'),
 (158,'sessionId','2D75917616D6F1A3D0D029210E993D17'),
 (159,'remoteAddress','0:0:0:0:0:0:0:1'),
 (159,'sessionId','79B88E476DF56F0F307CA5F0E89684D2'),
 (160,'remoteAddress','0:0:0:0:0:0:0:1'),
 (160,'sessionId','8DAEC7D30F4E393A27B4A4602C0959BD'),
 (161,'remoteAddress','0:0:0:0:0:0:0:1'),
 (161,'sessionId','F523851C13A9EAD66ED69314C5C1BABC'),
 (162,'remoteAddress','0:0:0:0:0:0:0:1'),
 (162,'sessionId','2A8CF113D8E49D8D62445C77019EC63D'),
 (163,'remoteAddress','0:0:0:0:0:0:0:1'),
 (163,'sessionId','9CF964F09711B90E13F6A3C9D3CA0FA1'),
 (164,'remoteAddress','0:0:0:0:0:0:0:1'),
 (164,'sessionId','99AD8593991A9DE7E9D53B41C15B9B74'),
 (165,'remoteAddress','0:0:0:0:0:0:0:1'),
 (165,'sessionId','0B655767F686FA7F9D58094E30755E59'),
 (166,'message','Bad credentials'),
 (166,'type','org.springframework.security.authentication.BadCredentialsException'),
 (167,'remoteAddress','0:0:0:0:0:0:0:1'),
 (167,'sessionId','9224431719279FCE8B1FA14AB19DC573'),
 (168,'message','Bad credentials'),
 (168,'type','org.springframework.security.authentication.BadCredentialsException'),
 (169,'remoteAddress','0:0:0:0:0:0:0:1'),
 (169,'sessionId','6EF549E06533D37E21F436941806C1B2'),
 (170,'remoteAddress','0:0:0:0:0:0:0:1'),
 (170,'sessionId','288A7E0C5117265B2C43147B50DDF747'),
 (171,'remoteAddress','0:0:0:0:0:0:0:1'),
 (171,'sessionId','39B2F824A3D69BD3EB7B389364D45681'),
 (172,'remoteAddress','0:0:0:0:0:0:0:1'),
 (172,'sessionId','D2FF69847B3020FC09D7583A4F6E6330'),
 (173,'remoteAddress','0:0:0:0:0:0:0:1'),
 (173,'sessionId','E4C3DD9CBFACF8CA03CB4E482C8F92EF'),
 (174,'remoteAddress','0:0:0:0:0:0:0:1'),
 (174,'sessionId','222A3CA3FEBC3CA25793D91C1701500B'),
 (175,'remoteAddress','0:0:0:0:0:0:0:1'),
 (175,'sessionId','1AC9817728FC95583EAA95E6964A90F9'),
 (176,'remoteAddress','0:0:0:0:0:0:0:1'),
 (176,'sessionId','4D566F68227DF0F204AB88133F0E2215'),
 (177,'remoteAddress','0:0:0:0:0:0:0:1'),
 (177,'sessionId','4021EE5BD2EECE22177CC84E2338C373'),
 (178,'remoteAddress','0:0:0:0:0:0:0:1'),
 (178,'sessionId','0F89D517BBCDA26A06A734A14B8F506D'),
 (179,'remoteAddress','0:0:0:0:0:0:0:1'),
 (179,'sessionId','D68750AE693575BF5621E75B5DDB6DE1'),
 (180,'remoteAddress','0:0:0:0:0:0:0:1'),
 (180,'sessionId','1930B0C5E1B71021A23A798D4F83CC94'),
 (181,'message','Bad credentials'),
 (181,'type','org.springframework.security.authentication.BadCredentialsException'),
 (182,'remoteAddress','0:0:0:0:0:0:0:1'),
 (182,'sessionId','48C9609450424BA8FB8866C73807DD2F'),
 (183,'remoteAddress','0:0:0:0:0:0:0:1'),
 (183,'sessionId','14334D43383BE6327705C352248F3F07'),
 (184,'remoteAddress','0:0:0:0:0:0:0:1'),
 (184,'sessionId','A905EF6678AAA896227B33FB222A37A5'),
 (185,'remoteAddress','0:0:0:0:0:0:0:1'),
 (185,'sessionId','EC7D50123A4269F28F295E886E5C2C24'),
 (186,'remoteAddress','0:0:0:0:0:0:0:1'),
 (186,'sessionId','81BDF653E455C78B5BCA72D18F009C6E'),
 (187,'remoteAddress','0:0:0:0:0:0:0:1'),
 (187,'sessionId','7AD32AD7CA7E245AFF347C0E9011AB4E'),
 (188,'remoteAddress','0:0:0:0:0:0:0:1'),
 (188,'sessionId','52401E1CFB382B23F3BB139F7486F635'),
 (189,'remoteAddress','0:0:0:0:0:0:0:1'),
 (189,'sessionId','B10B0E49EBB66CFA407692D0437A8F77'),
 (190,'remoteAddress','0:0:0:0:0:0:0:1'),
 (190,'sessionId','5F5C7DAAFFB13AD0D41A1780555C38F8'),
 (191,'remoteAddress','0:0:0:0:0:0:0:1'),
 (191,'sessionId','283960B81BF882A73C782299E7CC38D8'),
 (192,'remoteAddress','0:0:0:0:0:0:0:1'),
 (192,'sessionId','4D365EDB686669D46804EA2DBC6195CC'),
 (193,'remoteAddress','0:0:0:0:0:0:0:1'),
 (193,'sessionId','FA320D7E42FBAEE073DD7247F72FE5E8'),
 (194,'remoteAddress','0:0:0:0:0:0:0:1'),
 (194,'sessionId','869596EC0EAFA5AA4A17D45D00934103'),
 (195,'message','Bad credentials'),
 (195,'type','org.springframework.security.authentication.BadCredentialsException'),
 (196,'remoteAddress','0:0:0:0:0:0:0:1'),
 (196,'sessionId','E56842EED1D1B09E85B6346440113A44'),
 (197,'remoteAddress','0:0:0:0:0:0:0:1'),
 (197,'sessionId','A9D38E33EC7B6218267D7475055179C2'),
 (198,'remoteAddress','0:0:0:0:0:0:0:1'),
 (198,'sessionId','F1C44BF7335A42BF1189B63F76D1E68C'),
 (199,'remoteAddress','0:0:0:0:0:0:0:1'),
 (199,'sessionId','22973A0B404E8394A53B2016F2670051'),
 (200,'remoteAddress','0:0:0:0:0:0:0:1'),
 (200,'sessionId','F1345B335814B6888A7C6544FB65A4E2'),
 (201,'message','Bad credentials'),
 (201,'type','org.springframework.security.authentication.BadCredentialsException'),
 (202,'remoteAddress','0:0:0:0:0:0:0:1'),
 (202,'sessionId','C9BBAEE0A8D5E9C6F96F3367674F3AFB'),
 (203,'remoteAddress','0:0:0:0:0:0:0:1'),
 (203,'sessionId','ECD955C318DAEC17DC6F7AB264245017'),
 (204,'remoteAddress','0:0:0:0:0:0:0:1'),
 (204,'sessionId','F9F420CDEAA94B6C5C2B8FA4D90248FC'),
 (205,'remoteAddress','0:0:0:0:0:0:0:1'),
 (205,'sessionId','CC3CA8C8CDF14EE9EA7BF395B61FB215'),
 (206,'remoteAddress','0:0:0:0:0:0:0:1'),
 (206,'sessionId','3F188158B2D2D519A53C0C1FB1D6E6E7'),
 (207,'remoteAddress','0:0:0:0:0:0:0:1'),
 (207,'sessionId','DF67BE6D7192E23440237CAC0833A3C6'),
 (208,'remoteAddress','0:0:0:0:0:0:0:1'),
 (208,'sessionId','A28914D77C10E19742C3267F6ACDDE77'),
 (209,'remoteAddress','0:0:0:0:0:0:0:1'),
 (209,'sessionId','871279FAB4DF890A0D8D7001AEC15CDD'),
 (210,'remoteAddress','0:0:0:0:0:0:0:1'),
 (210,'sessionId','B09347A72F680BAE3E71F905F3266374'),
 (211,'remoteAddress','0:0:0:0:0:0:0:1'),
 (211,'sessionId','43AB6757F5EA6B41F75C87FDB8A518C2'),
 (212,'remoteAddress','0:0:0:0:0:0:0:1'),
 (212,'sessionId','D6757DAAF7C44994A4575D5392AFF664'),
 (213,'remoteAddress','0:0:0:0:0:0:0:1'),
 (213,'sessionId','7B190BC41C4E4F16557E9C9C9E00FDFB'),
 (214,'remoteAddress','0:0:0:0:0:0:0:1'),
 (214,'sessionId','157C73F5E7D7D29B28DF6C9A063E2284'),
 (215,'remoteAddress','0:0:0:0:0:0:0:1'),
 (215,'sessionId','BBC4B011D6F024F461D0A623B546B507'),
 (216,'remoteAddress','0:0:0:0:0:0:0:1'),
 (216,'sessionId','B4DCB2454E421DEE8287AA643DA4C9F0'),
 (217,'remoteAddress','0:0:0:0:0:0:0:1'),
 (217,'sessionId','597AFFBD21124261597DAC19C5A4733A'),
 (218,'remoteAddress','0:0:0:0:0:0:0:1'),
 (218,'sessionId','9B3077005306A43A732D78FDEF5995AB'),
 (219,'remoteAddress','0:0:0:0:0:0:0:1'),
 (219,'sessionId','F3EEBAF16DEF8779F3A01013C20A2FB5'),
 (220,'remoteAddress','0:0:0:0:0:0:0:1'),
 (220,'sessionId','5A08684C1E57FA0172D4804F1370EEB8'),
 (221,'remoteAddress','0:0:0:0:0:0:0:1'),
 (221,'sessionId','3C1DED5C5A8866C7215B182E5838C523'),
 (222,'remoteAddress','0:0:0:0:0:0:0:1'),
 (222,'sessionId','2A9F6D12A7CA246FB8DCD208EEE9FA5B'),
 (223,'remoteAddress','0:0:0:0:0:0:0:1'),
 (223,'sessionId','23812034AA63D07ADBEE5A38F0886379'),
 (224,'remoteAddress','0:0:0:0:0:0:0:1'),
 (224,'sessionId','A18EAB17BF47032D7BDCC451B19D5B90'),
 (225,'remoteAddress','0:0:0:0:0:0:0:1'),
 (225,'sessionId','E3054C034DA2F29014B821BC9E092456'),
 (226,'remoteAddress','0:0:0:0:0:0:0:1'),
 (226,'sessionId','A04320C56084B23365DD776993646093'),
 (227,'remoteAddress','0:0:0:0:0:0:0:1'),
 (227,'sessionId','2976C5581FA5AED4CCFF0505A53D982D'),
 (228,'remoteAddress','0:0:0:0:0:0:0:1'),
 (228,'sessionId','92C7E519CE7DE6179EB19CCDC176A62B'),
 (229,'remoteAddress','0:0:0:0:0:0:0:1'),
 (229,'sessionId','5FF53FA38F50C72AFF081EE20ABD3885'),
 (230,'remoteAddress','0:0:0:0:0:0:0:1'),
 (230,'sessionId','2044CCE7692A44A652D6E7B77880380F'),
 (231,'remoteAddress','0:0:0:0:0:0:0:1'),
 (231,'sessionId','88F947040DC98F3D2A2035643F760E13'),
 (232,'remoteAddress','0:0:0:0:0:0:0:1'),
 (232,'sessionId','703DD8BC8DE0E5AB8E9FB4A8815C76B8'),
 (233,'remoteAddress','0:0:0:0:0:0:0:1'),
 (233,'sessionId','0D5674517BB2D0F0F987CB8CD50350F2'),
 (234,'remoteAddress','0:0:0:0:0:0:0:1'),
 (234,'sessionId','62E5B5A7DFB9B2C591F6FEEDCD818C1A'),
 (235,'remoteAddress','0:0:0:0:0:0:0:1'),
 (235,'sessionId','8F94399F468196A72110D94FDB32B317'),
 (236,'remoteAddress','0:0:0:0:0:0:0:1'),
 (236,'sessionId','086469BD3ED8A14917756427210E98A6'),
 (237,'remoteAddress','0:0:0:0:0:0:0:1'),
 (237,'sessionId','CCEC7DA994A9849DA0A546794495B0F0'),
 (238,'remoteAddress','0:0:0:0:0:0:0:1'),
 (238,'sessionId','1F55E871619F8D68227E14A8AE47069A'),
 (239,'remoteAddress','127.0.0.1'),
 (239,'sessionId','3E35ACABA321EF5041A70542BACA06E3'),
 (240,'remoteAddress','127.0.0.1'),
 (240,'sessionId','F1AB5AB09311B45B006593B30D2CDFB5'),
 (241,'remoteAddress','0:0:0:0:0:0:0:1'),
 (241,'sessionId','EE9979299BBA4419DAE5477C1A3C093B'),
 (242,'remoteAddress','0:0:0:0:0:0:0:1'),
 (242,'sessionId','9920E29DEFD5849E760CAABB84A6E2D2'),
 (243,'remoteAddress','0:0:0:0:0:0:0:1'),
 (243,'sessionId','E687D60E75605CC9384190DB22CAD8AC'),
 (244,'remoteAddress','0:0:0:0:0:0:0:1'),
 (244,'sessionId','B71EFCF84593E484FE2342CE491B59E8'),
 (245,'remoteAddress','0:0:0:0:0:0:0:1'),
 (245,'sessionId','0BA656795E346284C4AD7D7A52096B84'),
 (246,'remoteAddress','0:0:0:0:0:0:0:1'),
 (246,'sessionId','2A7CEAF49EEF6ADC52708C9B4DD0CA4E'),
 (247,'remoteAddress','0:0:0:0:0:0:0:1'),
 (247,'sessionId','E9D59D5BA143BF2E015B2F1AF6D996EC'),
 (248,'remoteAddress','0:0:0:0:0:0:0:1'),
 (248,'sessionId','01DE6231B57D52092A31766FEA5BC97D'),
 (249,'remoteAddress','0:0:0:0:0:0:0:1'),
 (249,'sessionId','C1F6F52F1D68D6EB36CFE6094594A694'),
 (250,'remoteAddress','0:0:0:0:0:0:0:1'),
 (250,'sessionId','BA6CC01D47B22FC319DE0F8FEE9C047E');
UNLOCK TABLES;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;


--
-- Definition of table `watererp`.`jhi_persistent_token`
--

DROP TABLE IF EXISTS `watererp`.`jhi_persistent_token`;
CREATE TABLE  `watererp`.`jhi_persistent_token` (
  `series` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `token_value` varchar(255) NOT NULL,
  `token_date` date DEFAULT NULL,
  `ip_address` varchar(39) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`series`),
  KEY `fk_user_persistent_token` (`user_id`),
  CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_persistent_token`
--

/*!40000 ALTER TABLE `jhi_persistent_token` DISABLE KEYS */;
LOCK TABLES `jhi_persistent_token` WRITE;
INSERT INTO `watererp`.`jhi_persistent_token` VALUES  ('+kuZ8fh+MT05jeTHFp5gmw==',5,'/FPZJ9OhXHNXmMOFgz7obg==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('+Pk/ksqHFtjiZEpwz8IVFw==',3,'mxORTPY9bPxFOvMgRBlUAg==','2016-03-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('+x9pYUuhC9mbEe9rwtR11Q==',3,'moGQ9xPllsRoWmPrNGPN5A==','2016-03-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('/29bYPrVgu1d1Sc7jw0g/A==',3,'8LwcwLZFkj1AZCc96MulbA==','2016-03-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('1+QXFJmIy6UbwHIQ6pxyqw==',15,'HCEI/JsrSbkhfqKDPYVlNQ==','2016-03-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('2uRUyClVWXV8AlYMNeuJOw==',3,'PErAzkiwxOnl+ZDZP6T7JQ==','2016-03-09','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('3+5ue5QHIAl3gaEJu2oyFQ==',3,'VKZnSsNabZGNesRM+Hpsvg==','2016-03-11','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('3Jc58QBWoVau1yojOb/H3g==',15,'gy3ieEY/pll52Q8TEujbag==','2016-03-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('4Aup8X2ZRQtO8SBDix3vAg==',3,'JmcI8+KGnhTfgXHMzc2WbQ==','2016-03-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('4Kz54liz72Tw0RjO2Zrkpw==',3,'x3C2IoyZxxmQ8IZ4lG8oMg==','2016-03-03','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0'),
 ('6B2RG6/uWQnWC28vOw8aXg==',3,'8QX7Ux+CmA4n198EaajbnA==','2016-03-08','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('7wzGSQte7LQJZBy4FB00zA==',29,'3iFZ6Aa9rNbM3Mx+jwfRsw==','2016-03-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('7Ytkb8b+j1F3nPGWDNtKcw==',3,'l96/eI6viuEUtDy5AibxbA==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('94hvipCH71zaqo62IRdfRw==',3,'XVI6TLhxx+zep89XenteTw==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('9iL/4QMAvR0xB64rW5HESw==',3,'/k/ylVdhu5TQbQGtpI5N0w==','2016-03-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('b2tUjwgyONjcsbKlUklC+Q==',3,'NiOf/v/Os1vh0byDEVURJA==','2016-03-16','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('BaKFrbA5ergaotzhQATfrw==',29,'m+gH8u2sRCjqxAeORaQ3zQ==','2016-03-23','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('BFMeF7yubUPWeHMlxnpHhg==',3,'86Dlw7pit1sjL3P4rA601A==','2016-03-14','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('bh/be/AQUN2jP5F1P7kdSQ==',3,'kn71wIORmJGn7dP9vuFMTQ==','2016-03-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('C0yHTgCTgnXcC62bNAch3g==',15,'QM8xVxLqO6GowIcWRmrfyw==','2016-03-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('cDuDRxT54VRDENf238evFw==',3,'qL156YzlR0uNU4rfJVaZGw==','2016-03-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('DEqQipDM3iLfE1PFx+mwRA==',3,'rlSkhLwJf+8q3MOatcrcDA==','2016-02-26','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('E4Oy802QfILWTBjkwYm95A==',3,'PeGsLDuZMTMKFV/0eIveBQ==','2016-03-03','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0'),
 ('F2kB3vVrHHh0k5/n0nIzKA==',3,'70Zv5H6ShoO3S9SvA5QpCg==','2016-03-07','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('fNpzeb666xEusKesIpiVaA==',15,'EB7kPB3Prax0zRKgFghQUg==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('gjgSoEiEm2u4z82bxneQbA==',15,'Q5ukTsruzqRjPjl2jNIIBA==','2016-03-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('H5h1sXuT/PROO3BRuTQoxQ==',3,'8Mdnjzl+cjTeaivY6fzJyg==','2016-03-08','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('hFKfqjPrW8Z/amqU43M89g==',5,'OCMXKNowMoVsmqZ2jGSrxQ==','2016-03-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('Hw5vcTOmIcEuAJ+K5/ttTg==',3,'o/B/WvzQojyU6CRPqa0kug==','2016-03-17','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('IgMAY7/2pqUwaRD9fRqtZA==',5,'R/v6AK3DT+5hKspBsE0QdQ==','2016-03-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('jRDH7snELmCidi4buUDLww==',5,'/gjFefbhA17/a1ZM8SLTsw==','2016-03-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('KktZvzrrvQS+oXZvzgcTUg==',3,'u31QC6MQA9wwAxm5BJQjyw==','2016-03-14','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('KU6VZmBHcK3RKoGgwhLDcA==',3,'CtWXmv/L/eFLnOkikbadrw==','2016-03-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('mJ9QRewb5tZnJtq1JS6BeQ==',3,'Y4wzvxHQSVqsUa7uMIa7ug==','2016-03-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('mLyebPZIjbx7SSunOdf+Wg==',3,'41vTjgSojIAoGzh7zCvKlA==','2016-03-07','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('mwRI6gjdS4jSIbBL/M9S/A==',5,'YfiL0mj0bauty4dctUuzzw==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('N0VbVqugqcpOaPe2ycnLMQ==',3,'YsBBSdNhwbDeIWaz442tHQ==','2016-03-15','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('nicdzpFVE7fA8XUEciBnBg==',15,'jjnMZHjRSEuYg7FgpryrNw==','2016-03-29','127.0.0.1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('nK0IBkLMvCyRtRagV7xkIA==',15,'O271OweAwksyy+Yei161xA==','2016-03-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('P0e/MdJF+ZPyjCmmCP/VLA==',3,'X1UpUG5k1rbHVscMv8bt+g==','2016-03-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('PpZTKOgtRshlEF2Y5H8Nzw==',3,'2oilX1ZtHFQ62F6g/CMZMg==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('qFVuu+uRSBJTp3pUTFHbiw==',3,'0mje7iCbeSAXZrqoHwhF0A==','2016-03-17','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('R6uDzkAd46IU0D3H4E0FVA==',3,'XcfBIoCDayNepzQsEjnkJg==','2016-03-17','127.0.0.1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('rXIEaYCdRMm4gzrZtlo3+Q==',3,'TgmeEQSq33s5paxqyFtkmQ==','2016-02-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('TN6nvrS4DAcjld/PAsVu3w==',3,'pWLUEigr2nT7tCBerVO3rw==','2016-03-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('uBmBvYHKZMXr5UHwlzKAPA==',3,'oIXVYnHZ2CAqS/lxhNHtBQ==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('UDMPyK37cLIktTJg9P5RrQ==',3,'ShKdK3zme2OSRapV6fqKMw==','2016-03-23','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('w8R34xrr8QJmlP5bdbRg0Q==',3,'xrnCE2P4+F4Gm2wY/shF3w==','2016-03-02','127.0.0.1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('Waj7OoRQvtqW2FGm3rwFHw==',3,'DScvaJTEMdPTgvNKd4KoRg==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('wtWMmV27ptr0Uutob4MNiQ==',3,'kzJYJannR0ZBgbGhEbYHqQ==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('x4nyB9VRFeiND5t0i1DU8Q==',3,'cEW9l632DDQaJ+cxS1szpw==','2016-02-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('x4RtQD81f5es8yJniJlwMg==',3,'3Uiil1Q8ezwtbjsEjH7MaQ==','2016-03-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('XDGXmczwil8KeD6KL6Yt1w==',3,'fbROZLGioPS/YUzyTyG2kg==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('xf8lo1lE43DIRysCXswyzQ==',3,'BRRKxIW3118BhhPFt6AyXA==','2016-03-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('Y14PvuTMLFbPHaMU7KlW9A==',3,'yHJLgjbsyzRXqkVlnn1+bA==','2016-03-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('yC0cZ89A6opEpVDUp7MPLA==',3,'JNF2a3Nb1kSNcOuOEsAR4Q==','2016-03-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('zy6YAeHzjqbMHb8NPwiMQg==',3,'RfJrmS7MnC9GxnetOTdgpg==','2016-03-11','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
UNLOCK TABLES;
/*!40000 ALTER TABLE `jhi_persistent_token` ENABLE KEYS */;


--
-- Definition of table `watererp`.`jhi_user`
--

DROP TABLE IF EXISTS `watererp`.`jhi_user`;
CREATE TABLE  `watererp`.`jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_user`
--

/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
LOCK TABLES `jhi_user` WRITE;
INSERT INTO `watererp`.`jhi_user` VALUES  (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost',0x01,'en',NULL,NULL,'system','2016-02-24 18:37:37',NULL,'admin','2016-03-07 14:55:39'),
 (2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost',0x01,'en',NULL,NULL,'system','2016-02-24 18:37:37',NULL,NULL,NULL),
 (3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost',0x01,'en',NULL,NULL,'system','2016-02-24 18:37:37',NULL,NULL,NULL),
 (4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost',0x01,'en',NULL,NULL,'system','2016-02-24 18:37:37',NULL,NULL,NULL),
 (5,'customer','$2a$10$TYs/wcvo6fAPu0Xdt.jP3.pol1TJpvr13XvYIlBDzlut/Ytlsn/AO','customer','customer','customer@localhost',0x01,'en','NULL',NULL,'anonymousUser','2016-03-04 18:31:04',NULL,'admin','2016-03-08 10:00:31'),
 (6,'sf0006','$2a$10$.bcy9Ujl3NqgDj6zjXsRHe.UlXABdJyDbcByQ8nX9MvrHAwSYH9xi',NULL,NULL,'sf0006@localhost',0x01,'en','74075798038214678044',NULL,'anonymousUser','2016-03-18 12:43:28',NULL,'admin','2016-03-18 12:53:49'),
 (7,'sf0007','$2a$10$FDsPyaVUL9uFAR5vgWpX2ulAqEn5TJpoZjiCcGYqXO5iDkr11Ae3e',NULL,NULL,'sf0007@localhost',0x01,'en','96267878389108527398',NULL,'anonymousUser','2016-03-18 12:44:05',NULL,'admin','2016-03-18 12:53:50'),
 (8,'sf0008','$2a$10$/M8CkSWBuUlPxZavXb3h/.YQ.Q6lDArLBo5K/dIYcW6mdTfIf45bm',NULL,NULL,'sf0008@localhost',0x01,'en','54288279092286381964',NULL,'anonymousUser','2016-03-18 12:44:40',NULL,'admin','2016-03-18 12:53:51'),
 (9,'sf0009','$2a$10$nSqZo6ISDISQmxLFwbsq3u92OT3hOVS6mkjFlsDamxGInc8JBPtqq','Simon','Lupuga','sf0009@localhost',0x01,'en','97951374793812547574',NULL,'anonymousUser','2016-03-18 12:45:19',NULL,'sf0009','2016-03-28 12:45:10'),
 (10,'sf0010','$2a$10$rCzfnzFSb0dL3ocuA9RViuOaF1Gq5Vc0XzS7GSUKwBCv5tXI./39S','Claudius','Kaje','sf0010@localhost',0x01,'en','00161949524540976078',NULL,'anonymousUser','2016-03-18 12:45:54',NULL,'sf0010','2016-03-28 12:45:53'),
 (11,'sf0011','$2a$10$H7MmlGeb073RorJ69a1fdudLKTRLUFgkmFFeJ5xjCmtyjm.srVgDO','Vacant','vacant','sf0011@localhost',0x01,'en','33673054004780142550',NULL,'anonymousUser','2016-03-18 12:46:15',NULL,'sf0011','2016-03-28 12:46:42'),
 (12,'sf0012','$2a$10$sB2ETKC9vs0uljjuRtez6e0mhtaFDeJSyZGEwGIaGddJ34kRGIEpe',NULL,NULL,'sf0012@localhost',0x01,'en','71560095329758703944',NULL,'anonymousUser','2016-03-18 12:46:38',NULL,'admin','2016-03-18 12:53:56'),
 (13,'sf0013','$2a$10$okQIlNvGPOcBOadT8iyiJuC3vBGmXFtLe3UrvZpB33CWekNcdI/bu','Adoph','Shavu','sf0013@localhost',0x01,'en','91544376107529154273',NULL,'anonymousUser','2016-03-18 12:46:56',NULL,'sf0013','2016-03-28 12:47:31'),
 (14,'sf0014','$2a$10$qEvPMWpVpl54ndQdDE1FbuptYEfG9eqJbcYR7/DDXjSmaekdpwPyG','Herry','Makala','sf0014@localhost',0x01,'en','98218340790438067335',NULL,'anonymousUser','2016-03-18 12:47:34',NULL,'sf0014','2016-03-28 12:48:08'),
 (15,'sf0015','$2a$10$/396VihfwsI9iWd/UIEXKe7c3xbjwuWiePbUdUQL1Ug/tq9X2fy.i','Mbike','Jones','sf0015@localhost',0x01,'en','78744568076959050873',NULL,'anonymousUser','2016-03-18 12:47:51',NULL,'sf0015','2016-03-28 12:48:33'),
 (16,'sf0016','$2a$10$.YzGvqWeUSCQR5q2mtAl5u4ZVjBDmGCRh7xSbb5njETwx1ZwIN2bm','Fred','Selebwa','sf0016@localhost',0x01,'en','43535432288382530210',NULL,'anonymousUser','2016-03-18 12:48:46',NULL,'sf0016','2016-03-28 12:49:12'),
 (17,'sf0017','$2a$10$YDx44W8ShgQ0Jt7tVJXnWOMlyFJ3ernUc4Wh.yo.vVF6lYpGThccu','Eliezer','Josiah','sf0017@localhost',0x01,'en','06698912264567056315',NULL,'anonymousUser','2016-03-18 12:49:06',NULL,'sf0017','2016-03-28 12:49:56'),
 (18,'sf0018','$2a$10$if7uNqLVpZ.V5X/OkOes7uZ4gbDpwlrFmesdATE4PQrREuGff1c8G','Hellena','Mtembeje','sf0018@localhost',0x01,'en','38952305015731452387',NULL,'anonymousUser','2016-03-18 12:49:28',NULL,'sf0018','2016-03-28 12:50:47'),
 (19,'sf0019','$2a$10$43TTSOPygHilSwG5jA3joussQk3.vL.0B88bmbWGAiEyThp/0MLhm','Charles','Kiwely','sf0019@localhost',0x01,'en','80544871706487966991',NULL,'anonymousUser','2016-03-18 12:49:48',NULL,'sf0019','2016-03-28 12:51:19'),
 (20,'sf0020','$2a$10$UWsog7PJ9mJ5ALW3oT1qjuLsx4r6TtpC2.OtCi6D/qgMvD1pXuFiC',NULL,NULL,'sf0020@localhost',0x01,'en','83805380491517726753',NULL,'anonymousUser','2016-03-18 12:50:10',NULL,'admin','2016-03-18 12:54:00'),
 (21,'sf0021','$2a$10$fyfyZSZ/r94V30rK2V72r.Rs0yzTW81oB6mAvlq3Xz5OTBpBK0DJC','Salome','Mtwale','sf0021@localhost',0x01,'en','82028831560154180774',NULL,'anonymousUser','2016-03-18 12:51:39',NULL,'sf0021','2016-03-28 12:52:03'),
 (22,'sf0022','$2a$10$LV2mBS8VNveWYejGFcCh8OT2JoX.4oeEkPV/w5q5EDyY3dCQjUuGW','Mbaraka','Shemueta','sf0022@localhost',0x01,'en','29507620030240498935',NULL,'anonymousUser','2016-03-18 12:51:54',NULL,'sf0022','2016-03-28 12:53:17'),
 (23,'sf0023','$2a$10$ozkcRMy/Vjj6m.8O3XRg.OwDr3QybLS3qcX8/mZD.q4lzwVOxfPKW','Olivia','Masangya','sf0023@localhost',0x01,'en','73060005748380646738',NULL,'anonymousUser','2016-03-18 12:52:18',NULL,'sf0023','2016-03-28 12:53:58'),
 (24,'sf0024','$2a$10$0YPZsnW/yPzP5mx34t9Gse.x/.bAfB6nTTSBdvhifbYNzjsPQxeeW',NULL,NULL,'sf0024@localhost',0x01,'en','76650231398888348776',NULL,'anonymousUser','2016-03-18 12:52:33',NULL,'admin','2016-03-18 12:54:24'),
 (25,'sf0025','$2a$10$mUYlNHHcw0mpJiioW/p/0Ofx9qAPI6..bRfgSFHvo4ImvcB2CVQ96',NULL,NULL,'sf0025@localhost',0x01,'en','42984014546333704148',NULL,'anonymousUser','2016-03-18 12:52:46',NULL,'admin','2016-03-18 12:54:25'),
 (26,'sf0026','$2a$10$lnV2mPyzb/hLNWtYr7NeBeSe7SErEn4RwK3/Lzr6Oxjqkdj/yOUvS',NULL,NULL,'sf0026@localhost',0x01,'en','30926903415314102347',NULL,'anonymousUser','2016-03-18 12:53:00',NULL,'admin','2016-03-18 12:54:27'),
 (27,'sf0027','$2a$10$F58do6ARzxNnWwXFF3WVa.oH4Cnwfu9GGESf5c64Av7PnAVDDHQfu','Nobert','Kamba','sf0027@localhost',0x01,'en','88464590975638599993',NULL,'anonymousUser','2016-03-18 12:53:16',NULL,'sf0027','2016-03-28 12:57:46'),
 (28,'sf0028','$2a$10$K.A7jrXE..8CIm7SoeVMBepZyR2WdpZeQpPyUOfY429jeBaL.OMju','Francis','Hume','sf0028@localhost',0x01,'en','71805558970907066489',NULL,'anonymousUser','2016-03-18 12:53:33',NULL,'sf0028','2016-03-28 12:58:11'),
 (29,'sf0029','$2a$10$L1IXkaggwuSLeeF0fRR9CeMb/Qq6IM7EXLJI/jsdfFSOUQWf4oPHS',NULL,NULL,'sf0029@localhost',0x01,'en','68914493240806760364',NULL,'anonymousUser','2016-03-21 16:19:52',NULL,'admin','2016-03-21 16:20:23');
UNLOCK TABLES;
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;


--
-- Definition of table `watererp`.`jhi_user_authority`
--

DROP TABLE IF EXISTS `watererp`.`jhi_user_authority`;
CREATE TABLE  `watererp`.`jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_user_authority`
--

/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
LOCK TABLES `jhi_user_authority` WRITE;
INSERT INTO `watererp`.`jhi_user_authority` VALUES  (1,'ROLE_ADMIN'),
 (3,'ROLE_ADMIN'),
 (5,'ROLE_CUSTOMER'),
 (1,'ROLE_USER'),
 (3,'ROLE_USER'),
 (4,'ROLE_USER'),
 (6,'ROLE_USER'),
 (7,'ROLE_USER'),
 (8,'ROLE_USER'),
 (9,'ROLE_USER'),
 (10,'ROLE_USER'),
 (11,'ROLE_USER'),
 (12,'ROLE_USER'),
 (13,'ROLE_USER'),
 (14,'ROLE_USER'),
 (15,'ROLE_USER'),
 (16,'ROLE_USER'),
 (17,'ROLE_USER'),
 (18,'ROLE_USER'),
 (19,'ROLE_USER'),
 (20,'ROLE_USER'),
 (21,'ROLE_USER'),
 (22,'ROLE_USER'),
 (23,'ROLE_USER'),
 (24,'ROLE_USER'),
 (25,'ROLE_USER'),
 (26,'ROLE_USER'),
 (27,'ROLE_USER'),
 (28,'ROLE_USER'),
 (29,'ROLE_USER');
UNLOCK TABLES;
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;


--
-- Definition of table `watererp`.`main_sewerage_size`
--

DROP TABLE IF EXISTS `watererp`.`main_sewerage_size`;
CREATE TABLE  `watererp`.`main_sewerage_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `size` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`main_sewerage_size`
--

/*!40000 ALTER TABLE `main_sewerage_size` DISABLE KEYS */;
LOCK TABLES `main_sewerage_size` WRITE;
INSERT INTO `watererp`.`main_sewerage_size` VALUES  (1,10),
 (2,11),
 (3,12);
UNLOCK TABLES;
/*!40000 ALTER TABLE `main_sewerage_size` ENABLE KEYS */;


--
-- Definition of table `watererp`.`main_water_size`
--

DROP TABLE IF EXISTS `watererp`.`main_water_size`;
CREATE TABLE  `watererp`.`main_water_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `size` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`main_water_size`
--

/*!40000 ALTER TABLE `main_water_size` DISABLE KEYS */;
LOCK TABLES `main_water_size` WRITE;
INSERT INTO `watererp`.`main_water_size` VALUES  (1,5),
 (2,6),
 (3,7);
UNLOCK TABLES;
/*!40000 ALTER TABLE `main_water_size` ENABLE KEYS */;


--
-- Definition of table `watererp`.`make_of_pipe`
--

DROP TABLE IF EXISTS `watererp`.`make_of_pipe`;
CREATE TABLE  `watererp`.`make_of_pipe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `make_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`make_of_pipe`
--

/*!40000 ALTER TABLE `make_of_pipe` DISABLE KEYS */;
LOCK TABLES `make_of_pipe` WRITE;
INSERT INTO `watererp`.`make_of_pipe` VALUES  (1,'Pipe make 1'),
 (2,'Pipe Make 2');
UNLOCK TABLES;
/*!40000 ALTER TABLE `make_of_pipe` ENABLE KEYS */;


--
-- Definition of table `watererp`.`manage_cash_point`
--

DROP TABLE IF EXISTS `watererp`.`manage_cash_point`;
CREATE TABLE  `watererp`.`manage_cash_point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `today_date` timestamp NULL DEFAULT NULL,
  `payee_name` varchar(255) DEFAULT NULL,
  `txn_amount` float DEFAULT NULL,
  `open_bal` float DEFAULT NULL,
  `avail_bal` float DEFAULT NULL,
  `total_receipts` int(11) DEFAULT NULL,
  `location_code` varchar(255) DEFAULT NULL,
  `transaction_type_master_id` bigint(20) DEFAULT NULL,
  `cash_book_master_id` bigint(20) DEFAULT NULL,
  `payment_types_id` bigint(20) DEFAULT NULL,
  `file_number_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_managecashpoint_transactiontypemaster_id` (`transaction_type_master_id`),
  KEY `fk_managecashpoint_cashbookmaster_id` (`cash_book_master_id`),
  KEY `fk_managecashpoint_paymenttypes_id` (`payment_types_id`),
  KEY `fk_managecashpoint_filenumber_id` (`file_number_id`),
  KEY `fk_managecashpoint_customer_id` (`customer_id`),
  CONSTRAINT `fk_managecashpoint_cashbookmaster_id` FOREIGN KEY (`cash_book_master_id`) REFERENCES `cash_book_master` (`id`),
  CONSTRAINT `fk_managecashpoint_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_managecashpoint_filenumber_id` FOREIGN KEY (`file_number_id`) REFERENCES `file_number` (`id`),
  CONSTRAINT `fk_managecashpoint_paymenttypes_id` FOREIGN KEY (`payment_types_id`) REFERENCES `payment_types` (`id`),
  CONSTRAINT `fk_managecashpoint_transactiontypemaster_id` FOREIGN KEY (`transaction_type_master_id`) REFERENCES `transaction_type_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`manage_cash_point`
--

/*!40000 ALTER TABLE `manage_cash_point` DISABLE KEYS */;
LOCK TABLES `manage_cash_point` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `manage_cash_point` ENABLE KEYS */;


--
-- Definition of table `watererp`.`material_master`
--

DROP TABLE IF EXISTS `watererp`.`material_master`;
CREATE TABLE  `watererp`.`material_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `material_name` varchar(255) DEFAULT NULL,
  `consumable_flag` varchar(255) DEFAULT NULL,
  `uom_id` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `sub_category_id` bigint(20) DEFAULT NULL,
  `item_code_id` bigint(20) DEFAULT NULL,
  `item_sub_code_id` bigint(20) DEFAULT NULL,
  `rate_contract_flag` varchar(255) DEFAULT NULL,
  `unit_rate` decimal(10,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL,
  `last_modified_date` timestamp NULL,
  `company_code_id` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`material_master`
--

/*!40000 ALTER TABLE `material_master` DISABLE KEYS */;
LOCK TABLES `material_master` WRITE;
INSERT INTO `watererp`.`material_master` VALUES  (2,'Threading Tape',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (3,'G.S. Pipe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'30000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (4,'Pipe Polly',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1600.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (5,'Coupling',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (6,'Bib Tape',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (7,'Tee',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3500.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (8,'Union',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (9,'Elbow',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-23 00:00:00','2016-03-30 00:00:00',NULL),
 (10,'Polly Connector',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2500.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (11,'Plain Socket',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (12,'Reducing Socket/Bush',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (13,'Stop Cock',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (14,'Clamp Saddle',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `material_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`menu_item`
--

DROP TABLE IF EXISTS `watererp`.`menu_item`;
CREATE TABLE  `watererp`.`menu_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`menu_item`
--

/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
LOCK TABLES `menu_item` WRITE;
INSERT INTO `watererp`.`menu_item` VALUES  (1,'Module','#/modules','2016-03-09 00:00:00'),
 (2,'Application Form','#/applicationTxns/new','2016-03-10 00:00:00'),
 (3,'Menu Items','#/menuItems','2016-03-10 00:00:00'),
 (4,'Url','#/urls','2016-03-10 00:00:00'),
 (5,'Menu Item 2 Urls','#/menuItem2Urls','2016-03-10 00:00:00'),
 (6,'Module 2 Menu Items','#/module2MenuItems','2016-03-10 00:00:00'),
 (7,'Url 2 Roles','#/url2Roles','2016-03-10 00:00:00'),
 (8,'Application Details','#/applicationTxns','2016-03-10 00:00:00'),
 (9,'Manage Cash Point','#/manageCashPoints','2016-03-10 00:00:00'),
 (10,'Feasibility Study','#/feasibilityStudys','2016-03-11 00:00:00'),
 (11,'Prepare Proceedings','#/proceedingss','2016-03-15 00:00:00'),
 (12,'Proceeding Form','#/proceedingss/new','2016-03-22 00:00:00'),
 (13,'Item Category Master','#/itemCategoryMasters','2016-03-30 00:00:00'),
 (14,'Item Sub Category Masters','#/itemSubCategoryMasters','2016-03-30 00:00:00'),
 (15,'Item Code Masters','#/itemCodeMasters','2016-03-30 00:00:00'),
 (16,'Item Company Masters','#/itemCompanyMasters','2016-03-30 00:00:00'),
 (17,'Item Sub Code Master','#/itemSubCodeMasters','2016-03-30 00:00:00'),
 (18,'Material Master','#/materialMasters','2016-03-30 00:00:00'),
 (19,'Sib Entry','#/sibEntrys','2016-03-30 00:00:00');
UNLOCK TABLES;
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;


--
-- Definition of table `watererp`.`menu_item2_url`
--

DROP TABLE IF EXISTS `watererp`.`menu_item2_url`;
CREATE TABLE  `watererp`.`menu_item2_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_item_id` bigint(20) DEFAULT NULL,
  `url_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_menuitem2url_menuitem_id` (`menu_item_id`),
  KEY `fk_menuitem2url_url_id` (`url_id`),
  CONSTRAINT `fk_menuitem2url_menuitem_id` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_item` (`id`),
  CONSTRAINT `fk_menuitem2url_url_id` FOREIGN KEY (`url_id`) REFERENCES `url` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`menu_item2_url`
--

/*!40000 ALTER TABLE `menu_item2_url` DISABLE KEYS */;
LOCK TABLES `menu_item2_url` WRITE;
INSERT INTO `watererp`.`menu_item2_url` VALUES  (1,1,1),
 (2,2,8),
 (3,3,3),
 (4,4,4),
 (5,5,5),
 (6,6,6),
 (7,7,7),
 (8,8,2),
 (9,9,9),
 (10,10,10),
 (11,11,11),
 (12,12,12),
 (13,13,13),
 (14,14,14),
 (15,15,15),
 (16,16,16),
 (17,17,17),
 (18,18,18),
 (19,19,19);
UNLOCK TABLES;
/*!40000 ALTER TABLE `menu_item2_url` ENABLE KEYS */;


--
-- Definition of table `watererp`.`module`
--

DROP TABLE IF EXISTS `watererp`.`module`;
CREATE TABLE  `watererp`.`module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`module`
--

/*!40000 ALTER TABLE `module` DISABLE KEYS */;
LOCK TABLES `module` WRITE;
INSERT INTO `watererp`.`module` VALUES  (1,'Role Management',1,'2016-03-09 00:00:00'),
 (2,'Connection',2,'2016-03-10 00:00:00'),
 (3,'Items Details',3,'2016-03-30 00:00:00');
UNLOCK TABLES;
/*!40000 ALTER TABLE `module` ENABLE KEYS */;


--
-- Definition of table `watererp`.`module2_menu_item`
--

DROP TABLE IF EXISTS `watererp`.`module2_menu_item`;
CREATE TABLE  `watererp`.`module2_menu_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `priority` int(11) DEFAULT NULL,
  `module_id` bigint(20) DEFAULT NULL,
  `menu_item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_module2menuitem_module_id` (`module_id`),
  KEY `fk_module2menuitem_menuitem_id` (`menu_item_id`),
  CONSTRAINT `fk_module2menuitem_menuitem_id` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_item` (`id`),
  CONSTRAINT `fk_module2menuitem_module_id` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`module2_menu_item`
--

/*!40000 ALTER TABLE `module2_menu_item` DISABLE KEYS */;
LOCK TABLES `module2_menu_item` WRITE;
INSERT INTO `watererp`.`module2_menu_item` VALUES  (1,1,2,2),
 (2,1,1,1),
 (3,2,1,3),
 (4,3,1,4),
 (5,4,1,5),
 (6,5,1,6),
 (7,6,1,7),
 (8,2,2,8),
 (9,6,2,9),
 (10,3,2,10),
 (11,4,2,11),
 (12,5,2,12),
 (13,1,3,13),
 (14,2,3,14),
 (15,3,3,15),
 (16,4,3,16),
 (17,5,3,17),
 (18,6,3,18),
 (19,7,3,19);
UNLOCK TABLES;
/*!40000 ALTER TABLE `module2_menu_item` ENABLE KEYS */;


--
-- Definition of table `watererp`.`org_hierarchy`
--

DROP TABLE IF EXISTS `watererp`.`org_hierarchy`;
CREATE TABLE  `watererp`.`org_hierarchy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hierarchy_name` varchar(255) DEFAULT NULL,
  `parent_hierarchy_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orghierarchy_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_orghierarchy_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_hierarchy`
--

/*!40000 ALTER TABLE `org_hierarchy` DISABLE KEYS */;
LOCK TABLES `org_hierarchy` WRITE;
INSERT INTO `watererp`.`org_hierarchy` VALUES  (1,'Ministry Of Waters',0,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (2,'Board Of Directors',1,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (3,'Energy & Water Utilities Regulatory Authority',2,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (4,'Managing Director',2,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (5,'Internal Auditor',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (6,'Legal Officier',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (7,'Public Relations Officer',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (8,'HPMU',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (9,'Stores & Supplies Officer',8,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (10,'Technical Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (11,'Commercial Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (12,'Finance Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (13,'Human Resource & Administration Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (14,'Officer, GIS, Planning, Design & Construction',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (15,'Officer, Operation & Maintance - NRW, Water Supply and Sanitation',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (16,'Billing Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (17,'Credit Control Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (18,'ICT & Customer Care Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (19,'Accountant',12,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (20,'Human Resource & Administration Section',13,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (21,'Zonal Supervisers',16,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (22,'Assistant Accountant(Revenue)',19,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (23,'Assistant Accountant(Expenditure)',19,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (24,'Technical Zonal Supervisor',15,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (25,'Customer',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `org_hierarchy` ENABLE KEYS */;


--
-- Definition of table `watererp`.`org_role_hierarchy`
--

DROP TABLE IF EXISTS `watererp`.`org_role_hierarchy`;
CREATE TABLE  `watererp`.`org_role_hierarchy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_hierarchy_name` varchar(255) DEFAULT NULL,
  `parent_role_hierarchy_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orgrolehierarchy_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_orgrolehierarchy_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_role_hierarchy`
--

/*!40000 ALTER TABLE `org_role_hierarchy` DISABLE KEYS */;
LOCK TABLES `org_role_hierarchy` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `org_role_hierarchy` ENABLE KEYS */;


--
-- Definition of table `watererp`.`org_role_instance`
--

DROP TABLE IF EXISTS `watererp`.`org_role_instance`;
CREATE TABLE  `watererp`.`org_role_instance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_role_name` varchar(255) DEFAULT NULL,
  `parent_org_role_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `is_head` int(11) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  `org_role_hierarchy_id` bigint(20) DEFAULT NULL,
  `departments_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orgroleinstance_statusmaster_id` (`status_master_id`),
  KEY `fk_orgroleinstance_orgrolehierarchy_id` (`org_role_hierarchy_id`),
  KEY `fk_orgroleinstance_departmentsmaster_id` (`departments_master_id`),
  CONSTRAINT `fk_orgroleinstance_departmentsmaster_id` FOREIGN KEY (`departments_master_id`) REFERENCES `departments_master` (`id`),
  CONSTRAINT `fk_orgroleinstance_orgrolehierarchy_id` FOREIGN KEY (`org_role_hierarchy_id`) REFERENCES `org_role_hierarchy` (`id`),
  CONSTRAINT `fk_orgroleinstance_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_role_instance`
--

/*!40000 ALTER TABLE `org_role_instance` DISABLE KEYS */;
LOCK TABLES `org_role_instance` WRITE;
INSERT INTO `watererp`.`org_role_instance` VALUES  (1,'Ministry Of Waters',0,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (2,'Board Of Directors',1,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (3,'Energy & Water Utilities Regulatory Authority',2,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (4,'Managing Director',2,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (5,'Internal Auditor',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (6,'Legal Officier',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (7,'Public Relations Officer',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (8,'HPMU',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (9,'Stores & Supplies Officer',8,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (10,'Technical Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (11,'Commercial Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (12,'Finance Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (13,'Human Resource & Administration Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (14,'Officer, GIS, Planning, Design & Construction',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (15,'Officer, Operation & Maintance - NRW, Water Supply and Sanitation',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (16,'Billing Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (17,'Credit Control Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (18,'ICT & Customer Care Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (19,'Accountant',12,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (20,'Human Resource & Administration Section',13,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (21,'Zonal Supervisers',16,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (22,'Assistant Accountant(Revenue)',19,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (23,'Assistant Accountant(Expenditure)',19,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (24,'Technical Zonal Supervisor',15,'2016-03-21 00:00:00','2016-03-21 00:00:00',1,2,NULL,NULL),
 (25,'Customer',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `org_role_instance` ENABLE KEYS */;


--
-- Definition of table `watererp`.`org_roles_master`
--

DROP TABLE IF EXISTS `watererp`.`org_roles_master`;
CREATE TABLE  `watererp`.`org_roles_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_role_name` varchar(255) DEFAULT NULL,
  `hierarchy_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orgrolesmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_orgrolesmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_roles_master`
--

/*!40000 ALTER TABLE `org_roles_master` DISABLE KEYS */;
LOCK TABLES `org_roles_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `org_roles_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`payment_types`
--

DROP TABLE IF EXISTS `watererp`.`payment_types`;
CREATE TABLE  `watererp`.`payment_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_mode` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`payment_types`
--

/*!40000 ALTER TABLE `payment_types` DISABLE KEYS */;
LOCK TABLES `payment_types` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `payment_types` ENABLE KEYS */;


--
-- Definition of table `watererp`.`percentage_master`
--

DROP TABLE IF EXISTS `watererp`.`percentage_master`;
CREATE TABLE  `watererp`.`percentage_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `percent_type` varchar(255) DEFAULT NULL,
  `percent_value` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`percentage_master`
--

/*!40000 ALTER TABLE `percentage_master` DISABLE KEYS */;
LOCK TABLES `percentage_master` WRITE;
INSERT INTO `watererp`.`percentage_master` VALUES  (1,'Supervision',10),
 (2,'Labour Charges',20),
 (3,'Site Survey',5),
 (4,'Connection Fee of A & B',20);
UNLOCK TABLES;
/*!40000 ALTER TABLE `percentage_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`pipe_size_master`
--

DROP TABLE IF EXISTS `watererp`.`pipe_size_master`;
CREATE TABLE  `watererp`.`pipe_size_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pipe_size` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`pipe_size_master`
--

/*!40000 ALTER TABLE `pipe_size_master` DISABLE KEYS */;
LOCK TABLES `pipe_size_master` WRITE;
INSERT INTO `watererp`.`pipe_size_master` VALUES  (1,15),
 (2,20),
 (3,25),
 (4,40),
 (5,50);
UNLOCK TABLES;
/*!40000 ALTER TABLE `pipe_size_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`proceedings`
--

DROP TABLE IF EXISTS `watererp`.`proceedings`;
CREATE TABLE  `watererp`.`proceedings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sub_total_a` double DEFAULT NULL,
  `supervision_charge` double DEFAULT NULL,
  `labour_charge` double DEFAULT NULL,
  `site_survey` double DEFAULT NULL,
  `sub_total_b` double DEFAULT NULL,
  `connection_fee` double DEFAULT NULL,
  `water_meter_shs` double DEFAULT NULL,
  `application_form_fee` double DEFAULT NULL,
  `grand_total` double DEFAULT NULL,
  `supervision_percent_id` bigint(20) DEFAULT NULL,
  `labour_charge_percent_id` bigint(20) DEFAULT NULL,
  `site_survey_percent_id` bigint(20) DEFAULT NULL,
  `connection_fee_percent_id` bigint(20) DEFAULT NULL,
  `application_txn_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_proceedings_supervisionpercent_id` (`supervision_percent_id`),
  KEY `fk_proceedings_labourchargepercent_id` (`labour_charge_percent_id`),
  KEY `fk_proceedings_sitesurveypercent_id` (`site_survey_percent_id`),
  KEY `fk_proceedings_connectionfeepercent_id` (`connection_fee_percent_id`),
  KEY `fk_proceedings_applicationtxn_id` (`application_txn_id`),
  CONSTRAINT `fk_proceedings_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_proceedings_connectionfeepercent_id` FOREIGN KEY (`connection_fee_percent_id`) REFERENCES `percentage_master` (`id`),
  CONSTRAINT `fk_proceedings_labourchargepercent_id` FOREIGN KEY (`labour_charge_percent_id`) REFERENCES `percentage_master` (`id`),
  CONSTRAINT `fk_proceedings_sitesurveypercent_id` FOREIGN KEY (`site_survey_percent_id`) REFERENCES `percentage_master` (`id`),
  CONSTRAINT `fk_proceedings_supervisionpercent_id` FOREIGN KEY (`supervision_percent_id`) REFERENCES `percentage_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`proceedings`
--

/*!40000 ALTER TABLE `proceedings` DISABLE KEYS */;
LOCK TABLES `proceedings` WRITE;
INSERT INTO `watererp`.`proceedings` VALUES  (7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14),
 (8,3546,65465,654,654,654,654,654,654,654,NULL,NULL,NULL,NULL,14),
 (9,353,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,15),
 (11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14);
UNLOCK TABLES;
/*!40000 ALTER TABLE `proceedings` ENABLE KEYS */;


--
-- Definition of table `watererp`.`re_allotment`
--

DROP TABLE IF EXISTS `watererp`.`re_allotment`;
CREATE TABLE  `watererp`.`re_allotment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_number_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `feasibility_status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reallotment_filenumber_id` (`file_number_id`),
  KEY `fk_reallotment_customer_id` (`customer_id`),
  KEY `fk_reallotment_feasibilitystatus_id` (`feasibility_status_id`),
  CONSTRAINT `fk_reallotment_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_reallotment_feasibilitystatus_id` FOREIGN KEY (`feasibility_status_id`) REFERENCES `feasibility_status` (`id`),
  CONSTRAINT `fk_reallotment_filenumber_id` FOREIGN KEY (`file_number_id`) REFERENCES `file_number` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`re_allotment`
--

/*!40000 ALTER TABLE `re_allotment` DISABLE KEYS */;
LOCK TABLES `re_allotment` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `re_allotment` ENABLE KEYS */;


--
-- Definition of table `watererp`.`req_desig_workflow_mapping`
--

DROP TABLE IF EXISTS `watererp`.`req_desig_workflow_mapping`;
CREATE TABLE  `watererp`.`req_desig_workflow_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `workflow_master_id` bigint(20) DEFAULT NULL,
  `request_master_id` bigint(20) DEFAULT NULL,
  `designation_master_id` bigint(20) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reqdesigworkflowmapping_workflowmaster_id` (`workflow_master_id`),
  KEY `fk_reqdesigworkflowmapping_requestmaster_id` (`request_master_id`),
  KEY `fk_reqdesigworkflowmapping_designationmaster_id` (`designation_master_id`),
  KEY `fk_reqdesigworkflowmapping_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_reqdesigworkflowmapping_designationmaster_id` FOREIGN KEY (`designation_master_id`) REFERENCES `designation_master` (`id`),
  CONSTRAINT `fk_reqdesigworkflowmapping_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`),
  CONSTRAINT `fk_reqdesigworkflowmapping_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_reqdesigworkflowmapping_workflowmaster_id` FOREIGN KEY (`workflow_master_id`) REFERENCES `workflow_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`req_desig_workflow_mapping`
--

/*!40000 ALTER TABLE `req_desig_workflow_mapping` DISABLE KEYS */;
LOCK TABLES `req_desig_workflow_mapping` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `req_desig_workflow_mapping` ENABLE KEYS */;


--
-- Definition of table `watererp`.`req_org_workflow_mapping`
--

DROP TABLE IF EXISTS `watererp`.`req_org_workflow_mapping`;
CREATE TABLE  `watererp`.`req_org_workflow_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `workflow_master_id` bigint(20) DEFAULT NULL,
  `request_master_id` bigint(20) DEFAULT NULL,
  `org_role_instance_id` bigint(20) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reqorgworkflowmapping_workflowmaster_id` (`workflow_master_id`),
  KEY `fk_reqorgworkflowmapping_requestmaster_id` (`request_master_id`),
  KEY `fk_reqorgworkflowmapping_orgroleinstance_id` (`org_role_instance_id`),
  KEY `fk_reqorgworkflowmapping_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_reqorgworkflowmapping_orgroleinstance_id` FOREIGN KEY (`org_role_instance_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_reqorgworkflowmapping_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`),
  CONSTRAINT `fk_reqorgworkflowmapping_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_reqorgworkflowmapping_workflowmaster_id` FOREIGN KEY (`workflow_master_id`) REFERENCES `workflow_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`req_org_workflow_mapping`
--

/*!40000 ALTER TABLE `req_org_workflow_mapping` DISABLE KEYS */;
LOCK TABLES `req_org_workflow_mapping` WRITE;
INSERT INTO `watererp`.`req_org_workflow_mapping` VALUES  (1,'2016-03-21 00:00:00','2016-03-21 00:00:00',1,1,25,2),
 (2,'2016-03-21 00:00:00','2016-03-21 00:00:00',1,1,10,2),
 (3,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `req_org_workflow_mapping` ENABLE KEYS */;


--
-- Definition of table `watererp`.`request_master`
--

DROP TABLE IF EXISTS `watererp`.`request_master`;
CREATE TABLE  `watererp`.`request_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_type` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `internal_flag` int(11) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_requestmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_requestmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`request_master`
--

/*!40000 ALTER TABLE `request_master` DISABLE KEYS */;
LOCK TABLES `request_master` WRITE;
INSERT INTO `watererp`.`request_master` VALUES  (1,'REQUISITION','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,0,2),
 (2,'LEAVE','2016-03-26 00:00:00','2016-03-26 00:00:00',NULL,0,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `request_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`request_workflow_history`
--

DROP TABLE IF EXISTS `watererp`.`request_workflow_history`;
CREATE TABLE  `watererp`.`request_workflow_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_stage` int(11) DEFAULT NULL,
  `assigned_date` timestamp NULL DEFAULT NULL,
  `actioned_date` timestamp NULL DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `assigned_role` int(11) DEFAULT NULL,
  `domain_object` bigint(20) DEFAULT NULL,
  `assigned_from_id` bigint(20) DEFAULT NULL,
  `assigned_to_id` bigint(20) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  `request_master_id` bigint(20) DEFAULT NULL,
  `workflow_master_id` bigint(20) DEFAULT NULL,
  `workflow_stage_master_id` bigint(20) DEFAULT NULL,
  `applied_by_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_requestworkflowhistory_assignedfrom_id` (`assigned_from_id`),
  KEY `fk_requestworkflowhistory_assignedto_id` (`assigned_to_id`),
  KEY `fk_requestworkflowhistory_statusmaster_id` (`status_master_id`),
  KEY `fk_requestworkflowhistory_requestmaster_id` (`request_master_id`),
  KEY `fk_requestworkflowhistory_workflowmaster_id` (`workflow_master_id`),
  KEY `fk_requestworkflowhistory_workflowstagemaster_id` (`workflow_stage_master_id`),
  KEY `fk_requestworkflowhistory_appliedby_id` (`applied_by_id`),
  CONSTRAINT `fk_requestworkflowhistory_appliedby_id` FOREIGN KEY (`applied_by_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_requestworkflowhistory_assignedfrom_id` FOREIGN KEY (`assigned_from_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_requestworkflowhistory_assignedto_id` FOREIGN KEY (`assigned_to_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_requestworkflowhistory_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`),
  CONSTRAINT `fk_requestworkflowhistory_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_requestworkflowhistory_workflowmaster_id` FOREIGN KEY (`workflow_master_id`) REFERENCES `workflow_master` (`id`),
  CONSTRAINT `fk_requestworkflowhistory_workflowstagemaster_id` FOREIGN KEY (`workflow_stage_master_id`) REFERENCES `workflow_stage_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`request_workflow_history`
--

/*!40000 ALTER TABLE `request_workflow_history` DISABLE KEYS */;
LOCK TABLES `request_workflow_history` WRITE;
INSERT INTO `watererp`.`request_workflow_history` VALUES  (65,1,'2016-03-28 17:51:35','2016-03-28 17:51:35',NULL,'127.0.0.1',NULL,14,5,15,5,1,1,NULL,NULL),
 (66,2,'2016-03-28 17:53:09','2016-03-28 17:53:09','2nd stage','127.0.0.1',NULL,14,15,29,3,1,1,NULL,NULL),
 (67,1,'2016-03-28 18:26:19','2016-03-28 18:26:19',NULL,'127.0.0.1',NULL,15,5,15,5,1,1,NULL,NULL),
 (68,2,'2016-03-28 18:38:02','2016-03-28 18:38:02','2nd stage','127.0.0.1',NULL,15,15,29,3,1,1,NULL,NULL),
 (69,1,'2016-03-29 10:56:28','2016-03-29 10:56:28',NULL,'127.0.0.1',NULL,18,5,15,3,1,1,NULL,NULL),
 (70,1,'2016-03-29 11:23:29','2016-03-29 11:23:29',NULL,'127.0.0.1',NULL,19,5,15,3,1,1,NULL,NULL),
 (71,1,'2016-03-29 11:32:10','2016-03-29 11:32:10',NULL,'127.0.0.1',NULL,20,5,15,3,1,1,NULL,NULL),
 (72,1,'2016-03-29 11:38:54','2016-03-29 11:38:54',NULL,'127.0.0.1',NULL,21,5,15,3,1,1,NULL,NULL),
 (73,1,'2016-03-29 11:42:41','2016-03-29 11:42:41',NULL,'127.0.0.1',NULL,22,5,15,3,1,1,NULL,NULL),
 (74,1,'2016-03-29 11:47:24','2016-03-29 11:47:24',NULL,'127.0.0.1',NULL,23,5,15,3,1,1,NULL,NULL),
 (75,1,'2016-03-29 11:59:12','2016-03-29 11:59:12',NULL,'127.0.0.1',NULL,24,15,15,3,1,1,NULL,NULL),
 (76,1,'2016-03-29 12:25:35','2016-03-29 12:25:35',NULL,'127.0.0.1',NULL,25,5,15,3,1,1,NULL,NULL),
 (77,1,'2016-03-29 12:27:16','2016-03-29 12:27:16',NULL,'127.0.0.1',NULL,26,5,15,3,1,1,NULL,NULL),
 (78,1,'2016-03-29 12:38:18','2016-03-29 12:38:18',NULL,'127.0.0.1',NULL,27,5,15,3,1,1,NULL,NULL),
 (79,1,'2016-03-29 12:44:12','2016-03-29 12:44:12',NULL,'127.0.0.1',NULL,29,5,15,3,1,1,NULL,NULL),
 (80,1,'2016-03-29 12:47:15','2016-03-29 12:47:15',NULL,'127.0.0.1',NULL,30,5,15,3,1,1,NULL,NULL),
 (81,1,'2016-03-29 12:57:28','2016-03-29 12:57:28',NULL,'127.0.0.1',NULL,31,15,15,3,1,1,NULL,NULL),
 (82,1,'2016-03-29 13:10:45','2016-03-29 13:10:45',NULL,'127.0.0.1',NULL,32,15,15,3,1,1,NULL,NULL),
 (83,1,'2016-03-29 14:27:19','2016-03-29 14:27:19',NULL,'127.0.0.1',NULL,33,15,15,3,1,1,NULL,NULL),
 (84,1,'2016-03-29 14:48:58','2016-03-29 14:48:58',NULL,'127.0.0.1',NULL,34,5,15,3,1,1,NULL,NULL),
 (85,1,'2016-03-29 14:54:31','2016-03-29 14:54:31',NULL,'127.0.0.1',NULL,35,5,15,5,1,1,NULL,NULL),
 (86,2,'2016-03-29 19:05:32','2016-03-29 19:05:32','aswfawt','127.0.0.1',NULL,35,15,29,3,1,1,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `request_workflow_history` ENABLE KEYS */;


--
-- Definition of table `watererp`.`request_workflow_mapping`
--

DROP TABLE IF EXISTS `watererp`.`request_workflow_mapping`;
CREATE TABLE  `watererp`.`request_workflow_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  `workflow_master_id` bigint(20) DEFAULT NULL,
  `request_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_requestworkflowmapping_statusmaster_id` (`status_master_id`),
  KEY `fk_requestworkflowmapping_workflowmaster_id` (`workflow_master_id`),
  KEY `fk_requestworkflowmapping_requestmaster_id` (`request_master_id`),
  CONSTRAINT `fk_requestworkflowmapping_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`),
  CONSTRAINT `fk_requestworkflowmapping_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_requestworkflowmapping_workflowmaster_id` FOREIGN KEY (`workflow_master_id`) REFERENCES `workflow_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`request_workflow_mapping`
--

/*!40000 ALTER TABLE `request_workflow_mapping` DISABLE KEYS */;
LOCK TABLES `request_workflow_mapping` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `request_workflow_mapping` ENABLE KEYS */;


--
-- Definition of table `watererp`.`role_workflow_mapping`
--

DROP TABLE IF EXISTS `watererp`.`role_workflow_mapping`;
CREATE TABLE  `watererp`.`role_workflow_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  `org_role_instance_id` bigint(20) DEFAULT NULL,
  `workflow_master_id` bigint(20) DEFAULT NULL,
  `request_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_roleworkflowmapping_statusmaster_id` (`status_master_id`),
  KEY `fk_roleworkflowmapping_orgroleinstance_id` (`org_role_instance_id`),
  KEY `fk_roleworkflowmapping_workflowmaster_id` (`workflow_master_id`),
  KEY `fk_roleworkflowmapping_requestmaster_id` (`request_master_id`),
  CONSTRAINT `fk_roleworkflowmapping_orgroleinstance_id` FOREIGN KEY (`org_role_instance_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_roleworkflowmapping_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`),
  CONSTRAINT `fk_roleworkflowmapping_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_roleworkflowmapping_workflowmaster_id` FOREIGN KEY (`workflow_master_id`) REFERENCES `workflow_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`role_workflow_mapping`
--

/*!40000 ALTER TABLE `role_workflow_mapping` DISABLE KEYS */;
LOCK TABLES `role_workflow_mapping` WRITE;
INSERT INTO `watererp`.`role_workflow_mapping` VALUES  (1,'2016-03-21 00:00:00','2016-03-21 00:00:00',2,10,1,1),
 (2,'2016-03-21 00:00:00','2016-03-21 00:00:00',2,24,1,1),
 (3,'2016-03-21 00:00:00','2016-03-21 00:00:00',2,15,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `role_workflow_mapping` ENABLE KEYS */;


--
-- Definition of table `watererp`.`scheme_master`
--

DROP TABLE IF EXISTS `watererp`.`scheme_master`;
CREATE TABLE  `watererp`.`scheme_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scheme_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`scheme_master`
--

/*!40000 ALTER TABLE `scheme_master` DISABLE KEYS */;
LOCK TABLES `scheme_master` WRITE;
INSERT INTO `watererp`.`scheme_master` VALUES  (1,'Scheme 1'),
 (2,'Scheme 2');
UNLOCK TABLES;
/*!40000 ALTER TABLE `scheme_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`sewer_size`
--

DROP TABLE IF EXISTS `watererp`.`sewer_size`;
CREATE TABLE  `watererp`.`sewer_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sewer_size` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`sewer_size`
--

/*!40000 ALTER TABLE `sewer_size` DISABLE KEYS */;
LOCK TABLES `sewer_size` WRITE;
INSERT INTO `watererp`.`sewer_size` VALUES  (3,'100'),
 (4,'150'),
 (5,'200'),
 (6,'250'),
 (7,'300'),
 (31,'450');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sewer_size` ENABLE KEYS */;


--
-- Definition of table `watererp`.`sib_entry`
--

DROP TABLE IF EXISTS `watererp`.`sib_entry`;
CREATE TABLE  `watererp`.`sib_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sib_id` bigint(20) DEFAULT NULL,
  `so_no` varchar(255) DEFAULT NULL,
  `so_date` timestamp NULL,
  `demand_date` timestamp NULL,
  `dir` varchar(255) DEFAULT NULL,
  `div_name` varchar(255) DEFAULT NULL,
  `inv_no` bigint(20) DEFAULT NULL,
  `sib_date` timestamp NULL,
  `sib_no` varchar(255) DEFAULT NULL,
  `ir_date` timestamp NULL,
  `ir_no` varchar(255) DEFAULT NULL,
  `vendor_code` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `to_user` timestamp NULL,
  `from_user` timestamp NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL,
  `last_modified_date` timestamp NULL,
  `dc_no` varchar(255) DEFAULT NULL,
  `dc_date` timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`sib_entry`
--

/*!40000 ALTER TABLE `sib_entry` DISABLE KEYS */;
LOCK TABLES `sib_entry` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sib_entry` ENABLE KEYS */;


--
-- Definition of table `watererp`.`status_master`
--

DROP TABLE IF EXISTS `watererp`.`status_master`;
CREATE TABLE  `watererp`.`status_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`status_master`
--

/*!40000 ALTER TABLE `status_master` DISABLE KEYS */;
LOCK TABLES `status_master` WRITE;
INSERT INTO `watererp`.`status_master` VALUES  (1,'DISABLED','GENERAL'),
 (2,'ENABLED','GENERAL'),
 (3,'PENDING','WORKFLOW'),
 (4,'PROCESSING','WORKFLOW'),
 (5,'APPROVED','WORKFLOW'),
 (6,'DELEGATED','WORKFLOW'),
 (7,'DECLINED','WORKFLOW'),
 (8,'ESCALATED','WORKFLOW'),
 (9,'COMPLETED','WORKFLOW'),
 (10,'CANCELLED','WORKFLOW'),
 (11,'MANUAL','WORKFLOW'),
 (12,'AUTOMATIC','WORKFLOW'),
 (13,'RETIRED','EMPLOYEE STATUS'),
 (14,'TRANSFERED','EMPLOYEE STATUS'),
 (15,'RESIGNED','EMPLOYEE STATUS'),
 (16,'DEATH','EMPLOYEE STATUS');
UNLOCK TABLES;
/*!40000 ALTER TABLE `status_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`street_master`
--

DROP TABLE IF EXISTS `watererp`.`street_master`;
CREATE TABLE  `watererp`.`street_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `street_name` varchar(255) DEFAULT NULL,
  `street_code` varchar(255) DEFAULT NULL,
  `zone_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_streetmaster_zonemaster_id` (`zone_master_id`),
  CONSTRAINT `fk_streetmaster_zonemaster_id` FOREIGN KEY (`zone_master_id`) REFERENCES `zone_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`street_master`
--

/*!40000 ALTER TABLE `street_master` DISABLE KEYS */;
LOCK TABLES `street_master` WRITE;
INSERT INTO `watererp`.`street_master` VALUES  (1,'Street1','StreetCode1',1),
 (2,'Street2','StreetCode2',1),
 (3,'Street3','StreetCode3',2),
 (4,'Street4','StreetCode4',2),
 (5,'Street5','StreetCode5',3),
 (6,'Street6','StreetCode6',3),
 (7,'Street7','StreetCode7',4),
 (8,'Street8','StreetCode8',4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `street_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`sub_desig_category_master`
--

DROP TABLE IF EXISTS `watererp`.`sub_desig_category_master`;
CREATE TABLE  `watererp`.`sub_desig_category_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subdesigcategorymaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_subdesigcategorymaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`sub_desig_category_master`
--

/*!40000 ALTER TABLE `sub_desig_category_master` DISABLE KEYS */;
LOCK TABLES `sub_desig_category_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sub_desig_category_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`tariff_category_master`
--

DROP TABLE IF EXISTS `watererp`.`tariff_category_master`;
CREATE TABLE  `watererp`.`tariff_category_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tariff_name` varchar(255) NOT NULL,
  `tariff_unit` int(11) NOT NULL,
  `tariff_value` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_category_master`
--

/*!40000 ALTER TABLE `tariff_category_master` DISABLE KEYS */;
LOCK TABLES `tariff_category_master` WRITE;
INSERT INTO `watererp`.`tariff_category_master` VALUES  (1,'Tariff category 1',1,100),
 (2,'Tariff Category 2',2,200);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tariff_category_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`transaction_type_master`
--

DROP TABLE IF EXISTS `watererp`.`transaction_type_master`;
CREATE TABLE  `watererp`.`transaction_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_of_txn` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`transaction_type_master`
--

/*!40000 ALTER TABLE `transaction_type_master` DISABLE KEYS */;
LOCK TABLES `transaction_type_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `transaction_type_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`url`
--

DROP TABLE IF EXISTS `watererp`.`url`;
CREATE TABLE  `watererp`.`url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url_pattern` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`url`
--

/*!40000 ALTER TABLE `url` DISABLE KEYS */;
LOCK TABLES `url` WRITE;
INSERT INTO `watererp`.`url` VALUES  (1,'/modules',1),
 (2,'/applicationTxns',1),
 (3,'/menuItems',1),
 (4,'/urls',1),
 (5,'/menuItem2Urls',1),
 (6,'/module2MenuItems',1),
 (7,'/url2Roles',1),
 (8,'/applicationTxns/new',1),
 (9,'/manageCashPoints',1),
 (10,'/feasibilityStudys',1),
 (11,'/proceedingss',1),
 (12,'/proceedings-dialog',1),
 (13,'/itemCategoryMaster',1),
 (14,'/itemSubCategoryMaster',1),
 (15,'/itemCodeMaster',1),
 (16,'/itemCompanyMaster',1),
 (17,'/itemSubCodeMaster',1),
 (18,'/materialMaster',1),
 (19,'/sibEntry',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `url` ENABLE KEYS */;


--
-- Definition of table `watererp`.`url2_role`
--

DROP TABLE IF EXISTS `watererp`.`url2_role`;
CREATE TABLE  `watererp`.`url2_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url_id` bigint(20) DEFAULT NULL,
  `authority_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_url2role_url_id` (`url_id`),
  KEY `fk_url2role_authority_name` (`authority_name`),
  CONSTRAINT `fk_url2role_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_url2role_url_id` FOREIGN KEY (`url_id`) REFERENCES `url` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`url2_role`
--

/*!40000 ALTER TABLE `url2_role` DISABLE KEYS */;
LOCK TABLES `url2_role` WRITE;
INSERT INTO `watererp`.`url2_role` VALUES  (1,1,'ROLE_ADMIN'),
 (2,2,'ROLE_ADMIN'),
 (3,8,'ROLE_CUSTOMER'),
 (4,3,'ROLE_ADMIN'),
 (5,4,'ROLE_ADMIN'),
 (6,5,'ROLE_ADMIN'),
 (7,6,'ROLE_ADMIN'),
 (8,7,'ROLE_ADMIN'),
 (9,8,'ROLE_ADMIN'),
 (11,9,'ROLE_ADMIN'),
 (12,9,'ROLE_ADMIN'),
 (13,10,'ROLE_ADMIN'),
 (14,11,'ROLE_ADMIN'),
 (15,8,'ROLE_ADMIN'),
 (16,2,'ROLE_USER'),
 (17,8,'ROLE_USER'),
 (18,10,'ROLE_USER'),
 (19,11,'ROLE_USER'),
 (20,12,'ROLE_ADMIN'),
 (21,13,'ROLE_ADMIN'),
 (22,14,'ROLE_ADMIN'),
 (23,15,'ROLE_ADMIN'),
 (24,16,'ROLE_ADMIN'),
 (25,17,'ROLE_ADMIN'),
 (26,18,'ROLE_ADMIN'),
 (27,19,'ROLE_ADMIN');
UNLOCK TABLES;
/*!40000 ALTER TABLE `url2_role` ENABLE KEYS */;


--
-- Definition of table `watererp`.`workflow`
--

DROP TABLE IF EXISTS `watererp`.`workflow`;
CREATE TABLE  `watererp`.`workflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stage_id` int(11) DEFAULT NULL,
  `workflow_master_id` bigint(20) DEFAULT NULL,
  `relative_from_role_id` bigint(20) DEFAULT NULL,
  `absolute_from_role_id` bigint(20) DEFAULT NULL,
  `relationship_type_id` bigint(20) DEFAULT NULL,
  `relative_to_role_id` bigint(20) DEFAULT NULL,
  `absolute_to_role_id` bigint(20) DEFAULT NULL,
  `escalation_relationship_type_id` bigint(20) DEFAULT NULL,
  `relative_escalation_to_id` bigint(20) DEFAULT NULL,
  `absolute_escalation_to_id` bigint(20) DEFAULT NULL,
  `workflow_stage_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_workflow_workflowmaster_id` (`workflow_master_id`),
  KEY `fk_workflow_relativefromrole_id` (`relative_from_role_id`),
  KEY `fk_workflow_absolutefromrole_id` (`absolute_from_role_id`),
  KEY `fk_workflow_relationshiptype_id` (`relationship_type_id`),
  KEY `fk_workflow_relativetorole_id` (`relative_to_role_id`),
  KEY `fk_workflow_absolutetorole_id` (`absolute_to_role_id`),
  KEY `fk_workflow_escalationrelationshiptype_id` (`escalation_relationship_type_id`),
  KEY `fk_workflow_relativeescalationto_id` (`relative_escalation_to_id`),
  KEY `fk_workflow_absoluteescalationto_id` (`absolute_escalation_to_id`),
  KEY `fk_workflow_workflowstagemaster_id` (`workflow_stage_master_id`),
  CONSTRAINT `fk_workflow_absoluteescalationto_id` FOREIGN KEY (`absolute_escalation_to_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_workflow_absolutefromrole_id` FOREIGN KEY (`absolute_from_role_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_workflow_absolutetorole_id` FOREIGN KEY (`absolute_to_role_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_workflow_escalationrelationshiptype_id` FOREIGN KEY (`escalation_relationship_type_id`) REFERENCES `workflow_relationships` (`id`),
  CONSTRAINT `fk_workflow_relationshiptype_id` FOREIGN KEY (`relationship_type_id`) REFERENCES `workflow_relationships` (`id`),
  CONSTRAINT `fk_workflow_relativeescalationto_id` FOREIGN KEY (`relative_escalation_to_id`) REFERENCES `workflow_relations` (`id`),
  CONSTRAINT `fk_workflow_relativefromrole_id` FOREIGN KEY (`relative_from_role_id`) REFERENCES `workflow_relations` (`id`),
  CONSTRAINT `fk_workflow_relativetorole_id` FOREIGN KEY (`relative_to_role_id`) REFERENCES `workflow_relations` (`id`),
  CONSTRAINT `fk_workflow_workflowmaster_id` FOREIGN KEY (`workflow_master_id`) REFERENCES `workflow_master` (`id`),
  CONSTRAINT `fk_workflow_workflowstagemaster_id` FOREIGN KEY (`workflow_stage_master_id`) REFERENCES `workflow_stage_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow`
--

/*!40000 ALTER TABLE `workflow` DISABLE KEYS */;
LOCK TABLES `workflow` WRITE;
INSERT INTO `watererp`.`workflow` VALUES  (1,1,1,NULL,NULL,2,NULL,10,NULL,NULL,24,4),
 (2,2,1,NULL,10,2,NULL,24,NULL,NULL,NULL,5),
 (3,3,1,NULL,24,2,NULL,15,NULL,NULL,NULL,2),
 (4,4,1,NULL,15,2,NULL,10,NULL,NULL,NULL,2),
 (5,5,1,NULL,10,2,NULL,22,NULL,NULL,NULL,NULL),
 (6,6,1,NULL,22,2,NULL,9,NULL,NULL,NULL,NULL),
 (7,7,1,NULL,9,2,NULL,15,NULL,NULL,NULL,NULL),
 (8,8,1,NULL,15,2,NULL,16,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `workflow` ENABLE KEYS */;


--
-- Definition of table `watererp`.`workflow_master`
--

DROP TABLE IF EXISTS `watererp`.`workflow_master`;
CREATE TABLE  `watererp`.`workflow_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `workflow_name` varchar(255) NOT NULL,
  `to_workflow` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_workflowmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_master`
--

/*!40000 ALTER TABLE `workflow_master` DISABLE KEYS */;
LOCK TABLES `workflow_master` WRITE;
INSERT INTO `watererp`.`workflow_master` VALUES  (1,'REQUISITION',0,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (2,'LEAVE',0,'2016-03-26 00:00:00','2016-03-26 00:00:00',2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `workflow_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`workflow_relations`
--

DROP TABLE IF EXISTS `watererp`.`workflow_relations`;
CREATE TABLE  `watererp`.`workflow_relations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_workflowrelations_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowrelations_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_relations`
--

/*!40000 ALTER TABLE `workflow_relations` DISABLE KEYS */;
LOCK TABLES `workflow_relations` WRITE;
INSERT INTO `watererp`.`workflow_relations` VALUES  (1,'DIRECTOR',2),
 (3,'PROGRAMME DIRECTOR',2),
 (5,'ASSOCIATE DIRECTOR',2),
 (7,'PROJECT DIRECTOR',2),
 (9,'TECHNICAL DIRECTORATE',2),
 (13,'DIVISION HEAD',2),
 (20,'BOSS',2),
 (21,'BOSSES BOSS',2),
 (22,'ADMIN',2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `workflow_relations` ENABLE KEYS */;


--
-- Definition of table `watererp`.`workflow_relationships`
--

DROP TABLE IF EXISTS `watererp`.`workflow_relationships`;
CREATE TABLE  `watererp`.`workflow_relationships` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_workflowrelationships_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowrelationships_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_relationships`
--

/*!40000 ALTER TABLE `workflow_relationships` DISABLE KEYS */;
LOCK TABLES `workflow_relationships` WRITE;
INSERT INTO `watererp`.`workflow_relationships` VALUES  (1,'Relative',2),
 (2,'Absolute',3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `workflow_relationships` ENABLE KEYS */;


--
-- Definition of table `watererp`.`workflow_stage_master`
--

DROP TABLE IF EXISTS `watererp`.`workflow_stage_master`;
CREATE TABLE  `watererp`.`workflow_stage_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_workflowstagemaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowstagemaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_stage_master`
--

/*!40000 ALTER TABLE `workflow_stage_master` DISABLE KEYS */;
LOCK TABLES `workflow_stage_master` WRITE;
INSERT INTO `watererp`.`workflow_stage_master` VALUES  (1,'Recommended','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (2,'Approved','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (3,'Sanctioned','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (4,'Waiting','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (5,'Forwarded','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (6,'Completed','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `workflow_stage_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`workflow_txn_details`
--

DROP TABLE IF EXISTS `watererp`.`workflow_txn_details`;
CREATE TABLE  `watererp`.`workflow_txn_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) DEFAULT NULL,
  `reference_number` varchar(255) DEFAULT NULL,
  `row_number` int(11) DEFAULT NULL,
  `column_name` varchar(255) DEFAULT NULL,
  `previous_value` varchar(255) DEFAULT NULL,
  `new_value` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `request_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_workflowtxndetails_requestmaster_id` (`request_master_id`),
  CONSTRAINT `fk_workflowtxndetails_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_txn_details`
--

/*!40000 ALTER TABLE `workflow_txn_details` DISABLE KEYS */;
LOCK TABLES `workflow_txn_details` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `workflow_txn_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`workflow_type_master`
--

DROP TABLE IF EXISTS `watererp`.`workflow_type_master`;
CREATE TABLE  `watererp`.`workflow_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_workflowtypemaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowtypemaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_type_master`
--

/*!40000 ALTER TABLE `workflow_type_master` DISABLE KEYS */;
LOCK TABLES `workflow_type_master` WRITE;
INSERT INTO `watererp`.`workflow_type_master` VALUES  (1,'GENERIC','2016-03-26 00:00:00','2016-03-26 00:00:00',NULL,2),
 (2,'ROLE','2016-03-26 00:00:00','2016-03-26 00:00:00',NULL,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `workflow_type_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`zone_master`
--

DROP TABLE IF EXISTS `watererp`.`zone_master`;
CREATE TABLE  `watererp`.`zone_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zone_name` varchar(255) DEFAULT NULL,
  `zone_code` varchar(255) DEFAULT NULL,
  `division_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_zonemaster_divisionmaster_id` (`division_master_id`),
  CONSTRAINT `fk_zonemaster_divisionmaster_id` FOREIGN KEY (`division_master_id`) REFERENCES `division_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`zone_master`
--

/*!40000 ALTER TABLE `zone_master` DISABLE KEYS */;
LOCK TABLES `zone_master` WRITE;
INSERT INTO `watererp`.`zone_master` VALUES  (1,'Zone1','ZoneCode1',1),
 (2,'Zone2','ZoneCode2',1),
 (3,'Zone3','ZoneCode3',2),
 (4,'Zone4','ZoneCode4',2),
 (5,'Zone5','ZoneCode5',3),
 (6,'Zone6','ZoneCode6',3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `zone_master` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
