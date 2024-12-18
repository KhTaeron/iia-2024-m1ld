DELETE FROM video WHERE vid_id NOT IN ('video1', 'video2');

UPDATE video SET vid_name = 'Video 1', vid_desc = 'La description de la video' WHERE vid_id = 'video1';
