# Introduction

This repository has 3 folders:

- **word-count-api:** Contains the code to run the microservice.
- **RestAssuredTest:** Contains the code to run the api tests to cover word-count-api. Also it has the jenkinsfile to run the pipeline job on Jenkins.
- **PerformanceTest:** Contains the jmeter script and jenkinsfile to run the performance tests on Jenkins.

## Setup jenkins and Run microservice:
1- Install the jenkins server on local machine(i.e. localhost:8080)
2- Run the word-count-api microservice: mvn spring-boot:run
3- This will run the microservice on  localhost:8081

## Run RestAssuredTest
### Tools Used: 
- Java
- Maven
- RestAssured
- Serenity
- testNG

### How to Run
- Create a pipeline job and select Pipeline script from SCM option under Pipeline.
- Provide URL and master branch.
- Under Script Path, select RestAssuredTest/Jenkinsfile-rest-assured-test
- Click on Build.
- Serenity Report is generated which can be viewed after clicking serenity link on the left side.

# Performance Test

### Tools Used: 
- JMeter
- Taurus

### How to Run
- Create a pipeline job and select Pipeline script from SCM option under Pipeline.
- Provide URL and master branch.
- Under Script Path, select performance/Jenkinsfile-performance-test
- Click on Build.
- After running the job, performace trend graph is generated.

