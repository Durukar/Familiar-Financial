CREATE TABLE ff.roles (
    id uuid primary key,
    authorithy varchar(50) not null
);

CREATE TABLE ff.user_role (
    user_id uuid not null,
    role_id uuid not null,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
)