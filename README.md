## Welcome! 👋

Thanks for checking out my side project regarding Gym Membership web application. This is currently not hosted (ongoing) but can be run locally.

## Information

Framework/Tools used:

-   Angular
-   Springboot
-   MySQL

Programming Language used:

-   Typescript
-   HTML
-   CSS
-   Java
-   SQL

## Steps to run the program

1. setup MySQL localhost
2. run script 'create_db.sql'
3. git clone https://github.com/IvanBagsit/GymMembership
4. open server folder (Back End) in IntelliJ
5. enter vm options in run config:
   -DDB_USER=root
   -DDB_PASS=Pandesal123
   -DDB_HOST=localhost
   -DDB_NAME=gymmembership
   -DSPRING_PROFILES_ACTIVE=local
   -DSERVER_PORT=8080
   -DSERVER_CONTEXT_PATH=/gymmembership
6. run
7. open client folder (Front End) in VS Code
8. npm i
9. npm start
10. go to http://localhost:4200/home
