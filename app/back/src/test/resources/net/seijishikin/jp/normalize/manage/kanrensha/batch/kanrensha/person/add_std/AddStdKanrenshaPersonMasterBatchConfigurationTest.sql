DELETE FROM `wk_tbl_kanrensha_person_master`;
ALTER TABLE `wk_tbl_kanrensha_person_master` auto_increment = 0;

DELETE FROM `wk_tbl_kanrensha_person_master_result`;
ALTER TABLE `wk_tbl_kanrensha_person_master_result` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_person_master` (
  `wk_tbl_kanrensha_person_master_code`,
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
(4001, 1, 0, '斎藤 雄一', '広島県広島市中区基町10-52', 'コンサルタント', 0, NULL, 1, 190, 'gemini-user', NOW()),
(4002, 1, 0, '吉田 美咲', '宮城県仙台市青葉区本町3-8-1', '看護師', 0, NULL, 1, 190, 'gemini-user', NOW()),
(4003, 1, 0, '松本 拓也', '静岡県静岡市葵区追手町9-6', '営業', 0, NULL, 1, 190, 'gemini-user', NOW()),
(4004, 1, 0, '井上 あゆみ', '岡山県岡山市北区内山下2-4-6', '企画', 0, NULL, 1, 190, 'gemini-user', NOW()),
(4005, 1, 0, '木村 浩二', '熊本県熊本市中央区水前寺6-18-1', 'マーケティング', 0, NULL, 1, 216, 'gemini-user', NOW());

INSERT INTO `wk_tbl_kanrensha_person_master_result` (
  `wk_tbl_kanrensha_person_master_id`,
  `is_latest`,
  `is_affected`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 1, 0, 1, 190, 'gemini-user', NOW()),
(2, 1, 1, 1, 190, 'gemini-user', NOW()),
(3, 1, 0, 1, 190, 'gemini-user', NOW()),
(4, 1, 1, 1, 190, 'gemini-user', NOW()),
(5, 1, 0, 1, 216, 'gemini-user', NOW());


DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
