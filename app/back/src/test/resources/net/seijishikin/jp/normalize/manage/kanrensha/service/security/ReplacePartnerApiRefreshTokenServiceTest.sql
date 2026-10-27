DELETE FROM `partner_access_token`;
ALTER TABLE `partner_access_token` auto_increment = 0;

INSERT INTO `partner_access_token` (`partner_access_token_id`,`user_code`,`user_name`,`access_token_hash`,`expires_at`,`created_at`,`last_used_at`,`revoked_at`)
  VALUES 
     (325,22,'111','339912','2022-12-05 00:00:00','2022-12-05 00:00:00',NULL,NULL) --他人
    ,(326,190,'name_aaa','12345','2023-06-05 12:34:56','2022-12-05 12:34:56','2023-1-2 12:34:56',NULL) -- 有効
    ,(327,190,'name','98765','2023-06-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00',null) -- 有効
    ;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;
DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  (196,190,'aaa',1,'aaa@politician.balanse.report.net',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(82,83,'bbb',1,'bbb@politician.balanse.report.net',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59');

INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (1,'aaa@politician.balanse.report.net',1,'manager',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(2,'bbb@politician.balanse.report.net',1,'manager',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59');

