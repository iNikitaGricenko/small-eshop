alter table orders
    alter column deleted set default null;
alter table orders
    alter column created set default CURRENT_DATE;

delete from users_roles
    where users_roles_pk = 1;
insert into users_roles
    select 1, user_id, role_id
        from users, roles
            where user_id = 1
              and role_id = 2;

insert into users
    values (2, 'admin@eshop.com', 'admin');

insert into users_roles
    select 2, user_id, role_id
        from users, roles
            where user_id = 2
              and role_id = 1;

