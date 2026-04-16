TRUNCATE TABLE `wk_tbl_kanrensha_person_history`;
ALTER TABLE `wk_tbl_kanrensha_person_history` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_history` (
  `wk_kanrensha_person_history_id`,
  `wk_kanrensha_person_history_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `person_shokugyou`,
  `person_kanrensha_code`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(3001, 3001, 1, 0, '個人名A', '住所A', '職業A', 'P-CODE-A', 1, '正)', 1, 190, 'gemini-user', NOW()),
(3002, 3002, 1, 0, '個人名B', '住所B', '職業B', 'P-CODE-B', 1, '正)', 1, 190, 'gemini-user', NOW()),
(3003, 3003, 1, 0, '個人名C', '住所C', '職業C', 'P-CODE-C', 0, '判定理由', 1, 190, 'gemini-user', NOW()),
(3004, 3004, 0, 0, '個人名D', '住所D', '職業D', 'P-CODE-D', 1, '正)', 1, 190, 'gemini-user', NOW()),
(3005, 3005, 1, 1, '個人名E', '住所E', '職業E', 'P-CODE-E', 1, '正)', 1, 190, 'gemini-user', NOW()),
(3006, 3006, 1, 0, '個人名F', '住所F', '職業F', 'P-CODE-F', 1, '正)', 1, 191, 'gemini-user', NOW());


DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
