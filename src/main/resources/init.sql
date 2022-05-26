create table products(
                         id serial PRIMARY KEY,
                         product_number int not Null ,
                         product_name varchar not Null ,
                         price int not Null
);

insert into products(product_number, product_name, price) values(123, 'testprodukt1', 10);

select * from products;