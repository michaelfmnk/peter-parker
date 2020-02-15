INSERT INTO incidents (id, document_id, created_date, description, location)
VALUES (1, 'doc1', NOW(), 'Zaporizhia', POINT(47.79813, 35.188075)::geometry),
       (2, 'doc2', NOW(), 'Cape Town', POINT(-33.882478, 8.313917)::geometry),
       (3, 'doc3', NOW(), 'Kyiv', POINT(50.459935, 30.530606)::geometry),
       (4, 'doc4', NOW(), 'Brussel', POINT(50.832983, 4.497808)::geometry);

