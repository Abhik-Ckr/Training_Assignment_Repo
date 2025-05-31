# 📚 DBMS Assignment – Abhik Chakraborty (28th May 2025)

This Assignment consists of two parts:

1. 📘 **Student Information System**
2. 📦 **Order Management System**

The databases are built using MySQL. Each part contains table definitions, sample data, and SQL queries to perform real-world operations and insights.

---

## 🔹 1. Student Information System

### 🗂️ Tables

- `student (student_id, first_name, last_name, birthdate, department_id, address_id)`
- `address (address_id, street_address, city, state, postal_code)`
- `department (department_id, department_name)`

### 📥 Sample Data

Imported from `sampledata.txt`:
- 8 Students
- 6 Departments
- 8 Addresses

### 🔍 Sample Queries

- Count total students
- Fetch department for a student (e.g., John)
- Join students with department and address
- Update Jane’s city to "New York"
- Delete a student
- Filter by city, birth month, department, etc.
- Count students per department or city
- Retrieve students based on address or age range

---

## 🔹 2. Order Management System

### 📁 Database: `order_schema`

### 🗂️ Tables

- `product (product_id, product_name, price, stock_quantity)`
- `customer (customer_id, first_name, last_name, email, phone_number)`
- `status (status_id, status_name)`
- `orders (order_id, customer_id, order_date, total_amount, status_id)`
- `order_items (order_item_id, order_id, product_id, quantity, price)`
- `order_history (history_id, order_id, status_change_date, status_description)`

### 📥 Sample Data

- 4 Orders by 3 Customers
- 4 Products
- Statuses: Shipped, Pending, Cancelled
- Order items and historical status changes

### 🧠 SQL Tasks

1. Retrieve orders with customer and status
2. Get total order value for a customer in a date range
3. Most expensive order per customer
4. Revenue per product
5. Orders with null totals as `0.00`
6. Customer order history with product details
7. Average order value in last 30 days
8. Top 5 most ordered products
9. Customers with no orders in last 60 days
10. Orders with repeated products
11. Orders/revenue grouped by status
12. Customers who ordered a product more than once (e.g., Laptop)
13. Products never ordered
14. Quantity ordered in last 7 days

### 👓 Views

- `product_details`: All columns from the product table  
  ```sql
  CREATE VIEW product_details AS SELECT * FROM product;
