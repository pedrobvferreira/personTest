DROP TABLE IF EXISTS person;  
CREATE TABLE person (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
name VARCHAR(50) NOT NULL,  
age INT(8) NOT NULL,
email VARCHAR(100) NOT NULL
);

insert into person(id, name, age, email) values (1, 'pedro', 17, 'pedrobvf@hotmail.com');