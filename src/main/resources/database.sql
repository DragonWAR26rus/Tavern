
DROP TABLE IF EXISTS our_user CASCADE;
CREATE TABLE our_user (
    id serial primary key,
    login varchar(50) not null,
    passwordHash varchar(32) not null,
    lastAct varchar(20),
    email varchar(255) not null
);

DROP TABLE IF EXISTS platform CASCADE;
CREATE TABLE platform (
    id serial primary key,
    domain varchar(50) not null,
    key varchar(50) not null,
    ownerId integer NOT NULL REFERENCES our_user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS platform_user CASCADE;
CREATE TABLE platform_user (
    id serial primary key,
    login varchar(50) not null,
    avatarlink varchar(255),
    lastAct varchar(20),
    banned boolean,
    platformId integer NOT NULL REFERENCES platform(id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE( login, platformId )
);

DROP TABLE IF EXISTS message CASCADE;
CREATE TABLE message (
    id serial primary key,
    senderId integer NOT NULL REFERENCES platform_user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    text text,
    sendTime varchar(20),
    platformId integer NOT NULL REFERENCES platform(id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into our_user(id, login, passwordHash, lastAct, email) values(111, 'Sergey', 'passhashSuperHash', '123', 'sergey.s.l@mail.ru');
insert into platform(id, domain, key, ownerId) values(111, 'seregaDomain', 'keeey', 111);
insert into platform_user(id, login, avatarlink, lastAct, banned, platformId) values(111,'seregaLogin', 'avavava', 123, false, 111);
insert into message(id, senderId, text, sendTime, platformId) values(111, 111, 'avasdadasdvava', 124, 111);