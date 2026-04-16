TRUNCATE TABLE `wk_tbl_kanrensha_seijidantai_history`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_history` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_seijidantai_history` (
  `wk_kanrensha_seijidantai_history_id`,
  `wk_kanrensha_seijidantai_history_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `seijidantai_delegate`,
  `seijidantai_kanrensha_code`,
  `org_delegate_code`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(6001, 6001, 1, 0, '政治団体A', '住所A', '代表A', 'S-CODE-A', 'D-CODE-A', 1, '正)', 1, 190, 'gemini-user', NOW()),
(6002, 6002, 1, 0, '政治団体B', '住所B', '代表B', 'S-CODE-B', 'D-CODE-B', 1, '正)', 1, 190, 'gemini-user', NOW()),
(6003, 6003, 1, 0, '政治団体C', '住所C', '代表C', 'S-CODE-C', 'D-CODE-C', 0, '判定理由', 1, 190, 'gemini-user', NOW()),
(6004, 6004, 0, 0, '政治団体D', '住所D', '代表D', 'S-CODE-D', 'D-CODE-D', 1, '正)', 1, 190, 'gemini-user', NOW()),
(6005, 6005, 1, 1, '政治団体E', '住所E', '代表E', 'S-CODE-E', 'D-CODE-E', 1, '正)', 1, 190, 'gemini-user', NOW()),
(6006, 6006, 1, 0, '政治団体F', '住所F', '代表F', 'S-CODE-F', 'D-CODE-F', 1, '正)', 1, 191, 'gemini-user', NOW());


DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')

