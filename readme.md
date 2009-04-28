BF Run Club - Strava Activity Comparer
--

Web App and Cli to query activities from Strava official REST APIs.
API can only be called once end User has authorised app via OAuth2

**Web App**
* UI to display activities 

**CLI**
* queries activities from Strava API 
* runs every miniute via Crontab 

**Tech Stack**
* Java 13
* Postgresql 10
* Tomcat 9
* Ubuntu 18

**Production**
* bfrunclub.com
* AWS EC2 Ubuntu 18 t2.micro

**How to Deploy**

Web App
* Run Ant Build artifact.stravacustom:war_exploded_prod
* Manually copy files generated artifact to prod.  

Cli
* Run Ant Build artifact.stravacustom:jar
* Manually copy files generated artifact to prod.  


**Notes**
* Uses OAuth2 
* Strava API only allows for one application account.  So that's only one domain per app.
* Add host file entry to point bfrunclub.com to localhost for development


**Run locally**
