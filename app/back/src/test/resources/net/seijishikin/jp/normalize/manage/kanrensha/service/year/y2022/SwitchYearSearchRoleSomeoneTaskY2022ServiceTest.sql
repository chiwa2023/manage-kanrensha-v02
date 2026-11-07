DELETE FROM `task_plan_2022`;
ALTER TABLE `task_plan_2022` auto_increment = 0;

-- 抽出
INSERT INTO `task_plan_2022` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (205,189,901,'タスク名称4',2022,1,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','manager','pass',0,'ユーザ',213,190,'ユーザ','2022-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

-- 挿入日時対象外
INSERT INTO `task_plan_2022` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (206,189,901,'タスク名称4',2022,1,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','manager','pass',0,'ユーザ',213,190,'ユーザ','2022-2-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

-- 最新でない
INSERT INTO `task_plan_2022` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (207,189,901,'タスク名称4',2022,0,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','manager','pass',0,'ユーザ',213,190,'ユーザ','2022-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
  
-- 開始していない
INSERT INTO `task_plan_2022` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (208,189,901,'タスク名称4',2022,1,0,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','manager','pass',0,'ユーザ',213,190,'ユーザ','2022-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
  
-- 終了していない
INSERT INTO `task_plan_2022` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (209,189,901,'タスク名称4',2022,1,1,0,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','manager','pass',0,'ユーザ',213,190,'ユーザ','2022-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
  
-- 中断していない
INSERT INTO `task_plan_2022` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (210,189,901,'タスク名称4',2022,1,1,1,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','manager','pass',0,'ユーザ',213,190,'ユーザ','2022-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

-- SE専用タスクである
INSERT INTO `task_plan_2022` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (211,189,901,'タスク名称4',2022,1,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',0,'ユーザ',213,190,'ユーザ','2022-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
  