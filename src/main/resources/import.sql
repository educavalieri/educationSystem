INSERT INTO tb_user (name, email, password) VALUES ('Bob', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Ana', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_VISITOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_student (name, last_Name, year) VALUES ('Leonardo', 'Cavalieri' , 10)
INSERT INTO tb_student (name, last_Name, year) VALUES ('Rafael', 'Cavalieri' , 5)

INSERT INTO tb_subject(name) VALUES ('Matemática')
INSERT INTO tb_subject(name) VALUES ('Fisica')
INSERT INTO tb_subject(name) VALUES ('Quimica')

INSERT INTO tb_professor(name, subject_id) VALUES ('Astolfo', 1)
INSERT INTO tb_professor(name, subject_id) VALUES ('Mota', 1)
INSERT INTO tb_professor(name, subject_id) VALUES ('Giancarlo', 2)
INSERT INTO tb_professor(name, subject_id) VALUES ('Demetrio', 3)

INSERT INTO tb_student_subject(student_id, subject_id) VALUES (1, 1)
INSERT INTO tb_student_subject(student_id, subject_id) VALUES (1, 2)
INSERT INTO tb_student_subject(student_id, subject_id) VALUES (2, 1)