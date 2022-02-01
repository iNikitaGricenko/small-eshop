alter table products
    rename column deleted to deleted_at;
alter table products
    rename column is_deleted to deleted;

alter table products
    alter column deleted_at set default now();