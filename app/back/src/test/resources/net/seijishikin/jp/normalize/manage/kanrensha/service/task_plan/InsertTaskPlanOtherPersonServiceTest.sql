DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (321,311,'bbb',1,'bbb@politician.balanse.report.net',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;

  
DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (423,802,'利用者組織個人招待',1,'manager,partner_api','【transferPass】で作業を行ってください','/accept-combine-riyousha','personCode,orgCode',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00');
