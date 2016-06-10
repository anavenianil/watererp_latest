#This facilitates run in Jenkins
echo "Running script from PWD:" `pwd`
##### Unit Testing ##############
#grunt test

##### Integration Testing #######
#sudo SPRING_PROFILES_ACTIVE=fast ../../../tools/hudson.tasks.Maven_MavenInstallation/Maven/bin/mvn -Dmaven.test.failure.ignore=true test
sudo SPRING_PROFILES_ACTIVE=fast ../../../tools/hudson.tasks.Maven_MavenInstallation/Maven/bin/mvn -Dmaven.test.failure.ignore=true  -Dtest=BillRunMasterResourceIntTest test
##### E2E Testing ###############
a=`ps -ef|grep java|grep spring-boot|awk '{print $2}'`
if [ -n "$a" ]; then
	echo "Killing:" $a
	sudo kill -9 $a
fi
pwd
sudo BUILD_ID=dontKillMe nohup ../../../tools/hudson.tasks.Maven_MavenInstallation/Maven/bin/mvn -Pfast spring-boot:run  > /var/log/jenkins/app.log&
#sleep 60
#/var/lib/jenkins/tools/jenkins.plugins.nodejs.tools.NodeJSInstallation/Node/bin/npm install
#sudo grunt protractor-xvfb