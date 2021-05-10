# Simple Rest Hello Word Program

Baseline hello rs example adapted to use jib docker plugin to produce docker images

#References

##JIB
[Google Official](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin)

## Build
````
mvn jib:dockerBuild
````

#RUN
##Default Run - profile set to jib docker
````
docker run --name bs1 -p18080:8080 baseline-jib
````

## Set Spring profile through environment
````
# Selected profile and bypassed env var
docker run --name bs1 -p18080:8080 --env "SPRING_PROFILES_ACTIVE=docker" --env "HELLO_VAR=Environ Bypassed Profile Hello" baseline-jib  
````

#TODO 
- Expose Debug Ports, attach debuger
- Test Containers