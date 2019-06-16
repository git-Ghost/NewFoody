CREATE TABLE IF NOT EXISTS FOODY_STARBUCKS_MENU (ITEM_ID INT AUTO_INCREMENT, 
ITEM_NAME VARCHAR(65) NOT NULL UNIQUE,
ITEM_SRC VARCHAR(180) NOT NULL UNIQUE,
CURRENCY_CODE VARCHAR(3),
ITEM_PRICE FLOAT(6,2) NOT NULL UNIQUE,
primary KEY(ITEM_ID));

INSERT INTO FOODY_STARBUCKS_MENU ( ITEM_ID, ITEM_NAME,ITEM_SRC,CURRENCY_CODE,ITEM_PRICE) VALUES 
(1,'Vanilla Bean Crème Frappuccino', 
'https://globalassets.starbucks.com/assets/574bb3dfc4ff4294b172301f0848e17f.jpg',
'INR', 899.99);

INSERT INTO FOODY_STARBUCKS_MENU ( ITEM_ID, ITEM_NAME,ITEM_SRC,CURRENCY_CODE,ITEM_PRICE) VALUES 
(2,'Biscotti Frappuccino', 
'http://starbucksrecipes.com/wp-content/uploads/2018/04/25-recetas-secretas-de-starbucks-2.jpg',
'INR', 1500.95);


INSERT INTO FOODY_STARBUCKS_MENU ( ITEM_ID, ITEM_NAME,ITEM_SRC,CURRENCY_CODE,ITEM_PRICE) VALUES 
(3,'Strawberries & Crème Frappuccino', 
'https://www.biggerbolderbaking.com/wp-content/uploads/2017/01/1C5A9534.jpg',
'INR', 1099.99);

INSERT INTO FOODY_STARBUCKS_MENU ( ITEM_ID, ITEM_NAME,ITEM_SRC,CURRENCY_CODE,ITEM_PRICE) VALUES 
(4,'Iced Skinny Hazlenut Latte', 
'https://i.pinimg.com/originals/ea/19/76/ea1976ab6dba83fdc31019555d6a9725.jpg',
'INR',899.56 );
