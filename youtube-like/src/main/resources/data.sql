INSERT INTO "user" (usr_id, usr_name)
SELECT '123456', 'user1'
WHERE NOT EXISTS (SELECT usr_id FROM "user" WHERE usr_id = '123456');
