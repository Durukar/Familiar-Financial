CREATE SCHEMA IF NOT EXISTS ff;
DROP SCHEMA IF EXISTS public;

CREATE TABLE ff.users (
    id uuid primary key,
    username varchar(40) not null unique,
    password varchar(150) not null,
    role varchar(50) not null,
    created_at timestamp not null,
    updated_at timestamp null
)