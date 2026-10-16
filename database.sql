CREATE DATABASE IF NOT EXISTS online_food_ordering;
USE online_food_ordering;

CREATE TABLE IF NOT EXISTS food_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(60) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS customer_orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    delivery_address VARCHAR(255) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    food_item_id INT NOT NULL,
    food_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES customer_orders(id),
    FOREIGN KEY (food_item_id) REFERENCES food_items(id)
);

INSERT INTO food_items (name, description, price, category, available)
SELECT 'Veg Burger', 'Crispy vegetable patty with fresh vegetables', 99.00, 'Burgers', TRUE
WHERE NOT EXISTS (SELECT 1 FROM food_items WHERE name = 'Veg Burger');

INSERT INTO food_items (name, description, price, category, available)
SELECT 'Margherita Pizza', 'Classic cheese pizza with tomato sauce', 199.00, 'Pizza', TRUE
WHERE NOT EXISTS (SELECT 1 FROM food_items WHERE name = 'Margherita Pizza');

INSERT INTO food_items (name, description, price, category, available)
SELECT 'Paneer Wrap', 'Spiced paneer wrapped with vegetables', 149.00, 'Wraps', TRUE
WHERE NOT EXISTS (SELECT 1 FROM food_items WHERE name = 'Paneer Wrap');

INSERT INTO food_items (name, description, price, category, available)
SELECT 'French Fries', 'Golden crispy potato fries', 89.00, 'Sides', TRUE
WHERE NOT EXISTS (SELECT 1 FROM food_items WHERE name = 'French Fries');

INSERT INTO food_items (name, description, price, category, available)
SELECT 'Cold Coffee', 'Chilled coffee drink', 109.00, 'Drinks', TRUE
WHERE NOT EXISTS (SELECT 1 FROM food_items WHERE name = 'Cold Coffee');
