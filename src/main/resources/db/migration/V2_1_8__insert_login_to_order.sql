alter table users
    add constraint login_uq unique (login);
delete from orders where user_id=1 returning *;
alter table orders
    add column email varchar(345) not null,
    add constraint fk_user_email
        foreign key (email)
        references users(login);