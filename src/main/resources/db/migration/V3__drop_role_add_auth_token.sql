DROP TABLE user_roles;

DROP TABLE role;

DROP TABLE "user";

CREATE SEQUENCE hibernate_sequence start 1 increment 1;

CREATE TABLE auth_token (user_id int8 not null, expired_at timestamp, token varchar(255), primary key (user_id));

CREATE TABLE users (id int8 not null, password varchar(255), username varchar(255), primary key (id));

ALTER TABLE IF EXISTS auth_token ADD CONSTRAINT user_authtoken_fk foreign key (user_id) references users;