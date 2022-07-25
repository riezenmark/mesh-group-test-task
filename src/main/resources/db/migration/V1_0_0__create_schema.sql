create table if not exists users
(
    id    bigserial
        constraint users_pk
            primary key,
    name  varchar(255) not null,
    age   smallint     not null,
    email varchar(255) not null
);

alter table users
    owner to admin;

create unique index if not exists users_email_uindex
    on users (email);

create table if not exists profiles
(
    id      bigserial
        constraint profiles_pk
            primary key,
    cash    numeric(21, 2) not null,
    user_id bigint
        constraint profiles_users_id_fk
            references users
);

alter table profiles
    owner to admin;

create unique index if not exists profiles_id_uindex
    on profiles (id);

create unique index if not exists profiles_user_id_uindex
    on profiles (user_id);

create table if not exists phones
(
    id      bigserial
        constraint phones_pk
            primary key,
    value   varchar(31),
    user_id bigint not null
        constraint phones_users_id_fk
            references users
);

alter table phones
    owner to admin;

create unique index if not exists phones_id_uindex
    on phones (id);

create unique index if not exists phones_value_uindex
    on phones (value);
