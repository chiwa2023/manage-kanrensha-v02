DELETE FROM `task_plan_2021`;
ALTER TABLE `task_plan_2021` auto_increment = 0;

INSERT INTO `task_plan_2021` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (522,393,24,'タスク名称1',2021,0,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2021-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(523,393,24,'タスク名称1',2021,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2021-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(524,393,24,'タスク名称1',2021,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2021-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
;

