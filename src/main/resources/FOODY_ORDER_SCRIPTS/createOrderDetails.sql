CREATE TABLE IF NOT EXISTS FOODY_ORDER_DETAILS(
ORDER_SEQ int AUTO_INCREMENT,
ORDER_ID VARCHAR(19) , 
ORDERED_ITEM varchar(65),
ORDERED_QUANTITY int,
ITEM_UNIT_PRICE float(7,2) NOT null,
PRIMARY KEY ( ORDER_SEQ ),
FOREIGN KEY (ORDER_ID) REFERENCES FOODY_USER_ORDERS(ORDER_ID) ON DELETE CASCADE ON UPDATE CASCADE);