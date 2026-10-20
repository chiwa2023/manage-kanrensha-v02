DELETE FROM `timer_yotei`;
ALTER TABLE `timer_yotei` auto_increment = 0;

INSERT INTO `timer_yotei` (`timer_yotei_id`,`timer_yotei_code`,`timer_yotei_name`,`is_latest`,`yoyaku_task_kbn`,`next_timestamp`,`previous_timestamp`,`is_repeat`,`is_pause`,`end_timestamp`,`sabun_timestamp`,`is_period`,`year_period`,`month_period`,`day_period`,`hour_period`,`year_pointed`,`month_pointed`,`day_pointed`,`hour_pointed`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  -- 最新でないデータしか存在しない→起動すべき予定が存在しない
    (123,116,'サンプルタスク',0,243,'2023-06-11 12:34:56','2022-12-05 12:34:56',1,1,'2023-01-01 00:00:00','2022-07-01 00:00:00',1,2022,7,4,9,2026,10,28,1,213,190,'ユーザ','2022-12-05 00:00:00',0,0,NULL,'1947-07-28 23:59:59')
;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`, `is_alert_task_start`,`is_alert_task_end`, `insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
   (2,2,'自動システム処理',1,'aaa@politician.balanse.report.net',1,1,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(323,310,'bbb',1,'bbb@politician.balanse.report.net',1,1,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;

DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_finish`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
     (421,311,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
   , (422,312,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
   , (423,313,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
   , (424,314,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
   , (425,315,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
   , (426,316,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
   , (427,317,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
   , (428,318,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
   , (429,319,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
;

