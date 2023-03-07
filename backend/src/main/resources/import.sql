INSERT INTO tb_user (name, email, password) VALUES ('Alex Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Brown', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Brown', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);

INSERT INTO tb_course (name, img_uri, img_gray_uri) VALUES ('Bootcamp HTML', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3JKq4Xne_J5_X-n0iHColGkmVpIYmyfBfmyUvocXD4ijZSftuLyMvhwzye38F6eYIRAY&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSefLON8u1JE0pPOCpIBxubsTn_1J6ZrQvcqQ&usqp=CAU');

INSERT INTO tb_offer (edition, start_moment, end_moment, course_id) VALUES('1.0',  TIMESTAMP WITH TIME ZONE '2020-11-20T03:00:00', TIMESTAMP WITH TIME ZONE '2021-11-13T03:00:00', 1);
INSERT INTO tb_offer (edition, start_moment, end_moment, course_id) VALUES('2.0',  TIMESTAMP WITH TIME ZONE '2020-12-20T03:00:00', TIMESTAMP WITH TIME ZONE '2021-12-13T03:00:00', 1);



