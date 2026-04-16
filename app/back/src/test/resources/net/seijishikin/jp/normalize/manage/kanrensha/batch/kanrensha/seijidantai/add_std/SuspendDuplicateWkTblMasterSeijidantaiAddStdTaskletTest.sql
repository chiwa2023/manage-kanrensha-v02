TRUNCATE TABLE `wk_tbl_kanrensha_seijidantai_master`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_master` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_seijidantai_master` (`wk_tbl_kanrensha_seijidantai_master_id`,`wk_tbl_kanrensha_seijidantai_master_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `seijidantai_delegate`, `dantai_kbn`, `poli_org_no`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`, `delete_user_id`, `delete_user_code`, `delete_user_name`, `delete_timestamp`) VALUES
(1001,1001, 1, 0, '自由民主党', '東京都千代田区永田町1-11-23', '総裁K', 'A1', '10001', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(1002,1002, 1, 0, '立憲民主党', '東京都千代田区平河町2-12-4', '代表L', 'A1', '10002', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(1003,1003, 1, 0, '公明党', '東京都新宿区南元町17', '代表M', 'A1', '10003', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(1004,1004, 1, 0, '日本共産党', '東京都渋谷区千駄ケ谷4-26-7', '委員長N', 'A1', '10004', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(1005,1005, 1, 0, '日本維新の会', '大阪府大阪市北区天神橋2丁目北1-21', '代表O', 'A1', '10005', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),

(5001,1001, 1, 0, '自由民主党', '東京都千代田区永田町1-11-23', '総裁K', 'A1', '10001', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(5002,1001, 1, 0, '自由民主党', '東京都千代田区永田町1-11-23', '総裁K', 'A1', '10001', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(6001,1002, 1, 0, '立憲民主党', '東京都千代田区平河町2-12-4', '代表L', 'A1', '10002', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00');
