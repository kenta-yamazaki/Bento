CREATE TABLE if not exists bento_order(
    order_id SERIAL NOT NULL,
    order_date date NOT NULL,
    order_name varchar(10) NOT NULL,
    order_menu int NOT NULL,
    order_rice int NOT NULL,
    order_arrival date NOT NULL,
    PRIMARY KEY (order_id)
);

CREATE TABLE if not exists bento_info(
    bento_id int NOT NULL,
    bento_name varchar(10) NOT NULL,
    bento_price int NOT NULL,
    PRIMARY KEY (bento_id)
);

CREATE TABLE if not exists rice_info(
    rice_id int NOT NULL,
    rice_exists varchar(10) NOT NULL,
    rice_price int NOT NULL,
    PRIMARY KEY (rice_id)
);
