CREATE TABLE IF NOT EXISTS FOODY_KFC_MENU (ITEM_ID INT AUTO_INCREMENT, 
ITEM_NAME VARCHAR(65) NOT NULL UNIQUE,
ITEM_SRC VARCHAR(180) NOT NULL UNIQUE,
CURRENCY_CODE VARCHAR(3),
ITEM_PRICE FLOAT(6,2) NOT NULL UNIQUE,
primary KEY(ITEM_ID));

INSERT INTO FOODY_KFC_MENU(ITEM_ID,ITEM_NAME,ITEM_SRC,CURRENCY_CODE,ITEM_PRICE) VALUES
(1,
'15 Pc Smoky Red Wings' , 
'https://res.cloudinary.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_366,h_240,c_fill/ukzo6y6ylz8k78n26qgz',
'INR', 399.50);

INSERT INTO FOODY_KFC_MENU(ITEM_ID,ITEM_NAME,ITEM_SRC,CURRENCY_CODE,ITEM_PRICE) VALUES
(2,
'Friendship Bucket Meal' , 
'https://res.cloudinary.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_366,h_240,c_fill/tjohaxaepkxjnzdrsgru',
'INR', 700);

INSERT INTO FOODY_KFC_MENU(ITEM_ID,ITEM_NAME,ITEM_SRC,CURRENCY_CODE,ITEM_PRICE) VALUES
(3,
'5 in 1 Zinger Box' , 
'https://res.cloudinary.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_366,h_240,c_fill/xazk5r8tc0mj90973grs',
'INR', 239);

INSERT INTO FOODY_KFC_MENU( ITEM_ID, ITEM_NAME,ITEM_SRC,CURRENCY_CODE,ITEM_PRICE) VALUES 
(4,'Special Burger' , 
'https://media.bizj.us/view/img/11129447/cwprsandwichcrop*750xx2500-1406-0-297.jpg',
'INR', 210.80);
