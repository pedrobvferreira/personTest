DROP TABLE IF EXISTS person;  
CREATE TABLE person (  
id INT AUTO_INCREMENT PRIMARY KEY,  
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NULL,
date VARCHAR(100) NULL,
phone_number VARCHAR(100) NULL
);

insert into person(id, first_name, last_name, date, phone_number) values (1, 'Pedro', 'Ferreira', '2022-03-23', '982342215');
insert into person(id, first_name, last_name, date), phone_number values (2, 'João', 'Eduardo', '2022-03-24', '982342222');