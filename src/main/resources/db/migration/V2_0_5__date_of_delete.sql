alter table orders
    RENAME column deleted To is_deleted;
alter table orders
    RENAME column date TO created;
alter table orders
    add column deleted date default CURRENT_DATE;

alter table products
    RENAME column deleted To is_deleted;
alter table products
    add column deleted date;