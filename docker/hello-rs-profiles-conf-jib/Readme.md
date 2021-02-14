# Simple Rest Hello Word Program

Baseline hello rs example adapted to use jib docker plugin to produce docker images

#References

##JIB
[Google Official](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin)


#RUN
##Default Run
``
docker run -p18080:8080 jbprek/hello-rs 
``
##Bypass env var
``
docker run -p18080:8080  --env HELLO_MSG=ByPass jbprek/hello-rs
``

##Select Profile from the environment, bypass config property
``
docker run -p18080:8080  --env SPRING_PROFILES_ACTIVE=dev --env HELLO_MSG=ByPass jbprek/hello-rs
``

##Select Profile from the environment, clear the default value set from jib for HELLO_MSG
``
docker run -p18080:8080  --env SPRING_PROFILES_ACTIVE=dev --env HELLO_MSG jbprek/hello-rs
``
#TODO 
- Test Containers Test