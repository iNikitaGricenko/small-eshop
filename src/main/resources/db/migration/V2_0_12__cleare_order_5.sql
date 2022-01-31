delete from orders
    where is_deleted = true
    RETURNING *;