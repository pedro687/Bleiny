CREATE TABLE IF NOT EXISTS users(
    id bigint(20) auto_increment primary key,
    uuid varchar(150) unique not null,
    username varchar(25) not null,
    email varchar(255) unique not null,
    password varchar(255) not null,
    tellphone varchar(11) not null,
    image varchar(255) default null
    );

CREATE TABLE IF NOT EXISTS address(
    id bigint(20) auto_increment primary key,
    uf varchar(2) not null,
    city varchar(25) not null,
    country varchar(20) not null,
    user_id bigint(20) not null,
    foreign key(user_id) references users(id)
);