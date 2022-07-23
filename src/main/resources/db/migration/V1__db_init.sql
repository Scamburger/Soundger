CREATE TABLE role (id int8 generated by default as identity, role_name varchar(255), primary key (id));
CREATE TABLE "user" (id int8 generated by default as identity, password varchar(255), username varchar(255), primary key (id));
CREATE TABLE "user_roles" ("user_id" int8 not null, roles_id int8 not null, primary key ("user_id", roles_id));
ALTER TABLE IF EXISTS "user_roles" drop constraint IF EXISTS UK_amwlmdeik2qdnksxgd566knop;
ALTER TABLE IF EXISTS "user_roles" add constraint UK_amwlmdeik2qdnksxgd566knop unique (roles_id);
ALTER TABLE IF EXISTS "user_roles" add constraint FKsoyrbfa9510yyn3n9as9pfcsx foreign key (roles_id) references role;
ALTER TABLE IF EXISTS "user_roles" add constraint FK40cm955hgg5oxf1oax8mqw0c4 foreign key ("user_id") references "user";