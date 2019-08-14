CREATE TABLE IF NOT EXISTS FOODY_USERS(
	CID INT AUTO_INCREMENT,
	CNAME VARCHAR(20) NOT NULL,
	EMAIL VARCHAR(20) NOT NULL ,
	PASSWORD VARCHAR(10) NOT NULL,
	DOJ DATE not null, 
	ADDRESS VARCHAR(100),
	PRIMARY KEY(CID),
	UNIQUE KEY EMAIL_UQ(EMAIL)
);