select nextval ('hibernate_sequence');
insert into users (password, username,id) values ('user', 'user', 1);
insert into auth_token (expired_at, token, user_id) values ('Mon Jul 25 01:01:24 MSK 2022', 'useruser_token', 1);

select nextval ('hibernate_sequence');
insert into users (password, username,id) values ('admin', 'admin', 2);
insert into auth_token (expired_at, token, user_id) values ('Mon Jul 25 01:01:24 MSK 2022', 'adminadmin_token', 2);

