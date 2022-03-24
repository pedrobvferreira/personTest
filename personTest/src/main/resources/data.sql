DROP TABLE IF EXISTS person;  
CREATE TABLE person (  
id INT AUTO_INCREMENT PRIMARY KEY,  
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NULL,
date DATE NULL,
phone_number VARCHAR(100) NULL
);

insert into person(id, first_name, last_name, date, phone_number) values (1, 'pedro', 'ferreira', '2022-03-23', '98234221');
insert into person(id, first_name, last_name, date), phone_number values (2, 'joão', 'eduardo', '2022-03-23', '98234221');