CREATE TABLE audio_track (id int generated by default as identity, file_path varchar(2048), length int4 not null, uploaded_by_user_id int8, primary key (id));
ALTER TABLE if exists audio_track add constraint audio_track_uploaded_by_user_id_user_user_id foreign key (uploaded_by_user_id) references users;