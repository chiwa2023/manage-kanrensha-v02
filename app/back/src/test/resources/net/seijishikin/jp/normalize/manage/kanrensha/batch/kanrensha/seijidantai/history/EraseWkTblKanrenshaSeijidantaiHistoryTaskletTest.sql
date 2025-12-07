DELETE FROM `wk_tbl_kanrensha_seijidantai_history`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_history` auto_increment = 0;

DELETE FROM `wk_tbl_kanrensha_seijidantai_history_result`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_history_result` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_seijidantai_history` (`wk_kanrensha_seijidantai_history_id`, `wk_kanrensha_seijidantai_history_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `seijidantai_delegate`, `seijidantai_kanrensha_code`, `org_delegate_code`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 6001, 1, 0, '新党大地', '北海道札幌市中央区北1条西1丁目', '代表F', 'S001', 'P006', 0, NULL, 1, 190, 'gemini-user', NOW()),
(2, 6002, 1, 0, '地域政党京都党', '京都府京都市中京区寺町通御池上る上本能寺前町488', '代表G', 'S002', 'P007', 0, NULL, 1, 190, 'gemini-user', NOW()),
(3, 6003, 1, 0, '大阪維新の会', '大阪府大阪市中央区谷町3丁目1-11', '代表H', 'S003', 'P008', 0, NULL, 1, 190, 'gemini-user', NOW()),
(4, 6004, 1, 0, '減税日本', '愛知県名古屋市中区栄3丁目6-1', '代表I', 'S004', 'P009', 0, NULL, 1, 190, 'gemini-user', NOW()),
(5, 6005, 1, 0, '都民ファーストの会', '東京都新宿区西新宿2丁目8-1', '代表J', 'S005', 'P010', 0, NULL, 1, 216, 'gemini-user', NOW());

INSERT INTO `wk_tbl_kanrensha_seijidantai_history_result` (`wk_kanrensha_seijidantai_history_result_id`, `wk_kanrensha_seijidantai_history_id`, `is_latest`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 1, 1, 0, NULL, 1, 190, 'gemini-user', NOW()),
(2, 2, 1, 1, '代表者変更', 1, 190, 'gemini-user', NOW()),
(3, 3, 1, 0, NULL, 1, 190, 'gemini-user', NOW()),
(4, 4, 1, 1, '住所移転', 1, 190, 'gemini-user', NOW()),
(5, 5, 1, 0, NULL, 1, 216, 'gemini-user', NOW());