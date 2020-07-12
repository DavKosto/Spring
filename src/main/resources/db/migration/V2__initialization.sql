insert into categories (title) values
('dairy'),
('fruit'),
('bakery'),
('vegetables');

insert into products (title, description, price) values
('Cheese', 'Fresh cheese', 320),
('Milk', 'Fresh milk', 80),
('Apples', 'Fresh apples', 80),
('Orange', 'Fresh orange', 90),
('Bread', 'Fresh bread', 30),
('Bun', 'Fresh bun', 20),
('Onion', 'Fresh onion', 20),
('Cabbage', 'Fresh cabbage', 30);

insert  into products_categories (product_id, category_id) values
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(5, 3),
(6, 4),
(6, 4);