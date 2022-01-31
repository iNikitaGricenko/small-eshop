delete from user_orders
    where product_id = 2;
delete from orders
    where created is null
    RETURNING *;

alter table orders
    alter column status set not null,
    alter column created set not null;