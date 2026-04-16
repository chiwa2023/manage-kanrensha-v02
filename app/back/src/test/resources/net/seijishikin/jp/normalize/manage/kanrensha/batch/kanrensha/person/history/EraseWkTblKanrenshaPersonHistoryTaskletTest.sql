DELETE FROM `wk_tbl_kanrensha_person_history`;
ALTER TABLE `wk_tbl_kanrensha_person_history` auto_increment = 0;

DELETE FROM `wk_tbl_kanrensha_person_history_result`;
ALTER TABLE `wk_tbl_kanrensha_person_history_result` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_person_history` (`wk_kanrensha_person_history_id`, `wk_kanrensha_person_history_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `person_shokugyou`, `person_kanrensha_code`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 3001, 1, 0, '高橋 四郎', '神奈川県横浜市中区日本大通1', '医師', 'P001', 0, NULL, 1, 190, 'gemini-user', NOW()),
(2, 3002, 1, 0, '田中 恵子', '千葉県千葉市中央区市場町1-1', '弁護士', 'P002', 0, NULL, 1, 190, 'gemini-user', NOW()),
(3, 3003, 1, 0, '中村 健太', '埼玉県さいたま市浦和区高砂3-15-1', '教員', 'P003', 0, NULL, 1, 190, 'gemini-user', NOW()),
(4, 3004, 1, 0, '小林 直子', '兵庫県神戸市中央区下山手通5-10-1', 'エンジニア', 'P004', 0, NULL, 1, 190, 'gemini-user', NOW()),
(5, 3005, 1, 0, '加藤 久雄', '京都府京都市上京区下立売通新町西入薮ノ内町', 'デザイナー', 'P005', 0, NULL, 1, 216, 'gemini-user', NOW());

INSERT INTO `wk_tbl_kanrensha_person_history_result` (`wk_kanrensha_person_history_result_id`, `wk_kanrensha_person_history_id`, `is_latest`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 1, 1, 0, '新規登録', 1, 190, 'gemini-user', NOW()),
(2, 2, 1, 1, '内容更新', 1, 190, 'gemini-user', NOW()),
(3, 3, 1, 0, '変更なし', 1, 190, 'gemini-user', NOW()),
(4, 4, 1, 1, '住所変更', 1, 190, 'gemini-user', NOW()),
(5, 5, 1, 0, '確認済み', 1, 216, 'gemini-user', NOW());