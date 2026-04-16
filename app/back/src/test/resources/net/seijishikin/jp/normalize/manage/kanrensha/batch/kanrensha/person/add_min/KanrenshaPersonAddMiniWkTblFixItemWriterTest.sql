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
(2002, 2002, 1, 0, '吉田 美咲', '宮城県仙台市青葉区本町3-8-1', '看護師', 1, '正)', 1, 190, 'gemini-user', NOW());
