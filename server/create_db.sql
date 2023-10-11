CREATE DATABASE gymmembership;

USE gymmembership;

CREATE TABLE `membership_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `duration` varchar(255) DEFAULT NULL,
  `fee` int DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int DEFAULT '0',
  `birthday` date DEFAULT NULL,
  `disable` bit(1) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `join_date` date DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `terms_and_condition` bit(1) DEFAULT NULL,
  `membershiptype_id_fk` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`membershiptype_id_fk`) REFERENCES `membership_type`(`id`)
);

CREATE TABLE `utility_configuration` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `config` varchar(255) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO gymmembership.membership_type(duration, fee, type) 
VALUES (1, 50, 'Daily');
INSERT INTO gymmembership.membership_type(duration, fee, type) 
VALUES (30, 500, 'Monthly');
INSERT INTO gymmembership.membership_type(duration, fee, type) 
VALUES (365, 5000, 'Yearly');

INSERT INTO gymmembership.user(age, birthday, disable, expiration_date, first_name, join_date, last_name, terms_and_condition, membershiptype_id_fk)
VALUES('25', '1997-09-23', false, '2024-04-26', 'test103', '2023-04-27', 'test103', true, '1');

INSERT INTO gymmembership.utility_configuration(config, module, name)
VALUES('fitness center', 'frontend','title');