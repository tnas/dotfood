FROM bellsoft/liberica-openjdk-alpine:17
LABEL image="dotfood/app-orders"
LABEL version="1.0"
LABEL description="Orders Application"
EXPOSE 8083
WORKDIR /dotfood-orders
COPY target/dotfood-orders-0.0.1-SNAPSHOT.jar .
ENTRYPOINT java -jar ./dotfood-orders-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker