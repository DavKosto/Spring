drop table if exists products cascade;
create table products (id bigserial, title varchar(255), description varchar(5000), price int, primary key(id));

drop table if exists categories cascade;
create table categories (id bigserial, title varchar(255), primary key(id));

drop table if exists products_categories cascade;
create table products_categories (product_id bigint, category_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (category_id) REFERENCES categories (id));
