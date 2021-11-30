# An Authentication service in Spring Boot.
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


# Screenshots
### Invalid Login
![image](https://user-images.githubusercontent.com/31609389/144047806-86d6f023-fda9-470f-9065-9412a764bf4a.png)


### User already exists
![image](https://user-images.githubusercontent.com/31609389/144047908-459e006c-d85e-413c-a291-1b381b349228.png)

### Invalid Token
![image](https://user-images.githubusercontent.com/31609389/144048117-a6fb13fe-6b94-439f-baf8-d696d7f9eebe.png)

### JWT (This can only be verified from the server-side. Frontend can't read the value)
![image](https://user-images.githubusercontent.com/31609389/144048380-c40ea632-888f-4d5a-bcac-1dc8b0e69b79.png)

Happy coding :)
