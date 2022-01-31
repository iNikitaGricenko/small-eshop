alter table orders
    alter column status drop  not null,
    alter column created drop not null ;