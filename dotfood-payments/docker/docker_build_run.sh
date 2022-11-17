#!/bin/bash

########################################
# Add execute permission:
#		chmod +x docker_build.sh
#
# Create Database Contanier:
# 		./docker_build.sh -db
#
# Create Application Contanier: 
# 		./docker_build.sh -app
########################################

if [[ $# -eq 0 ]]; then
	echo 'No parameter supplied'
	exit 1
fi

############################
###### Bridge Network ######
############################

export NET_DOTFOOD=dotfood-network

net=$(docker network ls --filter "name=dotfood-network" -q)
if [ -z $net ]
then
	echo "Creating dotfood bridge network"
	docker network create -d bridge $NET_DOTFOOD;
fi

if [[ $1 == "-db" ]];then

	############################
	#### Database Container ####
	############################
	
	export IMAGE_DB_PAYMENTS=dotfood/db-payments:1.0
	export CONTAINER_DB_PAYMENTS=dotfood-db-payments

	container=$(docker ps --filter "name=$CONTAINER_DB_PAYMENTS" -q)
	if [ ! -z $container ]; 
	then
		docker rm -f $container
	fi
	
	echo "Creating MySQL database container"
	docker build -f db/Dockerfile -t $IMAGE_DB_PAYMENTS .
	docker run --name dotfood-db-payments -d \
		-e MYSQL_ROOT_PASSWORD=adminroot -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -e MYSQL_ROOT_HOST=% \
		-v $PWD/db/sql:/docker-entrypoint-initdb.d \
		-p 3306:3306 --network=$NET_DOTFOOD $IMAGE_DB_PAYMENTS

elif [[ $1 == "-app" ]];then

	#############################
	### Application Container ###
	#############################
	
	export IMAGE_APP_PAYMENTS=dotfood/app-payments:1.0
	export CONTAINER_APP_PAYMENTS=dotfood-app-payments
	export JAR_APP_PAYMENTS=dotfood-payments-0.0.1-SNAPSHOT.jar
	
	container=$(docker ps --filter "name=$CONTAINER_APP_PAYMENTS" -q)
	if [ ! -z $container ]; 
	then
		docker rm -f $container
	fi
	
	mvn install -f ../pom.xml
	
	echo "Creating Microservice Payments Application container"
	cp ../target/$JAR_APP_PAYMENTS app/
	cd app
	docker build -t $IMAGE_APP_PAYMENTS .
	docker run --name $CONTAINER_APP_PAYMENTS -d \
		-e MYSQL_HOST=dotfood-db-payments -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin \
		-p 8082:8082 --network=$NET_DOTFOOD $IMAGE_APP_PAYMENTS
else
	echo 'Invalid parameters'
	exit 1
fi