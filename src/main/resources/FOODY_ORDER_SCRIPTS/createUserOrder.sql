create table if not exists FOODY_USER_ORDERS (
ORDER_ID VARCHAR(19) , 
CID INT ,
ORDER_DATE DATE not null, 
ORDER_TIME TIME not null,
CURRENCY_CODE varchar(3) not null,
ORDER_AMOUT float(7,2) not null,
PRIMARY KEY ( ORDER_ID ),
FOREIGN KEY (CID) REFERENCES foody_users(CID) ON DELETE CASCADE ON UPDATE CASCADE );
