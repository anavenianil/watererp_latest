# Make CAN 02020005 new

delete from bill_run_details where can='02020005';
delete from bill_details where can='02020005';
delete from bill_full_details where can='02020005';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,lock_charges=0, prev_avg_kl=0.0,met_avg_kl=0.0 where can='02020005';

# Make CAN 04060001 new

delete from bill_run_details where can='04060001';
delete from bill_details where can='04060001';
delete from bill_full_details where can='04060001';
update cust_details set prev_Bill_Month=null,arrears=0.0,met_reading_dt=null,lock_charges=0, prev_avg_kl=0.0,met_avg_kl=0.0 where can='04060001';

# Scenario 1: New Customer, 1 month bill, Days < 15

# Scenario 2: New Customer, 1 month bill, Days > 15

# Scenario 3: New Customer, 2 months bill, Partial Days First Month > 15

# Scenario 4: New Customer, 2 months bill, Partial Days First Month < 15

# Scenario 5: New Customer, 2 months bill, Tariff Change 2nd Month, Partial Days First Month > 15

# Scenario 6: New Customer, 2 months bill, Tariff Change 2nd Month, Partial Days First Month < 15

# Scenario 7: New Customer, 3 months bill, Tariff Change 3rd Month, Partial Days First Month > 15

# Scenario 8: New Customer, 3 months bill, Tariff Change 3rd Month, Partial Days First Month < 15
