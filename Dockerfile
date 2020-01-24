# Extending everything from docker tomcat 8.0 image
FROM tomcat:8.0 -jre8

MAINTAINER Gourav

# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
COPY target/LibraryUser.war /usr/local/tomcat/webapps/