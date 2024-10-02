INSERT INTO "user" (usr_id, usr_name, usr_username, usr_password, usr_admin)
VALUES
    ('user1', 'User 1', 'user1', 'pass', false),
    ('user2', 'User 2', 'user2', 'pass', false),
    ('user3', 'User 3', 'user3', 'pass', false),
    ('admin', 'Administrateur', 'admin', 'pass', true);

INSERT INTO video (vid_id, vid_name, vid_desc, vid_date, vid_owner_id)
VALUES
    ('video1', 'Video 1', 'La description de la video', '2024-10-02 10:47:32', 'user1'),
    ('video2', 'Video 2', 'La description de cette vidéo', '2024-10-02 22:48:01', 'user1');

INSERT INTO comment (com_id, com_content, com_date, com_video_id, com_user_id)
VALUES
    ('com1', 'Le contenu 1', '2024-10-02 12:40:20', 'video1', 'user2'),
    ('com2', 'Le contenu 2', '2024-10-02 12:41:20', 'video1', 'user2'),
    ('com3', 'Le contenu 3', '2024-10-02 13:41:28', 'video2', 'user3'),
    ('com4', 'Le contenu 4', '2024-10-02 14:10:23', 'video2', 'user2');