FROM openjdk:8u191-jre-alpine

#Installing curl and jq
RUN apk add curl jq

#Workspace
WORKDIR /usr/share/udemy

#Add .jar files under target from host
ADD target/selenium-docker.jar        selenium-docker.jar
ADD target/selenium-docker-tests.jar   selenium-docker-tests.jar
ADD target/libs       libs

# ADD Suite files
ADD bookflightmodule.xml    bookflightmodule.xml
ADD searchmodule.xml   searchmodule.xml

# Docker Hub HealthCheck
#ADD healthcheck.sh      healthcheck.sh
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh

# Entrypoint changed as moved this part to healthcheck.sh
#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE

ENTRYPOINT sh healthcheck.sh
