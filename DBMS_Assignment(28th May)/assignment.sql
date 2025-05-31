CREATE DATABASE assignment;
show databases;
use assignment;

CREATE TABLE student (
    student_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    birthdate DATE,
    department_id INT REFERENCES department(department_id),
    address_id INT REFERENCES address(address_id)
);

CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    street_address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(50),
    postal_code VARCHAR(20)
);

CREATE TABLE department (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL
);

show tables;
INSERT INTO department (department_id, department_name) VALUES
(1, 'Computer Science'),
(2, 'Mechanical Engineering'),
(3, 'Electrical Engineering'),
(4, 'Civil Engineering'),
(5, 'Mathematics'),
(6, 'Biology');

INSERT INTO address (address_id, street_address, city, state, postal_code) VALUES
(1, '123 Elm St', 'Springfield', 'IL', '62701'),
(2, '456 Oak St', 'Decatur', 'IL', '62521'),
(3, '789 Pine St', 'Champaign', 'IL', '61820'),
(4, '102 Birch Rd', 'Peoria', 'IL', '61602'),
(5, '205 Cedar Ave', 'Chicago', 'IL', '60601'),
(6, '310 Maple Dr', 'Urbana', 'IL', '61801'),
(7, '415 Oak Blvd', 'Champaign', 'IL', '61821'),
(8, '520 Pine Rd', 'Carbondale', 'IL', '62901');

INSERT INTO student (student_id, first_name, last_name, birthdate, department_id, address_id) VALUES
(1, 'John', 'Doe', '1995-04-15', 1, 1),
(2, 'Jane', 'Smith', '1996-07-22', 2, 2),
(3, 'Alice', 'Johnson', '1994-11-30', 3, 3),
(4, 'Michael', 'Brown', '1997-02-19', 4, 4),
(5, 'Sophia', 'Davis', '1998-01-05', 5, 5),
(6, 'Daniel', 'Wilson', '1995-06-10', 6, 6),
(7, 'Olivia', 'Martinez', '1997-11-25', 1, 7),
(8, 'Ethan', 'Miller', '1996-03-30', 2, 8);

SELECT COUNT(*) FROM student;

SELECT d.department_name 
FROM student s
JOIN department d ON s.department_id = d.department_id
WHERE s.first_name = 'John';


SELECT d.department_name, COUNT(s.student_id) AS student_count
FROM department d
LEFT JOIN student s ON d.department_id = s.department_id
GROUP BY d.department_name;



SELECT s.*, d.department_name, a.city, a.street_address 
FROM student s
JOIN department d ON s.department_id = d.department_id
JOIN address a ON s.address_id = a.address_id;
	
SELECT s.* 
FROM student s
JOIN department d ON s.department_id = d.department_id
WHERE d.department_name = 'Computer Science';

UPDATE address
SET city = 'New York'
WHERE address_id = (
  SELECT address_id FROM student WHERE first_name = 'Jane'
);

SELECT s.first_name, s.last_name, a.city
FROM student s
JOIN address a ON s.address_id = a.address_id
WHERE s.first_name = 'Jane';

select * from student;

DELETE FROM student WHERE student_id = 1;
select * from student;


SELECT s.*, d.department_name, a.city 
FROM student s
JOIN department d ON s.department_id = d.department_id
JOIN address a ON s.address_id = a.address_id
WHERE a.city = 'New York';


SELECT d.department_name, COUNT(s.student_id) AS count
FROM department d
LEFT JOIN student s ON d.department_id = s.department_id
GROUP BY d.department_name;


SELECT s.* FROM student s
JOIN address a ON s.address_id = a.address_id
WHERE a.city = 'Springfield';

SELECT * FROM student
WHERE EXTRACT(MONTH FROM birthdate) = 2;


SELECT d.department_name, a.*
FROM student s
JOIN department d ON s.department_id = d.department_id
JOIN address a ON s.address_id = a.address_id
WHERE s.first_name = 'Jane';

SELECT * FROM student
WHERE birthdate BETWEEN '1995-01-01' AND '1998-12-31';

SELECT s.*, d.department_name 
FROM student s
JOIN department d ON s.department_id = d.department_id
ORDER BY d.department_name;

SELECT d.department_name, COUNT(*) AS count
FROM student s
JOIN department d ON s.department_id = d.department_id
JOIN address a ON s.address_id = a.address_id
WHERE a.city = 'Champaign'
GROUP BY d.department_name;

SELECT s.* 
FROM student s
JOIN address a ON s.address_id = a.address_id
WHERE LOWER(a.street_address) LIKE '%pine%';

UPDATE student
SET department_id = (
  SELECT department_id FROM department WHERE department_name = 'Mechanical Engineering'
)
WHERE student_id = 6;

SELECT s.student_id, s.first_name, s.department_id, d.department_name
FROM student s
JOIN department d ON s.department_id = d.department_id
WHERE s.student_id = 6;

SELECT s.* 
FROM student s
JOIN department d ON s.department_id = d.department_id
JOIN address a ON s.address_id = a.address_id
WHERE d.department_name = 'Mathematics' AND a.city = 'Chicago';

SELECT s.*
FROM student s
JOIN address a ON s.address_id = a.address_id
WHERE a.city IN ('Urbana', 'Peoria');

SELECT * FROM student
ORDER BY student_id DESC
LIMIT 1;

SELECT s.* 
FROM student s
JOIN department d ON s.department_id = d.department_id
WHERE d.department_name != 'Computer Science';

SELECT COUNT(*) 
FROM address
WHERE city = 'Champaign';

SELECT s.*
FROM student s
JOIN address a ON s.address_id = a.address_id
WHERE a.street_address = '520 Pine Rd';

SELECT AVG(TIMESTAMPDIFF(YEAR, s.birthdate, CURDATE())) AS avg_age
FROM student s
JOIN department d ON s.department_id = d.department_id
WHERE d.department_name = 'Electrical Engineering';

SELECT s.*, d.department_name, a.city
FROM student s
JOIN department d ON s.department_id = d.department_id
JOIN address a ON s.address_id = a.address_id
WHERE d.department_name LIKE 'M%';

DELETE FROM student
WHERE department_id = (
  SELECT department_id FROM department WHERE department_name = 'Mechanical Engineering'
)
LIMIT 1; 

SELECT *
FROM student
WHERE department_id = (
  SELECT department_id FROM department WHERE department_name = 'Mechanical Engineering'
)
LIMIT 1;
