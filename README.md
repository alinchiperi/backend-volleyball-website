# backend



## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/ro/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

## Steps to run from IDE
1. Open project in your IDE(IntelliJ IDEA or Community)
2. Wait until maven download all dependiences 
3. Execue the `main` method in the `ro.usv.ip.IpApplication` class from your IDE (Run Button)
4. The server is accessible via Localhost:8080


## Steps to run from CLI
Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

1. Build the project using `mvn clean install`
2. Run using `mvn spring-boot:run` 
3. The web application is accessible via localhost:8080
