DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

DELETE FROM `riyousha_combine_org_temp`;
ALTER TABLE `riyousha_combine_org_temp` auto_increment = 0;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (321,311,'bbb',1,'bbb@politician.balanse.report.net',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;
  
DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (423,802,'利用者組織個人招待',1,'manager,partner_api','【transferPass】で作業を行ってください','/accept-combine-riyousha','personCode,orgCode,userRole',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00');

  
DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;

INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (1,'aaa@politician.balanse.report.net',1,'manager',0,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(2,'bbb@politician.balanse.report.net',1,'partner_api',979,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(3,'ccc@politician.balanse.report.net',0,'manager',0,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59') -- 最新でない
  ,(4,'ddd-bbb@politician.balanse.report.net',1,'kanrensha_person',0,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(5,'eee@politician.balanse.report.net',1,'partner_api',0,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59') -- roleが抽出対象でない
  ,(6,'ddd@politician.balanse.report.net',1,'kanrensha_person',0,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;
  
DELETE FROM `riyousha_partner_api_master`;
ALTER TABLE `riyousha_partner_api_master` auto_increment = 0;

INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (325,315,1,1,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:12:30',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (326,316,2,2,0,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (327,317,3,3,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
    VALUES (328,979,4,4,1,'運営者 太郎','うんえいしゃ たろう','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444運営者太郎うんえいしゃたろう',196,190,'管理人 花子','2025-11-24 14:40:09',0,0,'','1948-07-28 23:59:59');
  