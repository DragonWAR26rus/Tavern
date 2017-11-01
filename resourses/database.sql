
DROP TABLE our_user CASCADE;
CREATE TABLE our_user (
    id serial primary key,
    login varchar(50) not null,
    password_hash varchar(32) not null,
    last_act varchar(20),
    email varchar(255) not null
);

DROP TABLE IF EXISTS platform;
CREATE TABLE platform (
    id serial primary key,
    domain varchar(50) not null,
    key varchar(50) not null,
    owner_id integer REFERENCES our_user(id)
);

DROP TABLE IF EXISTS platform_user;
CREATE TABLE platform_user (
    id serial primary key,
    name varchar(50) not null,
    avatar_link varchar(255),
    last_act varchar(20),
    banned boolean,
    platform_id integer REFERENCES platform(id),
    UNIQUE( name, platform_id )
);

DROP TABLE IF EXISTS message;
CREATE TABLE message (
    id serial primary key,
    sender_id integer REFERENCES platform_user(id),
    text text,
    send_time varchar(20),
    platform_id integer REFERENCES platform(id)
);