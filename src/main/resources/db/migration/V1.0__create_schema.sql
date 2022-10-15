CREATE TABLE advisors
(
    id         SERIAL PRIMARY KEY,
    email      TEXT NOT NULL,
    user_name  TEXT NOT NULL UNIQUE,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    role_type  TEXT NOT NULL
);

CREATE TABLE applicants
(
    id         SERIAL PRIMARY KEY,
    email      TEXT NOT NULL,
    user_name  TEXT NOT NULL UNIQUE,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    ssn        TEXT NOT NULL UNIQUE,
    city       TEXT NOT NULL,
    street     TEXT NOT NULL,
    zip        INT  NOT NULL,
    number     INT  NOT NULL,
    apt        INT
);

CREATE TABLE applications
(
    id                SERIAL PRIMARY KEY,
    credit_amount_usd DECIMAL(12, 2) NOT NULL,
    status            TEXT           NOT NULL,
    created_at        TIMESTAMP      NOT NULL default now(),
    applicant_id      BIGINT         NOT NULL REFERENCES applicants (id),
    advisor_id        BIGINT REFERENCES advisors (id),
    version           INT            NOT NULL default 1,
    assigned_at       TIMESTAMP,
    resolved_at       TIMESTAMP
);

CREATE TABLE applicant_phones_numbers
(
    type         TEXT   NOT NULL,
    number       TEXT   NOT NULL UNIQUE,
    applicant_id BIGINT NOT NULL REFERENCES applicants (id)
);