alter table orders
    rename column deleted to deleted_at;
alter table orders
    rename column is_deleted to deleted;