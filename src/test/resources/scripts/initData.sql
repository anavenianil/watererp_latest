# Make CAN 02020005 new (Customer 1)

delete from bill_run_details where can='02020005';
delete from bill_details where can='02020005';
delete from bill_full_details where can='02020005';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-02-03',lock_charges=0, prev_avg_kl=0.0,met_avg_kl=0.0 where can='02020005';

# Make CAN 04060001 new (Customer 2)

delete from bill_run_details where can='04060001';
delete from bill_details where can='04060001';
delete from bill_full_details where can='04060001';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-02-23',lock_charges=0, prev_avg_kl=0.0,met_avg_kl=0.0 where can='04060001';

# Make CAN 05050001 new (Customer 3)

delete from bill_run_details where can='05050001';
delete from bill_details where can='05050001';
delete from bill_full_details where can='05050001';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-02-03',lock_charges=0, prev_avg_kl=0.0,met_avg_kl=0.0 where can='05050001';
