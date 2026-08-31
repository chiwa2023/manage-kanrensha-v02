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


DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
