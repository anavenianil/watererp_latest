#How to make a new customer from an existing customer
``delete from bill_run_details where can='02020005';``

``delete from bill_details where can='02020005';``

``delete from bill_full_details where can='02020005';``

``update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,lock_charges=0, prev_avg_kl=0.0,met_avg_kl=0.0  where can='02020005';``