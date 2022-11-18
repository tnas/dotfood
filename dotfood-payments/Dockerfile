FROM bellsoft/liberica-openjdk-alpine:17
LABEL image="dotfood/app-payments"
LABEL version="1.0"
LABEL description="Payments Application"
EXPOSE 8082
WORKDIR /dotfood-payments
COPY target/dotfood-payments-0.0.1-SNAPSHOT.jar .
ENTRYPOINT java -jar ./dotfood-payments-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker