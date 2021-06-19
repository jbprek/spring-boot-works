# Simple Rest Hello Word Program

Baseline hello rs example adapted to use spring boot docker plugin to produce docker images

#References

##Spring Docker docs
[Spring Topical Guidel](https://spring.io/guides/topicals/spring-boot-docker/)

## Build
````
mvn spring-boot:build-image
````

#RUN
##Default Run - profile set to jib docker
````
docker run --name bs1 -p18080:8080 baseline-jib
````

## Set Spring profile through environment
````
# Example 1
# Selected profile and bypassed env var
docker run --name bs1 -p18080:8080 --env "SPRING_PROFILES_ACTIVE=docker" --env "HELLO_VAR=Environ Bypassed Profile Hello" baseline-jib  
````

````
# Example 2
# Selected profile and bypassed spring property through the environment
docker run --name bs1 -p18080:8080 --env "SPRING_PROFILES_ACTIVE=docker" --env "COM_FOO_SECRET=Environ Bypassed Profile Secret" baseline-jib  
````

# Tag image for pushing in DockerHub
````
docker tag baseline-jib jbprek/baseline-jib
````
#TODO 
- Expose Debug Ports, attach debuger
- Test Containers