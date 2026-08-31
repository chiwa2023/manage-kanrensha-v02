TRUNCATE TABLE `wk_tbl_kanrensha_person_master`;
ALTER TABLE `wk_tbl_kanrensha_person_master` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_master` (`wk_tbl_kanrensha_person_master_id`, `wk_tbl_kanrensha_person_master_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `person_shokugyou`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`, `delete_user_id`, `delete_user_code`, `delete_user_name`, `delete_timestamp`) VALUES
(4001,4001, 1, 0, '斎藤 雄一', '広島県広島市中区基町10-52', 'コンサルタント', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(4002,4002, 1, 0, '吉田 美咲', '宮城県仙台市青葉区本町3-8-1', '看護師', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(4003,4003, 1, 0, '松本 拓也', '静岡県静岡市葵区追手町9-6', '営業', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(4004,4004, 1, 0, '井上 あゆみ', '岡山県岡山市北区内山下2-4-6', '企画', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(4005,4005, 1, 0, '木村 浩二', '熊本県熊本市中央区水前寺6-18-1', 'マーケティング', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),

(5001,4001, 1, 0, '斎藤 雄一', '広島県広島市中区基町10-52', 'コンサルタント', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(5002,4001, 1, 0, '斎藤 雄一', '広島県広島市中区基町10-52', 'コンサルタント', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00'),
(6001,4002, 1, 0, '吉田 美咲', '宮城県仙台市青葉区本町3-8-1', '看護師', 0, NULL, 1, 190, 'gemini-user', NOW(), 1, 190, 'gemini-user', '1947-07-01 00:00:00');
