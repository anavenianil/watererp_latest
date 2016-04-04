-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt


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
 (13,'admin13'),
 (14,'dddd');
/*!40000 ALTER TABLE `access_list` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`application_txn`
--

DROP TABLE IF EXISTS `application_txn`;
CREATE TABLE `application_txn` (
  `id` bigint(20) NOT NULL auto_increment,
  `full_name` varchar(255) default NULL,
  `home_or_office_number` bigint(20) default NULL,
  `regional_number` bigint(20) default NULL,
  `fax_number` bigint(20) default NULL,
  `plot_number` varchar(255) default NULL,
  `area` varchar(255) default NULL,
  `street` varchar(255) default NULL,
  `village_executive_office` varchar(255) default NULL,
  `village_executive_office_number` varchar(255) default NULL,
  `po_box` varchar(255) default NULL,
  `requested_date` timestamp NULL default NULL,
  `photo` varchar(255) default NULL,
  `file_number` varchar(255) default NULL,
  `created_date` timestamp NULL default NULL,
  `updated_date` timestamp NULL default NULL,
  `status` int(11) default NULL,
  `category_master_id` bigint(20) default NULL,
  `customer_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_applicationtxn_categorymaster_id` (`category_master_id`),
  KEY `fk_applicationtxn_customer_id` (`customer_id`),
  CONSTRAINT `fk_applicationtxn_categorymaster_id` FOREIGN KEY (`category_master_id`) REFERENCES `category_master` (`id`),
  CONSTRAINT `fk_applicationtxn_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`application_txn`
--

/*!40000 ALTER TABLE `application_txn` DISABLE KEYS */;
INSERT INTO `application_txn` (`id`,`full_name`,`home_or_office_number`,`regional_number`,`fax_number`,`plot_number`,`area`,`street`,`village_executive_office`,`village_executive_office_number`,`po_box`,`requested_date`,`photo`,`file_number`,`created_date`,`updated_date`,`status`,`category_master_id`,`customer_id`) VALUES 
 (38,'mohib khan',9390148141,9390148141,9390148141,'148','977425','jksfgj','jhdkjhfjsh','jhkhfkjh','1234','2016-04-01 10:34:01','/api/download/38_f7deadb6e848534341158fd2dd84b9a7_eclipse error.png',NULL,'2016-04-01 10:34:48','2016-04-01 10:34:48',2,1,NULL);
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
  `meter_fix_date` date default NULL,
  `initial_reading` float default NULL,
  `present_reading` float default NULL,
  `units` float default NULL,
  `water_cess` float default NULL,
  `sewerage_cess` float default NULL,
  `service_charge` float default NULL,
  `meter_service_charge` float default NULL,
  `total_amount` float default NULL,
  `net_payable_amount` float default NULL,
  `telephone_no` varchar(255) default NULL,
  `meter_status` varchar(255) default NULL,
  `met_reader_code` varchar(255) default NULL,
  `bill_flag` varchar(255) default NULL,
  `svr_status` varchar(255) default NULL,
  `terminal_id` varchar(255) default NULL,
  `meter_reader_id` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  `mobile_no` varchar(255) default NULL,
  `notice_no` varchar(255) default NULL,
  `lat` varchar(255) default NULL,
  `longi` varchar(255) default NULL,
  `no_meter_amt` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`bill_details`
--

/*!40000 ALTER TABLE `bill_details` DISABLE KEYS */;
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`) VALUES 
 (1,'613427474','31197684','2013-11-26','155200','','U','201212','201310','2016-04-02',0,0,110000,1650,577.5,110,0,2337.5,2680.06,'1111111111','R','','Y','1','A0000','0000','123','0000000000','','0','0',0),
 (2,'613472557','31197746','2013-11-26','155200','','R','201212','201310','2016-04-02',0,0,110000,1650,577.5,110,0,2337.5,2985.07,'2222222222','U','','Y','1','A0000','0000','123','0000000000','','0','0',0),
 (3,'613472562','31197513','2013-11-26','155200','','R','201212','201310','2016-04-02',0,0,165000,1650,577.5,110,0,2337.5,7174.29,'0000000000','R','','Y','1','A0000','0000','123','0000000000','0000131130172628','0','0',0),
 (4,'613577519','31197514','2013-11-26','155200','','R','201212','201310','2016-04-02',0,0,165000,1650,577.5,110,0,2337.5,31664.3,'0000000563','R','','Y','1','A0000','0000','123','0000000000','0000131130172738','0','0',0);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`) VALUES 
 (5,'617738493','31197668','2013-11-26','155200','','R','201212','201310','2016-04-02',0,0,165000,1650,577.5,110,0,2337.5,2768.88,'0000000002','R','','Y','1','A0000','0000','123','0000000000','','0','0',0),
 (6,'617738918','D000000001','2013-11-30','173005','','L','201211','201310','2016-04-02',0,0,180000,1800,630,120,0,2550,3412.75,'0000000063','L','','','1','A0000','0000','123','0000000000','','0','0',0),
 (7,'617757078','D000000002','2013-11-30','173050','','M','201209','201310','2016-04-02',0,100000,10000,2100,735,140,0,2975,2981.38,'0000000006','U','','','1','A0000','0000','123','0000000000','','0','0',0),
 (8,'617757083','D000000003','2013-11-30','173124','','M','201209','201310','2016-04-02',0,500000,100000,2100,735,140,0,2975,2981.38,'0000000005','M','','','1','A0000','0000','123','0000000000','','0','0',0);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`) VALUES 
 (9,'617757111','D000000004','2013-11-30','173208','','L','201211','201310','2016-04-02',0,0,180000,1800,630,120,0,2550,3095.56,'0000000009','L','','','1','A0000','0000','123','0000000000','','0','0',0),
 (10,'617757186','D000000005','2013-11-30','173246','','L','201211','201310','2016-04-02',0,0,180000,1800,630,120,0,2550,2981.38,'0000000008','L','','','1','A0000','0000','123','0000000000','','0','0',0),
 (11,'617771922','D000000006','2013-11-30','173352','','L','201211','201310','2016-04-02',0,0,180000,1800,630,120,0,2550,2550,'0000000000','L','','','1','A0000','0000','123','0000000000','','0','0',0),
 (12,'617781451','D000000007','2013-11-30','173423','','M','201211','201310','2016-04-02',0,400000,40000,1800,630,120,0,2550,2550,'0000000002','M','','','1','A0000','0000','123','0000000000','','0','0',0);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`) VALUES 
 (13,'617781710','D000000008','2013-11-30','173449','','M','201211','201310','2016-04-02',0,50000,50000,1800,630,120,0,2550,2550,'0000000002','M','','','1','A0000','0000','123','0000000000','','0','0',0);
/*!40000 ALTER TABLE `bill_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`bill_full_details`
--

DROP TABLE IF EXISTS `bill_full_details`;
CREATE TABLE `bill_full_details` (
  `id` bigint(20) NOT NULL auto_increment,
  `can` varchar(255) NOT NULL,
  `divcode` varchar(255) NOT NULL,
  `sec_code` varchar(255) default NULL,
  `sec_name` varchar(255) default NULL,
  `met_reader_code` varchar(255) default NULL,
  `conn_date` date NOT NULL,
  `cons_name` varchar(255) NOT NULL,
  `house_no` varchar(255) NOT NULL,
  `address` varchar(255) default NULL,
  `city` varchar(255) NOT NULL,
  `pin_code` varchar(255) default NULL,
  `category` varchar(255) default NULL,
  `pipe_size` float default NULL,
  `board_meter` varchar(255) default NULL,
  `sewerage` varchar(255) default NULL,
  `meter_no` varchar(255) NOT NULL,
  `prev_bill_type` varchar(255) default NULL,
  `prev_bill_month` date default NULL,
  `prev_avg_kl` float default NULL,
  `met_reading_dt` date NOT NULL,
  `prev_reading` float default NULL,
  `met_reading_mo` date default NULL,
  `met_avg_kl` float default NULL,
  `arrears` float default NULL,
  `reversal_amt` float default NULL,
  `installment` float default NULL,
  `other_charges` float default NULL,
  `surcharge` float default NULL,
  `hrs_surcharge` float default NULL,
  `res_units` bigint(20) default NULL,
  `met_cost_installment` float default NULL,
  `int_on_arrears` float default NULL,
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
  `initial_reading` float default NULL,
  `present_reading` float default NULL,
  `units` float default NULL,
  `water_cess` float default NULL,
  `sewerage_cess` float default NULL,
  `service_charge` float default NULL,
  `meter_service_charge` float default NULL,
  `total_amount` float default NULL,
  `net_payable_amount` float default NULL,
  `telephone_no` varchar(255) default NULL,
  `meter_status` varchar(255) default NULL,
  `bill_flag` varchar(255) default NULL,
  `svr_status` varchar(255) default NULL,
  `terminal_id` varchar(255) default NULL,
  `meter_reader_id` varchar(255) default NULL,
  `user_id` varchar(255) default NULL,
  `mobile_no` varchar(255) default NULL,
  `notice_no` varchar(255) default NULL,
  `lat` varchar(255) default NULL,
  `longi` varchar(255) default NULL,
  `nometer_amt` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`bill_full_details`
--

/*!40000 ALTER TABLE `bill_full_details` DISABLE KEYS */;
INSERT INTO `bill_full_details` (`id`,`can`,`divcode`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`nometer_amt`) VALUES 
 (1,'613427474','15',NULL,NULL,'','2005-04-19','R.PRATIBHA ANNAPURNA','5-109/10/22',NULL,'',NULL,NULL,NULL,NULL,NULL,'05505137',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'31197684','2013-11-26','155200','','U','201212','201310','2016-04-02',0,0,110000,1650,577.5,110,0,2337.5,2680.06,'1111111111','R','Y','1','A0000','0000','123','0000000000','','0','0',NULL),
 (2,'613472557','15',NULL,NULL,'','2005-05-25','N.MANJULATHA','PLOT NO.21',NULL,'',NULL,NULL,NULL,NULL,NULL,'05-1-005551',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'31197746','2013-11-26','155200','','R','201212','201310','2016-04-02',0,0,110000,1650,577.5,110,0,2337.5,2985.07,'2222222222','U','Y','1','A0000','0000','123','0000000000','','0','0',NULL);
INSERT INTO `bill_full_details` (`id`,`can`,`divcode`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`nometer_amt`) VALUES 
 (3,'613472562','15',NULL,NULL,'','2005-05-25','G.MURALI MOHAN','H.NO.33',NULL,'',NULL,NULL,NULL,NULL,NULL,'05-1-004920',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'31197513','2013-11-26','155200','','R','201212','201310','2016-04-02',0,0,165000,1650,577.5,110,0,2337.5,7174.29,'0000000000','R','Y','1','A0000','0000','123','0000000000','0000131130172628','0','0',NULL),
 (4,'613577519','15',NULL,NULL,'','2005-09-21','K.V.V.BALA SUBRAMANA','PLOT NO.19 SY.47',NULL,'',NULL,NULL,NULL,NULL,NULL,'05-1-0017909',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'31197514','2013-11-26','155200','','R','201212','201310','2016-04-02',0,0,165000,1650,577.5,110,0,2337.5,31664.3,'0000000563','R','Y','1','A0000','0000','123','0000000000','0000131130172738','0','0',NULL);
INSERT INTO `bill_full_details` (`id`,`can`,`divcode`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`nometer_amt`) VALUES 
 (5,'617738493','15',NULL,NULL,'','2012-08-11','VANIN','3-615/3',NULL,'',NULL,NULL,NULL,NULL,NULL,'12B40628',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'31197668','2013-11-26','155200','','R','201212','201310','2016-04-02',0,0,165000,1650,577.5,110,0,2337.5,2768.88,'0000000002','R','Y','1','A0000','0000','123','0000000000','','0','0',NULL),
 (6,'617738918','15',NULL,NULL,'','2012-07-14','K RAVI','1-60/30/138/135',NULL,'',NULL,NULL,NULL,NULL,NULL,'12A19467',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'D000000001','2013-11-30','173005','','L','201211','201310','2016-04-02',0,0,180000,1800,630,120,0,2550,3412.75,'0000000063','L','','1','A0000','0000','123','0000000000','','0','0',NULL);
INSERT INTO `bill_full_details` (`id`,`can`,`divcode`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`nometer_amt`) VALUES 
 (7,'617757083','15',NULL,NULL,'','2012-09-15','P HIMA BINDU','1-58/103',NULL,'',NULL,NULL,NULL,NULL,NULL,'11B35430',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'D000000003','2013-11-30','173124','','M','201209','201310','2016-04-02',0,500000,100000,2100,735,140,0,2975,2981.38,'0000000005','M','','1','A0000','0000','123','0000000000','','0','0',NULL),
 (8,'617757111','15',NULL,NULL,'','2012-08-20','JAFFAR BEE','H.NO.2-63/2/A3',NULL,'',NULL,NULL,NULL,NULL,NULL,'12B40792',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'D000000004','2013-11-30','173208','','L','201211','201310','2016-04-02',0,0,180000,1800,630,120,0,2550,3095.56,'0000000009','L','','1','A0000','0000','123','0000000000','','0','0',NULL);
INSERT INTO `bill_full_details` (`id`,`can`,`divcode`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`nometer_amt`) VALUES 
 (9,'617757186','15',NULL,NULL,'','2012-09-26','SMT.SHIVALINGAMMA','2-62/2/A/10',NULL,'',NULL,NULL,NULL,NULL,NULL,'12B36048',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'D000000005','2013-11-30','173246','','L','201211','201310','2016-04-02',0,0,180000,1800,630,120,0,2550,2981.38,'0000000008','L','','1','A0000','0000','123','0000000000','','0','0',NULL),
 (10,'617771922','15',NULL,NULL,'','2012-11-01','SYED AZEEM','1-14',NULL,'',NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'D000000006','2013-11-30','173352','','L','201211','201310','2016-04-02',0,0,180000,1800,630,120,0,2550,2550,'0000000000','L','','1','A0000','0000','123','0000000000','','0','0',NULL);
INSERT INTO `bill_full_details` (`id`,`can`,`divcode`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`nometer_amt`) VALUES 
 (11,'617781451','15',NULL,NULL,'','2012-11-15','SRINIVAS','1-110/A/32',NULL,'',NULL,NULL,NULL,NULL,NULL,'12B53322',NULL,NULL,NULL,'2016-04-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-11-30',NULL,'D000000007','2013-11-30','173423','','M','201211','201310','2016-04-02',0,400000,40000,1800,630,120,0,2550,2550,'0000000002','M','','1','A0000','0000','123','0000000000','','0','0',NULL);
/*!40000 ALTER TABLE `bill_full_details` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`bill_of_material`
--

DROP TABLE IF EXISTS `bill_of_material`;
CREATE TABLE `bill_of_material` (
  `id` bigint(20) NOT NULL auto_increment,
  `amount` double default NULL,
  `bank_name` varchar(255) default NULL,
  `branch_name` varchar(255) default NULL,
  `check_or_dd_date` date default NULL,
  `check_or_dd_no` varchar(255) default NULL,
  `bill_date` date default NULL,
  `application_txn_id` bigint(20) default NULL,
  `payment_types_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_billofmaterial_applicationtxn_id` (`application_txn_id`),
  KEY `fk_billofmaterial_paymenttypes_id` (`payment_types_id`),
  CONSTRAINT `fk_billofmaterial_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_billofmaterial_paymenttypes_id` FOREIGN KEY (`payment_types_id`) REFERENCES `payment_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`bill_of_material`
--

/*!40000 ALTER TABLE `bill_of_material` DISABLE KEYS */;
INSERT INTO `bill_of_material` (`id`,`amount`,`bank_name`,`branch_name`,`check_or_dd_date`,`check_or_dd_no`,`bill_date`,`application_txn_id`,`payment_types_id`) VALUES 
 (1,346347,'sbi','kphb','2016-04-01','544656','2016-04-01',38,1),
 (2,654654,'icici','kphb','2016-04-01','65456','2016-04-01',38,2),
 (3,116565,'ksdjs','hksdjhksljhd','2016-04-01','64554','2016-04-01',38,3),
 (4,34634,'34sdg','gsga','2016-04-01','46346','2016-04-01',38,1);
/*!40000 ALTER TABLE `bill_of_material` ENABLE KEYS */;


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
 (1,'Domestic'),
 (2,'Institutional'),
 (3,'Commercial'),
 (4,'Industrial'),
 (5,'Kiosks');
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
-- Table structure for table `watererp`.`complaint_type_master`
--

DROP TABLE IF EXISTS `complaint_type_master`;
CREATE TABLE `complaint_type_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `complaint_type` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`complaint_type_master`
--

/*!40000 ALTER TABLE `complaint_type_master` DISABLE KEYS */;
INSERT INTO `complaint_type_master` (`id`,`complaint_type`) VALUES 
 (1,'Incorrect Bill'),
 (2,'Water Leakage'),
 (3,'Service Unavailability');
/*!40000 ALTER TABLE `complaint_type_master` ENABLE KEYS */;


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
 (2,'EWURA','1',NULL),
 (3,'SEWERAGE','10',NULL);
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
  `pipe_size` float default NULL,
  `board_meter` varchar(255) default NULL,
  `sewerage` varchar(255) default NULL,
  `meter_no` varchar(255) default NULL,
  `prev_bill_type` varchar(255) default NULL,
  `prev_bill_month` date default NULL,
  `prev_avg_kl` float default NULL,
  `met_reading_dt` date default NULL,
  `prev_reading` float default NULL,
  `met_reading_mo` date default NULL,
  `met_avg_kl` float default NULL,
  `arrears` float default NULL,
  `reversal_amt` float default NULL,
  `installment` float default NULL,
  `other_charges` float default NULL,
  `surcharge` float default NULL,
  `hrs_surcharge` varchar(255) default NULL,
  `res_units` bigint(20) default NULL,
  `met_cost_installment` float default NULL,
  `int_on_arrears` float default NULL,
  `last_pymt_dt` date default NULL,
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
 (1,'613427474','15','0614','MADHAPUR','0000','2005-04-19','R.PRATIBHA ANNAPURNA','5-109/10/22','RADHE NAGAR COLONY,GACHIBOWLI','','500032','D',0.5,'F','T','05505137','U','2013-10-01',10,'2016-03-13',0,NULL,10,680.06,0,0,0,0,'F',0,0,5.06,'2013-11-30',2000,'0000000000','1','1','0','1','0','0'),
 (2,'613472557','15','0614','MADHAPUR','0000','2005-05-25','N.MANJULATHA','PLOT NO.21','RADHE NAGAR COLONY,H.S.DARGA','','500032','D',0.5,'F','T','05-1-005551','R','2013-10-01',10,'2016-03-13',0,NULL,10,2785.07,0,0,0,0,'F',0,0,9.57,'2013-11-30',200,'0000000000','0','0','0','1','0','0'),
 (3,'613472562','15','0614','MADHAPUR','0000','2005-05-25','G.MURALI MOHAN','H.NO.33','RADHE NAGAR COLONY,H.S.DARGA.','','500032','D',0.5,'F','T','05-1-004920','R','2013-10-01',15,'2016-03-13',0,NULL,0,6924.29,0,0,0,0,'F',0,0,61.94,'2013-11-30',250,'0000000000','0','0','0','1','0','0');
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (4,'613577519','15','0614','MADHAPUR','0000','2005-09-21','K.V.V.BALA SUBRAMANA','PLOT NO.19 SY.47','RADHA NAGAR RAIDURGA,MADHAPUR','','500032','D',0.5,'F','T','05-1-0017909','R','2013-10-01',15,'2016-03-13',0,NULL,6,31417.3,0,0,0,0,'F',0,0,329.73,'2013-11-30',247,'0000000000','0','0','0','1','0','0'),
 (5,'617738493','15','0614','MADHAPUR','0000','2012-08-11','VANIN','3-615/3','PLOT NO. 195','','','D',0.5,'F','T','12B40628','R','2013-10-01',15,'2016-03-13',0,NULL,15,2508.88,0,0,0,0,'F',1,0,6.38,'2013-11-30',260,'0000000000','0','0','0','1','0','0'),
 (6,'617738918','15','0614','MADHAPUR','0000','2012-07-14','K RAVI','1-60/30/138/135','ANJAIAH NAGAR,GACHIBOWLI','','','D',0.5,'F','T','12A19467','L','2013-10-01',15,'2016-03-13',0,'2012-06-01',0,3032.75,3400,0,0,0,'F',1,0,12.75,'2013-11-30',380,'0000000000','0','0','0','1','0','0');
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (7,'617757078','15','0614','MADHAPUR','0000','2012-09-10','MALOTH.LACHIRAM NAYAK','1-57/31','RAJEEV NAGAR,GACHIBOWLI','','','D',0.5,'F','T','12B92564','M','2013-10-01',15,'2016-03-13',10000,'2013-10-01',0,2481.38,0,0,0,0,'F',1,0,6.38,'2013-11-30',500,'0000000000','0','0','0','1','0','0'),
 (8,'617757083','15','0614','MADHAPUR','0000','2012-09-15','P HIMA BINDU','1-58/103','RAJEEV NAGAR,GACHIBOWLI','','','D',0.5,'F','T','11B35430','M','2013-10-01',15,'2016-03-13',100000,'2013-10-01',0,2581.38,0,0,0,0,'F',1,0,6.38,'2013-11-30',400,'0000000000','0','0','0','1','0','0'),
 (9,'617757111','15','0614','MADHAPUR','0000','2012-08-20','JAFFAR BEE','H.NO.2-63/2/A3','CHINNA ANJAIAH NAGAR,GACHIBOWL','','','D',0.5,'F','T','12B40792','L','2013-10-01',15,'2016-03-13',0,'2012-07-01',0,2995.56,3187.5,0,0,0,'F',1,0,8.06,'2013-11-30',100,'0000000000','0','0','0','1','0','0');
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (10,'617757186','15','0614','MADHAPUR','0000','2012-09-26','SMT.SHIVALINGAMMA','2-62/2/A/10','GACHIBOWLI VILLAGE','','','D',0.5,'F','T','12B36048','L','2013-10-01',15,'2016-03-13',0,'2012-08-01',0,2746.38,2975,0,0,0,'F',1,0,6.38,'2013-11-30',235,'0000000000','0','0','0','1','0','0'),
 (11,'617771922','15','0614','MADHAPUR','0000','2012-11-01','SYED AZEEM','1-14','OLD GACHIBOWLI','','','D',0.5,'F','T','','L','2013-10-01',15,'2016-03-13',0,NULL,0,2100,2550,0,0,0,'F',1,0,0,'2013-11-30',450,'0000000000','0','0','0','1','0','0'),
 (12,'617781451','15','0614','MADHAPUR','0000','2012-11-15','SRINIVAS','1-110/A/32','KONDAPUR W/S COLONY,KONDAPUR','','','D',0.5,'F','T','12B53322','M','2013-10-01',15,'2016-03-13',400000,'2013-10-01',0,1750,0,0,0,0,'F',1,0,0,'2013-11-30',800,'0000000000','0','0','0','1','0','0');
INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category`,`pipe_size`,`board_meter`,`sewerage`,`meter_no`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`) VALUES 
 (13,'617781710','15','0614','MADHAPUR','0000','2012-11-10','K.DEVIDAS','3-618/2,P.NO.540/B','SCB NAGAR,NEW HAFEEZPET','','','D',0.5,'F','T','12B53305','M','2013-10-01',15,'2016-03-13',500000,'2013-10-01',0,2338,0,0,0,0,'F',2,0,0,'2013-11-30',212,'0000000000','0','0','0','1','0','0');
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
  `file_number` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_customer_filenumber_id` (`file_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`customer_complaints`
--

DROP TABLE IF EXISTS `customer_complaints`;
CREATE TABLE `customer_complaints` (
  `id` bigint(20) NOT NULL auto_increment,
  `remarks` varchar(255) default NULL,
  `relevant_doc` varchar(255) default NULL,
  `complaint_by` varchar(255) default NULL,
  `complaint_date` date default NULL,
  `application_txn_id` bigint(20) default NULL,
  `complaint_type_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_customercomplaints_applicationtxn_id` (`application_txn_id`),
  KEY `fk_customercomplaints_complainttypemaster_id` (`complaint_type_master_id`),
  CONSTRAINT `fk_customercomplaints_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_customercomplaints_complainttypemaster_id` FOREIGN KEY (`complaint_type_master_id`) REFERENCES `complaint_type_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`customer_complaints`
--

/*!40000 ALTER TABLE `customer_complaints` DISABLE KEYS */;
INSERT INTO `customer_complaints` (`id`,`remarks`,`relevant_doc`,`complaint_by`,`complaint_date`,`application_txn_id`,`complaint_type_master_id`) VALUES 
 (1,'Remarks 38','Relevant Document 38','Complaint By 38','2016-04-01',38,1),
 (5,NULL,'/api/download/5_31d55b40f38c9900968113df05747950_MMG.jpg',NULL,'2016-04-02',38,1),
 (6,NULL,'/api/download/6_497b0f39cbf26723f6fe5078eb05a76a_MMG.jpg',NULL,'2016-04-02',38,NULL),
 (7,NULL,'/api/download/7_fa003f0271e1a6596245bc2b9bded11c_MMG.jpg',NULL,'2016-04-02',38,1),
 (8,NULL,'/api/download/8_84e6e82ea51bbfc848320635fa6c4c7a_MMG.jpg',NULL,'2016-04-02',38,2),
 (10,'Remarks 38','','Complaint By 38','2016-04-02',38,2),
 (11,'Current bill not printed','/api/download/11_764006141546474bfc289f73777dbce1_MMG.jpg','me','2016-04-04',38,1);
/*!40000 ALTER TABLE `customer_complaints` ENABLE KEYS */;


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
 ('20160229071846','jhipster','classpath:config/liquibase/changelog/20160229071846_added_entity_Customer.xml','2016-02-29 14:06:35',12,'EXECUTED','7:a21f5fbe73cee6f0762e2f39af77124a','createTable, dropDefaultValue, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229072353','jhipster','classpath:config/liquibase/changelog/20160229072353_added_entity_ManageCashPoint.xml','2016-02-29 14:06:39',13,'EXECUTED','7:063f36a456a8a13982a11343a8a8dddd','createTable, dropDefaultValue, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL),
 ('20160229073210','jhipster','classpath:config/liquibase/changelog/20160229073210_added_entity_StatusMaster.xml','2016-02-29 14:06:40',14,'EXECUTED','7:37a09476506400cb3134316685ddbca6','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229073547','jhipster','classpath:config/liquibase/changelog/20160229073547_added_entity_DesignationMaster.xml','2016-02-29 14:06:41',15,'EXECUTED','7:127eba27413f9aa0a5e653084544e03a','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229073648','jhipster','classpath:config/liquibase/changelog/20160229073648_added_entity_FeasibilityStatus.xml','2016-02-29 14:06:41',16,'EXECUTED','7:190180bbcfa1c279424b098aae93a57c','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229074219','jhipster','classpath:config/liquibase/changelog/20160229074219_added_entity_ReAllotment.xml','2016-02-29 14:06:46',18,'EXECUTED','7:eb523405877dcf8d7dd83dc9199b8ea3','createTable, addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229084044','jhipster','classpath:config/liquibase/changelog/20160229084044_added_entity_ConfigurationDetails.xml','2016-02-29 15:03:13',20,'EXECUTED','7:839bd04dd36ec11b9c3f6f6db979cc63','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160229085139','jhipster','classpath:config/liquibase/changelog/20160229085139_added_entity_DesigCategoryMaster.xml','2016-02-29 15:03:14',21,'EXECUTED','7:fdd173d8f6e7a9bc336c5a494f57f052','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229085352','jhipster','classpath:config/liquibase/changelog/20160229085352_added_entity_SubDesigCategoryMaster.xml','2016-02-29 15:03:15',22,'EXECUTED','7:c1f7021bd38767b73129e94983eb54cf','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229085805','jhipster','classpath:config/liquibase/changelog/20160229085805_added_entity_GroupMaster.xml','2016-02-29 15:03:16',23,'EXECUTED','7:4c67fc449b40cce038c464ecc57d55d0','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229090028','jhipster','classpath:config/liquibase/changelog/20160229090028_added_entity_OrgHierarchy.xml','2016-02-29 15:03:17',24,'EXECUTED','7:fd82c8c6649f4fc468f75eda51211b68','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229090217','jhipster','classpath:config/liquibase/changelog/20160229090217_added_entity_DesignationMappings.xml','2016-02-29 15:03:20',25,'EXECUTED','7:a32248161fb67e22dcb376886d1f8d32','createTable, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229090442','jhipster','classpath:config/liquibase/changelog/20160229090442_added_entity_DepartmentsHierarchy.xml','2016-02-29 15:03:21',26,'EXECUTED','7:f61491f7db54a4caa099623241809053','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229090611','jhipster','classpath:config/liquibase/changelog/20160229090611_added_entity_DepartmentTypeMaster.xml','2016-02-29 15:03:23',27,'EXECUTED','7:877befbbede5aa2ae023cef1c740eda5','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229091226','jhipster','classpath:config/liquibase/changelog/20160229091226_added_entity_DepartmentsMaster.xml','2016-02-29 15:03:25',28,'EXECUTED','7:269c35f7c51323070df2bb00cb146173','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229091411','jhipster','classpath:config/liquibase/changelog/20160229091411_added_entity_OrgRoleHierarchy.xml','2016-02-29 15:03:26',29,'EXECUTED','7:2917d963a991b8d25ed698800fb6d36c','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229092048','jhipster','classpath:config/liquibase/changelog/20160229092048_added_entity_OrgRoleInstance.xml','2016-02-29 15:03:29',30,'EXECUTED','7:775abb35fbdc8d21cefae3e86deeb7e2','createTable, dropDefaultValue (x3), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229092244','jhipster','classpath:config/liquibase/changelog/20160229092244_added_entity_OrgRolesMaster.xml','2016-02-29 15:03:30',31,'EXECUTED','7:88f6b41a493861a105157b995199f4a1','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229092636','jhipster','classpath:config/liquibase/changelog/20160229092636_added_entity_WorkflowTypeMaster.xml','2016-02-29 15:03:31',32,'EXECUTED','7:c51656b7eeb13d3c2e615135ac3b1361','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229093926','jhipster','classpath:config/liquibase/changelog/20160229093926_added_entity_EmpMaster.xml','2016-02-29 15:12:23',33,'EXECUTED','7:3252e1c25b98fa713f2887568665564d','createTable, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229095205','jhipster','classpath:config/liquibase/changelog/20160229095205_added_entity_EmpRoleMapping.xml','2016-02-29 15:49:21',34,'EXECUTED','7:fc3da7a2667930eac07790ad970de8c1','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229095400','jhipster','classpath:config/liquibase/changelog/20160229095400_added_entity_WorkflowMaster.xml','2016-02-29 15:49:22',35,'EXECUTED','7:9e74b57e21636b130a51342ef513fa9b','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229095700','jhipster','classpath:config/liquibase/changelog/20160229095700_added_entity_RequestMaster.xml','2016-02-29 15:49:23',36,'EXECUTED','7:4ea16d61dc9c1631995ebcaa24172261','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229095841','jhipster','classpath:config/liquibase/changelog/20160229095841_added_entity_WorkflowStageMaster.xml','2016-02-29 15:49:24',37,'EXECUTED','7:b232f3ff1a94a47509fc8dcf9d99f1d4','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229095927','jhipster','classpath:config/liquibase/changelog/20160229095927_added_entity_WorkflowRelations.xml','2016-02-29 15:49:25',38,'EXECUTED','7:8f71bbc6064b9fe8aa41799e214bbc10','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229100042','jhipster','classpath:config/liquibase/changelog/20160229100042_added_entity_WorkflowRelationships.xml','2016-02-29 15:49:26',39,'EXECUTED','7:af9db222b3e3f53e4df472506e8d6995','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229100254','jhipster','classpath:config/liquibase/changelog/20160229100254_added_entity_ReqDesigWorkflowMapping.xml','2016-02-29 15:49:29',40,'EXECUTED','7:1e7a0c353f3ba594bbb665b568452d48','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229100431','jhipster','classpath:config/liquibase/changelog/20160229100431_added_entity_ReqOrgWorkflowMapping.xml','2016-02-29 15:49:32',41,'EXECUTED','7:f3cade643b99f3ca2eaf4c44cf764f9c','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL),
 ('20160229100821','jhipster','classpath:config/liquibase/changelog/20160229100821_added_entity_RoleWorkflowMapping.xml','2016-02-29 15:49:35',42,'EXECUTED','7:25c22c3ba0d09c89818f178cb23b9aea','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160229100952','jhipster','classpath:config/liquibase/changelog/20160229100952_added_entity_RequestWorkflowMapping.xml','2016-02-29 15:49:37',43,'EXECUTED','7:e2e3affdf6fd6213512081f5a7c52acd','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160229101231','jhipster','classpath:config/liquibase/changelog/20160229101231_added_entity_WorkflowTxnDetails.xml','2016-02-29 15:49:38',44,'EXECUTED','7:ffd53ef669a1b6a5fdacfdfc2c63c8d4','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229111312','jhipster','classpath:config/liquibase/changelog/20160229111312_added_entity_Workflow.xml','2016-02-29 16:56:15',45,'EXECUTED','7:aa3bbe903f8a96b777e321dc647773ab','createTable, addForeignKeyConstraint (x10)','',NULL,'3.4.2',NULL,NULL),
 ('20160229111821','jhipster','classpath:config/liquibase/changelog/20160229111821_added_entity_RequestWorkflowHistory.xml','2016-02-29 16:56:21',46,'EXECUTED','7:df037b52eebabe97bca6601174750057','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x7)','',NULL,'3.4.2',NULL,NULL),
 ('20160229074020','jhipster','classpath:config/liquibase/changelog/20160229074020_added_entity_ApprovalDetails.xml','2016-03-08 14:21:51',47,'EXECUTED','7:37e6360d74c522cda74763685e8f3e6e','createTable, dropDefaultValue, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160309103544','jhipster','classpath:config/liquibase/changelog/20160309103544_added_entity_Module.xml','2016-03-09 16:14:40',48,'EXECUTED','7:ae553e293db4d292b225cd36fb0ba43c','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160309103701','jhipster','classpath:config/liquibase/changelog/20160309103701_added_entity_MenuItem.xml','2016-03-09 16:14:41',49,'EXECUTED','7:bd9888add9c5af34bcdca93e50add4d1','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),
 ('20160309103829','jhipster','classpath:config/liquibase/changelog/20160309103829_added_entity_Url.xml','2016-03-09 16:14:41',50,'EXECUTED','7:1889bdedffb7df6fe11f9bc33d0efa35','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160309104001','jhipster','classpath:config/liquibase/changelog/20160309104001_added_entity_MenuItem2Url.xml','2016-03-09 16:14:43',51,'EXECUTED','7:902843a076e3bb258db90badb9e1dc9a','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160309104200','jhipster','classpath:config/liquibase/changelog/20160309104200_added_entity_Module2MenuItem.xml','2016-03-09 16:14:45',52,'EXECUTED','7:9824330082db5729d7b5d6bf940348df','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160309105304','jhipster','classpath:config/liquibase/changelog/20160309105304_added_entity_Url2Role.xml','2016-03-09 16:34:47',53,'EXECUTED','7:10331a2dfcdaf6902d65c17510f31bc4','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160311094234','jhipster','classpath:config/liquibase/changelog/20160311094234_added_entity_SchemeMaster.xml','2016-03-11 15:21:26',54,'EXECUTED','7:af80987658cadbe9d18ef393fd5559c7','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094550','jhipster','classpath:config/liquibase/changelog/20160311094550_added_entity_MakeOfPipe.xml','2016-03-11 15:21:26',56,'EXECUTED','7:0aae809bc0a38e5aa294a887f735acb8','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094820','jhipster','classpath:config/liquibase/changelog/20160311094820_added_entity_MainWaterSize.xml','2016-03-11 15:21:26',57,'EXECUTED','7:0e853a5e0c7721503d0203d88333bd91','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094904','jhipster','classpath:config/liquibase/changelog/20160311094904_added_entity_MainSewerageSize.xml','2016-03-11 15:21:27',58,'EXECUTED','7:03b9b58c058199566e8d55fd5635b97d','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160311095014','jhipster','classpath:config/liquibase/changelog/20160311095014_added_entity_DocketCode.xml','2016-03-11 15:21:27',59,'EXECUTED','7:9b3b9b3180ffc95d73eadf3d1294c5e0','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311100455','jhipster','classpath:config/liquibase/changelog/20160311100455_added_entity_FeasibilityStudy.xml','2016-03-11 15:35:47',60,'EXECUTED','7:02ff1a8ab9783cbd5eaec740ffa6d9cc','createTable, addForeignKeyConstraint (x12)','',NULL,'3.4.2',NULL,NULL),
 ('20160317071301','jhipster','classpath:config/liquibase/changelog/20160317071301_added_entity_ItemDetails.xml','2016-03-17 13:06:23',62,'EXECUTED','7:8d0c73c28056a5186d39bbed2ac7f38f','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160317100956','jhipster','classpath:config/liquibase/changelog/20160317100956_added_entity_ItemRequired.xml','2016-03-17 15:41:55',63,'EXECUTED','7:94df0c0621fc50b07f19c839c8c3e99d','createTable, addForeignKeyConstraint (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160324101153','jhipster','classpath:config/liquibase/changelog/20160324101153_added_entity_DivisionMaster.xml','2016-03-24 15:50:09',65,'EXECUTED','7:49f67b19188786accf52558a610c39c8','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160324101330','jhipster','classpath:config/liquibase/changelog/20160324101330_added_entity_ZoneMaster.xml','2016-03-24 15:50:10',66,'EXECUTED','7:7855073214c1c3e194f1b4125d049f25','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160324101502','jhipster','classpath:config/liquibase/changelog/20160324101502_added_entity_StreetMaster.xml','2016-03-24 15:50:11',67,'EXECUTED','7:7834259d37eff0b5a2feb5555c398f82','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160229075018','jhipster','classpath:config/liquibase/changelog/20160229075018_added_entity_ApplicationTxn.xml','2016-03-24 17:07:52',68,'EXECUTED','7:92c7f965d32eb43ea3431e8ab91bb501','createTable, dropDefaultValue (x3), addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160324105452','jhipster','classpath:config/liquibase/changelog/20160324105452_added_entity_FeasibilityStudy.xml','2016-03-28 13:17:41',71,'EXECUTED','7:aa06f800520a2b8bfef4654df0572d3d','createTable, dropDefaultValue (x6), addForeignKeyConstraint (x9)','',NULL,'3.4.2',NULL,NULL),
 ('20160328051857','jhipster','classpath:config/liquibase/changelog/20160328051857_added_entity_FileUploadMaster.xml','2016-03-28 18:56:06',72,'EXECUTED','7:9fd8cdb67e4d8cc76478621ecad2012b','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160328131639','jhipster','classpath:config/liquibase/changelog/20160328131639_added_entity_FileUploadMaster.xml','2016-03-28 18:57:23',73,'EXECUTED','7:03be8b2eae9799aec199f76ed49d55a1','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160330052554','jhipster','classpath:config/liquibase/changelog/20160330052554_added_entity_ItemCategoryMaster.xml','2016-03-30 11:28:44',74,'EXECUTED','7:360b2d16421c3564bb94f0775648580d','createTable, dropDefaultValue (x3)','',NULL,'3.4.2',NULL,NULL),
 ('20160330052808','jhipster','classpath:config/liquibase/changelog/20160330052808_added_entity_ItemSubCategoryMaster.xml','2016-03-30 11:28:45',75,'EXECUTED','7:77c40a173637ee134f58cd079c67aead','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160330053134','jhipster','classpath:config/liquibase/changelog/20160330053134_added_entity_ItemCodeMaster.xml','2016-03-30 11:28:47',76,'EXECUTED','7:a4e7f1b1ca5ebdb94b5d29dd9402b337','createTable, dropDefaultValue (x2), addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330053533','jhipster','classpath:config/liquibase/changelog/20160330053533_added_entity_ItemCompanyMaster.xml','2016-03-30 11:28:47',77,'EXECUTED','7:a250630d5ce589a445e309d152c4d935','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160330054006','jhipster','classpath:config/liquibase/changelog/20160330054006_added_entity_ItemSubCodeMaster.xml','2016-03-30 11:28:48',78,'EXECUTED','7:b3fbded42d2b493c86ffbd3649e87ceb','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330054928','jhipster','classpath:config/liquibase/changelog/20160330054928_added_entity_MaterialMaster.xml','2016-03-30 11:28:48',79,'EXECUTED','7:983fd1500db22988ac26254293296036','createTable, dropDefaultValue (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160330055745','jhipster','classpath:config/liquibase/changelog/20160330055745_added_entity_SibEntry.xml','2016-03-30 11:28:49',80,'EXECUTED','7:da7d47c69cd368ff14809fae56104b33','createTable, dropDefaultValue (x9)','',NULL,'3.4.2',NULL,NULL),
 ('20160315083447','jhipster','classpath:config/liquibase/changelog/20160315083447_added_entity_Proceedings.xml','2016-03-30 15:44:38',81,'EXECUTED','7:dd7e9c257108ab49866b8ad0020e73bb','createTable, addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160330092113','jhipster','classpath:config/liquibase/changelog/20160330092113_added_entity_ItemRequired.xml','2016-03-30 15:44:41',82,'EXECUTED','7:f7c7a5118cd1b8dad31bfb61a4aecfff','createTable, addForeignKeyConstraint (x4)','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160330095504','jhipster','classpath:config/liquibase/changelog/20160330095504_added_entity_Proceedings.xml','2016-03-30 15:58:29',84,'EXECUTED','7:5a186495ca7843f3c9eafd0f1e6025b6','createTable, addForeignKeyConstraint (x5)','',NULL,'3.4.2',NULL,NULL),
 ('20160330093457','jhipster','classpath:config/liquibase/changelog/20160330093457_added_entity_PercentageMaster.xml','2016-03-30 19:02:55',85,'EXECUTED','7:939ffd4e62fb70f2288fd5eb2055b778','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160401064028','jhipster','classpath:config/liquibase/changelog/20160401064028_added_entity_BillOfMaterial.xml','2016-04-01 13:00:10',86,'EXECUTED','7:4bc9d2b3db96ca1d16d623ad6adb5f8a','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffCategoryMaster.xml','2016-04-01 15:34:53',87,'EXECUTED','7:c01f3faca05eff0386e65260cc81849e','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffTypeMaster.xml','2016-04-01 15:34:53',88,'EXECUTED','7:2fdf8de5ece58a24c9842690d2599317','createTable','',NULL,'3.4.2',NULL,NULL);
INSERT INTO `databasechangelog` (`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) VALUES 
 ('20160311094431','jhipster','classpath:config/liquibase/changelog/20160311094431_added_entity_TariffMaster.xml','2016-04-01 15:34:54',89,'EXECUTED','7:de67571b345cd24609fcbc1d6d8e12ab','createTable, dropDefaultValue (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),
 ('20160401094431','jhipster','classpath:config/liquibase/changelog/20160401094431_added_entity_TariffCharges.xml','2016-04-01 15:34:56',90,'EXECUTED','7:84a4098f6e6489da14e4b14c2d948a2c','createTable, addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),
 ('20160314104149','jhipster','config/liquibase/changelog/20160314104149_added_entity_BillDetails.xml','2016-04-02 14:13:32',91,'EXECUTED','7:9eea8eb99e0765bf871ce4a2ba6dedc1','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160314132343','jhipster','config/liquibase/changelog/20160314132343_added_entity_CustDetails.xml','2016-04-02 14:13:32',92,'EXECUTED','7:88f8b20843599aca936b8ae4e1ac1e34','createTable','',NULL,'3.4.2',NULL,NULL),
 ('20160314104149','jhipster','config/liquibase/changelog/20160314104149_added_entity_BillFullDetails.xml','2016-04-02 14:13:32',93,'EXECUTED','7:1d7e8751c1e0e86930e4f6e3c3f39010','createTable','',NULL,'3.4.2',NULL,NULL);
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
INSERT INTO `docket_code` (`id`,`code`) VALUES 
 (1,'Docket code 1'),
 (2,'Docket code 2');
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
INSERT INTO `emp_master` (`id`,`user_id`,`office_id_id`,`designation_master_id`,`directorate_id_id`,`status_master_id`) VALUES 
 (1,6,1,NULL,1,2),
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
INSERT INTO `emp_role_mapping` (`id`,`internal_division`,`internal_role`,`creation_date`,`last_modified_date`,`parent_role_id`,`user_id`,`parent_user_id`,`org_role_instance_id`,`status_master_id`) VALUES 
 (1,'','','2016-03-18 00:00:00','2016-03-18 00:00:00',0,6,NULL,1,2),
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
 (18,NULL,NULL,NULL,NULL,NULL,23,NULL,18,2);
INSERT INTO `emp_role_mapping` (`id`,`internal_division`,`internal_role`,`creation_date`,`last_modified_date`,`parent_role_id`,`user_id`,`parent_user_id`,`org_role_instance_id`,`status_master_id`) VALUES 
 (19,NULL,NULL,NULL,NULL,NULL,24,NULL,19,2),
 (20,NULL,NULL,NULL,NULL,NULL,25,NULL,20,2),
 (22,NULL,NULL,NULL,NULL,NULL,26,NULL,21,2),
 (23,NULL,NULL,NULL,NULL,NULL,27,NULL,22,2),
 (25,NULL,NULL,NULL,NULL,NULL,28,NULL,23,2),
 (26,'',NULL,'2016-03-18 00:00:00',NULL,NULL,29,NULL,24,2),
 (27,NULL,NULL,NULL,NULL,NULL,5,NULL,25,2);
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
  `created_date` timestamp NULL default NULL,
  `modified_date` timestamp NULL default NULL,
  `prepared_date` timestamp NULL default NULL,
  `zonal_head_approval_date` timestamp NULL default NULL,
  `dept_head_inspected_date` timestamp NULL default NULL,
  `operation_mangrapprove_date` timestamp NULL default NULL,
  `status` int(11) default NULL,
  `division_master_id` bigint(20) default NULL,
  `zone_master_id` bigint(20) default NULL,
  `street_master_id` bigint(20) default NULL,
  `application_txn_id` bigint(20) default NULL,
  `prepared_by_id` bigint(20) default NULL,
  `approved_by_zonal_head_id` bigint(20) default NULL,
  `inspection_by_department_head_id` bigint(20) default NULL,
  `approved_by_operation_manager_id` bigint(20) default NULL,
  `category_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`feasibility_study`
--

/*!40000 ALTER TABLE `feasibility_study` DISABLE KEYS */;
INSERT INTO `feasibility_study` (`id`,`created_date`,`modified_date`,`prepared_date`,`zonal_head_approval_date`,`dept_head_inspected_date`,`operation_mangrapprove_date`,`status`,`division_master_id`,`zone_master_id`,`street_master_id`,`application_txn_id`,`prepared_by_id`,`approved_by_zonal_head_id`,`inspection_by_department_head_id`,`approved_by_operation_manager_id`,`category_master_id`) VALUES 
 (2,'2016-04-01 11:37:29','2016-04-01 11:37:29','2016-04-01 00:00:00','2016-04-02 00:00:00','2016-04-03 00:00:00','2016-04-04 00:00:00',0,1,2,4,38,13,14,15,16,1);
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
-- Table structure for table `watererp`.`file_upload_master`
--

DROP TABLE IF EXISTS `file_upload_master`;
CREATE TABLE `file_upload_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `photo` blob,
  `photo_content_type` varchar(50) NOT NULL,
  `text_file` longtext,
  `binary_file` blob,
  `binary_file_content_type` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`file_upload_master`
--

/*!40000 ALTER TABLE `file_upload_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_upload_master` ENABLE KEYS */;


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
-- Table structure for table `watererp`.`item_category_master`
--

DROP TABLE IF EXISTS `item_category_master`;
CREATE TABLE `item_category_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `status` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `category_code` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_category_master`
--

/*!40000 ALTER TABLE `item_category_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_category_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`item_code_master`
--

DROP TABLE IF EXISTS `item_code_master`;
CREATE TABLE `item_code_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `item_code` varchar(255) default NULL,
  `item_name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `status` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `item_category_master_id` bigint(20) default NULL,
  `item_sub_category_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_itemcodemaster_itemcategorymaster_id` (`item_category_master_id`),
  KEY `fk_itemcodemaster_itemsubcategorymaster_id` (`item_sub_category_master_id`),
  CONSTRAINT `fk_itemcodemaster_itemcategorymaster_id` FOREIGN KEY (`item_category_master_id`) REFERENCES `item_category_master` (`id`),
  CONSTRAINT `fk_itemcodemaster_itemsubcategorymaster_id` FOREIGN KEY (`item_sub_category_master_id`) REFERENCES `item_sub_category_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_code_master`
--

/*!40000 ALTER TABLE `item_code_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_code_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`item_company_master`
--

DROP TABLE IF EXISTS `item_company_master`;
CREATE TABLE `item_company_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `status` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `company_code` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_company_master`
--

/*!40000 ALTER TABLE `item_company_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_company_master` ENABLE KEYS */;


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
  `description` varchar(255) default NULL,
  `unit` varchar(255) default NULL,
  `quantity` int(11) default NULL,
  `rate_per_shs` decimal(10,2) default NULL,
  `amount` decimal(10,2) default NULL,
  `material_master_id` bigint(20) default NULL,
  `application_txn_id` bigint(20) default NULL,
  `feasibility_study_id` bigint(20) default NULL,
  `proceedings_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_itemrequired_materialmaster_id` (`material_master_id`),
  KEY `fk_itemrequired_applicationtxn_id` (`application_txn_id`),
  KEY `fk_itemrequired_feasibilitystudy_id` (`feasibility_study_id`),
  KEY `fk_itemrequired_proceedings_id` (`proceedings_id`),
  CONSTRAINT `fk_itemrequired_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`),
  CONSTRAINT `fk_itemrequired_feasibilitystudy_id` FOREIGN KEY (`feasibility_study_id`) REFERENCES `feasibility_study` (`id`),
  CONSTRAINT `fk_itemrequired_materialmaster_id` FOREIGN KEY (`material_master_id`) REFERENCES `material_master` (`id`),
  CONSTRAINT `fk_itemrequired_proceedings_id` FOREIGN KEY (`proceedings_id`) REFERENCES `proceedings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_required`
--

/*!40000 ALTER TABLE `item_required` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_required` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`item_sub_category_master`
--

DROP TABLE IF EXISTS `item_sub_category_master`;
CREATE TABLE `item_sub_category_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `item_sub_category_code` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `status` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `name` varchar(255) default NULL,
  `category_code` varchar(255) default NULL,
  `item_category_master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_itemsubcategorymaster_itemcategorymaster_id` (`item_category_master_id`),
  CONSTRAINT `fk_itemsubcategorymaster_itemcategorymaster_id` FOREIGN KEY (`item_category_master_id`) REFERENCES `item_category_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_sub_category_master`
--

/*!40000 ALTER TABLE `item_sub_category_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_sub_category_master` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`item_sub_code_master`
--

DROP TABLE IF EXISTS `item_sub_code_master`;
CREATE TABLE `item_sub_code_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `item_code_id` bigint(20) default NULL,
  `item_sub_code` varchar(255) default NULL,
  `item_name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `status` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `item_ccode_id` bigint(20) default NULL,
  `item_category_id` bigint(20) NOT NULL,
  `item_sub_category_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`item_sub_code_master`
--

/*!40000 ALTER TABLE `item_sub_code_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_sub_code_master` ENABLE KEYS */;


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
 ('ROLE_EMPLOYEE'),
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
 (96,'admin','2016-03-18 10:14:10','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (112,'customer','2016-03-21 16:21:14','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (128,'sf0020','2016-03-22 14:22:49','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (144,'sf0015','2016-03-23 15:03:56','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (160,'admin','2016-03-24 11:35:15','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (176,'sf0015','2016-03-25 10:27:55','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (192,'admin','2016-03-26 15:04:07','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (207,'sf0014','2016-03-28 12:47:44','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (223,'sf0029','2016-03-28 16:53:29','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (238,'admin','2016-03-28 18:42:41','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
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
 (250,'admin','2016-03-30 19:04:51','AUTHENTICATION_SUCCESS'),
 (251,'admin','2016-03-31 09:55:45','AUTHENTICATION_SUCCESS'),
 (252,'customer','2016-03-31 18:37:21','AUTHENTICATION_SUCCESS'),
 (253,'customer','2016-03-31 19:14:12','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
 (254,'sf0015','2016-04-01 10:07:33','AUTHENTICATION_SUCCESS'),
 (255,'customer','2016-04-01 10:11:22','AUTHENTICATION_SUCCESS'),
 (256,'sf0016','2016-04-01 10:17:46','AUTHENTICATION_SUCCESS'),
 (257,'sf0015','2016-04-01 10:18:05','AUTHENTICATION_SUCCESS'),
 (258,'sf0029','2016-04-01 10:30:50','AUTHENTICATION_SUCCESS'),
 (259,'customer','2016-04-01 10:34:01','AUTHENTICATION_SUCCESS'),
 (260,'sf0015','2016-04-01 10:36:08','AUTHENTICATION_SUCCESS'),
 (261,'sf0016','2016-04-01 10:36:22','AUTHENTICATION_SUCCESS'),
 (262,'sf0015','2016-04-01 10:36:34','AUTHENTICATION_FAILURE'),
 (263,'sf0015','2016-04-01 10:36:39','AUTHENTICATION_SUCCESS'),
 (264,'sf0029','2016-04-01 11:32:50','AUTHENTICATION_SUCCESS'),
 (265,'admin','2016-04-01 12:13:50','AUTHENTICATION_SUCCESS'),
 (266,'admin','2016-04-01 16:43:46','AUTHENTICATION_FAILURE'),
 (267,'admin','2016-04-01 16:43:51','AUTHENTICATION_SUCCESS'),
 (268,'admin','2016-04-01 16:51:17','AUTHENTICATION_SUCCESS'),
 (269,'admin','2016-04-01 18:23:13','AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` (`event_id`,`principal`,`event_date`,`event_type`) VALUES 
 (270,'admin','2016-04-02 07:29:18','AUTHENTICATION_SUCCESS'),
 (271,'admin','2016-04-04 15:54:37','AUTHENTICATION_SUCCESS'),
 (272,'admin','2016-04-04 15:54:49','AUTHENTICATION_SUCCESS'),
 (273,'sf0020','2016-04-04 16:02:31','AUTHENTICATION_SUCCESS'),
 (274,'admin','2016-04-04 16:11:04','AUTHENTICATION_SUCCESS'),
 (275,'admin','2016-04-04 16:27:56','AUTHENTICATION_SUCCESS'),
 (276,'admin','2016-04-04 17:57:14','AUTHENTICATION_SUCCESS'),
 (277,'admin','2016-04-04 17:57:24','AUTHENTICATION_SUCCESS'),
 (278,'customer','2016-04-04 18:08:07','AUTHENTICATION_SUCCESS');
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
 (84,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (94,'sessionId','3BC6F911B4E0B4AEA0379F2E6FBD3E2B');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (105,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (115,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (125,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (135,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (145,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (155,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (165,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (174,'sessionId','222A3CA3FEBC3CA25793D91C1701500B');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (184,'sessionId','A905EF6678AAA896227B33FB222A37A5');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (195,'message','Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (204,'sessionId','F9F420CDEAA94B6C5C2B8FA4D90248FC');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (215,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (225,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (235,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (245,'sessionId','0BA656795E346284C4AD7D7A52096B84');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (256,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (266,'message','Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
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
 (271,'sessionId','2B93427809C440629E72522C6F80C2ED'),
 (272,'remoteAddress','0:0:0:0:0:0:0:1'),
 (272,'sessionId','9E6295AB6C46FEC711591541431161D1'),
 (273,'remoteAddress','0:0:0:0:0:0:0:1'),
 (273,'sessionId','EDAE690F55575303255947F17355AECB'),
 (274,'remoteAddress','0:0:0:0:0:0:0:1'),
 (274,'sessionId','0F9D272DEC85FE2F7661D1BF4805931C'),
 (275,'remoteAddress','0:0:0:0:0:0:0:1'),
 (275,'sessionId','98694FEA9466368E5480C0FF1C3235D1'),
 (276,'remoteAddress','0:0:0:0:0:0:0:1');
INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`,`name`,`value`) VALUES 
 (276,'sessionId','54AEE1BF4AA3E4A88FBE2B7D9DDC5304'),
 (277,'remoteAddress','0:0:0:0:0:0:0:1'),
 (277,'sessionId','5574B6D7CD753305AFF78C4EEEEC06F2'),
 (278,'remoteAddress','0:0:0:0:0:0:0:1'),
 (278,'sessionId','B7C32F6AD93D0E504F4E3AF28610E325');
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
 ('+kuZ8fh+MT05jeTHFp5gmw==',5,'/FPZJ9OhXHNXmMOFgz7obg==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('+Pk/ksqHFtjiZEpwz8IVFw==',3,'mxORTPY9bPxFOvMgRBlUAg==','2016-03-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('+x9pYUuhC9mbEe9rwtR11Q==',3,'moGQ9xPllsRoWmPrNGPN5A==','2016-03-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('/29bYPrVgu1d1Sc7jw0g/A==',3,'8LwcwLZFkj1AZCc96MulbA==','2016-03-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('1+QXFJmIy6UbwHIQ6pxyqw==',15,'HCEI/JsrSbkhfqKDPYVlNQ==','2016-03-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('1/89/HnVJ0I06ozibiv32Q==',3,'CLNg9gJlNZ5pis+a+Q7wXg==','2016-04-01','127.0.0.1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('1V84cfVn/t71qSFvl+nJZw==',29,'nsyfHwdYGyWza9xnxrDHig==','2016-04-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('2uRUyClVWXV8AlYMNeuJOw==',3,'PErAzkiwxOnl+ZDZP6T7JQ==','2016-03-09','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('3+5ue5QHIAl3gaEJu2oyFQ==',3,'VKZnSsNabZGNesRM+Hpsvg==','2016-03-11','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('3Jc58QBWoVau1yojOb/H3g==',15,'gy3ieEY/pll52Q8TEujbag==','2016-03-25','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('4Aup8X2ZRQtO8SBDix3vAg==',3,'JmcI8+KGnhTfgXHMzc2WbQ==','2016-03-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('4Kz54liz72Tw0RjO2Zrkpw==',3,'x3C2IoyZxxmQ8IZ4lG8oMg==','2016-03-03','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0'),
 ('6B2RG6/uWQnWC28vOw8aXg==',3,'8QX7Ux+CmA4n198EaajbnA==','2016-03-08','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('7wzGSQte7LQJZBy4FB00zA==',29,'3iFZ6Aa9rNbM3Mx+jwfRsw==','2016-03-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('7Ytkb8b+j1F3nPGWDNtKcw==',3,'l96/eI6viuEUtDy5AibxbA==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('94hvipCH71zaqo62IRdfRw==',3,'XVI6TLhxx+zep89XenteTw==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('9iL/4QMAvR0xB64rW5HESw==',3,'/k/ylVdhu5TQbQGtpI5N0w==','2016-03-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('b2tUjwgyONjcsbKlUklC+Q==',3,'NiOf/v/Os1vh0byDEVURJA==','2016-03-16','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('BaKFrbA5ergaotzhQATfrw==',29,'m+gH8u2sRCjqxAeORaQ3zQ==','2016-03-23','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('BFMeF7yubUPWeHMlxnpHhg==',3,'86Dlw7pit1sjL3P4rA601A==','2016-03-14','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('bh/be/AQUN2jP5F1P7kdSQ==',3,'kn71wIORmJGn7dP9vuFMTQ==','2016-03-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('C0yHTgCTgnXcC62bNAch3g==',15,'QM8xVxLqO6GowIcWRmrfyw==','2016-03-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('cDuDRxT54VRDENf238evFw==',3,'qL156YzlR0uNU4rfJVaZGw==','2016-03-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('DEqQipDM3iLfE1PFx+mwRA==',3,'rlSkhLwJf+8q3MOatcrcDA==','2016-02-26','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('E4Oy802QfILWTBjkwYm95A==',3,'PeGsLDuZMTMKFV/0eIveBQ==','2016-03-03','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0'),
 ('e9IqVQci6qo0KydfoZjFlw==',3,'jtkywlmoMw4YctJ9M6wy7g==','2016-04-04','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('F2kB3vVrHHh0k5/n0nIzKA==',3,'70Zv5H6ShoO3S9SvA5QpCg==','2016-03-07','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('fNpzeb666xEusKesIpiVaA==',15,'EB7kPB3Prax0zRKgFghQUg==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('gDSEliPWD8ZBpMxy4sKgzw==',5,'JU7tUQKbDIftXBBphqfxHg==','2016-03-31','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('gjgSoEiEm2u4z82bxneQbA==',15,'Q5ukTsruzqRjPjl2jNIIBA==','2016-03-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('H5h1sXuT/PROO3BRuTQoxQ==',3,'8Mdnjzl+cjTeaivY6fzJyg==','2016-03-08','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('hFKfqjPrW8Z/amqU43M89g==',5,'OCMXKNowMoVsmqZ2jGSrxQ==','2016-03-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('Hw5vcTOmIcEuAJ+K5/ttTg==',3,'o/B/WvzQojyU6CRPqa0kug==','2016-03-17','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('IgMAY7/2pqUwaRD9fRqtZA==',5,'R/v6AK3DT+5hKspBsE0QdQ==','2016-03-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('jRDH7snELmCidi4buUDLww==',5,'/gjFefbhA17/a1ZM8SLTsw==','2016-03-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('Kb8YebfE7JAJymtqjh/HBQ==',3,'NLNenPNKYp99JL0VakVcDw==','2016-04-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('KktZvzrrvQS+oXZvzgcTUg==',3,'u31QC6MQA9wwAxm5BJQjyw==','2016-03-14','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('KU6VZmBHcK3RKoGgwhLDcA==',3,'CtWXmv/L/eFLnOkikbadrw==','2016-03-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('ltSsrr4LGe85jyi2xaM+GQ==',5,'O2cmSBS0fFhBkpiM4zdCkA==','2016-03-31','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('mJ9QRewb5tZnJtq1JS6BeQ==',3,'Y4wzvxHQSVqsUa7uMIa7ug==','2016-03-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('mLyebPZIjbx7SSunOdf+Wg==',3,'41vTjgSojIAoGzh7zCvKlA==','2016-03-07','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('mwRI6gjdS4jSIbBL/M9S/A==',5,'YfiL0mj0bauty4dctUuzzw==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('N0VbVqugqcpOaPe2ycnLMQ==',3,'YsBBSdNhwbDeIWaz442tHQ==','2016-03-15','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('nicdzpFVE7fA8XUEciBnBg==',15,'jjnMZHjRSEuYg7FgpryrNw==','2016-03-29','127.0.0.1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('nK0IBkLMvCyRtRagV7xkIA==',15,'O271OweAwksyy+Yei161xA==','2016-03-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('P0e/MdJF+ZPyjCmmCP/VLA==',3,'X1UpUG5k1rbHVscMv8bt+g==','2016-03-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('pHpRpa8xXCy3iCMrrNMIrA==',5,'R29MEgsOx1xUxZxxmbLkAQ==','2016-04-04','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0'),
 ('PpZTKOgtRshlEF2Y5H8Nzw==',3,'2oilX1ZtHFQ62F6g/CMZMg==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('qFVuu+uRSBJTp3pUTFHbiw==',3,'0mje7iCbeSAXZrqoHwhF0A==','2016-03-17','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('R6uDzkAd46IU0D3H4E0FVA==',3,'XcfBIoCDayNepzQsEjnkJg==','2016-03-17','127.0.0.1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('rXIEaYCdRMm4gzrZtlo3+Q==',3,'TgmeEQSq33s5paxqyFtkmQ==','2016-02-24','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('TN6nvrS4DAcjld/PAsVu3w==',3,'pWLUEigr2nT7tCBerVO3rw==','2016-03-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('uBmBvYHKZMXr5UHwlzKAPA==',3,'oIXVYnHZ2CAqS/lxhNHtBQ==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('UDMPyK37cLIktTJg9P5RrQ==',3,'ShKdK3zme2OSRapV6fqKMw==','2016-03-23','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('vtMl002D15Pa+vHwivvXRA==',3,'1DeW4nMVAE4PBnMcZPG3Fg==','2016-04-02','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('w8R34xrr8QJmlP5bdbRg0Q==',3,'xrnCE2P4+F4Gm2wY/shF3w==','2016-03-02','127.0.0.1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('Waj7OoRQvtqW2FGm3rwFHw==',3,'DScvaJTEMdPTgvNKd4KoRg==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('wtWMmV27ptr0Uutob4MNiQ==',3,'kzJYJannR0ZBgbGhEbYHqQ==','2016-03-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('x4nyB9VRFeiND5t0i1DU8Q==',3,'cEW9l632DDQaJ+cxS1szpw==','2016-02-29','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0'),
 ('x4RtQD81f5es8yJniJlwMg==',3,'3Uiil1Q8ezwtbjsEjH7MaQ==','2016-03-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('XDGXmczwil8KeD6KL6Yt1w==',3,'fbROZLGioPS/YUzyTyG2kg==','2016-03-10','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('xf8lo1lE43DIRysCXswyzQ==',3,'BRRKxIW3118BhhPFt6AyXA==','2016-03-28','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('xGIKoFe4g2VAv1/PRp0JLA==',3,'vsc1u7uz/s2rs9AmUbdl+g==','2016-04-01','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.2; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0');
INSERT INTO `jhi_persistent_token` (`series`,`user_id`,`token_value`,`token_date`,`ip_address`,`user_agent`) VALUES 
 ('Y14PvuTMLFbPHaMU7KlW9A==',3,'yHJLgjbsyzRXqkVlnn1+bA==','2016-03-22','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
 ('yC0cZ89A6opEpVDUp7MPLA==',3,'JNF2a3Nb1kSNcOuOEsAR4Q==','2016-03-18','0:0:0:0:0:0:0:1','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0'),
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
 (5,'customer','$2a$10$TYs/wcvo6fAPu0Xdt.jP3.pol1TJpvr13XvYIlBDzlut/Ytlsn/AO','customer','customer','customer@localhost','','en','NULL',NULL,'anonymousUser','2016-03-04 18:31:04',NULL,'admin','2016-03-08 10:00:31'),
 (6,'sf0006','$2a$10$.bcy9Ujl3NqgDj6zjXsRHe.UlXABdJyDbcByQ8nX9MvrHAwSYH9xi',NULL,NULL,'sf0006@localhost','','en','74075798038214678044',NULL,'anonymousUser','2016-03-18 12:43:28',NULL,'admin','2016-03-18 12:53:49');
INSERT INTO `jhi_user` (`id`,`login`,`password_hash`,`first_name`,`last_name`,`email`,`activated`,`lang_key`,`activation_key`,`reset_key`,`created_by`,`created_date`,`reset_date`,`last_modified_by`,`last_modified_date`) VALUES 
 (7,'sf0007','$2a$10$FDsPyaVUL9uFAR5vgWpX2ulAqEn5TJpoZjiCcGYqXO5iDkr11Ae3e',NULL,NULL,'sf0007@localhost','','en','96267878389108527398',NULL,'anonymousUser','2016-03-18 12:44:05',NULL,'admin','2016-03-18 12:53:50'),
 (8,'sf0008','$2a$10$/M8CkSWBuUlPxZavXb3h/.YQ.Q6lDArLBo5K/dIYcW6mdTfIf45bm',NULL,NULL,'sf0008@localhost','','en','54288279092286381964',NULL,'anonymousUser','2016-03-18 12:44:40',NULL,'admin','2016-03-18 12:53:51'),
 (9,'sf0009','$2a$10$nSqZo6ISDISQmxLFwbsq3u92OT3hOVS6mkjFlsDamxGInc8JBPtqq','Simon','Lupuga','sf0009@localhost','','en','97951374793812547574',NULL,'anonymousUser','2016-03-18 12:45:19',NULL,'sf0009','2016-03-28 12:45:10'),
 (10,'sf0010','$2a$10$rCzfnzFSb0dL3ocuA9RViuOaF1Gq5Vc0XzS7GSUKwBCv5tXI./39S','Claudius','Kaje','sf0010@localhost','','en','00161949524540976078',NULL,'anonymousUser','2016-03-18 12:45:54',NULL,'sf0010','2016-03-28 12:45:53'),
 (11,'sf0011','$2a$10$H7MmlGeb073RorJ69a1fdudLKTRLUFgkmFFeJ5xjCmtyjm.srVgDO','Vacant','vacant','sf0011@localhost','','en','33673054004780142550',NULL,'anonymousUser','2016-03-18 12:46:15',NULL,'sf0011','2016-03-28 12:46:42');
INSERT INTO `jhi_user` (`id`,`login`,`password_hash`,`first_name`,`last_name`,`email`,`activated`,`lang_key`,`activation_key`,`reset_key`,`created_by`,`created_date`,`reset_date`,`last_modified_by`,`last_modified_date`) VALUES 
 (12,'sf0012','$2a$10$sB2ETKC9vs0uljjuRtez6e0mhtaFDeJSyZGEwGIaGddJ34kRGIEpe',NULL,NULL,'sf0012@localhost','','en','71560095329758703944',NULL,'anonymousUser','2016-03-18 12:46:38',NULL,'admin','2016-03-18 12:53:56'),
 (13,'sf0013','$2a$10$okQIlNvGPOcBOadT8iyiJuC3vBGmXFtLe3UrvZpB33CWekNcdI/bu','Adoph','Shavu','sf0013@localhost','','en','91544376107529154273',NULL,'anonymousUser','2016-03-18 12:46:56',NULL,'sf0013','2016-03-28 12:47:31'),
 (14,'sf0014','$2a$10$qEvPMWpVpl54ndQdDE1FbuptYEfG9eqJbcYR7/DDXjSmaekdpwPyG','Herry','Makala','sf0014@localhost','','en','98218340790438067335',NULL,'anonymousUser','2016-03-18 12:47:34',NULL,'sf0014','2016-03-28 12:48:08'),
 (15,'sf0015','$2a$10$/396VihfwsI9iWd/UIEXKe7c3xbjwuWiePbUdUQL1Ug/tq9X2fy.i','Mbike','Jones','sf0015@localhost','','en','78744568076959050873',NULL,'anonymousUser','2016-03-18 12:47:51',NULL,'sf0015','2016-03-28 12:48:33'),
 (16,'sf0016','$2a$10$.YzGvqWeUSCQR5q2mtAl5u4ZVjBDmGCRh7xSbb5njETwx1ZwIN2bm','Fred','Selebwa','sf0016@localhost','','en','43535432288382530210',NULL,'anonymousUser','2016-03-18 12:48:46',NULL,'sf0016','2016-03-28 12:49:12');
INSERT INTO `jhi_user` (`id`,`login`,`password_hash`,`first_name`,`last_name`,`email`,`activated`,`lang_key`,`activation_key`,`reset_key`,`created_by`,`created_date`,`reset_date`,`last_modified_by`,`last_modified_date`) VALUES 
 (17,'sf0017','$2a$10$YDx44W8ShgQ0Jt7tVJXnWOMlyFJ3ernUc4Wh.yo.vVF6lYpGThccu','Eliezer','Josiah','sf0017@localhost','','en','06698912264567056315',NULL,'anonymousUser','2016-03-18 12:49:06',NULL,'sf0017','2016-03-28 12:49:56'),
 (18,'sf0018','$2a$10$if7uNqLVpZ.V5X/OkOes7uZ4gbDpwlrFmesdATE4PQrREuGff1c8G','Hellena','Mtembeje','sf0018@localhost','','en','38952305015731452387',NULL,'anonymousUser','2016-03-18 12:49:28',NULL,'sf0018','2016-03-28 12:50:47'),
 (19,'sf0019','$2a$10$43TTSOPygHilSwG5jA3joussQk3.vL.0B88bmbWGAiEyThp/0MLhm','Charles','Kiwely','sf0019@localhost','','en','80544871706487966991',NULL,'anonymousUser','2016-03-18 12:49:48',NULL,'sf0019','2016-03-28 12:51:19'),
 (20,'sf0020','$2a$10$UWsog7PJ9mJ5ALW3oT1qjuLsx4r6TtpC2.OtCi6D/qgMvD1pXuFiC',NULL,NULL,'sf0020@localhost','','en','83805380491517726753',NULL,'anonymousUser','2016-03-18 12:50:10',NULL,'admin','2016-03-18 12:54:00'),
 (21,'sf0021','$2a$10$fyfyZSZ/r94V30rK2V72r.Rs0yzTW81oB6mAvlq3Xz5OTBpBK0DJC','Salome','Mtwale','sf0021@localhost','','en','82028831560154180774',NULL,'anonymousUser','2016-03-18 12:51:39',NULL,'sf0021','2016-03-28 12:52:03');
INSERT INTO `jhi_user` (`id`,`login`,`password_hash`,`first_name`,`last_name`,`email`,`activated`,`lang_key`,`activation_key`,`reset_key`,`created_by`,`created_date`,`reset_date`,`last_modified_by`,`last_modified_date`) VALUES 
 (22,'sf0022','$2a$10$LV2mBS8VNveWYejGFcCh8OT2JoX.4oeEkPV/w5q5EDyY3dCQjUuGW','Mbaraka','Shemueta','sf0022@localhost','','en','29507620030240498935',NULL,'anonymousUser','2016-03-18 12:51:54',NULL,'sf0022','2016-03-28 12:53:17'),
 (23,'sf0023','$2a$10$ozkcRMy/Vjj6m.8O3XRg.OwDr3QybLS3qcX8/mZD.q4lzwVOxfPKW','Olivia','Masangya','sf0023@localhost','','en','73060005748380646738',NULL,'anonymousUser','2016-03-18 12:52:18',NULL,'sf0023','2016-03-28 12:53:58'),
 (24,'sf0024','$2a$10$0YPZsnW/yPzP5mx34t9Gse.x/.bAfB6nTTSBdvhifbYNzjsPQxeeW',NULL,NULL,'sf0024@localhost','','en','76650231398888348776',NULL,'anonymousUser','2016-03-18 12:52:33',NULL,'admin','2016-03-18 12:54:24'),
 (25,'sf0025','$2a$10$mUYlNHHcw0mpJiioW/p/0Ofx9qAPI6..bRfgSFHvo4ImvcB2CVQ96',NULL,NULL,'sf0025@localhost','','en','42984014546333704148',NULL,'anonymousUser','2016-03-18 12:52:46',NULL,'admin','2016-03-18 12:54:25'),
 (26,'sf0026','$2a$10$lnV2mPyzb/hLNWtYr7NeBeSe7SErEn4RwK3/Lzr6Oxjqkdj/yOUvS',NULL,NULL,'sf0026@localhost','','en','30926903415314102347',NULL,'anonymousUser','2016-03-18 12:53:00',NULL,'admin','2016-03-18 12:54:27');
INSERT INTO `jhi_user` (`id`,`login`,`password_hash`,`first_name`,`last_name`,`email`,`activated`,`lang_key`,`activation_key`,`reset_key`,`created_by`,`created_date`,`reset_date`,`last_modified_by`,`last_modified_date`) VALUES 
 (27,'sf0027','$2a$10$F58do6ARzxNnWwXFF3WVa.oH4Cnwfu9GGESf5c64Av7PnAVDDHQfu','Nobert','Kamba','sf0027@localhost','','en','88464590975638599993',NULL,'anonymousUser','2016-03-18 12:53:16',NULL,'sf0027','2016-03-28 12:57:46'),
 (28,'sf0028','$2a$10$K.A7jrXE..8CIm7SoeVMBepZyR2WdpZeQpPyUOfY429jeBaL.OMju','Francis','Hume','sf0028@localhost','','en','71805558970907066489',NULL,'anonymousUser','2016-03-18 12:53:33',NULL,'sf0028','2016-03-28 12:58:11'),
 (29,'sf0029','$2a$10$L1IXkaggwuSLeeF0fRR9CeMb/Qq6IM7EXLJI/jsdfFSOUQWf4oPHS',NULL,NULL,'sf0029@localhost','','en','68914493240806760364',NULL,'anonymousUser','2016-03-21 16:19:52',NULL,'admin','2016-03-21 16:20:23');
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
INSERT INTO `main_sewerage_size` (`id`,`size`) VALUES 
 (1,10),
 (2,11),
 (3,12);
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
INSERT INTO `main_water_size` (`id`,`size`) VALUES 
 (1,5),
 (2,6),
 (3,7);
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
INSERT INTO `make_of_pipe` (`id`,`make_name`) VALUES 
 (1,'Pipe make 1'),
 (2,'Pipe Make 2');
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
-- Table structure for table `watererp`.`material_master`
--

DROP TABLE IF EXISTS `material_master`;
CREATE TABLE `material_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `material_name` varchar(255) default NULL,
  `consumable_flag` varchar(255) default NULL,
  `uom_id` varchar(255) default NULL,
  `category_id` bigint(20) default NULL,
  `sub_category_id` bigint(20) default NULL,
  `item_code_id` bigint(20) default NULL,
  `item_sub_code_id` bigint(20) default NULL,
  `rate_contract_flag` varchar(255) default NULL,
  `unit_rate` decimal(10,2) default NULL,
  `description` varchar(255) default NULL,
  `status` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `company_code_id` decimal(10,2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`material_master`
--

/*!40000 ALTER TABLE `material_master` DISABLE KEYS */;
INSERT INTO `material_master` (`id`,`material_name`,`consumable_flag`,`uom_id`,`category_id`,`sub_category_id`,`item_code_id`,`item_sub_code_id`,`rate_contract_flag`,`unit_rate`,`description`,`status`,`creation_date`,`last_modified_date`,`company_code_id`) VALUES 
 (2,'Threading Tape',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (3,'G.S. Pipe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'30000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (4,'Pipe Polly',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (5,'Coupling',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (6,'Bib Tape',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (7,'Tee',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3500.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (8,'Union',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (9,'Elbow',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-23 00:00:00','2016-03-30 00:00:00',NULL);
INSERT INTO `material_master` (`id`,`material_name`,`consumable_flag`,`uom_id`,`category_id`,`sub_category_id`,`item_code_id`,`item_sub_code_id`,`rate_contract_flag`,`unit_rate`,`description`,`status`,`creation_date`,`last_modified_date`,`company_code_id`) VALUES 
 (10,'Nipple',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (11,'Polly Connector',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2500.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (12,'Plain Socket',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (13,'Reducing Socket/Bush',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (14,'Stop Cock',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12000.00',NULL,NULL,'2016-03-30 00:00:00','2016-03-30 00:00:00',NULL),
 (15,'Clamp Saddle',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `material_master` ENABLE KEYS */;


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
 (16,'Item Company Masters','#/itemCompanyMasters','2016-03-30 00:00:00');
INSERT INTO `menu_item` (`id`,`name`,`path`,`modified_date`) VALUES 
 (17,'Item Sub Code Master','#/itemSubCodeMasters','2016-03-30 00:00:00'),
 (18,'Material Master','#/materialMasters','2016-03-30 00:00:00'),
 (19,'Sib Entry','#/sibEntrys','2016-03-30 00:00:00'),
 (20,'Bill Of Material','#/billOfMaterials','2016-04-01 00:00:00'),
 (21,'Access List','#/accessLists','2016-03-15 00:00:00'),
 (22,'Bill Full Details','#/billFullDetailss','2016-03-15 00:00:00'),
 (23,'Collection Details','#/collDetailss','2016-03-15 00:00:00'),
 (24,'Current Users','#/currentUserss','2016-03-15 00:00:00'),
 (25,'Customer Details','#/custDetailss','2016-03-15 00:00:00'),
 (26,'Terminal','#/terminals','2016-03-15 00:00:00'),
 (27,'Terminal Log','#/terminalLogs','2016-03-15 00:00:00'),
 (28,'Version','#/versions','2016-03-15 00:00:00'),
 (29,'Complaint Type Master','#/complaintTypeMasters','2016-03-29 00:00:00'),
 (30,'Customer Complaints','#/customerComplaintss','2016-03-29 00:00:00');
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
 (30,30,30);
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
 (3,'Items Details',3,'2016-03-30 00:00:00'),
 (4,'Billing and Collection',3,'2016-03-15 00:00:00'),
 (5,'Customer Care',4,'2016-03-29 00:00:00');
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
 (30,1,5,30);
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
INSERT INTO `org_hierarchy` (`id`,`hierarchy_name`,`parent_hierarchy_id`,`creation_date`,`last_modified_date`,`status_master_id`) VALUES 
 (1,'Ministry Of Waters',0,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
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
 (13,'Human Resource & Administration Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',2);
INSERT INTO `org_hierarchy` (`id`,`hierarchy_name`,`parent_hierarchy_id`,`creation_date`,`last_modified_date`,`status_master_id`) VALUES 
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
 (24,'Technical Zonal Supervisor',15,'2016-03-18 00:00:00','2016-03-18 00:00:00',2);
INSERT INTO `org_hierarchy` (`id`,`hierarchy_name`,`parent_hierarchy_id`,`creation_date`,`last_modified_date`,`status_master_id`) VALUES 
 (25,'Customer',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',2);
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
  `is_head` int(11) default NULL,
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
INSERT INTO `org_role_instance` (`id`,`org_role_name`,`parent_org_role_id`,`creation_date`,`last_modified_date`,`is_head`,`status_master_id`,`org_role_hierarchy_id`,`departments_master_id`) VALUES 
 (1,'Ministry Of Waters',0,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (2,'Board Of Directors',1,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (3,'Energy & Water Utilities Regulatory Authority',2,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (4,'Managing Director',2,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (5,'Internal Auditor',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (6,'Legal Officier',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (7,'Public Relations Officer',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (8,'HPMU',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (9,'Stores & Supplies Officer',8,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (10,'Technical Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL);
INSERT INTO `org_role_instance` (`id`,`org_role_name`,`parent_org_role_id`,`creation_date`,`last_modified_date`,`is_head`,`status_master_id`,`org_role_hierarchy_id`,`departments_master_id`) VALUES 
 (11,'Commercial Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (12,'Finance Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (13,'Human Resource & Administration Manager',4,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (14,'Officer, GIS, Planning, Design & Construction',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (15,'Officer, Operation & Maintance - NRW, Water Supply and Sanitation',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (16,'Billing Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (17,'Credit Control Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (18,'ICT & Customer Care Officer',11,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (19,'Accountant',12,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL);
INSERT INTO `org_role_instance` (`id`,`org_role_name`,`parent_org_role_id`,`creation_date`,`last_modified_date`,`is_head`,`status_master_id`,`org_role_hierarchy_id`,`departments_master_id`) VALUES 
 (20,'Human Resource & Administration Section',13,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (21,'Zonal Supervisers',16,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (22,'Assistant Accountant(Revenue)',19,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (23,'Assistant Accountant(Expenditure)',19,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL),
 (24,'Technical Zonal Supervisor',15,'2016-03-21 00:00:00','2016-03-21 00:00:00',1,2,NULL,NULL),
 (25,'Customer',10,'2016-03-18 00:00:00','2016-03-18 00:00:00',1,2,NULL,NULL);
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
INSERT INTO `payment_types` (`id`,`payment_mode`) VALUES 
 (1,'Cash'),
 (2,'Demand Draft (DD)'),
 (3,'Cheque');
/*!40000 ALTER TABLE `payment_types` ENABLE KEYS */;


--
-- Table structure for table `watererp`.`percentage_master`
--

DROP TABLE IF EXISTS `percentage_master`;
CREATE TABLE `percentage_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `percent_type` varchar(255) default NULL,
  `percent_value` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`percentage_master`
--

/*!40000 ALTER TABLE `percentage_master` DISABLE KEYS */;
INSERT INTO `percentage_master` (`id`,`percent_type`,`percent_value`) VALUES 
 (1,'Supervision',10),
 (2,'Labour Charges',20),
 (3,'Site Survey',5),
 (4,'Connection Fee of A & B',20);
/*!40000 ALTER TABLE `percentage_master` ENABLE KEYS */;


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
  `sub_total_a` double default NULL,
  `supervision_charge` double default NULL,
  `labour_charge` double default NULL,
  `site_survey` double default NULL,
  `sub_total_b` double default NULL,
  `connection_fee` double default NULL,
  `water_meter_shs` double default NULL,
  `application_form_fee` double default NULL,
  `grand_total` double default NULL,
  `supervision_percent` double default NULL,
  `labour_charge_percent` double default NULL,
  `site_survey_percent` double default NULL,
  `connection_fee_percent` double default NULL,
  `application_txn_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_proceedings_supervisionpercent_id` (`supervision_percent`),
  KEY `fk_proceedings_labourchargepercent_id` (`labour_charge_percent`),
  KEY `fk_proceedings_sitesurveypercent_id` (`site_survey_percent`),
  KEY `fk_proceedings_connectionfeepercent_id` (`connection_fee_percent`),
  KEY `fk_proceedings_applicationtxn_id` (`application_txn_id`),
  CONSTRAINT `fk_proceedings_applicationtxn_id` FOREIGN KEY (`application_txn_id`) REFERENCES `application_txn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`proceedings`
--

/*!40000 ALTER TABLE `proceedings` DISABLE KEYS */;
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
INSERT INTO `req_org_workflow_mapping` (`id`,`creation_date`,`last_modified_date`,`workflow_master_id`,`request_master_id`,`org_role_instance_id`,`status_master_id`) VALUES 
 (1,'2016-03-21 00:00:00','2016-03-21 00:00:00',1,1,25,2),
 (2,'2016-03-21 00:00:00','2016-03-21 00:00:00',1,1,10,2),
 (3,'2016-03-21 00:00:00','2016-03-21 00:00:00',3,6,25,2);
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
INSERT INTO `request_master` (`id`,`request_type`,`creation_date`,`last_modified_date`,`description`,`internal_flag`,`status_master_id`) VALUES 
 (1,'REQUISITION','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,0,2),
 (2,'LEAVE','2016-03-26 00:00:00','2016-03-26 00:00:00',NULL,0,2),
 (3,'INCORRECT BILL','2016-03-31 00:00:00','2016-03-31 00:00:00',NULL,0,2),
 (4,'WATER LEAKAGE','2016-03-31 00:00:00','2016-03-31 00:00:00',NULL,0,2),
 (5,'SERVICE UNAVAILABILITY','2016-03-31 00:00:00','2016-03-31 00:00:00',NULL,0,2),
 (6,'CUSTOMERCOMPLAINTS','2016-03-31 00:00:00','2016-03-31 00:00:00',NULL,0,2);
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
INSERT INTO `request_workflow_history` (`id`,`request_stage`,`assigned_date`,`actioned_date`,`remarks`,`ip_address`,`assigned_role`,`domain_object`,`assigned_from_id`,`assigned_to_id`,`status_master_id`,`request_master_id`,`workflow_master_id`,`workflow_stage_master_id`,`applied_by_id`) VALUES 
 (90,1,'2016-04-01 10:34:49','2016-04-01 10:34:49',NULL,'127.0.0.1',NULL,38,5,15,5,1,1,NULL,NULL),
 (91,2,'2016-04-01 10:37:20','2016-04-01 10:37:20','approved at second level for feasibility','127.0.0.1',NULL,38,15,29,5,1,1,NULL,NULL),
 (93,3,'2016-04-01 11:37:29','2016-04-01 11:37:29',NULL,'127.0.0.1',NULL,38,29,20,3,1,1,NULL,NULL),
 (94,1,'2016-04-02 12:10:48','2016-04-02 12:10:48',NULL,'169.254.178.179',NULL,8,5,20,3,6,3,NULL,NULL),
 (95,1,'2016-04-02 12:22:09','2016-04-02 12:22:09',NULL,'169.254.178.179',NULL,9,5,20,3,6,3,NULL,NULL),
 (96,1,'2016-04-02 12:41:21','2016-04-02 12:41:21',NULL,'169.254.178.179',NULL,10,5,20,3,6,3,NULL,NULL),
 (97,1,'2016-04-04 18:12:21','2016-04-04 18:12:21',NULL,'169.254.178.179',NULL,11,5,20,3,6,3,NULL,NULL);
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
INSERT INTO `role_workflow_mapping` (`id`,`creation_date`,`last_modified_date`,`status_master_id`,`org_role_instance_id`,`workflow_master_id`,`request_master_id`) VALUES 
 (1,'2016-03-21 00:00:00','2016-03-21 00:00:00',2,10,1,1),
 (2,'2016-03-21 00:00:00','2016-03-21 00:00:00',2,24,1,1),
 (3,'2016-03-21 00:00:00','2016-03-21 00:00:00',2,15,1,1);
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
INSERT INTO `scheme_master` (`id`,`scheme_name`) VALUES 
 (1,'Scheme 1'),
 (2,'Scheme 2');
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
-- Table structure for table `watererp`.`sib_entry`
--

DROP TABLE IF EXISTS `sib_entry`;
CREATE TABLE `sib_entry` (
  `id` bigint(20) NOT NULL auto_increment,
  `sib_id` bigint(20) default NULL,
  `so_no` varchar(255) default NULL,
  `so_date` timestamp NULL default NULL,
  `demand_date` timestamp NULL default NULL,
  `dir` varchar(255) default NULL,
  `div_name` varchar(255) default NULL,
  `inv_no` bigint(20) default NULL,
  `sib_date` timestamp NULL default NULL,
  `sib_no` varchar(255) default NULL,
  `ir_date` timestamp NULL default NULL,
  `ir_no` varchar(255) default NULL,
  `vendor_code` varchar(255) default NULL,
  `remarks` varchar(255) default NULL,
  `to_user` timestamp NULL default NULL,
  `from_user` timestamp NULL default NULL,
  `status` int(11) default NULL,
  `creation_date` timestamp NULL default NULL,
  `last_modified_date` timestamp NULL default NULL,
  `dc_no` varchar(255) default NULL,
  `dc_date` timestamp NULL default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watererp`.`sib_entry`
--

/*!40000 ALTER TABLE `sib_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `sib_entry` ENABLE KEYS */;


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
 (1,'Domestic - Usage',0,999999,780,11,31,1,1),
 (2,'Domestic - Fixed',0,999999,18000,0,0,1,2),
 (3,'Domestic - Service',0,999999,1800,0,0,1,3),
 (4,'Domestic - Usage',0,999999,780,11,32,2,1),
 (5,'Domestic - Fixed',0,999999,19000,0,0,2,2),
 (6,'Domestic - Service',0,999999,1800,0,0,2,3),
 (7,'Domestic - Usage',0,999999,820,12,33,3,1),
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
  `valid_from` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `valid_to` timestamp NOT NULL default '0000-00-00 00:00:00',
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
 (20,'/billOfMaterials',1),
 (21,'/accessLists',1),
 (22,'/billFullDetailss',1),
 (23,'/collDetailss',1),
 (24,'/currentUserss',1),
 (25,'/custDetailss',1),
 (26,'/terminals',1),
 (27,'/terminalLogs',1),
 (28,'/versions',1),
 (29,'/complaintTypeMasters',1),
 (30,'/customerComplaintss',1);
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
 (39,30,'ROLE_CUSTOMER');
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
INSERT INTO `workflow` (`id`,`stage_id`,`workflow_master_id`,`relative_from_role_id`,`absolute_from_role_id`,`relationship_type_id`,`relative_to_role_id`,`absolute_to_role_id`,`escalation_relationship_type_id`,`relative_escalation_to_id`,`absolute_escalation_to_id`,`workflow_stage_master_id`) VALUES 
 (1,1,1,NULL,NULL,2,NULL,10,NULL,NULL,24,4),
 (2,2,1,NULL,10,2,NULL,24,NULL,NULL,NULL,5),
 (3,3,1,NULL,24,2,NULL,15,NULL,NULL,NULL,2),
 (4,4,1,NULL,15,2,NULL,10,NULL,NULL,NULL,2),
 (5,5,1,NULL,10,2,NULL,22,NULL,NULL,NULL,NULL),
 (6,6,1,NULL,22,2,NULL,9,NULL,NULL,NULL,NULL),
 (7,7,1,NULL,9,2,NULL,15,NULL,NULL,NULL,NULL),
 (8,8,1,NULL,15,2,NULL,16,NULL,NULL,NULL,NULL),
 (9,1,3,NULL,NULL,2,NULL,15,NULL,NULL,NULL,4),
 (10,2,3,NULL,15,2,NULL,12,NULL,NULL,NULL,5),
 (11,3,3,NULL,12,2,NULL,4,NULL,NULL,NULL,5),
 (12,4,3,NULL,4,2,NULL,16,NULL,NULL,NULL,2);
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
INSERT INTO `workflow_master` (`id`,`workflow_name`,`to_workflow`,`creation_date`,`last_modified_date`,`status_master_id`) VALUES 
 (1,'REQUISITION',0,'2016-03-18 00:00:00','2016-03-18 00:00:00',2),
 (2,'LEAVE',0,'2016-03-26 00:00:00','2016-03-26 00:00:00',2),
 (3,'CUSTOMER COMPLAINT',0,'2016-03-31 00:00:00','2016-03-31 00:00:00',2);
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
INSERT INTO `workflow_relations` (`id`,`name`,`status_master_id`) VALUES 
 (1,'DIRECTOR',2),
 (3,'PROGRAMME DIRECTOR',2),
 (5,'ASSOCIATE DIRECTOR',2),
 (7,'PROJECT DIRECTOR',2),
 (9,'TECHNICAL DIRECTORATE',2),
 (13,'DIVISION HEAD',2),
 (20,'BOSS',2),
 (21,'BOSSES BOSS',2),
 (22,'ADMIN',2);
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
INSERT INTO `workflow_relationships` (`id`,`name`,`status_master_id`) VALUES 
 (1,'Relative',2),
 (2,'Absolute',3);
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
INSERT INTO `workflow_stage_master` (`id`,`name`,`creation_date`,`last_modified_date`,`description`,`status_master_id`) VALUES 
 (1,'Recommended','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (2,'Approved','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (3,'Sanctioned','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (4,'Waiting','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (5,'Forwarded','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2),
 (6,'Completed','2016-03-21 00:00:00','2016-03-21 00:00:00',NULL,2);
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
INSERT INTO `workflow_type_master` (`id`,`name`,`creation_date`,`last_modified_date`,`description`,`status_master_id`) VALUES 
 (1,'GENERIC','2016-03-26 00:00:00','2016-03-26 00:00:00',NULL,2),
 (2,'ROLE','2016-03-26 00:00:00','2016-03-26 00:00:00',NULL,2);
/*!40000 ALTER TABLE `workflow_type_master` ENABLE KEYS */;


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
