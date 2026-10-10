DELETE FROM `login_status`;
ALTER TABLE `login_status` auto_increment = 0;

INSERT INTO `login_status` (`email`,`password`,`is_success`,`fail_reason`,`disabled`,`disabled_reason`,`login_time`,`pass_change_time`) VALUES 
('aaa@politician.balanse.report.net','$2a$10$VAAGICAVk.O.5ct7Vm/nauN0jDqo6aanBcJsXoW6Zeug6i3APbvhu',1,'',0,'','2026-01-03 16:10:25','2026-01-03 16:10:25')
;

DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`  ,`is_alert_task_start`  ,`is_alert_task_end`       ,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  (81,80,'aaa',1,'aaa@politician.balanse.report.net',0,0 ,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(82,83,'bbb',1,'bbb@politician.balanse.report.net',1,1 ,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(83,80,'aaa',0,'aaa@politician.balanse.report.net',0,0 ,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(208,196,'nnnn',1,'nnnn@politician.balanse.report.net',0,0,208,196,'nnnn','2026-05-05 10:43:56',0,0,'','1948-07-28 23:59:59')
  ,(206,194,'llll',1,'llll@politician.balanse.report.net',0,0,206,194,'llll','2026-05-05 09:40:56',0,0,'','1948-07-28 23:59:59')
  ,(207,195,'mmmm',1,'mmmm@politician.balanse.report.net',0,0,207,195,'mmmm','2026-05-05 10:05:06',0,0,'','1948-07-28 23:59:59')
  ;

INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`kanrensha_code`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (1,'aaa@politician.balanse.report.net',1,'manager','dvsdf',244,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(2,'bbb@politician.balanse.report.net',1,'manager','',14,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(156,'nnnn@politician.balanse.report.net',1,'kanrensha_person','DH-mkzsu-2mMW-rB8Y-Pl4zr',0,208,196,'nnnn','2026-05-05 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(152,'llll@politician.balanse.report.net',1,'kanrensha_kigyou_dt','1-2345-67-8903cQ-JAQ8qP1',0,206,194,'llll','2026-05-05 09:58:23',0,0,'','1948-07-28 23:59:59')
  ,(154,'mmmm@politician.balanse.report.net',1,'kanrensha_seijidantai','123-4567-0FRi-6kEt-RF77W',0,207,195,'mmmm','2026-05-05 10:07:44',0,0,'','1948-07-28 23:59:59')
  ;
  
