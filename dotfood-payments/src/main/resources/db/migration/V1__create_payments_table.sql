CREATE TABLE payments (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	total decimal(19,2) NOT NULL,
 	name varchar(100) DEFAULT NULL,
 	number varchar(19) DEFAULT NULL,
 	expire varchar(7) DEFAULT NULL,
 	code varchar(3) DEFAULT NULL,
 	status varchar(255) NOT NULL,
 	order_id bigint NOT NULL,
 	payment_method_id bigint NOT NULL
);