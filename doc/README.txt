you can build the project with:
mvn clean install

Than you can run it with 

java -jar target/javulna-1.0-SNAPSHOT.jar

This will start an embedded Tomcat, and run the app
If you want to change the port of the embedded Tomcat to 8089 (default is 8080):

java -jar target/javulna-1.0-SNAPSHOT.jar --server.port=8089

If you want to debug it:

java -Xdebug -Xrunjdwp:server=y,transport=dt_socket_address=5005,suspend=n -jar target/javulna-1.0-SNAPSHOT.jar
