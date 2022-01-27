create table orders (
                        orders_id int8 not null,
                        address varchar(255),
                        count int4 not null,
                        date date,
                        description varchar(255),
                        user_id int8,
                        primary key (orders_id)
);
create table products (
                          product_id int8 not null,
                          count int4 not null,
                          name varchar(255),
                          price int4 not null,
                          primary key (product_id)
);
create table roles (
                       role_id int8 not null,
                       role_name varchar(255),
                       primary key (role_id)
);
create table user_orders (
                             user_orders_pk int8 not null,
                             orders_id int8 not null,
                             product_id int8 not null,
                             primary key (user_orders_pk)
);
create table users (
                       user_id int8 not null,
                       login varchar(255),
                       password varchar(255),
                       primary key (user_id)
);
create table users_roles (
                             users_roles_pk int8 not null,
                             user_id int8 not null,
                             role_id int8 not null,
                             primary key (users_roles_pk)
);

alter table if exists user_orders
    add constraint UK_user_orders_pk
        unique (user_orders_pk);

alter table if exists orders
    add constraint FK_user_id
        foreign key (user_id)
            references users;

alter table if exists user_orders
    add constraint FK_product_id
        foreign key (product_id)
            references products;

alter table if exists user_orders
    add constraint FK_orders_id
        foreign key (orders_id)
            references orders;

alter table if exists users_roles
    add constraint FK_role_id
        foreign key (role_id)
            references roles;

alter table if exists users_roles
    add constraint FK_user_id
        foreign key (user_id)
            references users;

insert into roles
    values (1, 'ADMIN'), (2, 'USER');

insert into users
    values (1, 'postman@gmail.com', 123);

insert into users_roles
    select 1, role_id, user_id
        from roles, users
            where role_id = 1
              and user_id = 1;
