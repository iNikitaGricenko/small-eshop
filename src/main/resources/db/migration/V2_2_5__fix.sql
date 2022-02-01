delete from user_orders
    where user_orders_pk = 54;

delete from orders
    where user_id = 1;

delete from users_roles
    where user_id = 1
    returning *;

delete from users_roles
    where user_id = 2
    returning *;

delete from users
    where activation_code is null
        returning *;