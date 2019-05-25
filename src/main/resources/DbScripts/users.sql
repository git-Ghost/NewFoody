CREATE DATABASE IF NOT EXISTS AWSMYSQL 
CREATE TABLE IF NOT EXISTS 'USERS' (

	'CID' INT AUTO_INCREMENT,
	'CNAME' VARCHAR(20),
	'EMAIL' VARCHAR(20),
	'PASSWORD' VARCHAR(10),
	PRIMARY KEY(CID),
	UNIQUE KEY 'EMAIL_UQ'('EMAIL')
);