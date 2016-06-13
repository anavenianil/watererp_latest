#!/bin/bash

#This facilitates run in Jenkins
echo "Running script from PWD:" `pwd`
##### Unit Testing ##############
#grunt test

sudo mvn clean initialize

message=$(grep git.commit.message.full target/classes/config/git.properties | sed -n -e "s/.*=//p")

set +e
#If commit message contains the word [DB], watererp.sql is run in Jenkins Test server
#If commit message contains the word [PATCH], patch.sql is run in Jenkins Test server
#If these keys are not found, no DB script is run.

if [ -n "$message" ]; then
	echo "Message for this version:" $message
	db=$(echo $message|grep "\[DB\]")
	echo "This is the db:" $db
	if [ -n "$db" ]; then
		echo "Message contains [DB], Restoring DB"
		mysql -u root -pmysql watererp < Docs/DB/watererp.sql
	else 
		patch=$(echo $message|grep "\[PATCH\]")
		echo "This is the patch:" $patch
		if [ -n "$patch" ]; then
			echo "Message contains [PATCH], Applying Patch"
			mysql -u root -pmysql watererp < Docs/DB/patch.sql			
		else
			echo "Skipping DB script run"
		fi
	fi
fi

set -e

##### Integration Testing #######
#sudo mvn -Dspring.profiles.active=fast  test
sudo mvn -Dspring.profiles.active=fast -Dtest=BillRunMasterResourceIntTest test
##### E2E Testing ###############
a=`ps -ef|grep java|grep spring-boot|awk '{print $2}'`
if [ -n "$a" ]; then
	echo "Killing:" $a
	sudo kill -9 $a
fi

#export BUILD_ID=dontKillMe
sudo nohup mvn -Pfast spring-boot:run  > /var/log/jenkins/app.log&
#sleep 60
#npm install
#sudo grunt protractor-xvfb