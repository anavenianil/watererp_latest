#!/bin/bash

#This facilitates run in Jenkins
echo "Running script from PWD:" `pwd`
##### Unit Testing ##############
#grunt test

sudo mvn clean initialize

message=$(grep git.commit.message.full target/classes/config/git.properties | sed -n -e "s/.*=//p")

set +e

if [ -n "$message" ]; then
	echo "Message for this version:" $message
	db=$(echo $message|grep "\[DB\]")
	echo "This is the db:" $db
	if [ -n "$db" ]; then
		echo "Message contains [DB], Restoring DB"
		mysql -u root -pmysql watererp < Docs/DB/watererp.sql
	else
		echo "Skipping DB script run"
	fi
fi

set -e

##### Integration Testing #######
#sudo SPRING_PROFILES_ACTIVE=fast mvn test
sudo SPRING_PROFILES_ACTIVE=fast mvn -Dtest=BillRunMasterResourceIntTest test
##### E2E Testing ###############
a=`ps -ef|grep java|grep spring-boot|awk '{print $2}'`
if [ -n "$a" ]; then
	echo "Killing:" $a
	sudo kill -9 $a
fi
pwd
sudo BUILD_ID=dontKillMe nohup mvn -Pfast spring-boot:run  > /var/log/jenkins/app.log&
#sleep 60
#npm install
#sudo grunt protractor-xvfb