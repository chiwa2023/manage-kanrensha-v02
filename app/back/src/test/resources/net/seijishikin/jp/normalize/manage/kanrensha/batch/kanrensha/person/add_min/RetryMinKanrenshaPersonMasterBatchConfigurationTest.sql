TRUNCATE TABLE `wk_tbl_kanrensha_person_add_min`;
ALTER TABLE `wk_tbl_kanrensha_person_add_min` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_add_min` (
  `wk_tbl_kanrensha_person_add_min_id`,
  `wk_tbl_kanrensha_person_add_min_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `person_shokugyou`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(2001, 2001, 1, 0, '斎藤 雄一', '広島県広島市中区基町10-52', 'コンサルタント', 1, '正)', 1, 190, 'gemini-user', NOW()),
(2002, 2002, 1, 0, '吉田 美咲', '宮城県仙台市青葉区本町3-8-1', '看護師', 1, '正)', 1, 190, 'gemini-user', NOW()),
(2003, 2003, 1, 0, '松本 拓也', '静岡県静岡市葵区追手町9-6', '営業', 0, '判定理由', 1, 190, 'gemini-user', NOW()),
(2004, 2004, 0, 0, '井上 あゆみ', '岡山県岡山市北区内山下2-4-6', '企画', 1, '正)', 1, 190, 'gemini-user', NOW()),
(2005, 2005, 1, 1, '木村 浩二', '熊本県熊本市中央区水前寺6-18-1', 'マーケティング', 1, '正)', 1, 190, 'gemini-user', NOW()),
(2006, 2006, 1, 0, '田中 一郎', '東京都千代田区1-1-1', 'SE', 1, '正)', 1, 191, 'gemini-user', NOW());
