DELETE FROM `task_plan_2025`;
ALTER TABLE `task_plan_2025` auto_increment = 0;

INSERT INTO `task_plan_2025` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (202,187,24,'タスク名称',2025,0,1,0,0,'2022-12-05 12:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2022-12-05 12:34:56',231,190,'ユーザ','2022-12-06 23:45:12');

INSERT INTO `task_plan_2025` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (203,187,24,'タスク名称',2025,1,1,1,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2022-12-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `task_plan_2025` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (204,188,24,'タスク名称',2025,0,1,1,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2022-12-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `task_plan_2025` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (205,189,24,'タスク名称',2025,1,1,1,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2022-12-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00');
