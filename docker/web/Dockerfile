FROM tomcat:jdk11-corretto
MAINTAINER lpradel

RUN echo "export JAVA_OPTS=\"-Dapp.env=strava\"" > /usr/local/tomcat/bin/setenv.sh
COPY out/artifacts/StravaCustom_war/StravaCustom_war.war /usr/local/tomcat/webapps/strava.war
COPY docker/web/server.xml /usr/local/tomcat/conf/

CMD ["catalina.sh", "run"]