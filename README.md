# microservices-with-springboot
Student Microservices and Fees Microservices with Discovery Server, API Gateway and Zipkin Server

Architecture diagram
====================
![Microservice Architecture](https://github.com/prajeeshkv/microservices-with-springboot/assets/6111656/f7a3ff99-e861-47be-8a83-37f144fcea15)


Database Structure
==================
![Database table structure](https://github.com/prajeeshkv/microservices-with-springboot/assets/6111656/988ed548-df35-414b-aa0e-8c6422c0b68d)

Receipt with Thymeleaf
==================
<img width="809" alt="image" src="https://github.com/prajeeshkv/microservices-with-springboot/assets/6111656/2e862fe7-db1e-4c96-99dc-9180b6b522ab">

This microservices project is for student management and fee collection management

Components of this project 4 Microservices 
1. Student Service
2. Fees Service
3. Discovery/Eureka Server Service
4. Gateway Server Service 

and
5. Zipkin server is used for distributed tracing
	We can see the logging for each request
<img width="957" alt="zipkin-trace-1" src="https://github.com/prajeeshkv/microservices-with-springboot/assets/6111656/a5b30996-41e2-40c8-9139-497263fd4943">



Description and used different components in this project
------------------------------------------------------------
Student Service Microservice is used to save/update/different fetch for Student details
Fee Service is used for payment of a student and Generating receipt

1.For intercommunication we used : Webclient and restTemplate 
	Save Fees     -> inter micro communication using RestTemplate (for fetching sudent details)
	Get Receipt   -> inter micro communication using WebClient  (for fetching sudent details)
2. Thymeleaf Receipt generation 
3. Database : H2
4. mapstruct is used to mapp entity to dto and vicecersa
5. Dto class is used not to expose the sensitive entity to outside
6. Swagger is used for documentation
7. resilience4j is used for circuitbreaker and retry
8. log4j is used for loggin
9. Global exception handling is used to handle the exceptions
10. Validator is uesed for validation
11. Audit entity is used to provide additional dates fields to the entity


Technologies used while creating the project
--------------------------------------------- 
Java : 17
Spring boot : 3.2.6
spring cloud version : 2023.0.1
Maven : 3.9.7

microservice ports
------------------- 
STUDENT-SERVICE   : 9090
FEE-SERVICE       : 8082
DISCOVERY-SERVICE : 8761
GATEWAY-SERVICE   : 9191

Urls
----- 
url for DISCOVERY-SERVICE : http://localhost:8761/
url for STUDENT-SERVICE swagger-ui : http://localhost:9090/swagger-ui/index.html#/
url for STUDENT-SERVICE H2 db :  http://localhost:9090/h2-ui
url for FEE-SERVICE swagger-ui : http://localhost:8082/swagger-ui/index.html
url for FEE-SERVICE H2 db : localhost:8082/h2-ui

in browser
Thymeleaf for getting receipt : localhost:8082/api/fees/v1/receipt/1


Zipkin
------- 
Zipkin download : https://zipkin.io/pages/quickstart.html
	- Download the zipkin jar and run using  command
	- java -jar zipkin-server-3.4.0-exec.jar
Zipkin Version  : 3.4.0  
Zipkin url      : http://127.0.0.1:9411/



Files provided
---------------
1. Microservice Architecture
2. Postman collection for differnt APIs
3. Database table structure
4. H2 Database Connection image
5. Spring boot run configuration


Project Setup
==============

pre requisite
---------------
Java 11
Maven : 3.9.7
IntelliJ IDEA 2024.1.2 (Community Edition) Build #IC-241.17011.79, built on May 22, 2024
git latest version if you are cloning
postman  v11.1.14 / or use swagger

Steps
------
1. Git clone or download the project
2. Run the zipkin server
	go to path 'microservices-with-springboot\Supporting_Documents\Zipkin' and and run the zipkin using below command
	java -jar zipkin-server-3.4.0-exec.jar
3. If dont have intellij, download and install intellij
4. open intellij and import the project (microservices-with-springboot\Microservices\ -> in this location we  
   have all 4 project)
	File -> Open -> browse location and select the 'discovery-service' project -> ok
5. configure the project
	Run -> Edit configurations -> add new configuration (+) -> Select maven -> in the input field under Run Label
	type 'spring-boot:run' (will autopopulate) -> apply and run
<img width="599" alt="spring-boot run" src="https://github.com/prajeeshkv/microservices-with-springboot/assets/6111656/81a96d6f-6c14-41f1-8cd8-0d257d2d7be2">

import and run the remaining 3 project same way (step 4 and 5)
Order:
1. discovery-service
2. gateway-service
3. student-service
4. fees-service
Now all the project is running

Check Type below urls in  browser
1.  http://localhost:8761/   (This is for eureka server)
2.  http://localhost:9090/swagger-ui/index.html#/  (this is for showing STUDENT-SERVICE swagger-ui )
3.  http://localhost:8082/swagger-ui/index.html (this is for showing FEE-SERVICE swagger-ui )
4.  http://localhost:9090/h2-ui ( this is for  STUDENT-SERVICE H2 db)
     Then will prompt for credential type it correctly as shown below (or reffer application.yml)
      JDBC URL: jdbc:h2:~/studentdb
      User Name: sa
      Password: 
5.  http://localhost:8082/h2-ui ( this is for  FEE-SERVICE H2 db )
     Then will prompt for credential type it correctly as shown below(or reffer application.yml) 
      JDBC URL: jdbc:h2:~/feesdb
      User Name: sa
      Password: 
![H2 Database Connection](https://github.com/prajeeshkv/microservices-with-springboot/assets/6111656/fb04726b-216e-41ba-972e-142da3964362)

6. Install postman ( v11.1.14) 
 Import the postman collection to postman (collection location : microservices-with-springboot\Supporting Documents\Postman collections)
 Left side click any tab say 'collection'  -> right side you can see import browse or drag and drop


   


