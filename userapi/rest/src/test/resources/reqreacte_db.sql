DROP DATABASE IF EXISTS "peter-parker-userapi";
CREATE DATABASE "peter-parker-userapi";



DELETE
FROM users
WHERE TRUE;
INSERT INTO users (phone, password, first_name, last_name, enabled, role_id, plate_number)
VALUES ('0987309887', '$2a$10$aOvmMpDf5V0id4e.P9vBd.ppG.Ian1uVuvpI1VIVj0qsX9Qa/MW8a', 'Michael', 'Fomenko', true, 1,
        'AE567878IT');
