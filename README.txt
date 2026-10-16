ONLINE FOOD ORDERING SYSTEM - SETUP (WINDOWS)

Stack: Java 8, Maven, Java Servlets, JSP, JDBC, MySQL 8, Tomcat 9.
This is a separate project. It does not use or modify the Student Task Manager.

1. DATABASE
   - Start the MySQL80 Windows service.
   - Open MySQL Workbench or the MySQL command line.
   - Run the full database.sql script.
   - It creates online_food_ordering and sample food menu rows.

2. SET THE DATABASE PASSWORD
   Open:
   src/main/java/com/foodordering/util/DBConnection.java
   Replace YOUR_MYSQL_PASSWORD with your own local MySQL root password.
   Do not share the password or upload it publicly.

3. BUILD
   Open PowerShell in the project root (the folder containing pom.xml):
     mvn clean package
   On success, the WAR is:
     target\online-food-ordering-system.war

4. DEPLOY TO YOUR USER-OWNED TOMCAT (PORT 8081)
   Copy the WAR to:
     C:\Users\SOHAM\tomcat\apache-tomcat-9.0.122\webapps\online-food-ordering-system.war
   If the extracted Tomcat instance is already running, it normally deploys the WAR automatically.
   Otherwise start it in PowerShell with:
     $env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-8.0.504.1-hotspot"
     $env:Path="$env:JAVA_HOME\bin;$env:Path"
     $env:CATALINA_HOME="$env:USERPROFILE\tomcat\apache-tomcat-9.0.122"
     & "$env:CATALINA_HOME\bin\startup.bat"

5. OPEN
   http://localhost:8081/online-food-ordering-system/
   The app redirects to /menu.

6. TEST
   - Add one or more menu items to cart.
   - Remove an item if desired.
   - Checkout and enter guest name, phone, and address.
   - Place order.
   - In MySQL run:
       USE online_food_ordering;
       SELECT * FROM customer_orders;
       SELECT * FROM order_items;

TROUBLESHOOTING
- If Maven fails because the network is unavailable, Maven may need to download dependencies once.
- If the site reports a database error, verify MySQL80 is running, database.sql has been executed,
  and the password in DBConnection.java is correct.
- If Tomcat's Windows service already uses port 8080, use the separate user-owned Tomcat configured
  for port 8081 as above.
- Check the user-owned Tomcat logs in:
  C:\Users\SOHAM\tomcat\apache-tomcat-9.0.122\logs
- This project records orders only; it does not integrate a real payment gateway.
