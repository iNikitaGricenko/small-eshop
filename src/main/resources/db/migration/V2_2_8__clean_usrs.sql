delete from users_roles
    where role_id = 2
    returning *;
delete from users
    where user_id = 6 or user_id = 8
    returning *;