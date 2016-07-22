# Overall Architecture

## Billing Process

For each Bill_Details record (process_bill)

	- Init BFD (initBill)
	- If it is first bill (process_bill_new_meter)
		- If it is Metered (process_bill_new_meter)
			- Get units from current and previous reading (process_bill_new_meter)
			- Run tariff query based on AvgKL (process_bill_new_meter)
			- Check if Multiple tariffs exist (process_bill_new_meter)
			- If Yes, Calculate Partial Bill for first month and Normal bill for remaining period (process_bill_new_meter & calc_charges)
			- Aggregate both (calc_charges)
			- If No, Calculate whole bill in one go (process_bill_new_meter & calc_charges)
		- If it is Locked, Burnt, etc.,
			- Get Avg KL from configuration
			- Run tariff query based on AvgKL
			- Check if Multiple tariffs exist
			- If Yes, Calculate Partial Bill for first month and Normal bill for remaining period
			- Aggregate both
	- If it is Normal bill (process_bill_normal)
		- If it is Metered
			- Get units from current and previous reading
			- Run tariff query based on AvgKL
			- Calculate Normal bill for remaining period (process_bill_common)
		- If it is Locked, Burnt, etc.,
			- Get Avg KL from configuration
			- Run tariff query based on AvgKL
			- Calculate Normal bill for remaining period

