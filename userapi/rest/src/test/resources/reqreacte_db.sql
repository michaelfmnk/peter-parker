DROP DATABASE IF EXISTS "peter-parker-userapi";
CREATE DATABASE "peter-parker-userapi";


DELETE
FROM users
WHERE TRUE;
INSERT INTO users (phone, password, first_name, last_name, enabled, role_id, plate_number)
VALUES ('390987309887', '$2a$10$aOvmMpDf5V0id4e.P9vBd.ppG.Ian1uVuvpI1VIVj0qsX9Qa/MW8a', 'Michael', 'Fomenko', true, 1,
        'AE567878IT');

DELETE
FROM incidents
WHERE TRUE;

insert into incidents (document_id, description, plate_number, location, reporter_id)
VALUES ('IQyi2mo.jpg', 'man is sick', 'AE556677IT', POINT(48.473301, 35.001911)::geometry, 1),
       ('IQyi2mo.jpg', 'man is really sick', 'AE556699IT', POINT(48.470873, 35.023212)::geometry, 1),
       ('IQyi2mo.jpg', 'man went out of his mind', 'AE556667IT', POINT(48.458124, 35.060291)::geometry, 1),
       ('IQyi2mo.jpg', 'wtf??', 'AE556677IT', POINT(48.473301, 35.001911)::geometry, 1),
       ('IQyi2mo.jpg', 'wat?', 'AE567878IT', POINT(48.473301, 35.001911)::geometry, 1);
