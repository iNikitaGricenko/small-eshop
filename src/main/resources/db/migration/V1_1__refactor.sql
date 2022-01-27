alter table orders
    alter column address
        type varchar(200),
    alter column address
        set not null,
    alter column description
        type varchar(1024);

alter table products
    alter column price
        drop not null,
    alter column price
        set default 1,
    alter column count
        drop not null,
    alter column count
        set default 1;

alter table users
    alter column login
        set not null,
    alter column login
        type varchar(345),
    alter column password
        set not null;
