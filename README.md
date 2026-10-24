Library Management System

1. Extract this ZIP and open the folder containing pom.xml in VS Code.
2. Open src/main/resources/application.properties and replace YOUR_MYSQL_PASSWORD with your MySQL root password.
3. Start MySQL Server.
4. In the VS Code terminal, run: mvn spring-boot:run
5. Open http://localhost:8080/library

Requires Java 17, Maven, and MySQL. The app creates library_db and the table automatically if the configured MySQL user has permission. Dropdown entries are sample values editable in LibraryController.java.
