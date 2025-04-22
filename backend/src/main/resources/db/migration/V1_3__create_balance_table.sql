CREATE TABLE ff.familiar_balance (
    id uuid primary key,
    balance DECIMAL(10, 2),
    created_at timestamp not null,
    updated_at timestamp null
);

ALTER TABLE ff.users
ADD COLUMN balance_familiar_id uuid,
ADD CONSTRAINT fk_balance_familiar
    FOREIGN KEY (balance_familiar_id)
    REFERENCES ff.familiar_balance(id);