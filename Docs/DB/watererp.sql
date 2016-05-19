-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.30-0ubuntu0.14.04.1


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
-- Definition of table `watererp`.`access_list`
--

DROP TABLE IF EXISTS `watererp`.`access_list`;
CREATE TABLE  `watererp`.`access_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`access_list`
--

/*!40000 ALTER TABLE `access_list` DISABLE KEYS */;
LOCK TABLES `access_list` WRITE;
INSERT INTO `watererp`.`access_list` VALUES  (1,'admin1'),
 (2,'admin2'),
 (3,'admin3'),
 (4,'admin4'),
 (5,'admin5'),
 (6,'admin6'),
 (7,'admin7'),
 (8,'admin8'),
 (9,'admin9'),
 (10,'admin10'),
 (11,'admin11'),
 (12,'admin12'),
 (13,'admin13'),
 (14,'dddd');
UNLOCK TABLES;
/*!40000 ALTER TABLE `access_list` ENABLE KEYS */;


--
-- Definition of table `watererp`.`application_txn`
--

DROP TABLE IF EXISTS `watererp`.`application_txn`;
CREATE TABLE  `watererp`.`application_txn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `organization` bit(1) DEFAULT NULL,
  `organization_name` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `mobile_no` bigint(20) DEFAULT NULL,
  `office_no` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `plot_no` varchar(255) DEFAULT NULL,
  `block_no` varchar(255) DEFAULT NULL,
  `tanesco_meter` varchar(255) DEFAULT NULL,
  `water_connection_use` varchar(255) DEFAULT NULL,
  `b_street` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `dma` varchar(255) DEFAULT NULL,
  `b_plot_no` varchar(255) DEFAULT NULL,
  `registered_mobile` bigint(20) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `property_doc` varchar(255) DEFAULT NULL,
  `can` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `meter_reading` float DEFAULT NULL,
  `requested_date` date DEFAULT NULL,
  `connection_date` date DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `meter_no` varchar(255) DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  `deed_doc` varchar(255) DEFAULT NULL,
  `agreement_doc` varchar(255) DEFAULT NULL,
  `tariff_category_master_id` bigint(20) DEFAULT NULL,
  `meter_details_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `request_at_id` bigint(20) DEFAULT NULL,
  `division_master_id` bigint(20) DEFAULT NULL,
  `street_master_id` bigint(20) DEFAULT NULL,
  `id_proof_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_applicationtxn_tariffcategorymaster_id` (`tariff_category_master_id`),
  KEY `fk_applicationtxn_meterdetails_id` (`meter_details_id`),
  KEY `fk_applicationtxn_user_id` (`user_id`),
  KEY `fk_applicationtxn_requestat_id` (`request_at_id`),
  KEY `fk_applicationtxn_divisionmaster_id` (`division_master_id`),
  KEY `fk_applicationtxn_streetmaster_id` (`street_master_id`),
  KEY `fk_applicationtxn_idproofmaster_id` (`id_proof_master_id`),
  CONSTRAINT `fk_applicationtxn_divisionmaster_id` FOREIGN KEY (`division_master_id`) REFERENCES `division_master` (`id`),
  CONSTRAINT `fk_applicationtxn_idproofmaster_id` FOREIGN KEY (`id_proof_master_id`) REFERENCES `id_proof_master` (`id`),
  CONSTRAINT `fk_applicationtxn_meterdetails_id` FOREIGN KEY (`meter_details_id`) REFERENCES `meter_details` (`id`),
  CONSTRAINT `fk_applicationtxn_requestat_id` FOREIGN KEY (`request_at_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_applicationtxn_streetmaster_id` FOREIGN KEY (`street_master_id`) REFERENCES `street_master` (`id`),
  CONSTRAINT `fk_applicationtxn_tariffcategorymaster_id` FOREIGN KEY (`tariff_category_master_id`) REFERENCES `tariff_category_master` (`id`),
  CONSTRAINT `fk_applicationtxn_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`application_txn`
--

/*!40000 ALTER TABLE `application_txn` DISABLE KEYS */;
LOCK TABLES `application_txn` WRITE;
INSERT INTO `watererp`.`application_txn` VALUES  (35,'Mohib',NULL,'Khan',0x01,'Callippus','Programmer',9390148141,8686356232,'thisismohib@gmail.com','14','462','78','ASD4656','147',NULL,NULL,'Begumpet, Secunderabad',NULL,NULL,'MK1478',NULL,'03040001','/api/download/35_1921de276e6a30c2296384bb11347012_Screenshot from 2016-02-03 12:51:52.png',8,147,'2016-05-18','2016-05-18','CAN Generated','MeterId4',NULL,'Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement',2,4,NULL,21,3,4,3);
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
INSERT INTO `watererp`.`application_type_master` VALUES  (1,'Charity Institute','2016-03-03 05:30:00','2016-03-03 05:30:00','1','abc','abc'),
 (2,'Feasibility Reciept','2016-03-03 05:30:00','2016-03-03 05:30:00','1','abc','abc'),
 (3,'Filling Station','2016-03-03 05:30:00','2016-03-03 05:30:00','1','abc','abc'),
 (4,'General','2016-03-03 05:30:00','2016-03-03 05:30:00','1','abc','abc');
UNLOCK TABLES;
/*!40000 ALTER TABLE `application_type_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`bill_details`
--

DROP TABLE IF EXISTS `watererp`.`bill_details`;
CREATE TABLE  `watererp`.`bill_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `can` varchar(255) DEFAULT NULL,
  `bill_number` varchar(255) DEFAULT NULL,
  `bill_date` date NOT NULL,
  `bill_time` varchar(255) DEFAULT NULL,
  `meter_make` varchar(255) DEFAULT NULL,
  `current_bill_type` varchar(255) DEFAULT NULL,
  `from_month` varchar(255) DEFAULT NULL,
  `to_month` varchar(255) DEFAULT NULL,
  `meter_fix_date` date DEFAULT NULL,
  `initial_reading` float DEFAULT NULL,
  `present_reading` float DEFAULT NULL,
  `units` float DEFAULT NULL,
  `water_cess` float DEFAULT NULL,
  `sewerage_cess` float DEFAULT NULL,
  `service_charge` float DEFAULT NULL,
  `meter_service_charge` float DEFAULT NULL,
  `total_amount` float DEFAULT NULL,
  `net_payable_amount` float DEFAULT NULL,
  `telephone_no` varchar(255) DEFAULT NULL,
  `meter_status` varchar(255) DEFAULT NULL,
  `met_reader_code` varchar(255) DEFAULT NULL,
  `bill_flag` varchar(255) DEFAULT NULL,
  `svr_status` varchar(255) DEFAULT NULL,
  `terminal_id` varchar(255) DEFAULT NULL,
  `meter_reader_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `notice_no` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `longi` varchar(255) DEFAULT NULL,
  `no_meter_amt` float DEFAULT NULL,
  `met_reading_dt` date DEFAULT NULL,
  `is_rounding` bit(1) DEFAULT NULL,
  `insert_dt` timestamp NULL DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `mtr_reader_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_billdetails_mtrreader_id` (`mtr_reader_id`),
  CONSTRAINT `fk_billdetails_mtrreader_id` FOREIGN KEY (`mtr_reader_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `watererp`.`bill_details`
--

/*!40000 ALTER TABLE `bill_details` DISABLE KEYS */;
LOCK TABLES `bill_details` WRITE;
INSERT INTO `watererp`.`bill_details` VALUES  (29,'02020005',NULL,'2016-04-01',NULL,NULL,'M','201603','201604',NULL,1,16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-04-28',0x00,'2016-05-13 09:57:43','COMMITTED',14),
 (30,'02020005',NULL,'2016-05-01',NULL,NULL,'M','201604','201605',NULL,16,24,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-28',0x00,'2016-05-13 10:05:52','COMMITTED',14),
 (31,'02020005',NULL,'2016-06-01',NULL,NULL,'L','201605','201606',NULL,24,24,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-29',0x00,'2016-05-13 10:11:37','COMMITTED',16),
 (32,'02020005',NULL,'2016-07-01',NULL,NULL,'M','201606','201607',NULL,24,37,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-25',0x00,'2016-05-13 10:23:44','COMMITTED',17),
 (33,'02020005',NULL,'2016-09-01',NULL,NULL,'M','201607','201609',NULL,37,50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-09-28',0x00,'2016-05-13 11:05:35','COMMITTED',17),
 (34,'02020005',NULL,'2016-10-01',NULL,NULL,'S','201609','201610',NULL,50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-10-26',0x00,'2016-05-13 11:12:59','INITIATED',14);
UNLOCK TABLES;
/*!40000 ALTER TABLE `bill_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`bill_full_details`
--

DROP TABLE IF EXISTS `watererp`.`bill_full_details`;
CREATE TABLE  `watererp`.`bill_full_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `can` varchar(255) NOT NULL,
  `div_code` varchar(255) NOT NULL,
  `sec_code` varchar(255) DEFAULT NULL,
  `sec_name` varchar(255) DEFAULT NULL,
  `met_reader_code` varchar(255) DEFAULT NULL,
  `conn_date` date NOT NULL,
  `cons_name` varchar(255) NOT NULL,
  `house_no` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `pipe_size` float DEFAULT NULL,
  `board_meter` varchar(255) DEFAULT NULL,
  `sewerage` varchar(255) DEFAULT NULL,
  `meter_no` varchar(255) NOT NULL,
  `prev_bill_type` varchar(255) DEFAULT NULL,
  `prev_bill_month` date DEFAULT NULL,
  `prev_avg_kl` float DEFAULT NULL,
  `met_reading_dt` date NOT NULL,
  `prev_reading` float DEFAULT NULL,
  `met_reading_mo` date DEFAULT NULL,
  `met_avg_kl` float DEFAULT NULL,
  `arrears` float DEFAULT NULL,
  `reversal_amt` float DEFAULT NULL,
  `installment` float DEFAULT NULL,
  `other_charges` float DEFAULT NULL,
  `surcharge` float DEFAULT NULL,
  `hrs_surcharge` varchar(255) DEFAULT NULL,
  `res_units` bigint(20) DEFAULT NULL,
  `met_cost_installment` float DEFAULT NULL,
  `int_on_arrears` float DEFAULT NULL,
  `last_pymt_dt` date DEFAULT NULL,
  `last_pymt_amt` float DEFAULT NULL,
  `bill_number` varchar(255) DEFAULT NULL,
  `bill_date` date NOT NULL,
  `bill_time` varchar(255) DEFAULT NULL,
  `meter_make` varchar(255) DEFAULT NULL,
  `current_bill_type` varchar(255) DEFAULT NULL,
  `from_month` varchar(255) DEFAULT NULL,
  `to_month` varchar(255) DEFAULT NULL,
  `meter_fix_date` date DEFAULT NULL,
  `initial_reading` float DEFAULT NULL,
  `present_reading` float DEFAULT NULL,
  `units` float DEFAULT NULL,
  `water_cess` float DEFAULT NULL,
  `sewerage_cess` float DEFAULT NULL,
  `service_charge` float DEFAULT NULL,
  `meter_service_charge` float DEFAULT NULL,
  `total_amount` float DEFAULT NULL,
  `net_payable_amount` float DEFAULT NULL,
  `telephone_no` varchar(255) DEFAULT NULL,
  `meter_status` varchar(255) DEFAULT NULL,
  `bill_flag` varchar(255) DEFAULT NULL,
  `svr_status` varchar(255) DEFAULT NULL,
  `terminal_id` varchar(255) DEFAULT NULL,
  `meter_reader_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `notice_no` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `longi` varchar(255) DEFAULT NULL,
  `no_meter_amt` float DEFAULT NULL,
  `lock_charges` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `watererp`.`bill_full_details`
--

/*!40000 ALTER TABLE `bill_full_details` DISABLE KEYS */;
LOCK TABLES `bill_full_details` WRITE;
INSERT INTO `watererp`.`bill_full_details` VALUES  (10,'02020005','Rajeshwari Temple Street','23','DMA2 Street2',NULL,'2016-03-21','Punna Reddy Chilukuri','12','jdihjhd','KIGOMA','812','1',0.75,'T','F','MeterId3','M',NULL,0,'2016-04-28',1,'2016-04-01',15,0,0,0,0,120,'0.0',1,0,0,NULL,0,NULL,'2016-05-13','095900',NULL,'M','201603','201604','2016-03-21',1,16,15,12000,0,3620,1010,16750,16750,NULL,'M',NULL,NULL,NULL,NULL,NULL,'8887646577',NULL,NULL,NULL,800,0),
 (11,'02020005','Rajeshwari Temple Street','23','DMA2 Street2',NULL,'2016-03-21','Punna Reddy Chilukuri','12','jdihjhd','KIGOMA','812','1',0.75,'T','F','MeterId3','M','2016-04-01',5,'2016-05-28',16,'2016-05-01',8,50,0,0,0,65.6,'0.0',1,0,0,NULL,0,NULL,'2016-05-13','100649',NULL,'M','201604','201605','2016-03-21',16,24,8,6560,0,1820,510,8955.6,9005.6,NULL,'M',NULL,NULL,NULL,NULL,NULL,'8887646577',NULL,NULL,NULL,820,0),
 (12,'02020005','Rajeshwari Temple Street','23','DMA2 Street2',NULL,'2016-03-21','Punna Reddy Chilukuri','12','jdihjhd','KIGOMA','812','1',0.75,'T','F','MeterId3','M','2016-05-01',7.66667,'2016-06-29',24,'2016-05-01',0,-194.4,0,0,0,62.87,'0.0',1,0,0,NULL,0,NULL,'2016-05-13','101240',NULL,'L','201605','201606','2016-03-21',24,24,7.66667,6286.67,0,1820,510,8679.54,8485.14,NULL,'L',NULL,NULL,NULL,NULL,NULL,'8887646577',NULL,NULL,NULL,820,6286.67),
 (13,'02020005','Rajeshwari Temple Street','23','DMA2 Street2',NULL,'2016-03-21','Punna Reddy Chilukuri','12','jdihjhd','KIGOMA','812','1',0.75,'T','F','MeterId3','L','2016-06-01',10.2222,'2016-07-25',24,'2016-07-01',13,8485.14,0,0,0,43.73,'0.0',1,0,0,NULL,0,NULL,'2016-05-13','102436',NULL,'M','201606','201607','2016-03-21',24,37,13,4373.33,0,1820,510,6747.06,15232.2,NULL,'M',NULL,NULL,NULL,NULL,NULL,'8887646577',NULL,NULL,NULL,820,-6286.67),
 (14,'02020005','Rajeshwari Temple Street','23','DMA2 Street2',NULL,'2016-03-21','Punna Reddy Chilukuri','12','jdihjhd','KIGOMA','812','1',0.75,'T','F','MeterId3','M','2016-07-01',9.55556,'2016-09-28',37,'2016-09-01',6.5,8532.2,0,0,0,107.9,'0.0',1,0,0,NULL,0,NULL,'2016-05-13','110656',NULL,'M','201607','201609','2016-03-21',37,50,13,10790,0,3700,1060,15657.9,24190.1,NULL,'M',NULL,NULL,NULL,NULL,NULL,'8887646577',NULL,NULL,NULL,830,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `bill_full_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`bill_run_details`
--

DROP TABLE IF EXISTS `watererp`.`bill_run_details`;
CREATE TABLE  `watererp`.`bill_run_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `can` varchar(255) DEFAULT NULL,
  `from_dt` timestamp NULL DEFAULT NULL,
  `to_dt` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `bill_full_details_id` bigint(20) DEFAULT NULL,
  `bill_run_master_id` bigint(20) DEFAULT NULL,
  `bill_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_billrundetails_billfulldetails_id` (`bill_full_details_id`),
  KEY `fk_billrundetails_billrunmaster_id` (`bill_run_master_id`),
  KEY `fk_billrundetails_billdetails_id` (`bill_details_id`),
  CONSTRAINT `fk_billrundetails_billdetails_id` FOREIGN KEY (`bill_details_id`) REFERENCES `bill_details` (`id`),
  CONSTRAINT `fk_billrundetails_billfulldetails_id` FOREIGN KEY (`bill_full_details_id`) REFERENCES `bill_full_details` (`id`),
  CONSTRAINT `fk_billrundetails_billrunmaster_id` FOREIGN KEY (`bill_run_master_id`) REFERENCES `bill_run_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `watererp`.`bill_run_details`
--

/*!40000 ALTER TABLE `bill_run_details` DISABLE KEYS */;
LOCK TABLES `bill_run_details` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `bill_run_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`bill_run_master`
--

DROP TABLE IF EXISTS `watererp`.`bill_run_master`;
CREATE TABLE  `watererp`.`bill_run_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `success` int(11) DEFAULT NULL,
  `failed` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`bill_run_master`
--

/*!40000 ALTER TABLE `bill_run_master` DISABLE KEYS */;
LOCK TABLES `bill_run_master` WRITE;
INSERT INTO `watererp`.`bill_run_master` VALUES  (110,'2016-05-13 09:58:48','0',1,0,'COMMITTED'),
 (111,'2016-05-13 10:06:49','0',1,0,'COMMITTED'),
 (112,'2016-05-13 10:12:40','0',1,0,'COMMITTED'),
 (113,'2016-05-13 10:24:36','0',1,0,'COMMITTED'),
 (114,'2016-05-13 11:06:56','0',1,0,'COMMITTED');
UNLOCK TABLES;
/*!40000 ALTER TABLE `bill_run_master` ENABLE KEYS */;


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
-- Definition of table `watererp`.`coll_details`
--

DROP TABLE IF EXISTS `watererp`.`coll_details`;
CREATE TABLE  `watererp`.`coll_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reversal_ref` varchar(255) DEFAULT NULL,
  `receipt_no` varchar(255) DEFAULT NULL,
  `receipt_amt` float DEFAULT NULL,
  `receipt_dt` timestamp NULL DEFAULT NULL,
  `receipt_mode` varchar(255) DEFAULT NULL,
  `instr_no` varchar(255) DEFAULT NULL,
  `instr_dt` date DEFAULT NULL,
  `instr_issuer` varchar(255) DEFAULT NULL,
  `svr_status` varchar(255) DEFAULT NULL,
  `can` varchar(255) DEFAULT NULL,
  `cons_name` varchar(255) DEFAULT NULL,
  `terminal_id` varchar(255) DEFAULT NULL,
  `coll_time` timestamp NULL DEFAULT NULL,
  `txn_status` varchar(255) DEFAULT NULL,
  `meter_reader_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `settlement_id` varchar(255) DEFAULT NULL,
  `ext_settlement_id` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `long_i` varchar(255) DEFAULT NULL,
  `payment_types_id` bigint(20) DEFAULT NULL,
  `instrument_issuer_master_id` bigint(20) DEFAULT NULL,
  `collection_type_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_colldetails_paymenttypes_id` (`payment_types_id`),
  KEY `fk_colldetails_instrumentissuermaster_id` (`instrument_issuer_master_id`),
  KEY `fk_colldetails_collectiontypemaster_id` (`collection_type_master_id`),
  CONSTRAINT `fk_colldetails_collectiontypemaster_id` FOREIGN KEY (`collection_type_master_id`) REFERENCES `collection_type_master` (`id`),
  CONSTRAINT `fk_colldetails_instrumentissuermaster_id` FOREIGN KEY (`instrument_issuer_master_id`) REFERENCES `instrument_issuer_master` (`id`),
  CONSTRAINT `fk_colldetails_paymenttypes_id` FOREIGN KEY (`payment_types_id`) REFERENCES `payment_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`coll_details`
--

/*!40000 ALTER TABLE `coll_details` DISABLE KEYS */;
LOCK TABLES `coll_details` WRITE;
INSERT INTO `watererp`.`coll_details` VALUES  (1,NULL,NULL,11,'2016-04-19 05:30:00',NULL,'11','2016-04-19',NULL,NULL,'617830977 ',' V.BHAGYAMMA ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,2,2),
 (2,NULL,NULL,234,NULL,NULL,NULL,NULL,NULL,NULL,'617803425 ',' KAMISHETTYRAJENDRA PRASAD  AND OTHERS ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1),
 (3,NULL,NULL,40000,'2016-05-11 10:08:58',NULL,NULL,NULL,NULL,NULL,'02020004 ',' Patrick Fredrick Mbago ',NULL,NULL,'C',NULL,NULL,'cash paid',NULL,NULL,NULL,NULL,1,NULL,1),
 (4,NULL,NULL,2000,'2016-05-11 10:51:01',NULL,NULL,NULL,NULL,NULL,'02020004 ',' Patrick Fredrick Mbago ',NULL,NULL,'C',NULL,NULL,'cash paid2',NULL,NULL,NULL,NULL,1,NULL,1),
 (5,NULL,NULL,13000,'2016-05-11 11:02:07',NULL,NULL,NULL,NULL,NULL,'02020004 ',' Patrick Fredrick Mbago ',NULL,NULL,'C',NULL,NULL,'sfsd',NULL,NULL,NULL,NULL,1,NULL,1),
 (6,NULL,NULL,50000,'2016-05-11 11:08:09',NULL,NULL,NULL,NULL,NULL,'02020004 ',' Patrick Fredrick Mbago ',NULL,NULL,'C',NULL,NULL,'paid3',NULL,NULL,NULL,NULL,1,NULL,1),
 (7,NULL,NULL,39000,'2016-05-11 11:26:34',NULL,NULL,NULL,NULL,NULL,'02020004 ',' Patrick Fredrick Mbago ',NULL,NULL,'C',NULL,NULL,'paid4',NULL,NULL,NULL,NULL,1,NULL,1),
 (8,NULL,NULL,2000,'2016-05-11 18:48:49',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'Payment Done',NULL,NULL,NULL,NULL,1,NULL,1),
 (9,NULL,NULL,225000,'2016-05-12 14:44:07',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'paid5',NULL,NULL,NULL,NULL,1,NULL,1),
 (10,NULL,NULL,9000,'2016-05-12 14:47:21',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'paid6',NULL,NULL,NULL,NULL,1,NULL,1),
 (11,NULL,NULL,14000,'2016-05-12 15:36:26',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'paid9',NULL,NULL,NULL,NULL,1,NULL,1),
 (12,NULL,NULL,35000,'2016-05-12 17:22:15',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'paid10',NULL,NULL,NULL,NULL,1,NULL,1),
 (13,NULL,NULL,16700,'2016-05-13 10:04:30',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'paid1',NULL,NULL,NULL,NULL,1,NULL,1),
 (14,NULL,NULL,9200,'2016-05-13 10:09:05',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'paid2',NULL,NULL,NULL,NULL,1,NULL,1),
 (15,NULL,NULL,8500,'2016-05-13 10:20:59',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'paid3',NULL,NULL,NULL,NULL,1,NULL,1),
 (16,NULL,NULL,1000,'2016-05-13 10:28:51',NULL,NULL,NULL,NULL,NULL,'02020002 ',' SaiTeja Kothi ',NULL,NULL,'C',NULL,NULL,'paid2',NULL,NULL,NULL,NULL,1,NULL,1),
 (17,NULL,NULL,6700,'2016-05-13 10:36:39',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'Paid4',NULL,NULL,NULL,NULL,1,NULL,1),
 (18,NULL,NULL,15500,'2016-05-13 11:10:19',NULL,NULL,NULL,NULL,NULL,'02020005 ',' Punna Reddy Chilukuri ',NULL,NULL,'C',NULL,NULL,'Paid5',NULL,NULL,NULL,NULL,1,NULL,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `coll_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`collection_type_master`
--

DROP TABLE IF EXISTS `watererp`.`collection_type_master`;
CREATE TABLE  `watererp`.`collection_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coll_name` varchar(255) DEFAULT NULL,
  `txn_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`collection_type_master`
--

/*!40000 ALTER TABLE `collection_type_master` DISABLE KEYS */;
LOCK TABLES `collection_type_master` WRITE;
INSERT INTO `watererp`.`collection_type_master` VALUES  (1,'BILL PAYMENT','C'),
 (2,'NEW CONNECTION','C'),
 (3,'PENALTY','C'),
 (4,'RECONNECTION','C'),
 (5,'EXPENSE 1','E'),
 (6,'EXPENSE 2','E'),
 (7,'EXPENSE 3','E'),
 (8,'EXPENSE 4','E'),
 (9,'Rent','R'),
 (10,'Scrap Sale','R');
UNLOCK TABLES;
/*!40000 ALTER TABLE `collection_type_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`complaint_type_master`
--

DROP TABLE IF EXISTS `watererp`.`complaint_type_master`;
CREATE TABLE  `watererp`.`complaint_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `complaint_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`complaint_type_master`
--

/*!40000 ALTER TABLE `complaint_type_master` DISABLE KEYS */;
LOCK TABLES `complaint_type_master` WRITE;
INSERT INTO `watererp`.`complaint_type_master` VALUES  (1,'INCORRECT BILL'),
 (2,'WATER LEAKAGE'),
 (3,'SERVICE UNAVAILABILITY');
UNLOCK TABLES;
/*!40000 ALTER TABLE `complaint_type_master` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`configuration_details`
--

/*!40000 ALTER TABLE `configuration_details` DISABLE KEYS */;
LOCK TABLES `configuration_details` WRITE;
INSERT INTO `watererp`.`configuration_details` VALUES  (1,'ADMIN','97',NULL),
 (2,'EWURA','1','EWURA - % of Water Usage Charges'),
 (3,'SEWERAGE','10','Sewerage as % of Water Usage Charges'),
 (4,'SUPERVISION','10','Used in proceedings'),
 (5,'LABOUR CHARGES','20','Used in proceedings'),
 (6,'SITE SURVEY','5','Used in proceedings'),
 (7,'CONNECTION FEE OF A & B','20','Used in proceedings'),
 (8,'APPLICATION FORM FEE','1000','Used in proceedings'),
 (9,'ONLINE_PAYMENT_SERVER_URL','http://crystal.tekmindz.com/maxcompp/payapi/v1/unifiedorder','This server will be invoked for making online payments'),
 (10,'ONLINE_PAYMENT_SERVICE_CODE','TESTS001','WaterERP Service Code for Online Payment'),
 (11,'ONLINE_PAYMENT_MERCHANT_CODE','Test001','WaterERP Merchant Code for Online Payment'),
 (12,'BOARD_METER','T','true'),
 (13,'CITY','KIGOMA',''),
 (14,'PIN','812',''),
 (15,'SEWERAGE_CONN','F','false'),
 (16,'MIN_AVG_KL','1','Min. Avg KL in case customer\'s Avg KL is not available');
UNLOCK TABLES;
/*!40000 ALTER TABLE `configuration_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`connection_terminate`
--

DROP TABLE IF EXISTS `watererp`.`connection_terminate`;
CREATE TABLE  `watererp`.`connection_terminate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `can` varchar(255) DEFAULT NULL,
  `request_date` date DEFAULT NULL,
  `meter_recovered` bit(1) DEFAULT NULL,
  `last_meter_reading` float DEFAULT NULL,
  `meter_recovered_date` date DEFAULT NULL,
  `meter_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_connectionterminate_meterdetails_id` (`meter_details_id`),
  CONSTRAINT `fk_connectionterminate_meterdetails_id` FOREIGN KEY (`meter_details_id`) REFERENCES `meter_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`connection_terminate`
--

/*!40000 ALTER TABLE `connection_terminate` DISABLE KEYS */;
LOCK TABLES `connection_terminate` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `connection_terminate` ENABLE KEYS */;


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
-- Definition of table `watererp`.`current_users`
--

DROP TABLE IF EXISTS `watererp`.`current_users`;
CREATE TABLE  `watererp`.`current_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `terminal_id` varchar(255) DEFAULT NULL,
  `meter_reader_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `request_type` varchar(255) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`current_users`
--

/*!40000 ALTER TABLE `current_users` DISABLE KEYS */;
LOCK TABLES `current_users` WRITE;
INSERT INTO `watererp`.`current_users` VALUES  (1,'TerminalId1','MeterReaderId1','UserId1','RequestType1','2016-03-18 05:30:00','Ip1'),
 (2,'sss','sss','sss','ssss','2016-04-12 05:30:00','ssss');
UNLOCK TABLES;
/*!40000 ALTER TABLE `current_users` ENABLE KEYS */;


--
-- Definition of table `watererp`.`cust_details`
--

DROP TABLE IF EXISTS `watererp`.`cust_details`;
CREATE TABLE  `watererp`.`cust_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `can` varchar(255) NOT NULL,
  `div_code` varchar(255) DEFAULT NULL,
  `sec_code` varchar(255) DEFAULT NULL,
  `sec_name` varchar(255) DEFAULT NULL,
  `met_reader_code` varchar(255) DEFAULT NULL,
  `conn_date` date DEFAULT NULL,
  `cons_name` varchar(255) NOT NULL,
  `house_no` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  `category_unused` varchar(255) DEFAULT NULL,
  `pipe_size` float DEFAULT NULL,
  `board_meter` varchar(255) DEFAULT NULL,
  `sewerage` varchar(255) DEFAULT NULL,
  `meter_no` varchar(255) DEFAULT NULL,
  `prev_bill_type` varchar(255) DEFAULT NULL,
  `prev_bill_month` date DEFAULT NULL,
  `prev_avg_kl` float DEFAULT NULL,
  `met_reading_dt` date DEFAULT NULL,
  `prev_reading` float DEFAULT NULL,
  `met_reading_mo` date DEFAULT NULL,
  `met_avg_kl` float DEFAULT NULL,
  `arrears` float DEFAULT NULL,
  `reversal_amt` float DEFAULT NULL,
  `installment` float DEFAULT NULL,
  `other_charges` float DEFAULT NULL,
  `surcharge` float DEFAULT NULL,
  `hrs_surcharge` varchar(255) DEFAULT NULL,
  `res_units` bigint(20) DEFAULT NULL,
  `met_cost_installment` float DEFAULT NULL,
  `int_on_arrears` float DEFAULT NULL,
  `last_pymt_dt` date DEFAULT NULL,
  `last_pymt_amt` float DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `cc_flag` varchar(255) DEFAULT NULL,
  `cp_flag` varchar(255) DEFAULT NULL,
  `notice_flag` varchar(255) DEFAULT NULL,
  `dr_flag` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `longi` varchar(255) DEFAULT NULL,
  `meter_fix_date` date DEFAULT NULL,
  `lock_charges` float DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `tariff_category_master_id` bigint(20) DEFAULT NULL,
  `pipe_size_master_id` bigint(20) DEFAULT NULL,
  `division_master_id` bigint(20) DEFAULT NULL,
  `street_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_custdetails_tariffcategorymaster_id` (`tariff_category_master_id`),
  KEY `fk_custdetails_pipesizemaster_id` (`pipe_size_master_id`),
  KEY `fk_custdetails_divisionmaster_id` (`division_master_id`),
  KEY `fk_custdetails_streetmaster_id` (`street_master_id`),
  CONSTRAINT `fk_custdetails_divisionmaster_id` FOREIGN KEY (`division_master_id`) REFERENCES `division_master` (`id`),
  CONSTRAINT `fk_custdetails_pipesizemaster_id` FOREIGN KEY (`pipe_size_master_id`) REFERENCES `pipe_size_master` (`id`),
  CONSTRAINT `fk_custdetails_streetmaster_id` FOREIGN KEY (`street_master_id`) REFERENCES `street_master` (`id`),
  CONSTRAINT `fk_custdetails_tariffcategorymaster_id` FOREIGN KEY (`tariff_category_master_id`) REFERENCES `tariff_category_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `watererp`.`cust_details`
--

/*!40000 ALTER TABLE `cust_details` DISABLE KEYS */;
LOCK TABLES `cust_details` WRITE;
INSERT INTO `watererp`.`cust_details` VALUES  (1,'03040001','14','78','DMA3 Street4',NULL,'2016-05-18','Mohib Khan','462','Begumpet, Secunderabad','KIGOMA','812',NULL,0.75,'T','F','MeterId4',NULL,NULL,0,'2016-05-18',147,'2016-05-18',0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9390148141','0','0','0','0','0','0','2016-05-18',NULL,'MK1478','thisismohib@gmail.com','ACTIVE',2,2,3,4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `cust_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`cust_meter_mapping`
--

DROP TABLE IF EXISTS `watererp`.`cust_meter_mapping`;
CREATE TABLE  `watererp`.`cust_meter_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_date` date NOT NULL,
  `to_date` date DEFAULT NULL,
  `cust_details_id` bigint(20) DEFAULT NULL,
  `meter_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_custmetermapping_custdetails_id` (`cust_details_id`),
  KEY `fk_custmetermapping_meterdetails_id` (`meter_details_id`),
  CONSTRAINT `fk_custmetermapping_custdetails_id` FOREIGN KEY (`cust_details_id`) REFERENCES `cust_details` (`id`),
  CONSTRAINT `fk_custmetermapping_meterdetails_id` FOREIGN KEY (`meter_details_id`) REFERENCES `meter_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `watererp`.`cust_meter_mapping`
--

/*!40000 ALTER TABLE `cust_meter_mapping` DISABLE KEYS */;
LOCK TABLES `cust_meter_mapping` WRITE;
INSERT INTO `watererp`.`cust_meter_mapping` VALUES  (1,'2016-05-18',NULL,1,4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `cust_meter_mapping` ENABLE KEYS */;


--
-- Definition of table `watererp`.`customer`
--

DROP TABLE IF EXISTS `watererp`.`customer`;
CREATE TABLE  `watererp`.`customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meter_reading` float DEFAULT NULL,
  `present_reading` float DEFAULT NULL,
  `organization` bit(1) DEFAULT NULL,
  `organization_name` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `deed_doc` varchar(255) DEFAULT NULL,
  `agreement_doc` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `requested_date` date DEFAULT NULL,
  `can` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_no` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  `change_type` varchar(255) DEFAULT NULL,
  `tariff_category_master_id` bigint(20) DEFAULT NULL,
  `present_category_id` bigint(20) DEFAULT NULL,
  `new_proof_master_id` bigint(20) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  `pipe_size_master_id` bigint(20) DEFAULT NULL,
  `requested_pipe_size_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_tariffcategorymaster_id` (`tariff_category_master_id`),
  KEY `fk_customer_presentcategory_id` (`present_category_id`),
  KEY `fk_customer_newproofmaster_id` (`new_proof_master_id`),
  KEY `fk_customer_statusmaster_id` (`status_master_id`),
  KEY `fk_customer_pipesizemaster_id` (`pipe_size_master_id`),
  KEY `fk_customer_requestedpipesizemaster_id` (`requested_pipe_size_master_id`),
  CONSTRAINT `fk_customer_newproofmaster_id` FOREIGN KEY (`new_proof_master_id`) REFERENCES `id_proof_master` (`id`),
  CONSTRAINT `fk_customer_pipesizemaster_id` FOREIGN KEY (`pipe_size_master_id`) REFERENCES `pipe_size_master` (`id`),
  CONSTRAINT `fk_customer_presentcategory_id` FOREIGN KEY (`present_category_id`) REFERENCES `tariff_category_master` (`id`),
  CONSTRAINT `fk_customer_requestedpipesizemaster_id` FOREIGN KEY (`requested_pipe_size_master_id`) REFERENCES `pipe_size_master` (`id`),
  CONSTRAINT `fk_customer_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_customer_tariffcategorymaster_id` FOREIGN KEY (`tariff_category_master_id`) REFERENCES `tariff_category_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
LOCK TABLES `customer` WRITE;
INSERT INTO `watererp`.`customer` VALUES  (6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-18','03040001','Yasir','Ehtesham','Khan',8686356232,'thisisyasir@gmail.com','7845','',0,NULL,'CHANGENAME',NULL,NULL,3,NULL,NULL,NULL),
 (7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-09','03040001','yasir','ehtesham','khan',8686356232,'thisisyasir','369852','/api/download/7_f6a7a169ee9dfa152860469d61761e7d_receipt.png',0,NULL,'CHANGENAME',NULL,NULL,4,NULL,NULL,NULL),
 (9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Approved for Name Change','2016-05-10','03040001','yasir','ehesham','khan',8686356232,'thisisyasir@gmail.com','789654','',1,NULL,'CHANGENAME',NULL,NULL,2,NULL,NULL,NULL),
 (10,147,1478,NULL,'Callippus','Developer','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','Category Change Case','2016-05-18','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,3,NULL,NULL,NULL,NULL),
 (12,147,68,NULL,'s;ljflj','dslgl;','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','skjjfkshfkj','2016-05-18','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,5,NULL,NULL,NULL,NULL),
 (13,147,325,NULL,'dfklk','lkjlkjsd','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','SEGSGSEG','2016-05-18','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,3,NULL,NULL,NULL,NULL),
 (14,147,8798,NULL,'89789','89987','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','SHFKHF','2016-05-18','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,3,NULL,NULL,NULL,NULL),
 (15,147,34236,NULL,'2362','2336263','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','236236','2016-05-18','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,3,NULL,NULL,NULL,NULL),
 (16,147,7897,NULL,'klhdh','lkhlkh','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','sdsdsd','2016-05-18','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,4,NULL,NULL,NULL,NULL),
 (17,147,4626,NULL,'asfsa','sgsg','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','asfasf','2016-05-18','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,4,NULL,NULL,NULL,NULL),
 (18,147,346,NULL,'tw','sdgs','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','asfas','2016-05-18','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,3,NULL,NULL,NULL,NULL),
 (19,147,2523,NULL,'sdgsg','sgsgd','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','sdgsg','2016-05-17','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,4,NULL,NULL,NULL,NULL),
 (20,147,3626,NULL,'sdsg','sdgsdg','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','','2016-05-16','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',0,NULL,'CONNECTIONCATEGORY',2,5,NULL,NULL,NULL,NULL),
 (21,147,566546,NULL,'56456','654654','Tittle Deed/Offer letter','Rented Property-lease-Rent Agreement','','2016-05-16','03040001',NULL,NULL,NULL,NULL,NULL,NULL,'',2,NULL,'CONNECTIONCATEGORY',2,3,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `watererp`.`customer_complaints`
--

DROP TABLE IF EXISTS `watererp`.`customer_complaints`;
CREATE TABLE  `watererp`.`customer_complaints` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remarks` varchar(255) DEFAULT NULL,
  `relevant_doc` varchar(255) DEFAULT NULL,
  `complaint_by` varchar(255) DEFAULT NULL,
  `complaint_date` date DEFAULT NULL,
  `can` varchar(255) DEFAULT NULL,
  `adjustment_amt` float DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `complaint_type_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customercomplaints_complainttypemaster_id` (`complaint_type_master_id`),
  CONSTRAINT `fk_customercomplaints_complainttypemaster_id` FOREIGN KEY (`complaint_type_master_id`) REFERENCES `complaint_type_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`customer_complaints`
--

/*!40000 ALTER TABLE `customer_complaints` DISABLE KEYS */;
LOCK TABLES `customer_complaints` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `customer_complaints` ENABLE KEYS */;


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
 ('20160229070517','jhipster','classpath:config/liquibase/changelog/20160229070517_added_entity_FileNumber.xml','2016-02-29 14:06:34',8,'EXECUTED','7:d3e42819856cbf28b67058bba741014f','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229070707','jhipster','classpath:config/liquibase/changelog/20160229070707_added_entity_TransactionTypeMaster.xml','2016-02-29 14:06:34',9,'EXECUTED','7:afd37db16070bbc1db18016656893a32','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229070800','jhipster','classpath:config/liquibase/changelog/20160229070800_added_entity_CashBookMaster.xml','2016-02-29 14:06:34',10,'EXECUTED','7:d0decf027ebc34ee609eba6744ef51c2','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229070919','jhipster','classpath:config/liquibase/changelog/20160229070919_added_entity_PaymentTypes.xml','2016-02-29 14:06:34',11,'EXECUTED','7:ef4e1a7913b69b2869492e1ec5644d0d','createTable','',NULL,'3.4.2',NULL,NULL),
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
 ('20160309103701','jhipster','classpath:config/liquibase/changelog/20160309103701_added_entity_MenuItem.xml','2016-03-09 16:14:41',49,'EXECUTED','7:bd9888add9c5af34bcdca93e50add4d1','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160309103829','jhipster','classpath:config/liquibase/changelog/20160309103829_added_entity_Url.xml','2016-03-09 16:14:41',50,'EXECUTED','7:1889bdedffb7df6fe11f9bc33d0efa35','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160309104001','jhipster','classpath:config/liquibase/changelog/20160309104001_added_entity_MenuItem2Url.xml','2016-03-09 16:14:43',51,'EXECUTED','7:902843a076e3bb258db90badb9e1dc9a','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160309105304','jhipster','classpath:config/liquibase/changelog/20160309105304_added_entity_Url2Role.xml','2016-03-09 16:34:47',53,'EXECUTED','7:10331a2dfcdaf6902d65c17510f31bc4','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160311094234','jhipster','classpath:config/liquibase/changelog/20160311094234_added_entity_SchemeMaster.xml','2016-03-11 15:21:26',54,'EXECUTED','7:af80987658cadbe9d18ef393fd5559c7','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094550','jhipster','classpath:config/liquibase/changelog/20160311094550_added_entity_MakeOfPipe.xml','2016-03-11 15:21:26',56,'EXECUTED','7:0aae809bc0a38e5aa294a887f735acb8','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094820','jhipster','classpath:config/liquibase/changelog/20160311094820_added_entity_MainWaterSize.xml','2016-03-11 15:21:26',57,'EXECUTED','7:0e853a5e0c7721503d0203d88333bd91','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094904','jhipster','classpath:config/liquibase/changelog/20160311094904_added_entity_MainSewerageSize.xml','2016-03-11 15:21:27',58,'EXECUTED','7:03b9b58c058199566e8d55fd5635b97d','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311095014','jhipster','classpath:config/liquibase/changelog/20160311095014_added_entity_DocketCode.xml','2016-03-11 15:21:27',59,'EXECUTED','7:9b3b9b3180ffc95d73eadf3d1294c5e0','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160317071301','jhipster','classpath:config/liquibase/changelog/20160317071301_added_entity_ItemDetails.xml','2016-03-17 13:06:23',62,'EXECUTED','7:8d0c73c28056a5186d39bbed2ac7f38f','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160324101153','jhipster','classpath:config/liquibase/changelog/20160324101153_added_entity_DivisionMaster.xml','2016-03-24 15:50:09',65,'EXECUTED','7:49f67b19188786accf52558a610c39c8','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160324101330','jhipster','classpath:config/liquibase/changelog/20160324101330_added_entity_ZoneMaster.xml','2016-03-24 15:50:10',66,'EXECUTED','7:7855073214c1c3e194f1b4125d049f25','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160328051857','jhipster','classpath:config/liquibase/changelog/20160328051857_added_entity_FileUploadMaster.xml','2016-03-28 18:56:06',72,'EXECUTED','7:9fd8cdb67e4d8cc76478621ecad2012b','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160328131639','jhipster','classpath:config/liquibase/changelog/20160328131639_added_entity_FileUploadMaster.xml','2016-03-28 18:57:23',73,'EXECUTED','7:03be8b2eae9799aec199f76ed49d55a1','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160330052554','jhipster','classpath:config/liquibase/changelog/20160330052554_added_entity_ItemCategoryMaster.xml','2016-03-30 11:28:44',74,'EXECUTED','7:360b2d16421c3564bb94f0775648580d','createTable, dropDefaultValue (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160330052808','jhipster','classpath:config/liquibase/changelog/20160330052808_added_entity_ItemSubCategoryMaster.xml','2016-03-30 11:28:45',75,'EXECUTED','7:77c40a173637ee134f58cd079c67aead','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160330053134','jhipster','classpath:config/liquibase/changelog/20160330053134_added_entity_ItemCodeMaster.xml','2016-03-30 11:28:47',76,'EXECUTED','7:a4e7f1b1ca5ebdb94b5d29dd9402b337','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330053533','jhipster','classpath:config/liquibase/changelog/20160330053533_added_entity_ItemCompanyMaster.xml','2016-03-30 11:28:47',77,'EXECUTED','7:a250630d5ce589a445e309d152c4d935','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330054006','jhipster','classpath:config/liquibase/changelog/20160330054006_added_entity_ItemSubCodeMaster.xml','2016-03-30 11:28:48',78,'EXECUTED','7:b3fbded42d2b493c86ffbd3649e87ceb','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330054928','jhipster','classpath:config/liquibase/changelog/20160330054928_added_entity_MaterialMaster.xml','2016-03-30 11:28:48',79,'EXECUTED','7:983fd1500db22988ac26254293296036','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330055745','jhipster','classpath:config/liquibase/changelog/20160330055745_added_entity_SibEntry.xml','2016-03-30 11:28:49',80,'EXECUTED','7:da7d47c69cd368ff14809fae56104b33','createTable, dropDefaultValue (x9)','',NULL,'3.4.2',NULL,NULL),
 ('20160330093457','jhipster','classpath:config/liquibase/changelog/20160330093457_added_entity_PercentageMaster.xml','2016-03-30 19:02:55',85,'EXECUTED','7:939ffd4e62fb70f2288fd5eb2055b778','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffCategoryMaster.xml','2016-04-01 15:34:53',87,'EXECUTED','7:c01f3faca05eff0386e65260cc81849e','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffTypeMaster.xml','2016-04-01 15:34:53',88,'EXECUTED','7:2fdf8de5ece58a24c9842690d2599317','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffMaster.xml','2016-04-01 15:34:54',89,'EXECUTED','7:de67571b345cd24609fcbc1d6d8e12ab','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160401094431','jhipster','classpath:config/liquibase/changelog/20160401094431_added_entity_TariffCharges.xml','2016-04-01 15:34:56',90,'EXECUTED','7:84a4098f6e6489da14e4b14c2d948a2c','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160406042024','jhipster','classpath:config/liquibase/changelog/20160406042024_added_entity_InstrumentIssuerMaster.xml','2016-04-06 10:33:04',98,'EXECUTED','7:e6ddd2edc49130805cd0d945da5c8bff','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160409042538','jhipster','classpath:config/liquibase/changelog/20160409042538_added_entity_Uom.xml','2016-04-12 13:09:17',107,'EXECUTED','7:16829833f2d3e87199a121db96980eaf','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160412142749','jhipster','classpath:config/liquibase/changelog/20160412142749_added_entity_BillRunMaster.xml','2016-04-13 11:50:30',109,'EXECUTED','7:b34ea10d67f40bb5c95e7ad4be486e01','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160229111821','jhipster','classpath:config/liquibase/changelog/20160229111821_added_entity_RequestWorkflowHistory.xml','2016-04-13 12:52:57',112,'EXECUTED','7:df037b52eebabe97bca6601174750057','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x7)','',NULL,'3.4.2',NULL,NULL),
 ('20160329064157','jhipster','classpath:config/liquibase/changelog/20160329064157_added_entity_ComplaintTypeMaster.xml','2016-04-13 13:19:32',120,'EXECUTED','7:925d2a76e3b71e5bc7daf39c4f8ee2b5','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160413093028','jhipster','classpath:config/liquibase/changelog/20160413093028_added_entity_MeterStatus.xml','2016-04-18 12:57:45',121,'EXECUTED','7:4d7c1ce6ba1e28d5591ed0f73c0058a8','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160405010101','jhipster','classpath:config/liquibase/changelog/20160405010101_added_entity_MeterDetails.xml','2016-04-18 12:57:45',122,'EXECUTED','7:5a4422703a44b3283529717af68a7e91','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160412112557','jhipster','classpath:config/liquibase/changelog/20160412112557_added_entity_CollectionTypeMaster.xml','2016-04-18 13:01:24',128,'EXECUTED','7:1128b00324be2ae65e6b38b670f60e36','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160315053144','jhipster','classpath:config/liquibase/changelog/20160315053144_added_entity_CollDetails.xml','2016-04-18 13:01:26',129,'EXECUTED','7:86e8348b225c581edd72d5d4787583ad','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160415064155','jhipster','classpath:config/liquibase/changelog/20160415064155_added_entity_ExpenseDetails.xml','2016-04-18 13:01:28',132,'EXECUTED','7:5fc5d42163edf25d27484172b6a31d64','createTable, dropDefaultValue, addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160309103544','jhipster','classpath:config/liquibase/changelog/20160309103544_added_entity_Module.xml','2016-04-21 15:40:56',136,'EXECUTED','7:2bd44e3f9e9ef3e2df74cd6440f20f4e','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160309104200','jhipster','classpath:config/liquibase/changelog/20160309104200_added_entity_Module2MenuItem.xml','2016-04-21 15:40:56',137,'EXECUTED','7:9824330082db5729d7b5d6bf940348df','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160324101502','jhipster','classpath:config/liquibase/changelog/20160324101502_added_entity_StreetMaster.xml','2016-04-22 16:32:17',138,'EXECUTED','7:06a439d83aa505f96f7a5912600760b3','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160426103301','jhipster','classpath:config/liquibase/changelog/20160426103301_added_entity_IdProofMaster.xml','2016-04-27 09:12:30',141,'EXECUTED','7:94a5cf42044a1b16758e77fdaf7ee4a5','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160427084244','jhipster','classpath:config/liquibase/changelog/20160427084244_added_entity_MerchantMaster.xml','2016-04-27 09:15:33',146,'EXECUTED','7:6c558187f8cb1283d0f35b86b24e3d8f','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229075018','jhipster','classpath:config/liquibase/changelog/20160229075018_added_entity_ApplicationTxn.xml','2016-04-27 09:19:48',148,'EXECUTED','7:bc3817fceaf1e83b90459c81f129b3ab','createTable, addForeignKeyConstraint (x7)','',NULL,'3.4.2',NULL,NULL),
 ('20160324105452','jhipster','classpath:config/liquibase/changelog/20160324105452_added_entity_FeasibilityStudy.xml','2016-04-27 09:19:50',149,'EXECUTED','7:aa06f800520a2b8bfef4654df0572d3d','createTable, dropDefaultValue (x6), addForeignKeyConstraint (x9)','',NULL,'3.4.2',NULL,NULL),
 ('20160401064028','jhipster','classpath:config/liquibase/changelog/20160401064028_added_entity_Receipt.xml','2016-04-27 09:19:51',151,'EXECUTED','7:74e096fa5e9ab1d7a353aefc6702527a','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160229065514','jhipster','classpath:config/liquibase/changelog/20160229065514_added_entity_PipeSizeMaster.xml','2016-05-02 14:07:37',152,'EXECUTED','7:84481a490a68b0f89ed9d2f5fcd3293b','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229065700','jhipster','classpath:config/liquibase/changelog/20160229065700_added_entity_CategoryPipeSizeMapping.xml','2016-05-02 14:07:38',153,'EXECUTED','7:10b74427e2abf13a1e46b02177b879c5','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330095504','jhipster','classpath:config/liquibase/changelog/20160330095504_added_entity_Proceedings.xml','2016-05-02 14:07:38',154,'EXECUTED','7:b7446d7e9934ac87de93b1c42cf3107a','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330092113','jhipster','classpath:config/liquibase/changelog/20160330092113_added_entity_ItemRequired.xml','2016-05-02 14:07:40',155,'EXECUTED','7:4ea9f0b6d412315e5baff9213f3fbaeb','createTable, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL),
 ('20160329064343','jhipster','classpath:config/liquibase/changelog/20160329064343_added_entity_CustomerComplaints.xml','2016-05-02 14:07:40',156,'EXECUTED','7:a8f621ed8b7a57ffbe0c5187791018c5','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160427084544','jhipster','classpath:config/liquibase/changelog/20160427084544_added_entity_OnlinePaymentOrder.xml','2016-05-02 14:07:41',157,'EXECUTED','7:c89108e03c232e11ba95545a3077646e','createTable, dropDefaultValue, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160427104544','jhipster','classpath:config/liquibase/changelog/20160427104544_added_entity_OnlinePaymentResponse.xml','2016-05-03 17:04:09',158,'EXECUTED','7:446fb20c8df2e6211ae618789b57766b','createTable, dropDefaultValue, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160427105244','jhipster','classpath:config/liquibase/changelog/20160427105244_added_entity_OnlinePaymentCallback.xml','2016-05-03 17:04:09',159,'EXECUTED','7:c8e948a9e513be117924fe3c630468fb','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160503041249','jhipster','classpath:config/liquibase/changelog/20160503041249_added_entity_RevenueTypeMaster.xml','2016-05-04 17:19:51',160,'EXECUTED','7:47e3555eee069585ccb21daa674b3687','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160504065608','jhipster','classpath:config/liquibase/changelog/20160504065608_added_entity_ConnectionTerminate.xml','2016-05-05 10:38:39',163,'EXECUTED','7:77b9eddf440cf38d04fe73c2b25a4aaf','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160314104149','jhipster','classpath:config/liquibase/changelog/20160314104149_added_entity_BillDetails.xml','2016-05-09 11:24:46',164,'EXECUTED','7:b0895b1354bdcdc8e2ba43450c1d4dde','createTable, dropDefaultValue, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229071846','jhipster','classpath:config/liquibase/changelog/20160229071846_added_entity_Customer.xml','2016-05-09 14:49:49',166,'EXECUTED','7:837e9c6ee63fcbb441a44c5a87fc5d05','createTable, addForeignKeyConstraint (x6)','',NULL,'3.4.2',NULL,NULL),
 ('20160229093926','jhipster','classpath:config/liquibase/changelog/20160229093926_added_entity_EmpMaster.xml','2016-05-12 17:53:12',167,'EXECUTED','7:68ed39d402d606773083015df2c45161','createTable, addForeignKeyConstraint (x6)','',NULL,'3.4.2',NULL,NULL),
 ('20160314104149','jhipster','classpath:config/liquibase/changelog/20160314104149_added_entity_BillFullDetails.xml','2016-05-12 18:33:41',170,'EXECUTED','7:0f528b6a210e9e5bfa75883b0a834aaa','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160314132343','jhipster','classpath:config/liquibase/changelog/20160314132343_added_entity_CustDetails.xml','2016-05-17 09:15:18',171,'EXECUTED','7:bf3c4e50b5a33a9f0c25ee7bbb273ab7','createTable, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160405010101','jhipster','classpath:config/liquibase/changelog/20160405010101_added_entity_CustMeterMapping.xml','2016-05-17 09:15:19',172,'EXECUTED','7:daff9712c10bda0f757f367deb57dcc9','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160412143549','jhipster','classpath:config/liquibase/changelog/20160412143549_added_entity_BillRunDetails.xml','2016-05-17 09:15:21',173,'EXECUTED','7:208b28854110ec930d0f7348946c1469','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160419095001','jhipster','classpath:config/liquibase/changelog/20160419095001_added_entity_MeterChange.xml','2016-05-17 09:15:22',174,'EXECUTED','7:bc8ec5c09f0aaedab3a493d844a5ef5a','createTable, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160517040011','jhipster','classpath:config/liquibase/changelog/20160517040011_added_entity_Hetero.xml','2016-05-17 09:33:14',175,'EXECUTED','7:f0aab1f3ff08628ff2ff55d6c6e0ba47','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL);
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
INSERT INTO `watererp`.`desig_category_master` VALUES  (1,'DRDS','2016-03-03 05:30:00','2016-03-03 05:30:00','Scientists','123',1,NULL);
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
INSERT INTO `watererp`.`division_master` VALUES  (1,'DMA1','01'),
 (2,'DMA2','02'),
 (3,'DMA3','03'),
 (4,'DMA4','04'),
 (5,'DMA5','05'),
 (6,'DMA6','06'),
 (7,'DMA7','07'),
 (8,'DMA8','08'),
 (9,'DMA9','09'),
 (10,'DMA10','10');
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
  `date_of_birth` date DEFAULT NULL,
  `joining_date` date DEFAULT NULL,
  `marital_status` varchar(255) DEFAULT NULL,
  `employee_type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `office_id_id` bigint(20) DEFAULT NULL,
  `designation_master_id` bigint(20) DEFAULT NULL,
  `directorate_id_id` bigint(20) DEFAULT NULL,
  `status_master_id` bigint(20) DEFAULT NULL,
  `reporting_to_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_empmaster_user_id` (`user_id`),
  KEY `fk_empmaster_officeid_id` (`office_id_id`),
  KEY `fk_empmaster_designationmaster_id` (`designation_master_id`),
  KEY `fk_empmaster_directorateid_id` (`directorate_id_id`),
  KEY `fk_empmaster_statusmaster_id` (`status_master_id`),
  KEY `fk_empmaster_reportingto_id` (`reporting_to_id`),
  CONSTRAINT `fk_empmaster_designationmaster_id` FOREIGN KEY (`designation_master_id`) REFERENCES `designation_master` (`id`),
  CONSTRAINT `fk_empmaster_directorateid_id` FOREIGN KEY (`directorate_id_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_empmaster_officeid_id` FOREIGN KEY (`office_id_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_empmaster_reportingto_id` FOREIGN KEY (`reporting_to_id`) REFERENCES `designation_master` (`id`),
  CONSTRAINT `fk_empmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_empmaster_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `watererp`.`emp_master`
--

/*!40000 ALTER TABLE `emp_master` DISABLE KEYS */;
LOCK TABLES `emp_master` WRITE;
INSERT INTO `watererp`.`emp_master` VALUES  (1,NULL,NULL,NULL,NULL,6,1,NULL,1,2,NULL),
 (2,NULL,NULL,NULL,NULL,7,2,NULL,2,2,NULL),
 (3,NULL,NULL,NULL,NULL,8,3,NULL,3,2,NULL),
 (4,NULL,NULL,NULL,NULL,9,4,NULL,4,2,NULL),
 (5,NULL,NULL,NULL,NULL,10,5,NULL,5,2,NULL),
 (6,NULL,NULL,NULL,NULL,11,6,NULL,6,2,NULL),
 (7,NULL,NULL,NULL,NULL,12,7,NULL,7,2,NULL),
 (8,NULL,NULL,NULL,NULL,13,8,NULL,8,2,NULL),
 (9,NULL,NULL,NULL,NULL,14,9,NULL,9,2,NULL),
 (10,NULL,NULL,NULL,NULL,15,10,NULL,10,2,NULL),
 (11,NULL,NULL,NULL,NULL,16,11,NULL,11,2,NULL),
 (12,NULL,NULL,NULL,NULL,5,25,NULL,25,2,NULL),
 (13,NULL,NULL,NULL,NULL,24,19,NULL,19,2,NULL),
 (14,NULL,NULL,NULL,NULL,17,12,NULL,12,2,NULL),
 (16,NULL,NULL,NULL,NULL,18,13,NULL,13,2,NULL),
 (17,NULL,NULL,NULL,NULL,19,14,NULL,14,2,NULL),
 (18,NULL,NULL,NULL,NULL,20,15,NULL,15,2,NULL),
 (20,NULL,NULL,NULL,NULL,21,16,NULL,16,2,NULL),
 (21,NULL,NULL,NULL,NULL,22,17,NULL,17,2,NULL),
 (22,NULL,NULL,NULL,NULL,23,18,NULL,18,2,NULL),
 (23,NULL,NULL,NULL,NULL,24,19,NULL,19,2,NULL),
 (24,NULL,NULL,NULL,NULL,25,20,NULL,20,2,NULL),
 (25,NULL,NULL,NULL,NULL,26,21,NULL,21,2,NULL),
 (26,NULL,NULL,NULL,NULL,27,22,NULL,22,2,NULL),
 (27,NULL,NULL,NULL,NULL,28,23,NULL,23,2,NULL),
 (28,NULL,NULL,NULL,NULL,29,24,NULL,24,2,NULL),
 (29,NULL,NULL,NULL,NULL,3,26,NULL,26,2,NULL);
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
INSERT INTO `watererp`.`emp_role_mapping` VALUES  (1,'','','2016-03-18 05:30:00','2016-03-18 05:30:00',0,6,NULL,1,2),
 (2,'','','2016-03-18 05:30:00','2016-03-18 05:30:00',0,7,NULL,2,2),
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
 (26,'',NULL,'2016-03-18 05:30:00',NULL,NULL,29,NULL,24,2),
 (27,NULL,NULL,NULL,NULL,NULL,5,NULL,25,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `emp_role_mapping` ENABLE KEYS */;


--
-- Definition of table `watererp`.`expense_details`
--

DROP TABLE IF EXISTS `watererp`.`expense_details`;
CREATE TABLE  `watererp`.`expense_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expense_no` varchar(255) DEFAULT NULL,
  `expense_amt` float DEFAULT NULL,
  `expense_dt` timestamp NULL DEFAULT NULL,
  `instr_no` varchar(255) DEFAULT NULL,
  `instr_dt` date DEFAULT NULL,
  `payment_types_id` bigint(20) DEFAULT NULL,
  `instrument_issuer_master_id` bigint(20) DEFAULT NULL,
  `collection_type_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_expensedetails_paymenttypes_id` (`payment_types_id`),
  KEY `fk_expensedetails_instrumentissuermaster_id` (`instrument_issuer_master_id`),
  KEY `fk_expensedetails_collectiontypemaster_id` (`collection_type_master_id`),
  CONSTRAINT `fk_expensedetails_collectiontypemaster_id` FOREIGN KEY (`collection_type_master_id`) REFERENCES `collection_type_master` (`id`),
  CONSTRAINT `fk_expensedetails_instrumentissuermaster_id` FOREIGN KEY (`instrument_issuer_master_id`) REFERENCES `instrument_issuer_master` (`id`),
  CONSTRAINT `fk_expensedetails_paymenttypes_id` FOREIGN KEY (`payment_types_id`) REFERENCES `payment_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`expense_details`
--

/*!40000 ALTER TABLE `expense_details` DISABLE KEYS */;
LOCK TABLES `expense_details` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `expense_details` ENABLE KEYS */;


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
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `prepared_date` timestamp NULL DEFAULT NULL,
  `zonal_head_approval_date` timestamp NULL DEFAULT NULL,
  `dept_head_inspected_date` timestamp NULL DEFAULT NULL,
  `operation_mangrapprove_date` timestamp NULL DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`feasibility_study`
--

/*!40000 ALTER TABLE `feasibility_study` DISABLE KEYS */;
LOCK TABLES `feasibility_study` WRITE;
INSERT INTO `watererp`.`feasibility_study` VALUES  (17,'2016-05-18 15:21:07','2016-05-18 15:21:07','2016-05-18 00:00:00','2016-05-17 00:00:00','2016-05-16 00:00:00','2016-05-15 00:00:00',0,3,NULL,4,35,11,17,17,19,NULL);
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
-- Definition of table `watererp`.`hetero`
--

DROP TABLE IF EXISTS `watererp`.`hetero`;
CREATE TABLE  `watererp`.`hetero` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `company` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `watererp`.`hetero`
--

/*!40000 ALTER TABLE `hetero` DISABLE KEYS */;
LOCK TABLES `hetero` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `hetero` ENABLE KEYS */;


--
-- Definition of table `watererp`.`id_proof_master`
--

DROP TABLE IF EXISTS `watererp`.`id_proof_master`;
CREATE TABLE  `watererp`.`id_proof_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_proof` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`id_proof_master`
--

/*!40000 ALTER TABLE `id_proof_master` DISABLE KEYS */;
LOCK TABLES `id_proof_master` WRITE;
INSERT INTO `watererp`.`id_proof_master` VALUES  (1,'OTHER ID'),
 (2,'DRIVING LICENSE'),
 (3,'VOTER\'S ID'),
 (4,'PASSPORT');
UNLOCK TABLES;
/*!40000 ALTER TABLE `id_proof_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`instrument_issuer_master`
--

DROP TABLE IF EXISTS `watererp`.`instrument_issuer_master`;
CREATE TABLE  `watererp`.`instrument_issuer_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `instrument_issuer` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`instrument_issuer_master`
--

/*!40000 ALTER TABLE `instrument_issuer_master` DISABLE KEYS */;
LOCK TABLES `instrument_issuer_master` WRITE;
INSERT INTO `watererp`.`instrument_issuer_master` VALUES  (1,'issuer1'),
 (2,'issuer2'),
 (3,'issuer3');
UNLOCK TABLES;
/*!40000 ALTER TABLE `instrument_issuer_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`item_category_master`
--

DROP TABLE IF EXISTS `watererp`.`item_category_master`;
CREATE TABLE  `watererp`.`item_category_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `category_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `item_category_master_id` bigint(20) DEFAULT NULL,
  `item_sub_category_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_itemcodemaster_itemcategorymaster_id` (`item_category_master_id`),
  KEY `fk_itemcodemaster_itemsubcategorymaster_id` (`item_sub_category_master_id`),
  CONSTRAINT `fk_itemcodemaster_itemcategorymaster_id` FOREIGN KEY (`item_category_master_id`) REFERENCES `item_category_master` (`id`),
  CONSTRAINT `fk_itemcodemaster_itemsubcategorymaster_id` FOREIGN KEY (`item_sub_category_master_id`) REFERENCES `item_sub_category_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `company_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `provided` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `rate_per_shs` decimal(10,2) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `material_master_id` bigint(20) DEFAULT NULL,
  `application_txn_id` bigint(20) DEFAULT NULL,
  `feasibility_study_id` bigint(20) DEFAULT NULL,
  `proceedings_id` bigint(20) DEFAULT NULL,
  `uom_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_itemrequired_materialmaster_id` (`material_master_id`),
  KEY `fk_itemrequired_applicationtxn_id` (`application_txn_id`),
  KEY `fk_itemrequired_feasibilitystudy_id` (`feasibility_study_id`),
  KEY `fk_itemrequired_proceedings_id` (`proceedings_id`),
  KEY `fk_itemrequired_uom_id` (`uom_id`),
  CONSTRAINT `fk_itemrequired_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_itemrequired_feasibilitystudy_id` FOREIGN KEY (`feasibility_study_id`) REFERENCES `feasibility_study` (`id`),
  CONSTRAINT `fk_itemrequired_materialmaster_id` FOREIGN KEY (`material_master_id`) REFERENCES `material_master` (`id`),
  CONSTRAINT `fk_itemrequired_proceedings_id` FOREIGN KEY (`proceedings_id`) REFERENCES `proceedings` (`id`),
  CONSTRAINT `fk_itemrequired_uom_id` FOREIGN KEY (`uom_id`) REFERENCES `uom` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_required`
--

/*!40000 ALTER TABLE `item_required` DISABLE KEYS */;
LOCK TABLES `item_required` WRITE;
INSERT INTO `watererp`.`item_required` VALUES  (16,5,5,'1000.00','5000.00',2,35,NULL,9,2),
 (17,1,1,'30000.00','30000.00',3,35,NULL,9,2),
 (18,15,15,'1000.00','15000.00',4,35,NULL,9,1),
 (19,1,1,'12000.00','12000.00',6,35,NULL,9,3),
 (20,1,1,'3500.00','3500.00',7,35,NULL,9,3),
 (21,1,1,'2000.00','2000.00',8,35,NULL,9,1),
 (22,6,6,'1000.00','6000.00',9,35,NULL,9,2),
 (23,3,3,'1000.00','3000.00',10,35,NULL,9,3),
 (24,4,4,'2500.00','10000.00',11,35,NULL,9,3),
 (25,3,3,'1000.00','3000.00',12,35,NULL,9,2),
 (26,1,1,'2000.00','2000.00',13,35,NULL,9,2),
 (27,1,1,'12000.00','12000.00',14,35,NULL,9,3);
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
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_code` varchar(255) DEFAULT NULL,
  `item_category_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_itemsubcategorymaster_itemcategorymaster_id` (`item_category_master_id`),
  CONSTRAINT `fk_itemsubcategorymaster_itemcategorymaster_id` FOREIGN KEY (`item_category_master_id`) REFERENCES `item_category_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `item_ccode_id` bigint(20) DEFAULT NULL,
  `item_category_id` bigint(20) NOT NULL,
  `item_sub_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
INSERT INTO `watererp`.`jhi_authority` VALUES  ('ROLE_ACCOUNANT'),
 ('ROLE_ADMIN'),
 ('ROLE_BILLING'),
 ('ROLE_BILLRUN'),
 ('ROLE_BILLRUN_MANAGER'),
 ('ROLE_CUSTOMER'),
 ('ROLE_EMPLOYEE'),
 ('ROLE_OPERATION_MAINTENANCE'),
 ('ROLE_STORES_OFFICER'),
 ('ROLE_TECHNICAL_MANAGER'),
 ('ROLE_TECH_ZONAL_SUPERVISOR'),
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
) ENGINE=InnoDB AUTO_INCREMENT=835 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_persistent_audit_event`
--

/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
LOCK TABLES `jhi_persistent_audit_event` WRITE;
INSERT INTO `watererp`.`jhi_persistent_audit_event` VALUES  (1,'admin','2016-02-25 00:08:04','AUTHENTICATION_SUCCESS'),
 (2,'admin','2016-02-26 16:05:34','AUTHENTICATION_SUCCESS'),
 (3,'admin','2016-02-29 17:33:56','AUTHENTICATION_SUCCESS'),
 (4,'admin','2016-03-01 16:04:54','AUTHENTICATION_SUCCESS'),
 (5,'admin','2016-03-01 16:13:37','AUTHENTICATION_SUCCESS'),
 (6,'admin','2016-03-02 15:11:51','AUTHENTICATION_SUCCESS'),
 (7,'admin','2016-03-02 17:29:59','AUTHENTICATION_SUCCESS'),
 (8,'admin','2016-03-03 15:23:21','AUTHENTICATION_SUCCESS'),
 (9,'sf0220','2016-03-03 19:55:28','AUTHENTICATION_FAILURE'),
 (10,'admin','2016-03-03 19:55:34','AUTHENTICATION_SUCCESS'),
 (11,'admin','2016-03-03 23:30:35','AUTHENTICATION_SUCCESS'),
 (12,'admin','2016-03-04 15:53:26','AUTHENTICATION_SUCCESS'),
 (13,'admin','2016-03-04 17:32:35','AUTHENTICATION_SUCCESS'),
 (14,'user','2016-03-04 23:57:50','AUTHENTICATION_SUCCESS'),
 (15,'admin','2016-03-07 15:11:18','AUTHENTICATION_SUCCESS'),
 (16,'admin','2016-03-07 15:18:13','AUTHENTICATION_SUCCESS'),
 (17,'customer','2016-03-07 15:21:56','AUTHENTICATION_SUCCESS'),
 (18,'admin','2016-03-07 15:22:22','AUTHENTICATION_SUCCESS'),
 (19,'admin','2016-03-07 18:51:35','AUTHENTICATION_SUCCESS'),
 (20,'customer','2016-03-07 20:28:29','AUTHENTICATION_SUCCESS'),
 (21,'admin','2016-03-07 20:30:55','AUTHENTICATION_SUCCESS'),
 (22,'customer','2016-03-07 20:31:30','AUTHENTICATION_SUCCESS'),
 (23,'admin','2016-03-07 20:36:12','AUTHENTICATION_SUCCESS'),
 (24,'customer','2016-03-07 20:36:39','AUTHENTICATION_SUCCESS'),
 (25,'admin','2016-03-07 20:38:57','AUTHENTICATION_SUCCESS'),
 (26,'admin','2016-03-07 20:50:03','AUTHENTICATION_SUCCESS'),
 (27,'admin','2016-03-07 20:54:23','AUTHENTICATION_FAILURE'),
 (28,'admin','2016-03-07 20:54:29','AUTHENTICATION_FAILURE'),
 (29,'admin','2016-03-07 20:54:56','AUTHENTICATION_SUCCESS'),
 (30,'customer','2016-03-07 21:03:02','AUTHENTICATION_SUCCESS'),
 (31,'admin','2016-03-07 21:38:33','AUTHENTICATION_SUCCESS'),
 (32,'customer','2016-03-07 22:11:12','AUTHENTICATION_SUCCESS'),
 (33,'customer','2016-03-07 22:18:23','AUTHENTICATION_SUCCESS'),
 (34,'admin','2016-03-07 22:18:42','AUTHENTICATION_SUCCESS'),
 (35,'admin','2016-03-07 22:42:43','AUTHENTICATION_SUCCESS'),
 (36,'admin','2016-03-08 15:17:44','AUTHENTICATION_SUCCESS'),
 (37,'admin','2016-03-08 15:29:25','AUTHENTICATION_SUCCESS'),
 (38,'customer','2016-03-08 16:37:03','AUTHENTICATION_SUCCESS'),
 (39,'admin','2016-03-08 16:38:45','AUTHENTICATION_SUCCESS'),
 (40,'admin','2016-03-08 20:00:25','AUTHENTICATION_SUCCESS'),
 (41,'admin','2016-03-08 23:17:58','AUTHENTICATION_SUCCESS'),
 (42,'customer','2016-03-08 23:18:10','AUTHENTICATION_SUCCESS'),
 (43,'admin','2016-03-08 23:18:48','AUTHENTICATION_SUCCESS'),
 (44,'admin','2016-03-09 15:29:57','AUTHENTICATION_SUCCESS'),
 (45,'admin','2016-03-09 15:30:32','AUTHENTICATION_SUCCESS'),
 (46,'admin','2016-03-09 15:35:34','AUTHENTICATION_SUCCESS'),
 (47,'customer','2016-03-09 15:36:01','AUTHENTICATION_SUCCESS'),
 (48,'admin','2016-03-09 15:36:53','AUTHENTICATION_SUCCESS'),
 (49,'customer','2016-03-09 15:37:14','AUTHENTICATION_SUCCESS'),
 (50,'admin','2016-03-09 15:41:42','AUTHENTICATION_SUCCESS'),
 (51,'customer','2016-03-09 18:38:54','AUTHENTICATION_SUCCESS'),
 (52,'admin','2016-03-09 18:39:19','AUTHENTICATION_SUCCESS'),
 (53,'customer','2016-03-09 19:38:02','AUTHENTICATION_SUCCESS'),
 (54,'admin','2016-03-09 20:16:38','AUTHENTICATION_SUCCESS'),
 (55,'admin','2016-03-09 20:37:35','AUTHENTICATION_SUCCESS'),
 (56,'customer','2016-03-09 21:09:11','AUTHENTICATION_SUCCESS'),
 (57,'admin','2016-03-09 21:10:32','AUTHENTICATION_SUCCESS'),
 (58,'customer','2016-03-10 00:23:35','AUTHENTICATION_SUCCESS'),
 (59,'user','2016-03-10 00:23:56','AUTHENTICATION_SUCCESS'),
 (60,'admin','2016-03-10 00:24:42','AUTHENTICATION_SUCCESS'),
 (61,'admin','2016-03-10 15:33:13','AUTHENTICATION_SUCCESS'),
 (62,'admin','2016-03-10 15:52:36','AUTHENTICATION_SUCCESS'),
 (63,'admin','2016-03-10 16:03:05','AUTHENTICATION_SUCCESS'),
 (64,'customer','2016-03-10 16:03:58','AUTHENTICATION_SUCCESS'),
 (65,'admin','2016-03-10 16:06:26','AUTHENTICATION_SUCCESS'),
 (66,'customer','2016-03-10 16:06:41','AUTHENTICATION_SUCCESS'),
 (67,'admin','2016-03-10 16:17:17','AUTHENTICATION_SUCCESS'),
 (68,'admin','2016-03-10 16:18:02','AUTHENTICATION_SUCCESS'),
 (69,'admin','2016-03-10 16:43:18','AUTHENTICATION_SUCCESS'),
 (70,'customer','2016-03-10 16:43:31','AUTHENTICATION_SUCCESS'),
 (71,'admin','2016-03-10 16:46:55','AUTHENTICATION_SUCCESS'),
 (72,'admin','2016-03-10 16:47:02','AUTHENTICATION_SUCCESS'),
 (73,'customer','2016-03-10 17:00:43','AUTHENTICATION_SUCCESS'),
 (74,'admin','2016-03-10 17:00:56','AUTHENTICATION_SUCCESS'),
 (75,'admin','2016-03-10 18:13:31','AUTHENTICATION_SUCCESS'),
 (76,'customer','2016-03-10 19:58:13','AUTHENTICATION_SUCCESS'),
 (77,'user','2016-03-10 19:59:52','AUTHENTICATION_SUCCESS'),
 (78,'admin','2016-03-10 20:12:31','AUTHENTICATION_SUCCESS'),
 (79,'admin','2016-03-10 20:48:32','AUTHENTICATION_SUCCESS'),
 (80,'customer','2016-03-10 20:48:53','AUTHENTICATION_SUCCESS'),
 (81,'admin','2016-03-10 21:23:24','AUTHENTICATION_SUCCESS'),
 (82,'customer','2016-03-10 22:51:19','AUTHENTICATION_SUCCESS'),
 (83,'admin','2016-03-11 15:44:38','AUTHENTICATION_SUCCESS'),
 (84,'admin','2016-03-11 22:46:38','AUTHENTICATION_SUCCESS'),
 (85,'admin','2016-03-14 15:23:55','AUTHENTICATION_SUCCESS'),
 (86,'admin','2016-03-14 15:33:43','AUTHENTICATION_SUCCESS'),
 (87,'admin','2016-03-14 15:39:25','AUTHENTICATION_SUCCESS'),
 (88,'admin','2016-03-15 15:58:56','AUTHENTICATION_SUCCESS'),
 (89,'admin','2016-03-16 15:28:42','AUTHENTICATION_SUCCESS'),
 (90,'admin','2016-03-16 19:26:44','AUTHENTICATION_SUCCESS'),
 (91,'admin','2016-03-16 19:27:10','AUTHENTICATION_SUCCESS'),
 (92,'admin','2016-03-16 21:06:44','AUTHENTICATION_SUCCESS'),
 (93,'admin','2016-03-17 15:35:50','AUTHENTICATION_SUCCESS'),
 (94,'admin','2016-03-17 18:02:18','AUTHENTICATION_SUCCESS'),
 (95,'admin','2016-03-17 18:28:43','AUTHENTICATION_SUCCESS'),
 (96,'admin','2016-03-18 15:44:10','AUTHENTICATION_SUCCESS'),
 (97,'admin','2016-03-18 15:58:22','AUTHENTICATION_SUCCESS'),
 (98,'admin','2016-03-18 16:43:39','AUTHENTICATION_SUCCESS'),
 (99,'admin','2016-03-18 16:44:08','AUTHENTICATION_SUCCESS'),
 (100,'admin','2016-03-18 18:06:54','AUTHENTICATION_SUCCESS'),
 (101,'admin','2016-03-18 18:23:42','AUTHENTICATION_SUCCESS'),
 (102,'sf0006','2016-03-18 18:25:06','AUTHENTICATION_SUCCESS'),
 (103,'admin','2016-03-18 18:25:51','AUTHENTICATION_SUCCESS'),
 (104,'admin','2016-03-21 15:24:47','AUTHENTICATION_SUCCESS'),
 (105,'admin','2016-03-21 16:53:39','AUTHENTICATION_SUCCESS'),
 (106,'admin','2016-03-21 17:45:54','AUTHENTICATION_SUCCESS'),
 (107,'admin','2016-03-21 17:46:06','AUTHENTICATION_SUCCESS'),
 (108,'admin','2016-03-21 18:46:46','AUTHENTICATION_SUCCESS'),
 (109,'admin','2016-03-21 20:12:34','AUTHENTICATION_SUCCESS'),
 (110,'customer','2016-03-21 20:12:55','AUTHENTICATION_SUCCESS'),
 (111,'admin','2016-03-21 21:50:14','AUTHENTICATION_SUCCESS'),
 (112,'customer','2016-03-21 21:51:14','AUTHENTICATION_SUCCESS'),
 (113,'admin','2016-03-21 22:18:01','AUTHENTICATION_SUCCESS'),
 (114,'customer','2016-03-21 22:42:21','AUTHENTICATION_SUCCESS'),
 (115,'customer','2016-03-21 23:01:11','AUTHENTICATION_SUCCESS'),
 (116,'sf0015','2016-03-22 00:14:17','AUTHENTICATION_SUCCESS'),
 (117,'admin','2016-03-22 00:15:09','AUTHENTICATION_SUCCESS'),
 (118,'sf0015','2016-03-22 00:20:12','AUTHENTICATION_SUCCESS'),
 (119,'sf0015','2016-03-22 15:16:11','AUTHENTICATION_SUCCESS'),
 (120,'admin','2016-03-22 15:19:49','AUTHENTICATION_SUCCESS'),
 (121,'sf0015','2016-03-22 15:29:10','AUTHENTICATION_SUCCESS'),
 (122,'sf0015','2016-03-22 17:26:14','AUTHENTICATION_SUCCESS'),
 (123,'sf0024','2016-03-22 18:08:58','AUTHENTICATION_SUCCESS'),
 (124,'customer','2016-03-22 18:36:10','AUTHENTICATION_SUCCESS'),
 (125,'sf0015','2016-03-22 18:40:05','AUTHENTICATION_SUCCESS'),
 (126,'sf0029','2016-03-22 18:50:44','AUTHENTICATION_SUCCESS'),
 (127,'admin','2016-03-22 19:50:10','AUTHENTICATION_SUCCESS'),
 (128,'sf0020','2016-03-22 19:52:49','AUTHENTICATION_SUCCESS'),
 (129,'sf0015','2016-03-22 20:08:47','AUTHENTICATION_SUCCESS'),
 (130,'sf0027','2016-03-22 20:16:46','AUTHENTICATION_SUCCESS'),
 (131,'sf0014','2016-03-22 20:18:51','AUTHENTICATION_SUCCESS'),
 (132,'admin','2016-03-22 20:34:49','AUTHENTICATION_SUCCESS'),
 (133,'admin','2016-03-22 21:12:51','AUTHENTICATION_SUCCESS'),
 (134,'admin','2016-03-22 21:14:53','AUTHENTICATION_SUCCESS'),
 (135,'customer','2016-03-22 22:06:01','AUTHENTICATION_SUCCESS'),
 (136,'admin','2016-03-22 22:08:24','AUTHENTICATION_SUCCESS'),
 (137,'customer','2016-03-22 22:10:04','AUTHENTICATION_SUCCESS'),
 (138,'sf0015','2016-03-22 23:16:55','AUTHENTICATION_SUCCESS'),
 (139,'admin','2016-03-23 15:29:46','AUTHENTICATION_SUCCESS'),
 (140,'admin','2016-03-23 15:59:24','AUTHENTICATION_SUCCESS'),
 (141,'admin','2016-03-23 16:08:47','AUTHENTICATION_SUCCESS'),
 (142,'sf0015','2016-03-23 18:47:51','AUTHENTICATION_SUCCESS'),
 (143,'customer','2016-03-23 20:11:07','AUTHENTICATION_SUCCESS'),
 (144,'sf0015','2016-03-23 20:33:56','AUTHENTICATION_SUCCESS'),
 (145,'sf0029','2016-03-23 21:06:37','AUTHENTICATION_SUCCESS'),
 (146,'sf0020','2016-03-23 21:39:50','AUTHENTICATION_SUCCESS'),
 (147,'sf0015','2016-03-23 21:42:29','AUTHENTICATION_SUCCESS'),
 (148,'sf0027','2016-03-23 21:44:32','AUTHENTICATION_SUCCESS'),
 (149,'sf0014','2016-03-23 21:47:53','AUTHENTICATION_SUCCESS'),
 (150,'sf0015','2016-03-23 21:54:16','AUTHENTICATION_SUCCESS'),
 (151,'customer','2016-03-23 21:59:55','AUTHENTICATION_SUCCESS'),
 (152,'sf0015','2016-03-23 22:03:48','AUTHENTICATION_SUCCESS'),
 (153,'customer','2016-03-23 22:56:04','AUTHENTICATION_SUCCESS'),
 (154,'sf0015','2016-03-23 22:57:00','AUTHENTICATION_SUCCESS'),
 (155,'sf0029','2016-03-23 23:02:45','AUTHENTICATION_SUCCESS'),
 (156,'admin','2016-03-23 23:14:12','AUTHENTICATION_SUCCESS'),
 (157,'sf0029','2016-03-23 23:49:54','AUTHENTICATION_SUCCESS'),
 (158,'sf0020','2016-03-24 15:16:53','AUTHENTICATION_SUCCESS'),
 (159,'customer','2016-03-24 16:56:49','AUTHENTICATION_SUCCESS'),
 (160,'admin','2016-03-24 17:05:15','AUTHENTICATION_SUCCESS'),
 (161,'sf0015','2016-03-24 17:45:10','AUTHENTICATION_SUCCESS'),
 (162,'customer','2016-03-24 17:54:03','AUTHENTICATION_SUCCESS'),
 (163,'sf0015','2016-03-24 17:57:24','AUTHENTICATION_SUCCESS'),
 (164,'customer','2016-03-24 19:51:54','AUTHENTICATION_SUCCESS'),
 (165,'customer','2016-03-24 20:04:11','AUTHENTICATION_SUCCESS'),
 (166,'ssf0015','2016-03-24 20:05:37','AUTHENTICATION_FAILURE'),
 (167,'sf0015','2016-03-24 20:05:47','AUTHENTICATION_SUCCESS'),
 (168,'sf0029','2016-03-24 20:09:43','AUTHENTICATION_FAILURE'),
 (169,'sf0029','2016-03-24 20:09:51','AUTHENTICATION_SUCCESS'),
 (170,'admin','2016-03-24 21:03:32','AUTHENTICATION_SUCCESS'),
 (171,'admin','2016-03-24 21:59:29','AUTHENTICATION_SUCCESS'),
 (172,'admin','2016-03-24 22:20:38','AUTHENTICATION_SUCCESS'),
 (173,'admin','2016-03-24 22:23:51','AUTHENTICATION_SUCCESS'),
 (174,'admin','2016-03-24 22:33:17','AUTHENTICATION_SUCCESS'),
 (175,'admin','2016-03-25 15:22:32','AUTHENTICATION_SUCCESS'),
 (176,'sf0015','2016-03-25 15:57:55','AUTHENTICATION_SUCCESS'),
 (177,'customer','2016-03-25 21:06:43','AUTHENTICATION_SUCCESS'),
 (178,'sf0015','2016-03-25 21:07:43','AUTHENTICATION_SUCCESS'),
 (179,'customer','2016-03-25 22:46:12','AUTHENTICATION_SUCCESS'),
 (180,'sf0015','2016-03-25 22:46:47','AUTHENTICATION_SUCCESS'),
 (181,'sf0015','2016-03-25 23:19:09','AUTHENTICATION_FAILURE'),
 (182,'sf0015','2016-03-25 23:19:20','AUTHENTICATION_SUCCESS'),
 (183,'admin','2016-03-26 15:37:09','AUTHENTICATION_SUCCESS'),
 (184,'sf0015','2016-03-26 15:38:12','AUTHENTICATION_SUCCESS'),
 (185,'admin','2016-03-26 17:43:00','AUTHENTICATION_SUCCESS'),
 (186,'admin','2016-03-26 17:46:04','AUTHENTICATION_SUCCESS'),
 (187,'sf0015','2016-03-26 18:08:24','AUTHENTICATION_SUCCESS'),
 (188,'admin','2016-03-26 18:13:18','AUTHENTICATION_SUCCESS'),
 (189,'customer','2016-03-26 18:34:18','AUTHENTICATION_SUCCESS'),
 (190,'admin','2016-03-26 18:45:53','AUTHENTICATION_SUCCESS'),
 (191,'customer','2016-03-26 19:56:20','AUTHENTICATION_SUCCESS'),
 (192,'admin','2016-03-26 20:34:07','AUTHENTICATION_SUCCESS'),
 (193,'customer','2016-03-26 20:36:03','AUTHENTICATION_SUCCESS'),
 (194,'sf0015','2016-03-26 20:43:19','AUTHENTICATION_SUCCESS'),
 (195,'customer','2016-03-26 21:24:24','AUTHENTICATION_FAILURE'),
 (196,'customer','2016-03-26 21:24:31','AUTHENTICATION_SUCCESS'),
 (197,'sf0015','2016-03-26 21:25:50','AUTHENTICATION_SUCCESS'),
 (198,'sf0015','2016-03-28 15:27:59','AUTHENTICATION_SUCCESS'),
 (199,'sf0015','2016-03-28 15:31:48','AUTHENTICATION_SUCCESS'),
 (200,'admin','2016-03-28 18:13:06','AUTHENTICATION_SUCCESS'),
 (201,'sf0006','2016-03-28 18:14:05','AUTHENTICATION_FAILURE'),
 (202,'sf0006','2016-03-28 18:14:20','AUTHENTICATION_SUCCESS'),
 (203,'sf0009','2016-03-28 18:14:48','AUTHENTICATION_SUCCESS'),
 (204,'sf0010','2016-03-28 18:15:25','AUTHENTICATION_SUCCESS'),
 (205,'sf0011','2016-03-28 18:16:19','AUTHENTICATION_SUCCESS'),
 (206,'sf0013','2016-03-28 18:16:59','AUTHENTICATION_SUCCESS'),
 (207,'sf0014','2016-03-28 18:17:44','AUTHENTICATION_SUCCESS'),
 (208,'sf0015','2016-03-28 18:18:18','AUTHENTICATION_SUCCESS'),
 (209,'sf0016','2016-03-28 18:18:43','AUTHENTICATION_SUCCESS'),
 (210,'sf0017','2016-03-28 18:19:28','AUTHENTICATION_SUCCESS'),
 (211,'sf0018','2016-03-28 18:20:15','AUTHENTICATION_SUCCESS'),
 (212,'sf0019','2016-03-28 18:20:59','AUTHENTICATION_SUCCESS'),
 (213,'sf0021','2016-03-28 18:21:34','AUTHENTICATION_SUCCESS'),
 (214,'sf0022','2016-03-28 18:22:15','AUTHENTICATION_SUCCESS'),
 (215,'sf0023','2016-03-28 18:23:30','AUTHENTICATION_SUCCESS'),
 (216,'sf0027','2016-03-28 18:27:19','AUTHENTICATION_SUCCESS'),
 (217,'sf0028','2016-03-28 18:27:55','AUTHENTICATION_SUCCESS'),
 (218,'sf0015','2016-03-28 18:28:26','AUTHENTICATION_SUCCESS'),
 (219,'sf0015','2016-03-28 19:35:33','AUTHENTICATION_SUCCESS'),
 (220,'sf0029','2016-03-28 19:49:02','AUTHENTICATION_SUCCESS'),
 (221,'customer','2016-03-28 22:14:03','AUTHENTICATION_SUCCESS'),
 (222,'sf0006','2016-03-28 22:23:02','AUTHENTICATION_SUCCESS'),
 (223,'sf0029','2016-03-28 22:23:29','AUTHENTICATION_SUCCESS'),
 (224,'sf0015','2016-03-28 22:23:45','AUTHENTICATION_SUCCESS'),
 (225,'customer','2016-03-28 22:41:07','AUTHENTICATION_SUCCESS'),
 (226,'sf0015','2016-03-28 22:49:20','AUTHENTICATION_SUCCESS'),
 (227,'sf0015','2016-03-28 23:04:50','AUTHENTICATION_SUCCESS'),
 (228,'customer','2016-03-28 23:09:35','AUTHENTICATION_SUCCESS'),
 (229,'sf0015','2016-03-28 23:11:04','AUTHENTICATION_SUCCESS'),
 (230,'sf0015','2016-03-28 23:19:39','AUTHENTICATION_SUCCESS'),
 (231,'customer','2016-03-28 23:20:54','AUTHENTICATION_SUCCESS'),
 (232,'sf0015','2016-03-28 23:21:50','AUTHENTICATION_SUCCESS'),
 (233,'sf0029','2016-03-28 23:45:50','AUTHENTICATION_SUCCESS'),
 (234,'customer','2016-03-28 23:55:52','AUTHENTICATION_SUCCESS'),
 (235,'sf0015','2016-03-28 23:56:37','AUTHENTICATION_SUCCESS'),
 (236,'admin','2016-03-29 00:09:20','AUTHENTICATION_SUCCESS'),
 (237,'sf0029','2016-03-29 00:10:58','AUTHENTICATION_SUCCESS'),
 (238,'admin','2016-03-29 00:12:41','AUTHENTICATION_SUCCESS'),
 (239,'customer','2016-03-29 16:00:34','AUTHENTICATION_SUCCESS'),
 (240,'sf0015','2016-03-29 17:17:54','AUTHENTICATION_SUCCESS'),
 (241,'customer','2016-03-29 17:53:39','AUTHENTICATION_SUCCESS'),
 (242,'customer','2016-03-29 18:06:12','AUTHENTICATION_SUCCESS'),
 (243,'sf0015','2016-03-29 18:19:09','AUTHENTICATION_SUCCESS'),
 (244,'customer','2016-03-29 20:18:31','AUTHENTICATION_SUCCESS'),
 (245,'sf0015','2016-03-29 20:26:45','AUTHENTICATION_SUCCESS'),
 (246,'sf0029','2016-03-30 00:36:19','AUTHENTICATION_SUCCESS'),
 (247,'admin','2016-03-30 17:00:02','AUTHENTICATION_SUCCESS'),
 (248,'admin','2016-03-30 17:18:07','AUTHENTICATION_SUCCESS'),
 (249,'admin','2016-03-30 19:30:46','AUTHENTICATION_SUCCESS'),
 (250,'admin','2016-03-31 00:34:51','AUTHENTICATION_SUCCESS'),
 (251,'admin','2016-03-31 15:25:45','AUTHENTICATION_SUCCESS'),
 (252,'customer','2016-04-01 00:07:21','AUTHENTICATION_SUCCESS'),
 (253,'customer','2016-04-01 00:44:12','AUTHENTICATION_SUCCESS'),
 (254,'sf0015','2016-04-01 15:37:33','AUTHENTICATION_SUCCESS'),
 (255,'customer','2016-04-01 15:41:22','AUTHENTICATION_SUCCESS'),
 (256,'sf0016','2016-04-01 15:47:46','AUTHENTICATION_SUCCESS'),
 (257,'sf0015','2016-04-01 15:48:05','AUTHENTICATION_SUCCESS'),
 (258,'sf0029','2016-04-01 16:00:50','AUTHENTICATION_SUCCESS'),
 (259,'customer','2016-04-01 16:04:01','AUTHENTICATION_SUCCESS'),
 (260,'sf0015','2016-04-01 16:06:08','AUTHENTICATION_SUCCESS'),
 (261,'sf0016','2016-04-01 16:06:22','AUTHENTICATION_SUCCESS'),
 (262,'sf0015','2016-04-01 16:06:34','AUTHENTICATION_FAILURE'),
 (263,'sf0015','2016-04-01 16:06:39','AUTHENTICATION_SUCCESS'),
 (264,'sf0029','2016-04-01 17:02:50','AUTHENTICATION_SUCCESS'),
 (265,'admin','2016-04-01 17:43:50','AUTHENTICATION_SUCCESS'),
 (266,'admin','2016-04-01 22:13:46','AUTHENTICATION_FAILURE'),
 (267,'admin','2016-04-01 22:13:51','AUTHENTICATION_SUCCESS'),
 (268,'admin','2016-04-01 22:21:17','AUTHENTICATION_SUCCESS'),
 (269,'admin','2016-04-01 23:53:13','AUTHENTICATION_SUCCESS'),
 (270,'admin','2016-04-02 12:59:18','AUTHENTICATION_SUCCESS'),
 (271,'admin','2016-04-03 12:48:49','AUTHENTICATION_SUCCESS'),
 (272,'admin','2016-04-05 16:04:36','AUTHENTICATION_SUCCESS'),
 (273,'customer','2016-04-05 16:24:06','AUTHENTICATION_SUCCESS'),
 (274,'admin','2016-04-05 16:30:01','AUTHENTICATION_SUCCESS'),
 (275,'admin','2016-04-05 16:34:41','AUTHENTICATION_SUCCESS'),
 (276,'customer','2016-04-05 16:35:26','AUTHENTICATION_SUCCESS'),
 (277,'sf0020','2016-04-05 17:26:35','AUTHENTICATION_SUCCESS'),
 (278,'customer','2016-04-05 19:55:44','AUTHENTICATION_SUCCESS'),
 (279,'sf0020','2016-04-05 20:10:44','AUTHENTICATION_SUCCESS'),
 (280,'sf0017','2016-04-05 20:44:22','AUTHENTICATION_SUCCESS'),
 (281,'sf0009','2016-04-05 20:47:09','AUTHENTICATION_SUCCESS'),
 (282,'sf0021','2016-04-05 20:49:01','AUTHENTICATION_SUCCESS'),
 (283,'admin','2016-04-05 21:17:53','AUTHENTICATION_SUCCESS'),
 (284,'admin','2016-04-05 21:32:48','AUTHENTICATION_SUCCESS'),
 (285,'admin','2016-04-05 22:40:28','AUTHENTICATION_SUCCESS'),
 (286,'admin','2016-04-06 00:06:20','AUTHENTICATION_SUCCESS'),
 (287,'admin','2016-04-06 00:30:09','AUTHENTICATION_SUCCESS'),
 (288,'admin','2016-04-06 17:36:48','AUTHENTICATION_SUCCESS'),
 (289,'admin','2016-04-07 15:19:45','AUTHENTICATION_SUCCESS'),
 (290,'admin','2016-04-07 20:01:19','AUTHENTICATION_SUCCESS'),
 (291,'admin','2016-04-07 20:01:26','AUTHENTICATION_SUCCESS'),
 (292,'admin','2016-04-07 21:04:25','AUTHENTICATION_SUCCESS'),
 (293,'admin','2016-04-07 21:42:15','AUTHENTICATION_SUCCESS'),
 (294,'admin','2016-04-07 23:19:32','AUTHENTICATION_SUCCESS'),
 (295,'admin','2016-04-09 16:45:54','AUTHENTICATION_SUCCESS'),
 (296,'admin','2016-04-09 22:31:51','AUTHENTICATION_SUCCESS'),
 (297,'admin','2016-04-12 22:12:49','AUTHENTICATION_SUCCESS'),
 (298,'admin','2016-04-12 22:37:08','AUTHENTICATION_SUCCESS'),
 (299,'admin','2016-04-13 00:08:02','AUTHENTICATION_SUCCESS'),
 (300,'sf0015','2016-04-13 18:58:30','AUTHENTICATION_SUCCESS'),
 (301,'admin','2016-04-13 18:58:49','AUTHENTICATION_SUCCESS'),
 (302,'admin','2016-04-15 20:36:37','AUTHENTICATION_SUCCESS'),
 (303,'admin','2016-04-15 20:36:42','AUTHENTICATION_SUCCESS'),
 (304,'billrunuser','2016-04-16 11:19:54','AUTHENTICATION_FAILURE'),
 (305,'admin','2016-04-16 11:20:10','AUTHENTICATION_SUCCESS'),
 (306,'billrunuser','2016-04-16 11:24:11','AUTHENTICATION_SUCCESS'),
 (307,'admin','2016-04-16 11:24:49','AUTHENTICATION_SUCCESS'),
 (308,'admin','2016-04-16 17:07:25','AUTHENTICATION_SUCCESS'),
 (309,'billrunuser','2016-04-16 17:16:09','AUTHENTICATION_SUCCESS'),
 (310,'admin','2016-04-16 17:23:00','AUTHENTICATION_SUCCESS'),
 (311,'billrunuser','2016-04-16 17:24:58','AUTHENTICATION_SUCCESS'),
 (312,'billrunuser','2016-04-16 17:52:23','AUTHENTICATION_SUCCESS'),
 (313,'admin','2016-04-16 18:13:14','AUTHENTICATION_SUCCESS'),
 (314,'billrunuser','2016-04-16 18:39:14','AUTHENTICATION_SUCCESS'),
 (315,'admin','2016-04-16 20:36:34','AUTHENTICATION_SUCCESS'),
 (316,'billrunmgr','2016-04-16 23:00:45','AUTHENTICATION_SUCCESS'),
 (317,'billrunuser','2016-04-16 23:09:57','AUTHENTICATION_SUCCESS'),
 (318,'billrunmgr','2016-04-16 23:14:26','AUTHENTICATION_SUCCESS'),
 (319,'admin','2016-04-18 16:08:23','AUTHENTICATION_SUCCESS'),
 (320,'billrunuser','2016-04-18 16:22:08','AUTHENTICATION_SUCCESS'),
 (321,'sf0020','2016-04-18 19:34:44','AUTHENTICATION_SUCCESS'),
 (322,'customer','2016-04-18 19:35:31','AUTHENTICATION_SUCCESS'),
 (323,'sf0015','2016-04-18 19:37:05','AUTHENTICATION_SUCCESS'),
 (324,'sf0029','2016-04-18 19:37:37','AUTHENTICATION_SUCCESS'),
 (325,'sf0029','2016-04-18 19:37:49','AUTHENTICATION_SUCCESS'),
 (326,'sf0020','2016-04-18 19:38:16','AUTHENTICATION_SUCCESS'),
 (327,'sf0015','2016-04-18 19:39:14','AUTHENTICATION_SUCCESS'),
 (328,'sf0027','2016-04-18 19:52:00','AUTHENTICATION_SUCCESS'),
 (329,'sf0014','2016-04-18 20:07:19','AUTHENTICATION_SUCCESS'),
 (330,'sf0020','2016-04-18 20:57:52','AUTHENTICATION_SUCCESS'),
 (331,'customer','2016-04-18 21:44:37','AUTHENTICATION_SUCCESS'),
 (332,'sf0015','2016-04-18 21:45:27','AUTHENTICATION_SUCCESS'),
 (333,'sf0029','2016-04-18 21:49:09','AUTHENTICATION_SUCCESS'),
 (334,'sf0020','2016-04-18 21:49:31','AUTHENTICATION_SUCCESS'),
 (335,'sf0015','2016-04-18 21:50:58','AUTHENTICATION_SUCCESS'),
 (336,'sf0027','2016-04-18 21:58:45','AUTHENTICATION_SUCCESS'),
 (337,'customer','2016-04-18 23:05:10','AUTHENTICATION_SUCCESS'),
 (338,'sf0015','2016-04-18 23:06:10','AUTHENTICATION_SUCCESS'),
 (339,'sf0029','2016-04-18 23:31:20','AUTHENTICATION_SUCCESS'),
 (340,'sf0020','2016-04-18 23:32:42','AUTHENTICATION_SUCCESS'),
 (341,'sf0015','2016-04-18 23:33:52','AUTHENTICATION_SUCCESS'),
 (342,'sf0027','2016-04-18 23:36:58','AUTHENTICATION_SUCCESS'),
 (343,'sf0020','2016-04-18 23:50:02','AUTHENTICATION_SUCCESS'),
 (344,'sf0021','2016-04-18 23:51:50','AUTHENTICATION_SUCCESS'),
 (345,'sf0021','2016-04-18 23:53:55','AUTHENTICATION_SUCCESS'),
 (346,'admin','2016-04-19 15:44:39','AUTHENTICATION_SUCCESS'),
 (347,'customer','2016-04-19 15:59:35','AUTHENTICATION_FAILURE'),
 (348,'customer','2016-04-19 15:59:39','AUTHENTICATION_SUCCESS'),
 (349,'sf0015','2016-04-19 16:00:58','AUTHENTICATION_SUCCESS'),
 (350,'admin','2016-04-19 16:03:19','AUTHENTICATION_SUCCESS'),
 (351,'sf0015','2016-04-19 16:09:52','AUTHENTICATION_SUCCESS'),
 (352,'admin','2016-04-19 16:10:08','AUTHENTICATION_SUCCESS'),
 (353,'sf0015','2016-04-19 16:19:11','AUTHENTICATION_SUCCESS'),
 (354,'sf0029','2016-04-19 16:19:32','AUTHENTICATION_SUCCESS'),
 (355,'admin','2016-04-19 16:20:27','AUTHENTICATION_SUCCESS'),
 (356,'customer','2016-04-19 16:33:36','AUTHENTICATION_SUCCESS'),
 (357,'sf0015','2016-04-19 16:36:55','AUTHENTICATION_SUCCESS'),
 (358,'sf0029','2016-04-19 16:37:22','AUTHENTICATION_SUCCESS'),
 (359,'sf0020','2016-04-19 16:37:45','AUTHENTICATION_SUCCESS'),
 (360,'sf0015','2016-04-19 16:39:09','AUTHENTICATION_SUCCESS'),
 (361,'sf0027','2016-04-19 16:40:08','AUTHENTICATION_SUCCESS'),
 (362,'sf0020','2016-04-19 16:45:49','AUTHENTICATION_SUCCESS'),
 (363,'sf0021','2016-04-19 16:47:59','AUTHENTICATION_SUCCESS'),
 (364,'admin','2016-04-19 17:23:27','AUTHENTICATION_SUCCESS'),
 (365,'sf0020','2016-04-19 18:12:16','AUTHENTICATION_SUCCESS'),
 (366,'sf0015','2016-04-19 18:25:57','AUTHENTICATION_SUCCESS'),
 (367,'customer','2016-04-19 18:42:20','AUTHENTICATION_SUCCESS'),
 (368,'sf0020','2016-04-19 18:50:09','AUTHENTICATION_SUCCESS'),
 (369,'admin','2016-04-19 21:39:43','AUTHENTICATION_FAILURE'),
 (370,'admin','2016-04-19 21:39:49','AUTHENTICATION_SUCCESS'),
 (371,'customer','2016-04-20 00:50:50','AUTHENTICATION_SUCCESS'),
 (372,'customer','2016-04-20 00:58:40','AUTHENTICATION_SUCCESS'),
 (373,'sf0015','2016-04-20 00:59:20','AUTHENTICATION_FAILURE'),
 (374,'sf0015','2016-04-20 00:59:25','AUTHENTICATION_SUCCESS'),
 (375,'sf0029','2016-04-20 00:59:41','AUTHENTICATION_SUCCESS'),
 (376,'sf0020','2016-04-20 00:59:56','AUTHENTICATION_SUCCESS'),
 (377,'sf0015','2016-04-20 01:00:41','AUTHENTICATION_SUCCESS'),
 (378,'sf0027','2016-04-20 01:01:13','AUTHENTICATION_SUCCESS'),
 (379,'sf0020','2016-04-20 01:01:43','AUTHENTICATION_SUCCESS'),
 (380,'billrunuser','2016-04-20 20:48:52','AUTHENTICATION_SUCCESS'),
 (381,'admin','2016-04-20 22:47:06','AUTHENTICATION_SUCCESS'),
 (382,'billrunuser','2016-04-21 00:16:01','AUTHENTICATION_SUCCESS'),
 (383,'admin','2016-04-21 14:07:30','AUTHENTICATION_SUCCESS'),
 (384,'admin','2016-04-21 15:16:59','AUTHENTICATION_SUCCESS'),
 (385,'billrunuser','2016-04-21 15:17:32','AUTHENTICATION_SUCCESS'),
 (386,'admin','2016-04-21 16:48:57','AUTHENTICATION_SUCCESS'),
 (387,'admin','2016-04-21 16:49:07','AUTHENTICATION_SUCCESS'),
 (388,'admin','2016-04-21 16:49:25','AUTHENTICATION_SUCCESS'),
 (389,'admin','2016-04-21 17:27:22','AUTHENTICATION_SUCCESS'),
 (390,'billrunuser','2016-04-21 17:28:03','AUTHENTICATION_SUCCESS'),
 (391,'admin','2016-04-21 20:02:15','AUTHENTICATION_SUCCESS'),
 (392,'admin','2016-04-21 21:30:45','AUTHENTICATION_SUCCESS'),
 (393,'admin','2016-04-21 21:30:59','AUTHENTICATION_SUCCESS'),
 (394,'admin','2016-04-21 21:38:15','AUTHENTICATION_SUCCESS'),
 (395,'admin','2016-04-21 21:38:35','AUTHENTICATION_SUCCESS'),
 (396,'admin','2016-04-21 21:44:00','AUTHENTICATION_SUCCESS'),
 (397,'admin','2016-04-21 21:45:38','AUTHENTICATION_SUCCESS'),
 (398,'admin','2016-04-21 21:47:47','AUTHENTICATION_SUCCESS'),
 (399,'admin','2016-04-21 22:02:19','AUTHENTICATION_SUCCESS'),
 (400,'admin','2016-04-21 22:22:11','AUTHENTICATION_SUCCESS'),
 (401,'admin','2016-04-21 22:22:41','AUTHENTICATION_SUCCESS'),
 (402,'admin','2016-04-21 22:23:37','AUTHENTICATION_SUCCESS'),
 (403,'admin','2016-04-22 21:24:26','AUTHENTICATION_SUCCESS'),
 (404,'sf0015','2016-04-22 21:26:35','AUTHENTICATION_SUCCESS'),
 (405,'billrunuser','2016-04-23 00:54:33','AUTHENTICATION_SUCCESS'),
 (406,'billrunuser','2016-04-23 16:15:01','AUTHENTICATION_SUCCESS'),
 (407,'billrunuser','2016-04-23 19:37:57','AUTHENTICATION_SUCCESS'),
 (408,'admin','2016-04-23 20:08:18','AUTHENTICATION_SUCCESS'),
 (409,'admin','2016-04-23 20:51:55','AUTHENTICATION_SUCCESS'),
 (410,'billrunuser','2016-04-23 20:57:29','AUTHENTICATION_SUCCESS'),
 (411,'admin','2016-04-24 15:45:50','AUTHENTICATION_SUCCESS'),
 (412,'admin','2016-04-25 13:28:30','AUTHENTICATION_SUCCESS'),
 (413,'admin','2016-04-25 17:51:44','AUTHENTICATION_SUCCESS'),
 (414,'admin','2016-04-25 21:58:17','AUTHENTICATION_SUCCESS'),
 (415,'admin','2016-04-25 22:00:43','AUTHENTICATION_SUCCESS'),
 (416,'admin','2016-04-25 22:09:02','AUTHENTICATION_SUCCESS'),
 (417,'admin','2016-04-25 22:13:50','AUTHENTICATION_SUCCESS'),
 (418,'billrunuser','2016-04-25 22:14:22','AUTHENTICATION_SUCCESS'),
 (419,'billrunuser','2016-04-25 22:16:27','AUTHENTICATION_SUCCESS'),
 (420,'billrunuser','2016-04-25 22:26:09','AUTHENTICATION_SUCCESS'),
 (421,'billrunuser','2016-04-25 22:28:41','AUTHENTICATION_SUCCESS'),
 (422,'admin','2016-04-25 22:39:25','AUTHENTICATION_SUCCESS'),
 (423,'billrunuser','2016-04-25 22:40:25','AUTHENTICATION_SUCCESS'),
 (424,'billrunuser','2016-04-25 22:46:15','AUTHENTICATION_SUCCESS'),
 (425,'billrunuser','2016-04-25 22:55:29','AUTHENTICATION_SUCCESS'),
 (426,'billrunuser','2016-04-25 22:55:49','AUTHENTICATION_SUCCESS'),
 (427,'admin','2016-04-25 23:02:16','AUTHENTICATION_SUCCESS'),
 (428,'admin','2016-04-26 13:14:16','AUTHENTICATION_SUCCESS'),
 (429,'billrunuser','2016-04-26 13:58:45','AUTHENTICATION_SUCCESS'),
 (430,'customer','2016-04-26 16:19:51','AUTHENTICATION_SUCCESS'),
 (431,'sf0020','2016-04-26 16:31:28','AUTHENTICATION_SUCCESS'),
 (432,'sf0015','2016-04-26 16:31:52','AUTHENTICATION_SUCCESS'),
 (433,'sf0029','2016-04-26 16:35:59','AUTHENTICATION_SUCCESS'),
 (434,'sf0020','2016-04-26 16:51:58','AUTHENTICATION_SUCCESS'),
 (435,'sf0015','2016-04-26 17:05:40','AUTHENTICATION_SUCCESS'),
 (436,'sf0027','2016-04-26 17:24:07','AUTHENTICATION_SUCCESS'),
 (437,'sf0014','2016-04-26 17:29:12','AUTHENTICATION_SUCCESS'),
 (438,'sf0020','2016-04-26 17:33:26','AUTHENTICATION_SUCCESS'),
 (439,'sf0021','2016-04-26 17:35:53','AUTHENTICATION_SUCCESS'),
 (440,'customer','2016-04-26 18:24:54','AUTHENTICATION_SUCCESS'),
 (441,'admin','2016-04-26 19:13:32','AUTHENTICATION_SUCCESS'),
 (442,'billrunuser','2016-04-26 21:53:02','AUTHENTICATION_SUCCESS'),
 (443,'admin','2016-04-27 13:55:53','AUTHENTICATION_SUCCESS'),
 (444,'admin','2016-04-27 15:31:13','AUTHENTICATION_SUCCESS'),
 (445,'sf0016','2016-04-27 15:31:30','AUTHENTICATION_SUCCESS'),
 (446,'admin','2016-04-27 15:31:57','AUTHENTICATION_SUCCESS'),
 (447,'sf0016','2016-04-27 15:42:42','AUTHENTICATION_SUCCESS'),
 (448,'sf0016','2016-04-27 15:55:31','AUTHENTICATION_SUCCESS'),
 (449,'admin','2016-04-27 15:59:49','AUTHENTICATION_SUCCESS'),
 (450,'admin','2016-04-27 16:02:33','AUTHENTICATION_SUCCESS'),
 (451,'customer','2016-04-27 16:12:08','AUTHENTICATION_SUCCESS'),
 (452,'sf0023','2016-04-27 16:19:15','AUTHENTICATION_SUCCESS'),
 (453,'sf0016','2016-04-27 16:19:26','AUTHENTICATION_SUCCESS'),
 (454,'admin','2016-04-27 17:07:44','AUTHENTICATION_SUCCESS'),
 (455,'admin','2016-04-27 20:26:12','AUTHENTICATION_SUCCESS'),
 (456,'customer','2016-04-27 20:56:17','AUTHENTICATION_SUCCESS'),
 (457,'admin','2016-04-27 23:35:11','AUTHENTICATION_SUCCESS'),
 (458,'sf0016','2016-04-28 00:21:33','AUTHENTICATION_SUCCESS'),
 (459,'admin','2016-04-28 16:20:34','AUTHENTICATION_SUCCESS'),
 (460,'sf0016','2016-04-28 16:24:21','AUTHENTICATION_SUCCESS'),
 (461,'sf0023','2016-04-28 16:24:58','AUTHENTICATION_SUCCESS'),
 (462,'admin','2016-04-28 17:07:37','AUTHENTICATION_SUCCESS'),
 (463,'sf0021','2016-04-28 23:09:14','AUTHENTICATION_SUCCESS'),
 (464,'customer','2016-04-28 23:11:05','AUTHENTICATION_SUCCESS'),
 (465,'sf0023','2016-04-28 23:13:05','AUTHENTICATION_SUCCESS'),
 (466,'sf0016','2016-04-28 23:14:18','AUTHENTICATION_SUCCESS'),
 (467,'sf0009','2016-04-28 23:16:06','AUTHENTICATION_SUCCESS'),
 (468,'sf0021','2016-04-28 23:20:34','AUTHENTICATION_SUCCESS'),
 (469,'customer','2016-04-28 23:27:09','AUTHENTICATION_SUCCESS'),
 (470,'sf0023','2016-04-28 23:28:12','AUTHENTICATION_SUCCESS'),
 (471,'sf0016','2016-04-28 23:29:12','AUTHENTICATION_SUCCESS'),
 (472,'sf0017','2016-04-28 23:30:05','AUTHENTICATION_SUCCESS'),
 (473,'sf0009','2016-04-28 23:31:12','AUTHENTICATION_SUCCESS'),
 (474,'sf0021','2016-04-28 23:32:33','AUTHENTICATION_SUCCESS'),
 (475,'admin','2016-04-29 13:31:17','AUTHENTICATION_SUCCESS'),
 (476,'customer','2016-04-29 15:01:47','AUTHENTICATION_SUCCESS'),
 (477,'sf0016','2016-04-29 16:42:25','AUTHENTICATION_SUCCESS'),
 (478,'sf0016','2016-04-29 17:04:00','AUTHENTICATION_SUCCESS'),
 (479,'admin','2016-04-29 17:06:59','AUTHENTICATION_SUCCESS'),
 (480,'sf0023','2016-04-29 17:19:20','AUTHENTICATION_SUCCESS'),
 (481,'admin','2016-04-29 17:32:25','AUTHENTICATION_SUCCESS'),
 (482,'sf0016','2016-04-29 17:45:02','AUTHENTICATION_SUCCESS'),
 (483,'customer','2016-04-29 18:31:11','AUTHENTICATION_SUCCESS'),
 (484,'sf0023','2016-04-29 19:36:57','AUTHENTICATION_SUCCESS'),
 (485,'sf0016','2016-04-29 19:38:50','AUTHENTICATION_SUCCESS'),
 (486,'sf0017','2016-04-29 19:41:17','AUTHENTICATION_SUCCESS'),
 (487,'sf0009','2016-04-29 19:43:57','AUTHENTICATION_SUCCESS'),
 (488,'sf0009','2016-04-29 19:50:19','AUTHENTICATION_SUCCESS'),
 (489,'sf0021','2016-04-29 19:51:12','AUTHENTICATION_SUCCESS'),
 (490,'admin','2016-04-29 20:02:06','AUTHENTICATION_SUCCESS'),
 (491,'customer','2016-04-29 20:41:02','AUTHENTICATION_SUCCESS'),
 (492,'admin','2016-04-29 20:53:07','AUTHENTICATION_SUCCESS'),
 (493,'sf0016','2016-04-30 15:37:47','AUTHENTICATION_SUCCESS'),
 (494,'admin','2016-04-30 16:46:49','AUTHENTICATION_SUCCESS'),
 (495,'admin','2016-04-30 22:13:01','AUTHENTICATION_SUCCESS'),
 (496,'customer','2016-04-30 22:19:23','AUTHENTICATION_SUCCESS'),
 (497,'sf0023','2016-04-30 22:45:15','AUTHENTICATION_SUCCESS'),
 (498,'customer','2016-04-30 22:52:22','AUTHENTICATION_SUCCESS'),
 (499,'sf0023','2016-04-30 22:56:31','AUTHENTICATION_SUCCESS'),
 (500,'customer','2016-04-30 23:26:23','AUTHENTICATION_SUCCESS'),
 (501,'sf0023','2016-04-30 23:29:24','AUTHENTICATION_SUCCESS'),
 (502,'sf0015','2016-04-30 23:36:43','AUTHENTICATION_SUCCESS'),
 (503,'sf0029','2016-04-30 23:38:24','AUTHENTICATION_SUCCESS'),
 (504,'sf0029','2016-04-30 23:43:08','AUTHENTICATION_SUCCESS'),
 (505,'sf0015','2016-04-30 23:46:34','AUTHENTICATION_SUCCESS'),
 (510,'admin','2016-05-02 20:05:06','AUTHENTICATION_SUCCESS'),
 (511,'admin','2016-05-04 21:37:34','AUTHENTICATION_SUCCESS'),
 (512,'admin','2016-05-04 21:44:12','AUTHENTICATION_SUCCESS'),
 (513,'customer','2016-05-04 21:47:07','AUTHENTICATION_SUCCESS'),
 (514,'sf0015','2016-05-04 21:49:29','AUTHENTICATION_SUCCESS'),
 (515,'sf0029','2016-05-04 21:50:37','AUTHENTICATION_SUCCESS'),
 (516,'sf0020','2016-05-04 21:51:40','AUTHENTICATION_SUCCESS'),
 (517,'sf0015','2016-05-04 21:53:11','AUTHENTICATION_SUCCESS'),
 (518,'sf0027','2016-05-04 21:54:55','AUTHENTICATION_SUCCESS'),
 (519,'sf0014','2016-05-04 21:56:02','AUTHENTICATION_SUCCESS'),
 (520,'sf0020','2016-05-04 21:57:00','AUTHENTICATION_SUCCESS'),
 (521,'sf0021','2016-05-04 21:58:08','AUTHENTICATION_SUCCESS'),
 (522,'admin','2016-05-04 21:59:43','AUTHENTICATION_SUCCESS'),
 (523,'sf0014','2016-05-04 23:41:50','AUTHENTICATION_SUCCESS'),
 (524,'sf0015','2016-05-04 23:42:30','AUTHENTICATION_SUCCESS'),
 (525,'sf0015','2016-05-04 23:45:15','AUTHENTICATION_SUCCESS'),
 (526,'sf0020','2016-05-04 23:49:23','AUTHENTICATION_SUCCESS'),
 (527,'sf0021','2016-05-04 23:51:37','AUTHENTICATION_SUCCESS'),
 (528,'admin','2016-05-04 23:52:43','AUTHENTICATION_SUCCESS'),
 (529,'admin','2016-05-05 00:10:01','AUTHENTICATION_SUCCESS'),
 (530,'billrunusr','2016-05-05 00:42:32','AUTHENTICATION_FAILURE'),
 (531,'billrunuser','2016-05-05 00:42:43','AUTHENTICATION_SUCCESS'),
 (532,'admin','2016-05-05 21:05:56','AUTHENTICATION_SUCCESS'),
 (533,'billrunmgr','2016-05-05 21:08:52','AUTHENTICATION_SUCCESS'),
 (534,'admin','2016-05-05 21:54:19','AUTHENTICATION_SUCCESS'),
 (535,'billrunuser','2016-05-06 16:17:37','AUTHENTICATION_SUCCESS'),
 (536,'admin','2016-05-09 15:57:01','AUTHENTICATION_SUCCESS'),
 (537,'customer','2016-05-09 20:25:52','AUTHENTICATION_SUCCESS'),
 (538,'sf0015','2016-05-09 20:42:28','AUTHENTICATION_SUCCESS'),
 (539,'sf0021','2016-05-09 20:44:02','AUTHENTICATION_SUCCESS'),
 (540,'customer','2016-05-09 20:51:47','AUTHENTICATION_SUCCESS'),
 (541,'sf0015','2016-05-09 20:54:10','AUTHENTICATION_SUCCESS'),
 (542,'sf0021','2016-05-09 20:56:07','AUTHENTICATION_SUCCESS'),
 (543,'customer','2016-05-09 21:04:19','AUTHENTICATION_SUCCESS'),
 (544,'customer','2016-05-09 16:31:05','AUTHENTICATION_SUCCESS'),
 (545,'sf0015','2016-05-09 16:31:42','AUTHENTICATION_SUCCESS'),
 (546,'admin','2016-05-09 16:32:29','AUTHENTICATION_SUCCESS'),
 (547,'customer','2016-05-09 16:34:29','AUTHENTICATION_SUCCESS'),
 (548,'customer','2016-05-09 16:55:21','AUTHENTICATION_SUCCESS'),
 (549,'sf0015','2016-05-09 16:59:19','AUTHENTICATION_SUCCESS'),
 (550,'sf0015','2016-05-09 16:59:55','AUTHENTICATION_SUCCESS'),
 (551,'sf0029','2016-05-09 17:03:38','AUTHENTICATION_SUCCESS'),
 (552,'sf0020','2016-05-09 17:10:37','AUTHENTICATION_SUCCESS'),
 (553,'sf0015','2016-05-09 17:15:50','AUTHENTICATION_SUCCESS'),
 (554,'sf0027','2016-05-09 17:19:49','AUTHENTICATION_SUCCESS'),
 (555,'sf0015','2016-05-09 17:24:45','AUTHENTICATION_SUCCESS'),
 (556,'sf0014','2016-05-09 17:26:16','AUTHENTICATION_SUCCESS'),
 (557,'sf0020','2016-05-09 17:30:10','AUTHENTICATION_SUCCESS'),
 (558,'sf0021','2016-05-09 17:36:30','AUTHENTICATION_SUCCESS'),
 (559,'sf0020','2016-05-09 18:03:35','AUTHENTICATION_SUCCESS'),
 (560,'sf0021','2016-05-09 18:05:26','AUTHENTICATION_SUCCESS'),
 (561,'admin','2016-05-09 18:16:29','AUTHENTICATION_SUCCESS'),
 (562,'billrunmgr','2016-05-09 18:20:56','AUTHENTICATION_FAILURE'),
 (563,'billrunmgr','2016-05-09 18:21:06','AUTHENTICATION_SUCCESS'),
 (564,'sf0015','2016-05-09 18:25:51','AUTHENTICATION_SUCCESS'),
 (565,'sf0021','2016-05-09 18:27:08','AUTHENTICATION_SUCCESS'),
 (566,'billrunmgr','2016-05-09 18:29:06','AUTHENTICATION_SUCCESS'),
 (567,'sf0015','2016-05-09 18:29:38','AUTHENTICATION_SUCCESS'),
 (568,'customer','2016-05-09 18:32:49','AUTHENTICATION_SUCCESS'),
 (569,'sf0015','2016-05-09 18:44:30','AUTHENTICATION_SUCCESS'),
 (570,'sf0029','2016-05-09 18:45:49','AUTHENTICATION_SUCCESS'),
 (571,'sf0020','2016-05-09 18:48:52','AUTHENTICATION_SUCCESS'),
 (572,'sf0015','2016-05-09 18:51:41','AUTHENTICATION_SUCCESS'),
 (573,'sf0027','2016-05-09 19:00:31','AUTHENTICATION_SUCCESS'),
 (574,'sf0014','2016-05-09 19:02:43','AUTHENTICATION_SUCCESS'),
 (575,'sf0020','2016-05-09 19:04:09','AUTHENTICATION_SUCCESS'),
 (576,'sf0021','2016-05-09 19:06:09','AUTHENTICATION_SUCCESS'),
 (577,'sf0021','2016-05-09 19:11:18','AUTHENTICATION_SUCCESS'),
 (578,'billrunmgr','2016-05-09 19:19:26','AUTHENTICATION_SUCCESS'),
 (579,'admin','2016-05-09 19:23:26','AUTHENTICATION_SUCCESS'),
 (580,'admin','2016-05-09 19:33:12','AUTHENTICATION_SUCCESS'),
 (581,'billrunmgr','2016-05-09 19:36:00','AUTHENTICATION_SUCCESS'),
 (582,'admin','2016-05-09 19:46:13','AUTHENTICATION_SUCCESS'),
 (583,'billrunmgr','2016-05-09 19:48:46','AUTHENTICATION_SUCCESS'),
 (584,'sf0021','2016-05-10 10:00:00','AUTHENTICATION_SUCCESS'),
 (585,'sf0021','2016-05-10 10:03:02','AUTHENTICATION_SUCCESS'),
 (586,'billrunmgr','2016-05-10 10:20:26','AUTHENTICATION_SUCCESS'),
 (587,'sf0023','2016-05-10 10:21:08','AUTHENTICATION_SUCCESS'),
 (588,'customer','2016-05-10 10:23:02','AUTHENTICATION_SUCCESS'),
 (589,'sf0023','2016-05-10 10:24:12','AUTHENTICATION_SUCCESS'),
 (590,'sf0023','2016-05-10 10:28:01','AUTHENTICATION_SUCCESS'),
 (591,'admin','2016-05-10 10:34:03','AUTHENTICATION_SUCCESS'),
 (592,'admin','2016-05-10 10:36:12','AUTHENTICATION_SUCCESS'),
 (593,'customer','2016-05-10 10:45:31','AUTHENTICATION_SUCCESS'),
 (594,'customer','2016-05-10 10:49:01','AUTHENTICATION_SUCCESS'),
 (595,'customer','2016-05-10 10:54:46','AUTHENTICATION_FAILURE'),
 (596,'customer','2016-05-10 10:54:57','AUTHENTICATION_SUCCESS'),
 (597,'admin','2016-05-10 11:01:06','AUTHENTICATION_SUCCESS'),
 (598,'customer','2016-05-10 11:06:05','AUTHENTICATION_SUCCESS'),
 (599,'billrunmgr','2016-05-10 11:06:18','AUTHENTICATION_SUCCESS'),
 (600,'admin','2016-05-10 11:10:06','AUTHENTICATION_SUCCESS'),
 (601,'customer','2016-05-10 11:12:08','AUTHENTICATION_SUCCESS'),
 (602,'admin','2016-05-10 11:13:36','AUTHENTICATION_SUCCESS'),
 (603,'admin','2016-05-10 11:23:49','AUTHENTICATION_SUCCESS'),
 (604,'billrunmgr','2016-05-10 11:29:19','AUTHENTICATION_SUCCESS'),
 (605,'customer','2016-05-10 11:37:52','AUTHENTICATION_SUCCESS'),
 (606,'sf0015','2016-05-10 11:45:09','AUTHENTICATION_SUCCESS'),
 (607,'customer','2016-05-10 11:50:18','AUTHENTICATION_SUCCESS'),
 (608,'sf0029','2016-05-10 12:05:19','AUTHENTICATION_SUCCESS'),
 (609,'customer','2016-05-10 12:13:16','AUTHENTICATION_SUCCESS'),
 (610,'sf0015','2016-05-10 12:22:32','AUTHENTICATION_SUCCESS'),
 (611,'sf0029','2016-05-10 12:26:21','AUTHENTICATION_SUCCESS'),
 (612,'sf0020','2016-05-10 12:36:38','AUTHENTICATION_SUCCESS'),
 (613,'sf0015','2016-05-10 12:41:29','AUTHENTICATION_FAILURE'),
 (614,'sf0020','2016-05-10 12:41:35','AUTHENTICATION_SUCCESS'),
 (615,'billrunmgr','2016-05-10 12:43:18','AUTHENTICATION_SUCCESS'),
 (616,'customer','2016-05-10 13:54:21','AUTHENTICATION_SUCCESS'),
 (617,'customer','2016-05-10 13:56:38','AUTHENTICATION_SUCCESS'),
 (618,'customer','2016-05-10 13:57:52','AUTHENTICATION_SUCCESS'),
 (619,'customer','2016-05-10 13:59:31','AUTHENTICATION_FAILURE'),
 (620,'customer','2016-05-10 13:59:34','AUTHENTICATION_SUCCESS'),
 (621,'customer','2016-05-10 14:02:36','AUTHENTICATION_SUCCESS'),
 (622,'sf0023','2016-05-10 14:04:11','AUTHENTICATION_SUCCESS'),
 (623,'sf0023','2016-05-10 14:06:46','AUTHENTICATION_SUCCESS'),
 (624,'sf0015','2016-05-10 14:08:47','AUTHENTICATION_SUCCESS'),
 (625,'customer','2016-05-10 14:11:01','AUTHENTICATION_SUCCESS'),
 (626,'sf0023','2016-05-10 14:15:32','AUTHENTICATION_SUCCESS'),
 (627,'customer','2016-05-10 14:16:44','AUTHENTICATION_SUCCESS'),
 (628,'customer','2016-05-10 14:20:07','AUTHENTICATION_SUCCESS'),
 (629,'customer','2016-05-10 15:13:14','AUTHENTICATION_SUCCESS'),
 (630,'sf0015','2016-05-10 15:15:35','AUTHENTICATION_SUCCESS'),
 (631,'customer','2016-05-10 15:16:27','AUTHENTICATION_SUCCESS'),
 (632,'sf0023','2016-05-10 15:18:10','AUTHENTICATION_SUCCESS'),
 (633,'admin','2016-05-10 17:08:22','AUTHENTICATION_SUCCESS'),
 (634,'admin','2016-05-10 17:35:11','AUTHENTICATION_SUCCESS'),
 (635,'customer','2016-05-10 17:48:48','AUTHENTICATION_SUCCESS'),
 (636,'sf0015','2016-05-10 17:58:58','AUTHENTICATION_SUCCESS'),
 (637,'sf0029','2016-05-10 18:01:08','AUTHENTICATION_SUCCESS'),
 (638,'sf0020','2016-05-10 18:04:00','AUTHENTICATION_SUCCESS'),
 (639,'sf0015','2016-05-10 18:08:36','AUTHENTICATION_SUCCESS'),
 (640,'sf0027','2016-05-10 18:12:52','AUTHENTICATION_SUCCESS'),
 (641,'sf0014','2016-05-10 18:15:10','AUTHENTICATION_SUCCESS'),
 (642,'sf0020','2016-05-10 18:18:19','AUTHENTICATION_SUCCESS'),
 (643,'sf0021','2016-05-10 18:21:03','AUTHENTICATION_SUCCESS'),
 (644,'billrunuser','2016-05-10 18:26:27','AUTHENTICATION_SUCCESS'),
 (645,'admin','2016-05-10 18:28:24','AUTHENTICATION_SUCCESS'),
 (646,'billrunmgr','2016-05-10 18:32:08','AUTHENTICATION_SUCCESS'),
 (647,'admin','2016-05-10 18:42:02','AUTHENTICATION_SUCCESS'),
 (648,'billrunmgr','2016-05-10 18:44:17','AUTHENTICATION_SUCCESS'),
 (649,'customer','2016-05-10 18:46:43','AUTHENTICATION_SUCCESS'),
 (650,'sf0023','2016-05-10 18:49:20','AUTHENTICATION_SUCCESS'),
 (651,'sf0017','2016-05-10 18:51:50','AUTHENTICATION_SUCCESS'),
 (652,'sf0009','2016-05-10 18:54:33','AUTHENTICATION_SUCCESS'),
 (653,'customer','2016-05-10 18:56:35','AUTHENTICATION_SUCCESS'),
 (654,'sf0023','2016-05-10 18:58:07','AUTHENTICATION_SUCCESS'),
 (655,'sf0015','2016-05-10 18:59:27','AUTHENTICATION_SUCCESS'),
 (656,'sf0029','2016-05-10 19:01:26','AUTHENTICATION_SUCCESS'),
 (657,'sf0023','2016-05-10 19:03:20','AUTHENTICATION_SUCCESS'),
 (658,'admin','2016-05-10 19:05:04','AUTHENTICATION_SUCCESS'),
 (659,'customer','2016-05-11 09:47:08','AUTHENTICATION_SUCCESS'),
 (660,'sf0023','2016-05-11 09:48:29','AUTHENTICATION_SUCCESS'),
 (661,'sf0017','2016-05-11 09:50:26','AUTHENTICATION_SUCCESS'),
 (662,'customer','2016-05-11 09:53:06','AUTHENTICATION_SUCCESS'),
 (663,'billrunmgr','2016-05-11 10:15:51','AUTHENTICATION_SUCCESS'),
 (664,'admin','2016-05-11 10:20:07','AUTHENTICATION_SUCCESS'),
 (665,'billrunmgr','2016-05-11 10:25:07','AUTHENTICATION_SUCCESS'),
 (666,'sf0015','2016-05-11 10:26:50','AUTHENTICATION_SUCCESS'),
 (667,'customer','2016-05-11 10:29:08','AUTHENTICATION_SUCCESS'),
 (668,'sf0023','2016-05-11 10:30:58','AUTHENTICATION_SUCCESS'),
 (669,'sf0015','2016-05-11 10:32:19','AUTHENTICATION_SUCCESS'),
 (670,'sf0029','2016-05-11 10:33:48','AUTHENTICATION_SUCCESS'),
 (671,'billrunmgr','2016-05-11 10:38:21','AUTHENTICATION_SUCCESS'),
 (672,'customer','2016-05-11 10:40:17','AUTHENTICATION_SUCCESS'),
 (673,'admin','2016-05-11 10:48:25','AUTHENTICATION_SUCCESS'),
 (674,'admin','2016-05-11 10:54:20','AUTHENTICATION_SUCCESS'),
 (675,'billrunmgr','2016-05-11 10:56:30','AUTHENTICATION_SUCCESS'),
 (676,'admin','2016-05-11 11:02:17','AUTHENTICATION_SUCCESS'),
 (677,'sf0015','2016-05-11 11:06:35','AUTHENTICATION_SUCCESS'),
 (678,'sf0021','2016-05-11 11:10:16','AUTHENTICATION_SUCCESS'),
 (679,'admin','2016-05-11 11:12:58','AUTHENTICATION_SUCCESS'),
 (680,'sf0021','2016-05-11 11:13:07','AUTHENTICATION_SUCCESS'),
 (681,'admin','2016-05-11 11:15:11','AUTHENTICATION_SUCCESS'),
 (682,'billrunmgr','2016-05-11 11:16:58','AUTHENTICATION_SUCCESS'),
 (683,'sf0021','2016-05-11 11:17:46','AUTHENTICATION_SUCCESS'),
 (684,'customer','2016-05-11 11:19:40','AUTHENTICATION_SUCCESS'),
 (685,'customer','2016-05-11 11:28:14','AUTHENTICATION_SUCCESS'),
 (686,'customer','2016-05-11 11:48:32','AUTHENTICATION_SUCCESS'),
 (687,'customer','2016-05-11 11:50:16','AUTHENTICATION_SUCCESS'),
 (688,'customer','2016-05-11 11:56:31','AUTHENTICATION_SUCCESS'),
 (689,'admin','2016-05-11 12:24:18','AUTHENTICATION_SUCCESS'),
 (690,'admin','2016-05-11 12:26:12','AUTHENTICATION_SUCCESS'),
 (691,'billrunmgr','2016-05-11 12:30:52','AUTHENTICATION_SUCCESS'),
 (692,'customer','2016-05-11 12:33:40','AUTHENTICATION_SUCCESS'),
 (693,'customer','2016-05-11 12:38:00','AUTHENTICATION_SUCCESS'),
 (694,'customer','2016-05-11 12:55:58','AUTHENTICATION_SUCCESS'),
 (695,'customer','2016-05-11 14:11:08','AUTHENTICATION_FAILURE'),
 (696,'customer','2016-05-11 14:11:16','AUTHENTICATION_SUCCESS'),
 (697,'customer','2016-05-11 14:12:44','AUTHENTICATION_FAILURE'),
 (698,'customer','2016-05-11 14:12:50','AUTHENTICATION_SUCCESS'),
 (699,'customer','2016-05-11 14:24:51','AUTHENTICATION_SUCCESS'),
 (700,'customer','2016-05-11 14:30:16','AUTHENTICATION_SUCCESS'),
 (701,'admin','2016-05-11 14:48:26','AUTHENTICATION_SUCCESS'),
 (702,'billrunmgr','2016-05-11 14:55:45','AUTHENTICATION_SUCCESS'),
 (703,'customer','2016-05-11 15:00:13','AUTHENTICATION_SUCCESS'),
 (704,'sf0015','2016-05-11 15:09:09','AUTHENTICATION_SUCCESS'),
 (705,'sf0029','2016-05-11 15:10:58','AUTHENTICATION_SUCCESS'),
 (706,'sf0020','2016-05-11 15:13:30','AUTHENTICATION_SUCCESS'),
 (707,'sf0015','2016-05-11 15:16:42','AUTHENTICATION_SUCCESS'),
 (708,'sf0027','2016-05-11 15:18:38','AUTHENTICATION_SUCCESS'),
 (709,'s0015','2016-05-11 15:20:10','AUTHENTICATION_FAILURE'),
 (710,'sf0015','2016-05-11 15:20:21','AUTHENTICATION_SUCCESS'),
 (711,'sf0014','2016-05-11 15:20:45','AUTHENTICATION_SUCCESS'),
 (712,'sf0020','2016-05-11 15:22:55','AUTHENTICATION_SUCCESS'),
 (713,'sf0021','2016-05-11 15:25:22','AUTHENTICATION_SUCCESS'),
 (714,'admin','2016-05-11 15:30:00','AUTHENTICATION_SUCCESS'),
 (715,'billrunmgr','2016-05-11 15:36:50','AUTHENTICATION_SUCCESS'),
 (716,'admin','2016-05-11 15:57:42','AUTHENTICATION_SUCCESS'),
 (717,'admin','2016-05-11 16:09:25','AUTHENTICATION_SUCCESS'),
 (718,'billrunmgr','2016-05-11 16:25:20','AUTHENTICATION_FAILURE'),
 (719,'admin','2016-05-11 16:25:30','AUTHENTICATION_SUCCESS'),
 (720,'customer','2016-05-11 16:27:55','AUTHENTICATION_SUCCESS'),
 (721,'admin','2016-05-11 16:37:34','AUTHENTICATION_SUCCESS'),
 (722,'admin','2016-05-11 16:44:38','AUTHENTICATION_SUCCESS'),
 (723,'admin','2016-05-11 16:52:06','AUTHENTICATION_SUCCESS'),
 (724,'admin','2016-05-11 18:23:17','AUTHENTICATION_SUCCESS'),
 (725,'billrunmgr','2016-05-11 18:26:26','AUTHENTICATION_SUCCESS'),
 (726,'admin','2016-05-11 18:29:03','AUTHENTICATION_SUCCESS'),
 (727,'admin','2016-05-11 18:30:42','AUTHENTICATION_SUCCESS'),
 (728,'billrunmgr','2016-05-11 18:47:38','AUTHENTICATION_SUCCESS'),
 (729,'billrunmgr','2016-05-11 18:54:28','AUTHENTICATION_SUCCESS'),
 (730,'admin','2016-05-11 18:56:23','AUTHENTICATION_SUCCESS'),
 (731,'admin','2016-05-11 19:07:43','AUTHENTICATION_SUCCESS'),
 (732,'billrunmgr','2016-05-11 19:10:53','AUTHENTICATION_SUCCESS'),
 (733,'customer','2016-05-12 09:54:34','AUTHENTICATION_SUCCESS'),
 (734,'customer','2016-05-12 09:56:21','AUTHENTICATION_SUCCESS'),
 (735,'customer','2016-05-12 10:05:50','AUTHENTICATION_SUCCESS'),
 (736,'customer','2016-05-12 10:24:27','AUTHENTICATION_FAILURE'),
 (737,'sf0015','2016-05-12 10:24:35','AUTHENTICATION_SUCCESS'),
 (738,'sf0029','2016-05-12 10:30:44','AUTHENTICATION_SUCCESS'),
 (739,'sf0020','2016-05-12 10:33:12','AUTHENTICATION_SUCCESS'),
 (740,'billrunmgr','2016-05-12 10:44:38','AUTHENTICATION_SUCCESS'),
 (741,'admin','2016-05-12 10:46:25','AUTHENTICATION_SUCCESS'),
 (742,'billrunmgr','2016-05-12 10:47:55','AUTHENTICATION_SUCCESS'),
 (743,'admin','2016-05-12 14:44:01','AUTHENTICATION_SUCCESS'),
 (744,'billrunmgr','2016-05-12 14:45:59','AUTHENTICATION_SUCCESS'),
 (745,'admin','2016-05-12 14:47:10','AUTHENTICATION_FAILURE'),
 (746,'admin','2016-05-12 14:47:14','AUTHENTICATION_SUCCESS'),
 (747,'billrunmgr','2016-05-12 14:48:32','AUTHENTICATION_SUCCESS'),
 (748,'admin','2016-05-12 14:49:00','AUTHENTICATION_SUCCESS'),
 (749,'billrunmgr','2016-05-12 14:50:14','AUTHENTICATION_SUCCESS'),
 (750,'admin','2016-05-12 14:56:31','AUTHENTICATION_SUCCESS'),
 (751,'billrunmgr','2016-05-12 15:15:02','AUTHENTICATION_SUCCESS'),
 (752,'billrunmgr','2016-05-12 15:15:16','AUTHENTICATION_SUCCESS'),
 (753,'billrunmgr','2016-05-12 15:15:16','AUTHENTICATION_SUCCESS'),
 (754,'admin','2016-05-12 15:36:12','AUTHENTICATION_SUCCESS'),
 (755,'billrunmgr','2016-05-12 15:52:23','AUTHENTICATION_SUCCESS'),
 (756,'admin','2016-05-12 15:52:35','AUTHENTICATION_SUCCESS'),
 (757,'billrunmgr','2016-05-12 16:28:21','AUTHENTICATION_SUCCESS'),
 (758,'admin','2016-05-12 16:29:40','AUTHENTICATION_SUCCESS'),
 (759,'billrunmgr','2016-05-12 16:32:15','AUTHENTICATION_SUCCESS'),
 (760,'admin','2016-05-12 17:21:56','AUTHENTICATION_SUCCESS'),
 (761,'billrunmgr','2016-05-12 17:23:39','AUTHENTICATION_SUCCESS'),
 (762,'admin','2016-05-12 17:24:19','AUTHENTICATION_SUCCESS'),
 (763,'billrunmgr','2016-05-12 17:25:48','AUTHENTICATION_SUCCESS'),
 (764,'billrunmgr','2016-05-12 18:45:48','AUTHENTICATION_SUCCESS'),
 (765,'admin','2016-05-12 18:57:20','AUTHENTICATION_SUCCESS'),
 (766,'billrunmgr','2016-05-12 18:58:13','AUTHENTICATION_SUCCESS'),
 (767,'billrunmgr','2016-05-12 18:59:42','AUTHENTICATION_SUCCESS'),
 (768,'admin','2016-05-12 18:59:54','AUTHENTICATION_SUCCESS'),
 (769,'billrunmgr','2016-05-12 19:00:47','AUTHENTICATION_SUCCESS'),
 (770,'admin','2016-05-12 19:03:11','AUTHENTICATION_SUCCESS'),
 (771,'billrunmgr','2016-05-12 19:03:50','AUTHENTICATION_SUCCESS'),
 (772,'admin','2016-05-13 07:47:42','AUTHENTICATION_SUCCESS'),
 (773,'billrunmgr','2016-05-13 07:54:53','AUTHENTICATION_SUCCESS'),
 (774,'admin','2016-05-13 07:59:00','AUTHENTICATION_SUCCESS'),
 (775,'billrunmgr','2016-05-13 07:59:39','AUTHENTICATION_SUCCESS'),
 (776,'admin','2016-05-13 08:00:05','AUTHENTICATION_SUCCESS'),
 (777,'billrunmgr','2016-05-13 08:00:57','AUTHENTICATION_SUCCESS'),
 (778,'billrunmgr','2016-05-13 08:01:57','AUTHENTICATION_SUCCESS'),
 (779,'admin','2016-05-13 08:02:21','AUTHENTICATION_SUCCESS'),
 (780,'billrunmgr','2016-05-13 08:02:56','AUTHENTICATION_SUCCESS'),
 (781,'admin','2016-05-13 08:04:03','AUTHENTICATION_SUCCESS'),
 (782,'billrunmgr','2016-05-13 08:04:20','AUTHENTICATION_SUCCESS'),
 (783,'admin','2016-05-13 08:04:50','AUTHENTICATION_SUCCESS'),
 (784,'billrunmgr','2016-05-13 08:05:25','AUTHENTICATION_SUCCESS'),
 (785,'billrunmgr','2016-05-13 08:24:09','AUTHENTICATION_SUCCESS'),
 (786,'admin','2016-05-13 09:56:33','AUTHENTICATION_SUCCESS'),
 (787,'billrunmgr','2016-05-13 09:58:30','AUTHENTICATION_SUCCESS'),
 (788,'admin','2016-05-13 10:04:14','AUTHENTICATION_SUCCESS'),
 (789,'billrunmgr','2016-05-13 10:06:27','AUTHENTICATION_SUCCESS'),
 (790,'billrunmgr','2016-05-13 10:08:18','AUTHENTICATION_SUCCESS'),
 (791,'admin','2016-05-13 10:08:51','AUTHENTICATION_SUCCESS'),
 (792,'billrunmgr','2016-05-13 10:12:24','AUTHENTICATION_SUCCESS'),
 (793,'admin','2016-05-13 10:20:38','AUTHENTICATION_SUCCESS'),
 (794,'billrunmgr','2016-05-13 10:24:21','AUTHENTICATION_SUCCESS'),
 (795,'admin','2016-05-13 10:28:02','AUTHENTICATION_SUCCESS'),
 (796,'billrunmgr','2016-05-13 11:06:19','AUTHENTICATION_SUCCESS'),
 (797,'admin','2016-05-13 11:09:52','AUTHENTICATION_SUCCESS'),
 (798,'admin','2016-05-17 09:57:07','AUTHENTICATION_SUCCESS'),
 (799,'admin','2016-05-17 10:08:12','AUTHENTICATION_SUCCESS'),
 (800,'admin','2016-05-17 10:14:32','AUTHENTICATION_SUCCESS'),
 (801,'admin','2016-05-17 10:47:10','AUTHENTICATION_SUCCESS'),
 (802,'admin','2016-05-17 10:55:23','AUTHENTICATION_SUCCESS'),
 (803,'admin','2016-05-18 08:18:52','AUTHENTICATION_SUCCESS'),
 (804,'admin','2016-05-18 08:20:09','AUTHENTICATION_SUCCESS'),
 (805,'admin','2016-05-18 12:02:14','AUTHENTICATION_SUCCESS'),
 (806,'sf0015','2016-05-18 12:02:28','AUTHENTICATION_SUCCESS'),
 (807,'customer','2016-05-18 12:02:53','AUTHENTICATION_SUCCESS'),
 (808,'admin','2016-05-18 12:09:11','AUTHENTICATION_SUCCESS'),
 (809,'customer','2016-05-18 12:52:23','AUTHENTICATION_SUCCESS'),
 (810,'sf0015','2016-05-18 12:56:48','AUTHENTICATION_SUCCESS'),
 (811,'customer','2016-05-18 13:00:21','AUTHENTICATION_SUCCESS'),
 (812,'sf0015','2016-05-18 13:03:16','AUTHENTICATION_SUCCESS'),
 (813,'customer','2016-05-18 13:46:52','AUTHENTICATION_SUCCESS'),
 (814,'sf0029','2016-05-18 13:47:50','AUTHENTICATION_SUCCESS'),
 (815,'sf0015','2016-05-18 13:49:55','AUTHENTICATION_SUCCESS'),
 (816,'sf0029','2016-05-18 14:27:50','AUTHENTICATION_SUCCESS'),
 (817,'sf0020','2016-05-18 14:29:42','AUTHENTICATION_SUCCESS'),
 (818,'sf0015','2016-05-18 15:23:06','AUTHENTICATION_SUCCESS'),
 (819,'sf0027','2016-05-18 15:33:21','AUTHENTICATION_SUCCESS'),
 (820,'sf0014','2016-05-18 15:43:10','AUTHENTICATION_SUCCESS'),
 (821,'sf0020','2016-05-18 15:49:40','AUTHENTICATION_SUCCESS'),
 (822,'sf0021','2016-05-18 15:56:07','AUTHENTICATION_SUCCESS'),
 (823,'customer','2016-05-18 16:09:31','AUTHENTICATION_SUCCESS'),
 (824,'sf0015','2016-05-18 16:47:03','AUTHENTICATION_SUCCESS'),
 (825,'sf0027','2016-05-18 16:52:04','AUTHENTICATION_SUCCESS'),
 (826,'customer','2016-05-18 17:36:51','AUTHENTICATION_SUCCESS'),
 (827,'customer','2016-05-18 18:11:54','AUTHENTICATION_SUCCESS'),
 (828,'sf0015','2016-05-18 18:24:53','AUTHENTICATION_SUCCESS'),
 (829,'customer','2016-05-18 18:46:12','AUTHENTICATION_SUCCESS'),
 (830,'sf0015','2016-05-18 18:48:44','AUTHENTICATION_SUCCESS'),
 (831,'sf0015','2016-05-18 19:16:21','AUTHENTICATION_SUCCESS'),
 (832,'customer','2016-05-18 19:18:55','AUTHENTICATION_SUCCESS'),
 (833,'sf0015','2016-05-18 19:21:34','AUTHENTICATION_SUCCESS'),
 (834,'sf0021','2016-05-18 19:23:46','AUTHENTICATION_SUCCESS');
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
 (250,'sessionId','BA6CC01D47B22FC319DE0F8FEE9C047E'),
 (251,'remoteAddress','0:0:0:0:0:0:0:1'),
 (251,'sessionId','25A552732CBDBA9EA03106767FE04312'),
 (252,'remoteAddress','0:0:0:0:0:0:0:1'),
 (252,'sessionId','6F14C4CBDCA2FEA7AF0EFEA115C283FD'),
 (253,'remoteAddress','0:0:0:0:0:0:0:1'),
 (253,'sessionId','D0EFDE53BF33DB4D6FE0A7355B59B850'),
 (254,'remoteAddress','0:0:0:0:0:0:0:1'),
 (254,'sessionId','34F464957ECD9A0C7113E52EC692137A'),
 (255,'remoteAddress','0:0:0:0:0:0:0:1'),
 (255,'sessionId','0F8E1F4B6BB12D9EA3ECA09FA5C8F3EF'),
 (256,'remoteAddress','0:0:0:0:0:0:0:1'),
 (256,'sessionId','1A62FEF114962C44B3D8A93CD3A5FC7C'),
 (257,'remoteAddress','0:0:0:0:0:0:0:1'),
 (257,'sessionId','9521A3DDE9FE55B4AECB46DDD5FDFAF5'),
 (258,'remoteAddress','0:0:0:0:0:0:0:1'),
 (258,'sessionId','8D9E16A4EE513EF01796E8AF38C86911'),
 (259,'remoteAddress','0:0:0:0:0:0:0:1'),
 (259,'sessionId','9FD08254D28BCF23E067DB984E8C3C68'),
 (260,'remoteAddress','0:0:0:0:0:0:0:1'),
 (260,'sessionId','203C2F1171777CD73C14C620F821F780'),
 (261,'remoteAddress','0:0:0:0:0:0:0:1'),
 (261,'sessionId','10F1C5C66A5736C7B5CC6688B177C8A9'),
 (262,'message','Bad credentials'),
 (262,'type','org.springframework.security.authentication.BadCredentialsException'),
 (263,'remoteAddress','0:0:0:0:0:0:0:1'),
 (263,'sessionId','DADF6A033526C4E4C1458890D7BF97A0'),
 (264,'remoteAddress','0:0:0:0:0:0:0:1'),
 (264,'sessionId','2A86C935F8CBC129B22A2275D26FBF64'),
 (265,'remoteAddress','0:0:0:0:0:0:0:1'),
 (265,'sessionId','163B5E59F3327A0CAAD5E9CF086E32EB'),
 (266,'message','Bad credentials'),
 (266,'type','org.springframework.security.authentication.BadCredentialsException'),
 (267,'remoteAddress','0:0:0:0:0:0:0:1'),
 (267,'sessionId','83741110A7949E0D25E4CFF072E38981'),
 (268,'remoteAddress','127.0.0.1'),
 (268,'sessionId','4F715896F2A61480F1DB30B2D7E8DDEF'),
 (269,'remoteAddress','0:0:0:0:0:0:0:1'),
 (269,'sessionId','210F9EF380835A5C1DFCE01FCE1C5F73'),
 (270,'remoteAddress','0:0:0:0:0:0:0:1'),
 (270,'sessionId','B001458BA1E366FB5CB144E67634C0B6'),
 (271,'remoteAddress','0:0:0:0:0:0:0:1'),
 (271,'sessionId','7C95A85901394A81927CA9BF2DF5B870'),
 (272,'remoteAddress','127.0.0.1'),
 (272,'sessionId','19673A292546AC53D2A42A6CCF902738'),
 (273,'remoteAddress','127.0.0.1'),
 (273,'sessionId','D2ED26A476F9582037C2468A22DC1680'),
 (274,'remoteAddress','127.0.0.1'),
 (274,'sessionId','07B90ACCD94F4A27E744A8AAAFBBD649'),
 (275,'remoteAddress','127.0.0.1'),
 (275,'sessionId','AAFD3F578434CCB08970EEA6BD6FACC2'),
 (276,'remoteAddress','127.0.0.1'),
 (276,'sessionId','7ECFF94F9EB43C401514F3473B417053'),
 (277,'remoteAddress','127.0.0.1'),
 (277,'sessionId','DFC1EF484AB8F419A73A15E869BE3663'),
 (278,'remoteAddress','127.0.0.1'),
 (278,'sessionId','B1681098F4F05E22A158D9EE2719E097'),
 (279,'remoteAddress','0:0:0:0:0:0:0:1'),
 (279,'sessionId','6D0CB8FA672262CB60DB36FBD3178BEF'),
 (280,'remoteAddress','0:0:0:0:0:0:0:1'),
 (280,'sessionId','7EFD515A0EF759E6FFD7F817DB7330D4'),
 (281,'remoteAddress','0:0:0:0:0:0:0:1'),
 (281,'sessionId','DF5261AC82EF7DCA915EA853664E90CF'),
 (282,'remoteAddress','0:0:0:0:0:0:0:1'),
 (282,'sessionId','EC58296801B663902BC6AAC3458E6628'),
 (283,'remoteAddress','0:0:0:0:0:0:0:1'),
 (283,'sessionId','3F97890787CCAF8E23795171CB31A282'),
 (284,'remoteAddress','127.0.0.1'),
 (284,'sessionId','6FD630A2C12240CDF4DB4118AB19EE51'),
 (285,'remoteAddress','127.0.0.1'),
 (285,'sessionId','A1F21ABBF24E17D682A79F2E818B4646'),
 (286,'remoteAddress','127.0.0.1'),
 (286,'sessionId','CBFFAFFE0ABE6BD6062C898F3D6F0508'),
 (287,'remoteAddress','127.0.0.1'),
 (287,'sessionId','922AD6C2289B842979270A8529FE3463'),
 (288,'remoteAddress','127.0.0.1'),
 (288,'sessionId','9253E0EF532CE330876FD81F674F887D'),
 (289,'remoteAddress','0:0:0:0:0:0:0:1'),
 (289,'sessionId','6C355DFD7859A66D16B0A2C9154B58D2'),
 (290,'remoteAddress','0:0:0:0:0:0:0:1'),
 (290,'sessionId','C101D64891946F57E2E008BBCA50456B'),
 (291,'remoteAddress','0:0:0:0:0:0:0:1'),
 (291,'sessionId','5E0D2A833E4AE9AA40571106C6ADE614'),
 (292,'remoteAddress','0:0:0:0:0:0:0:1'),
 (292,'sessionId','0049AD4D7F13AD61A7BB96B00AB0BDC9'),
 (293,'remoteAddress','0:0:0:0:0:0:0:1'),
 (293,'sessionId','18740D8F7B045B3B4E5520F05871C13F'),
 (294,'remoteAddress','0:0:0:0:0:0:0:1'),
 (294,'sessionId','6D393994D2A70A427291206B94170040'),
 (295,'remoteAddress','0:0:0:0:0:0:0:1'),
 (295,'sessionId','2C30DE75B8346DA0FB633D10687FF370'),
 (296,'remoteAddress','0:0:0:0:0:0:0:1'),
 (296,'sessionId','4945FBE31F56C166EB2F7F66A7235960'),
 (297,'remoteAddress','0:0:0:0:0:0:0:1'),
 (297,'sessionId','A7F33561786A9A9498214652EF5AAFB6'),
 (298,'remoteAddress','0:0:0:0:0:0:0:1'),
 (298,'sessionId','6D21A0A107AB497036F38822DF3019B2'),
 (299,'remoteAddress','0:0:0:0:0:0:0:1'),
 (299,'sessionId','DC4BCD4C9CAD397114A90283F6CD0EF4'),
 (300,'remoteAddress','0:0:0:0:0:0:0:1'),
 (300,'sessionId','66B8729D086BC6A3CAF9E29BA5D02DBB'),
 (301,'remoteAddress','0:0:0:0:0:0:0:1'),
 (301,'sessionId','D9239A9C8F9A5DB94FF28D6B3D4E3CD7'),
 (302,'remoteAddress','0:0:0:0:0:0:0:1'),
 (302,'sessionId','7A80F2747E789302AE7AEB41D7937FA6'),
 (303,'remoteAddress','0:0:0:0:0:0:0:1'),
 (303,'sessionId','0AA80D72F8B32E63BDB46ECF6E762435'),
 (304,'message','Bad credentials'),
 (304,'type','org.springframework.security.authentication.BadCredentialsException'),
 (305,'remoteAddress','0:0:0:0:0:0:0:1'),
 (305,'sessionId','273EB5F1A4C7B8171617A5136DEB7ED1'),
 (306,'remoteAddress','0:0:0:0:0:0:0:1'),
 (306,'sessionId','703E44E2351A104800BAFD5D4C3F0FAD'),
 (307,'remoteAddress','0:0:0:0:0:0:0:1'),
 (307,'sessionId','7254CABF4255E21CF2AD15F6E5660CD3'),
 (308,'remoteAddress','0:0:0:0:0:0:0:1'),
 (308,'sessionId','8A6577C5415D4ECCA271302E2FF09723'),
 (309,'remoteAddress','0:0:0:0:0:0:0:1'),
 (309,'sessionId','C787CB233310712312D6B4367D64320F'),
 (310,'remoteAddress','0:0:0:0:0:0:0:1'),
 (310,'sessionId','9DE82C3900C2689A1B330FB394C83587'),
 (311,'remoteAddress','0:0:0:0:0:0:0:1'),
 (311,'sessionId','8C9DF7764BB5F76331FD09B94E0542C0'),
 (312,'remoteAddress','0:0:0:0:0:0:0:1'),
 (312,'sessionId','2F2C8288C3F6902409842477E3CA9A91'),
 (313,'remoteAddress','0:0:0:0:0:0:0:1'),
 (313,'sessionId','490A74A0C2F737528F8405F605BC032F'),
 (314,'remoteAddress','0:0:0:0:0:0:0:1'),
 (314,'sessionId','4F3E502C6B2BAA7D273BF4BEDECA7FD2'),
 (315,'remoteAddress','0:0:0:0:0:0:0:1'),
 (315,'sessionId','7E163D42816608A071526DCF66FA6251'),
 (316,'remoteAddress','0:0:0:0:0:0:0:1'),
 (316,'sessionId','1903E0005826676F779BFE5DBBD4CF79'),
 (317,'remoteAddress','0:0:0:0:0:0:0:1'),
 (317,'sessionId','D3549569B7838E8A3F85413733FA3E3B'),
 (318,'remoteAddress','0:0:0:0:0:0:0:1'),
 (318,'sessionId','9B54A04ADD5BE27E80F86539365307B3'),
 (319,'remoteAddress','0:0:0:0:0:0:0:1'),
 (319,'sessionId','C95807D88D4AF0465D03AC34708BB614'),
 (320,'remoteAddress','0:0:0:0:0:0:0:1'),
 (320,'sessionId','B1B44C550403E983DF85086F5B04536D'),
 (321,'remoteAddress','0:0:0:0:0:0:0:1'),
 (321,'sessionId','E4B4966A4C0358BAF643A7A144B96420'),
 (322,'remoteAddress','0:0:0:0:0:0:0:1'),
 (322,'sessionId','54ECCBDC9E4C34D0EE88425BC0C639BE'),
 (323,'remoteAddress','0:0:0:0:0:0:0:1'),
 (323,'sessionId','C63A3A81C4404C2EFCB9A61C2668D4B9'),
 (324,'remoteAddress','0:0:0:0:0:0:0:1'),
 (324,'sessionId','E08070B40FE7F69AA725C83710B5A5D1'),
 (325,'remoteAddress','0:0:0:0:0:0:0:1'),
 (325,'sessionId','18566FB626ACAA1D526221049A0E734B'),
 (326,'remoteAddress','0:0:0:0:0:0:0:1'),
 (326,'sessionId','3A545C33D2C480B2F51844120E3AD29D'),
 (327,'remoteAddress','0:0:0:0:0:0:0:1'),
 (327,'sessionId','E715F77F3F94E2E1A0575A7C49200F9A'),
 (328,'remoteAddress','0:0:0:0:0:0:0:1'),
 (328,'sessionId','AA70F43A2610E81BEFF44FEE2FC07873'),
 (329,'remoteAddress','0:0:0:0:0:0:0:1'),
 (329,'sessionId','4A0C120A0C7AC52442EC4220A34E6327'),
 (330,'remoteAddress','0:0:0:0:0:0:0:1'),
 (330,'sessionId','AA6A85C7DFD59DA480972582D7056333'),
 (331,'remoteAddress','0:0:0:0:0:0:0:1'),
 (331,'sessionId','28BD86292C8EDE4DE514C0B74E50B806'),
 (332,'remoteAddress','0:0:0:0:0:0:0:1'),
 (332,'sessionId','A8D8F5F39034A2B2C3978AF14D7695E9'),
 (333,'remoteAddress','0:0:0:0:0:0:0:1'),
 (333,'sessionId','9852D06328D5E53F4AA7BF0060D7FE3A'),
 (334,'remoteAddress','0:0:0:0:0:0:0:1'),
 (334,'sessionId','C7CFAFF76BF57FE45ED5189B6B97869C'),
 (335,'remoteAddress','0:0:0:0:0:0:0:1'),
 (335,'sessionId','49139F9008150D621D4A5F1D2039DD6E'),
 (336,'remoteAddress','0:0:0:0:0:0:0:1'),
 (336,'sessionId','883131F37FE3735E962B43835C1FBDD7'),
 (337,'remoteAddress','0:0:0:0:0:0:0:1'),
 (337,'sessionId','DD33CB6BA37F3B1FAD6F2B8736CF2981'),
 (338,'remoteAddress','0:0:0:0:0:0:0:1'),
 (338,'sessionId','02C1252DA56785BF057CB655E55D90FF'),
 (339,'remoteAddress','0:0:0:0:0:0:0:1'),
 (339,'sessionId','CE5566C0AE7B0B06843240DE6048A932'),
 (340,'remoteAddress','0:0:0:0:0:0:0:1'),
 (340,'sessionId','9A47CA9763D5110EE9860573544C2A4E'),
 (341,'remoteAddress','0:0:0:0:0:0:0:1'),
 (341,'sessionId','CB329C7E43DA1A7660C5E11AAB74D723'),
 (342,'remoteAddress','0:0:0:0:0:0:0:1'),
 (342,'sessionId','64F8A9799E15FE47CEB2D12967AF36B0'),
 (343,'remoteAddress','0:0:0:0:0:0:0:1'),
 (343,'sessionId','6275471BC6CA612E70094F4406B3F741'),
 (344,'remoteAddress','0:0:0:0:0:0:0:1'),
 (344,'sessionId','4223973B6E1DFCDDEC5710F4E0A7C1EC'),
 (345,'remoteAddress','0:0:0:0:0:0:0:1'),
 (345,'sessionId','916724C0904950C85F0D4ED0F154B464'),
 (346,'remoteAddress','0:0:0:0:0:0:0:1'),
 (346,'sessionId','D09CBC871F406D225DB8943BD7956FBE'),
 (347,'message','Bad credentials'),
 (347,'type','org.springframework.security.authentication.BadCredentialsException'),
 (348,'remoteAddress','0:0:0:0:0:0:0:1'),
 (348,'sessionId','BC17EE6A502EBB4CDAC66E5DE9C5F446'),
 (349,'remoteAddress','0:0:0:0:0:0:0:1'),
 (349,'sessionId','BA02A48DC73DE073BEDE12D9277AF330'),
 (350,'remoteAddress','0:0:0:0:0:0:0:1'),
 (350,'sessionId','94A8428405B6B785528045BECBC7E985'),
 (351,'remoteAddress','0:0:0:0:0:0:0:1'),
 (351,'sessionId','1BC25D2AA581562ED993D4E7FBC5DED5'),
 (352,'remoteAddress','0:0:0:0:0:0:0:1'),
 (352,'sessionId','33C625A1209DEA65EC9C6CAA2D87D1ED'),
 (353,'remoteAddress','0:0:0:0:0:0:0:1'),
 (353,'sessionId','CF0CDC45B13CCFCBB7A3EDD573666CF2'),
 (354,'remoteAddress','0:0:0:0:0:0:0:1'),
 (354,'sessionId','6596D33616DA276E97C7A1EE695E840F'),
 (355,'remoteAddress','0:0:0:0:0:0:0:1'),
 (355,'sessionId','6A8231E9E6C2E13631C5AB8D31ACA5AE'),
 (356,'remoteAddress','0:0:0:0:0:0:0:1'),
 (356,'sessionId','32C76359E53EF6CCBDE24922D60E5C28'),
 (357,'remoteAddress','0:0:0:0:0:0:0:1'),
 (357,'sessionId','4E809E34D7303FAEA829BBC7198BE073'),
 (358,'remoteAddress','0:0:0:0:0:0:0:1'),
 (358,'sessionId','8F9AA3E34D661F22F69F575ADD059761'),
 (359,'remoteAddress','0:0:0:0:0:0:0:1'),
 (359,'sessionId','8219E92E4A7A8BD82C5DD7714E5928FD'),
 (360,'remoteAddress','0:0:0:0:0:0:0:1'),
 (360,'sessionId','1617157E5E78354001F39BCB8C8651AE'),
 (361,'remoteAddress','0:0:0:0:0:0:0:1'),
 (361,'sessionId','64D863D75415DB2E4AA905809E7885EE'),
 (362,'remoteAddress','0:0:0:0:0:0:0:1'),
 (362,'sessionId','12C56B52F0D7F3706DF09D29BB86B91D'),
 (363,'remoteAddress','0:0:0:0:0:0:0:1'),
 (363,'sessionId','6A179273E8731AAE8C4DBCF2A48AE31E'),
 (364,'remoteAddress','0:0:0:0:0:0:0:1'),
 (364,'sessionId','C5FD6CF4ADADD2B643AC5CDB2EF31CC8'),
 (365,'remoteAddress','0:0:0:0:0:0:0:1'),
 (365,'sessionId','BCE376503DDB980CA01C17DF62684B88'),
 (366,'remoteAddress','0:0:0:0:0:0:0:1'),
 (366,'sessionId','E58463D50B282E1F793E55546FD37C16'),
 (367,'remoteAddress','0:0:0:0:0:0:0:1'),
 (367,'sessionId','812D6FA1530BD7DF3F7C5DA212AA0E03'),
 (368,'remoteAddress','0:0:0:0:0:0:0:1'),
 (368,'sessionId','A52E6D15470D805E39E8968B5B75DD22'),
 (369,'message','Bad credentials'),
 (369,'type','org.springframework.security.authentication.BadCredentialsException'),
 (370,'remoteAddress','0:0:0:0:0:0:0:1'),
 (370,'sessionId','0BA84BCFDA557FC0A3E42B9E70C24115'),
 (371,'remoteAddress','0:0:0:0:0:0:0:1'),
 (371,'sessionId','EAE5F32EBC94E8A2131B696D8F5CD664'),
 (372,'remoteAddress','0:0:0:0:0:0:0:1'),
 (372,'sessionId','C318F69CAFBDABD41D5339FF1E129050'),
 (373,'message','Bad credentials'),
 (373,'type','org.springframework.security.authentication.BadCredentialsException'),
 (374,'remoteAddress','0:0:0:0:0:0:0:1'),
 (374,'sessionId','BD2BF1E7E999055E96AFB74A0ADECEC0'),
 (375,'remoteAddress','0:0:0:0:0:0:0:1'),
 (375,'sessionId','5F14F2AF04E0C1423EF98276E0BD9741'),
 (376,'remoteAddress','0:0:0:0:0:0:0:1'),
 (376,'sessionId','C4829CB2383FAFFBF6C42CCDC409C0F8'),
 (377,'remoteAddress','0:0:0:0:0:0:0:1'),
 (377,'sessionId','1424BAAF30C5896AF2840A1916AFCBF1'),
 (378,'remoteAddress','0:0:0:0:0:0:0:1'),
 (378,'sessionId','DE30BA305218C729240066069B788189'),
 (379,'remoteAddress','0:0:0:0:0:0:0:1'),
 (379,'sessionId','9E13C221DDA2FEFF8A281BCAF6667510'),
 (380,'remoteAddress','0:0:0:0:0:0:0:1'),
 (380,'sessionId','277974E8950F73F49E5BD6E3AA7DBD36'),
 (381,'remoteAddress','0:0:0:0:0:0:0:1'),
 (381,'sessionId','36B44E3F44DD55203C3F10A654C51243'),
 (382,'remoteAddress','0:0:0:0:0:0:0:1'),
 (382,'sessionId','542781506E23357D5456559142348B6B'),
 (383,'remoteAddress','0:0:0:0:0:0:0:1'),
 (383,'sessionId','CA676EFD05B10955528175A95697692E'),
 (384,'remoteAddress','0:0:0:0:0:0:0:1'),
 (384,'sessionId','2CC5C578952F1BD2121CD941DFBFF4E9'),
 (385,'remoteAddress','0:0:0:0:0:0:0:1'),
 (385,'sessionId','2050CB7C9BDC37F127F74B13FB6B1D71'),
 (386,'remoteAddress','0:0:0:0:0:0:0:1'),
 (386,'sessionId','1FF8029DC5B4F351E9DDF9F871119353'),
 (387,'remoteAddress','0:0:0:0:0:0:0:1'),
 (387,'sessionId','AFA1329F3C3FC804DC565F6426EA19C4'),
 (388,'remoteAddress','0:0:0:0:0:0:0:1'),
 (388,'sessionId','572514FB389DCA4447F6213ED07E2A98'),
 (389,'remoteAddress','0:0:0:0:0:0:0:1'),
 (389,'sessionId','40FBD67BD0AC2976914DFB5B8992651D'),
 (390,'remoteAddress','0:0:0:0:0:0:0:1'),
 (390,'sessionId','65CAA97BA9C90C46AE0AA3BE177C8DF5'),
 (391,'remoteAddress','127.0.0.1'),
 (391,'sessionId','ECE0DED9E0B46A514052D7E5B32945F3'),
 (392,'remoteAddress','127.0.0.1'),
 (392,'sessionId','C836246D03ECAA8B095954C2B58B12C2'),
 (393,'remoteAddress','127.0.0.1'),
 (393,'sessionId','7025818A4808D99C8BCF162479004E1E'),
 (394,'remoteAddress','127.0.0.1'),
 (394,'sessionId','8ECE2AD6F18B1D3610185BE684412BC9'),
 (395,'remoteAddress','127.0.0.1'),
 (395,'sessionId','1BE40FEEF0BCE7C48D5C23A4C62CA4B0'),
 (396,'remoteAddress','127.0.0.1'),
 (396,'sessionId','D7D9C3BE8E67ABD26123E35EF559503D'),
 (397,'remoteAddress','127.0.0.1'),
 (397,'sessionId','150BD946D8300EEEDAC68086C6784E0B'),
 (398,'remoteAddress','127.0.0.1'),
 (398,'sessionId','228904587E9D702ABF5C22E941157A6E'),
 (399,'remoteAddress','127.0.0.1'),
 (399,'sessionId','A8BACE0E94E9D064D2171385281604DB'),
 (400,'remoteAddress','127.0.0.1'),
 (400,'sessionId','2728E650CAE0E871AE803EFE453E9C6D'),
 (401,'remoteAddress','127.0.0.1'),
 (401,'sessionId','F5B5C3D904CE94B8C6E46AEBFFCF804B'),
 (402,'remoteAddress','127.0.0.1'),
 (402,'sessionId','8341650B344601A19E2AB7DBD4EEDAA6'),
 (403,'remoteAddress','0:0:0:0:0:0:0:1'),
 (403,'sessionId','496EB9607AD208C02ED13F2CBBE51612'),
 (404,'remoteAddress','0:0:0:0:0:0:0:1'),
 (404,'sessionId','941A6A47DA2FE8943189764BB57660E4'),
 (405,'remoteAddress','0:0:0:0:0:0:0:1'),
 (405,'sessionId','8ABBBCE1920629C47A93F40EE3B5C1FB'),
 (406,'remoteAddress','0:0:0:0:0:0:0:1'),
 (406,'sessionId','44EE26A8ED1D6CA2F8D702A01E31117B'),
 (407,'remoteAddress','0:0:0:0:0:0:0:1'),
 (407,'sessionId','51C8158C3D8CCEC6557567E7D86BD1FE'),
 (408,'remoteAddress','0:0:0:0:0:0:0:1'),
 (408,'sessionId','8CF646E777AB9097AD5F34E4A06F08AD'),
 (409,'remoteAddress','0:0:0:0:0:0:0:1'),
 (409,'sessionId','677DCEEE62F550C47956335412A7941F'),
 (410,'remoteAddress','0:0:0:0:0:0:0:1'),
 (410,'sessionId','A3349B9F0C509A074CB52BE18D5E69CF'),
 (411,'remoteAddress','0:0:0:0:0:0:0:1'),
 (411,'sessionId','4E6562FA7D277396C76D10C5850EDE36'),
 (412,'remoteAddress','0:0:0:0:0:0:0:1'),
 (412,'sessionId','42BB7CF10DA62E69E449B0FA3B6F65CA'),
 (413,'remoteAddress','0:0:0:0:0:0:0:1'),
 (413,'sessionId','E41C0196FDAA2CCAEC27C8E14C9213D1'),
 (414,'remoteAddress','0:0:0:0:0:0:0:1'),
 (414,'sessionId','32CB01262604E796B8C1180003282D40'),
 (415,'remoteAddress','0:0:0:0:0:0:0:1'),
 (415,'sessionId','D24513B20D896E23C44975B930BCE564'),
 (416,'remoteAddress','0:0:0:0:0:0:0:1'),
 (416,'sessionId','AAEC5FB79F2C19CF01E347B5B615A371'),
 (417,'remoteAddress','0:0:0:0:0:0:0:1'),
 (417,'sessionId','B3FC1B67918B763E3CEADF5E04227804'),
 (418,'remoteAddress','0:0:0:0:0:0:0:1'),
 (418,'sessionId','FB075A2FD035B29309AD41400CF5ADF3'),
 (419,'remoteAddress','0:0:0:0:0:0:0:1'),
 (419,'sessionId','4229122FAE70EF6385FFCE3FA50BAE0C'),
 (420,'remoteAddress','0:0:0:0:0:0:0:1'),
 (420,'sessionId','B8B3C8D1FD58F8D83D418577DE9231B4'),
 (421,'remoteAddress','0:0:0:0:0:0:0:1'),
 (421,'sessionId','36A856C4950BD121BFD257B29FF88BF6'),
 (422,'remoteAddress','0:0:0:0:0:0:0:1'),
 (422,'sessionId','ED817BACA72AE6D090FB69A91993003A'),
 (423,'remoteAddress','0:0:0:0:0:0:0:1'),
 (423,'sessionId','FC0258D44EC8F94BBFAAA2884B213212'),
 (424,'remoteAddress','0:0:0:0:0:0:0:1'),
 (424,'sessionId','A0EF0E0307607B444E11C5EC5DD1DCC3'),
 (425,'remoteAddress','0:0:0:0:0:0:0:1'),
 (425,'sessionId','4C0AC30BF71BA39D3603CB4EEC69F1B8'),
 (426,'remoteAddress','0:0:0:0:0:0:0:1'),
 (426,'sessionId','B5A913722DF21E2AB70EA3F2715D6EA2'),
 (427,'remoteAddress','0:0:0:0:0:0:0:1'),
 (427,'sessionId','D5BFE1C35EE0F81FA04C730D4B8EB1E2'),
 (428,'remoteAddress','0:0:0:0:0:0:0:1'),
 (428,'sessionId','FD4D3FD38B97541A49EA8BD0A33EDC23'),
 (429,'remoteAddress','0:0:0:0:0:0:0:1'),
 (429,'sessionId','A9AFC066746BEF918EC1F07A424CC4EF'),
 (430,'remoteAddress','0:0:0:0:0:0:0:1'),
 (430,'sessionId','056D756441AC19C0A788CD9FBA93AC29'),
 (431,'remoteAddress','0:0:0:0:0:0:0:1'),
 (431,'sessionId','157A21C8C43A275F018568C8C529247F'),
 (432,'remoteAddress','0:0:0:0:0:0:0:1'),
 (432,'sessionId','CB82D568FDB48A82D9763A98B2AAEAAC'),
 (433,'remoteAddress','0:0:0:0:0:0:0:1'),
 (433,'sessionId','308127703EAC8FE9615F426BA504F420'),
 (434,'remoteAddress','0:0:0:0:0:0:0:1'),
 (434,'sessionId','CAF707FA877744839D26567AE5852335'),
 (435,'remoteAddress','0:0:0:0:0:0:0:1'),
 (435,'sessionId','6E5B5335CDEF6DB6C2B312AD7F1E99A1'),
 (436,'remoteAddress','0:0:0:0:0:0:0:1'),
 (436,'sessionId','5E178806FB187DF447522C1C3E14ADD1'),
 (437,'remoteAddress','0:0:0:0:0:0:0:1'),
 (437,'sessionId','A06DF6A16EDC92872DFEB7406A69D061'),
 (438,'remoteAddress','0:0:0:0:0:0:0:1'),
 (438,'sessionId','07988B419DA056B9E5BAA9C17016B75B'),
 (439,'remoteAddress','0:0:0:0:0:0:0:1'),
 (439,'sessionId','C563D38E6B424B7D438520220A157DAF'),
 (440,'remoteAddress','0:0:0:0:0:0:0:1'),
 (440,'sessionId','877A6D28F3C6C0431E1462D9AD50453C'),
 (441,'remoteAddress','0:0:0:0:0:0:0:1'),
 (441,'sessionId','C3ABECE3E81475AE9EB4AE976F3F0C4B'),
 (442,'remoteAddress','0:0:0:0:0:0:0:1'),
 (442,'sessionId','FD15210A9E29F58C0005C67EA530C0B2'),
 (443,'remoteAddress','0:0:0:0:0:0:0:1'),
 (443,'sessionId','55C11C9719532AF4BEC4EB90AF968921'),
 (444,'remoteAddress','127.0.0.1'),
 (444,'sessionId','B753BF415D9A1F4ECF340D425A9A686A'),
 (445,'remoteAddress','127.0.0.1'),
 (445,'sessionId','5457F285CA74863F498A59CC581EA796'),
 (446,'remoteAddress','127.0.0.1'),
 (446,'sessionId','62555D96BC5CC54E6FD67A86A31AD549'),
 (447,'remoteAddress','127.0.0.1'),
 (447,'sessionId','F3F43CDEEAC433B60C40DC3652B4804B'),
 (448,'remoteAddress','127.0.0.1'),
 (448,'sessionId','E870B825C148F0F13E47E3E524A92383'),
 (449,'remoteAddress','127.0.0.1'),
 (449,'sessionId','7F99C2BD51ED8DE2AF76BE59D3966F2F'),
 (450,'remoteAddress','0:0:0:0:0:0:0:1'),
 (450,'sessionId','AC11E52C53671EA72F64D48E05E7CE64'),
 (451,'remoteAddress','0:0:0:0:0:0:0:1'),
 (451,'sessionId','10F1474A6B66B3B98FAEBC7EE3998702'),
 (452,'remoteAddress','0:0:0:0:0:0:0:1'),
 (452,'sessionId','BE3CA9AECCF3B456184F8BB4B652D6A2'),
 (453,'remoteAddress','0:0:0:0:0:0:0:1'),
 (453,'sessionId','8A5E4F3EAB5E3DB454E8BA1F51D4D74B'),
 (454,'remoteAddress','0:0:0:0:0:0:0:1'),
 (454,'sessionId','9C6D205CB7DE44D6943CA183F1561A8B'),
 (455,'remoteAddress','0:0:0:0:0:0:0:1'),
 (455,'sessionId','566CFBBFA726E527D4823116E86A0AE0'),
 (456,'remoteAddress','0:0:0:0:0:0:0:1'),
 (456,'sessionId','C4F5F56BF4DE2C20A5EBD3AB11B2441A'),
 (457,'remoteAddress','0:0:0:0:0:0:0:1'),
 (457,'sessionId','C5E265E8A1BEDB88E81E2CA7AC49766B'),
 (458,'remoteAddress','0:0:0:0:0:0:0:1'),
 (458,'sessionId','470293FBB0D51C8FD5A2EB6B7EDC7CD9'),
 (459,'remoteAddress','0:0:0:0:0:0:0:1'),
 (459,'sessionId','626AF47EFA5C1D38F6AD0D0C01CF2C22'),
 (460,'remoteAddress','0:0:0:0:0:0:0:1'),
 (460,'sessionId','ED7AF1B9F3D9FDBD950537CBC888C382'),
 (461,'remoteAddress','0:0:0:0:0:0:0:1'),
 (461,'sessionId','F979F69A85B3DB48123864D8A3749B95'),
 (462,'remoteAddress','0:0:0:0:0:0:0:1'),
 (462,'sessionId','DF9D93FB77D4DD4C2EBEF9684D71B81E'),
 (463,'remoteAddress','0:0:0:0:0:0:0:1'),
 (463,'sessionId','4A365927D85B2ED71B5F1AE90A1750BF'),
 (464,'remoteAddress','0:0:0:0:0:0:0:1'),
 (464,'sessionId','927183A94C0806038B88E876C9F9D7EC'),
 (465,'remoteAddress','0:0:0:0:0:0:0:1'),
 (465,'sessionId','C940B22EC21DE0DF602195F0E2B2A77F'),
 (466,'remoteAddress','0:0:0:0:0:0:0:1'),
 (466,'sessionId','71267024FAC0B05CEB639759F9598516'),
 (467,'remoteAddress','0:0:0:0:0:0:0:1'),
 (467,'sessionId','AF37642563531EE43381F0AD41C0D7C4'),
 (468,'remoteAddress','0:0:0:0:0:0:0:1'),
 (468,'sessionId','A10CB3D56B7137D33B75C09ACFAA8467'),
 (469,'remoteAddress','0:0:0:0:0:0:0:1'),
 (469,'sessionId','BEA5286BC69471057BF9B440C563569E'),
 (470,'remoteAddress','0:0:0:0:0:0:0:1'),
 (470,'sessionId','280C21BE2E35BFF96386CDE65DC8362C'),
 (471,'remoteAddress','0:0:0:0:0:0:0:1'),
 (471,'sessionId','7A1B886FB2B690048D6BB41C4FD455C0'),
 (472,'remoteAddress','0:0:0:0:0:0:0:1'),
 (472,'sessionId','4ADF42BDDE9F4CFF33109BBDAC818A08'),
 (473,'remoteAddress','0:0:0:0:0:0:0:1'),
 (473,'sessionId','FDBC265FBAACF88F876D8247F1D24ECB'),
 (474,'remoteAddress','0:0:0:0:0:0:0:1'),
 (474,'sessionId','38B5E6844FA629E5475553D553C38A63'),
 (475,'remoteAddress','0:0:0:0:0:0:0:1'),
 (475,'sessionId','C2B90631DBDBF5EC510EBAA53CD0D5D6'),
 (476,'remoteAddress','0:0:0:0:0:0:0:1'),
 (476,'sessionId','D43315837F642645306140ED8FE89411'),
 (477,'remoteAddress','0:0:0:0:0:0:0:1'),
 (477,'sessionId','272EC08E4870B02ACF5865F82B975129'),
 (478,'remoteAddress','0:0:0:0:0:0:0:1'),
 (478,'sessionId','58D6226C53072B358F0AD0191A300A80'),
 (479,'remoteAddress','0:0:0:0:0:0:0:1'),
 (479,'sessionId','B4005C13271E5392C578B346D2D59AEE'),
 (480,'remoteAddress','0:0:0:0:0:0:0:1'),
 (480,'sessionId','6B74D9D3A9240D4EC9557E0C92FF3E4E'),
 (481,'remoteAddress','0:0:0:0:0:0:0:1'),
 (481,'sessionId','03730CEF96A77676BC0B8A9AE7853DCA'),
 (482,'remoteAddress','0:0:0:0:0:0:0:1'),
 (482,'sessionId','8E98C2EC1B0EA29DC917E6A667090607'),
 (483,'remoteAddress','0:0:0:0:0:0:0:1'),
 (483,'sessionId','B2E7959D9DEB5C30D9A60780341F3926'),
 (484,'remoteAddress','0:0:0:0:0:0:0:1'),
 (484,'sessionId','2D8CE57FA7D936770614F5238804FDAE'),
 (485,'remoteAddress','0:0:0:0:0:0:0:1'),
 (485,'sessionId','5BE62CBE55976FF84D3B4A898A9BBEBB'),
 (486,'remoteAddress','0:0:0:0:0:0:0:1'),
 (486,'sessionId','679A32DB95F6B4D723FD6CCCBAE477C9'),
 (487,'remoteAddress','0:0:0:0:0:0:0:1'),
 (487,'sessionId','B4E41151B40F3A5FFF3E24249D1AB73D'),
 (488,'remoteAddress','0:0:0:0:0:0:0:1'),
 (488,'sessionId','AF4C97F4F9545A663C00799211B1790D'),
 (489,'message','Bad credentials'),
 (489,'type','org.springframework.security.authentication.BadCredentialsException'),
 (490,'remoteAddress','0:0:0:0:0:0:0:1'),
 (490,'sessionId','E8ECFBFCF343B38395C975A056C00A06'),
 (491,'remoteAddress','0:0:0:0:0:0:0:1'),
 (491,'sessionId','C6BA4655CA29183C8ED3B2961FCE2C0B'),
 (492,'remoteAddress','0:0:0:0:0:0:0:1'),
 (492,'sessionId','F8A1D712764CCC6B02A54DE0EB4573BC'),
 (493,'remoteAddress','0:0:0:0:0:0:0:1'),
 (493,'sessionId','BBD7AA5E0C975B028A0C62268DDC36B5'),
 (494,'remoteAddress','0:0:0:0:0:0:0:1'),
 (494,'sessionId','0B49E574D9ED031737CCFC6B3355A5A2'),
 (495,'remoteAddress','0:0:0:0:0:0:0:1'),
 (495,'sessionId','891EB12ACF8BD17AA9349D6D8459A855'),
 (496,'remoteAddress','0:0:0:0:0:0:0:1'),
 (496,'sessionId','C6B85FBFF563860922A34F87C729B02E'),
 (497,'remoteAddress','0:0:0:0:0:0:0:1'),
 (497,'sessionId','C088040BCBAB7AFEE4E7A2CB2971761A'),
 (498,'remoteAddress','0:0:0:0:0:0:0:1'),
 (498,'sessionId','3C6DF780C1D2922D14B030764622561D'),
 (499,'remoteAddress','0:0:0:0:0:0:0:1'),
 (499,'sessionId','B10413BBD1DAAD01A823F425C313C727'),
 (500,'remoteAddress','0:0:0:0:0:0:0:1'),
 (500,'sessionId','15EE89086F0C0FDDB02430DA7897C6DA'),
 (501,'remoteAddress','0:0:0:0:0:0:0:1'),
 (501,'sessionId','64F38904EAA65368127ED2204E49EED0'),
 (502,'remoteAddress','0:0:0:0:0:0:0:1'),
 (502,'sessionId','49E2E7539FBB6B8AB48EA1E4F70710E2'),
 (503,'remoteAddress','0:0:0:0:0:0:0:1'),
 (503,'sessionId','0ABDAD563FE36415DE4DB1EF3263CBCF'),
 (504,'remoteAddress','0:0:0:0:0:0:0:1'),
 (504,'sessionId','D31DD994C3291F509ED6FF48E1733FC5'),
 (505,'message','Bad credentials'),
 (505,'type','org.springframework.security.authentication.BadCredentialsException'),
 (506,'remoteAddress','0:0:0:0:0:0:0:1'),
 (506,'sessionId','941536C01AA1ACB165228E6E41C62A28'),
 (507,'remoteAddress','0:0:0:0:0:0:0:1'),
 (507,'sessionId','3EAF5268A6EBCD5D9321B18E6DA90D8A'),
 (508,'remoteAddress','0:0:0:0:0:0:0:1'),
 (508,'sessionId','63B9367BFEDD4A84AF4038C2F2F598E9'),
 (509,'remoteAddress','0:0:0:0:0:0:0:1'),
 (509,'sessionId','4E49A4A9A960A3896A547621708A298D'),
 (510,'remoteAddress','0:0:0:0:0:0:0:1'),
 (510,'sessionId','15A21E5FA25D7A69E37021FAE55E80C3'),
 (511,'remoteAddress','192.168.1.23'),
 (511,'sessionId','E3953D43AF65919C5A8E2D341B85C068'),
 (512,'remoteAddress','192.168.1.23'),
 (512,'sessionId','A56D0A508B0A1B8ED906B1F4DD050433'),
 (513,'remoteAddress','192.168.1.23'),
 (513,'sessionId','805A06EE5B66FABC858862F6684B38BD'),
 (514,'remoteAddress','192.168.1.23'),
 (514,'sessionId','E5E0B5DA26C9E8BB67BF16CD5F082DF2'),
 (515,'remoteAddress','192.168.1.23'),
 (515,'sessionId','F423BE5ECD83B3FBEBB44EBDC6436E4F'),
 (516,'remoteAddress','192.168.1.23'),
 (516,'sessionId','4512DD37D1AF77C13899E61A5D6CB92E'),
 (517,'remoteAddress','192.168.1.23'),
 (517,'sessionId','649DD7B50584680A82ADDBC4522E9D80'),
 (518,'remoteAddress','192.168.1.23'),
 (518,'sessionId','3AC5A4EDFC5B576E5EA9F04CAF3ECB34'),
 (519,'remoteAddress','192.168.1.23'),
 (519,'sessionId','4D04BB74D02DAF2C1D1F3135997E406A'),
 (520,'remoteAddress','192.168.1.23'),
 (520,'sessionId','92292261E179A5D1C1D6D4E5E58D145C'),
 (521,'remoteAddress','192.168.1.23'),
 (521,'sessionId','679BA26C86853E27CABBEB0C0DBC1757'),
 (522,'remoteAddress','192.168.1.23'),
 (522,'sessionId','1EC65ABA4EB75CABC157E903EB37FA54'),
 (523,'remoteAddress','192.168.1.23'),
 (523,'sessionId','42EC69E89CEAE7CD9B6A3FC000D98769'),
 (524,'remoteAddress','192.168.1.23'),
 (524,'sessionId','D1E8D348B282AAE99F2C81653F8AD196'),
 (525,'remoteAddress','192.168.1.23'),
 (525,'sessionId','40C803BF6F45E7E58F7509C42A8DFC34'),
 (526,'remoteAddress','192.168.1.23'),
 (526,'sessionId','17AF298971C5B5F48B5590662D4668BA'),
 (527,'remoteAddress','192.168.1.23'),
 (527,'sessionId','57FA980FE862A9350F35BDFD7A53B3F0'),
 (528,'remoteAddress','192.168.1.23'),
 (528,'sessionId','530E569719D35DEF201EAF8890029148'),
 (529,'remoteAddress','192.168.1.23'),
 (529,'sessionId','FC8FD3443621870CB96F40DFED30A8C6'),
 (530,'message','Bad credentials'),
 (530,'type','org.springframework.security.authentication.BadCredentialsException'),
 (531,'remoteAddress','192.168.1.23'),
 (531,'sessionId','90945C8BE38FB7A629C06C519C8D81CA'),
 (532,'remoteAddress','192.168.1.4'),
 (532,'sessionId','DAF92A09F9255EF044262A315527B54D'),
 (533,'remoteAddress','192.168.1.4'),
 (533,'sessionId','9AF621EBA2CA94EF9DE8BB6873491728'),
 (534,'remoteAddress','0:0:0:0:0:0:0:1'),
 (534,'sessionId','B4C7BEA58D23F4C2F3C49381D4CDBE89'),
 (535,'remoteAddress','0:0:0:0:0:0:0:1'),
 (535,'sessionId','F5932C17655AEE457965F270CB56B1CB'),
 (536,'remoteAddress','0:0:0:0:0:0:0:1'),
 (536,'sessionId','4D9A1589B9268107308F69265F560FCA'),
 (537,'remoteAddress','0:0:0:0:0:0:0:1'),
 (537,'sessionId','D9E7B7E9605EFEBA172A6AB87A071483'),
 (538,'remoteAddress','0:0:0:0:0:0:0:1'),
 (538,'sessionId','43F5589514A50A5D360CF567BA356C70'),
 (539,'remoteAddress','0:0:0:0:0:0:0:1'),
 (539,'sessionId','D561527D8B24961DEF2F9BACBF06464C'),
 (540,'remoteAddress','0:0:0:0:0:0:0:1'),
 (540,'sessionId','00CD84F19AB0B56F49A4E0047447A152'),
 (541,'remoteAddress','0:0:0:0:0:0:0:1'),
 (541,'sessionId','75036536516C97C0043DBC6026176CDB'),
 (542,'remoteAddress','0:0:0:0:0:0:0:1'),
 (542,'sessionId','0D64BD5457E4A650EA7EE20B44B6E7D6'),
 (543,'remoteAddress','0:0:0:0:0:0:0:1'),
 (543,'sessionId','CE1336D08FCEA1D683AD0C54E00E5BE5'),
 (544,'remoteAddress','110.224.230.23'),
 (544,'sessionId','419ADF226F1980FEC1993A47793EBD29'),
 (545,'remoteAddress','110.224.230.23'),
 (545,'sessionId','C636CC1F813E62C6AFCF584D4261FF62'),
 (546,'remoteAddress','110.224.230.23'),
 (546,'sessionId','EAA8618EE089E54DACF1CD7AEDD2FEEC'),
 (547,'remoteAddress','110.224.230.23'),
 (547,'sessionId','70E05D9AE710387898B7B72E5C01E8C0'),
 (548,'remoteAddress','110.224.230.23'),
 (548,'sessionId','2D14EAA73E326EA17D5CDF1B293D3D31'),
 (549,'remoteAddress','110.224.230.23'),
 (549,'sessionId','4A9243A937D189FC4A1C681B302D1EA8'),
 (550,'remoteAddress','110.224.230.23'),
 (550,'sessionId','55FFF103BBC5D8AD7E873A208A5AAF7B'),
 (551,'remoteAddress','110.224.230.23'),
 (551,'sessionId','5B0670964FE9869E7FDCB55126B32FA6'),
 (552,'remoteAddress','110.224.230.23'),
 (552,'sessionId','B316691801934C11211D20502BECACA0'),
 (553,'remoteAddress','110.224.230.23'),
 (553,'sessionId','F725FDAAC2CB33E5BE24C8615ADC2B67'),
 (554,'remoteAddress','110.224.230.23'),
 (554,'sessionId','234AA776D5FB4AA1D7DD3C95AE50EA80'),
 (555,'remoteAddress','110.224.230.23'),
 (555,'sessionId','DD9372788BF0B39AF8CA105BB82858F5'),
 (556,'remoteAddress','110.224.230.23'),
 (556,'sessionId','7CD34E722B8FF05CAB6EAED24F35CA3B'),
 (557,'remoteAddress','110.224.230.23'),
 (557,'sessionId','CEC105D17E73227F9E060C98ECD3D4AF'),
 (558,'remoteAddress','110.224.230.23'),
 (558,'sessionId','6FFF8E57A1C5EC1EEB3379E232A02A80'),
 (559,'remoteAddress','110.224.230.23'),
 (559,'sessionId','197323320B10BBE11C974D49D75F2235'),
 (560,'remoteAddress','110.224.230.23'),
 (560,'sessionId','5E3D419C96AE4DB7B5635AD315CC1EE5'),
 (561,'remoteAddress','110.224.230.23'),
 (561,'sessionId','8414FEBA7C0338297210C6E8646FE744'),
 (562,'message','Bad credentials'),
 (562,'type','org.springframework.security.authentication.BadCredentialsException'),
 (563,'remoteAddress','110.224.230.23'),
 (563,'sessionId','2AAD869EB6C40594BBB15F30B92EEE07'),
 (564,'remoteAddress','110.224.230.23'),
 (564,'sessionId','E87CEA1184B36CC84E573B70BDE3667E'),
 (565,'remoteAddress','110.224.230.23'),
 (565,'sessionId','6BBC1916C03FAF10954FDD1E6B070880'),
 (566,'remoteAddress','110.224.230.23'),
 (566,'sessionId','A4A4DC1C7CE454201EF697DBA60CEC22'),
 (567,'remoteAddress','110.224.230.23'),
 (567,'sessionId','216A7E3689E06670E52EAB233A61D441'),
 (568,'remoteAddress','110.224.230.23'),
 (568,'sessionId','B7600E291222C146B0FB1BAB5BB1623A'),
 (569,'remoteAddress','110.224.230.23'),
 (569,'sessionId','F26F68DA7B145CA9E1E400BC79152BD2'),
 (570,'remoteAddress','110.224.230.23'),
 (570,'sessionId','E8C7C0305736D5548F18C26576239437'),
 (571,'remoteAddress','110.224.230.23'),
 (571,'sessionId','1C9C70D4A8836BA0281C7636BCE4F3A6'),
 (572,'remoteAddress','110.224.230.23'),
 (572,'sessionId','CB1B497A2F741219AA4FB67239EA17E8'),
 (573,'remoteAddress','110.224.230.23'),
 (573,'sessionId','DCDD24949FD69E269E5055C33D1BB296'),
 (574,'remoteAddress','110.224.230.23'),
 (574,'sessionId','997B01AD7F1CD44F84436BB401655841'),
 (575,'remoteAddress','110.224.230.23'),
 (575,'sessionId','9CFF39FBB798A13394AE7211AE196400'),
 (576,'remoteAddress','110.224.230.23'),
 (576,'sessionId','B8BD25F06815726B3A84C267C60BBB50'),
 (577,'remoteAddress','110.224.230.23'),
 (577,'sessionId','E801BE5BB5FDFBDC0D6392EF16AB4626'),
 (578,'remoteAddress','110.224.230.23'),
 (578,'sessionId','D52B72A3596C0EE28BD67A412A66E5E5'),
 (579,'remoteAddress','110.224.230.23'),
 (579,'sessionId','7D9B7B418A5B9A380E3C521BC15FCAC6'),
 (580,'remoteAddress','110.224.230.23'),
 (580,'sessionId','214391C4D1E707C225744F022B30C75C'),
 (581,'remoteAddress','110.224.230.23'),
 (581,'sessionId','A0B899419D0A4C3AF5C63BBFCA06D84C'),
 (582,'remoteAddress','110.224.230.23'),
 (582,'sessionId','43977632F5A0C3928DD8B28E40254AB7'),
 (583,'remoteAddress','110.224.230.23'),
 (583,'sessionId','E40EBD6B875A7226B1E6F58179570BC9'),
 (584,'remoteAddress','122.170.237.113'),
 (584,'sessionId','92AF795CCCD863F4425B0F7EAEEB4E37'),
 (585,'remoteAddress','122.170.237.113'),
 (585,'sessionId','7C255949B1DE2BD50A5D8B2B50E2A96D'),
 (586,'remoteAddress','122.170.237.113'),
 (586,'sessionId','F26B83EC524DAF590F999035A38ACF55'),
 (587,'remoteAddress','122.170.237.113'),
 (587,'sessionId','72D7A45117002C856857CD571B762C78'),
 (588,'remoteAddress','122.170.237.113'),
 (588,'sessionId','7940775DEEE653F41F182B21BA510A68'),
 (589,'remoteAddress','122.170.237.113'),
 (589,'sessionId','25FBBBFE67EC6039AAB96FD977885A2B'),
 (590,'remoteAddress','122.170.237.113'),
 (590,'sessionId','1A300E00177ADBFAAC3EEE28F018A814'),
 (591,'remoteAddress','122.170.237.113'),
 (591,'sessionId','511E3151BD5A3251E9A81CA190C805A4'),
 (592,'remoteAddress','122.170.237.113'),
 (592,'sessionId','FA87BBEDFC994DAB7CF63957486CBC69'),
 (593,'remoteAddress','122.170.237.113'),
 (593,'sessionId','A557C9DCB27499CA70E2886CCEA617B3'),
 (594,'remoteAddress','122.170.237.113'),
 (594,'sessionId','A9B90A854C729BFF734CE1728F5BE8DC'),
 (595,'message','Bad credentials'),
 (595,'type','org.springframework.security.authentication.BadCredentialsException'),
 (596,'remoteAddress','122.170.237.113'),
 (596,'sessionId','4295092756DD4CA08FAD30DB458E1ECE'),
 (597,'remoteAddress','122.170.237.113'),
 (597,'sessionId','1A25FE02528B706DC8B98F7D6FD97195'),
 (598,'remoteAddress','122.170.237.113'),
 (598,'sessionId','9B7C39D219F0ECBF4A00515417323802'),
 (599,'remoteAddress','122.170.237.113'),
 (599,'sessionId','3AB891B98BAE93D67F874000DD2BC902'),
 (600,'remoteAddress','122.170.237.113'),
 (600,'sessionId','D833CBF933D9955CF4A49B3C61E4D547'),
 (601,'remoteAddress','122.170.237.113'),
 (601,'sessionId','90380A551D3A995FB6755DC5DA6643B7'),
 (602,'remoteAddress','122.170.237.113'),
 (602,'sessionId','65514CF504B7789CA4B8E53C585EC17B'),
 (603,'remoteAddress','122.170.237.113'),
 (603,'sessionId','F13BD9AB47C2EF5A896A0CB3D75B47EF'),
 (604,'remoteAddress','122.170.237.113'),
 (604,'sessionId','1B87FFD76FE2DC4AC1508AA0BAB56331'),
 (605,'remoteAddress','122.170.237.113'),
 (605,'sessionId','1787F50A0AEA9B3BD553E21251112B64'),
 (606,'remoteAddress','122.170.237.113'),
 (606,'sessionId','3906F3EFA2BB6DC21A1A3ED0A114027E'),
 (607,'remoteAddress','122.170.237.113'),
 (607,'sessionId','D1E22AEE177C6845B33D5C8E4C76FD60'),
 (608,'remoteAddress','122.170.237.113'),
 (608,'sessionId','4EF54AD808DEED38D93B43775377AC3A'),
 (609,'remoteAddress','122.170.237.113'),
 (609,'sessionId','B6D398124046DA476BEF37D3271B6614'),
 (610,'remoteAddress','122.170.237.113'),
 (610,'sessionId','8B5F79BBD4D4ED033C98871B7550992D'),
 (611,'remoteAddress','122.170.237.113'),
 (611,'sessionId','32C6DCA8B7CB9B2A974D20BE51F86EC8'),
 (612,'remoteAddress','122.170.237.113'),
 (612,'sessionId','1330F61ECC11FC555B555E306C979018'),
 (613,'message','Bad credentials'),
 (613,'type','org.springframework.security.authentication.BadCredentialsException'),
 (614,'remoteAddress','122.170.237.113'),
 (614,'sessionId','15D75C2EC60B9D6003C85E8D2637B291'),
 (615,'remoteAddress','122.170.237.113'),
 (615,'sessionId','77E50FFDB6BAEF77D25F36081A38482D'),
 (616,'remoteAddress','122.170.237.113'),
 (616,'sessionId','4C1F1245F843C0C429100C76033535B2'),
 (617,'remoteAddress','122.170.237.113'),
 (617,'sessionId','6A7239B93207D508983322B7033A04DB'),
 (618,'remoteAddress','122.170.237.113'),
 (618,'sessionId','FB1895647AF6CF3436FF0A674A1C70CF'),
 (619,'message','Bad credentials'),
 (619,'type','org.springframework.security.authentication.BadCredentialsException'),
 (620,'remoteAddress','122.170.237.113'),
 (620,'sessionId','5C51308CDC580EEB9A07C851B62F9744'),
 (621,'remoteAddress','122.170.237.113'),
 (621,'sessionId','C1863A5D42531B641541A4BDC9CAAE6B'),
 (622,'remoteAddress','122.170.237.113'),
 (622,'sessionId','55FC81F745508ADBD880164A2ED009B4'),
 (623,'remoteAddress','122.170.237.113'),
 (623,'sessionId','DCE32295C75C98BA9F91D43E4CF1B5BB'),
 (624,'remoteAddress','122.170.237.113'),
 (624,'sessionId','270408D52EA6AC0DB3A7220EAED2EEC9'),
 (625,'remoteAddress','122.170.237.113'),
 (625,'sessionId','0F253B389BC1361888327961D8D9199D'),
 (626,'remoteAddress','122.170.237.113'),
 (626,'sessionId','DECF77AE12F25E3BCD56A8000530BA6E'),
 (627,'remoteAddress','122.170.237.113'),
 (627,'sessionId','53BC49540659735EA31F4EAD89D89E33'),
 (628,'remoteAddress','122.170.237.113'),
 (628,'sessionId','37DA3C312029942CC05541369705F45C'),
 (629,'remoteAddress','122.170.237.113'),
 (629,'sessionId','12944C32979277E000DD8E67503C8E2F'),
 (630,'remoteAddress','122.170.237.113'),
 (630,'sessionId','2C764F792D2AC92527C55177963877A8'),
 (631,'remoteAddress','122.170.237.113'),
 (631,'sessionId','8EE8D6ED48C4943D7B0B88A62F738A1C'),
 (632,'remoteAddress','122.170.237.113'),
 (632,'sessionId','343F26B22D0D8712A30FA9AB42564BF0'),
 (633,'remoteAddress','196.41.61.82'),
 (633,'sessionId','190968B4209BD762BB915A16C6399B06'),
 (634,'remoteAddress','122.170.237.113'),
 (634,'sessionId','09BCB0575B2BCB309ACAAC63FB217D2A'),
 (635,'remoteAddress','122.170.237.113'),
 (635,'sessionId','12AAFD8225DBB3E7936EF6052568C75F'),
 (636,'remoteAddress','122.170.237.113'),
 (636,'sessionId','BF681D83A353D63353A9DB9499E619C9'),
 (637,'remoteAddress','122.170.237.113'),
 (637,'sessionId','B7ABB7EEFEC76CA85D0F844B4E699823'),
 (638,'remoteAddress','122.170.237.113'),
 (638,'sessionId','7BB524CCCDE3EBCE343F5670FF9A07E9'),
 (639,'remoteAddress','122.170.237.113'),
 (639,'sessionId','7AF1DCC3714EE9D9619CCB9103054158'),
 (640,'remoteAddress','122.170.237.113'),
 (640,'sessionId','27955C06013676FFE31C40882610A186'),
 (641,'remoteAddress','122.170.237.113'),
 (641,'sessionId','6A7CBE1690D9E05D7BFB091BA1353234'),
 (642,'remoteAddress','122.170.237.113'),
 (642,'sessionId','481C57CA4246D7737207B230216EE4D4'),
 (643,'remoteAddress','122.170.237.113'),
 (643,'sessionId','C7657C11B12ED6578CFE9A1025053823'),
 (644,'remoteAddress','122.170.237.113'),
 (644,'sessionId','871531A3EB3DDC4507BB52BFB7EDFE92'),
 (645,'remoteAddress','122.170.237.113'),
 (645,'sessionId','B2CD2DE9E74ADED4EC9CC73A458CB6A4'),
 (646,'remoteAddress','122.170.237.113'),
 (646,'sessionId','26C0D6237E6DB7977F3B7A284BA8B1AD'),
 (647,'remoteAddress','122.170.237.113'),
 (647,'sessionId','91807459C1EB48C2EFE6C0CD0F1902CF'),
 (648,'remoteAddress','122.170.237.113'),
 (648,'sessionId','8D64C8BCF0E3808953D29205EE0A9837'),
 (649,'remoteAddress','122.170.237.113'),
 (649,'sessionId','16E16E6E1102F8C073C8B750E7DDC243'),
 (650,'remoteAddress','122.170.237.113'),
 (650,'sessionId','38EA408FDAD49AB08CC150B29E67DB8B'),
 (651,'remoteAddress','122.170.237.113'),
 (651,'sessionId','E7381ED0250506F8869152C7EFC4B160'),
 (652,'remoteAddress','122.170.237.113'),
 (652,'sessionId','BD1FD802198CC2FD9461ECA157FBA2C9'),
 (653,'remoteAddress','122.170.237.113'),
 (653,'sessionId','0A994A18437AC3015180E09E2E9ED1C2'),
 (654,'remoteAddress','122.170.237.113'),
 (654,'sessionId','E27A30123F1F7B6F3014AA0FB9D7BBFD'),
 (655,'remoteAddress','122.170.237.113'),
 (655,'sessionId','E24C69FBC77F8564E700BF1B9E210F65'),
 (656,'remoteAddress','122.170.237.113'),
 (656,'sessionId','B2E0A2754E6C5FFA088BD4B4987A41C7'),
 (657,'remoteAddress','122.170.237.113'),
 (657,'sessionId','D536A807A41946D456D88D6462FBE223'),
 (658,'remoteAddress','122.170.237.113'),
 (658,'sessionId','AD9F30CA8838934EFAD9FCC3032A6FAD'),
 (659,'remoteAddress','110.224.236.177'),
 (659,'sessionId','114F4EA380B10269203E089850BD2728'),
 (660,'remoteAddress','110.224.236.177'),
 (660,'sessionId','A4208CDDB839CC781A491BBC00791594'),
 (661,'remoteAddress','110.224.236.177');
INSERT INTO `watererp`.`jhi_persistent_audit_evt_data` VALUES  (661,'sessionId','5BB2DD0D49C78077123F51D3C0F81261'),
 (662,'remoteAddress','110.224.236.177'),
 (662,'sessionId','1F5D258940343396A050805D0C8E2790'),
 (663,'remoteAddress','110.224.236.177'),
 (663,'sessionId','C22AAB96AB6029D6FD0D156FDA39C120'),
 (664,'remoteAddress','110.224.236.177'),
 (664,'sessionId','BE3F590A31FDA828F11B817765A8F7D4'),
 (665,'remoteAddress','110.224.236.177'),
 (665,'sessionId','4CDC4CE0CADA65B9936FAD95BC2C9B8B'),
 (666,'remoteAddress','110.224.236.177'),
 (666,'sessionId','8BB5E4B63DCCE1286FA53D4B2328B14A'),
 (667,'remoteAddress','110.224.236.177'),
 (667,'sessionId','225380C6B3FAA92D43B916B7C49F79E0'),
 (668,'remoteAddress','110.224.236.177'),
 (668,'sessionId','5222E298BF017E154FBB41383CF8F520'),
 (669,'remoteAddress','110.224.236.177'),
 (669,'sessionId','72D43E1E1C1F32ADE61B04C32071D718'),
 (670,'remoteAddress','110.224.236.177'),
 (670,'sessionId','DD785F520FB8B8AB2A13E667FA4FCE13'),
 (671,'remoteAddress','110.224.236.177'),
 (671,'sessionId','22795818A126DC99B15AD96149410C5E'),
 (672,'remoteAddress','110.224.236.177'),
 (672,'sessionId','20A62B2C793813454F954B3D76ED2748'),
 (673,'remoteAddress','110.224.236.177'),
 (673,'sessionId','C3E0E3E2E13CE354F784D0CF37006B55'),
 (674,'remoteAddress','110.224.236.177'),
 (674,'sessionId','4F464976649F443675BF487262940536'),
 (675,'remoteAddress','110.224.236.177'),
 (675,'sessionId','D48687774955EE1AEAFCC2ED7FBE1DD2'),
 (676,'remoteAddress','110.224.236.177'),
 (676,'sessionId','BEF4F1693C6286B725041C1448528F55'),
 (677,'remoteAddress','110.224.236.177'),
 (677,'sessionId','32FCCB62F46FACED683AA4C0EF6CAA13'),
 (678,'remoteAddress','110.224.236.177'),
 (678,'sessionId','9F89F3389BB1721295E46FB637188584'),
 (679,'remoteAddress','110.224.236.177'),
 (679,'sessionId','0D5E51B7AE3BA15BC8DD4EB9EAA946D1'),
 (680,'remoteAddress','110.224.236.177'),
 (680,'sessionId','E8E2F76A1757AA45A62CCB556AB98C58'),
 (681,'remoteAddress','110.224.236.177'),
 (681,'sessionId','9406CD822015F04B2F83303F67B91BFF'),
 (682,'remoteAddress','110.224.236.177'),
 (682,'sessionId','A5620B7F115A13BC31DC7E058011D728'),
 (683,'remoteAddress','110.224.236.177'),
 (683,'sessionId','A43C493D94533213C244428B8333C7F0'),
 (684,'remoteAddress','110.224.236.177'),
 (684,'sessionId','6DEA573F900B0565378D924496857622'),
 (685,'remoteAddress','110.224.236.177'),
 (685,'sessionId','456291D2963111A8B39CEC2CF371A9CD'),
 (686,'remoteAddress','110.224.236.177'),
 (686,'sessionId','138A372DCD307C5E181983CD7877A410'),
 (687,'remoteAddress','110.224.236.177'),
 (687,'sessionId','111D1964260ED59C2E1457CB6C016779'),
 (688,'remoteAddress','110.224.236.177'),
 (688,'sessionId','2AD6873566120BC03D52BE106C5C1A40'),
 (689,'remoteAddress','110.224.236.177'),
 (689,'sessionId','059AED5E034D3CE38AAA9A9F003F5DFA'),
 (690,'remoteAddress','110.224.236.177'),
 (690,'sessionId','D571F0EBF0A2E743F056232D8EAC724E'),
 (691,'remoteAddress','110.224.236.177'),
 (691,'sessionId','3FCDED17BE1B8A4426BEC799203152F7'),
 (692,'remoteAddress','110.224.236.177'),
 (692,'sessionId','88077946A7F3D18B3ACFD4A926ADA62B'),
 (693,'remoteAddress','110.224.236.177'),
 (693,'sessionId','3EB866847EC09485AD475A14D52C0A7A'),
 (694,'remoteAddress','110.224.236.177'),
 (694,'sessionId','4D035287ED8670D614D64F43D25800E4'),
 (695,'message','Bad credentials'),
 (695,'type','org.springframework.security.authentication.BadCredentialsException'),
 (696,'remoteAddress','110.224.236.177'),
 (696,'sessionId','EC56AE21E4C4369DA76A8630A4DDADB1'),
 (697,'message','Bad credentials'),
 (697,'type','org.springframework.security.authentication.BadCredentialsException'),
 (698,'remoteAddress','110.224.236.177'),
 (698,'sessionId','14F98A06F3AB91731863645DFF61E688'),
 (699,'remoteAddress','110.224.236.177'),
 (699,'sessionId','01654723BBC3F1DF2C2213C175C86678'),
 (700,'remoteAddress','110.224.236.177'),
 (700,'sessionId','BE7B9DF7810D12960875328012B5A046'),
 (701,'remoteAddress','110.224.236.177'),
 (701,'sessionId','EA8766EF3AB331F05DF437F0F57A9DDA'),
 (702,'remoteAddress','110.224.236.177'),
 (702,'sessionId','5516FFFA80E0AFE703815D5A9C7F5360'),
 (703,'remoteAddress','110.224.236.177'),
 (703,'sessionId','49D64AC82C2F7226EB42BB284E42E075'),
 (704,'remoteAddress','110.224.236.177'),
 (704,'sessionId','1C3077D4073281E78805F69D49BB393A'),
 (705,'remoteAddress','110.224.236.177'),
 (705,'sessionId','F911EB108CF6E2BCB41EB8AE77B17874'),
 (706,'remoteAddress','110.224.236.177'),
 (706,'sessionId','79B98BA5BBE24CDF1450E9C1BADF852C'),
 (707,'remoteAddress','110.224.236.177'),
 (707,'sessionId','9AB49758FEEA383A7B06892E912E80A9'),
 (708,'remoteAddress','110.224.236.177'),
 (708,'sessionId','7733E9DF7324396843B179A422134755'),
 (709,'message','Bad credentials'),
 (709,'type','org.springframework.security.authentication.BadCredentialsException'),
 (710,'remoteAddress','110.224.236.177'),
 (710,'sessionId','D7D5B4BD935FC50ADB96A96E78729362'),
 (711,'remoteAddress','110.224.236.177'),
 (711,'sessionId','1F8E87FA5617333BC7CCB8F2191C533B'),
 (712,'remoteAddress','110.224.236.177'),
 (712,'sessionId','048F0883DCCC49F657C3E482514236D7'),
 (713,'remoteAddress','110.224.236.177'),
 (713,'sessionId','87CDDDA3CC59C7383F06EAE2F759A745'),
 (714,'remoteAddress','110.224.236.177'),
 (714,'sessionId','88035C683BB80E4FC41D4F6E67CAF558'),
 (715,'remoteAddress','110.224.236.177'),
 (715,'sessionId','C9B12995E33D2016895F542EE6351363'),
 (716,'remoteAddress','110.224.236.177'),
 (716,'sessionId','75A4E354C4954E74CC4629BB6355AB79'),
 (717,'remoteAddress','110.224.236.177'),
 (717,'sessionId','1D4CD0BF7E369374843A80D9E4D60D9B'),
 (718,'message','Bad credentials'),
 (718,'type','org.springframework.security.authentication.BadCredentialsException'),
 (719,'remoteAddress','110.224.236.177'),
 (719,'sessionId','1707C8EDA67495B48F6A65173852A27B'),
 (720,'remoteAddress','110.224.236.177'),
 (720,'sessionId','F41F0D43A696D5DF92AD4A213315E91E'),
 (721,'remoteAddress','110.224.236.177'),
 (721,'sessionId','0276C73FA0C13F8BB7547E125E3F8FF7'),
 (722,'remoteAddress','110.224.236.177'),
 (722,'sessionId','5335E4D9F473CB27246DB02AB61197A4'),
 (723,'remoteAddress','110.224.236.177'),
 (723,'sessionId','A1882FDC7A61E97E4ADAA3E84C992BB1'),
 (724,'remoteAddress','110.224.236.177'),
 (724,'sessionId','FAD55F63F6A9D3F32044DCC028080E1D'),
 (725,'remoteAddress','110.224.236.177'),
 (725,'sessionId','AA3BBB53AE1A0B75902B800C13AD73FB'),
 (726,'remoteAddress','110.224.236.177'),
 (726,'sessionId','90FE7C8501BA41BE72CE2EF23FE65825'),
 (727,'remoteAddress','110.224.236.177'),
 (727,'sessionId','336606D6D84B3A186B7596AE5628311E'),
 (728,'remoteAddress','110.224.236.177'),
 (728,'sessionId','A51D5314CEB5D4DAC48EF5FB2926ECDD'),
 (729,'remoteAddress','110.224.236.177'),
 (729,'sessionId','043B3CDD4C3B353B41CF19C09927A4CD'),
 (730,'remoteAddress','110.224.236.177'),
 (730,'sessionId','55DA467DCC857FE49281280174D5FF63'),
 (731,'remoteAddress','110.224.236.177'),
 (731,'sessionId','A229344E85039AA15E035F10FFBD92A5'),
 (732,'remoteAddress','110.224.236.177'),
 (732,'sessionId','20FA2A0BE5C27BC346586CEF1B737383'),
 (733,'remoteAddress','122.170.249.158'),
 (733,'sessionId','B5ACF360CD30E83843FA0DB8F965A47F'),
 (734,'remoteAddress','122.170.249.158'),
 (734,'sessionId','FFB13F0618CAA2239A197C219791818D'),
 (735,'remoteAddress','122.170.249.158'),
 (735,'sessionId','68A0E36577145BE79AA317CC6BEE7E79'),
 (736,'message','Bad credentials'),
 (736,'type','org.springframework.security.authentication.BadCredentialsException'),
 (737,'remoteAddress','122.170.249.158'),
 (737,'sessionId','C9D86A725CF68F302F0CD71FE67C20A9'),
 (738,'remoteAddress','122.170.249.158'),
 (738,'sessionId','1C96B6D055F56C26E8768F35EFCEC9F0'),
 (739,'remoteAddress','122.170.249.158'),
 (739,'sessionId','E4084BC82D5493503B03859686DEB1E0'),
 (740,'remoteAddress','0:0:0:0:0:0:0:1'),
 (740,'sessionId','2F7DA5E712FC3B1975D968E210C77506'),
 (741,'remoteAddress','192.168.1.5'),
 (741,'sessionId','A8FA309E670E84B1482E254C72FCB928'),
 (742,'remoteAddress','192.168.1.5'),
 (742,'sessionId','91E0BF5AEE5998B62863AEDF28656C25'),
 (743,'remoteAddress','192.168.1.5'),
 (743,'sessionId','D0DC245D8CA4E8C724400E9888E5D7A4'),
 (744,'remoteAddress','192.168.1.5'),
 (744,'sessionId','6D5197C31A3FBAE2EB357C5FC26D8757'),
 (745,'message','Bad credentials'),
 (745,'type','org.springframework.security.authentication.BadCredentialsException'),
 (746,'remoteAddress','192.168.1.5'),
 (746,'sessionId','F8696C373DA3837690B09B9A15807AC4'),
 (747,'remoteAddress','192.168.1.5'),
 (747,'sessionId','D20D046C1D111858645F4AEDC2FF72B5'),
 (748,'remoteAddress','192.168.1.5'),
 (748,'sessionId','FB6E5A51CB9DB09CC70AD132A48D9FE5'),
 (749,'remoteAddress','192.168.1.5'),
 (749,'sessionId','8544C9665755927CF1E2C3E8DFD076B0'),
 (750,'remoteAddress','192.168.1.5'),
 (750,'sessionId','1D5DEDBF5A1CE6C36454CBAD7DCBCE8E'),
 (751,'remoteAddress','192.168.1.5'),
 (751,'sessionId','DDCED39817F606E9F14BA5E7250A8F82'),
 (752,'remoteAddress','0:0:0:0:0:0:0:1'),
 (752,'sessionId','F85A2C8374D8155314E3DE9BD855905E'),
 (753,'remoteAddress','0:0:0:0:0:0:0:1'),
 (753,'sessionId','F85A2C8374D8155314E3DE9BD855905E'),
 (754,'remoteAddress','192.168.1.5'),
 (754,'sessionId','E4847B23BCEA5AAB1647A7D281819664'),
 (755,'remoteAddress','0:0:0:0:0:0:0:1'),
 (755,'sessionId','D2177336150D218509F56B4A7B61A35C'),
 (756,'remoteAddress','0:0:0:0:0:0:0:1'),
 (756,'sessionId','AA3DBDEDDCEDB5195E349DCD10112905'),
 (757,'remoteAddress','192.168.1.5'),
 (757,'sessionId','3853F8AF4496391859B467F031220DD9'),
 (758,'remoteAddress','192.168.1.5'),
 (758,'sessionId','9420515DC827E8AD647AD46FAA360C88'),
 (759,'remoteAddress','192.168.1.5'),
 (759,'sessionId','37A96FA605F92F91D668DEF081DDD209'),
 (760,'remoteAddress','192.168.1.5'),
 (760,'sessionId','9123BA939E34CEEDE7D4B9015D65721B'),
 (761,'remoteAddress','192.168.1.5'),
 (761,'sessionId','6CCBA6AF402A17C19FC04C209D42A3FF'),
 (762,'remoteAddress','192.168.1.5'),
 (762,'sessionId','1DF93216BCA982E1398063976B0EE5E2'),
 (763,'remoteAddress','192.168.1.5'),
 (763,'sessionId','063138DD0E596AFD2D02BC8E1C357A7C'),
 (764,'remoteAddress','0:0:0:0:0:0:0:1'),
 (764,'sessionId','0720BA44ACD5602F6C368F4EF940B836'),
 (765,'remoteAddress','0:0:0:0:0:0:0:1'),
 (765,'sessionId','F888F7EB8AE7AFCFF45696E9E0737129'),
 (766,'remoteAddress','0:0:0:0:0:0:0:1'),
 (766,'sessionId','4618F435364F73B6FC9B31AB4D9025E6'),
 (767,'remoteAddress','0:0:0:0:0:0:0:1'),
 (767,'sessionId','12218876CA6D1A3FE1A47F7FDAEFBC4F'),
 (768,'remoteAddress','0:0:0:0:0:0:0:1'),
 (768,'sessionId','9CBF70E7F8520823D9E8A93ECB0C19A1'),
 (769,'remoteAddress','0:0:0:0:0:0:0:1'),
 (769,'sessionId','A0B10D1555965FEC2574AE864598F5E8'),
 (770,'remoteAddress','0:0:0:0:0:0:0:1'),
 (770,'sessionId','8DCA4060B6C47F42F95457019F42087B'),
 (771,'remoteAddress','0:0:0:0:0:0:0:1'),
 (771,'sessionId','A2F5D6D81E26151F67736C7028B695F6'),
 (772,'remoteAddress','0:0:0:0:0:0:0:1'),
 (772,'sessionId','40CC55ED237D4BFB74DE74FC45342E9B'),
 (773,'remoteAddress','0:0:0:0:0:0:0:1'),
 (773,'sessionId','8365318BA096FFDE9A25FA3E689038ED'),
 (774,'remoteAddress','0:0:0:0:0:0:0:1'),
 (774,'sessionId','C3DA154CCB2EF56A45724518B2C71CD2'),
 (775,'remoteAddress','0:0:0:0:0:0:0:1'),
 (775,'sessionId','ED8B96A3EB0BC5723A237341B72EADD1'),
 (776,'remoteAddress','0:0:0:0:0:0:0:1'),
 (776,'sessionId','73CFBB42EC003D84BA4968F42481A3C5'),
 (777,'remoteAddress','0:0:0:0:0:0:0:1'),
 (777,'sessionId','45FD073D36F4CA765296B98553AF6248'),
 (778,'remoteAddress','0:0:0:0:0:0:0:1'),
 (778,'sessionId','1D8866EA70DBE8ED9143A6D0B64B1EEA'),
 (779,'remoteAddress','0:0:0:0:0:0:0:1'),
 (779,'sessionId','D278AA6DA9182AF8A7C5D75D3861DE6A'),
 (780,'remoteAddress','0:0:0:0:0:0:0:1'),
 (780,'sessionId','E1099EEF1EC306E2E5C067CFECAA878C'),
 (781,'remoteAddress','0:0:0:0:0:0:0:1'),
 (781,'sessionId','C9003223E82097B4A0D360A4CB9871B7'),
 (782,'remoteAddress','0:0:0:0:0:0:0:1'),
 (782,'sessionId','E157CC614E231190C6C5E38D18D90C54'),
 (783,'remoteAddress','0:0:0:0:0:0:0:1'),
 (783,'sessionId','B7127E9EBD63A63792D964C3702A286C'),
 (784,'remoteAddress','0:0:0:0:0:0:0:1'),
 (784,'sessionId','C7D362A011D53717BD6341DD78C25D4C'),
 (785,'remoteAddress','0:0:0:0:0:0:0:1'),
 (785,'sessionId','A47BDE36A99E97CA8F2B278449E895A4'),
 (786,'remoteAddress','192.168.1.5'),
 (786,'sessionId','F86D0A02BB57B7DB88758F0E385568FC'),
 (787,'remoteAddress','192.168.1.5'),
 (787,'sessionId','8413D518BC769927CA899B1A796741C8'),
 (788,'remoteAddress','192.168.1.5'),
 (788,'sessionId','7BEFB1A824631E6000F19F789EB743D6'),
 (789,'remoteAddress','192.168.1.5'),
 (789,'sessionId','E31B3692D12A0051E41ED9ADFB8493B4'),
 (790,'remoteAddress','192.168.1.5'),
 (790,'sessionId','2EDE5A28BF055417D4EA68839DC76C06'),
 (791,'remoteAddress','192.168.1.5'),
 (791,'sessionId','4FF734BA0E348E225330F3BF67C086A5'),
 (792,'remoteAddress','192.168.1.5'),
 (792,'sessionId','FF25C746C157044659DF2634FB90FD5B'),
 (793,'remoteAddress','192.168.1.5'),
 (793,'sessionId','EB7BB9B4DC7C2461071B2FD76678CED4'),
 (794,'remoteAddress','192.168.1.5'),
 (794,'sessionId','EF77ED9F7381A902D07A6AAD8B12F0F2'),
 (795,'remoteAddress','192.168.1.5'),
 (795,'sessionId','4101B98B054075CE98EE6C5073E04269'),
 (796,'remoteAddress','192.168.1.5'),
 (796,'sessionId','52009B22E7D7DF9576DA89C7C7FFD3DA'),
 (797,'remoteAddress','192.168.1.5'),
 (797,'sessionId','D28B04C1504F61DFC30D0B43811D94E5'),
 (798,'remoteAddress','0:0:0:0:0:0:0:1'),
 (798,'sessionId','877900C734F4ACEB48FFF96969419594'),
 (799,'remoteAddress','0:0:0:0:0:0:0:1'),
 (799,'sessionId','FDFD44A7ED6CBD8959CC56B7490DC9B9'),
 (800,'remoteAddress','0:0:0:0:0:0:0:1'),
 (800,'sessionId','DD38B5960C52D34199713A4F5D40117D'),
 (801,'remoteAddress','0:0:0:0:0:0:0:1'),
 (801,'sessionId','A0F1DB0B77B27B7A240ED599FF73468D'),
 (802,'remoteAddress','0:0:0:0:0:0:0:1'),
 (802,'sessionId','FBC27AC6DB21EC70124B8CEC2A28D61E'),
 (803,'remoteAddress','0:0:0:0:0:0:0:1'),
 (803,'sessionId','C121322BAFF2C9C7CE6773981B4E113D'),
 (804,'remoteAddress','0:0:0:0:0:0:0:1'),
 (804,'sessionId','797DA3CB08144763E16FD5ABB251334A'),
 (805,'remoteAddress','0:0:0:0:0:0:0:1'),
 (805,'sessionId','D9DD273F0B354FE746F5BBD2435DDEE9'),
 (806,'remoteAddress','0:0:0:0:0:0:0:1'),
 (806,'sessionId','B8EDF67056F12BDCBF61DB56E97B956C'),
 (807,'remoteAddress','0:0:0:0:0:0:0:1'),
 (807,'sessionId','10627C34D32CDB328D33D6567B52C539'),
 (808,'remoteAddress','0:0:0:0:0:0:0:1'),
 (808,'sessionId','1E7F33021E007B8D7FC767911FB8A966'),
 (809,'remoteAddress','0:0:0:0:0:0:0:1'),
 (809,'sessionId','84CC73CA678C515509644D2D22379113'),
 (810,'remoteAddress','0:0:0:0:0:0:0:1'),
 (810,'sessionId','29080CB1B137FE051BBC34BA8304BEFC'),
 (811,'remoteAddress','0:0:0:0:0:0:0:1'),
 (811,'sessionId','C8FC8F1D828823A711006E524C75CA0F'),
 (812,'remoteAddress','0:0:0:0:0:0:0:1'),
 (812,'sessionId','624817ADF433353E898F930AD6730932'),
 (813,'remoteAddress','0:0:0:0:0:0:0:1'),
 (813,'sessionId','80C6E42C7153E56671010526785847F4'),
 (814,'remoteAddress','0:0:0:0:0:0:0:1'),
 (814,'sessionId','DE0B276D48FB3DD774BC59414D9045EE'),
 (815,'remoteAddress','0:0:0:0:0:0:0:1'),
 (815,'sessionId','6B18908F3BA86FE44AE29E5E4BA0C08C'),
 (816,'remoteAddress','0:0:0:0:0:0:0:1'),
 (816,'sessionId','5A94B62068A70DBA75A8700BF5515D9B'),
 (817,'remoteAddress','0:0:0:0:0:0:0:1'),
 (817,'sessionId','D1602036E7CEE699D885802018C4B6E1'),
 (818,'remoteAddress','0:0:0:0:0:0:0:1'),
 (818,'sessionId','DE35477C1F32F1E5594417EDE19C82E0'),
 (819,'remoteAddress','0:0:0:0:0:0:0:1'),
 (819,'sessionId','264D3B02213A2E3959B2D6CD1C64475C'),
 (820,'remoteAddress','0:0:0:0:0:0:0:1'),
 (820,'sessionId','1392FB8EBD21BEB59F349D2E6C567547'),
 (821,'remoteAddress','0:0:0:0:0:0:0:1'),
 (821,'sessionId','B24B481FD7C7A574A03C6C49B3A0EEAC'),
 (822,'remoteAddress','0:0:0:0:0:0:0:1'),
 (822,'sessionId','6627B3F6A5AD9768A0ACD369AF3BE8D7'),
 (823,'remoteAddress','0:0:0:0:0:0:0:1'),
 (823,'sessionId','97F9959FF143FBB9FC15D3904933442C'),
 (824,'remoteAddress','0:0:0:0:0:0:0:1'),
 (824,'sessionId','273243219AFFB65D22B9EE5A96C7BA48'),
 (825,'remoteAddress','0:0:0:0:0:0:0:1'),
 (825,'sessionId','F96BB124D69BC809BB008EF4C5A2ADA2'),
 (826,'remoteAddress','0:0:0:0:0:0:0:1'),
 (826,'sessionId','28EB1F753025D9C1DFD204171E5E7220'),
 (827,'remoteAddress','0:0:0:0:0:0:0:1'),
 (827,'sessionId','744A672530494AEC5BBB585600448B83'),
 (828,'remoteAddress','0:0:0:0:0:0:0:1'),
 (828,'sessionId','5F0EFEEE2AD8F214104602365489CAFF'),
 (829,'remoteAddress','0:0:0:0:0:0:0:1'),
 (829,'sessionId','A1696A25DE1FBE3E2DCDF388A135243C'),
 (830,'remoteAddress','0:0:0:0:0:0:0:1'),
 (830,'sessionId','34FB8C436A76F6D57E344D064EF845FA'),
 (831,'remoteAddress','0:0:0:0:0:0:0:1'),
 (831,'sessionId','EC58799549808EB5C5DFBDC7937E23FB'),
 (832,'remoteAddress','0:0:0:0:0:0:0:1'),
 (832,'sessionId','BBF160E5BB1BAC4E1515AE978D95CBEE'),
 (833,'remoteAddress','0:0:0:0:0:0:0:1'),
 (833,'sessionId','27EE8E44F4F8096F474FE117E372396C'),
 (834,'remoteAddress','0:0:0:0:0:0:0:1'),
 (834,'sessionId','B902625DF20F2EE70F3618A0BAC08CA4');
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
INSERT INTO `watererp`.`jhi_persistent_token` VALUES  ('+7U0yycBuHmv+/d33EOmkw==',3,'dMDREmAGWqyusdVWQQ7vYA==','2016-04-27','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('1mZqfRNb3PGMA66LQG0Fhg==',3,'PpS3UQPmUb24wzCVFjGjNQ==','2016-05-12','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('2LjYAaKNZjnF1v/HIkjwsg==',30,'OqLtMTUUzaxLP2dP2WF4UA==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('2yjVI0YodHXMcX9UYNv9jw==',21,'kafn/6inJsopOarTM46AxA==','2016-04-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('3937fVSl0f6WWAydhNdUZg==',3,'rJfdttno/vtYeY2tsnQe+Q==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('4oZOM9WeVb8ocnwRxVvJfA==',31,'pzo3LAfZ2YQXoPdbAIfhPg==','2016-05-13','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('8qOeh9Wi3faC2r5IguaA8g==',21,'LginbCyzj1t1PN4V/ORfUw==','2016-05-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('8zfMKiZOPrVt6lXtFt/vJQ==',3,'/N+mw7gTID3HBRr0XCncMg==','2016-04-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('Ac6XW1CPScAvp4jClsPmMA==',20,'vuJUxH0AyhlMMyPTvUprag==','2016-05-12','122.170.249.158','Mozilla/5.0 (X11; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0'),
 ('aVS1+MnWV34wggZUmJvL9w==',31,'44AoSeN/P86Yz5k5cUd7lg==','2016-05-11','110.224.236.177','Mozilla/5.0 (X11; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0'),
 ('B8febmGNzSucvnszfJn8aQ==',31,'ayhyRwskOfRp1oDNOvZ9qw==','2016-05-12','122.170.249.158','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('BgqvCpZRcut7eRM9BYGikA==',30,'5iU7rwFlZQ4Gt1dHdofdkg==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('BjuoMFmske3TrzieL4XAlg==',3,'Ln/wXnsNTA7acj8Ouqoi+Q==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('BqSGG3u7I3FNEGIS4YTdnA==',30,'TVPCFvOjZvfNKQfOcumCGA==','2016-05-06','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('ChkJAcPJOneYygJ35FjR9g==',31,'6F9B28Pl70WP5rM5Q5Q0/w==','2016-05-12','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('DaK7uQT48/cKNPmvfHNy7g==',31,'nWj1dWsWeqKsrAzGShVYtA==','2016-05-12','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('DbAm0z5VPEc+EtXrkXdvKw==',3,'Yous7+mwwzsCcw6ZF1OdMg==','2016-05-11','110.224.236.177','Mozilla/5.0 (X11; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0'),
 ('e5qNbeFco2eWENKj/+RW1g==',3,'niUqrvzC03eynQfROEZDqA==','2016-05-06','192.168.1.4','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('EG3kGgtX3liShbfTTaAgiA==',5,'aD22faP9jxmf7wdLDd9hMA==','2016-05-09','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('ePbL4hkUt4E52k/aBtE/MA==',21,'UsBLX/CfktztTx7Od1cTzA==','2016-04-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0'),
 ('F8sJ+cN9mT4bgFb0qWuElQ==',30,'1r74O2dQgko/ccI8eUYcGA==','2016-04-26','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('h2bUpKDi3+XLdwZvYfctSg==',16,'0kDBdHEYWPkiv5WGMUyl8w==','2016-04-27','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('hfGksqBgjUnqndHWJ3/5wA==',30,'FbXszKhHyDuqmM8FnVRRUw==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('HoUZ0JlyDpz02Gjz/PKAIw==',15,'uK6UIkCTlVGzDiCNbYrLpw==','2016-04-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('hpx7sHez6/mnrjnhZ3DX+A==',3,'VqaJz6VLNIqEid/PNNJdhw==','2016-05-10','196.41.61.82','Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('ihEf0pcPskdgqK7cOFzTJw==',21,'TdMRgg17krhlPYtJrkjWXA==','2016-04-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('JkO1Egyw+3JpnCDvarFSJQ==',31,'+rmweMdpNs/d3K82VBmA3Q==','2016-05-12','192.168.1.5','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('Jt1tQaRUywvgCflDNZISJg==',3,'kDuQ5LaUwKjGAmkNe8eMCg==','2016-04-27','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('k2Tiai7BXpQHEwfZgrhj6g==',16,'EoRRRHI4yUgdB7cEkJz0rQ==','2016-04-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0'),
 ('lqqkRZHMgCV5ZRZsdmOctA==',3,'Sqs4OVQ6a8Sm5+xrE5sxsg==','2016-04-13','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('mFxt08kJ+MznP+HasUarJQ==',31,'HwEBWdjwSvPHSnqpA1yNUA==','2016-05-13','192.168.1.5','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586'),
 ('Mi6MLXJPl0/RJj05s09iMQ==',3,'NerOglc8HIpbaSpJJ3m5fA==','2016-05-04','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('mN78rTbpO1zJ3NSIXBQ2wg==',30,'A009nxRkiVkn8Up1bOtQfQ==','2016-05-04','192.168.1.23','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('N5tURezUqhBwraYfORpoOg==',3,'Z7l8+8dlatnIze1ZddxZoQ==','2016-05-09','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('ny63voHlh1g5DzOlyLwYTQ==',27,'ZAFbKrGGRPl7EtKIpaxgqA==','2016-04-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('odIcxtIiUMAAdHvLI1fZ0g==',3,'6hyclrvKtc4YTW6j6ulqLw==','2016-05-11','110.224.236.177','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586'),
 ('omL/EzrvAvwVEbBKTEH5aw==',30,'CseC8BLe5lXIEbYKOM4Abw==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('pMyOIrgHgrCeHf9U1k1jig==',15,'JN5k9M/NYqnv3OyrQgjchw==','2016-04-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('PUZqawt2TNbtv0rm6Prqrw==',15,'FMAVvl/E4wu0GlqN4dGvyw==','2016-05-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('q7XsLPGhgLfQI03IAy+sMg==',15,'f96/QO7HrHkS+8H1N6VIWw==','2016-05-09','110.224.230.23','Mozilla/5.0 (X11; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0'),
 ('QNKrooOpjWJXpm7AAOEFQQ==',20,'DMvylSUGtrldSFzxyjrKXw==','2016-04-19','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('QnOVEcUN7EljG4/UD6aXXA==',3,'YZtjIP/DY6zaxa37xPsGWQ==','2016-05-13','192.168.1.5','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('r5Y5nWr/bmCMnQtyORiTgQ==',5,'y8SLeTqVqOBC3CVUr7bcmg==','2016-05-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('T53sqN50j/yG+Xy1nwQZEA==',3,'aNXX3d+8jixBot4xU/iyMA==','2016-05-11','110.224.236.177','Mozilla/5.0 (X11; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0'),
 ('tSOtngv/3diHOsCJo6rYOg==',31,'Epepxgsy2Z5mG5jhhc9fQw==','2016-05-12','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('uuRMPqbtEBc6QVsYIXN2pA==',21,'oeCuCAeQjxjpbYtfS8o4/w==','2016-04-26','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('V9rQqqIRC/goYf0v4KMZ3g==',30,'d8YPqVoG4JnyxyH1XvqOyA==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('vCltSl9MsKd488jrCU8XQw==',5,'m9uZJi+jxdO842VU7ckQzA==','2016-05-10','122.170.237.113','Mozilla/5.0 (X11; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0'),
 ('vehx07ryxugUHCENmGBUSA==',30,'tSECGXgNhKq8auv6nZmQuw==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('vnR7DP8oSYXDmF+mnmU1kg==',21,'/81Dy452hRlZFVFwMvjsag==','2016-04-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('w19dFvKWblZHPQ4qBmRwOQ==',3,'niw5it4bdTF1FOsKFYODRQ==','2016-04-21','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('Wcl3JtiPVeESKHgkm9DWVA==',30,'2E/wOFh/Y8+Y89I+G+sIQA==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('wHOaPe95y0bFDhv7rwp+pQ==',3,'G5PATNutbflLHCMZntNdzA==','2016-04-21','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('wlsxEVx3DOGE5elnLj7U8Q==',3,'OA+2jIAtEEIKEr1jUU54UA==','2016-04-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('XQG+pOIkmGVy/h3BRMq+Pw==',30,'rmKrD1xN8nP8aLEDALqvFA==','2016-04-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('YCy7O8uNAqrw7SFOZCRH7Q==',15,'96gB2HojQE8BSXPuGXHk7A==','2016-04-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('yeGR7p45ZYo74KGrWntAZA==',3,'ytU215AgvrBXIiLMgsecaw==','2016-04-15','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('ynZOMTxODvqkNvAvZyrabw==',30,'/neHN/ixIp/JbDwgJM2N3A==','2016-04-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('ZeWv40JzO9chd/3DLw5QZw==',3,'7qpybtDxl+Azh/kyfSI5dg==','2016-04-21','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('zFE08dIb3ttfqV37i6eHKA==',3,'4X2BHKVSVF8Lb6nvKk79IQ==','2016-04-12','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('zhd4rwE9C7X22qgUPtBn5A==',3,'cv4E4LDutK6UEVxu9T3kow==','2016-05-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('zti4U4Exyo6eEJVADM9M2A==',31,'TCPjsqFMABVuo1YA4kMgJQ==','2016-05-13','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0');
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_user`
--

/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
LOCK TABLES `jhi_user` WRITE;
INSERT INTO `watererp`.`jhi_user` VALUES  (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost',0x01,'en',NULL,NULL,'system','2016-02-25 00:07:37',NULL,'admin','2016-03-07 20:25:39'),
 (2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost',0x01,'en',NULL,NULL,'system','2016-02-25 00:07:37',NULL,NULL,NULL),
 (3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost',0x01,'en',NULL,NULL,'system','2016-02-25 00:07:37',NULL,NULL,NULL),
 (4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost',0x01,'en',NULL,NULL,'system','2016-02-25 00:07:37',NULL,NULL,NULL),
 (5,'customer','$2a$10$TYs/wcvo6fAPu0Xdt.jP3.pol1TJpvr13XvYIlBDzlut/Ytlsn/AO','customer','customer','customer@localhost',0x01,'en','NULL',NULL,'anonymousUser','2016-03-05 00:01:04',NULL,'admin','2016-03-08 15:30:31'),
 (6,'sf0006','$2a$10$.bcy9Ujl3NqgDj6zjXsRHe.UlXABdJyDbcByQ8nX9MvrHAwSYH9xi','Bhaskar','Reddy','sf0006@localhost',0x01,'en','74075798038214678044',NULL,'anonymousUser','2016-04-27 15:51:22',NULL,'admin','2016-03-18 18:23:49'),
 (7,'sf0007','$2a$10$FDsPyaVUL9uFAR5vgWpX2ulAqEn5TJpoZjiCcGYqXO5iDkr11Ae3e','Krishna','Reddy','sf0007@localhost',0x01,'en','96267878389108527398',NULL,'anonymousUser','2016-04-26 16:24:31',NULL,'admin','2016-03-18 18:23:50'),
 (8,'sf0008','$2a$10$/M8CkSWBuUlPxZavXb3h/.YQ.Q6lDArLBo5K/dIYcW6mdTfIf45bm','Mohib','Khan','sf0008@localhost',0x01,'en','54288279092286381964',NULL,'anonymousUser','2016-04-26 16:24:31',NULL,'admin','2016-03-18 18:23:51'),
 (9,'sf0009','$2a$10$nSqZo6ISDISQmxLFwbsq3u92OT3hOVS6mkjFlsDamxGInc8JBPtqq','Simon','Lupuga','sf0009@localhost',0x01,'en','97951374793812547574',NULL,'anonymousUser','2016-03-18 18:15:19',NULL,'sf0009','2016-03-28 18:15:10'),
 (10,'sf0010','$2a$10$rCzfnzFSb0dL3ocuA9RViuOaF1Gq5Vc0XzS7GSUKwBCv5tXI./39S','Claudius','Kaje','sf0010@localhost',0x01,'en','00161949524540976078',NULL,'anonymousUser','2016-03-18 18:15:54',NULL,'sf0010','2016-03-28 18:15:53'),
 (11,'sf0011','$2a$10$H7MmlGeb073RorJ69a1fdudLKTRLUFgkmFFeJ5xjCmtyjm.srVgDO','Vacant','vacant','sf0011@localhost',0x01,'en','33673054004780142550',NULL,'anonymousUser','2016-03-18 18:16:15',NULL,'sf0011','2016-03-28 18:16:42'),
 (12,'sf0012','$2a$10$sB2ETKC9vs0uljjuRtez6e0mhtaFDeJSyZGEwGIaGddJ34kRGIEpe','Rohit','Sharma','sf0012@localhost',0x01,'en','71560095329758703944',NULL,'anonymousUser','2016-04-26 16:24:31',NULL,'admin','2016-03-18 18:23:56'),
 (13,'sf0013','$2a$10$okQIlNvGPOcBOadT8iyiJuC3vBGmXFtLe3UrvZpB33CWekNcdI/bu','Adoph','Shavu','sf0013@localhost',0x01,'en','91544376107529154273',NULL,'anonymousUser','2016-03-18 18:16:56',NULL,'sf0013','2016-03-28 18:17:31'),
 (14,'sf0014','$2a$10$qEvPMWpVpl54ndQdDE1FbuptYEfG9eqJbcYR7/DDXjSmaekdpwPyG','Herry','Makala','sf0014@localhost',0x01,'en','98218340790438067335',NULL,'anonymousUser','2016-03-18 18:17:34',NULL,'sf0014','2016-03-28 18:18:08'),
 (15,'sf0015','$2a$10$/396VihfwsI9iWd/UIEXKe7c3xbjwuWiePbUdUQL1Ug/tq9X2fy.i','Mbike','Jones','sf0015@localhost',0x01,'en','78744568076959050873',NULL,'anonymousUser','2016-03-18 18:17:51',NULL,'sf0015','2016-03-28 18:18:33'),
 (16,'sf0016','$2a$10$.YzGvqWeUSCQR5q2mtAl5u4ZVjBDmGCRh7xSbb5njETwx1ZwIN2bm','Fred','Selebwa','sf0016@localhost',0x01,'en','43535432288382530210',NULL,'anonymousUser','2016-03-18 18:18:46',NULL,'sf0016','2016-03-28 18:19:12'),
 (17,'sf0017','$2a$10$YDx44W8ShgQ0Jt7tVJXnWOMlyFJ3ernUc4Wh.yo.vVF6lYpGThccu','Eliezer','Josiah','sf0017@localhost',0x01,'en','06698912264567056315',NULL,'anonymousUser','2016-03-18 18:19:06',NULL,'sf0017','2016-03-28 18:19:56'),
 (18,'sf0018','$2a$10$if7uNqLVpZ.V5X/OkOes7uZ4gbDpwlrFmesdATE4PQrREuGff1c8G','Hellena','Mtembeje','sf0018@localhost',0x01,'en','38952305015731452387',NULL,'anonymousUser','2016-03-18 18:19:28',NULL,'sf0018','2016-03-28 18:20:47'),
 (19,'sf0019','$2a$10$43TTSOPygHilSwG5jA3joussQk3.vL.0B88bmbWGAiEyThp/0MLhm','Charles','Kiwely','sf0019@localhost',0x01,'en','80544871706487966991',NULL,'anonymousUser','2016-03-18 18:19:48',NULL,'sf0019','2016-03-28 18:21:19'),
 (20,'sf0020','$2a$10$UWsog7PJ9mJ5ALW3oT1qjuLsx4r6TtpC2.OtCi6D/qgMvD1pXuFiC','Virat','Kohli','sf0020@localhost',0x01,'en','83805380491517726753',NULL,'anonymousUser','2016-04-26 16:24:31',NULL,'admin','2016-03-18 18:24:00'),
 (21,'sf0021','$2a$10$fyfyZSZ/r94V30rK2V72r.Rs0yzTW81oB6mAvlq3Xz5OTBpBK0DJC','Salome','Mtwale','sf0021@localhost',0x01,'en','82028831560154180774',NULL,'anonymousUser','2016-03-18 18:21:39',NULL,'sf0021','2016-03-28 18:22:03'),
 (22,'sf0022','$2a$10$LV2mBS8VNveWYejGFcCh8OT2JoX.4oeEkPV/w5q5EDyY3dCQjUuGW','Mbaraka','Shemueta','sf0022@localhost',0x01,'en','29507620030240498935',NULL,'anonymousUser','2016-03-18 18:21:54',NULL,'sf0022','2016-03-28 18:23:17'),
 (23,'sf0023','$2a$10$ozkcRMy/Vjj6m.8O3XRg.OwDr3QybLS3qcX8/mZD.q4lzwVOxfPKW','Olivia','Masangya','sf0023@localhost',0x01,'en','73060005748380646738',NULL,'anonymousUser','2016-03-18 18:22:18',NULL,'sf0023','2016-03-28 18:23:58'),
 (24,'sf0024','$2a$10$0YPZsnW/yPzP5mx34t9Gse.x/.bAfB6nTTSBdvhifbYNzjsPQxeeW','Shikhar','Dhawan','sf0024@localhost',0x01,'en','76650231398888348776',NULL,'anonymousUser','2016-04-26 16:24:31',NULL,'admin','2016-03-18 18:24:24'),
 (25,'sf0025','$2a$10$mUYlNHHcw0mpJiioW/p/0Ofx9qAPI6..bRfgSFHvo4ImvcB2CVQ96','Suresh','Raina','sf0025@localhost',0x01,'en','42984014546333704148',NULL,'anonymousUser','2016-04-26 16:24:30',NULL,'admin','2016-03-18 18:24:25'),
 (26,'sf0026','$2a$10$lnV2mPyzb/hLNWtYr7NeBeSe7SErEn4RwK3/Lzr6Oxjqkdj/yOUvS','Yuvraj','Singh','sf0026@localhost',0x01,'en','30926903415314102347',NULL,'anonymousUser','2016-04-26 16:24:30',NULL,'admin','2016-03-18 18:24:27'),
 (27,'sf0027','$2a$10$F58do6ARzxNnWwXFF3WVa.oH4Cnwfu9GGESf5c64Av7PnAVDDHQfu','Nobert','Kamba','sf0027@localhost',0x01,'en','88464590975638599993',NULL,'anonymousUser','2016-03-18 18:23:16',NULL,'sf0027','2016-03-28 18:27:46'),
 (28,'sf0028','$2a$10$K.A7jrXE..8CIm7SoeVMBepZyR2WdpZeQpPyUOfY429jeBaL.OMju','Francis','Hume','sf0028@localhost',0x01,'en','71805558970907066489',NULL,'anonymousUser','2016-03-18 18:23:33',NULL,'sf0028','2016-03-28 18:28:11'),
 (29,'sf0029','$2a$10$L1IXkaggwuSLeeF0fRR9CeMb/Qq6IM7EXLJI/jsdfFSOUQWf4oPHS','Ajinkya','Rahane','sf0029@localhost',0x01,'en','68914493240806760364',NULL,'anonymousUser','2016-04-26 16:24:30',NULL,'admin','2016-03-21 21:50:23'),
 (30,'billrunuser','$2a$10$I.tUzJDgXrahq.VwnsPWB.0ttM55FfufrXt1LJ1wJHQdNHQjXj8em','Bill Run','User','srinigattu@gmail.com',0x01,'en',NULL,NULL,'admin','2016-04-16 11:18:43',NULL,'anonymousUser','2016-04-16 11:23:57'),
 (31,'billrunmgr','$2a$10$8OUigJFHCFBPGqcDxXaEw./lNGSnb9NalwH4b1nzUawcfBTQlVMEm','Bill Run','Manager','srinivas@callippus.co.uk',0x01,'en',NULL,NULL,'admin','2016-04-16 22:55:28',NULL,'anonymousUser','2016-04-16 23:00:29');
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
 (30,'ROLE_BILLRUN'),
 (31,'ROLE_BILLRUN_MANAGER'),
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
 (29,'ROLE_USER'),
 (30,'ROLE_USER'),
 (31,'ROLE_USER');
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
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `company_code_id` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`material_master`
--

/*!40000 ALTER TABLE `material_master` DISABLE KEYS */;
LOCK TABLES `material_master` WRITE;
INSERT INTO `watererp`.`material_master` VALUES  (2,'Threading Tape',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (3,'G.S. Pipe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'30000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (4,'Pipe Polly',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (5,'Coupling',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (6,'Bib Tape',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (7,'Tee',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3500.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (8,'Union',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (9,'Elbow',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-23 05:30:00','2016-03-30 05:30:00',NULL),
 (10,'Nipple',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (11,'Polly Connector',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2500.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (12,'Plain Socket',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (13,'Reducing Socket/Bush',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (14,'Stop Cock',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL),
 (15,'Clamp Saddle',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3000.00',NULL,NULL,'2016-03-30 05:30:00','2016-03-30 05:30:00',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`menu_item`
--

/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
LOCK TABLES `menu_item` WRITE;
INSERT INTO `watererp`.`menu_item` VALUES  (1,'Module','#/modules','2016-03-09 05:30:00'),
 (2,'Application Form','#/applicationTxns/new','2016-03-10 05:30:00'),
 (3,'Menu Items','#/menuItems','2016-03-10 05:30:00'),
 (4,'Url','#/urls','2016-03-10 05:30:00'),
 (5,'Menu Item 2 Urls','#/menuItem2Urls','2016-03-10 05:30:00'),
 (6,'Module 2 Menu Items','#/module2MenuItems','2016-03-10 05:30:00'),
 (7,'Url 2 Roles','#/url2Roles','2016-03-10 05:30:00'),
 (8,'Application Details','#/applicationTxns','2016-03-10 05:30:00'),
 (9,'Manage Cash Point','#/manageCashPoints','2016-03-10 05:30:00'),
 (10,'Feasibility Study','#/feasibilityStudys','2016-03-11 05:30:00'),
 (11,'Prepare Proceedings','#/proceedingss','2016-03-15 05:30:00'),
 (12,'Proceeding Form','#/proceedingss/new','2016-03-22 05:30:00'),
 (13,'Item Category Master','#/itemCategoryMasters','2016-03-30 05:30:00'),
 (14,'Item Sub Category Masters','#/itemSubCategoryMasters','2016-03-30 05:30:00'),
 (15,'Item Code Masters','#/itemCodeMasters','2016-03-30 05:30:00'),
 (16,'Item Company Masters','#/itemCompanyMasters','2016-03-30 05:30:00'),
 (17,'Item Sub Code Master','#/itemSubCodeMasters','2016-03-30 05:30:00'),
 (18,'Material Master','#/materialMasters','2016-03-30 05:30:00'),
 (19,'Sib Entry','#/sibEntrys','2016-03-30 05:30:00'),
 (20,'Receipt','#/receipts','2016-04-01 05:30:00'),
 (21,'Access List','#/accessLists','2016-03-15 05:30:00'),
 (22,'Bill Full Details','#/billFullDetailss','2016-03-15 05:30:00'),
 (23,'Collection Details','#/collDetailss/new','2016-03-15 05:30:00'),
 (24,'Current Users','#/currentUserss','2016-03-15 05:30:00'),
 (25,'Customer Details','#/custDetailss','2016-03-15 05:30:00'),
 (26,'Terminal','#/terminals','2016-03-15 05:30:00'),
 (27,'Terminal Log','#/terminalLogs','2016-03-15 05:30:00'),
 (28,'Version','#/versions','2016-03-15 05:30:00'),
 (29,'Complaint Type Master','#/complaintTypeMasters','2016-03-29 05:30:00'),
 (30,'Customer Complaints','#/customerComplaintss','2016-03-29 05:30:00'),
 (31,'Bill Run Master','#/billRunMasters','2016-04-01 05:30:00'),
 (32,'New Bill Run','#/billRunMasters/new','2016-04-01 05:30:00'),
 (33,'Meter Change','#/meterChanges','2016-04-19 05:30:00'),
 (34,'Without Meter','#/applicationTxns/withoutMeter','2016-04-20 05:30:00'),
 (35,'Bill Details','#/billDetailss/new','2016-04-18 05:30:00'),
 (36,'Merchant Master','#/merchantMasters','2016-04-18 05:30:00'),
 (37,'Online Payment Order','#/onlinePaymentOrders','2016-04-18 05:30:00'),
 (38,'Online Payment Response','#/onlinePaymentResponses','2016-04-18 05:30:00'),
 (39,'Online Payment Callback','#/onlinePaymentCallbacks','2016-04-18 05:30:00'),
 (40,'Customer Category Change','#/customers/categoryChange','2016-05-03 05:30:00'),
 (41,'Pipe Size Change','#/customers/pipeSizeChange','2016-05-03 05:30:00'),
 (42,'Name Change','#/customers/nameChange','2016-05-03 05:30:00'),
 (43,'Connection Termination','#/connectionTerminates/new','2016-05-03 05:30:00'),
 (44,'Application Type Master','#/application_type_masters','2016-05-03 05:30:00'),
 (45,'Bill Run Details','#/bill_run_detailss','2016-05-03 05:30:00'),
 (46,'Cash Book Master','#/cash_book_masters','2016-05-03 05:30:00'),
 (47,'Category Master','#/category_masters','2016-05-03 05:30:00'),
 (48,'Category Pipe Size Mapping','#/category_pipe_size_mappings','2016-05-03 05:30:00'),
 (49,'Collection Type Master','#/collection_type_masters','2016-05-03 05:30:00'),
 (50,'Configuration Details','#/configuration_detailss','2016-05-03 05:30:00'),
 (51,'Connection Type Master','#/connection_type_masters','2016-05-03 05:30:00'),
 (52,'Customer Meter Mapping','#/cust_meter_mappings','2016-05-03 05:30:00'),
 (53,'Customer','#/customers','2016-05-03 05:30:00'),
 (54,'Data Base Change Log','#/databasechangelogs','2016-05-03 05:30:00'),
 (55,'Data Base Change Log Lock','#/databasechangeloglocks','2016-05-03 05:30:00'),
 (56,'Department Type Master','#/department_type_masters','2016-05-03 05:30:00'),
 (57,'Departments Hierarchy','#/departments_hierarchys','2016-05-03 05:30:00'),
 (58,'Departments Master','#/departments_masters','2016-05-03 05:30:00'),
 (59,'Design Category Master','#/desig_category_masters','2016-05-03 05:30:00'),
 (60,'Designation Mappings','#/designation_mappingss','2016-05-03 05:30:00'),
 (61,'Designation Master','#/designation_masters','2016-05-03 05:30:00'),
 (62,'Division Master','#/division_masters','2016-05-03 05:30:00'),
 (63,'Docket Code','#/docket_codes','2016-05-03 05:30:00'),
 (64,'Employee Master','#/emp_masters','2016-05-03 05:30:00'),
 (65,'Employee Role Mapping ','#/emp_role_mappings','2016-05-03 05:30:00'),
 (66,'Expense Detaills','#/expenseDetailss/new','2016-05-03 05:30:00'),
 (67,'Feasibility Status','#/feasibility_statuss','2016-05-03 05:30:00'),
 (68,'File Number','#/file_numbers','2016-05-03 05:30:00'),
 (69,'File Upload Master','#/file_upload_masters','2016-05-03 05:30:00'),
 (70,'Group Master','#/group_masters','2016-05-03 05:30:00'),
 (71,'Id Proof Master','#/id_proof_masters','2016-05-03 05:30:00'),
 (72,'Instument Issuer Master ','#/instrument_issuer_masters','2016-05-03 05:30:00'),
 (73,'Item Details','#/item_detailss','2016-05-03 05:30:00'),
 (74,'Item Required','#/item_requireds','2016-05-03 05:30:00'),
 (75,'Main Sewerage Size','#/main_sewerage_sizes','2016-05-03 05:30:00'),
 (76,'Main Water Size','#/main_water_sizes','2016-05-03 05:30:00'),
 (77,'Make Of Pipe','#/make_of_pipes','2016-05-03 05:30:00'),
 (78,'Meter Details','#/meter_detailss','2016-05-03 05:30:00'),
 (79,'Meter Status','#/meter_statuss','2016-05-03 05:30:00'),
 (80,'MMG Material Master','#/mmg_material_masters','2016-05-03 05:30:00'),
 (81,'MMG Terms Master','#/mmg_terms_masters','2016-05-03 05:30:00'),
 (82,'Organization Hierarchy','#/org_hierarchys','2016-05-03 05:30:00'),
 (83,'Organization Role Hierarchy','#/org_role_hierarchys','2016-05-03 05:30:00'),
 (84,'Organization Role Instance','#/org_role_instances','2016-05-03 05:30:00'),
 (85,'Organization Role Master','#/org_roles_masters','2016-05-03 05:30:00'),
 (86,'Payment Types','#/payment_typess','2016-05-03 05:30:00'),
 (87,'Percentage Master','#/percentage_masters','2016-05-03 05:30:00'),
 (88,'Piipe Size Master','#/pipe_size_masters','2016-05-03 05:30:00'),
 (89,'Proceedings','#/proceedingss','2016-05-03 05:30:00'),
 (90,'Reallotment','#/re_allotments','2016-05-03 05:30:00'),
 (91,'Request Design Work Flow Mapping','#/req_desig_workflow_mappings','2016-05-03 05:30:00'),
 (92,'Request Organization Work Flow Mapping','#/req_org_workflow_mappings','2016-05-03 05:30:00'),
 (93,'Request Master','#/request_masters','2016-05-03 05:30:00'),
 (94,'Request Work Flow History','#/request_workflow_historys','2016-05-03 05:30:00'),
 (95,'Request Work Flow mapping','#/request_workflow_mappings','2016-05-03 05:30:00'),
 (96,'Revenue Type Master','#/revenue_type_masters','2016-05-03 05:30:00'),
 (97,'Role  Work Flow Mapping','#/role_workflow_mappings','2016-05-03 05:30:00'),
 (98,'Schema Master','#/scheme_masters','2016-05-03 05:30:00'),
 (99,'Revenue Details','#/revDetails/new','2016-05-03 05:30:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

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
 (19,19,19),
 (20,20,20),
 (21,21,21),
 (22,22,22),
 (23,23,23),
 (24,24,24),
 (25,25,25),
 (26,26,26),
 (27,27,27),
 (28,28,28),
 (29,29,29),
 (30,30,30),
 (31,31,31),
 (32,32,32),
 (33,33,33),
 (34,34,34),
 (35,35,35),
 (36,36,39),
 (37,37,40),
 (38,38,41),
 (39,39,42),
 (40,40,43),
 (41,41,44),
 (42,42,45),
 (43,43,46),
 (44,99,101),
 (45,66,69);
UNLOCK TABLES;
/*!40000 ALTER TABLE `menu_item2_url` ENABLE KEYS */;


--
-- Definition of table `watererp`.`merchant_master`
--

DROP TABLE IF EXISTS `watererp`.`merchant_master`;
CREATE TABLE  `watererp`.`merchant_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_code` varchar(255) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `merchant_key` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`merchant_master`
--

/*!40000 ALTER TABLE `merchant_master` DISABLE KEYS */;
LOCK TABLES `merchant_master` WRITE;
INSERT INTO `watererp`.`merchant_master` VALUES  (2,'Test001','testmerchant','5b56ca5b-a882-4224-b3e7-b558e93e6cb0','TSh');
UNLOCK TABLES;
/*!40000 ALTER TABLE `merchant_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`meter_change`
--

DROP TABLE IF EXISTS `watererp`.`meter_change`;
CREATE TABLE  `watererp`.`meter_change` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `can` varchar(255) DEFAULT NULL,
  `reason_for_change` varchar(255) DEFAULT NULL,
  `prev_meter_reading` float DEFAULT NULL,
  `new_meter_reading` float DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cust_details_id` bigint(20) DEFAULT NULL,
  `prev_meter_no_id` bigint(20) DEFAULT NULL,
  `new_meter_no_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_meterchange_custdetails_id` (`cust_details_id`),
  KEY `fk_meterchange_prevmeterno_id` (`prev_meter_no_id`),
  KEY `fk_meterchange_newmeterno_id` (`new_meter_no_id`),
  KEY `fk_meterchange_user_id` (`user_id`),
  CONSTRAINT `fk_meterchange_custdetails_id` FOREIGN KEY (`cust_details_id`) REFERENCES `cust_details` (`id`),
  CONSTRAINT `fk_meterchange_newmeterno_id` FOREIGN KEY (`new_meter_no_id`) REFERENCES `meter_details` (`id`),
  CONSTRAINT `fk_meterchange_prevmeterno_id` FOREIGN KEY (`prev_meter_no_id`) REFERENCES `meter_details` (`id`),
  CONSTRAINT `fk_meterchange_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `watererp`.`meter_change`
--

/*!40000 ALTER TABLE `meter_change` DISABLE KEYS */;
LOCK TABLES `meter_change` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `meter_change` ENABLE KEYS */;


--
-- Definition of table `watererp`.`meter_details`
--

DROP TABLE IF EXISTS `watererp`.`meter_details`;
CREATE TABLE  `watererp`.`meter_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meter_id` varchar(255) NOT NULL,
  `meter_type` varchar(255) DEFAULT NULL,
  `meter_make` varchar(255) DEFAULT NULL,
  `min` float DEFAULT NULL,
  `max` float DEFAULT NULL,
  `meter_status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_meterdetails_meterstatus_id` (`meter_status_id`),
  CONSTRAINT `fk_meterdetails_meterstatus_id` FOREIGN KEY (`meter_status_id`) REFERENCES `meter_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`meter_details`
--

/*!40000 ALTER TABLE `meter_details` DISABLE KEYS */;
LOCK TABLES `meter_details` WRITE;
INSERT INTO `watererp`.`meter_details` VALUES  (1,'MeterId1','type1','make1',1,999999,1),
 (2,'MeterId2','Type2','make2',1,99999,1),
 (3,'MeterId3','type3','make3',1,99999,1),
 (4,'MeterId4','type4','make4',1,99999,1),
 (5,'MeterId5','type5','make5',1,99999,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `meter_details` ENABLE KEYS */;


--
-- Definition of table `watererp`.`meter_status`
--

DROP TABLE IF EXISTS `watererp`.`meter_status`;
CREATE TABLE  `watererp`.`meter_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`meter_status`
--

/*!40000 ALTER TABLE `meter_status` DISABLE KEYS */;
LOCK TABLES `meter_status` WRITE;
INSERT INTO `watererp`.`meter_status` VALUES  (1,'Allotted'),
 (2,'Unalloted'),
 (3,'Processing');
UNLOCK TABLES;
/*!40000 ALTER TABLE `meter_status` ENABLE KEYS */;


--
-- Definition of table `watererp`.`mmg_material_master`
--

DROP TABLE IF EXISTS `watererp`.`mmg_material_master`;
CREATE TABLE  `watererp`.`mmg_material_master` (
  `id` bigint(10) NOT NULL,
  `material_name` varchar(200) DEFAULT NULL,
  `consumable_flag` varchar(5) DEFAULT NULL,
  `uom_id` bigint(10) DEFAULT NULL,
  `category_id` bigint(10) DEFAULT NULL,
  `sub_category_id` bigint(10) DEFAULT NULL,
  `item_code_id` bigint(10) DEFAULT NULL,
  `item_sub_code_id` bigint(10) DEFAULT NULL,
  `rate_contract_flag` varchar(5) DEFAULT NULL,
  `unit_rate` bigint(126) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `status` bigint(10) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `company_code_id` bigint(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`mmg_material_master`
--

/*!40000 ALTER TABLE `mmg_material_master` DISABLE KEYS */;
LOCK TABLES `mmg_material_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `mmg_material_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`mmg_terms_master`
--

DROP TABLE IF EXISTS `watererp`.`mmg_terms_master`;
CREATE TABLE  `watererp`.`mmg_terms_master` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `status` bigint(10) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `tax_type_id` bigint(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`mmg_terms_master`
--

/*!40000 ALTER TABLE `mmg_terms_master` DISABLE KEYS */;
LOCK TABLES `mmg_terms_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `mmg_terms_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`module`
--

DROP TABLE IF EXISTS `watererp`.`module`;
CREATE TABLE  `watererp`.`module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `server_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`module`
--

/*!40000 ALTER TABLE `module` DISABLE KEYS */;
LOCK TABLES `module` WRITE;
INSERT INTO `watererp`.`module` VALUES  (1,'Role Management',1,'2016-03-09 05:30:00','roleManagement'),
 (2,'Connection',2,'2016-03-10 05:30:00','connection'),
 (3,'Items Details',3,'2016-03-30 05:30:00','itemDetails'),
 (4,'Billing and Collection',4,'2016-03-15 05:30:00','billingAndCollection'),
 (5,'Customer Care',5,'2016-03-29 05:30:00','customerCare'),
 (6,'Bill Cycle Run',6,'2016-04-04 05:30:00','billCycleRun'),
 (7,'Online Payment',7,'2016-04-04 05:30:00','onlinePayment');
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

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
 (19,7,3,19),
 (20,7,2,20),
 (21,1,4,21),
 (22,2,4,22),
 (23,3,4,23),
 (24,4,4,24),
 (25,5,4,25),
 (26,6,4,26),
 (27,7,4,27),
 (28,8,4,28),
 (29,2,5,29),
 (30,1,5,30),
 (31,2,6,31),
 (32,1,6,32),
 (33,8,2,33),
 (34,9,2,34),
 (35,2,4,35),
 (36,1,7,36),
 (37,2,7,37),
 (38,3,7,38),
 (39,4,7,39),
 (40,10,2,40),
 (41,11,2,41),
 (42,12,2,42),
 (43,13,2,43),
 (44,9,4,99),
 (45,10,4,66);
UNLOCK TABLES;
/*!40000 ALTER TABLE `module2_menu_item` ENABLE KEYS */;


--
-- Definition of table `watererp`.`online_payment_callback`
--

DROP TABLE IF EXISTS `watererp`.`online_payment_callback`;
CREATE TABLE  `watererp`.`online_payment_callback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `currency` varchar(255) DEFAULT NULL,
  `payment_mode` varchar(255) DEFAULT NULL,
  `service_code` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `response_code` varchar(255) DEFAULT NULL,
  `total_amount_paid` float DEFAULT NULL,
  `user_defined_field` varchar(255) DEFAULT NULL,
  `merchant_txn_ref` varchar(255) DEFAULT NULL,
  `merchant_master_id` bigint(20) DEFAULT NULL,
  `online_payment_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_onlinepaymentcallback_merchantmaster_id` (`merchant_master_id`),
  KEY `fk_onlinepaymentcallback_onlinepaymentorder_id` (`online_payment_order_id`),
  CONSTRAINT `fk_onlinepaymentcallback_merchantmaster_id` FOREIGN KEY (`merchant_master_id`) REFERENCES `merchant_master` (`id`),
  CONSTRAINT `fk_onlinepaymentcallback_onlinepaymentorder_id` FOREIGN KEY (`online_payment_order_id`) REFERENCES `online_payment_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`online_payment_callback`
--

/*!40000 ALTER TABLE `online_payment_callback` DISABLE KEYS */;
LOCK TABLES `online_payment_callback` WRITE;
INSERT INTO `watererp`.`online_payment_callback` VALUES  (1,'TSh','TIGOPESADIR','TESTS001',NULL,'2009',280,'123','6828618857',2,11);
UNLOCK TABLES;
/*!40000 ALTER TABLE `online_payment_callback` ENABLE KEYS */;


--
-- Definition of table `watererp`.`online_payment_order`
--

DROP TABLE IF EXISTS `watererp`.`online_payment_order`;
CREATE TABLE  `watererp`.`online_payment_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_code` varchar(255) NOT NULL,
  `amount` float NOT NULL,
  `pay_by` varchar(255) NOT NULL,
  `user_defined_field` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` bigint(20) NOT NULL,
  `order_time` timestamp NULL DEFAULT NULL,
  `merchant_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_onlinepaymentorder_merchantmaster_id` (`merchant_master_id`),
  CONSTRAINT `fk_onlinepaymentorder_merchantmaster_id` FOREIGN KEY (`merchant_master_id`) REFERENCES `merchant_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`online_payment_order`
--

/*!40000 ALTER TABLE `online_payment_order` DISABLE KEYS */;
LOCK TABLES `online_payment_order` WRITE;
INSERT INTO `watererp`.`online_payment_order` VALUES  (1,'TESTS001',12354,'TIGOPESADIR','617783704','test@gmail.com',1234567890,'2016-05-02 22:17:25',2),
 (3,'TESTS001',1234,'TIGOPESADIR','617999021','test@gmail.com',123456789,'2016-05-03 13:53:56',2),
 (4,'TESTS001',123,'TIGOPESADIR','061224612','test@gmail.com',123456789,'2016-05-03 15:08:13',2),
 (5,'TESTS001',12,'TIGOPESADIR','617783704','test@gmail.com',1234567890,'2016-05-03 15:10:47',2),
 (6,'TESTS001',12,'TIGOPESADIR','617999021','test@gmail.com',123455678,'2016-05-03 15:18:46',2),
 (7,'TESTS001',1234,'TIGOPESADIR','617999021','test@gmail.com',1234567890,'2016-05-03 15:52:51',2),
 (8,'TESTS001',123,'TIGOPESADIR','617790069','test@gmail.com',1234567890,'2016-05-03 16:29:13',2),
 (9,'TESTS001',123,'TIGOPESADIR','617999021','test@gmail.com',123456789,'2016-05-03 16:30:13',2),
 (10,'TESTS001',1234,'TIGOPESADIR','617783704','test@gmail.com',123456789,'2016-05-03 22:17:30',2),
 (11,'TESTS001',123,'TIGOPESADIR','617999021','test@gmail.com',123456789,'2016-05-03 22:35:45',2),
 (12,'TESTS001',1000,'TIGOPESADIR','02020002','ravimrb@yahoo.com',9949976058,'2016-05-10 10:31:32',2),
 (13,'TESTS001',12000,'TIGOPESADIR','02020004','patrick.mbago@maxcom.com',655134858,'2016-05-10 19:06:35',2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `online_payment_order` ENABLE KEYS */;


--
-- Definition of table `watererp`.`online_payment_response`
--

DROP TABLE IF EXISTS `watererp`.`online_payment_response`;
CREATE TABLE  `watererp`.`online_payment_response` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `response_code` varchar(255) DEFAULT NULL,
  `response_time` timestamp NULL DEFAULT NULL,
  `redirect_url` varchar(255) DEFAULT NULL,
  `merchant_txn_ref` varchar(255) DEFAULT NULL,
  `online_payment_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_onlinepaymentresponse_onlinepaymentorder_id` (`online_payment_order_id`),
  CONSTRAINT `fk_onlinepaymentresponse_onlinepaymentorder_id` FOREIGN KEY (`online_payment_order_id`) REFERENCES `online_payment_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`online_payment_response`
--

/*!40000 ALTER TABLE `online_payment_response` DISABLE KEYS */;
LOCK TABLES `online_payment_response` WRITE;
INSERT INTO `watererp`.`online_payment_response` VALUES  (1,'100','2016-05-03 22:35:46','http://crystal.tekmindz.com:80/maxcompp/directpaymentreceipt.xhtml?txnref=6828618857&name=null&paymentmode=TIGOPESADIR','6828618857',11),
 (2,'100','2016-05-10 10:31:34','http://crystal.tekmindz.com:80/maxcompp/directpaymentreceipt.xhtml?txnref=4557988987&name=null&paymentmode=TIGOPESADIR','4557988987',12),
 (3,'100','2016-05-10 19:06:36','http://crystal.tekmindz.com:80/maxcompp/directpaymentreceipt.xhtml?txnref=6869048643&name=null&paymentmode=TIGOPESADIR','6869048643',13);
UNLOCK TABLES;
/*!40000 ALTER TABLE `online_payment_response` ENABLE KEYS */;


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
INSERT INTO `watererp`.`org_hierarchy` VALUES  (1,'Ministry Of Waters',0,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (2,'Board Of Directors',1,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (3,'Energy & Water Utilities Regulatory Authority',2,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (4,'Managing Director',2,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (5,'Internal Auditor',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (6,'Legal Officier',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (7,'Public Relations Officer',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (8,'HPMU',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (9,'Stores & Supplies Officer',8,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (10,'Technical Manager',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (11,'Commercial Manager',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (12,'Finance Manager',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (13,'Human Resource & Administration Manager',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (14,'Officer, GIS, Planning, Design & Construction',10,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (15,'Officer, Operation & Maintance - NRW, Water Supply and Sanitation',10,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (16,'Billing Officer',11,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (17,'Credit Control Officer',11,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (18,'ICT & Customer Care Officer',11,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (19,'Accountant',12,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (20,'Human Resource & Administration Section',13,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (21,'Zonal Supervisers',16,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (22,'Assistant Accountant(Revenue)',19,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (23,'Assistant Accountant(Expenditure)',19,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (24,'Technical Zonal Supervisor',15,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (25,'Customer',10,'2016-03-18 05:30:00','2016-03-18 05:30:00',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_role_instance`
--

/*!40000 ALTER TABLE `org_role_instance` DISABLE KEYS */;
LOCK TABLES `org_role_instance` WRITE;
INSERT INTO `watererp`.`org_role_instance` VALUES  (1,'Ministry Of Waters',0,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (2,'Board Of Directors',1,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (3,'Energy & Water Utilities Regulatory Authority',2,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (4,'Managing Director',2,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (5,'Internal Auditor',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (6,'Legal Officier',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (7,'Public Relations Officer',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (8,'HPMU',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (9,'Stores & Supplies Officer',8,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (10,'Technical Manager',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (11,'Commercial Manager',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (12,'Finance Manager',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (13,'Human Resource & Administration Manager',4,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (14,'Officer, GIS, Planning, Design & Construction',10,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (15,'Officer, Operation & Maintance - NRW, Water Supply and Sanitation',10,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (16,'Billing Officer',11,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (17,'Credit Control Officer',11,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (18,'ICT & Customer Care Officer',11,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (19,'Accountant',12,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (20,'Human Resource & Administration Section',13,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (21,'Zonal Supervisers',16,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (22,'Assistant Accountant(Revenue)',19,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (23,'Assistant Accountant(Expenditure)',19,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (24,'Technical Zonal Supervisor',15,'2016-03-21 05:30:00','2016-03-21 05:30:00',1,2,NULL,NULL),
 (25,'Customer',10,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL),
 (26,'ADMIN',1,'2016-03-18 05:30:00','2016-03-18 05:30:00',1,2,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`payment_types`
--

/*!40000 ALTER TABLE `payment_types` DISABLE KEYS */;
LOCK TABLES `payment_types` WRITE;
INSERT INTO `watererp`.`payment_types` VALUES  (1,'Cash'),
 (2,'Demand Draft (DD)'),
 (3,'Cheque');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`percentage_master`
--

/*!40000 ALTER TABLE `percentage_master` DISABLE KEYS */;
LOCK TABLES `percentage_master` WRITE;
INSERT INTO `watererp`.`percentage_master` VALUES  (1,'SUPERVISION',10),
 (2,'LABOUR CHARGES',20),
 (3,'SITE SURVEY',5),
 (4,'CONNECTION FEE OF A & B',20),
 (5,'APPLICATION FORM FEE',1000);
UNLOCK TABLES;
/*!40000 ALTER TABLE `percentage_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`pipe_size_master`
--

DROP TABLE IF EXISTS `watererp`.`pipe_size_master`;
CREATE TABLE  `watererp`.`pipe_size_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pipe_size` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`pipe_size_master`
--

/*!40000 ALTER TABLE `pipe_size_master` DISABLE KEYS */;
LOCK TABLES `pipe_size_master` WRITE;
INSERT INTO `watererp`.`pipe_size_master` VALUES  (1,0.5),
 (2,0.75),
 (3,1);
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
  `supervision_percent` double DEFAULT NULL,
  `labour_charge_percent` double DEFAULT NULL,
  `site_survey_percent` double DEFAULT NULL,
  `connection_fee_percent` double DEFAULT NULL,
  `application_txn_id` bigint(20) DEFAULT NULL,
  `pipe_size_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_proceedings_applicationtxn_id` (`application_txn_id`),
  KEY `fk_proceedings_pipesizemaster_id` (`pipe_size_master_id`),
  CONSTRAINT `fk_proceedings_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_proceedings_pipesizemaster_id` FOREIGN KEY (`pipe_size_master_id`) REFERENCES `pipe_size_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`proceedings`
--

/*!40000 ALTER TABLE `proceedings` DISABLE KEYS */;
LOCK TABLES `proceedings` WRITE;
INSERT INTO `watererp`.`proceedings` VALUES  (9,103500,10350,20700,5175,139725,27945,NULL,1000,168670,10,20,5,20,35,2);
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
-- Definition of table `watererp`.`receipt`
--

DROP TABLE IF EXISTS `watererp`.`receipt`;
CREATE TABLE  `watererp`.`receipt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `check_or_dd_date` date DEFAULT NULL,
  `check_or_dd_no` varchar(255) DEFAULT NULL,
  `receipt_date` date DEFAULT NULL,
  `application_txn_id` bigint(20) DEFAULT NULL,
  `payment_types_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_receipt_applicationtxn_id` (`application_txn_id`),
  KEY `fk_receipt_paymenttypes_id` (`payment_types_id`),
  CONSTRAINT `fk_receipt_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_receipt_paymenttypes_id` FOREIGN KEY (`payment_types_id`) REFERENCES `payment_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`receipt`
--

/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
LOCK TABLES `receipt` WRITE;
INSERT INTO `watererp`.`receipt` VALUES  (13,168670,'654654','654654','2016-05-18','654654','2016-05-18',35,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`req_org_workflow_mapping`
--

/*!40000 ALTER TABLE `req_org_workflow_mapping` DISABLE KEYS */;
LOCK TABLES `req_org_workflow_mapping` WRITE;
INSERT INTO `watererp`.`req_org_workflow_mapping` VALUES  (1,'2016-03-21 05:30:00','2016-03-21 05:30:00',1,1,25,2),
 (2,'2016-03-21 05:30:00','2016-03-21 05:30:00',1,1,10,2),
 (3,'2016-03-21 05:30:00','2016-03-21 05:30:00',4,3,25,2),
 (4,'2016-03-21 05:30:00','2016-03-21 05:30:00',5,6,15,2),
 (5,'2016-03-21 05:30:00','2016-03-21 05:30:00',6,7,15,2),
 (6,'2016-03-21 05:30:00','2016-03-21 05:30:00',5,6,10,1),
 (7,'2016-03-21 05:30:00','2016-03-21 05:30:00',5,6,25,2),
 (8,'2016-03-21 05:30:00','2016-03-21 05:30:00',7,4,25,2),
 (9,'2016-03-21 05:30:00','2016-03-21 05:30:00',8,8,25,2),
 (10,'2016-04-29 05:30:00','2016-04-29 05:30:00',9,9,25,2),
 (11,'2016-04-29 00:00:00','2016-04-29 00:00:00',10,10,25,2),
 (12,'2016-04-29 00:00:00','2016-04-29 00:00:00',11,11,25,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`request_master`
--

/*!40000 ALTER TABLE `request_master` DISABLE KEYS */;
LOCK TABLES `request_master` WRITE;
INSERT INTO `watererp`.`request_master` VALUES  (1,'REQUISITION','2016-03-21 05:30:00','2016-03-21 05:30:00',NULL,0,2),
 (2,'LEAVE','2016-03-26 05:30:00','2016-03-26 05:30:00',NULL,0,2),
 (3,'INCORRECT BILL','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2),
 (4,'WATER LEAKAGE','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2),
 (5,'SERVICE UNAVAILABILITY','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2),
 (6,'WITHOUTMETER','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2),
 (7,'METER CHANGE','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2),
 (8,'CONNECTION CATEGORY','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2),
 (9,'PIPE SIZE','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2),
 (10,'CHANGE NAME','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2),
 (11,'CONNECTION TERMINATION','2016-03-31 05:30:00','2016-03-31 05:30:00',NULL,0,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`request_workflow_history`
--

/*!40000 ALTER TABLE `request_workflow_history` DISABLE KEYS */;
LOCK TABLES `request_workflow_history` WRITE;
INSERT INTO `watererp`.`request_workflow_history` VALUES  (196,1,'2016-05-18 13:02:23','2016-05-18 00:00:00','approved','127.0.0.1',NULL,35,5,15,5,1,1,NULL,NULL),
 (198,2,'2016-05-18 00:00:00','2016-05-18 00:00:00','Approved at second level','127.0.0.1',NULL,35,15,29,5,1,1,NULL,NULL),
 (199,3,'2016-05-18 00:00:00','2016-05-18 15:21:15','Feasibility Created','127.0.0.1',NULL,35,29,20,5,1,1,NULL,NULL),
 (200,4,'2016-05-18 15:21:15','2016-05-18 15:32:13','Proceedings created','127.0.0.1',NULL,35,20,15,5,1,1,NULL,NULL),
 (201,5,'2016-05-18 15:32:13','2016-05-18 15:40:14','Receipt Created','127.0.0.1',NULL,35,15,27,5,1,1,NULL,NULL),
 (202,6,'2016-05-18 15:40:14','2016-05-18 15:48:38','All Items Issued','127.0.0.1',NULL,35,27,14,5,1,1,NULL,NULL),
 (203,7,'2016-05-18 15:48:38','2016-05-18 15:53:56','Meter Issued','127.0.0.1',NULL,35,14,20,5,1,1,NULL,NULL),
 (204,8,'2016-05-18 15:53:56','2016-05-18 16:04:07','CAN Generated','127.0.0.1',NULL,35,20,21,9,1,1,NULL,NULL),
 (205,1,'2016-05-18 16:42:08',NULL,'Approved for Name Change','127.0.0.1',NULL,9,5,15,5,10,10,NULL,NULL),
 (206,2,NULL,NULL,NULL,'127.0.0.1',NULL,9,15,27,3,10,10,NULL,NULL),
 (207,1,'2016-05-18 18:23:33',NULL,NULL,'127.0.0.1',NULL,19,5,15,3,8,8,NULL,NULL),
 (208,1,'2016-05-18 18:47:56',NULL,'','127.0.0.1',NULL,20,5,15,3,8,8,NULL,NULL),
 (210,1,'2016-05-18 19:20:42','2016-05-18 19:22:48','','127.0.0.1',NULL,21,5,15,5,8,8,NULL,NULL),
 (211,2,'2016-05-18 19:22:48','2016-05-18 19:27:34','Approved category change','127.0.0.1',NULL,21,15,21,9,8,8,NULL,NULL);
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
-- Definition of table `watererp`.`revenue_type_master`
--

DROP TABLE IF EXISTS `watererp`.`revenue_type_master`;
CREATE TABLE  `watererp`.`revenue_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `revenue_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`revenue_type_master`
--

/*!40000 ALTER TABLE `revenue_type_master` DISABLE KEYS */;
LOCK TABLES `revenue_type_master` WRITE;
INSERT INTO `watererp`.`revenue_type_master` VALUES  (1,'Rent'),
 (2,'Scrap Sale');
UNLOCK TABLES;
/*!40000 ALTER TABLE `revenue_type_master` ENABLE KEYS */;


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
INSERT INTO `watererp`.`role_workflow_mapping` VALUES  (1,'2016-03-21 05:30:00','2016-03-21 05:30:00',2,10,1,1),
 (2,'2016-03-21 05:30:00','2016-03-21 05:30:00',2,24,1,1),
 (3,'2016-03-21 05:30:00','2016-03-21 05:30:00',2,15,1,1);
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
  `so_date` timestamp NULL DEFAULT NULL,
  `demand_date` timestamp NULL DEFAULT NULL,
  `dir` varchar(255) DEFAULT NULL,
  `div_name` varchar(255) DEFAULT NULL,
  `inv_no` bigint(20) DEFAULT NULL,
  `sib_date` timestamp NULL DEFAULT NULL,
  `sib_no` varchar(255) DEFAULT NULL,
  `ir_date` timestamp NULL DEFAULT NULL,
  `ir_no` varchar(255) DEFAULT NULL,
  `vendor_code` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `to_user` timestamp NULL DEFAULT NULL,
  `from_user` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `dc_no` varchar(255) DEFAULT NULL,
  `dc_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `street_no` varchar(255) DEFAULT NULL,
  `division_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_streetmaster_divisionmaster_id` (`division_master_id`),
  CONSTRAINT `fk_streetmaster_divisionmaster_id` FOREIGN KEY (`division_master_id`) REFERENCES `division_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`street_master`
--

/*!40000 ALTER TABLE `street_master` DISABLE KEYS */;
LOCK TABLES `street_master` WRITE;
INSERT INTO `watererp`.`street_master` VALUES  (1,'Street1','01',1),
 (2,'Street2','02',2),
 (3,'Street3','03',3),
 (4,'Street4','04',3),
 (5,'Street5','05',5),
 (6,'Street6','06',4),
 (7,'Street7','07',6),
 (8,'Street8','08',7),
 (9,'Street9','09',8),
 (10,'Street10','10',9),
 (11,'Street11','11',10);
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
  `tariff_category` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_category_master`
--

/*!40000 ALTER TABLE `tariff_category_master` DISABLE KEYS */;
LOCK TABLES `tariff_category_master` WRITE;
INSERT INTO `watererp`.`tariff_category_master` VALUES  (1,'Domestic'),
 (2,'Institutional'),
 (3,'Commercial'),
 (4,'Industrial'),
 (5,'Kiosks');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tariff_category_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`tariff_charges`
--

DROP TABLE IF EXISTS `watererp`.`tariff_charges`;
CREATE TABLE  `watererp`.`tariff_charges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tariff_desc` varchar(255) NOT NULL,
  `slab_min` int(11) NOT NULL,
  `slab_max` int(11) NOT NULL,
  `rate` float NOT NULL,
  `min_kl` float NOT NULL,
  `min_unmetered_kl` float NOT NULL,
  `tariff_master_id` bigint(20) DEFAULT NULL,
  `tariff_type_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tariffcharges_tariffmaster_id` (`tariff_master_id`),
  KEY `fk_tariffcharges_tarifftypemaster_id` (`tariff_type_master_id`),
  CONSTRAINT `fk_tariffcharges_tariffmaster_id` FOREIGN KEY (`tariff_master_id`) REFERENCES `tariff_master` (`id`),
  CONSTRAINT `fk_tariffcharges_tarifftypemaster_id` FOREIGN KEY (`tariff_type_master_id`) REFERENCES `tariff_type_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_charges`
--

/*!40000 ALTER TABLE `tariff_charges` DISABLE KEYS */;
LOCK TABLES `tariff_charges` WRITE;
INSERT INTO `watererp`.`tariff_charges` VALUES  (1,'Domestic - Usage',0,999999,780,11,31,1,1),
 (2,'Domestic - Fixed',0,999999,500,0,0,1,2),
 (3,'Domestic - Service',0,999999,1800,0,0,1,3),
 (4,'Domestic - Usage',0,999999,780,0,32,2,1),
 (5,'Domestic - Fixed',0,999999,500,0,0,2,2),
 (6,'Domestic - Service',0,999999,1800,0,0,2,3),
 (7,'Domestic - Usage',0,999999,820,0,33,3,1),
 (8,'Domestic - Fixed',0,999999,510,0,0,3,2),
 (9,'Domestic - Service',0,999999,1820,0,0,3,3),
 (10,'Domestic - Usage',0,99999,830,0,18,4,1),
 (11,'Domestic - Fixed',0,100,530,0,18,4,2),
 (12,'Domestic - Service',0,99999,1850,0,18,4,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tariff_charges` ENABLE KEYS */;


--
-- Definition of table `watererp`.`tariff_master`
--

DROP TABLE IF EXISTS `watererp`.`tariff_master`;
CREATE TABLE  `watererp`.`tariff_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tariff_name` varchar(255) NOT NULL,
  `valid_from` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valid_to` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `active` varchar(255) NOT NULL,
  `tariff_category_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tariffmaster_tariffcategorymaster_id` (`tariff_category_master_id`),
  CONSTRAINT `fk_tariffmaster_tariffcategorymaster_id` FOREIGN KEY (`tariff_category_master_id`) REFERENCES `tariff_category_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_master`
--

/*!40000 ALTER TABLE `tariff_master` DISABLE KEYS */;
LOCK TABLES `tariff_master` WRITE;
INSERT INTO `watererp`.`tariff_master` VALUES  (1,'Domestic - 2010 to 2015','2010-01-01 05:30:00','2015-12-31 05:30:00','1',1),
 (2,'Domestic - 2016 Jan to Mar','2016-01-01 05:30:00','2016-03-31 05:30:00','1',1),
 (3,'Domestic - 2016 Apr to  June','2016-04-01 05:30:00','2016-06-30 05:30:00','1',1),
 (4,'Domestic - 2016 Jul to Dec','2016-07-01 05:30:00','2016-12-31 05:30:00','1',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tariff_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`tariff_type_master`
--

DROP TABLE IF EXISTS `watererp`.`tariff_type_master`;
CREATE TABLE  `watererp`.`tariff_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tariff_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_type_master`
--

/*!40000 ALTER TABLE `tariff_type_master` DISABLE KEYS */;
LOCK TABLES `tariff_type_master` WRITE;
INSERT INTO `watererp`.`tariff_type_master` VALUES  (1,'Usage Charges'),
 (2,'Fixed Charges'),
 (3,'Service Charges');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tariff_type_master` ENABLE KEYS */;


--
-- Definition of table `watererp`.`terminal`
--

DROP TABLE IF EXISTS `watererp`.`terminal`;
CREATE TABLE  `watererp`.`terminal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `mr_code` varchar(255) DEFAULT NULL,
  `sec_code` varchar(255) DEFAULT NULL,
  `div_code` varchar(255) DEFAULT NULL,
  `sec_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `ver` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`terminal`
--

/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
LOCK TABLES `terminal` WRITE;
INSERT INTO `watererp`.`terminal` VALUES  (1,1111,'Status1','UserId1','MrCode1','SecCode1','DivCode1','SecName1','UserName1','MobileNo1','Ver1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;


--
-- Definition of table `watererp`.`terminal_log`
--

DROP TABLE IF EXISTS `watererp`.`terminal_log`;
CREATE TABLE  `watererp`.`terminal_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `last_modified` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `bank_deposit_date` date DEFAULT NULL,
  `before_update` varchar(255) DEFAULT NULL,
  `after_update` varchar(255) DEFAULT NULL,
  `mr_code` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `txn_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`terminal_log`
--

/*!40000 ALTER TABLE `terminal_log` DISABLE KEYS */;
LOCK TABLES `terminal_log` WRITE;
INSERT INTO `watererp`.`terminal_log` VALUES  (2,1222,'2016-03-16 05:30:00','mohib',NULL,'2016-03-16',NULL,NULL,NULL,NULL,NULL),
 (3,12,'2016-03-16 05:30:00','111','111','2016-03-16','11','11','11',NULL,NULL),
 (4,2222,'2016-03-18 05:30:00','ModifiedBy2','UserId2','2016-03-18','BeforeUpdate2','AfterUpdate2','MrCode2','Remark2','TxnType2');
UNLOCK TABLES;
/*!40000 ALTER TABLE `terminal_log` ENABLE KEYS */;


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
-- Definition of table `watererp`.`uom`
--

DROP TABLE IF EXISTS `watererp`.`uom`;
CREATE TABLE  `watererp`.`uom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`uom`
--

/*!40000 ALTER TABLE `uom` DISABLE KEYS */;
LOCK TABLES `uom` WRITE;
INSERT INTO `watererp`.`uom` VALUES  (1,'Meter'),
 (2,'Centi Meter'),
 (3,'Piece');
UNLOCK TABLES;
/*!40000 ALTER TABLE `uom` ENABLE KEYS */;


--
-- Definition of table `watererp`.`url`
--

DROP TABLE IF EXISTS `watererp`.`url`;
CREATE TABLE  `watererp`.`url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url_pattern` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;

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
 (19,'/sibEntry',1),
 (20,'/receipts',1),
 (21,'/accessLists',1),
 (22,'/billFullDetailss',1),
 (23,'/collDetailss',1),
 (24,'/currentUserss',1),
 (25,'/custDetailss',1),
 (26,'/terminals',1),
 (27,'/terminalLogs',1),
 (28,'/versions',1),
 (29,'/complaintTypeMasters',1),
 (30,'/customerComplaintss',1),
 (31,'/billRunMasters',1),
 (32,'/billRunDetails',1),
 (33,'/meterChanges',1),
 (34,'/applicationTxns/withoutMeter',1),
 (35,'/billDetailss',1),
 (36,'/account',1),
 (37,'/logout',1),
 (38,'/authentication',1),
 (39,'/merchantMaster',1),
 (40,'/onlinePaymentOrder',1),
 (41,'/onlinePaymentResponse',1),
 (42,'/onlinePaymentCallback',1),
 (43,'/customers/categoryChange',1),
 (44,'/customers/pipeSizeChange',1),
 (45,'/customers/nameChange',1),
 (46,'/connectionTerminates/new',1),
 (47,'/application_type_masters',1),
 (48,'/bill_run_detailss',1),
 (49,'/cash_book_masters',1),
 (50,'/category_masters',1),
 (51,'/category_pipe_size_mappings',1),
 (52,'/collection_type_masters',1),
 (53,'/configuration_detailss',1),
 (54,'/connection_type_masters',1),
 (55,'/cust_meter_mappings',1),
 (56,'/customers',1),
 (57,'/databasechangelogs',1),
 (58,'/databasechangeloglocks',1),
 (59,'/department_type_masters',1),
 (60,'/departments_hierarchys',1),
 (61,'/departments_masters',1),
 (62,'/desig_category_masters',1),
 (63,'/designation_mappingss',1),
 (64,'/designation_masters',1),
 (65,'/division_masters',1),
 (66,'/docket_codes',1),
 (67,'/emp_masters',1),
 (68,'/emp_role_mappings',1),
 (69,'/expenseDetailss/new',1),
 (70,'/feasibility_statuss',1),
 (71,'/file_numbers',1),
 (72,'/file_upload_masters',1),
 (73,'/group_masters',1),
 (74,'/id_proof_masters',1),
 (75,' /item_detailss',1),
 (76,'/item_requireds',1),
 (77,'/main_sewerage_sizes',1),
 (78,'/main_water_sizes',1),
 (79,'/make_of_pipes',1),
 (80,'/meter_detailss',1),
 (81,'/meter_statuss',1),
 (82,'/mmg_material_masters',1),
 (83,'/mmg_terms_masters',1),
 (84,'/org_hierarchys',1),
 (85,'/org_role_hierarchys',1),
 (86,'/org_role_instances',1),
 (87,'/org_roles_masters',1),
 (88,'/payment_typess',1),
 (89,'/percentage_masters',1),
 (90,'/pipe_size_masters',1),
 (91,'/proceedingss',1),
 (92,'/re_allotments',1),
 (93,'/req_desig_workflow_mappings',1),
 (94,'/req_org_workflow_mappings',1),
 (95,'/request_masters',1),
 (96,'/request_workflow_historys',1),
 (97,'/request_workflow_mappings',1),
 (98,'/revenue_type_masters',1),
 (99,'/role_workflow_mappings',1),
 (100,'/scheme_masters',1),
 (101,'/revDetails/new',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;

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
 (27,19,'ROLE_ADMIN'),
 (28,20,'ROLE_USER'),
 (29,21,'ROLE_ADMIN'),
 (30,22,'ROLE_ADMIN'),
 (31,23,'ROLE_ADMIN'),
 (32,24,'ROLE_ADMIN'),
 (33,25,'ROLE_ADMIN'),
 (34,26,'ROLE_ADMIN'),
 (35,27,'ROLE_ADMIN'),
 (36,28,'ROLE_ADMIN'),
 (37,29,'ROLE_ADMIN'),
 (38,30,'ROLE_ADMIN'),
 (39,30,'ROLE_CUSTOMER'),
 (40,31,'ROLE_BILLRUN'),
 (41,32,'ROLE_BILLRUN'),
 (42,31,'ROLE_BILLRUN_MANAGER'),
 (43,32,'ROLE_BILLRUN_MANAGER'),
 (44,33,'ROLE_USER'),
 (45,34,'ROLE_USER'),
 (46,35,'ROLE_ADMIN'),
 (47,36,'ROLE_BILLRUN'),
 (48,37,'ROLE_BILLRUN'),
 (51,36,'ROLE_USER'),
 (52,38,'ROLE_USER'),
 (53,38,'ROLE_ADMIN'),
 (54,38,'ROLE_BILLRUN'),
 (55,31,'ROLE_ADMIN'),
 (56,32,'ROLE_ADMIN'),
 (57,30,'ROLE_USER'),
 (58,39,'ROLE_ADMIN'),
 (59,40,'ROLE_ADMIN'),
 (60,41,'ROLE_ADMIN'),
 (61,42,'ROLE_ADMIN'),
 (62,34,'ROLE_CUSTOMER'),
 (63,43,'ROLE_USER'),
 (65,44,'ROLE_USER'),
 (66,45,'ROLE_USER'),
 (67,46,'ROLE_USER'),
 (68,43,'ROLE_CUSTOMER'),
 (69,44,'ROLE_CUSTOMER'),
 (70,45,'ROLE_CUSTOMER'),
 (71,46,'ROLE_CUSTOMER'),
 (72,101,'ROLE_USER'),
 (73,101,'ROLE_ADMIN'),
 (74,33,'ROLE_CUSTOMER'),
 (75,69,'ROLE_ADMIN'),
 (76,69,'ROLE_USER');
UNLOCK TABLES;
/*!40000 ALTER TABLE `url2_role` ENABLE KEYS */;


--
-- Definition of table `watererp`.`version`
--

DROP TABLE IF EXISTS `watererp`.`version`;
CREATE TABLE  `watererp`.`version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version_low` varchar(255) DEFAULT NULL,
  `version_high` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`version`
--

/*!40000 ALTER TABLE `version` DISABLE KEYS */;
LOCK TABLES `version` WRITE;
INSERT INTO `watererp`.`version` VALUES  (1,'1','533'),
 (2,'1','19'),
 (6,'11','33');
UNLOCK TABLES;
/*!40000 ALTER TABLE `version` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

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
 (8,8,1,NULL,15,2,NULL,16,NULL,NULL,NULL,NULL),
 (9,1,4,NULL,NULL,2,NULL,18,NULL,NULL,NULL,4),
 (10,2,4,NULL,18,2,NULL,11,NULL,NULL,NULL,5),
 (11,3,4,NULL,11,2,NULL,12,NULL,NULL,NULL,5),
 (12,4,4,NULL,12,2,NULL,4,NULL,NULL,NULL,5),
 (13,5,4,NULL,4,2,NULL,16,NULL,NULL,NULL,NULL),
 (14,1,7,NULL,NULL,2,NULL,18,NULL,NULL,NULL,5),
 (15,2,7,NULL,18,2,NULL,10,NULL,NULL,NULL,5),
 (16,3,7,NULL,10,2,NULL,24,NULL,NULL,NULL,5),
 (17,4,7,NULL,24,2,NULL,10,NULL,NULL,NULL,5),
 (18,1,5,NULL,NULL,2,NULL,10,NULL,NULL,NULL,2),
 (19,2,5,NULL,10,2,NULL,16,NULL,NULL,NULL,NULL),
 (20,1,6,NULL,NULL,2,NULL,10,NULL,NULL,NULL,4),
 (21,1,8,NULL,NULL,2,NULL,10,NULL,NULL,NULL,1),
 (22,2,8,NULL,10,2,NULL,16,NULL,NULL,NULL,2),
 (23,1,9,NULL,NULL,2,NULL,10,NULL,NULL,NULL,1),
 (24,2,9,NULL,10,2,NULL,16,NULL,NULL,NULL,2),
 (25,1,10,NULL,NULL,2,NULL,10,NULL,NULL,NULL,5),
 (26,2,10,NULL,10,2,NULL,22,NULL,NULL,NULL,2),
 (27,3,10,NULL,22,2,NULL,16,NULL,NULL,NULL,2),
 (28,2,6,NULL,15,2,NULL,10,NULL,NULL,NULL,2),
 (29,1,11,NULL,NULL,2,NULL,10,NULL,NULL,NULL,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_master`
--

/*!40000 ALTER TABLE `workflow_master` DISABLE KEYS */;
LOCK TABLES `workflow_master` WRITE;
INSERT INTO `watererp`.`workflow_master` VALUES  (1,'REQUISITION',0,'2016-03-18 05:30:00','2016-03-18 05:30:00',2),
 (2,'LEAVE',0,'2016-03-26 05:30:00','2016-03-26 05:30:00',2),
 (3,'CUSTOMER COMPLAINT',0,'2016-03-31 05:30:00','2016-03-31 05:30:00',2),
 (4,'INCORRECT BILL',0,'2016-03-31 05:30:00','2016-03-31 05:30:00',2),
 (5,'WITHOUTMETER',0,'2016-03-31 05:30:00','2016-03-31 05:30:00',2),
 (6,'METER CHANGE',0,'2016-03-31 05:30:00','2016-03-31 05:30:00',2),
 (7,'WATER LEAKAGE',0,'2016-04-29 05:30:00','2016-04-29 05:30:00',2),
 (8,'CONNECTION CATEGORY',0,'2016-04-29 05:30:00','2016-04-29 05:30:00',2),
 (9,'PIPE SIZE',0,'2016-04-29 05:30:00','2016-04-29 05:30:00',2),
 (10,'CHANGE NAME',0,'2016-04-29 05:30:00','2016-04-29 05:30:00',2),
 (11,'CONNECTION TERMINATION',0,'2016-04-29 05:30:00','2016-04-29 05:30:00',2);
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
INSERT INTO `watererp`.`workflow_stage_master` VALUES  (1,'Recommended','2016-03-21 05:30:00','2016-03-21 05:30:00',NULL,2),
 (2,'Approved','2016-03-21 05:30:00','2016-03-21 05:30:00',NULL,2),
 (3,'Sanctioned','2016-03-21 05:30:00','2016-03-21 05:30:00',NULL,2),
 (4,'Waiting','2016-03-21 05:30:00','2016-03-21 05:30:00',NULL,2),
 (5,'Forwarded','2016-03-21 05:30:00','2016-03-21 05:30:00',NULL,2),
 (6,'Completed','2016-03-21 05:30:00','2016-03-21 05:30:00',NULL,2);
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
INSERT INTO `watererp`.`workflow_type_master` VALUES  (1,'GENERIC','2016-03-26 05:30:00','2016-03-26 05:30:00',NULL,2),
 (2,'ROLE','2016-03-26 05:30:00','2016-03-26 05:30:00',NULL,2);
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`zone_master`
--

/*!40000 ALTER TABLE `zone_master` DISABLE KEYS */;
LOCK TABLES `zone_master` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `zone_master` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
