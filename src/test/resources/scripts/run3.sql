### RUN3

### Scenario 9: Normal Customer 02020005, 1 month bill, Same Tariff

### Scenario 9: Normal Customer 08090001, 1 month bill, Same Tariff, Door Lock

### Scenario 10: Normal Customer 04060001, 1 month bill, Same Tariff, Door Lock to Reading

### Scenario 11: Normal Customer 05050001, 1 month bill, Same Tariff, Door Lock (2nd month also)

### Scenario 12: Normal Customer 04060002, 1 month bill, Same Tariff, 

### Scenario 12: Normal Customer 04060003, 1 month bill, Same Tariff, Door Lock (2nd Month)

### Scenario 12: Normal Customer 04060004, 1 month bill, Same Tariff, Door Lock

### Scenario 12: Normal Customer 05050002, 1 month bill, Same Tariff


delete from bill_run_details where bill_details_id between 90 and 97;

delete from bill_details where id between 90 and 97;

INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (90,'02020005',NULL,'2016-06-01',NULL,NULL,'M','201606','201606',NULL,22,25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-27','\0','2016-06-01 16:09:21','INITIATED',10), (91,'08090001',NULL,'2016-06-01',NULL,NULL,'L','201606','201606',NULL,26,26,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-28','\0','2016-06-01 16:34:25','INITIATED',15),(92,'04060001',NULL,'2016-06-01',NULL,NULL,'M','201606','201606',NULL,22,32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-30','\0','2016-06-01 16:35:15','INITIATED',14);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (93,'05050001',NULL,'2016-06-01',NULL,NULL,'L','201606','201606',NULL,25,25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-30','\0','2016-06-01 17:21:03','INITIATED',15), (94,'04060002',NULL,'2016-06-01',NULL,NULL,'M','201606','201606',NULL,47,49,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-28','\0','2016-06-01 17:21:32','INITIATED',17), (95,'04060003',NULL,'2016-06-01',NULL,NULL,'L','201606','201606',NULL,42,42,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-29','\0','2016-06-01 17:33:00','INITIATED',15);
INSERT INTO `bill_details` (`id`,`can`,`bill_number`,`bill_date`,`bill_time`,`meter_make`,`current_bill_type`,`from_month`,`to_month`,`meter_fix_date`,`initial_reading`,`present_reading`,`units`,`water_cess`,`sewerage_cess`,`service_charge`,`meter_service_charge`,`total_amount`,`net_payable_amount`,`telephone_no`,`meter_status`,`met_reader_code`,`bill_flag`,`svr_status`,`terminal_id`,`meter_reader_id`,`user_id`,`mobile_no`,`notice_no`,`lat`,`longi`,`no_meter_amt`,`met_reading_dt`,`is_rounding`,`insert_dt`,`status`,`mtr_reader_id`) VALUES  (96,'04060004',NULL,'2016-06-01',NULL,NULL,'L','201606','201606',NULL,60,60,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-30','\0','2016-06-01 17:33:34','INITIATED',15), (97,'05050002',NULL,'2016-06-01',NULL,NULL,'M','201606','201606',NULL,68,71,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-06-29','\0','2016-06-01 17:39:55','INITIATED',14);


#Preparation for Run 4: Before posting online Payments
delete FROM `online_payment_response` where id between 16 and 16;
delete FROM `online_payment_response` where online_payment_order_id between 16 and 16;
delete FROM `online_payment_callback` where online_payment_order_id between 16 and 16;
delete FROM `online_payment_order` where id between 16 and 16;

INSERT INTO `online_payment_order` (`id`,`service_code`,`amount`,`pay_by`,`user_defined_field`,`email`,`phone`,`order_time`,`merchant_master_id`) VALUES (16,'TESTS001',2000.0,'TIGOPESADIR','02020005','test@gmail.com',1234567890,'2016-05-02 22:17:25',2);
INSERT INTO `online_payment_response` (`id`,`response_code`,`response_time`,`redirect_url`,`merchant_txn_ref`,`online_payment_order_id`) VALUES  (16,'100','2016-05-03 22:35:46','http://crystal.tekmindz.com:80/maxcompp/directpaymentreceipt.xhtml?txnref=6828618857&name=null&paymentmode=TIGOPESADIR','1100',16);
