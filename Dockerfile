FROM openjdk:8-jre-alpine
RUN mkdir -p /home/kashitkala
RUN mkdir -p /home/kashitkala/logs
WORKDIR /home/kashitkala/ecom
RUN chmod -R 777 /home
WORKDIR /home/kashitkala/ecom
ADD target/brandmodelmgmt.jar /home/kashitkala/ecom
EXPOSE 8002
ENTRYPOINT ["java", "-Dserver.address=0.0.0.0", "-Dfile.encoding=UTF-8","-jar","/home/kashitkala/ecom/brandmodelmgmt.jar"]
#ENTRYPOINT ["java","-jar", "-Dfile.encoding=UTF-8" ,"/ArtthaCore.jar"]
#CMD ["/usr/bin/java", "-jar", "-Dfile.encoding=UTF-8", "-Dserver.address=0.0.0.0", "/home/arttha/arttha-loan/ArtthaLoan.jar"]
