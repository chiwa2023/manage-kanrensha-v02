DELETE FROM `task_plan_2020`;
ALTER TABLE `task_plan_2020` auto_increment = 0;

-- 抽出(本人作成)
INSERT INTO `task_plan_2020` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (205,189,901,'タスク名称4',2020,1,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',0,'user',213,190,'ユーザ','2020-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

-- 抽出(他人作成)
INSERT INTO `task_plan_2020` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (206,189,101,'タスク名称4',2020,1,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',190,'aaa',168,120,'ユーザ','2020-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

-- 作成時期が異なる
INSERT INTO `task_plan_2020` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (207,189,901,'タスク名称4',2020,1,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',0,'user',213,190,'ユーザ','2020-02-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

  -- タスクが該当しない
INSERT INTO `task_plan_2020` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (208,189,24,'タスク名称4',2020,1,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',0,'user',213,190,'ユーザ','2020-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

  -- 開始していない
INSERT INTO `task_plan_2020` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (209,189,901,'タスク名称4',2020,1,0,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',0,'user',213,190,'ユーザ','2020-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
  
  -- 終了していない
INSERT INTO `task_plan_2020` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (210,189,901,'タスク名称4',2020,1,1,0,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',0,'user',213,190,'ユーザ','2020-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
  
  -- 中断していない
INSERT INTO `task_plan_2020` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (211,189,901,'タスク名称4',2020,1,1,1,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',0,'user',213,190,'ユーザ','2020-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
  
  -- 最新でない
INSERT INTO `task_plan_2020` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`task_user_code`,`task_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (212,189,901,'タスク名称4',2020,0,1,1,1,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',0,'user',213,190,'ユーザ','2020-10-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
  
DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
   (423,311,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(424,312,'タスク名称2',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(425,313,'タスク名称2',0,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(426,314,'タスク名称2',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(427,315,'タスク名称2',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ;
  