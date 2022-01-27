alter table products
    add column deleted bool default false;

alter table orders
    add column deleted bool default false;