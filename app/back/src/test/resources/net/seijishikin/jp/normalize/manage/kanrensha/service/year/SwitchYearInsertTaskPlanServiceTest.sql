DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

DELETE FROM `task_plan_2025`;
ALTER TABLE `task_plan_2025` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_template`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (136,101,'郵便番号差分修正',1,'admin,manager','メッセージ','pageUrl','123=4',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00');

DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (203,187,24,'タスク名称2',2026,1,1,1,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-6-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
