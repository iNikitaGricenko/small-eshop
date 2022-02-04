drop table users_roles;
drop table roles;
delete from user_orders
    returning *;
delete from orders
    returning *;
delete from users
    returning *;
alter table users
    add column role varchar(25);