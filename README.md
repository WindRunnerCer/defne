Prequisite 
Lombok plugin
Maven and Java 1.8 is used.

You can test the application via 
Swagger http://localhost:8080/swagger-ui.html


For checking db connection
http://localhost:8080/console/login.do
Connection Info
Driver Class : org.h2.Driver
JDBC URL : jdbc:h2:mem:testdb
User Name : sa
Password : 

Missing Pieces:
Unfortunately there is some missing parts which i should declare:
 Client authentication did not implemented.Instead of this you should give your client id in your requests when needed.
 Only reports that have been specified as per client gives data specific to the client. Others give the sum of all clients.  
 Each client have a daily quota but control about it did not implemented.
 Maximum message length can be bigger than 1024. Control did not implemented.
 
 
 
 
