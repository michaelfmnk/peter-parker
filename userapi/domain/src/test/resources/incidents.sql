INSERT INTO users (id, phone, password, first_name, last_name, enabled, role_id, plate_number)
VALUES (1, '0987309887', '$2a$10$aOvmMpDf5V0id4e.P9vBd.ppG.Ian1uVuvpI1VIVj0qsX9Qa/MW8a', 'Michael', 'Fomenko', true, 1,
        'AE567878IT');

INSERT INTO incidents (id, document_id, created_date, description, location, reporter_id)
VALUES (1, 'doc1', NOW(), 'Zaporizhia', POINT(47.79813, 35.188075)::geometry, 1),
       (2, 'doc2', NOW(), 'Cape Town', POINT(-33.882478, 8.313917)::geometry, 1),
       (3, 'doc3', NOW(), 'Kyiv', POINT(50.459935, 30.530606)::geometry, 1),
       (4, 'doc4', NOW(), 'Brussel', POINT(50.832983, 4.497808)::geometry, 1);

