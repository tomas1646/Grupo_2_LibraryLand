FROM openjdk:11

COPY /target/libraryland-0.0.1-SNAPSHOT.jar /tmp/prueba.jar

WORKDIR /tmp

CMD ["java","-jar", "prueba.jar"]