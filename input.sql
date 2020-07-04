BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE IF NOT EXISTS customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Arnold'),
('Silvester'),
('Bob');

DROP TABLE IF EXISTS items CASCADE;
CREATE TABLE IF NOT EXISTS items (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO items (title, cost) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('camera', 500);

DROP TABLE IF EXISTS customers_items CASCADE;
CREATE TABLE IF NOT EXISTS customers_items (customer_id bigint, item_id bigint);
INSERT INTO customers_items (customer_id, item_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 2),
(3, 3);

COMMIT;