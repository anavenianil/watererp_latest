update `adjustments` set bill_full_details_id=null, status='INITIATED' where can in ('02020005', '04060001', '05050001', '04060002', '04060003', '04060004', '05050002', '08090001')

# Make CAN 02020005 new (Customer 1)

delete from bill_run_details where can='02020005';
delete from bill_details where can='02020005';
delete from bill_full_details where can='02020005';
delete from coll_details where can='02020005';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-04-23',lock_charges=null, prev_avg_kl=0.0,met_avg_kl=0.0 where can='02020005';

# Make CAN 04060001 new (Customer 2)

delete from bill_run_details where can='04060001';
delete from bill_details where can='04060001';
delete from bill_full_details where can='04060001';
delete from coll_details where can='04060001';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-03-03',lock_charges=null, prev_avg_kl=0.0,met_avg_kl=0.0 where can='04060001';

# Make CAN 05050001 new (Customer 3)

delete from bill_run_details where can='05050001';
delete from bill_details where can='05050001';
delete from bill_full_details where can='05050001';
delete from coll_details where can='05050001';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-03-23',lock_charges=null, prev_avg_kl=0.0,met_avg_kl=0.0 where can='05050001';

# Make CAN 04060002 new (Customer 4)

delete from bill_run_details where can='04060002';
delete from bill_details where can='04060002';
delete from bill_full_details where can='04060002';
delete from coll_details where can='04060002';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-03-03',lock_charges=null, prev_avg_kl=0.0,met_avg_kl=0.0 where can='04060002';

# Make CAN 04060003 new (Customer 5)

delete from bill_run_details where can='04060003';
delete from bill_details where can='04060003';
delete from bill_full_details where can='04060003';
delete from coll_details where can='04060003';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-03-23',lock_charges=null, prev_avg_kl=0.0,met_avg_kl=0.0 where can='04060003';

# Make CAN 04060004 new (Customer 6)

delete from bill_run_details where can='04060004';
delete from bill_details where can='04060004';
delete from bill_full_details where can='04060004';
delete from coll_details where can='04060004';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-02-03',lock_charges=null, prev_avg_kl=0.0,met_avg_kl=0.0 where can='04060004';

# Make CAN 05050002 new (Customer 7)

delete from bill_run_details where can='05050002';
delete from bill_details where can='05050002';
delete from bill_full_details where can='05050002';
delete from coll_details where can='05050002';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-02-23',lock_charges=null, prev_avg_kl=0.0,met_avg_kl=0.0 where can='05050002';

# Make CAN 08090001 new (Customer 8)

delete from bill_run_details where can='08090001';
delete from bill_details where can='08090001';
delete from bill_full_details where can='08090001';
delete from coll_details where can='08090001';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,meter_fix_date='2016-04-03',lock_charges=null, prev_avg_kl=0.0,met_avg_kl=0.0 where can='08090001';
