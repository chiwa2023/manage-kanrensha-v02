DELETE FROM `user_new`;

INSERT INTO `user_new` (`email`,`regist_code`,`limit_datetime`,`verify_token`,`verify_limit_date_time`)  VALUES
  -- 認証した
    ('aaa@example.com','2111','2022-12-05 12:34:56',NULL,NULL)
  -- 認証しなかった
   ,('bbb@example.com','3111','2022-12-05 12:34:56','3112','2022-12-05 12:34:56')
  -- 期限切れでないので残る
   ,('ccc@example.com','2111','2022-12-05 12:34:56','2112','2090-01-03 10:08:18')
;

DELETE FROM `partner_access_token`;
ALTER TABLE `partner_access_token` auto_increment = 0;

INSERT INTO `partner_access_token` (`partner_access_token_id`,`user_code`,`user_name`,`access_token_hash`,`expires_at`,`created_at`,`last_used_at`,`revoked_at`)
  VALUES 
     (325,22,'111','339912','2027-12-05 00:00:00','2022-12-05 00:00:00',NULL,NULL)
    ,(326,190,'name_aaa','12345','2023-06-05 12:34:56','2022-12-05 12:34:56','2023-1-2 12:34:56',NULL)
    ,(327,191,'name','98765','2023-06-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00',null) 
    ;

DELETE FROM `user_password_reset`;

INSERT INTO `user_password_reset` (`email`,`regist_code`,`limit_datetime`)    VALUES 
    ('ddd@seijishikin.net','12345','2099-12-05 00:00:00')
   ,('eee@seijishikin.net','23456','2024-09-05 00:00:00')
;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`, `is_alert_task_start`,`is_alert_task_end`, `insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
   (2,2,'自動システム処理',1,'aaa@politician.balanse.report.net',0,0,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(323,310,'bbb',1,'bbb@politician.balanse.report.net',1,1,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;

   
    