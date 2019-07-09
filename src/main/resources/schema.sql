CREATE TABLE if not exists bento_order(
    id SERIAL NOT NULL,
    order_date date NOT NULL,
    name varchar(10) NOT NULL,
    bento_id int NOT NULL,
    rice_id int NOT NULL,
    arrival date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists bento(
    id int NOT NULL,
    name varchar(10) NOT NULL,
    price int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists rice(
    id int NOT NULL,
    availability varchar(10) NOT NULL,
    price int NOT NULL,
    PRIMARY KEY (id)
);
