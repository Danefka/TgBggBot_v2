CREATE TYPE user_status AS ENUM (
    'Default'
    );

CREATE TABLE user_context (
                              user_id    BIGINT       PRIMARY KEY,
                              user_name  VARCHAR(255),
                              user_status user_status NOT NULL DEFAULT 'Default'
);