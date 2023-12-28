-- UC-1

CREATE DATABASE payroll_service;
SHOW DATABASES;
USE payroll_service;

-- UC-2

CREATE TABLE employeePayroll
( 
	id	INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name	VARCHAR(150) NOT NULL,
    salary	DOUBLE NOT NULL,
    start	DATE NOT NULL,
    PRIMARY KEY (id)
);

-- UC-3

INSERT INTO employeePayroll(name, salary, start) VALUES
	('Bill', 1000000.00, '2021-11-01'),
    ('Teresa', 20000000.00, '2021-10-05'),
    ('Charlie', 30000000.00, '2022-08-25');
    
SELECT * FROM employeePayroll;

SELECT * FROM employeePayroll
WHERE start BETWEEN CAST('2022-07-01' AS DATE) AND DATE(NOW());

ALTER TABLE employeePayroll ADD COLUMN Gender CHAR(1) AFTER Name;

SET SQL_SAFE_UPDATES = 0;
UPDATE employeePayroll SET Gender = 'M' WHERE Name = 'Bill' or Name = 'Charlie';
UPDATE employeePayroll SET Gender = 'F' WHERE Name = 'Teresa';

SELECT Gender, AVG(Salary) FROM employeePayroll 
GROUP  BY Gender;
SELECT Gender, COUNT(Name) FROM employeePayroll
GROUP BY Gender;
SELECT Gender, SUM(Salary) FROM employeePayroll 
GROUP BY Gender;
SELECT Gender, MIN(Salary) FROM employeePayroll
GROUP BY Gender;
SELECT Gender, MAX(Salary) FROM employeePayroll
GROUP BY Gender;

-- UC-8
ALTER TABLE employeePayroll ADD phoneNum VARCHAR(150) AFTER Name;
ALTER TABLE employeePayroll ADD address VARCHAR(100) AFTER phoneNum;
ALTER TABLE employeePayroll ADD department VARCHAR(100) AFTER address;
ALTER TABLE employeePayroll ALTER address SET DEFAULT 'TBD';
INSERT INTO employeePayroll (name, salary, start) VALUES ('Mark', '10000', '2020-12-30');

-- UC -9
ALTER TABLE employeePayroll RENAME COLUMN salary TO basic_pay;
ALTER TABLE employeePayroll ADD deductions Double NOT NULL AFTER basic_pay;
ALTER TABLE employeePayroll ADD taxable_pay Double NOT NULL AFTER deductions;
ALTER TABLE employeePayroll ADD tax Double NOT NULL AFTER taxable_pay;
ALTER TABLE employeePayroll ADD net_pay Double NOT NULL AFTER tax;

DESCRIBE employeePayroll;

-- UC - 11
CREATE TABLE department
(
	dept_id VARCHAR(100) PRIMARY KEY NOT NULL,
    dept_name VARCHAR(100)
);

CREATE TABLE employee
(
	emp_id VARCHAR(50) NOT NULL PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    comp_id VARCHAR(100) NOT NULL,
    phone_num VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    gender VARCHAR(2) NOT NULL,
    FOREIGN KEY (comp_id) REFERENCES comp(comp_id)
);

CREATE TABLE comp
(
	comp_id VARCHAR(100) NOT NULL PRIMARY KEY,
    comp_name VARCHAR(100) NOT NULL
);

CREATE TABLE emp_dept
(
	emp_id VARCHAR(50) NOT NULL,
    dept_id VARCHAR(100),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
    
);

CREATE TABLE payroll
(
	emp_id VARCHAR(50) NOT NULL,
    basic_pay Double NOT NULL,
    deductions Double NOT NULL,
    tax Double NOT NULL,
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);
    
    

