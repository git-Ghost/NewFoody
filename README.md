# NewFoody

Maven Project of earlier Dynamic Web Project. Foody WebApplication initiated by Akash which later taken by me for more backend integration.
This Project is also headed by Debasish (Testing team) with their own selenium test integration added to it.

To Use this Application take a latest checkout from the branch master with Tomcat server pre-installed and the run application in that. Modules currently availble for this application are :

1. Registration
2. Login - In Progress

Tools Used :
   1. Spring MVC 
   2. Hibernate - For DB Interaction
   3. Servlet - For intermediate Servicing logic
   4. MySQL 5.x - DataBase
   5. AWS - For Hosting the WebApp
   6. Tomcat 9.x - Server Hosting

To Use this application, get the latest checkout from the master branch.

   1. Change the context.xml File contents with the db cred, username, password, connecting string and schema name. [which will help in connection pooling]
   2. Provide the log directory path, which will be configured with log4j.properties (located at : src/main/webapp/WEB-INF/classes folder)
   3. FOODY_USERS Table must be present in the DB at least as this won't be created automatically via code 
   4. TimeZone Setting need to be configured as per the timeZone, found in conf.json file (located at : src/main/resources) [NB: PST/IST won't accepeted instead use Asia/kolkata example]

Post checkout get the war file for the project and deploy in installed Tomcat Server
