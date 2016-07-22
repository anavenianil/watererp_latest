#README for Jenkins Setup

##For Linux
    sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
    sudo rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key
    sudo yum install jenkins


##Jenkins Configuration
- During first install enable all plugins suggested.
- First install JDK, ANT, Maven vi Setup Tools
- Tools will only get installed when you do a first dummy build
- Install additional plugins like ..., etc.,
- Configure New Item as Maven Project
- Setup BitBucket via Git
- Setup SCM Configuration: H/15 * * * * *
- Give goal as compile or Execute Script:
sudo /bin/sh -xe /var/lib/jenkins/workspace/watererp/run.sh
- Post build actions, Execute script:
sudo /bin/sh -xe /var/lib/jenkins/workspace/watererp/run.sh

##Jenkins Configuration for running script
Add this to /etc/sudoers file

jenkins ALL = NOPASSWD: /bin/sh -xe /var/lib/jenkins/workspace/watererp/run.sh

##Installing NodeJS, Grunt, Bower, etc.,
curl --silent --location https://rpm.nodesource.com/setup_6.x | bash -
su -
yum clean all
yum list nodejs

npm install -g grunt
npm install -g grunt-cli
npm install grunt --save-dev

$ grunt test
Loading "Gruntfile.js" tasks...ERROR
>> Error: Cannot find module 'xml2js'
Warning: Task "test" not found. Use --force to continue.


srini@Lenovo-PC MINGW32 /D/projects/watererp (bhaskar)
$ grunt test
Loading "Gruntfile.js" tasks...ERROR
>> Error: Cannot find module 'load-grunt-tasks'
Warning: Task "test" not found. Use --force to continue.


$npm install

$npm install -g webdriver-manager

$bower install && webdriver-manager update

Configuration required for Jenkins CI
Execute shell using this command in Jenkins config

sudo /bin/sh -xe /var/lib/jenkins/workspace/DOI/run.sh
Add this to /etc/sudoers file

jenkins ALL = NOPASSWD: /bin/sh -xe /var/lib/jenkins/workspace/DOI/run.sh
Running Java Test in Debug mode

/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/Maven/bin/mvn -Dmaven.surefire.debug test
Running test with fast profile

SPRING_PROFILES_ACTIVE=dev,fast /var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/Maven/bin/mvn test OR SPRING_PROFILES_ACTIVE=dev,fast mvn test
Code coverage for Java

SPRING_PROFILES_ACTIVE=fast /var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/Maven/bin/mvn -Dmaven.test.failure.ignore=true test - Coverage report will be in: DOI/target/test-results/coverage/jacoco
Code coverage for Javascript

grunt test - Coverage report will be in: DOI/src/coverage/PhantomJS\ 1.9.8\ (Linux\ 0.0.0)/
