CREATE TABLE audio_track (id int8 generated by default as identity, file_path varchar(255), length int4 not null, uploaded_by_user_id int8, primary key (id));
ALTER TABLE if exists audio_track add constraint FKhujs9f5mtkq2mouvcieccdmjq foreign key (uploaded_by_user_id) references users;