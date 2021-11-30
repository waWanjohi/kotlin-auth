# A simple Authentication service in Spring Boot.
#### Implemented in Kotlin 

# Recommended Tools:
  ### 1. Jetbrains IntellijIDEA
  ### 2. Insomnia/Postman REST Client
  <br><br>
## Setting up:
Make sure you have a working MySQL.
1. Clone the repo:
```bash
git clone https://github.com/waWanjohi/kotlin-auth
```
<br>
2. Open  <code>application.properies</code>  and edit depending on your database settings:

```java
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/NAME-OF-YOUR-DATABASE
spring.datasource.username=YOUR-USERNAME
spring.datasource.password=YOUR-PASSWORD
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true

server.port=9000
```
3. Build the project and run

Happy coding :)