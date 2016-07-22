new connnection form
======================
stage	status	description
====	======	=====================================================
1 	0	request saved in applictionTxn and in request_workflow_history
2 	1	request approved with approval date and remarks in request_workflow_history
3 	2	request approved with approval date and remarks in request_workflow_history
4	3	feasibility_study, application_txn(division and street are saaved)
5	4	proceedings & item_required
6	5	receipt generated(data saved in receipt table)
7	6	issue items(item_required table updates with provided)
8	7	issue meter(meterDetails status is changed to allotted & other details saved in application_txn as well as in request_workflow_history)
9	8	generate CAN(meter status chnaged to allotted in meter_details, record saved in cust_details, cust_meter_mapping)