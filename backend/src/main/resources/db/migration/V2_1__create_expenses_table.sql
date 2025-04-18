CREATE TABLE ff.expenses (
    id uuid primary key,
    description varchar(255) not null,
    title varchar(150) not null,
    status varchar(50) not null,
    requested_at timestamp not null,
    approved_at timestamp not null,
    requested_by_id uuid,
    approved_by_id uuid,
    CONSTRAINT fk_requested_by FOREIGN KEY (requested_by_id) REFERENCES "user" (id) ON DELETE SET NULL,
    CONSTRAINT fk_approved_by FOREIGN KEY (approved_by_id) REFERENCES "user" (id) ON DELETE SET NULL
)