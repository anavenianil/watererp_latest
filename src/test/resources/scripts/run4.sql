### Run4

### Scenario 9: Normal Customer 02020005, 1 month bill, Tariff change

### Scenario 9: Normal Customer 08090001, 1 month bill, 1 month old tariff and 1 month new tariff, Door Lock to reading

### Scenario 10: Normal Customer 04060001, 1 month bill, New Tariff, 

### Scenario 11: Normal Customer 05050001, 1 month bill, 2 months old tariff and one month new Tariff, 2 months Door Lock to reading

### Scenario 12: Normal Customer 04060002, 1 month bill, Same Tariff, 

### Scenario 12: Normal Customer 04060003, 1 month bill, Same Tariff, Door Lock (3rd Month)

### Scenario 12: Normal Customer 04060004, 1 month bill,  1 month old tariff and 1 month new tariff, Door Lock to reading 

### Scenario 12: Normal Customer 05050002, 1 month bill, New Tariff


delete from bill_run_details where bill_details_id between 100 and 107;

delete from bill_details where id between 100 and 107;


INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (100,'02020005',NULL,'2016-07-01',NULL,NULL,'M','201607','201607',NULL,25,29,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-27','\0','2016-06-01 16:09:21','INITIATED',10), (101,'08090001',NULL,'2016-07-01',NULL,NULL,'M','201607','201607',NULL,26,37,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-28','\0','2016-06-01 16:34:25','INITIATED',15), (102,'04060001',NULL,'2016-07-01',NULL,NULL,'M','201607','201607',NULL,32,36,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-30','\0','2016-06-01 16:35:15','INITIATED',14);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (103,'05050001',NULL,'2016-07-01',NULL,NULL,'M','201607','201607',NULL,25,33,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-30','\0','2016-06-01 17:21:03','INITIATED',15), (104,'04060002',NULL,'2016-07-01',NULL,NULL,'M','201607','201607',NULL,47,49,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-28','\0','2016-06-01 17:21:32','INITIATED',17), (105,'04060003',NULL,'2016-07-01',NULL,NULL,'L','201607','201607',NULL,42,42,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-29','\0','2016-06-01 17:33:00','INITIATED',15);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (106,'04060004',NULL,'2016-07-01',NULL,NULL,'M','201607','201607',NULL,60,68,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-30','\0','2016-06-01 17:33:34','INITIATED',15), (107,'05050002',NULL,'2016-07-01',NULL,NULL,'M','201607','201607',NULL,71,77,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-07-29','\0','2016-06-01 17:39:55','INITIATED',14);
