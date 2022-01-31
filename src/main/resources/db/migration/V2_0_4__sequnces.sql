CREATE SEQUENCE orders_seq AS integer;
ALTER TABLE orders ALTER COLUMN orders_id SET DEFAULT nextval('orders_seq');

CREATE SEQUENCE products_seq AS integer;
ALTER TABLE products ALTER COLUMN product_id SET DEFAULT nextval('products_seq');

CREATE SEQUENCE users_roles_seq AS integer;
ALTER TABLE users_roles ALTER COLUMN users_roles_pk SET DEFAULT nextval('users_roles_seq');

CREATE SEQUENCE users_seq AS integer;
ALTER TABLE users ALTER COLUMN user_id SET DEFAULT nextval('users_seq');

CREATE SEQUENCE roles_seq AS integer;
ALTER TABLE roles ALTER COLUMN role_id SET DEFAULT nextval('roles_seq');