#Overall Architecture

##Billing Process

For each Bill_Details record
	- Init BFD
	- If it is first bill
		- If it is Metered
			- Get units from current and previous reading
			- Run tariff query based on AvgKL
			- Check if Multiple tariffs exist
			- If Yes, Calculate Partial Bill for first month and Normal bill for remaining period
			- Aggregate both
		- If it is Locked, Burnt, etc.,
			- Get Avg KL from configuration
			- Run tariff query based on AvgKL
			- Check if Multiple tariffs exist
			- If Yes, Calculate Partial Bill for first month and Normal bill for remaining period
			- Aggregate both
	- If it is Normal bill
		- If it is Metered
			- Get units from current and previous reading
			- Run tariff query based on AvgKL
			- Calculate Normal bill for remaining period
		- If it is Locked, Burnt, etc.,
			- Get Avg KL from configuration
			- Run tariff query based on AvgKL
			- Calculate Normal bill for remaining period

