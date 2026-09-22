DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (424,401,'APIパートナトークン切れ',1,'','長期トークンが期限切れとなります。サイトにアクセスして更新してください【transferPass】','/partner-api/token-replace','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ;

DELETE FROM `partner_access_token`;
ALTER TABLE `partner_access_token` auto_increment = 0;


INSERT INTO `partner_access_token` (`partner_access_token_id`,`user_code`,`user_name`,`access_token_hash`,`expires_at`,`created_at`,`last_used_at`,`revoked_at`,`ip_address`)
  VALUES 
     -- 当日(期限切れ)
     (320,386,'name','627eeabfb9477ebc73c916f38bee30800','2025-07-01 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 1日前(取得)
    ,(321,80,'name','627eeabfb9477ebc73c916f38bee30801','2025-07-02 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 2日前(取得)
    ,(322,83,'name','627eeabfb9477ebc73c916f38bee30802','2025-07-03 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 3日前(対象外)
    ,(323,387,'name','627eeabfb9477ebc73c916f38bee30803','2025-07-04 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 6日前(対象外)
    ,(324,388,'name','627eeabfb9477ebc73c916f38bee30804','2025-07-07 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 7日前(取得)
    ,(325,194,'name','627eeabfb9477ebc73c916f38bee30805','2025-07-08 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 8日前(対象外)
    ,(326,389,'name','627eeabfb9477ebc73c916f38bee30806','2025-07-09 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 16日前(対象外)
    ,(327,390,'name','627eeabfb9477ebc73c916f38bee30807','2025-07-17 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 17日前(取得)
    ,(328,195,'name','627eeabfb9477ebc73c916f38bee30808','2025-07-18 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
     -- 18日前(対象外)
    ,(329,391,'name','627eeabfb9477ebc73c916f38bee30809','2025-07-19 00:00:01','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1')
    ;
      
DELETE FROM `login_status`;

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
  ;

  
        
