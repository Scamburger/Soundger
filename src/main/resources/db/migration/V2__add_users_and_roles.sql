INSERT INTO "user" (password, username) VALUES ('$2a$10$dbSmN015ujuIn7GQ9.gGnOi4e7FowAvjui5fbFim9w6IGp.SVm9SC', 'user');
INSERT INTO role (role_name) VALUES ('ROLE_USER');
INSERT INTO "user_roles" ("user_id", roles_id) VALUES (1, 1);

INSERT INTO "user" (password, username) VALUES ('$2a$10$Y3zYY3MwzYZWxlff1Md/nOklmeBBpcfmG8ArIJK0GhXOJ3nVPp4Wi', 'admin');
INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO "user_roles" ("user_id", roles_id) VALUES (2, 2);