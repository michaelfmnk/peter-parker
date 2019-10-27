CREATE TABLE roles
(
    id      INT PRIMARY KEY,
    api_key VARCHAR NOT NULL
);

INSERT INTO roles (id, api_key)
VALUES (1, 'ADMIN'),
       (2, 'WATCHER'),
       (3, 'OFFICER');

CREATE TABLE users
(
    user_id  SERIAL PRIMARY KEY,
    phone    VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    role_id  INT REFERENCES roles (id)
);

CREATE TABLE otps
(
    value   UUID PRIMARY KEY,
    date    TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    user_id INT REFERENCES users (user_id)
);

CREATE TABLE incident_statuses
(
    incident_status_id INT PRIMARY KEY,
    api_key            VARCHAR NOT NULL
);

INSERT INTO incident_statuses (incident_status_id, api_key)
VALUES (1, 'RECORDED'),
       (2, 'RESOLVED_BY_DRIVER'),
       (3, 'PASSED_TO_POLICE'),
       (4, 'RESOLVED_BY_POLICE'),
       (5, 'NOT_FOUND_BY_POLICE');

CREATE TABLE incidents
(
    id           SERIAL PRIMARY KEY,
    document_id  VARCHAR                     NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE incident_progress
(
    incident_progress_id SERIAL PRIMARY KEY,
    incident_id          INT REFERENCES incidents (id),
    date                 TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    status_id            INT REFERENCES incident_statuses (incident_status_id)
);


