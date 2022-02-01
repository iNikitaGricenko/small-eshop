alter table users
    add column first_name varchar(150),
    add column second_name varchar(165),
    add column surname varchar(150),
    add column address varchar(500),
    add column activated boolean default false,
    add column activation_code varchar(40);