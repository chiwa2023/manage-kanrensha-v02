TRUNCATE TABLE `wk_tbl_kanrensha_seijidantai_add_min`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_add_min` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (`wk_tbl_kanrensha_seijidantai_add_min_id`,`wk_tbl_kanrensha_seijidantai_add_min_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `seijidantai_delegate`, `dantai_kbn`, `poli_org_no`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`, `delete_user_id`, `delete_user_code`, `delete_user_name`, `delete_timestamp`) VALUES
(1001,1001, 1, 0, '日本未来党', '東京都千代田区永田町1-7-1', '代表A', 'A1', '12345', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(1002,1002, 1, 0, '国民の聲', '東京都千代田区永田町2-1-1', '代表B', 'B2', '23456', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(1003,1003, 1, 0, '改革クラブ', '東京都千代田区平河町2-16-1', '代表C', 'C3', '34567', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(1004,1004, 1, 0, '平和と進歩の会', '東京都港区赤坂1-7-1', '代表D', 'D4', '45678', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(1005,1005, 1, 0, '緑の党グリーンズジャパン', '東京都新宿区西新宿3-7-1', '代表E', 'E5', '56789', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),

(5001,1001, 1, 0, '日本未来党', '東京都千代田区永田町1-7-1', '代表A', 'A1', '12345', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(5002,1001, 1, 0, '日本未来党', '東京都千代田区永田町1-7-1', '代表A', 'A1', '12345', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(6001,1002, 1, 0, '国民の聲', '東京都千代田区永田町2-1-1', '代表B', 'B2', '23456', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00');
