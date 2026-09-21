DELETE FROM `login_status`;

DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 1;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 80;

INSERT INTO `login_status` (`email`,`password`,`is_success`,`fail_reason`,`disabled`,`disabled_reason`,`login_time`,`pass_change_time`)
  VALUES 
       ('aaa@politician.balanse.report.net','$2a$10$KLZCF1ao2ZOZ1NRTDhfYSuwcY0jHFgUoIOJE9S44z/mxA0WeR3yg6',1,'',0,'','2026-01-05 20:15:48','2026-01-05 20:15:48')
      ,('bbb@politician.balanse.report.net','$2a$10$KLZCF1ao2ZOZ1NRTDhfYSuwcY0jHFgUoIOJE9S44z/mxA0WeR3yg6',1,'',1,'','2000-01-05 20:15:48','2000-01-05 20:15:48')
      ,('nnnn@politician.balanse.report.net','$2a$10$.otLO/4XEMQGv1tVwaJ9ZOYPabzwr5eI6CBGdo0xTyrhvwMRCVx5a',1,'',0,'','2026-05-05 10:43:57','2026-05-05 10:43:57')
      ,('llll@politician.balanse.report.net','$2a$10$Z0pJXYQfXDRSxzg/mutzy.dTUB/.lN5RYWsY43WgRx23e16Vpg7hS',1,'',0,'','2026-05-05 09:40:56','2026-05-05 09:43:43')
      ,('mmmm@politician.balanse.report.net','$2a$10$EvuI7fvM4ejfSghbGEMPoe8GL7wDkVS30X63i8P0v/eYD6BW9Na6O',1,'',0,'','2026-05-05 10:05:06','2026-05-05 10:05:06')
      ;


INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`  ,`is_alert_task_start`  ,`is_alert_task_end`       ,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  (81,80,'aaa',1,'aaa@politician.balanse.report.net',0,0 ,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(82,83,'bbb',1,'bbb@politician.balanse.report.net',1,1 ,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(208,196,'nnnn',1,'nnnn@politician.balanse.report.net',0,0,208,196,'nnnn','2026-05-05 10:43:56',0,0,'','1948-07-28 23:59:59')
  ,(206,194,'llll',1,'llll@politician.balanse.report.net',0,0,206,194,'llll','2026-05-05 09:40:56',0,0,'','1948-07-28 23:59:59')
  ,(207,195,'mmmm',1,'mmmm@politician.balanse.report.net',0,0,207,195,'mmmm','2026-05-05 10:05:06',0,0,'','1948-07-28 23:59:59')
  ,(209,196,'oooo',1,'oooo@politician.balanse.report.net',0,0,208,196,'nnnn','2026-05-05 10:43:56',0,0,'','1948-07-28 23:59:59')
  ,(210,194,'pppp',1,'pppp@politician.balanse.report.net',0,0,206,194,'llll','2026-05-05 09:40:56',0,0,'','1948-07-28 23:59:59')
  ,(211,195,'qqqq',1,'qqqq@politician.balanse.report.net',0,0,207,195,'mmmm','2026-05-05 10:05:06',0,0,'','1948-07-28 23:59:59')

  ;

INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`kanrensha_code`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (1,'aaa@politician.balanse.report.net',1,'manager','dvsdf',244,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(2,'bbb@politician.balanse.report.net',1,'manager','',14,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  -- ここまで登録に関係ない  
  
  -- 廃止コード履歴(3人)  
  ,(161,'nnnn@politician.balanse.report.net',1,'kanrensha_person','98765',0,208,196,'nnnn','2026-05-08 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(162,'oooo@politician.balanse.report.net',0,'kanrensha_person','98765',0,208,196,'nnnn','2026-05-07 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(163,'pppp@politician.balanse.report.net',0,'kanrensha_person','98765',0,208,196,'nnnn','2026-05-06 10:46:28',0,0,'','1948-07-28 23:59:59')

  -- 存続コード履歴(3人)  
  ,(164,'llll@politician.balanse.report.net',1,'kanrensha_person','12345',0,208,196,'nnnn','2026-05-08 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(165,'qqqq@politician.balanse.report.net',0,'kanrensha_person','12345',0,208,196,'nnnn','2026-05-07 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(166,'mmmm@politician.balanse.report.net',0,'kanrensha_person','12345',0,208,196,'nnnn','2026-05-06 10:46:28',0,0,'','1948-07-28 23:59:59')

  
  -- 関係するユーザがそれぞれ何らかの形で関連者コードを所有している  
  ,(171,'oooo@politician.balanse.report.net',1,'kanrensha_person','aa-aa',0,208,196,'nnnn','2026-06-07 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(172,'pppp@politician.balanse.report.net',1,'kanrensha_person','bb-bb',0,208,196,'nnnn','2026-06-06 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(173,'qqqq@politician.balanse.report.net',0,'kanrensha_person','cc-cc',0,208,196,'nnnn','2026-06-06 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(174,'llll@politician.balanse.report.net',1,'kanrensha_person','dd-dd',0,208,196,'nnnn','2026-06-08 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(175,'mmmm@politician.balanse.report.net',1,'kanrensha_person','ee-ee',0,208,196,'nnnn','2026-06-06 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(176,'qqqq@politician.balanse.report.net',1,'kanrensha_person','ff-ff',0,208,196,'nnnn','2026-07-06 10:46:28',0,0,'','1948-07-28 23:59:59')
  
  ;
  
        
