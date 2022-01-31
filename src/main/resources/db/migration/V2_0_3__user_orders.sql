CREATE SEQUENCE user_orders_seq AS integer;
ALTER TABLE user_orders ALTER COLUMN user_orders_pk SET DEFAULT nextval('user_orders_seq');

