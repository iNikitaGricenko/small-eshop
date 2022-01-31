INSERT INTO users values (1, 'postman@gmail.com', 123);
INSERT INTO users_roles SELECT 1, user_id, role_id FROM users, roles WHERE user_id=1 and role_id = 1;