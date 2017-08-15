SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

CREATE USER IF NOT EXISTS 'demo'@'localhost' IDENTIFIED BY 'demo';

GRANT ALL PRIVILEGES ON *.* TO 'demo'@'localhost';

DROP DATABASE IF EXISTS `chad`;
CREATE DATABASE `chad`;
USE chad;

CREATE TABLE IF NOT EXISTS person
(
	person_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(60) NOT NULL,
	last_name VARCHAR(60) NOT NULL,
	email VARCHAR(60) NOT NULL
)engine=InnoDB; 

INSERT INTO person VALUES
(NULL, 'Marian','Popandescu',"marian.popandescu@gmail.com"),
(NULL, 'Alexandra','Pisicescu',"alexandra.pisicescu@gmail.com"),
(NULL, 'Sorin','Evitescu',"s.evit@apple.com"),
(NULL, 'Catalin','Porcescu',"cata.pig@gmail.com"),
(NULL, 'Demetra','Vasilescu',"v.dem@yahoo.com"),
(NULL, 'Gigel','Infometescu',"mananc.panacrap@hotmail.com"),
(NULL, 'Andra','Pitiponcescu',"pisi.pitzi@glamour.com");

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;