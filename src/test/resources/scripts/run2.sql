# Make Payments
### Full Payment for 02020005

### Partial Payment for 04060001

### Full Payment for 08090001

### Partial Payment for 05050001

### No Payment for 04060002

### Online Payment for 04060003

### Partial Online Payment for 04060004

### Partial Online Payment for 05050002

### Partial Manual Payment for 04060004




### Scenario 9: Normal Customer 02020005, 1 month bill

### Scenario 10: Normal Customer 04060001, 2 months bill, Same Tariff

### Scenario 11: Normal Customer 05050001, 3 months bill, Same Tariff

### Scenario 12: Normal Customer 05050002, 2 months bill, Tariff Change 2nd Month

### Scenario 13: Normal Customer 05050003, 3 months bill, Tariff Change 3rd Month

delete from bill_run_details where bill_details_id between 45 and 52;

delete from bill_details where id between 45 and 52;


INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (45,'02020005',NULL,'2016-05-01',NULL,NULL,'M','201605','201605',NULL,15,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-27','\0','2016-06-01 16:09:21','INITIATED',10), (46,'08090001',NULL,'2016-05-01',NULL,NULL,'M','201604','201605',NULL,18,21,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-28','\0','2016-06-01 16:34:25','INITIATED',15), (47,'04060001',NULL,'2016-05-01',NULL,NULL,'M','201603','201605',NULL,18,22,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-30','\0','2016-06-01 16:35:15','INITIATED',14);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (48,'05050001',NULL,'2016-05-01',NULL,NULL,'M','201604','201605',NULL,21,25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-30','\0','2016-06-01 17:21:03','INITIATED',15), (49,'04060002',NULL,'2016-05-01',NULL,NULL,'M','201603','201605',NULL,22,41,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-28','\0','2016-06-01 17:21:32','INITIATED',17), (50,'04060003',NULL,'2016-05-01',NULL,NULL,'M','201603','201605',NULL,25,42,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-29','\0','2016-06-01 17:33:00','INITIATED',15);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (51,'04060004',NULL,'2016-05-01',NULL,NULL,'M','201603','201605',NULL,23,43,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-30','\0','2016-06-01 17:33:34','INITIATED',15), (52,'05050002',NULL,'2016-05-01',NULL,NULL,'M','201603','201605',NULL,30,60,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-29','\0','2016-06-01 17:39:55','INITIATED',14);
