#!/bin/bash

export NET_DOTFOOD=dotfood-network

export IMAGE_DB_PAYMENTS=dotfood/db-payments:1.0
export CONTAINER_DB_PAYMENTS=dotfood-db-payments

export IMAGE_APP_PAYMENTS=dotfood/app-payments:1.0
export CONTAINER_APP_PAYMENTS=dotfood-app-payments
export JAR_APP_PAYMENTS=dotfood-payments-0.0.1-SNAPSHOT.jar

net=$(docker network ls --filter "name=dotfood-network" -q)
if [ -z $net ]
then
	echo "Creating dotfood bridge network"
	docker network create -d bridge $NET_DOTFOOD;
fi

container=$(docker ps --filter "name=$CONTAINER_DB_PAYMENTS" -q)
if [ ! -z $container ]; 
then
	docker rm -f $container
fi	 

echo "Creating MySQL database container"
docker build -f db/Dockerfile -t $IMAGE_DB_PAYMENTS .
docker run --name dotfood-db-payments -d \
	-e MYSQL_ROOT_PASSWORD=adminroot -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin \
	-p 3306:3306 --network=$NET_DOTFOOD $IMAGE_DB_PAYMENTS

container=$(docker ps --filter "name=$CONTAINER_APP_PAYMENTS" -q)
if [ ! -z $container ]; 
then
	docker rm -f $container
fi

echo "Creating Microservice Payments Application container"
cp ../target/$JAR_APP_PAYMENTS app/
cd app
docker build -t $IMAGE_APP_PAYMENTS .
docker run --name $CONTAINER_APP_PAYMENTS -d \
	-p 8082:8082 --network=$NET_DOTFOOD $IMAGE_APP_PAYMENTS