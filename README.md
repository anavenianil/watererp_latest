# WaterERP

This application was generated using JHipster, you can find documentation and help at [https://jhipster.github.io](https://jhipster.github.io).

Before you can build this project, you must install and configure the following dependencies on your machine:


1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools (like
[Bower][] and [BrowserSync][]). You will only need to run this command when dependencies change in package.json.

    npm install

We use [Grunt][] as our build system. Install the grunt command-line tool globally with:

    npm install -g grunt-cli

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    mvn
    grunt

Bower is used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in `bower.json`. You can also run `bower update` and `bower install` to manage dependencies.
Add the `-h` flag on any command to see how you can use it. For example, `bower update -h`.

#JENKINS Integration
Jenkins is running on http://webmail.callippus.co.uk:9090/. 
It's configured with WebHook URL:  http://webmail.callippus.co.uk:9090/job/watererp/build?token=JHGFGKGFHGFDJH
Right now this has been removed as proper Authentication in Jenkins is still not configured.
Do not forget to install tools in Jenkins before configuring this.

# Building for production

To optimize the WaterERP client for production, run:

    mvn -Pprod clean package

This will concatenate and minify CSS and JavaScript files. It will also modify `index.html` so it references
these new files.

To ensure everything worked, run:

    java -jar target/*.war --spring.profiles.active=prod

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

# Testing

Unit tests are run by [Karma][] and written with [Jasmine][]. They're located in `src/test/javascript` and can be run with:

    grunt test



# Continuous Integration

To setup this project in Jenkins, use the following configuration:

* Project name: `WaterERP`
* Source Code Management
    * Git Repository: `git@github.com:xxxx/WaterERP.git`
    * Branches to build: `*/master`
    * Additional Behaviours: `Wipe out repository & force clone`
* Build Triggers
    * Poll SCM / Schedule: `H/5 * * * *`
* Build
    * Invoke Maven / Tasks: `-Pprod clean package`
* Post-build Actions
    * Publish JUnit test result report / Test Report XMLs: `build/test-results/*.xml`

[JHipster]: https://jhipster.github.io/
[Node.js]: https://nodejs.org/
[Bower]: http://bower.io/
[Grunt]: http://gruntjs.com/
[BrowserSync]: http://www.browsersync.io/
[Karma]: http://karma-runner.github.io/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/

# Dependent tables for dropping
SELECT
TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME
FROM
INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
REFERENCED_TABLE_NAME = 'application_txn';

# Automated query for dropping
SELECT concat('drop table ',TABLE_NAME,'; delete from databasechangelog where filename like ''%_',replace(table_name,'_',''),'.xml'';')
FROM
INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
REFERENCED_TABLE_NAME = 'application_txn'
union
select concat('drop table ',REFERENCED_TABLE_NAME,'; delete from databasechangelog where filename like ''%_',replace(REFERENCED_TABLE_NAME,'_',''),'.xml'';')
FROM
INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
REFERENCED_TABLE_NAME = 'application_txn'


update `adjustments` set bill_full_details_id=null, status='INITIATED';

INSERT INTO `cust_details` (`id`,`can`,`div_code`,`sec_code`,`sec_name`,`met_reader_code`,`conn_date`,`cons_name`,`house_no`,`address`,`city`,`pin_code`,`category_unused`,`pipe_size`,`board_meter`,`sewerage`,`prev_bill_type`,`prev_bill_month`,`prev_avg_kl`,`met_reading_dt`,`prev_reading`,`met_reading_mo`,`met_avg_kl`,`arrears`,`reversal_amt`,`installment`,`other_charges`,`surcharge`,`hrs_surcharge`,`res_units`,`met_cost_installment`,`int_on_arrears`,`last_pymt_dt`,`last_pymt_amt`,`mobile_no`,`cc_flag`,`cp_flag`,`notice_flag`,`dr_flag`,`lat`,`longi`,`meter_fix_date`,`lock_charges`,`id_number`,`email`,`status`,`tariff_category_master_id`,`pipe_size_master_id`,`division_master_id`,`street_master_id`,`meter_details_id`) VALUES 
 (9,'06020001','06','02','PQR','14','2016-04-23','Test Customer 06020001','123','HIJ','HYDERABAD','500086',NULL,0.5,'T','F','M',NULL,0,NULL,6,NULL,0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9989505111','0','0','0','0','0','0','2016-04-23',0,'123456','abc@abc.abc','ACTIVE',1,1,6,2,11),
 (10,'06020002','06','02','PQR','14','2016-04-23','Test Customer 06020002','123','HIJ','HYDERABAD','500086',NULL,0.5,'T','F','M',NULL,0,NULL,0,NULL,0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9989505111','0','0','0','0','0','0','2016-04-03',0,'123456','abc@abc.abc','ACTIVE',1,1,6,2,12),
 (11,'06020003','06','02','PQR','14','2016-04-23','Test Customer 06020003','123','HIJ','HYDERABAD','500086',NULL,0.5,'T','F','M',NULL,0,NULL,14,NULL,0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9989505111','0','0','0','0','0','0','2016-04-23',0,'123456','abc@abc.abc','ACTIVE',1,1,6,2,13),
 (12,'06020004','06','02','PQR','14','2016-04-23','Test Customer 06020004','123','HIJ','HYDERABAD','500086',NULL,0.5,'T','F','M',NULL,0,NULL,21,NULL,0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9989505111','0','0','0','0','0','0','2016-04-03',0,'123456','abc@abc.abc','ACTIVE',1,1,6,2,14),
 (13,'06020005','06','02','PQR','14','2016-04-23','Test Customer 06020005','123','HIJ','HYDERABAD','500086',NULL,0.5,'T','F','M',NULL,0,NULL,2,NULL,0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9989505111','0','0','0','0','0','0','2016-03-23',0,'123456','abc@abc.abc','ACTIVE',1,1,6,2,15)
 ,
 (14,'06020006','06','02','PQR','14','2016-04-23','Test Customer 06020006','123','HIJ','HYDERABAD','500086',NULL,0.5,'T','F','M',NULL,0,NULL,18,NULL,0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9989505111','0','0','0','0','0','0','2016-03-03',0,'123456','abc@abc.abc','ACTIVE',1,1,6,2,16),
 (15,'06020007','06','02','PQR','14','2016-04-23','Test Customer 06020007','123','HIJ','HYDERABAD','500086',NULL,0.5,'T','F','M',NULL,0,NULL,14,NULL,0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9989505111','0','0','0','0','0','0','2016-03-23',0,'123456','abc@abc.abc','ACTIVE',1,1,6,2,17),
 (16,'06020008','06','02','PQR','14','2016-04-23','Test Customer 06020008','123','HIJ','HYDERABAD','500086',NULL,0.5,'T','F','M',NULL,0,NULL,20,NULL,0,0,0,0,0,0,'0.0',1,0,0,NULL,0,'9989505111','0','0','0','0','0','0','2016-03-03',0,'123456','abc@abc.abc','ACTIVE',1,1,6,2,18);

