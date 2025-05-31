create database order_schema;

USE order_schema;

DROP DATABASE assignment2;
show databases;

CREATE TABLE product (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100),
    price DECIMAL(10, 2),
    stock_quantity INT
);

INSERT INTO product VALUES
(1, 'Laptop', 999.99, 50),
(2, 'Smartphone', 499.99, 100),
(3, 'Headphones', 149.99, 200),
(4, 'Monitor', 199.99, 75);

CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(15)
);

INSERT INTO customer VALUES
(1, 'John', 'Doe', 'john.doe@example.com', '555-1234'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', '555-5678'),
(3, 'Emily', 'Jones', 'emily.jones@example.com', '555-8765');

CREATE TABLE status (
    status_id INT PRIMARY KEY,
    status_name VARCHAR(50)
);

INSERT INTO status VALUES
(1, 'Shipped'),
(2, 'Pending'),
(3, 'Cancelled');

CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    total_amount DECIMAL(10, 2),
    status_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (status_id) REFERENCES status(status_id)
);

INSERT INTO orders VALUES
(1, 1, '2025-02-15', 1499.98, 1),
(2, 2, '2025-02-16', 199.99, 2),
(3, 3, '2025-02-17', 499.99, 1),
(4, 1, '2025-02-18', 149.99, 3);

CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

INSERT INTO order_items VALUES
(1, 1, 1, 1, 999.99),  -- 1 Laptop
(2, 1, 2, 1, 499.99),  -- 1 Smartphone
(3, 2, 3, 2, 149.99),  -- 2 Headphones
(4, 3, 2, 1, 499.99),  -- 1 Smartphone
(5, 4, 3, 1, 149.99);  -- 1 Headphones


CREATE TABLE order_history (
    history_id INT PRIMARY KEY,
    order_id INT,
    status_change_date DATE,
    status_description VARCHAR(100),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

INSERT INTO order_history VALUES
(1, 1, '2025-02-15', 'Order Placed'),
(2, 1, '2025-02-16', 'Payment Processed'),
(3, 2, '2025-02-16', 'Order Placed'),
(4, 3, '2025-02-17', 'Order Placed'),
(5, 3, '2025-02-18', 'Payment Processed'),
(6, 4, '2025-02-18', 'Order Placed');

SELECT o.order_id, o.order_date, o.total_amount,
       c.first_name, c.last_name, s.status_name
FROM order_schema.orders o
JOIN order_schema.customer c ON o.customer_id = c.customer_id
JOIN order_schema.status s ON o.status_id = s.status_id;

SELECT SUM(total_amount) AS total_value
FROM order_schema.orders
WHERE customer_id = 1
  AND order_date BETWEEN '2025-02-15' AND '2025-02-18';
  
SELECT customer_id, MAX(total_amount) AS max_order_amount
FROM order_schema.orders
GROUP BY customer_id;

SELECT p.product_name, SUM(oi.quantity * oi.price) AS total_revenue
FROM order_schema.order_items oi
JOIN order_schema.product p ON oi.product_id = p.product_id
GROUP BY p.product_name;

SELECT order_id, customer_id, 
IFNULL(total_amount, 0.00) AS total_amount
FROM order_schema.orders;


SELECT o.order_id, o.order_date, p.product_name, oi.quantity, oi.price
FROM order_schema.orders o
JOIN order_schema.order_items oi ON o.order_id = oi.order_id
JOIN order_schema.product p ON oi.product_id = p.product_id
WHERE o.customer_id = 1;



SELECT customer_id, AVG(total_amount) AS avg_order_value
FROM order_schema.orders
WHERE order_date >= CURDATE() - INTERVAL 30 DAY
GROUP BY customer_id;

SELECT p.product_name, COUNT(DISTINCT oi.order_id) AS num_orders
FROM order_schema.order_items oi
JOIN order_schema.product p ON oi.product_id = p.product_id
GROUP BY p.product_name
ORDER BY num_orders DESC
LIMIT 5;


SELECT c.customer_id, c.first_name, c.last_name
FROM order_schema.customer c
LEFT JOIN order_schema.orders o 
ON c.customer_id = o.customer_id AND o.order_date >= CURDATE() - INTERVAL 60 DAY
WHERE o.order_id IS NULL;


SELECT o.order_id, o.order_date
FROM order_schema.orders o
JOIN order_schema.order_items oi ON o.order_id = oi.order_id
WHERE oi.quantity > 1
ORDER BY o.order_date;

SELECT s.status_name,
COUNT(o.order_id) AS num_orders,
SUM(o.total_amount) AS total_revenue
FROM order_schema.orders o
JOIN order_schema.status s ON o.status_id = s.status_id
GROUP BY s.status_name;


SELECT DISTINCT c.customer_id, c.first_name, c.last_name
FROM order_schema.customer c
JOIN order_schema.orders o ON c.customer_id = o.customer_id
JOIN order_schema.order_items oi ON o.order_id = oi.order_id
JOIN order_schema.product p ON oi.product_id = p.product_id
WHERE p.product_name = 'Laptop' AND oi.quantity > 1;

SELECT p.product_id, p.product_name
FROM order_schema.product p
LEFT JOIN order_schema.order_items oi ON p.product_id = oi.product_id
WHERE oi.product_id IS NULL;

SELECT p.product_name, SUM(oi.quantity) AS total_quantity
FROM order_schema.order_items oi
JOIN order_schema.orders o ON oi.order_id = o.order_id
JOIN order_schema.product p ON oi.product_id = p.product_id
WHERE o.order_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY p.product_name;



CREATE VIEW order_schema.product_details AS
SELECT * FROM order_schema.product;

SELECT * FROM order_schema.product_details;


CREATE VIEW order_summary AS
SELECT 
    o.order_id,
    o.customer_id,
    o.order_date,
    o.total_amount,
    s.status_name
FROM orders o
JOIN status s ON o.status_id = s.status_id;
SELECT * FROM order_summary;

