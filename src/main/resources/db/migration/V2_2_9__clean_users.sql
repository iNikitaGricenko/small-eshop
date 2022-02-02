delete from users_roles
    where role_id = 2
    returning *;
delete from users
    returning *;