delete from products where deleted is null returning *;
delete from products where deleted = true returning *;