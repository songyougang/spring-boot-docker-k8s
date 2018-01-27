FROM 192.168.147.11:5000/common/jdk8
VOLUME /tmp
RUN sh -c 'touch /app.jar'
ADD spring-boot-docker-k8s-login-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]