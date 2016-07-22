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

# Automated query for dropping meter_details
drop table adjustments; delete from databasechangelog where filename like '%_adjustments.xml';
drop table bill_run_details; delete from databasechangelog where filename like '%_billrundetails.xml';
drop table meter_change; delete from databasechangelog where filename like '%_meterchange.xml';
drop table bill_full_details; delete from databasechangelog where filename like '%_billfulldetails.xml';
drop table connection_terminate; delete from databasechangelog where filename like '%_connectionterminate.xml';
drop table cust_meter_mapping; delete from databasechangelog where filename like '%_custmetermapping.xml';
drop table cust_details; delete from databasechangelog where filename like '%_custdetails.xml';
drop table item_required; delete from databasechangelog where filename like '%_itemrequired.xml';
drop table feasibility_study; delete from databasechangelog where filename like '%_feasibilitystudy.xml';
drop table proceedings; delete from databasechangelog where filename like '%_proceedings.xml';
drop table receipt; delete from databasechangelog where filename like '%_receipt.xml';
drop table application_txn; delete from databasechangelog where filename like '%_applicationtxn.xml';
drop table meter_details; delete from databasechangelog where filename like '%_meterdetails.xml';
