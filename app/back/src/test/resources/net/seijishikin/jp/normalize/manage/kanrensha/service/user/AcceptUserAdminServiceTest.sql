DELETE FROM `promote_admin`;
ALTER TABLE `promote_admin` auto_increment = 0;

INSERT INTO `promote_admin` (`promote_admin_id`,`promote_admin_code`,`is_latest`,`promote_user_id`,`promote_user_code`,`promote_user_name`,`is_accept`,`task_plan_id`,`task_year`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
      (280,1,1,196,190,'bbb',0,421,2026,196,190,'aaa','2026-01-25 15:13:24',0,0,'','1948-07-28 23:59:59')
     ,(281,1,1,196,190,'bbb',0,521,2026,323,310,'bbb','2026-01-25 15:13:24',0,0,'','1948-07-28 23:59:59')
     ,(282,1,1,196,190,'bbb',0,621,2026,496,492,'ccc','2026-01-25 15:13:24',0,0,'','1948-07-28 23:59:59')
     ;

DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
      (421,187,24,'タスク名称2',2026,0,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-6-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00')
     ,(521,187,24,'タスク名称2',2026,0,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-6-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00')
     ,(621,187,24,'タスク名称2',2026,0,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-6-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00')
;


DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
   (196,190,'aaa',1,'aaa@politician.balanse.report.net',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(323,310,'bbb',1,'bbb@politician.balanse.report.net',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(496,492,'ccc',1,'ccc@politician.balanse.report.net',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
;

DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;

INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1,'aaa@politician.balanse.report.net',1,'manager',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59');
  
