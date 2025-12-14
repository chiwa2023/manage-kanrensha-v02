TRUNCATE TABLE `wk_tbl_kanrensha_person_add_min`;
ALTER TABLE `wk_tbl_kanrensha_person_add_min` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_add_min` (`wk_tbl_kanrensha_person_add_min_id`,`wk_tbl_kanrensha_person_add_min_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `person_shokugyou`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`, `delete_user_id`, `delete_user_code`, `delete_user_name`, `delete_timestamp`) VALUES
(2001,2001, 1, 0, '山田 太郎', '東京都新宿区西新宿2-8-1', '会社員', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(2002,2002, 1, 0, '鈴木 花子', '大阪府大阪市中央区大手前2-1-22', '公務員', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(2003,2003, 1, 0, '佐藤 次郎', '愛知県名古屋市中区三の丸3-1-2', '自営業', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(2004,2004, 1, 0, '伊藤 さくら', '福岡県福岡市博多区東公園7-7', '主婦', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(2005,2005, 1, 0, '渡辺 三郎', '北海道札幌市中央区北3条西6', '学生', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(5001,2001, 1, 0, '山田 太郎', '東京都新宿区西新宿2-8-1', '会社員', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(5002,2001, 1, 0, '山田 太郎', '東京都新宿区西新宿2-8-1', '会社員', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(6001,2002, 1, 0, '鈴木 花子', '大阪府大阪市中央区大手前2-1-22', '公務員', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00');
