#inicia con la imagen base que contiene Java runtime
FROM openjdk:17-jdk-alpine

# se cambia el jar del microservicio a imagen
COPY target/Prueba2-0.0.1-SNAPSHOT.jar Prueba2-0.0.1-SNAPSHOT.jar

#se ejecuta el microservicio
ENTRYPOINT ["java","-jar","/Prueba2-0.0.1-SNAPSHOT.jar"]