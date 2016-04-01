-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema `watererp`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `watererp`;
USE `watererp`;

--
-- Table structure for table `watererp`.`access_list`
--

DROP TABLE IF EXISTS `access_list`;
CREATE TABLE `access_list` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`access_list`
--

/*!40000 ALTER TABLE `access_list` DISABLE KEYS */;
INSERT INTO `access_list` (`id`,`user_id`) VALUES 
 (1,'admin1'),
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
 (13,'admin13');
/*!40000 ALTER TABLE `access_list` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`application_txn`
--

DROP TABLE IF EXISTS `application_txn`;
CREATE TABLE `application_txn` (
  `id` bigint(20) NOT NULL auto_increment,
  `s_house_no` varchar(255) default NULL,
  `govt_official_no` varchar(255) default NULL,
  `ward` varchar(255) default NULL,
  `street` varchar(255) default NULL,
  `pincode` varchar(255) default NULL,
  `block` varchar(255) default NULL,
  `area` varchar(255) default NULL,
  `section` varchar(255) default NULL,
  `constituency` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `telephone_number` varchar(255) default NULL,
  `mobile` varchar(255) default NULL,
  `scan_plan` float default NULL,
  `scan_plan1` float default NULL,
  `sale_deed` float default NULL,
  `sale_deed1` float default NULL,
  `total_plinth_area` float default NULL,
  `created_date` timestamp NULL default NULL,
  `updated_date` timestamp NULL default NULL,
  `status` varchar(255) default NULL,
  `application_type_master_id` bigint(20) default NULL,
  `connection_type_master_id` bigint(20) default NULL,
  `category_master_id` bigint(20) default NULL,
  `pipe_size_master_id` bigint(20) default NULL,
  `sewer_size_id` bigint(20) default NULL,
  `file_number_id` bigint(20) default NULL,
  `customer_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_applicationtxn_applicationtypemaster_id` (`application_type_master_id`),
  KEY `fk_applicationtxn_connectiontypemaster_id` (`connection_type_master_id`),
  KEY `fk_applicationtxn_categorymaster_id` (`category_master_id`),
  KEY `fk_applicationtxn_pipesizemaster_id` (`pipe_size_master_id`),
  KEY `fk_applicationtxn_sewersize_id` (`sewer_size_id`),
  KEY `fk_applicationtxn_filenumber_id` (`file_number_id`),
  KEY `fk_applicationtxn_customer_id` (`customer_id`),
  CONSTRAINT `fk_applicationtxn_applicationtypemaster_id` FOREIGN KEY (`application_type_master_id`) REFERENCES `application_type_master` (`id`),
  CONSTRAINT `fk_applicationtxn_categorymaster_id` FOREIGN KEY (`category_master_id`) REFERENCES `category_master` (`id`),
  CONSTRAINT `fk_applicationtxn_connectiontypemaster_id` FOREIGN KEY (`connection_type_master_id`) REFERENCES `connection_type_master` (`id`),
  CONSTRAINT `fk_applicationtxn_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_applicationtxn_filenumber_id` FOREIGN KEY (`file_number_id`) REFERENCES `file_number` (`id`),
  CONSTRAINT `fk_applicationtxn_pipesizemaster_id` FOREIGN KEY (`pipe_size_master_id`) REFERENCES `pipe_size_master` (`id`),
  CONSTRAINT `fk_applicationtxn_sewersize_id` FOREIGN KEY (`sewer_size_id`) REFERENCES `sewer_size` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`application_txn`
--

/*!40000 ALTER TABLE `application_txn` DISABLE KEYS */;
INSERT INTO `application_txn` (`id`,`s_house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`telephone_number`,`mobile`,`scan_plan`,`scan_plan1`,`sale_deed`,`sale_deed1`,`total_plinth_area`,`created_date`,`updated_date`,`status`,`application_type_master_id`,`connection_type_master_id`,`category_master_id`,`pipe_size_master_id`,`sewer_size_id`,`file_number_id`,`customer_id`) VALUES 
 (5,'123','339','233','258','741','789','852','2558','255','hhjjh','789','58741',14,25,114,58,48.25,'2016-03-03 00:00:00','2016-03-03 00:00:00','Approved',1,1,1,2,3,1,NULL),
 (15,'123','369','hug','258','1148','gjy','369','2548','jh','ashkjhs@gmail','8876786','76876',2,2.4,3,3.6,47,NULL,NULL,'In Feasibility',1,1,2,2,4,2,15),
 (16,'123','369','hug','258','1148','gjy','369','2548','jh','ashkjhs@gmail','8876786','76876',2,2.4,3,3.6,47,'2016-03-04 00:00:00','2016-03-04 00:00:00','In Feasibility',1,1,2,2,4,2,16),
 (18,'WEY','WEY','WEY','WEY','WEY','WEY','WEY','WEY','WEY','ATA','WERYWE','ERU',4,4.8,4.2,5,8,'2016-03-04 00:00:00','2016-03-04 00:00:00','In Feasibility',2,1,3,2,5,2,18),
 (19,'hkjh','hkj','kjh','kjhkj','hkjh','kjh','hkj','kjh','kjh','89878','0907','7987',5.04,6,6,7.2,5,'2016-03-04 00:00:00','2016-03-04 00:00:00','In Feasibility',1,1,1,1,3,1,19);
INSERT INTO `application_txn` (`id`,`s_house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`telephone_number`,`mobile`,`scan_plan`,`scan_plan1`,`sale_deed`,`sale_deed1`,`total_plinth_area`,`created_date`,`updated_date`,`status`,`application_type_master_id`,`connection_type_master_id`,`category_master_id`,`pipe_size_master_id`,`sewer_size_id`,`file_number_id`,`customer_id`) VALUES 
 (20,'147','889','888','855','9999','9999','998','8996','999','999','9999','999',2,2.4,3,3.6,1,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',2,1,2,1,4,1,20),
 (21,'9','88','788','78','887','788','88','778','997','5567','888','876',7,8.4,9,10.8,8,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',1,1,1,1,3,2,21),
 (22,'kjhkj','hkj','hkj','hjk','hkj','h','hkj','hkj','kjh','hkj','kjhkj','hkj',6,7.2,9,10.8,55,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',1,1,1,1,6,3,22),
 (23,'hgjh','ghjh','hjhkj','hgjj','hgjh','hkjhkjh','hjhjhg','jhgj','hkkjhj','hkhkjff','khkjkhj','kjkhkj',5.88,7,8,9.6,13,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',1,1,4,2,3,4,23),
 (24,'jkhkjhjkh','khhjkh','hjhhjkj','kjhkhk','jhj','hkjhkjh','jhkjh','jhkj','kjhkjh','khkjh','kjhkjhkj','hkjh',5,6,9,10.8,6,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',2,2,1,1,3,4,24);
INSERT INTO `application_txn` (`id`,`s_house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`telephone_number`,`mobile`,`scan_plan`,`scan_plan1`,`sale_deed`,`sale_deed1`,`total_plinth_area`,`created_date`,`updated_date`,`status`,`application_type_master_id`,`connection_type_master_id`,`category_master_id`,`pipe_size_master_id`,`sewer_size_id`,`file_number_id`,`customer_id`) VALUES 
 (25,'hjkhkh','hkj','jhk','jhk','hjh','hkjh','hkjjk','jhkjh','kjhk','hkhjh','jhkh','khk',7,8.4,7,8.4,11,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',2,2,2,3,4,2,25),
 (26,'jhkjh','jhkjhkjh','kjhkjh','jhkjhk','kjhkjh','kjhk','jkhkjhk','jkhkjh','hk','hkj','hkjhkj','khkj',9,10.8,7,8.4,11,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',1,1,1,1,4,4,26),
 (27,'jhkjh','hjkh','kjh','jhjk','kjh','jkhkj','hkjhkj','jkhkjh','hkjh','hkj','kjh','kjhkj',4,4.8,2,2.4,7,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',1,1,1,1,3,2,27),
 (28,'asg','ag','sag','ag','asg','asg','asg','asga','aasg','asg','sdgsd','ssg',6,7.2,6,7.2,4734,'2016-03-07 00:00:00','2016-03-07 00:00:00','Pending',3,2,2,3,5,3,28),
 (29,'sdhsh','shsd','shs','shs','sdhs','sfhshf','shshdh','shsd','shsdf','sg','dfhs','sh',3,3.6,5,6,667,'2016-03-08 00:00:00','2016-03-08 00:00:00','Pending',2,2,3,2,5,2,29);
INSERT INTO `application_txn` (`id`,`s_house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`telephone_number`,`mobile`,`scan_plan`,`scan_plan1`,`sale_deed`,`sale_deed1`,`total_plinth_area`,`created_date`,`updated_date`,`status`,`application_type_master_id`,`connection_type_master_id`,`category_master_id`,`pipe_size_master_id`,`sewer_size_id`,`file_number_id`,`customer_id`) VALUES 
 (30,'bhb','.b',',mn,mn','kkjkj',',,m,mn','mnjh','hk',',mn,mn','gh','vhjh','ghgc','h',4,4.8,6,7.2,4626,'2016-03-08 00:00:00','2016-03-08 00:00:00','Pending',2,2,3,2,5,3,30),
 (31,'hgh','ghg','v ccnbv','hgjg','jhg','vbnfhgd','hjh','hvbn','vhvhjh','jkgjjhj','vhjvg','6876876',2,2.4,4,4.8,5,'2016-03-08 00:00:00','2016-03-08 00:00:00','Pending',2,2,3,2,5,3,31),
 (32,'jhkjh','kjhjk','hkj','jhjkh','hkj','hkjh','jkh','hkj','kjh','jhkj','jkhkjh','jkh',3,3.6,5,6,6,'2016-03-10 00:00:00','2016-03-10 00:00:00','Pending',1,1,2,2,4,3,32),
 (33,'236','236','236','236','236','236','236','236','236','236','236','326',6,7.2,7,8.4,43,'2016-03-11 00:00:00','2016-03-11 00:00:00','Pending',3,2,2,2,4,2,33);
/*!40000 ALTER TABLE `application_txn` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`application_type_master`
--

DROP TABLE IF EXISTS `application_type_master`;
CREATE TABLE `application_type_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `application_type` varchar(255) default NULL,
  `created_date` timestamp NULL default NULL,
  `updated_date` timestamp NULL default NULL,
  `status` varchar(255) default NULL,
  `created_by` varchar(255) default NULL,
  `updated_by` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`application_type_master`
--

/*!40000 ALTER TABLE `application_type_master` DISABLE KEYS */;
INSERT INTO `application_type_master` (`id`,`application_type`,`created_date`,`updated_date`,`status`,`created_by`,`updated_by`) VALUES 
 (1,'Charity Institute','2016-03-03 00:00:00','2016-03-03 00:00:00','1','abc','abc'),
 (2,'Feasibility Reciept','2016-03-03 00:00:00','2016-03-03 00:00:00','1','abc','abc'),
 (3,'Filling Station','2016-03-03 00:00:00','2016-03-03 00:00:00','1','abc','abc'),
 (4,'General','2016-03-03 00:00:00','2016-03-03 00:00:00','1','abc','abc');
/*!40000 ALTER TABLE `application_type_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`approval_details`
--

DROP TABLE IF EXISTS `approval_details`;
CREATE TABLE `approval_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `remarks` varchar(255) default NULL,
  `approved_date` timestamp NULL default NULL,
  `approved_emp_no` varchar(255) default NULL,
  `approved_emp_name` varchar(255) default NULL,
  `approved_emp_desig` varchar(255) default NULL,
  `customer_id` bigint(20) default NULL,
  `feasibility_status_id` bigint(20) default NULL,
  `designation_master_id` bigint(20) default NULL,
  `application_txn_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
INSERT INTO `approval_details` (`id`,`remarks`,`approved_date`,`approved_emp_no`,`approved_emp_name`,`approved_emp_desig`,`customer_id`,`feasibility_status_id`,`designation_master_id`,`application_txn_id`) VALUES 
 (19,'wwqwt',NULL,'qwtqwt','wtwt','weywey',19,NULL,NULL,19),
 (20,'wqwrw','2016-03-09 00:00:00','qwr','qwrqw','qwq',20,NULL,NULL,20),
 (21,'ewtwet',NULL,'wetwet','wey','wywe',5,NULL,NULL,5),
 (22,'wewe',NULL,'wewey','wewey','weywey',15,NULL,NULL,15),
 (23,'wgwg','2016-03-09 00:00:00','sdgsdg','sdgsdg','sdgsdg',19,NULL,NULL,19),
 (24,'dsgds',NULL,'sdh','sdh','sdh',16,NULL,NULL,16),
 (25,'qr',NULL,'qrqwrqw','tqw','qwtqwt',18,NULL,NULL,18);
/*!40000 ALTER TABLE `approval_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`bill_details`
--

DROP TABLE IF EXISTS `bill_details`;
CREATE TABLE `bill_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `can` varchar(255) default NULL,
  `bill_number` varchar(255) default NULL,
  `bill_date` date NOT NULL,
  `bill_time` varchar(255) default NULL,
  `meter_make` varchar(255) default NULL,
  `current_bill_type` varchar(255) default NULL,
  `from_month` varchar(255) default NULL,
  `to_month` varchar(255) default NULL,
  `meter_fix_date` varchar(255) default NULL,
  `initial_reading` varchar(255) default NULL,
  `present_reading` varchar(255) default NULL,
  `units` varchar(255) default NULL,
  `water_cess` varchar(255) default NULL,
  `sewerage_cess` varchar(255) default NULL,
  `service_charge` varchar(255) default NULL,
  `meter_service_charge` varchar(255) default NULL,
  `total_amount` float default NULL,
  `net_payable_amount` float default NULL,
  `telephone_no` varchar(255) default NULL,
  `meter_status` varchar(255) default NULL,
  `mc_met_reader_code` varchar(255) default NULL,
  `bill_flag` varchar(255) default NULL,
  `svr_status` varchar(255) default NULL,
  `terminal_id` varchar(255) default NULL,
  `meter_reader_id` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  `mobile_no` varchar(255) default NULL,
  `notice_no` varchar(255) default NULL,
  `lat` varchar(255) default NULL,
  `long_i` varchar(255) default NULL,
  `nometer_amt` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`bill_details`
--

/*!40000 ALTER TABLE `bill_details` DISABLE KEYS */;
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`mc_met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`long_i`,`nometer_amt`) VALUES 
 (1,'613427474','31197684','2013-11-26','155200','','U','201212','201310','','0','0','110000','1650.00','577.50','110.00','0.00',2337.5,2680.06,'1111111111','R','','Y','1','A0000','0000','123','0000000000','','0','0','0'),
 (2,'613472557','31197746','2013-11-26','155200','','R','201212','201310','','0','0','110000','1650.00','577.50','110.00','0.00',2337.5,2985.07,'2222222222','U','','Y','1','A0000','0000','123','0000000000','','0','0','0'),
 (3,'613472562','31197513','2013-11-26','155200','','R','201212','201310','','0','0','165000','1650.00','577.50','110.00','0.00',2337.5,7174.29,'0000000000','R','','Y','1','A0000','0000','123','0000000000','0000131130172628','0','0','0'),
 (4,'613577519','31197514','2013-11-26','155200','','R','201212','201310','','0','0','165000','1650.00','577.50','110.00','0.00',2337.5,31664.3,'0000000563','R','','Y','1','A0000','0000','123','0000000000','0000131130172738','0','0','0');
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`mc_met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`long_i`,`nometer_amt`) VALUES 
 (5,'617738493','31197668','2013-11-26','155200','','R','201212','201310','','0','0','165000','1650.00','577.50','110.00','0.00',2337.5,2768.88,'0000000002','R','','Y','1','A0000','0000','123','0000000000','','0','0','0'),
 (6,'617738918','D000000001','2013-11-30','173005','','L','201211','201310','','0','0','180000','1800.00','630.00','120.00','0.00',2550,3412.75,'0000000063','L','','','1','A0000','0000','123','0000000000','','0','0','0'),
 (7,'617757078','D000000002','2013-11-30','173050','','M','201209','201310','','0','100000','10000','2100.00','735.00','140.00','0.00',2975,2981.38,'0000000006','U','','','1','A0000','0000','123','0000000000','','0','0','0'),
 (8,'617757083','D000000003','2013-11-30','173124','','M','201209','201310','','0','500000','100000','2100.00','735.00','140.00','0.00',2975,2981.38,'0000000005','M','','','1','A0000','0000','123','0000000000','','0','0','0');
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`mc_met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`long_i`,`nometer_amt`) VALUES 
 (9,'617757111','D000000004','2013-11-30','173208','','L','201211','201310','','0','0','180000','1800.00','630.00','120.00','0.00',2550,3095.56,'0000000009','L','','','1','A0000','0000','123','0000000000','','0','0','0'),
 (10,'617757186','D000000005','2013-11-30','173246','','L','201211','201310','','0','0','180000','1800.00','630.00','120.00','0.00',2550,2981.38,'0000000008','L','','','1','A0000','0000','123','0000000000','','0','0','0'),
 (11,'617771922','D000000006','2013-11-30','173352','','L','201211','201310','','0','0','180000','1800.00','630.00','120.00','0.00',2550,2550,'0000000000','L','','','1','A0000','0000','123','0000000000','','0','0','0'),
 (12,'617781451','D000000007','2013-11-30','173423','','M','201211','201310','','0','400000','40000','1800.00','630.00','120.00','0.00',2550,2550,'0000000002','M','','','1','A0000','0000','123','0000000000','','0','0','0');
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`mc_met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`long_i`,`nometer_amt`) VALUES 
 (13,'617781710','D000000008','2013-11-30','173449','','M','201211','201310','','0','50000','50000','1800.00','630.00','120.00','0.00',2550,2550,'0000000002','M','','','1','A0000','0000','123','0000000000','','0','0','0');
/*!40000 ALTER TABLE `bill_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`bill_full_details`
--

DROP TABLE IF EXISTS `bill_full_details`;
CREATE TABLE `bill_full_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `can` varchar(255) NOT NULL,
  `divcode` varchar(255) NOT NULL,
  `seccode` varchar(255) default NULL,
  `secname` varchar(255) default NULL,
  `metreadercode` varchar(255) default NULL,
  `conn_date` date NOT NULL,
  `cons_name` varchar(255) NOT NULL,
  `house_no` varchar(255) NOT NULL,
  `address` varchar(255) default NULL,
  `city` varchar(255) NOT NULL,
  `pin_code` varchar(255) default NULL,
  `category` varchar(255) default NULL,
  `pipe_size` varchar(255) default NULL,
  `board_meter` varchar(255) default NULL,
  `sewerage` varchar(255) default NULL,
  `meter_no` varchar(255) NOT NULL,
  `prev_bill_type` varchar(255) default NULL,
  `prev_bill_month` varchar(255) default NULL,
  `prev_avg_kl` varchar(255) default NULL,
  `met_reading_dt` date NOT NULL,
  `prev_reading` varchar(255) default NULL,
  `met_reading_mo` varchar(255) default NULL,
  `met_avg_kl` varchar(255) default NULL,
  `arrears` varchar(255) default NULL,
  `reversal_amt` varchar(255) default NULL,
  `installment` varchar(255) default NULL,
  `other_charges` varchar(255) default NULL,
  `sur_charge` varchar(255) default NULL,
  `hrs_sur_charge` varchar(255) default NULL,
  `res_units` varchar(255) default NULL,
  `met_cost_installment` varchar(255) default NULL,
  `int_on_arrears` varchar(255) default NULL,
  `last_pymt_dt` date NOT NULL,
  `last_pymt_amt` float default NULL,
  `bill_number` varchar(255) default NULL,
  `bill_date` date NOT NULL,
  `bill_time` varchar(255) default NULL,
  `meter_make` varchar(255) default NULL,
  `current_bill_type` varchar(255) default NULL,
  `from_month` varchar(255) default NULL,
  `to_month` varchar(255) default NULL,
  `meter_fix_date` varchar(255) default NULL,
  `initial_reading` varchar(255) default NULL,
  `present_reading` varchar(255) default NULL,
  `units` varchar(255) default NULL,
  `water_cess` varchar(255) default NULL,
  `sewerage_cess` varchar(255) default NULL,
  `service_charge` varchar(255) default NULL,
  `meter_service_charge` varchar(255) default NULL,
  `total_amount` float default NULL,
  `net_payable_amount` float default NULL,
  `telephone_no` varchar(255) default NULL,
  `meter_status` varchar(255) default NULL,
  `mc_met_reader_code` varchar(255) default NULL,
  `bill_flag` varchar(255) default NULL,
  `svr_status` varchar(255) default NULL,
  `terminal_id` varchar(255) default NULL,
  `meter_reader_id` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  `mobile_no` varchar(255) default NULL,
  `notice_no` varchar(255) default NULL,
  `lat` varchar(255) default NULL,
  `long_i` varchar(255) default NULL,
  `nometer_amt` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`bill_full_details`
--

/*!40000 ALTER TABLE `bill_full_details` DISABLE KEYS */;
INSERT INTO `bill_full_details` (`id`,`can`,`divcode`,`seccode`,`secname`,`metreadercode`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`sur_charge`,`hrs_sur_charge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`mc_met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`long_i`,`nometer_amt`) VALUES 
 (1,'Can1','Divcode1',NULL,NULL,NULL,'2016-03-07','ConsName1','HouseNo1',NULL,'City1',NULL,NULL,NULL,NULL,NULL,'MeterNo1',NULL,'',NULL,'2016-03-18',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-03-18',11,NULL,'2016-03-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NometerAmt1');
/*!40000 ALTER TABLE `bill_full_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`cash_book_master`
--

DROP TABLE IF EXISTS `cash_book_master`;
CREATE TABLE `cash_book_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `cash_book_entry_type` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`cash_book_master`
--

/*!40000 ALTER TABLE `cash_book_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `cash_book_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`category_master`
--

DROP TABLE IF EXISTS `category_master`;
CREATE TABLE `category_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `category_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`category_master`
--

/*!40000 ALTER TABLE `category_master` DISABLE KEYS */;
INSERT INTO `category_master` (`id`,`category_name`) VALUES 
 (1,'BULK'),
 (2,'INDIVIDUAL/DOMESTIC'),
 (3,'MULTISTORIED/BUILDING'),
 (4,'NON REVENUE CONNECTION');
/*!40000 ALTER TABLE `category_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`category_pipe_size_mapping`
--

DROP TABLE IF EXISTS `category_pipe_size_mapping`;
CREATE TABLE `category_pipe_size_mapping` (
  `id` bigint(20) NOT NULL auto_increment,
  `category_master_id` bigint(20) default NULL,
  `pipe_size_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_categorypipesizemapping_categorymaster_id` (`category_master_id`),
  KEY `fk_categorypipesizemapping_pipesizemaster_id` (`pipe_size_master_id`),
  CONSTRAINT `fk_categorypipesizemapping_categorymaster_id` FOREIGN KEY (`category_master_id`) REFERENCES `category_master` (`id`),
  CONSTRAINT `fk_categorypipesizemapping_pipesizemaster_id` FOREIGN KEY (`pipe_size_master_id`) REFERENCES `pipe_size_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`category_pipe_size_mapping`
--

/*!40000 ALTER TABLE `category_pipe_size_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_pipe_size_mapping` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`coll_details`
--

DROP TABLE IF EXISTS `coll_details`;
CREATE TABLE `coll_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `reversal_ref` varchar(255) default NULL,
  `receipt_no` varchar(255) default NULL,
  `receipt_amt` varchar(255) default NULL,
  `receipt_dt` date default NULL,
  `receipt_time` varchar(255) default NULL,
  `receipt_mode` varchar(255) default NULL,
  `instr_no` varchar(255) default NULL,
  `instr_dt` date default NULL,
  `instr_issuer` varchar(255) default NULL,
  `svr_status` varchar(255) default NULL,
  `can` varchar(255) default NULL,
  `cons_name` varchar(255) default NULL,
  `terminal_id` varchar(255) default NULL,
  `coll_time` timestamp NULL default NULL,
  `txn_status` varchar(255) default NULL,
  `meter_reader_id` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  `remarks` varchar(255) default NULL,
  `settlement_id` varchar(255) default NULL,
  `ext_settlement_id` varchar(255) default NULL,
  `lat` varchar(255) default NULL,
  `long_i` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`coll_details`
--

/*!40000 ALTER TABLE `coll_details` DISABLE KEYS */;
INSERT INTO `coll_details` (`id`,`reversal_ref`,`receipt_no`,`receipt_amt`,`receipt_dt`,`receipt_time`,`receipt_mode`,`instr_no`,`instr_dt`,`instr_issuer`,`svr_status`,`can`,`cons_name`,`terminal_id`,`coll_time`,`txn_status`,`meter_reader_id`,`user_id`,`remarks`,`settlement_id`,`ext_settlement_id`,`lat`,`long_i`) VALUES 
 (1,'ReversalRef1','ReceiptNo1','ReceiptAmt1','2016-03-18','ReceiptTime1','ReceiptMode1',NULL,'2016-03-18',NULL,NULL,NULL,NULL,NULL,'2016-03-18 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'LongI1'),
 (2,'ReversalRef2','ReceiptNo2','ReceiptAmt2','2016-03-18','ReceiptTime2','ReceiptMode2','InstrNo2','2016-03-18',NULL,NULL,NULL,NULL,NULL,'2016-03-18 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'LongI2');
/*!40000 ALTER TABLE `coll_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`configuration_details`
--

DROP TABLE IF EXISTS `configuration_details`;
CREATE TABLE `configuration_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`configuration_details`
--

/*!40000 ALTER TABLE `configuration_details` DISABLE KEYS */;
INSERT INTO `configuration_details` (`id`,`name`,`value`,`description`) VALUES 
 (1,'ADMIN','97',NULL),
 (2,'EWURA','1','EWURA Water Tax Charges (%)');
/*!40000 ALTER TABLE `configuration_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`connection_type_master`
--

DROP TABLE IF EXISTS `connection_type_master`;
CREATE TABLE `connection_type_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `connection_type` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`connection_type_master`
--

/*!40000 ALTER TABLE `connection_type_master` DISABLE KEYS */;
INSERT INTO `connection_type_master` (`id`,`connection_type`) VALUES 
 (1,'Sewerage Connection'),
 (2,'Water And Sewerage');
/*!40000 ALTER TABLE `connection_type_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`current_users`
--

DROP TABLE IF EXISTS `current_users`;
CREATE TABLE `current_users` (
  `id` bigint(20) NOT NULL auto_increment,
  `terminal_id` varchar(255) default NULL,
  `meter_reader_id` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  `request_type` varchar(255) default NULL,
  `login_time` timestamp NULL default NULL,
  `ip` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`current_users`
--

/*!40000 ALTER TABLE `current_users` DISABLE KEYS */;
INSERT INTO `current_users` (`id`,`terminal_id`,`meter_reader_id`,`user_id`,`request_type`,`login_time`,`ip`) VALUES 
 (1,'TerminalId1','MeterReaderId1','UserId1','RequestType1','2016-03-18 00:00:00','Ip1'),
 (2,'sss','sss','sss','ssss','2016-04-12 00:00:00','ssss');
/*!40000 ALTER TABLE `current_users` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`cust_details`
--

DROP TABLE IF EXISTS `cust_details`;
CREATE TABLE `cust_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `can` varchar(255) NOT NULL,
  `div_code` varchar(255) default NULL,
  `sec_code` varchar(255) default NULL,
  `sec_name` varchar(255) default NULL,
  `met_reader_code` varchar(255) default NULL,
  `conn_date` date default NULL,
  `cons_name` varchar(255) NOT NULL,
  `house_no` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `pin_code` varchar(255) default NULL,
  `category` varchar(255) default NULL,
  `pipe_size` varchar(255) default NULL,
  `board_meter` varchar(255) default NULL,
  `sewerage` varchar(255) default NULL,
  `meter_no` varchar(255) default NULL,
  `prev_bill_type` varchar(255) default NULL,
  `prev_bill_month` varchar(255) default NULL,
  `prev_avg_kl` varchar(255) default NULL,
  `met_reading_dt` date default NULL,
  `prev_reading` varchar(255) default NULL,
  `met_reading_mo` varchar(255) default NULL,
  `met_avg_kl` varchar(255) default NULL,
  `arrears` varchar(255) default NULL,
  `reversal_amt` varchar(255) default NULL,
  `installment` varchar(255) default NULL,
  `other_charges` varchar(255) default NULL,
  `surcharge` varchar(255) default NULL,
  `hrs_surcharge` varchar(255) default NULL,
  `res_units` varchar(255) default NULL,
  `met_cost_installment` varchar(255) default NULL,
  `int_on_arrears` varchar(255) default NULL,
  `last_pymt_dt` varchar(255) default NULL,
  `last_pymt_amt` float default NULL,
  `mobile_no` varchar(255) default NULL,
  `cc_flag` varchar(255) default NULL,
  `cp_flag` varchar(255) default NULL,
  `notice_flag` varchar(255) default NULL,
  `dr_flag` varchar(255) default NULL,
  `lat` varchar(255) default NULL,
  `longi` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`cust_details`
--

/*!40000 ALTER TABLE `cust_details` DISABLE KEYS */;
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (1,'613427474','15','0614','MADHAPUR','0000','2005-04-19','R.PRATIBHA ANNAPURNA','5-109/10/22','RADHE NAGAR COLONY,GACHIBOWLI','','500032','D','0.50','F','T','05505137','U','20131001','10','2016-03-13','0','','10','680.06','0.00','0.00','0.00','0','F','0','0.00','5.06','20131130',2000,'0000000000','1','1','0','1','0','0'),
 (2,'613472557','15','0614','MADHAPUR','0000','2005-05-25','N.MANJULATHA','PLOT NO.21','RADHE NAGAR COLONY,H.S.DARGA','','500032','D','0.50','F','T','05-1-005551','R','20131001','10','2016-03-13','0','','10','2785.07','0.00','0.00','0.00','0','F','0','0.00','9.57','20131130',200,'0000000000','0','0','0','1','0','0'),
 (3,'613472562','15','0614','MADHAPUR','0000','2005-05-25','G.MURALI MOHAN','H.NO.33','RADHE NAGAR COLONY,H.S.DARGA.','','500032','D','0.50','F','T','05-1-004920','R','20131001','15','2016-03-13','0','','0','6924.29','0.00','0.00','0.00','0','F','0','0.00','61.94','20131130',250,'0000000000','0','0','0','1','0','0');
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (4,'613577519','15','0614','MADHAPUR','0000','2005-09-21','K.V.V.BALA SUBRAMANA','PLOT NO.19 SY.47','RADHA NAGAR RAIDURGA,MADHAPUR','','500032','D','0.50','F','T','05-1-0017909','R','20131001','15','2016-03-13','0','','6','31417.30','0.00','0.00','0.00','0','F','0','0.00','329.73','20131130',247,'0000000000','0','0','0','1','0','0'),
 (5,'617738493','15','0614','MADHAPUR','0000','2012-08-11','VANIN','3-615/3','PLOT NO. 195','','','D','0.50','F','T','12B40628','R','20131001','15','2016-03-13','0','','15','2508.88','0.00','0.00','0.00','0','F','1','0.00','6.38','20131130',260,'0000000000','0','0','0','1','0','0'),
 (6,'617738918','15','0614','MADHAPUR','0000','2012-07-14','K RAVI','1-60/30/138/135','ANJAIAH NAGAR,GACHIBOWLI','','','D','0.50','F','T','12A19467','L','20131001','15','2016-03-13','0','20120601','0','3032.75','3400.00','0.00','0.00','0','F','1','0.00','12.75','20131130',380,'0000000000','0','0','0','1','0','0');
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (7,'617757078','15','0614','MADHAPUR','0000','2012-09-10','MALOTH.LACHIRAM NAYAK','1-57/31','RAJEEV NAGAR,GACHIBOWLI','','','D','0.50','F','T','12B92564','M','20131001','15','2016-03-13','10000','20131001','0','2481.38','0.00','0.00','0.00','0','F','1','0.00','6.38','20131130',500,'0000000000','0','0','0','1','0','0'),
 (8,'617757083','15','0614','MADHAPUR','0000','2012-09-15','P HIMA BINDU','1-58/103','RAJEEV NAGAR,GACHIBOWLI','','','D','0.50','F','T','11B35430','M','20131001','15','2016-03-13','100000','20131001','0','2581.38','0.00','0.00','0.00','0','F','1','0.00','6.38','20131130',400,'0000000000','0','0','0','1','0','0'),
 (9,'617757111','15','0614','MADHAPUR','0000','2012-08-20','JAFFAR BEE','H.NO.2-63/2/A3','CHINNA ANJAIAH NAGAR,GACHIBOWL','','','D','0.50','F','T','12B40792','L','20131001','15','2016-03-13','0','20120701','0','2995.56','3187.50','0.00','0.00','0','F','1','0.00','8.06','20131130',100,'0000000000','0','0','0','1','0','0');
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (10,'617757186','15','0614','MADHAPUR','0000','2012-09-26','SMT.SHIVALINGAMMA','2-62/2/A/10','GACHIBOWLI VILLAGE','','','D','0.50','F','T','12B36048','L','20131001','15','2016-03-13','0','20120801','0','2746.38','2975.00','0.00','0.00','0','F','1','0.00','6.38','20131130',235,'0000000000','0','0','0','1','0','0'),
 (11,'617771922','15','0614','MADHAPUR','0000','2012-11-01','SYED AZEEM','1-14','OLD GACHIBOWLI','','','D','0.50','F','T','','L','20131001','15','2016-03-13','0','','0','2100.00','2550.00','0.00','0.00','0','F','1','0.00','0.00','20131130',450,'0000000000','0','0','0','1','0','0'),
 (12,'617781451','15','0614','MADHAPUR','0000','2012-11-15','SRINIVAS','1-110/A/32','KONDAPUR W/S COLONY,KONDAPUR','','','D','0.50','F','T','12B53322','M','20131001','15','2016-03-13','400000','20131001','0','1750.00','0.00','0.00','0.00','0','F','1','0.00','0.00','20131130',800,'0000000000','0','0','0','1','0','0');
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (13,'617781710','15','0614','MADHAPUR','0000','2012-11-10','K.DEVIDAS','3-618/2,P.NO.540/B','SCB NAGAR,NEW HAFEEZPET','','','D','0.50','F','T','12B53305','M','20131001','15','2016-03-13','500000','20131001','0','2338.00','0.00','0.00','0.00','0','F','2','0.00','0.00','20131130',212,'0000000000','0','0','0','1','0','0');
/*!40000 ALTER TABLE `cust_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL auto_increment,
  `request_date` timestamp NULL default NULL,
  `first_name` varchar(255) default NULL,
  `middle_name` varchar(255) default NULL,
  `last_name` varchar(255) default NULL,
  `house_no` varchar(255) default NULL,
  `govt_official_no` varchar(255) default NULL,
  `ward` varchar(255) default NULL,
  `street` varchar(255) default NULL,
  `pincode` varchar(255) default NULL,
  `block` varchar(255) default NULL,
  `area` varchar(255) default NULL,
  `section` varchar(255) default NULL,
  `constituency` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `tel_office` varchar(255) default NULL,
  `tel_home` varchar(255) default NULL,
  `mobile` varchar(255) default NULL,
  `file_number_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_customer_filenumber_id` (`file_number_id`),
  CONSTRAINT `fk_customer_filenumber_id` FOREIGN KEY (`file_number_id`) REFERENCES `file_number` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`,`request_date`,`first_name`,`middle_name`,`last_name`,`house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`tel_office`,`tel_home`,`mobile`,`file_number_id`) VALUES 
 (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'2016-03-03 00:00:00','mohib',NULL,'khan','123','589','kpbh','5','50003','kukatpally','kphb','258','hyd','thisismohib','147','852','1447',1),
 (3,'2016-03-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),
 (4,'2016-03-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),
 (5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (7,'2016-03-04 00:00:00','Mohib',NULL,'Khan','123',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),
 (8,'2016-03-04 00:00:00','Mohib','ullah','khan','dA','AF','Q','AF','AAF','Af','AF','QFR','FQ','QWTQWT','QW','WEYW','AWTQW',1);
INSERT INTO `customer` (`id`,`request_date`,`first_name`,`middle_name`,`last_name`,`house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`tel_office`,`tel_home`,`mobile`,`file_number_id`) VALUES 
 (9,'2016-03-04 00:00:00','asd','sdfas',NULL,'sdh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),
 (10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (13,'2016-03-04 00:00:00','mohib','ullah','khan','123','bvnbch','jhjgjhg','jjh','775765','hgjhgjfh','jhjgf','as','hhjgh','bgh@gmail.com','87987','8798798','87987',1),
 (14,'2016-03-04 00:00:00','mohib','ullah','khan','123','369','hug','258','1148','gjy','369','2548','jh','ashkjhs@gmail','8876786','876876','76876',2),
 (15,'2016-03-04 00:00:00','mohib','ullah','khan','123','369','hug','258','1148','gjy','369','2548','jh','ashkjhs@gmail','8876786','876876','76876',2),
 (16,'2016-03-04 00:00:00','mohib','ullah','khan','123','369','hug','258','1148','gjy','369','2548','jh','ashkjhs@gmail','8876786','876876','76876',2);
INSERT INTO `customer` (`id`,`request_date`,`first_name`,`middle_name`,`last_name`,`house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`tel_office`,`tel_home`,`mobile`,`file_number_id`) VALUES 
 (17,'2016-03-04 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (18,'2016-03-04 00:00:00','AR','AT','WET','WEY','WEY','WEY','WEY','WEY','WEY','WEY','WEY','WEY','ATA','WERYWE','ERU','ERU',2),
 (19,'2016-03-04 00:00:00','hjk','kjhj','hkj','hkjh','hkj','kjh','kjhkj','hkjh','kjh','hkj','kjh','kjh','89878','0907','8798','7987',1),
 (20,'2016-03-07 00:00:00','mohib',NULL,'khan','147','889','888','855','9999','9999','998','8996','999','999','9999','999','999',1),
 (21,'2016-03-07 00:00:00','mohib',NULL,'khan','9','88','788','78','887','788','88','778','997','5567','888','8888','876',2),
 (22,'2016-03-07 00:00:00','hkjhjk','kjhkj','hkjh','kjhkj','hkj','hkj','hjk','hkj','h','hkj','hkj','kjh','hkj','kjhkj','hkj','hkj',3),
 (23,'2016-03-07 00:00:00','hghg','hgjh','jhgjhg','hgjh','ghjh','hjhkj','hgjj','hgjh','hkjhkjh','hjhjhg','jhgj','hkkjhj','hkhkjff','khkjkhj','hhjjk','kjkhkj',4);
INSERT INTO `customer` (`id`,`request_date`,`first_name`,`middle_name`,`last_name`,`house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`tel_office`,`tel_home`,`mobile`,`file_number_id`) VALUES 
 (24,'2016-03-07 00:00:00','jgjgkj','jkjhkjh','kjhkjh','jkhkjhjkh','khhjkh','hjhhjkj','kjhkhk','jhj','hkjhkjh','jhkjh','jhkj','kjhkjh','khkjh','kjhkjhkj','hkjhkj','hkjh',4),
 (25,'2016-03-07 00:00:00','kjkjh','jkhkjh','kjhkjh','hjkhkh','hkj','jhk','jhk','hjh','hkjh','hkjjk','jhkjh','kjhk','hkhjh','jhkh','kjh','khk',2),
 (26,'2016-03-07 00:00:00','jkjhkj','hkjhkjh','hjjkh','jhkjh','jhkjhkjh','kjhkjh','jhkjhk','kjhkjh','kjhk','jkhkjhk','jkhkjh','hk','hkj','hkjhkj','hkjh','khkj',4),
 (27,'2016-03-07 00:00:00','kjhkj','jhkhj','kjhlk','jhkjh','hjkh','kjh','jhjk','kjh','jkhkj','hkjhkj','jkhkjh','hkjh','hkj','kjh','jh','kjhkj',2),
 (28,'2016-03-07 00:00:00','asf','saf','assgf','asg','ag','sag','ag','asg','asg','asg','asga','aasg','asg','sdgsd','g','ssg',3),
 (29,'2016-03-08 00:00:00','awrf','sg','sg','sdhsh','shsd','shs','shs','sdhs','sfhshf','shshdh','shsd','shsdf','sg','dfhs','sh','sh',2);
INSERT INTO `customer` (`id`,`request_date`,`first_name`,`middle_name`,`last_name`,`house_no`,`govt_official_no`,`ward`,`street`,`pincode`,`block`,`area`,`section`,`constituency`,`email`,`tel_office`,`tel_home`,`mobile`,`file_number_id`) VALUES 
 (30,'2016-03-08 00:00:00','kj','jhg','ujjkk','bhb','.b',',mn,mn','kkjkj',',,m,mn','mnjh','hk',',mn,mn','gh','vhjh','ghgc','chc','h',3),
 (31,'2016-03-08 00:00:00','asd','lkj','iuy','hgh','ghg','v ccnbv','hgjg','jhg','vbnfhgd','hjh','hvbn','vhvhjh','jkgjjhj','vhjvg','tutty','6876876',3),
 (32,'2016-03-10 00:00:00','jj','jhkjh','jhkjh','jhkjh','kjhjk','hkj','jhjkh','hkj','hkjh','jkh','hkj','kjh','jhkj','jkhkjh','jh','jkh',3),
 (33,'2016-03-11 00:00:00','235','23','236','236','236','236','236','236','236','236','236','236','236','236','26','326',2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `TAG` varchar(255) default NULL,
  `LIQUIBASE` varchar(20) default NULL,
  `CONTEXTS` varchar(255) default NULL,
  `LABELS` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`databasechangelog`
--

/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('00000000000001','jhipster','classpath:config/liquibase/changelog/00000000000000_initial_schema.xml','2016-02-24 18:37:40',1,'EXECUTED','7:e5d421759980df5ea9b5cd2ebcfd994c','createTable, createIndex (x2), createTable (x2), addPrimaryKey, createTable, addForeignKeyConstraint (x3), loadData, dropDefaultValue, loadData (x2), createTable (x2), addPrimaryKey, createIndex (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160224131058','jhipster','classpath:config/liquibase/changelog/20160224131058_added_entity_SewerSize.xml','2016-02-26 10:35:06',2,'EXECUTED','7:be40d7c60d89c57e1672aaaed4915727','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229064640','jhipster','classpath:config/liquibase/changelog/20160229064640_added_entity_ApplicationTypeMaster.xml','2016-02-29 12:31:59',3,'EXECUTED','7:c1c9f7b3be47bd9dca371ebec3968605','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160229065150','jhipster','classpath:config/liquibase/changelog/20160229065150_added_entity_ConnectionTypeMaster.xml','2016-02-29 12:31:59',4,'EXECUTED','7:1d2213aedb239d233daaff9d017fe21f','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229065305','jhipster','classpath:config/liquibase/changelog/20160229065305_added_entity_CategoryMaster.xml','2016-02-29 12:32:00',5,'EXECUTED','7:bdbad9897632fcb3b27b84614eb65631','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229065514','jhipster','classpath:config/liquibase/changelog/20160229065514_added_entity_PipeSizeMaster.xml','2016-02-29 12:32:00',6,'EXECUTED','7:5530b835ccc14b682cdd22ef99c3cd80','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229065700','jhipster','classpath:config/liquibase/changelog/20160229065700_added_entity_CategoryPipeSizeMapping.xml','2016-02-29 12:32:01',7,'EXECUTED','7:10b74427e2abf13a1e46b02177b879c5','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160229070517','jhipster','classpath:config/liquibase/changelog/20160229070517_added_entity_FileNumber.xml','2016-02-29 14:06:34',8,'EXECUTED','7:d3e42819856cbf28b67058bba741014f','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229070707','jhipster','classpath:config/liquibase/changelog/20160229070707_added_entity_TransactionTypeMaster.xml','2016-02-29 14:06:34',9,'EXECUTED','7:afd37db16070bbc1db18016656893a32','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229070800','jhipster','classpath:config/liquibase/changelog/20160229070800_added_entity_CashBookMaster.xml','2016-02-29 14:06:34',10,'EXECUTED','7:d0decf027ebc34ee609eba6744ef51c2','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229070919','jhipster','classpath:config/liquibase/changelog/20160229070919_added_entity_PaymentTypes.xml','2016-02-29 14:06:34',11,'EXECUTED','7:ef4e1a7913b69b2869492e1ec5644d0d','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229071846','jhipster','classpath:config/liquibase/changelog/20160229071846_added_entity_Customer.xml','2016-02-29 14:06:35',12,'EXECUTED','7:0846444118a7c9657f6a537077f6c1d0','createTable, dropDefaultValue, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229072353','jhipster','classpath:config/liquibase/changelog/20160229072353_added_entity_ManageCashPoint.xml','2016-02-29 14:06:39',13,'EXECUTED','7:063f36a456a8a13982a11343a8a8dddd','createTable, dropDefaultValue, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL),
 ('20160229073210','jhipster','classpath:config/liquibase/changelog/20160229073210_added_entity_StatusMaster.xml','2016-02-29 14:06:40',14,'EXECUTED','7:37a09476506400cb3134316685ddbca6','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229073547','jhipster','classpath:config/liquibase/changelog/20160229073547_added_entity_DesignationMaster.xml','2016-02-29 14:06:41',15,'EXECUTED','7:127eba27413f9aa0a5e653084544e03a','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229073648','jhipster','classpath:config/liquibase/changelog/20160229073648_added_entity_FeasibilityStatus.xml','2016-02-29 14:06:41',16,'EXECUTED','7:190180bbcfa1c279424b098aae93a57c','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229074219','jhipster','classpath:config/liquibase/changelog/20160229074219_added_entity_ReAllotment.xml','2016-02-29 14:06:46',18,'EXECUTED','7:eb523405877dcf8d7dd83dc9199b8ea3','createTable, addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229075018','jhipster','classpath:config/liquibase/changelog/20160229075018_added_entity_ApplicationTxn.xml','2016-02-29 14:06:51',19,'EXECUTED','7:ff65e8435ea7b29464676823a9a5eb0c','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x7)','',NULL,'3.4.2',NULL,NULL),
 ('20160229084044','jhipster','classpath:config/liquibase/changelog/20160229084044_added_entity_ConfigurationDetails.xml','2016-02-29 15:03:13',20,'EXECUTED','7:839bd04dd36ec11b9c3f6f6db979cc63','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229085139','jhipster','classpath:config/liquibase/changelog/20160229085139_added_entity_DesigCategoryMaster.xml','2016-02-29 15:03:14',21,'EXECUTED','7:fdd173d8f6e7a9bc336c5a494f57f052','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229085352','jhipster','classpath:config/liquibase/changelog/20160229085352_added_entity_SubDesigCategoryMaster.xml','2016-02-29 15:03:15',22,'EXECUTED','7:c1f7021bd38767b73129e94983eb54cf','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229085805','jhipster','classpath:config/liquibase/changelog/20160229085805_added_entity_GroupMaster.xml','2016-02-29 15:03:16',23,'EXECUTED','7:4c67fc449b40cce038c464ecc57d55d0','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229090028','jhipster','classpath:config/liquibase/changelog/20160229090028_added_entity_OrgHierarchy.xml','2016-02-29 15:03:17',24,'EXECUTED','7:fd82c8c6649f4fc468f75eda51211b68','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229090217','jhipster','classpath:config/liquibase/changelog/20160229090217_added_entity_DesignationMappings.xml','2016-02-29 15:03:20',25,'EXECUTED','7:a32248161fb67e22dcb376886d1f8d32','createTable, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229090442','jhipster','classpath:config/liquibase/changelog/20160229090442_added_entity_DepartmentsHierarchy.xml','2016-02-29 15:03:21',26,'EXECUTED','7:f61491f7db54a4caa099623241809053','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229090611','jhipster','classpath:config/liquibase/changelog/20160229090611_added_entity_DepartmentTypeMaster.xml','2016-02-29 15:03:23',27,'EXECUTED','7:877befbbede5aa2ae023cef1c740eda5','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229091226','jhipster','classpath:config/liquibase/changelog/20160229091226_added_entity_DepartmentsMaster.xml','2016-02-29 15:03:25',28,'EXECUTED','7:269c35f7c51323070df2bb00cb146173','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229091411','jhipster','classpath:config/liquibase/changelog/20160229091411_added_entity_OrgRoleHierarchy.xml','2016-02-29 15:03:26',29,'EXECUTED','7:2917d963a991b8d25ed698800fb6d36c','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229092048','jhipster','classpath:config/liquibase/changelog/20160229092048_added_entity_OrgRoleInstance.xml','2016-02-29 15:03:29',30,'EXECUTED','7:80c939e32d702c86b401ccf87087024d','createTable, dropDefaultValue (x3), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229092244','jhipster','classpath:config/liquibase/changelog/20160229092244_added_entity_OrgRolesMaster.xml','2016-02-29 15:03:30',31,'EXECUTED','7:88f6b41a493861a105157b995199f4a1','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229092636','jhipster','classpath:config/liquibase/changelog/20160229092636_added_entity_WorkflowTypeMaster.xml','2016-02-29 15:03:31',32,'EXECUTED','7:c51656b7eeb13d3c2e615135ac3b1361','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229093926','jhipster','classpath:config/liquibase/changelog/20160229093926_added_entity_EmpMaster.xml','2016-02-29 15:12:23',33,'EXECUTED','7:3252e1c25b98fa713f2887568665564d','createTable, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL),
 ('20160229095205','jhipster','classpath:config/liquibase/changelog/20160229095205_added_entity_EmpRoleMapping.xml','2016-02-29 15:49:21',34,'EXECUTED','7:fc3da7a2667930eac07790ad970de8c1','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229095400','jhipster','classpath:config/liquibase/changelog/20160229095400_added_entity_WorkflowMaster.xml','2016-02-29 15:49:22',35,'EXECUTED','7:9e74b57e21636b130a51342ef513fa9b','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229095700','jhipster','classpath:config/liquibase/changelog/20160229095700_added_entity_RequestMaster.xml','2016-02-29 15:49:23',36,'EXECUTED','7:4ea16d61dc9c1631995ebcaa24172261','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229095841','jhipster','classpath:config/liquibase/changelog/20160229095841_added_entity_WorkflowStageMaster.xml','2016-02-29 15:49:24',37,'EXECUTED','7:b232f3ff1a94a47509fc8dcf9d99f1d4','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229095927','jhipster','classpath:config/liquibase/changelog/20160229095927_added_entity_WorkflowRelations.xml','2016-02-29 15:49:25',38,'EXECUTED','7:8f71bbc6064b9fe8aa41799e214bbc10','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229100042','jhipster','classpath:config/liquibase/changelog/20160229100042_added_entity_WorkflowRelationships.xml','2016-02-29 15:49:26',39,'EXECUTED','7:af9db222b3e3f53e4df472506e8d6995','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229100254','jhipster','classpath:config/liquibase/changelog/20160229100254_added_entity_ReqDesigWorkflowMapping.xml','2016-02-29 15:49:29',40,'EXECUTED','7:1e7a0c353f3ba594bbb665b568452d48','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229100431','jhipster','classpath:config/liquibase/changelog/20160229100431_added_entity_ReqOrgWorkflowMapping.xml','2016-02-29 15:49:32',41,'EXECUTED','7:f3cade643b99f3ca2eaf4c44cf764f9c','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229100821','jhipster','classpath:config/liquibase/changelog/20160229100821_added_entity_RoleWorkflowMapping.xml','2016-02-29 15:49:35',42,'EXECUTED','7:25c22c3ba0d09c89818f178cb23b9aea','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229100952','jhipster','classpath:config/liquibase/changelog/20160229100952_added_entity_RequestWorkflowMapping.xml','2016-02-29 15:49:37',43,'EXECUTED','7:e2e3affdf6fd6213512081f5a7c52acd','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229101231','jhipster','classpath:config/liquibase/changelog/20160229101231_added_entity_WorkflowTxnDetails.xml','2016-02-29 15:49:38',44,'EXECUTED','7:ffd53ef669a1b6a5fdacfdfc2c63c8d4','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229111312','jhipster','classpath:config/liquibase/changelog/20160229111312_added_entity_Workflow.xml','2016-02-29 16:56:15',45,'EXECUTED','7:aa3bbe903f8a96b777e321dc647773ab','createTable, addForeignKeyConstraint (x10)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229111821','jhipster','classpath:config/liquibase/changelog/20160229111821_added_entity_RequestWorkflowHistory.xml','2016-02-29 16:56:21',46,'EXECUTED','7:df037b52eebabe97bca6601174750057','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x7)','',NULL,'3.4.2',NULL,NULL),
 ('20160229074020','jhipster','classpath:config/liquibase/changelog/20160229074020_added_entity_ApprovalDetails.xml','2016-03-08 14:21:51',47,'EXECUTED','7:37e6360d74c522cda74763685e8f3e6e','createTable, dropDefaultValue, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160309103544','jhipster','classpath:config/liquibase/changelog/20160309103544_added_entity_Module.xml','2016-03-09 16:14:40',48,'EXECUTED','7:ae553e293db4d292b225cd36fb0ba43c','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160309103701','jhipster','classpath:config/liquibase/changelog/20160309103701_added_entity_MenuItem.xml','2016-03-09 16:14:41',49,'EXECUTED','7:bd9888add9c5af34bcdca93e50add4d1','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160309103829','jhipster','classpath:config/liquibase/changelog/20160309103829_added_entity_Url.xml','2016-03-09 16:14:41',50,'EXECUTED','7:1889bdedffb7df6fe11f9bc33d0efa35','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160309104001','jhipster','classpath:config/liquibase/changelog/20160309104001_added_entity_MenuItem2Url.xml','2016-03-09 16:14:43',51,'EXECUTED','7:902843a076e3bb258db90badb9e1dc9a','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160309104200','jhipster','classpath:config/liquibase/changelog/20160309104200_added_entity_Module2MenuItem.xml','2016-03-09 16:14:45',52,'EXECUTED','7:9824330082db5729d7b5d6bf940348df','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160309105304','jhipster','classpath:config/liquibase/changelog/20160309105304_added_entity_Url2Role.xml','2016-03-09 16:34:47',53,'EXECUTED','7:10331a2dfcdaf6902d65c17510f31bc4','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160311094234','jhipster','classpath:config/liquibase/changelog/20160311094234_added_entity_SchemeMaster.xml','2016-03-11 15:21:26',54,'EXECUTED','7:af80987658cadbe9d18ef393fd5559c7','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094550','jhipster','classpath:config/liquibase/changelog/20160311094550_added_entity_MakeOfPipe.xml','2016-03-11 15:21:26',56,'EXECUTED','7:0aae809bc0a38e5aa294a887f735acb8','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160311094820','jhipster','classpath:config/liquibase/changelog/20160311094820_added_entity_MainWaterSize.xml','2016-03-11 15:21:26',57,'EXECUTED','7:0e853a5e0c7721503d0203d88333bd91','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094904','jhipster','classpath:config/liquibase/changelog/20160311094904_added_entity_MainSewerageSize.xml','2016-03-11 15:21:27',58,'EXECUTED','7:03b9b58c058199566e8d55fd5635b97d','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311095014','jhipster','classpath:config/liquibase/changelog/20160311095014_added_entity_DocketCode.xml','2016-03-11 15:21:27',59,'EXECUTED','7:9b3b9b3180ffc95d73eadf3d1294c5e0','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311100455','jhipster','classpath:config/liquibase/changelog/20160311100455_added_entity_FeasibilityStudy.xml','2016-03-11 15:35:47',60,'EXECUTED','7:02ff1a8ab9783cbd5eaec740ffa6d9cc','createTable, addForeignKeyConstraint (x12)','',NULL,'3.4.2',NULL,NULL),
 ('20160314085424','jhipster','classpath:config/liquibase/changelog/20160314085424_added_entity_AccessList.xml','2016-03-14 16:13:27',61,'EXECUTED','7:0bbcbab98b16045642e3ecbe7f8d5e68','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160315053144','jhipster','classpath:config/liquibase/changelog/20160315053144_added_entity_CollDetails.xml','2016-03-15 11:54:55',64,'EXECUTED','7:15f4f13ea9c62925eda8f2dfe76b5250','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160315053612','jhipster','classpath:config/liquibase/changelog/20160315053612_added_entity_CurrentUsers.xml','2016-03-15 11:54:55',65,'EXECUTED','7:8a4a035e32b0b643cecd398c7f310072','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160315055126','jhipster','classpath:config/liquibase/changelog/20160315055126_added_entity_Terminal.xml','2016-03-15 11:54:55',66,'EXECUTED','7:e1d4bfddad1bbb1d0f00ddd8f44aa0b0','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160315055926','jhipster','classpath:config/liquibase/changelog/20160315055926_added_entity_TerminalLog.xml','2016-03-15 11:54:55',67,'EXECUTED','7:68ef0c670ad1ca54884347c8f8ce48a0','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160315060107','jhipster','classpath:config/liquibase/changelog/20160315060107_added_entity_Version.xml','2016-03-15 11:54:56',68,'EXECUTED','7:b17c4a085e285d295f72ab3d5f873ecf','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160314104149','jhipster','classpath:config/liquibase/changelog/20160314104149_added_entity_BillFullDetails.xml','2016-03-15 18:27:22',69,'EXECUTED','7:2e904c908c3eea4a5836561fad0dda47','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160314104149','jhipster','classpath:config/liquibase/changelog/20160314104149_added_entity_BillDetails.xml','2016-03-28 17:40:06',70,'EXECUTED','7:d4acae70934c11a3b823cff795b26406','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160314132343','jhipster','classpath:config/liquibase/changelog/20160314132343_added_entity_CustDetails.xml','2016-03-29 12:48:32',71,'EXECUTED','7:bbd26d35aa2c97875a53556da90c045d','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffCategoryMaster.xml','2016-03-30 12:06:06',72,'EXECUTED','7:c01f3faca05eff0386e65260cc81849e','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffMaster.xml','2016-03-30 12:06:07',73,'EXECUTED','7:de67571b345cd24609fcbc1d6d8e12ab','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffTypeMaster.xml','2016-03-30 12:06:07',74,'EXECUTED','7:2fdf8de5ece58a24c9842690d2599317','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160401094431','jhipster','classpath:config/liquibase/changelog/20160401094431_added_entity_TariffCharges.xml','2016-04-01 14:13:10',75,'EXECUTED','7:84a4098f6e6489da14e4b14c2d948a2c','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL);
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime default NULL,
  `LOCKEDBY` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`databasechangeloglock`
--

/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` (`ID`,`LOCKED`,`LOCKGRANTED`,`LOCKEDBY`) VALUES 
 (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`department_type_master`
--

DROP TABLE IF EXISTS `department_type_master`;
CREATE TABLE `department_type_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `description` varchar(255) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_departmenttypemaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_departmenttypemaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`department_type_master`
--

/*!40000 ALTER TABLE `department_type_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_type_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`departments_hierarchy`
--

DROP TABLE IF EXISTS `departments_hierarchy`;
CREATE TABLE `departments_hierarchy` (
  `id` bigint(20) NOT NULL auto_increment,
  `dept_hierarchy_name` varchar(255) default NULL,
  `parent_dept_hierarchy_id` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_departmentshierarchy_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_departmentshierarchy_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`departments_hierarchy`
--

/*!40000 ALTER TABLE `departments_hierarchy` DISABLE KEYS */;
/*!40000 ALTER TABLE `departments_hierarchy` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`departments_master`
--

DROP TABLE IF EXISTS `departments_master`;
CREATE TABLE `departments_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `department_name` varchar(255) default NULL,
  `parent_deparment` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `departments_hierarchy_id` bigint(20) default NULL,
  `department_type_master_id` bigint(20) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
/*!40000 ALTER TABLE `departments_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`desig_category_master`
--

DROP TABLE IF EXISTS `desig_category_master`;
CREATE TABLE `desig_category_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `description` varchar(255) default NULL,
  `alias` varchar(255) default NULL,
  `order_by` int(11) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_desigcategorymaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_desigcategorymaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`desig_category_master`
--

/*!40000 ALTER TABLE `desig_category_master` DISABLE KEYS */;
INSERT INTO `desig_category_master` (`id`,`name`,`creation_date`,`last_modified_date`,`description`,`alias`,`order_by`,`status_master_id`) VALUES 
 (1,'DRDS','2016-03-03 00:00:00','2016-03-03 00:00:00','Scientists','123',1,NULL);
/*!40000 ALTER TABLE `desig_category_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`designation_mappings`
--

DROP TABLE IF EXISTS `designation_mappings`;
CREATE TABLE `designation_mappings` (
  `id` bigint(20) NOT NULL auto_increment,
  `type` varchar(255) default NULL,
  `desig_category_master_id` bigint(20) default NULL,
  `sub_desig_category_master_id` bigint(20) default NULL,
  `designation_master_id` bigint(20) default NULL,
  `group_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
/*!40000 ALTER TABLE `designation_mappings` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`designation_master`
--

DROP TABLE IF EXISTS `designation_master`;
CREATE TABLE `designation_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `description` varchar(255) default NULL,
  `order_no` int(11) default NULL,
  `service_type` varchar(255) default NULL,
  `code` varchar(255) default NULL,
  `desigalias` varchar(255) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_designationmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_designationmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`designation_master`
--

/*!40000 ALTER TABLE `designation_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `designation_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`division_master`
--

DROP TABLE IF EXISTS `division_master`;
CREATE TABLE `division_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `division_name` varchar(255) default NULL,
  `division_code` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`division_master`
--

/*!40000 ALTER TABLE `division_master` DISABLE KEYS */;
INSERT INTO `division_master` (`id`,`division_name`,`division_code`) VALUES 
 (1,'D1','DCode1'),
 (2,'D2','DCode2'),
 (3,'D3','DCode3'),
 (4,'D4','DCode4'),
 (5,'D5','DCode5'),
 (6,'D6','DCode6'),
 (7,'D7','DCode7'),
 (8,'D8','DCode8'),
 (9,'D9','DCode9'),
 (10,'D10','DCode10');
/*!40000 ALTER TABLE `division_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`docket_code`
--

DROP TABLE IF EXISTS `docket_code`;
CREATE TABLE `docket_code` (
  `id` bigint(20) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`docket_code`
--

/*!40000 ALTER TABLE `docket_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `docket_code` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`emp_master`
--

DROP TABLE IF EXISTS `emp_master`;
CREATE TABLE `emp_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL,
  `office_id_id` bigint(20) default NULL,
  `designation_master_id` bigint(20) default NULL,
  `directorate_id_id` bigint(20) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`emp_master`
--

/*!40000 ALTER TABLE `emp_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`emp_role_mapping`
--

DROP TABLE IF EXISTS `emp_role_mapping`;
CREATE TABLE `emp_role_mapping` (
  `id` bigint(20) NOT NULL auto_increment,
  `internal_division` varchar(255) default NULL,
  `internal_role` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `parent_role_id` int(11) default NULL,
  `user_id` bigint(20) default NULL,
  `parent_user_id` bigint(20) default NULL,
  `org_role_instance_id` bigint(20) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_emprolemapping_user_id` (`user_id`),
  KEY `fk_emprolemapping_parentuser_id` (`parent_user_id`),
  KEY `fk_emprolemapping_orgroleinstance_id` (`org_role_instance_id`),
  KEY `fk_emprolemapping_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_emprolemapping_orgroleinstance_id` FOREIGN KEY (`org_role_instance_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_emprolemapping_parentuser_id` FOREIGN KEY (`parent_user_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_emprolemapping_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_emprolemapping_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`emp_role_mapping`
--

/*!40000 ALTER TABLE `emp_role_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp_role_mapping` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`feasibility_status`
--

DROP TABLE IF EXISTS `feasibility_status`;
CREATE TABLE `feasibility_status` (
  `id` bigint(20) NOT NULL auto_increment,
  `status` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`feasibility_status`
--

/*!40000 ALTER TABLE `feasibility_status` DISABLE KEYS */;
INSERT INTO `feasibility_status` (`id`,`status`) VALUES 
 (1,'Application Accepted');
/*!40000 ALTER TABLE `feasibility_status` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`feasibility_study`
--

DROP TABLE IF EXISTS `feasibility_study`;
CREATE TABLE `feasibility_study` (
  `id` bigint(20) NOT NULL auto_increment,
  `plot_area_in_sq_mtrs` float default NULL,
  `plot_area_in_yards` float default NULL,
  `no_of_flats_or_no_of_units` int(11) default NULL,
  `no_of_floors` int(11) default NULL,
  `total_plinth_area` float default NULL,
  `water_requirement` float default NULL,
  `scheme_master_id` bigint(20) default NULL,
  `tariff_category_master_id` bigint(20) default NULL,
  `make_of_water_pipe_id` bigint(20) default NULL,
  `make_of_sewerage_pipe_id` bigint(20) default NULL,
  `main_water_size_id` bigint(20) default NULL,
  `main_sewerage_size_id` bigint(20) default NULL,
  `docket_code_id` bigint(20) default NULL,
  `application_txn_id` bigint(20) default NULL,
  `category_master_id` bigint(20) default NULL,
  `sewer_size_id` bigint(20) default NULL,
  `pipe_size_master_id` bigint(20) default NULL,
  `feasibility_status_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_feasibilitystudy_schememaster_id` (`scheme_master_id`),
  KEY `fk_feasibilitystudy_tariffcategorymaster_id` (`tariff_category_master_id`),
  KEY `fk_feasibilitystudy_makeofwaterpipe_id` (`make_of_water_pipe_id`),
  KEY `fk_feasibilitystudy_makeofseweragepipe_id` (`make_of_sewerage_pipe_id`),
  KEY `fk_feasibilitystudy_mainwatersize_id` (`main_water_size_id`),
  KEY `fk_feasibilitystudy_mainseweragesize_id` (`main_sewerage_size_id`),
  KEY `fk_feasibilitystudy_docketcode_id` (`docket_code_id`),
  KEY `fk_feasibilitystudy_applicationtxn_id` (`application_txn_id`),
  KEY `fk_feasibilitystudy_categorymaster_id` (`category_master_id`),
  KEY `fk_feasibilitystudy_sewersize_id` (`sewer_size_id`),
  KEY `fk_feasibilitystudy_pipesizemaster_id` (`pipe_size_master_id`),
  KEY `fk_feasibilitystudy_feasibilitystatus_id` (`feasibility_status_id`),
  CONSTRAINT `fk_feasibilitystudy_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_feasibilitystudy_categorymaster_id` FOREIGN KEY (`category_master_id`) REFERENCES `category_master` (`id`),
  CONSTRAINT `fk_feasibilitystudy_docketcode_id` FOREIGN KEY (`docket_code_id`) REFERENCES `docket_code` (`id`),
  CONSTRAINT `fk_feasibilitystudy_feasibilitystatus_id` FOREIGN KEY (`feasibility_status_id`) REFERENCES `feasibility_status` (`id`),
  CONSTRAINT `fk_feasibilitystudy_mainseweragesize_id` FOREIGN KEY (`main_sewerage_size_id`) REFERENCES `main_sewerage_size` (`id`),
  CONSTRAINT `fk_feasibilitystudy_mainwatersize_id` FOREIGN KEY (`main_water_size_id`) REFERENCES `main_water_size` (`id`),
  CONSTRAINT `fk_feasibilitystudy_makeofseweragepipe_id` FOREIGN KEY (`make_of_sewerage_pipe_id`) REFERENCES `make_of_pipe` (`id`),
  CONSTRAINT `fk_feasibilitystudy_makeofwaterpipe_id` FOREIGN KEY (`make_of_water_pipe_id`) REFERENCES `make_of_pipe` (`id`),
  CONSTRAINT `fk_feasibilitystudy_pipesizemaster_id` FOREIGN KEY (`pipe_size_master_id`) REFERENCES `pipe_size_master` (`id`),
  CONSTRAINT `fk_feasibilitystudy_schememaster_id` FOREIGN KEY (`scheme_master_id`) REFERENCES `scheme_master` (`id`),
  CONSTRAINT `fk_feasibilitystudy_sewersize_id` FOREIGN KEY (`sewer_size_id`) REFERENCES `sewer_size` (`id`),
  CONSTRAINT `fk_feasibilitystudy_tariffcategorymaster_id` FOREIGN KEY (`tariff_category_master_id`) REFERENCES `tariff_category_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`feasibility_study`
--

/*!40000 ALTER TABLE `feasibility_study` DISABLE KEYS */;
/*!40000 ALTER TABLE `feasibility_study` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`file_number`
--

DROP TABLE IF EXISTS `file_number`;
CREATE TABLE `file_number` (
  `id` bigint(20) NOT NULL auto_increment,
  `file_no` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`file_number`
--

/*!40000 ALTER TABLE `file_number` DISABLE KEYS */;
INSERT INTO `file_number` (`id`,`file_no`) VALUES 
 (1,'F_101'),
 (2,'F_102'),
 (3,'F_103'),
 (4,'F_104'),
 (5,'F_105');
/*!40000 ALTER TABLE `file_number` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`group_master`
--

DROP TABLE IF EXISTS `group_master`;
CREATE TABLE `group_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `description` varchar(255) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_groupmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_groupmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`group_master`
--

/*!40000 ALTER TABLE `group_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`item_details`
--

DROP TABLE IF EXISTS `item_details`;
CREATE TABLE `item_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `item_code` varchar(255) default NULL,
  `item_name` varchar(255) default NULL,
  `item_description` varchar(255) default NULL,
  `size` varchar(255) default NULL,
  `item_quantity` int(11) default NULL,
  `unit_price` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_details`
--

/*!40000 ALTER TABLE `item_details` DISABLE KEYS */;
INSERT INTO `item_details` (`id`,`item_code`,`item_name`,`item_description`,`size`,`item_quantity`,`unit_price`) VALUES 
 (1,NULL,'Pipe','1m','1',1,10),
 (2,NULL,'Pipe 2.5','2.5m','2',1,20),
 (3,NULL,'Threading tape',NULL,'10',1,15),
 (4,NULL,'G. S. Pipe',NULL,'3',1,25);
/*!40000 ALTER TABLE `item_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`item_required`
--

DROP TABLE IF EXISTS `item_required`;
CREATE TABLE `item_required` (
  `id` bigint(20) NOT NULL auto_increment,
  `quantity` int(11) default NULL,
  `item_details_id` bigint(20) default NULL,
  `feasibility_study_id` bigint(20) default NULL,
  `application_txn_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_itemrequired_itemdetails_id` (`item_details_id`),
  KEY `fk_itemrequired_feasibilitystudy_id` (`feasibility_study_id`),
  KEY `fk_itemrequired_applicationtxn_id` (`application_txn_id`),
  CONSTRAINT `fk_itemrequired_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_itemrequired_feasibilitystudy_id` FOREIGN KEY (`feasibility_study_id`) REFERENCES `feasibility_study` (`id`),
  CONSTRAINT `fk_itemrequired_itemdetails_id` FOREIGN KEY (`item_details_id`) REFERENCES `item_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_required`
--

/*!40000 ALTER TABLE `item_required` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_required` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_authority`
--

/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` (`name`) VALUES 
 ('ROLE_ADMIN'),
 ('ROLE_CUSTOMER'),
 ('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL auto_increment,
  `principal` varchar(255) NOT NULL,
  `event_date` timestamp NULL default NULL,
  `event_type` varchar(255) default NULL,
  PRIMARY KEY  (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_persistent_audit_event`
--

/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
 (1,'admin','2016-02-24 18:38:04','AUTHENTICATION_SUCCESS'),
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
 (16,'admin','2016-03-07 09:48:13','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (32,'customer','2016-03-07 16:41:12','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (48,'admin','2016-03-09 10:06:53','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (64,'customer','2016-03-10 10:33:58','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (80,'customer','2016-03-10 15:18:53','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
 (81,'admin','2016-03-10 15:53:24','AUTHENTICATION_SUCCESS'),
 (82,'customer','2016-03-10 17:21:19','AUTHENTICATION_SUCCESS'),
 (83,'admin','2016-03-11 10:14:38','AUTHENTICATION_SUCCESS'),
 (84,'admin','2016-03-14 13:16:23','AUTHENTICATION_SUCCESS'),
 (85,'admin','2016-03-15 16:36:05','AUTHENTICATION_SUCCESS'),
 (86,'admin','2016-03-15 17:38:19','AUTHENTICATION_SUCCESS'),
 (87,'customer','2016-03-15 17:41:30','AUTHENTICATION_SUCCESS'),
 (88,'admin','2016-03-15 17:46:54','AUTHENTICATION_SUCCESS'),
 (89,'admin','2016-03-16 11:51:05','AUTHENTICATION_SUCCESS'),
 (90,'admin','2016-03-16 14:31:31','AUTHENTICATION_SUCCESS'),
 (91,'admin','2016-03-17 10:19:30','AUTHENTICATION_SUCCESS'),
 (92,'admin','2016-03-18 13:27:33','AUTHENTICATION_SUCCESS'),
 (93,'admin','2016-03-18 14:38:11','AUTHENTICATION_SUCCESS'),
 (94,'admin','2016-03-18 14:38:25','AUTHENTICATION_SUCCESS'),
 (95,'admin','2016-03-21 15:41:46','AUTHENTICATION_SUCCESS'),
 (96,'admin','2016-03-21 19:04:21','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
 (97,'admin','2016-03-21 19:04:32','AUTHENTICATION_SUCCESS'),
 (98,'admin','2016-03-22 09:47:30','AUTHENTICATION_SUCCESS'),
 (99,'admin','2016-03-22 12:38:02','AUTHENTICATION_SUCCESS'),
 (100,'admin','2016-03-22 17:49:19','AUTHENTICATION_SUCCESS'),
 (101,'admin','2016-03-22 18:04:19','AUTHENTICATION_SUCCESS'),
 (102,'admin','2016-03-23 11:23:36','AUTHENTICATION_SUCCESS'),
 (103,'admin','2016-03-23 15:47:08','AUTHENTICATION_FAILURE'),
 (104,'ADMIN','2016-03-23 15:47:35','AUTHENTICATION_FAILURE'),
 (105,'admin','2016-03-23 15:55:37','AUTHENTICATION_SUCCESS'),
 (106,'admin','2016-03-23 17:43:14','AUTHENTICATION_SUCCESS'),
 (107,'admin','2016-03-24 10:18:09','AUTHENTICATION_SUCCESS'),
 (108,'admin','2016-03-24 13:04:48','AUTHENTICATION_SUCCESS'),
 (109,'admin','2016-03-28 16:15:34','AUTHENTICATION_SUCCESS'),
 (110,'admin','2016-03-29 11:38:31','AUTHENTICATION_SUCCESS'),
 (111,'admin','2016-03-29 13:56:03','AUTHENTICATION_SUCCESS'),
 (112,'admin','2016-03-29 14:19:03','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
 (113,'admin','2016-03-30 15:02:27','AUTHENTICATION_SUCCESS'),
 (114,'admin','2016-03-30 15:26:48','AUTHENTICATION_SUCCESS'),
 (115,'admin','2016-03-30 16:56:02','AUTHENTICATION_SUCCESS'),
 (116,'admin','2016-03-30 18:28:01','AUTHENTICATION_SUCCESS'),
 (117,'admin','2016-03-31 19:09:31','AUTHENTICATION_SUCCESS'),
 (118,'admin','2016-04-01 10:07:36','AUTHENTICATION_SUCCESS'),
 (119,'admin','2016-04-01 10:35:52','AUTHENTICATION_SUCCESS'),
 (120,'admin','2016-04-01 11:41:20','AUTHENTICATION_SUCCESS'),
 (121,'admin','2016-04-01 12:18:30','AUTHENTICATION_SUCCESS'),
 (122,'admin','2016-04-01 12:24:39','AUTHENTICATION_SUCCESS'),
 (123,'admin','2016-04-01 14:52:55','AUTHENTICATION_SUCCESS'),
 (124,'admin','2016-04-01 15:10:57','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) default NULL,
  PRIMARY KEY  (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_persistent_audit_evt_data`
--

/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
 (1,'remoteAddress','0:0:0:0:0:0:0:1'),
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
 (11,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (21,'sessionId','3920A42E28C08EB421079A0E7B38657E');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (31,'sessionId','84BCBFA069FDF19CF9FEB6F7D2EC3B07');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (42,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (52,'sessionId','2B98554668A1AEF6EFD7E36CCAB1FBC9');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (63,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (73,'sessionId','95D6D9211BE063E997A41AED5A0916E8');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (84,'remoteAddress','127.0.0.1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
 (84,'sessionId','6374EC838E6B2E5A86DD11297E95C24A'),
 (85,'remoteAddress','127.0.0.1'),
 (85,'sessionId','3AB1BC0B270D79595CE16BD5D0979A4A'),
 (86,'remoteAddress','127.0.0.1'),
 (86,'sessionId','89D76B5A024F285874E7B0EB354313AE'),
 (87,'remoteAddress','127.0.0.1'),
 (87,'sessionId','155AEA249ED408D6B2137F7FFB357BCA'),
 (88,'remoteAddress','127.0.0.1'),
 (88,'sessionId','63617245318514B248998A8B9958DA18'),
 (89,'remoteAddress','127.0.0.1'),
 (89,'sessionId','E80EFB1489602030024DABDC1896319F'),
 (90,'remoteAddress','127.0.0.1'),
 (90,'sessionId','087F7AD16C520A469B781F841B04FA90'),
 (91,'remoteAddress','169.254.178.179'),
 (91,'sessionId','5E4A28B2435D291A910B780E9B2CD767'),
 (92,'remoteAddress','127.0.0.1'),
 (92,'sessionId','99568A9FEF33C51170A1B25620923F33'),
 (93,'remoteAddress','127.0.0.1'),
 (93,'sessionId','9637F51215829C152296697576E397D3'),
 (94,'remoteAddress','127.0.0.1'),
 (94,'sessionId','2FC81B79B70D6E468515EDB1391E2987'),
 (95,'remoteAddress','127.0.0.1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
 (95,'sessionId','947CB8279D957CB004DC6F85C4B2DB93'),
 (96,'remoteAddress','127.0.0.1'),
 (96,'sessionId','28D87A0893F79C8D881861A13FDFF3F1'),
 (97,'remoteAddress','127.0.0.1'),
 (97,'sessionId','EE71883C58F744615ADE8C94B759A01B'),
 (98,'remoteAddress','127.0.0.1'),
 (98,'sessionId','C2534A040F06D6498015393D2A01DD40'),
 (99,'remoteAddress','127.0.0.1'),
 (99,'sessionId','B25564FE5D847A3F2B9DE7683245AD9B'),
 (100,'remoteAddress','127.0.0.1'),
 (100,'sessionId','DB22C3352E1D453D6ED2AC387E313FA6'),
 (101,'remoteAddress','127.0.0.1'),
 (101,'sessionId','2A981A3C5F07C2C2FDDFF5D08A815431'),
 (102,'remoteAddress','127.0.0.1'),
 (102,'sessionId','85CC9D63ABF69763147AC0CBAB484B27'),
 (103,'message','Bad credentials'),
 (103,'type','org.springframework.security.authentication.BadCredentialsException'),
 (104,'message','Bad credentials'),
 (104,'type','org.springframework.security.authentication.BadCredentialsException'),
 (105,'remoteAddress','127.0.0.1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
 (105,'sessionId','2AB2A0B631D79F46974EB68542CFF55A'),
 (106,'remoteAddress','127.0.0.1'),
 (106,'sessionId','DB22283023A75F82F91B1514AC4F672E'),
 (107,'remoteAddress','127.0.0.1'),
 (107,'sessionId','597C16D7771AE96010B931B132156216'),
 (108,'remoteAddress','127.0.0.1'),
 (108,'sessionId','D6B78D35672069E9949A73579D4D2F94'),
 (109,'remoteAddress','127.0.0.1'),
 (109,'sessionId','C598609AD12D79C2912184AB489575E8'),
 (110,'remoteAddress','127.0.0.1'),
 (110,'sessionId','112456BC4B4F8FA4C7C5AC209463523F'),
 (111,'remoteAddress','0:0:0:0:0:0:0:1'),
 (111,'sessionId','31B4C3F636A85DBA24099CDE6B24C3C0'),
 (112,'remoteAddress','0:0:0:0:0:0:0:1'),
 (112,'sessionId','9A41E8AE4AE7EE6B35955EDE8E726A12'),
 (113,'remoteAddress','0:0:0:0:0:0:0:1'),
 (113,'sessionId','7600B45F4E366F0FDFFF7B4DB5C3B9F0'),
 (114,'remoteAddress','0:0:0:0:0:0:0:1'),
 (114,'sessionId','25F5A1DF6E3AC55289BB1EED82E9B378'),
 (115,'remoteAddress','0:0:0:0:0:0:0:1'),
 (115,'sessionId','1672728C33CBDF7BC38608432E43FB69');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
 (116,'remoteAddress','0:0:0:0:0:0:0:1'),
 (116,'sessionId','28E61BD62430C198E729ED4DB68893F7'),
 (117,'remoteAddress','0:0:0:0:0:0:0:1'),
 (117,'sessionId','77C2D570E258F9D771218F3F5A285A5E'),
 (118,'remoteAddress','127.0.0.1'),
 (118,'sessionId','B8F34910D5705763F9C46453A5FD3623'),
 (119,'remoteAddress','127.0.0.1'),
 (119,'sessionId','E7B4AB3FA943DCCF1DD280A97E039378'),
 (120,'remoteAddress','127.0.0.1'),
 (120,'sessionId','627FBACB79F7E0394CAD0BF115CFEBDE'),
 (121,'remoteAddress','127.0.0.1'),
 (121,'sessionId','51527384DB01E73DA4CEBBB7341BCCBE'),
 (122,'remoteAddress','127.0.0.1'),
 (122,'sessionId','CE023DF115D1A0D6A5B9F79DF5B8D34C'),
 (123,'remoteAddress','127.0.0.1'),
 (123,'sessionId','AA314B841A899394521A108CBA49F694'),
 (124,'remoteAddress','127.0.0.1'),
 (124,'sessionId','E65CB81A168BAB1FA68304BA5A7CA1D4');
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`jhi_persistent_token`
--

DROP TABLE IF EXISTS `jhi_persistent_token`;
CREATE TABLE `jhi_persistent_token` (
  `series` varchar(255) NOT NULL,
  `user_id` bigint(20) default NULL,
  `token_value` varchar(255) NOT NULL,
  `token_date` date default NULL,
  `ip_address` varchar(39) default NULL,
  `user_agent` varchar(255) default NULL,
  PRIMARY KEY  (`series`),
  KEY `fk_user_persistent_token` (`user_id`),
  CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_persistent_token`
--

/*!40000 ALTER TABLE `jhi_persistent_token` DISABLE KEYS */;
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('+7kfspW2WJpi2bKM/dP9lw==',3,'hBwLquQ1naRCYyedhwXeJA==','2016-03-17','169.254.178.179','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('+kuZ8fh+MT05jeTHFp5gmw==',5,'/FPZJ9OhXHNXmMOFgz7obg==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('2r6Z/StVCqb6VQE17jT96A==',3,'PCvfc7Hey8JJ8tf5lIycdg==','2016-03-23','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36'),
 ('2uRUyClVWXV8AlYMNeuJOw==',3,'PErAzkiwxOnl+ZDZP6T7JQ==','2016-03-09','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('4Kz54liz72Tw0RjO2Zrkpw==',3,'x3C2IoyZxxmQ8IZ4lG8oMg==','2016-03-03','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0'),
 ('6B2RG6/uWQnWC28vOw8aXg==',3,'8QX7Ux+CmA4n198EaajbnA==','2016-03-08','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('7Ytkb8b+j1F3nPGWDNtKcw==',3,'l96/eI6viuEUtDy5AibxbA==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('8FWdd/b8HL6zl541Kpr54A==',3,'OQxQHa8eBK/6lZG3uZPUhQ==','2016-03-26','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('9iL/4QMAvR0xB64rW5HESw==',3,'/k/ylVdhu5TQbQGtpI5N0w==','2016-03-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('bcCO32K28oP7r/PbgTTlXA==',3,'x/GmKXJ4i+FtuZWSHRBe0w==','2016-03-16','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('DEqQipDM3iLfE1PFx+mwRA==',3,'rlSkhLwJf+8q3MOatcrcDA==','2016-02-26','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('dOUzFnBhKu3El7plQu9D8g==',3,'t/g+E732euarMu6XC1r0sw==','2016-03-24','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('E4Oy802QfILWTBjkwYm95A==',3,'PeGsLDuZMTMKFV/0eIveBQ==','2016-03-03','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0'),
 ('F2kB3vVrHHh0k5/n0nIzKA==',3,'70Zv5H6ShoO3S9SvA5QpCg==','2016-03-07','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('H5h1sXuT/PROO3BRuTQoxQ==',3,'8Mdnjzl+cjTeaivY6fzJyg==','2016-03-08','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('I1obpFAJGvnvP+unr/4qpA==',3,'DyVyLUyM4Y83gbnjA7x6yQ==','2016-04-01','127.0.0.1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('jEwMwchTTuqCLrwkSt2ARA==',3,'+3EGK4k26JImuyZpwYIppA==','2016-03-18','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('mLyebPZIjbx7SSunOdf+Wg==',3,'41vTjgSojIAoGzh7zCvKlA==','2016-03-07','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('nsyg6h+LdTgXwbH56+FakA==',3,'TVLj32ZdV7zQcM/9mWq5fA==','2016-03-15','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('P0e/MdJF+ZPyjCmmCP/VLA==',3,'X1UpUG5k1rbHVscMv8bt+g==','2016-03-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('pEEiFf7B/NhJo5aEvI40wQ==',3,'TZNUHLSBFRr1XY4ecdlXzA==','2016-03-31','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('q3EjfXcMrSJFW7SlKnUuSw==',3,'f/ln79tU4x1LjeMjm0/mbg==','2016-03-22','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('rXIEaYCdRMm4gzrZtlo3+Q==',3,'TgmeEQSq33s5paxqyFtkmQ==','2016-02-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('rXxoQKNaiKgwPBXkYCm5RQ==',3,'nZ3Z39G+GWqRWUQGXDRT1w==','2016-03-22','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('uBmBvYHKZMXr5UHwlzKAPA==',3,'oIXVYnHZ2CAqS/lxhNHtBQ==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('w8R34xrr8QJmlP5bdbRg0Q==',3,'xrnCE2P4+F4Gm2wY/shF3w==','2016-03-02','127.0.0.1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('x4nyB9VRFeiND5t0i1DU8Q==',3,'cEW9l632DDQaJ+cxS1szpw==','2016-02-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('XDGXmczwil8KeD6KL6Yt1w==',3,'fbROZLGioPS/YUzyTyG2kg==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('zy6YAeHzjqbMHb8NPwiMQg==',3,'RfJrmS7MnC9GxnetOTdgpg==','2016-03-11','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
/*!40000 ALTER TABLE `jhi_persistent_token` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) default NULL,
  `first_name` varchar(50) default NULL,
  `last_name` varchar(50) default NULL,
  `email` varchar(100) default NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) default NULL,
  `activation_key` varchar(20) default NULL,
  `reset_key` varchar(20) default NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `reset_date` timestamp NULL default NULL,
  `last_modified_by` varchar(50) default NULL,
  `last_modified_date` timestamp NULL default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_user`
--

/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` (`id`,`login`,`password_hash`,`first_name`,`last_name`,`email`,`activated`,`lang_key`,`activation_key`,`reset_key`,`created_by`,`created_date`,`reset_date`,`last_modified_by`,`last_modified_date`) VALUES 
 (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','','en',NULL,NULL,'system','2016-02-24 18:37:37',NULL,'admin','2016-03-07 14:55:39'),
 (2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','','en',NULL,NULL,'system','2016-02-24 18:37:37',NULL,NULL,NULL),
 (3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','en',NULL,NULL,'system','2016-02-24 18:37:37',NULL,NULL,NULL),
 (4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','en',NULL,NULL,'system','2016-02-24 18:37:37',NULL,NULL,NULL),
 (5,'customer','$2a$10$TYs/wcvo6fAPu0Xdt.jP3.pol1TJpvr13XvYIlBDzlut/Ytlsn/AO','customer','customer','customer@localhost','','en','NULL',NULL,'anonymousUser','2016-03-04 18:31:04',NULL,'admin','2016-03-08 10:00:31');
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`jhi_user_authority`
--

/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` (`user_id`,`authority_name`) VALUES 
 (1,'ROLE_ADMIN'),
 (3,'ROLE_ADMIN'),
 (5,'ROLE_CUSTOMER'),
 (1,'ROLE_USER'),
 (3,'ROLE_USER'),
 (4,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`main_sewerage_size`
--

DROP TABLE IF EXISTS `main_sewerage_size`;
CREATE TABLE `main_sewerage_size` (
  `id` bigint(20) NOT NULL auto_increment,
  `size` float NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`main_sewerage_size`
--

/*!40000 ALTER TABLE `main_sewerage_size` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_sewerage_size` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`main_water_size`
--

DROP TABLE IF EXISTS `main_water_size`;
CREATE TABLE `main_water_size` (
  `id` bigint(20) NOT NULL auto_increment,
  `size` float NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`main_water_size`
--

/*!40000 ALTER TABLE `main_water_size` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_water_size` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`make_of_pipe`
--

DROP TABLE IF EXISTS `make_of_pipe`;
CREATE TABLE `make_of_pipe` (
  `id` bigint(20) NOT NULL auto_increment,
  `make_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`make_of_pipe`
--

/*!40000 ALTER TABLE `make_of_pipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `make_of_pipe` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`manage_cash_point`
--

DROP TABLE IF EXISTS `manage_cash_point`;
CREATE TABLE `manage_cash_point` (
  `id` bigint(20) NOT NULL auto_increment,
  `today_date` timestamp NULL default NULL,
  `payee_name` varchar(255) default NULL,
  `txn_amount` float default NULL,
  `open_bal` float default NULL,
  `avail_bal` float default NULL,
  `total_receipts` int(11) default NULL,
  `location_code` varchar(255) default NULL,
  `transaction_type_master_id` bigint(20) default NULL,
  `cash_book_master_id` bigint(20) default NULL,
  `payment_types_id` bigint(20) default NULL,
  `file_number_id` bigint(20) default NULL,
  `customer_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
/*!40000 ALTER TABLE `manage_cash_point` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`menu_item`
--

DROP TABLE IF EXISTS `menu_item`;
CREATE TABLE `menu_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `modified_date` timestamp NULL default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`menu_item`
--

/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
INSERT INTO `menu_item` (`id`,`name`,`path`,`modified_date`) VALUES 
 (1,'Module','#/modules','2016-03-09 00:00:00'),
 (2,'Application Form','#/applicationTxns','2016-03-10 00:00:00'),
 (3,'Menu Items','#/menuItems','2016-03-10 00:00:00'),
 (4,'Url','#/urls','2016-03-10 00:00:00'),
 (5,'Menu Item 2 Urls','#/menuItem2Urls','2016-03-10 00:00:00'),
 (6,'Module 2 Menu Items','#/module2MenuItems','2016-03-10 00:00:00'),
 (7,'Url 2 Roles','#/url2Roles','2016-03-10 00:00:00'),
 (8,'Application Details','#/applicationTxns/all','2016-03-10 00:00:00'),
 (9,'Manage Cash Point','#/manageCashPoints','2016-03-10 00:00:00'),
 (10,'Access List','#/accessLists','2016-03-15 00:00:00'),
 (11,'Bill Full Details','#/billFullDetailss','2016-03-15 00:00:00'),
 (12,'Collection Details','#/collDetailss','2016-03-15 00:00:00'),
 (13,'Current Users','#/currentUserss','2016-03-15 00:00:00'),
 (14,'Customer Details','#/custDetailss','2016-03-15 00:00:00'),
 (15,'Terminal','#/terminals','2016-03-15 00:00:00'),
 (16,'Terminal Log','#/terminalLogs','2016-03-15 00:00:00'),
 (17,'Version','#/versions','2016-03-15 00:00:00');
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`menu_item2_url`
--

DROP TABLE IF EXISTS `menu_item2_url`;
CREATE TABLE `menu_item2_url` (
  `id` bigint(20) NOT NULL auto_increment,
  `menu_item_id` bigint(20) default NULL,
  `url_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_menuitem2url_menuitem_id` (`menu_item_id`),
  KEY `fk_menuitem2url_url_id` (`url_id`),
  CONSTRAINT `fk_menuitem2url_menuitem_id` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_item` (`id`),
  CONSTRAINT `fk_menuitem2url_url_id` FOREIGN KEY (`url_id`) REFERENCES `url` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`menu_item2_url`
--

/*!40000 ALTER TABLE `menu_item2_url` DISABLE KEYS */;
INSERT INTO `menu_item2_url` (`id`,`menu_item_id`,`url_id`) VALUES 
 (1,1,1),
 (2,2,2),
 (3,3,3),
 (4,4,4),
 (5,5,5),
 (6,6,6),
 (7,7,7),
 (8,8,8),
 (9,9,9),
 (10,10,10),
 (11,11,11),
 (12,12,12),
 (13,13,13),
 (14,14,14),
 (15,15,15),
 (16,16,16),
 (17,17,17);
/*!40000 ALTER TABLE `menu_item2_url` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`mmg_material_master`
--

DROP TABLE IF EXISTS `mmg_material_master`;
CREATE TABLE `mmg_material_master` (
  `id` bigint(10) NOT NULL,
  `material_name` varchar(200) default NULL,
  `consumable_flag` varchar(5) default NULL,
  `uom_id` bigint(10) default NULL,
  `category_id` bigint(10) default NULL,
  `sub_category_id` bigint(10) default NULL,
  `item_code_id` bigint(10) default NULL,
  `item_sub_code_id` bigint(10) default NULL,
  `rate_contract_flag` varchar(5) default NULL,
  `unit_rate` bigint(126) default NULL,
  `description` varchar(500) default NULL,
  `status` bigint(10) default NULL,
  `creation_date` datetime default NULL,
  `last_modified_date` datetime default NULL,
  `company_code_id` bigint(10) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`mmg_material_master`
--

/*!40000 ALTER TABLE `mmg_material_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `mmg_material_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`mmg_terms_master`
--

DROP TABLE IF EXISTS `mmg_terms_master`;
CREATE TABLE `mmg_terms_master` (
  `id` bigint(10) NOT NULL auto_increment,
  `name` varchar(25) default NULL,
  `description` varchar(500) default NULL,
  `status` bigint(10) default NULL,
  `creation_date` datetime default NULL,
  `last_modified_date` datetime default NULL,
  `tax_type_id` bigint(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`mmg_terms_master`
--

/*!40000 ALTER TABLE `mmg_terms_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `mmg_terms_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`module`
--

DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `priority` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`module`
--

/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` (`id`,`name`,`priority`,`modified_date`) VALUES 
 (1,'Role Management',1,'2016-03-09 00:00:00'),
 (2,'Connection',2,'2016-03-10 00:00:00'),
 (3,'Billing and Collection',3,'2016-03-15 00:00:00');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`module2_menu_item`
--

DROP TABLE IF EXISTS `module2_menu_item`;
CREATE TABLE `module2_menu_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `priority` int(11) default NULL,
  `module_id` bigint(20) default NULL,
  `menu_item_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_module2menuitem_module_id` (`module_id`),
  KEY `fk_module2menuitem_menuitem_id` (`menu_item_id`),
  CONSTRAINT `fk_module2menuitem_menuitem_id` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_item` (`id`),
  CONSTRAINT `fk_module2menuitem_module_id` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`module2_menu_item`
--

/*!40000 ALTER TABLE `module2_menu_item` DISABLE KEYS */;
INSERT INTO `module2_menu_item` (`id`,`priority`,`module_id`,`menu_item_id`) VALUES 
 (1,1,2,2),
 (2,2,1,1),
 (3,3,1,3),
 (4,4,1,4),
 (5,5,1,5),
 (6,6,1,6),
 (7,7,1,7),
 (8,8,2,8),
 (9,9,2,9),
 (10,1,3,10),
 (11,2,3,11),
 (12,3,3,12),
 (13,4,3,13),
 (14,5,3,14),
 (15,6,3,15),
 (16,7,3,16),
 (17,8,3,17);
/*!40000 ALTER TABLE `module2_menu_item` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`org_hierarchy`
--

DROP TABLE IF EXISTS `org_hierarchy`;
CREATE TABLE `org_hierarchy` (
  `id` bigint(20) NOT NULL auto_increment,
  `hierarchy_name` varchar(255) default NULL,
  `parent_hierarchy_id` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_orghierarchy_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_orghierarchy_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_hierarchy`
--

/*!40000 ALTER TABLE `org_hierarchy` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_hierarchy` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`org_role_hierarchy`
--

DROP TABLE IF EXISTS `org_role_hierarchy`;
CREATE TABLE `org_role_hierarchy` (
  `id` bigint(20) NOT NULL auto_increment,
  `role_hierarchy_name` varchar(255) default NULL,
  `parent_role_hierarchy_id` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_orgrolehierarchy_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_orgrolehierarchy_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_role_hierarchy`
--

/*!40000 ALTER TABLE `org_role_hierarchy` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_role_hierarchy` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`org_role_instance`
--

DROP TABLE IF EXISTS `org_role_instance`;
CREATE TABLE `org_role_instance` (
  `id` bigint(20) NOT NULL auto_increment,
  `org_role_name` varchar(255) default NULL,
  `parent_org_role_id` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `is_head` timestamp NULL default NULL,
  `status_master_id` bigint(20) default NULL,
  `org_role_hierarchy_id` bigint(20) default NULL,
  `departments_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_orgroleinstance_statusmaster_id` (`status_master_id`),
  KEY `fk_orgroleinstance_orgrolehierarchy_id` (`org_role_hierarchy_id`),
  KEY `fk_orgroleinstance_departmentsmaster_id` (`departments_master_id`),
  CONSTRAINT `fk_orgroleinstance_departmentsmaster_id` FOREIGN KEY (`departments_master_id`) REFERENCES `departments_master` (`id`),
  CONSTRAINT `fk_orgroleinstance_orgrolehierarchy_id` FOREIGN KEY (`org_role_hierarchy_id`) REFERENCES `org_role_hierarchy` (`id`),
  CONSTRAINT `fk_orgroleinstance_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_role_instance`
--

/*!40000 ALTER TABLE `org_role_instance` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_role_instance` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`org_roles_master`
--

DROP TABLE IF EXISTS `org_roles_master`;
CREATE TABLE `org_roles_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `org_role_name` varchar(255) default NULL,
  `hierarchy_id` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_orgrolesmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_orgrolesmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`org_roles_master`
--

/*!40000 ALTER TABLE `org_roles_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_roles_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`payment_types`
--

DROP TABLE IF EXISTS `payment_types`;
CREATE TABLE `payment_types` (
  `id` bigint(20) NOT NULL auto_increment,
  `payment_mode` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`payment_types`
--

/*!40000 ALTER TABLE `payment_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_types` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`pipe_size_master`
--

DROP TABLE IF EXISTS `pipe_size_master`;
CREATE TABLE `pipe_size_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `pipe_size` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`pipe_size_master`
--

/*!40000 ALTER TABLE `pipe_size_master` DISABLE KEYS */;
INSERT INTO `pipe_size_master` (`id`,`pipe_size`) VALUES 
 (1,15),
 (2,20),
 (3,25),
 (4,40),
 (5,50);
/*!40000 ALTER TABLE `pipe_size_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`proceedings`
--

DROP TABLE IF EXISTS `proceedings`;
CREATE TABLE `proceedings` (
  `id` bigint(20) NOT NULL auto_increment,
  `water_supply_connection_charges` float NOT NULL,
  `water_other_charges` float default NULL,
  `sixty_days_consumption_charges` float NOT NULL,
  `water_supply_improvement_charges` float default NULL,
  `meter_cost` float default NULL,
  `green_brigade_charges` float default NULL,
  `rwhs_charges` float default NULL,
  `total_water_charges` float default NULL,
  `sewerage_connection_charges` float default NULL,
  `sewerage_other_charges` float default NULL,
  `sewerge_improvement_charges` float default NULL,
  `total_sewerage_charges` float default NULL,
  `total_amount` float default NULL,
  `total_deduction` float default NULL,
  `balance` float default NULL,
  `application_txn_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_proceedings_applicationtxn_id` (`application_txn_id`),
  CONSTRAINT `fk_proceedings_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`proceedings`
--

/*!40000 ALTER TABLE `proceedings` DISABLE KEYS */;
INSERT INTO `proceedings` (`id`,`water_supply_connection_charges`,`water_other_charges`,`sixty_days_consumption_charges`,`water_supply_improvement_charges`,`meter_cost`,`green_brigade_charges`,`rwhs_charges`,`total_water_charges`,`sewerage_connection_charges`,`sewerage_other_charges`,`sewerge_improvement_charges`,`total_sewerage_charges`,`total_amount`,`total_deduction`,`balance`,`application_txn_id`) VALUES 
 (1,1,564,564,54,6554,6554,6554,654,6554,6554,654,6554,6545,654,6545,1);
/*!40000 ALTER TABLE `proceedings` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`re_allotment`
--

DROP TABLE IF EXISTS `re_allotment`;
CREATE TABLE `re_allotment` (
  `id` bigint(20) NOT NULL auto_increment,
  `file_number_id` bigint(20) default NULL,
  `customer_id` bigint(20) default NULL,
  `feasibility_status_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
/*!40000 ALTER TABLE `re_allotment` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`req_desig_workflow_mapping`
--

DROP TABLE IF EXISTS `req_desig_workflow_mapping`;
CREATE TABLE `req_desig_workflow_mapping` (
  `id` bigint(20) NOT NULL auto_increment,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `workflow_master_id` bigint(20) default NULL,
  `request_master_id` bigint(20) default NULL,
  `designation_master_id` bigint(20) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
/*!40000 ALTER TABLE `req_desig_workflow_mapping` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`req_org_workflow_mapping`
--

DROP TABLE IF EXISTS `req_org_workflow_mapping`;
CREATE TABLE `req_org_workflow_mapping` (
  `id` bigint(20) NOT NULL auto_increment,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `workflow_master_id` bigint(20) default NULL,
  `request_master_id` bigint(20) default NULL,
  `org_role_instance_id` bigint(20) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_reqorgworkflowmapping_workflowmaster_id` (`workflow_master_id`),
  KEY `fk_reqorgworkflowmapping_requestmaster_id` (`request_master_id`),
  KEY `fk_reqorgworkflowmapping_orgroleinstance_id` (`org_role_instance_id`),
  KEY `fk_reqorgworkflowmapping_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_reqorgworkflowmapping_orgroleinstance_id` FOREIGN KEY (`org_role_instance_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_reqorgworkflowmapping_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`),
  CONSTRAINT `fk_reqorgworkflowmapping_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_reqorgworkflowmapping_workflowmaster_id` FOREIGN KEY (`workflow_master_id`) REFERENCES `workflow_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`req_org_workflow_mapping`
--

/*!40000 ALTER TABLE `req_org_workflow_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `req_org_workflow_mapping` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`request_master`
--

DROP TABLE IF EXISTS `request_master`;
CREATE TABLE `request_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `request_type` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `description` varchar(255) default NULL,
  `internal_flag` int(11) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_requestmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_requestmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`request_master`
--

/*!40000 ALTER TABLE `request_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `request_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`request_workflow_history`
--

DROP TABLE IF EXISTS `request_workflow_history`;
CREATE TABLE `request_workflow_history` (
  `id` bigint(20) NOT NULL auto_increment,
  `request_stage` int(11) default NULL,
  `assigned_date` timestamp NULL default NULL,
  `actioned_date` timestamp NULL default NULL,
  `remarks` varchar(255) default NULL,
  `ip_address` varchar(255) default NULL,
  `assigned_role` int(11) default NULL,
  `domain_object` bigint(20) default NULL,
  `assigned_from_id` bigint(20) default NULL,
  `assigned_to_id` bigint(20) default NULL,
  `status_master_id` bigint(20) default NULL,
  `request_master_id` bigint(20) default NULL,
  `workflow_master_id` bigint(20) default NULL,
  `workflow_stage_master_id` bigint(20) default NULL,
  `applied_by_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`request_workflow_history`
--

/*!40000 ALTER TABLE `request_workflow_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `request_workflow_history` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`request_workflow_mapping`
--

DROP TABLE IF EXISTS `request_workflow_mapping`;
CREATE TABLE `request_workflow_mapping` (
  `id` bigint(20) NOT NULL auto_increment,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `status_master_id` bigint(20) default NULL,
  `workflow_master_id` bigint(20) default NULL,
  `request_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
/*!40000 ALTER TABLE `request_workflow_mapping` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`role_workflow_mapping`
--

DROP TABLE IF EXISTS `role_workflow_mapping`;
CREATE TABLE `role_workflow_mapping` (
  `id` bigint(20) NOT NULL auto_increment,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `status_master_id` bigint(20) default NULL,
  `org_role_instance_id` bigint(20) default NULL,
  `workflow_master_id` bigint(20) default NULL,
  `request_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_roleworkflowmapping_statusmaster_id` (`status_master_id`),
  KEY `fk_roleworkflowmapping_orgroleinstance_id` (`org_role_instance_id`),
  KEY `fk_roleworkflowmapping_workflowmaster_id` (`workflow_master_id`),
  KEY `fk_roleworkflowmapping_requestmaster_id` (`request_master_id`),
  CONSTRAINT `fk_roleworkflowmapping_orgroleinstance_id` FOREIGN KEY (`org_role_instance_id`) REFERENCES `org_role_instance` (`id`),
  CONSTRAINT `fk_roleworkflowmapping_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`),
  CONSTRAINT `fk_roleworkflowmapping_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `fk_roleworkflowmapping_workflowmaster_id` FOREIGN KEY (`workflow_master_id`) REFERENCES `workflow_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`role_workflow_mapping`
--

/*!40000 ALTER TABLE `role_workflow_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_workflow_mapping` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`scheme_master`
--

DROP TABLE IF EXISTS `scheme_master`;
CREATE TABLE `scheme_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `scheme_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`scheme_master`
--

/*!40000 ALTER TABLE `scheme_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheme_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`sewer_size`
--

DROP TABLE IF EXISTS `sewer_size`;
CREATE TABLE `sewer_size` (
  `id` bigint(20) NOT NULL auto_increment,
  `sewer_size` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`sewer_size`
--

/*!40000 ALTER TABLE `sewer_size` DISABLE KEYS */;
INSERT INTO `sewer_size` (`id`,`sewer_size`) VALUES 
 (3,'100'),
 (4,'150'),
 (5,'200'),
 (6,'250'),
 (7,'300'),
 (31,'450');
/*!40000 ALTER TABLE `sewer_size` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`status_master`
--

DROP TABLE IF EXISTS `status_master`;
CREATE TABLE `status_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `status` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`status_master`
--

/*!40000 ALTER TABLE `status_master` DISABLE KEYS */;
INSERT INTO `status_master` (`id`,`status`,`description`) VALUES 
 (1,'DISABLED','GENERAL'),
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
/*!40000 ALTER TABLE `status_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`street_master`
--

DROP TABLE IF EXISTS `street_master`;
CREATE TABLE `street_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `street_name` varchar(255) default NULL,
  `street_code` varchar(255) default NULL,
  `zone_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_streetmaster_zonemaster_id` (`zone_master_id`),
  CONSTRAINT `fk_streetmaster_zonemaster_id` FOREIGN KEY (`zone_master_id`) REFERENCES `zone_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`street_master`
--

/*!40000 ALTER TABLE `street_master` DISABLE KEYS */;
INSERT INTO `street_master` (`id`,`street_name`,`street_code`,`zone_master_id`) VALUES 
 (1,'Street1','StreetCode1',1),
 (2,'Street2','StreetCode2',1),
 (3,'Street3','StreetCode3',2),
 (4,'Street4','StreetCode4',2),
 (5,'Street5','StreetCode5',3),
 (6,'Street6','StreetCode6',3),
 (7,'Street7','StreetCode7',4),
 (8,'Street8','StreetCode8',4);
/*!40000 ALTER TABLE `street_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`sub_desig_category_master`
--

DROP TABLE IF EXISTS `sub_desig_category_master`;
CREATE TABLE `sub_desig_category_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `description` varchar(255) default NULL,
  `alias` varchar(255) default NULL,
  `order_by` int(11) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_subdesigcategorymaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_subdesigcategorymaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`sub_desig_category_master`
--

/*!40000 ALTER TABLE `sub_desig_category_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_desig_category_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`tariff_category_master`
--

DROP TABLE IF EXISTS `tariff_category_master`;
CREATE TABLE `tariff_category_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `tariff_category` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_category_master`
--

/*!40000 ALTER TABLE `tariff_category_master` DISABLE KEYS */;
INSERT INTO `tariff_category_master` (`id`,`tariff_category`) VALUES 
 (1,'Domestic'),
 (2,'Institutional'),
 (3,'Commercial'),
 (4,'Industrial'),
 (5,'Kiosks');
/*!40000 ALTER TABLE `tariff_category_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`tariff_charges`
--

DROP TABLE IF EXISTS `tariff_charges`;
CREATE TABLE `tariff_charges` (
  `id` bigint(20) NOT NULL auto_increment,
  `tariff_desc` varchar(255) NOT NULL,
  `slab_min` int(11) NOT NULL,
  `slab_max` int(11) NOT NULL,
  `rate` float NOT NULL,
  `min_kl` float NOT NULL,
  `min_unmetered_kl` float NOT NULL,
  `tariff_master_id` bigint(20) default NULL,
  `tariff_type_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_tariffcharges_tariffmaster_id` (`tariff_master_id`),
  KEY `fk_tariffcharges_tarifftypemaster_id` (`tariff_type_master_id`),
  CONSTRAINT `fk_tariffcharges_tariffmaster_id` FOREIGN KEY (`tariff_master_id`) REFERENCES `tariff_master` (`id`),
  CONSTRAINT `fk_tariffcharges_tarifftypemaster_id` FOREIGN KEY (`tariff_type_master_id`) REFERENCES `tariff_type_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_charges`
--

/*!40000 ALTER TABLE `tariff_charges` DISABLE KEYS */;
INSERT INTO `tariff_charges` (`id`,`tariff_desc`,`slab_min`,`slab_max`,`rate`,`min_kl`,`min_unmetered_kl`,`tariff_master_id`,`tariff_type_master_id`) VALUES 
 (1,'Domestic - Usage',0,999999,780,10,31,1,1),
 (2,'Domestic - Fixed',0,999999,18000,0,0,1,2),
 (3,'Domestic - Service',0,999999,1800,0,0,1,3),
 (4,'Domestic - Usage',0,999999,780,11,32,2,1),
 (5,'Domestic - Fixed',0,999999,19000,0,0,2,2),
 (6,'Domestic - Service',0,999999,1800,0,0,2,3),
 (7,'Domestic - Usage',0,999999,800,12,33,3,1),
 (8,'Domestic - Fixed',0,999999,19500,0,0,3,2),
 (9,'Domestic - Service',0,999999,1820,0,0,3,3);
/*!40000 ALTER TABLE `tariff_charges` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`tariff_master`
--

DROP TABLE IF EXISTS `tariff_master`;
CREATE TABLE `tariff_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `tariff_name` varchar(255) NOT NULL,
  `valid_from` timestamp NOT NULL on update CURRENT_TIMESTAMP,
  `valid_to` timestamp NOT NULL,
  `active` varchar(255) NOT NULL,
  `tariff_category_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_tariffmaster_tariffcategorymaster_id` (`tariff_category_master_id`),
  CONSTRAINT `fk_tariffmaster_tariffcategorymaster_id` FOREIGN KEY (`tariff_category_master_id`) REFERENCES `tariff_category_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_master`
--

/*!40000 ALTER TABLE `tariff_master` DISABLE KEYS */;
INSERT INTO `tariff_master` (`id`,`tariff_name`,`valid_from`,`valid_to`,`active`,`tariff_category_master_id`) VALUES 
 (1,'Domestic - 2010 to 2015','2010-01-01 00:00:00','2015-12-31 00:00:00','1',1),
 (2,'Domestic - 2016 Jan to Mar','2016-01-01 00:00:00','2016-03-31 00:00:00','1',1),
 (3,'Domestic - 2016 Apr to  June','2016-04-01 00:00:00','2016-06-30 00:00:00','1',1),
 (4,'Domestic - 2016 Jul to Dec','2016-07-01 00:00:00','2016-12-31 00:00:00','1',1);
/*!40000 ALTER TABLE `tariff_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`tariff_type_master`
--

DROP TABLE IF EXISTS `tariff_type_master`;
CREATE TABLE `tariff_type_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `tariff_type` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`tariff_type_master`
--

/*!40000 ALTER TABLE `tariff_type_master` DISABLE KEYS */;
INSERT INTO `tariff_type_master` (`id`,`tariff_type`) VALUES 
 (1,'Usage Charges'),
 (2,'Fixed Charges'),
 (3,'Service Charges');
/*!40000 ALTER TABLE `tariff_type_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`terminal`
--

DROP TABLE IF EXISTS `terminal`;
CREATE TABLE `terminal` (
  `id` bigint(20) NOT NULL auto_increment,
  `amount` float default NULL,
  `status` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  `mr_code` varchar(255) default NULL,
  `sec_code` varchar(255) default NULL,
  `div_code` varchar(255) default NULL,
  `sec_name` varchar(255) default NULL,
  `user_name` varchar(255) default NULL,
  `mobile_no` varchar(255) default NULL,
  `ver` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`terminal`
--

/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
INSERT INTO `terminal` (`id`,`amount`,`status`,`user_id`,`mr_code`,`sec_code`,`div_code`,`sec_name`,`user_name`,`mobile_no`,`ver`) VALUES 
 (1,1111,'Status1','UserId1','MrCode1','SecCode1','DivCode1','SecName1','UserName1','MobileNo1','Ver1');
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`terminal_log`
--

DROP TABLE IF EXISTS `terminal_log`;
CREATE TABLE `terminal_log` (
  `id` bigint(20) NOT NULL auto_increment,
  `amount` float default NULL,
  `last_modified` timestamp NULL default NULL,
  `modified_by` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  `bank_deposit_date` date default NULL,
  `before_update` varchar(255) default NULL,
  `after_update` varchar(255) default NULL,
  `mr_code` varchar(255) default NULL,
  `remark` varchar(255) default NULL,
  `txn_type` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`terminal_log`
--

/*!40000 ALTER TABLE `terminal_log` DISABLE KEYS */;
INSERT INTO `terminal_log` (`id`,`amount`,`last_modified`,`modified_by`,`user_id`,`bank_deposit_date`,`before_update`,`after_update`,`mr_code`,`remark`,`txn_type`) VALUES 
 (2,1222,'2016-03-16 00:00:00','mohib',NULL,'2016-03-16',NULL,NULL,NULL,NULL,NULL),
 (3,12,'2016-03-16 00:00:00','111','111','2016-03-16','11','11','11',NULL,NULL),
 (4,2222,'2016-03-18 00:00:00','ModifiedBy2','UserId2','2016-03-18','BeforeUpdate2','AfterUpdate2','MrCode2','Remark2','TxnType2');
/*!40000 ALTER TABLE `terminal_log` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`transaction_type_master`
--

DROP TABLE IF EXISTS `transaction_type_master`;
CREATE TABLE `transaction_type_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `type_of_txn` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`transaction_type_master`
--

/*!40000 ALTER TABLE `transaction_type_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_type_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`url`
--

DROP TABLE IF EXISTS `url`;
CREATE TABLE `url` (
  `id` bigint(20) NOT NULL auto_increment,
  `url_pattern` varchar(255) NOT NULL,
  `version` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`url`
--

/*!40000 ALTER TABLE `url` DISABLE KEYS */;
INSERT INTO `url` (`id`,`url_pattern`,`version`) VALUES 
 (1,'/modules',1),
 (2,'/applicationTxns',1),
 (3,'/menuItems',1),
 (4,'/urls',1),
 (5,'/menuItem2Urls',1),
 (6,'/module2MenuItems',1),
 (7,'/url2Roles',1),
 (8,'/applicationTxns/all',1),
 (9,'/manageCashPoints',1),
 (10,'/accessLists',1),
 (11,'/billFullDetailss',1),
 (12,'/collDetailss',1),
 (13,'/currentUserss',1),
 (14,'/custDetailss',1),
 (15,'/terminals',1),
 (16,'/terminalLogs',1),
 (17,'/versions',1);
/*!40000 ALTER TABLE `url` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`url2_role`
--

DROP TABLE IF EXISTS `url2_role`;
CREATE TABLE `url2_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `url_id` bigint(20) default NULL,
  `authority_name` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_url2role_url_id` (`url_id`),
  KEY `fk_url2role_authority_name` (`authority_name`),
  CONSTRAINT `fk_url2role_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_url2role_url_id` FOREIGN KEY (`url_id`) REFERENCES `url` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`url2_role`
--

/*!40000 ALTER TABLE `url2_role` DISABLE KEYS */;
INSERT INTO `url2_role` (`id`,`url_id`,`authority_name`) VALUES 
 (1,1,'ROLE_ADMIN'),
 (2,2,'ROLE_ADMIN'),
 (3,2,'ROLE_CUSTOMER'),
 (4,3,'ROLE_ADMIN'),
 (5,4,'ROLE_ADMIN'),
 (6,5,'ROLE_ADMIN'),
 (7,6,'ROLE_ADMIN'),
 (8,7,'ROLE_ADMIN'),
 (9,8,'ROLE_ADMIN'),
 (11,9,'ROLE_CUSTOMER'),
 (12,9,'ROLE_ADMIN'),
 (13,10,'ROLE_ADMIN'),
 (14,11,'ROLE_ADMIN'),
 (15,12,'ROLE_ADMIN'),
 (16,13,'ROLE_ADMIN'),
 (17,14,'ROLE_ADMIN'),
 (18,15,'ROLE_ADMIN'),
 (19,16,'ROLE_ADMIN'),
 (20,17,'ROLE_ADMIN');
/*!40000 ALTER TABLE `url2_role` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`version`
--

DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` bigint(20) NOT NULL auto_increment,
  `version_low` varchar(255) default NULL,
  `version_high` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`version`
--

/*!40000 ALTER TABLE `version` DISABLE KEYS */;
INSERT INTO `version` (`id`,`version_low`,`version_high`) VALUES 
 (1,'1','533'),
 (2,'1','19'),
 (6,'11','33');
/*!40000 ALTER TABLE `version` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`workflow`
--

DROP TABLE IF EXISTS `workflow`;
CREATE TABLE `workflow` (
  `id` bigint(20) NOT NULL auto_increment,
  `stage_id` int(11) default NULL,
  `workflow_master_id` bigint(20) default NULL,
  `relative_from_role_id` bigint(20) default NULL,
  `absolute_from_role_id` bigint(20) default NULL,
  `relationship_type_id` bigint(20) default NULL,
  `relative_to_role_id` bigint(20) default NULL,
  `absolute_to_role_id` bigint(20) default NULL,
  `escalation_relationship_type_id` bigint(20) default NULL,
  `relative_escalation_to_id` bigint(20) default NULL,
  `absolute_escalation_to_id` bigint(20) default NULL,
  `workflow_stage_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow`
--

/*!40000 ALTER TABLE `workflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`workflow_master`
--

DROP TABLE IF EXISTS `workflow_master`;
CREATE TABLE `workflow_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `workflow_name` varchar(255) NOT NULL,
  `to_workflow` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_workflowmaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowmaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_master`
--

/*!40000 ALTER TABLE `workflow_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`workflow_relations`
--

DROP TABLE IF EXISTS `workflow_relations`;
CREATE TABLE `workflow_relations` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_workflowrelations_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowrelations_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_relations`
--

/*!40000 ALTER TABLE `workflow_relations` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_relations` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`workflow_relationships`
--

DROP TABLE IF EXISTS `workflow_relationships`;
CREATE TABLE `workflow_relationships` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_workflowrelationships_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowrelationships_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_relationships`
--

/*!40000 ALTER TABLE `workflow_relationships` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_relationships` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`workflow_stage_master`
--

DROP TABLE IF EXISTS `workflow_stage_master`;
CREATE TABLE `workflow_stage_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `description` varchar(255) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_workflowstagemaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowstagemaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_stage_master`
--

/*!40000 ALTER TABLE `workflow_stage_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_stage_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`workflow_txn_details`
--

DROP TABLE IF EXISTS `workflow_txn_details`;
CREATE TABLE `workflow_txn_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `request_id` int(11) default NULL,
  `reference_number` varchar(255) default NULL,
  `row_number` int(11) default NULL,
  `column_name` varchar(255) default NULL,
  `previous_value` varchar(255) default NULL,
  `new_value` varchar(255) default NULL,
  `ip_address` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `request_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_workflowtxndetails_requestmaster_id` (`request_master_id`),
  CONSTRAINT `fk_workflowtxndetails_requestmaster_id` FOREIGN KEY (`request_master_id`) REFERENCES `request_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_txn_details`
--

/*!40000 ALTER TABLE `workflow_txn_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_txn_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`workflow_type_master`
--

DROP TABLE IF EXISTS `workflow_type_master`;
CREATE TABLE `workflow_type_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `description` varchar(255) default NULL,
  `status_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_workflowtypemaster_statusmaster_id` (`status_master_id`),
  CONSTRAINT `fk_workflowtypemaster_statusmaster_id` FOREIGN KEY (`status_master_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`workflow_type_master`
--

/*!40000 ALTER TABLE `workflow_type_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_type_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`x`
--

DROP TABLE IF EXISTS `x`;
CREATE TABLE `x` (
  `id` bigint(20) NOT NULL default '0',
  `tariff_desc` varchar(255) NOT NULL,
  `slab_min` int(11) NOT NULL,
  `slab_max` int(11) NOT NULL,
  `rate` float NOT NULL,
  `min_kl` float NOT NULL,
  `tariff_master_id` bigint(20) default NULL,
  `tariff_type_master_id` bigint(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`x`
--

/*!40000 ALTER TABLE `x` DISABLE KEYS */;
INSERT INTO `x` (`id`,`tariff_desc`,`slab_min`,`slab_max`,`rate`,`min_kl`,`tariff_master_id`,`tariff_type_master_id`) VALUES 
 (1,'Domestic - Usage',0,999999,780,10,1,1),
 (2,'Domestic - Fixed',0,999999,18000,0,1,2),
 (3,'Domestic - Service',0,999999,1800,0,1,3),
 (4,'Domestic - Usage',0,999999,780,11,2,1),
 (5,'Domestic - Fixed',0,999999,19000,0,2,2),
 (6,'Domestic - Service',0,999999,1800,0,2,3),
 (7,'Domestic - Usage',0,999999,800,12,3,1),
 (8,'Domestic - Fixed',0,999999,19500,0,3,2),
 (9,'Domestic - Service',0,999999,1820,0,3,3);
/*!40000 ALTER TABLE `x` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`zone_master`
--

DROP TABLE IF EXISTS `zone_master`;
CREATE TABLE `zone_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `zone_name` varchar(255) default NULL,
  `zone_code` varchar(255) default NULL,
  `division_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_zonemaster_divisionmaster_id` (`division_master_id`),
  CONSTRAINT `fk_zonemaster_divisionmaster_id` FOREIGN KEY (`division_master_id`) REFERENCES `division_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`zone_master`
--

/*!40000 ALTER TABLE `zone_master` DISABLE KEYS */;
INSERT INTO `zone_master` (`id`,`zone_name`,`zone_code`,`division_master_id`) VALUES 
 (1,'Zone1','ZoneCode1',1),
 (2,'Zone2','ZoneCode2',1),
 (3,'Zone3','ZoneCode3',2),
 (4,'Zone4','ZoneCode4',2),
 (5,'Zone5','ZoneCode5',3),
 (6,'Zone6','ZoneCode6',3);
/*!40000 ALTER TABLE `zone_master` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
