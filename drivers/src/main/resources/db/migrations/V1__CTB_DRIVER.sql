create table driver (
    id uuid primary key,
    name varchar(255) not null,
    status varchar(255) not null,
    delivery_region varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version int not null default 0
)