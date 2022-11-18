FROM bellsoft/liberica-openjdk-alpine:17
LABEL image="dotfood-gateway"
LABEL version="1.0"
LABEL description="Dotfood Eureka Gateway"
EXPOSE 8080
WORKDIR /dotfood-gateway
COPY target/dotfood-gateway-0.0.1-SNAPSHOT.jar .
ENTRYPOINT java -jar ./dotfood-gateway-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker