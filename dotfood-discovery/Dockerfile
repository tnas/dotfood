FROM bellsoft/liberica-openjdk-alpine:17
LABEL image="dotfood-discovery"
LABEL version="1.0"
LABEL description="Dotfood Eureka Registry Server"
EXPOSE 8081
WORKDIR /dotfood-discovery
COPY target/dotfood-discovery-0.0.1-SNAPSHOT.jar .
ENTRYPOINT java -jar ./dotfood-discovery-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker