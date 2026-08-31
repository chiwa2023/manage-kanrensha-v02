DELETE FROM `riyousha_manager_master`;
DELETE FROM `riyousha_person_property`;

ALTER TABLE `riyousha_manager_master` auto_increment = 0;
ALTER TABLE `riyousha_person_property` auto_increment = 0;


INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (1,11,1,1,1,'管理者　マリア花子','うんえいしゃ　まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子うんえいしゃまりあはなこ',196,190,'管理人　花子','2025-11-24 14:12:30',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (2,12,2,2,1,'管理者　マリア花子','うんえいしゃ　まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子うんえいしゃまりあはなこ',196,190,'管理人　花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (3,13,3,3,1,'管理者　マリア花子','うんえいしゃ　まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子うんえいしゃまりあはなこ',196,190,'管理人　花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (4,14,4,4,1,'管理者　マリア花子','うんえいしゃ　まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子うんえいしゃまりあはなこ',196,190,'管理人　花子','2025-11-24 14:40:09',0,0,'','1948-07-28 23:59:59');


INSERT INTO `riyousha_person_property` (`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`last_name`,`first_name`,`middle_name`,`last_name_kana`,`first_name_kana`,`middle_name_kana`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (1,1,1,'管理者','花子','マリア','うんえいしゃ','はなこ','まりあ','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_person_property` (`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`last_name`,`first_name`,`middle_name`,`last_name_kana`,`first_name_kana`,`middle_name_kana`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (2,2,1,'管理者','花子','マリア','うんえいしゃ','はなこ','まりあ','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_person_property` (`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`last_name`,`first_name`,`middle_name`,`last_name_kana`,`first_name_kana`,`middle_name_kana`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (3,3,1,'管理者','花子','マリア','うんえいしゃ','はなこ','まりあ','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_person_property` (`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`last_name`,`first_name`,`middle_name`,`last_name_kana`,`first_name_kana`,`middle_name_kana`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (4,4,1,'管理者','花子','マリア','うんえいしゃ','はなこ','まりあ','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:40:08',0,0,'','1948-07-28 23:59:59');

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
  ;

INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`kanrensha_code`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (1,'aaa@politician.balanse.report.net',1,'admin','dvsdf',244,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(2,'bbb@politician.balanse.report.net',1,'manager','',14,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(156,'nnnn@politician.balanse.report.net',1,'kanrensha_person','DH-mkzsu-2mMW-rB8Y-Pl4zr',0,208,196,'nnnn','2026-05-05 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(152,'llll@politician.balanse.report.net',1,'kanrensha_kigyou_dt','1-2345-67-8903cQ-JAQ8qP1',0,206,194,'llll','2026-05-05 09:58:23',0,0,'','1948-07-28 23:59:59')
  ,(154,'mmmm@politician.balanse.report.net',1,'kanrensha_seijidantai','123-4567-0FRi-6kEt-RF77W',0,207,195,'mmmm','2026-05-05 10:07:44',0,0,'','1948-07-28 23:59:59')
  ;
  
    