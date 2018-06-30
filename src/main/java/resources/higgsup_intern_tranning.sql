CREATE DATABASE IF NOT EXISTS higgsup_intern_tranning;

use higgsup_intern_tranning;

create table classroom (
id bigint primary key NOT NULL,-- Khóa chính , không tự động tăng khi id , và không được để trống
name_class varchar(225),
description text,
instructor_id bigint 
);
create table student (
id bigint primary key auto_increment NOT NULL, -- Khóa chính ,tự động tăng khi id , không được để trống
name_student varchar(225),
year_of_birth int,
address text 
);
create table instructor (
id bigint primary key NOT NULL, -- Khóa chính, không tự động tăng id , không được để trống
name_instructor varchar(225),
year_of_birth int,
address text 
);
create table enrollment (
id bigint primary key auto_increment NOT NULL, -- Khóa chính,tự động tăng id , không được để trống
student_id bigint,
classroom_id bigint
);
-- tạo khóa ngoại
ALTER TABLE classroom ADD FOREIGN KEY(instructor_id) REFERENCES instructor(id);
ALTER TABLE enrollment ADD FOREIGN KEY(classroom_id) REFERENCES classroom(id);
ALTER TABLE enrollment ADD FOREIGN KEY(student_id) REFERENCES student(id);

insert into instructor values 
(1,'Jons','1988','New York'),
(2,'Marianna','1992','London');

insert into classroom values 
(1,'Class 01','Learn PHP basic',2),
(2,'Class 03','Learn SQL',1);

insert into student values 
(1,'Jame','1998','London'),
(2,'Anna','1997','Berlin'),
(3,'Jenni','1992','HaNoi'),
(4,'Hosenzi','1998','Bac Kinh'),
(5,'Mackey','1990','New York'),
(6,'Jennifer','1998','Ho Chi Minh');
insert into enrollment values 
(2,4,1),
(3,5,1),
(4,5,2),
(5,1,1);

SELECT*FROM student;
SELECT*FROM student WHERE id=?;
UPDATE student SET name_student=?,year_of_birth=?,adress=? WHERE id=?;
INSERT INTO student (name_student,year_of_birth,address) VALUES (?,?,?);
DELETE FROM student WHERE id =?; 
INSERT INTO enrollment vales (?,?,?);
DELETE FROM enrollment WHERE id =?; 
