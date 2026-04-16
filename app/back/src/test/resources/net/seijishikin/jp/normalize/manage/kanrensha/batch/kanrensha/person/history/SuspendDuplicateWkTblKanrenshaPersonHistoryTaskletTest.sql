TRUNCATE TABLE `wk_tbl_kanrensha_person_history`;
ALTER TABLE `wk_tbl_kanrensha_person_history` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_history` (`wk_kanrensha_person_history_id`,`wk_kanrensha_person_history_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `person_shokugyou`, `person_kanrensha_code`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`, `delete_user_id`, `delete_user_code`, `delete_user_name`, `delete_timestamp`) VALUES
(3001,3001, 1, 0, '高橋 四郎', '神奈川県横浜市中区日本大通1', '医師', 'P001', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(3002,3002, 1, 0, '田中 恵子', '千葉県千葉市中央区市場町1-1', '弁護士', 'P002', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(3003,3003, 1, 0, '中村 健太', '埼玉県さいたま市浦和区高砂3-15-1', '教員', 'P003', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(3004,3004, 1, 0, '小林 直子', '兵庫県神戸市中央区下山手通5-10-1', 'エンジニア', 'P004', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(3005,3005, 1, 0, '加藤 久雄', '京都府京都市上京区下立売通新町西入薮ノ内町', 'デザイナー', 'P005', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),

(5001,3001, 1, 0, '高橋 四郎', '神奈川県横浜市中区日本大通1', '医師', 'P001', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(5002,3001, 1, 0, '高橋 四郎', '神奈川県横浜市中区日本大通1', '医師', 'P001', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(6001,3002, 1, 0, '田中 恵子', '千葉県千葉市中央区市場町1-1', '弁護士', 'P002', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00');
