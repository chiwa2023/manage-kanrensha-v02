DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;


/**
INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`  ,`is_alert_task_start`  ,`is_alert_task_end`       ,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  (80,80,'aaa',1,'qqq@politician.balanse.report.net',0,0 ,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(81,80,'aaa',1,'aaa@politician.balanse.report.net',0,0 ,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(82,83,'bbb',1,'bbb@politician.balanse.report.net',1,1 ,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(196,83,'bbb',1,'ccc@politician.balanse.report.net',1,1 ,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;
*/

/*
INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`kanrensha_code`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (80,'qqq@politician.balanse.report.net',1,'manager','dvsdf',0,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(81,'aaa@politician.balanse.report.net',1,'manager','',244,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(82,'bbb@politician.balanse.report.net',1,'manager','ndfx',913,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(83,'bbb@politician.balanse.report.net',1,'manager','ndfx',913,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(84,'bbb@politician.balanse.report.net',1,'manager','ndfx',913,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(190,'ccc@politician.balanse.report.net',1,'manager','',0,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;
*/
  
DELETE FROM `kanrensha_person_access`;
ALTER TABLE `kanrensha_person_access` auto_increment = 0;

DELETE FROM `kanrensha_person_address`;
ALTER TABLE `kanrensha_person_address` auto_increment = 0;

DELETE FROM `kanrensha_person_master`;
ALTER TABLE `kanrensha_person_master` auto_increment = 0;

DELETE FROM `kanrensha_person_property`;
ALTER TABLE `kanrensha_person_property` auto_increment = 0;

INSERT INTO `kanrensha_person_master` (`kanrensha_person_master_id`,`person_kanrensha_code`,`is_latest`,`kanrensha_name`,`all_address`,`person_shokugyou`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (390,'DH-mkzsu-2mMW-rB8Y-Pl4zr',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','素浪人','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ,(391,'49261-2mMW-rB-Pl4zrX',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','素浪人','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ,(392,'5A231kzsu-2mMW-rBPl4zr',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','素浪人','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ;


INSERT INTO `kanrensha_person_access` (`kanrensha_person_access_id`,`kanrensha_person_id`,`person_kanrensha_code`,`kanrensha_name`,`is_latest`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (656,390,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,'0985','26','7132','test@example.com','https://my-portal/index.html',0,0,'弱小SNS','https://jyakusho-sns/','@taro123456',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ,(657,391,'49261-2mMW-rB-Pl4zrX','迂回献金　ミカエル太郎',1,'0985','26','7132','test@example.com','https://my-portal/index.html',0,0,'弱小SNS','https://jyakusho-sns/','@taro123456',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ,(658,392,'5A231kzsu-2mMW-rBPl4zr','迂回献金　ミカエル太郎',1,'0985','26','7132','test@example.com','https://my-portal/index.html',0,0,'弱小SNS','https://jyakusho-sns/','@taro123456',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ;


INSERT INTO `kanrensha_person_address` (`kanrensha_person_address_id`,`kanrensha_person_id`,`person_kanrensha_code`,`kanrensha_name`,`is_latest`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (774,390,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ,(775,391,'49261-2mMW-rB-Pl4zrX','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ,(776,392,'5A231kzsu-2mMW-rBPl4zr','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
;

INSERT INTO `kanrensha_person_property` (`kanrensha_person_property_id`,`kanrensha_person_id`,`person_kanrensha_code`,`kanrensha_name`,`is_latest`,`is_foreign`,`last_name`,`first_name`,`middle_name`,`last_name_kana`,`first_name_kana`,`middle_name_kana`,`gyoushu`,`yakushoku`,`shokugyou_user_write`,`kigyou_dt_no`,`kigyou_dt_address`,`kigyou_dt_name`,`is_shokyou_edit`,`is_shokyou_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (824,390,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,1,'太郎','迂回献金','ミカエル','たろう','うかいけんきん','みかえる','農林業','部長','農作業','1-2345','山梨県実在市湖畔町','ほったらかし農園',1,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ,(825,391,'49261-2mMW-rB-Pl4zrX','迂回献金　ミカエル太郎',1,1,'太郎','迂回献金','ミカエル','たろう','うかいけんきん','みかえる','農林業','部長','農作業','1-2345','山梨県実在市湖畔町','ほったらかし農園',1,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ,(826,392,'5A231kzsu-2mMW-rBPl4zr','迂回献金　ミカエル太郎',1,1,'太郎','迂回献金','ミカエル','たろう','うかいけんきん','みかえる','農林業','部長','農作業','1-2345','山梨県実在市湖畔町','ほったらかし農園',1,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ;

DELETE FROM `riyousha_manager_master`;
ALTER TABLE `riyousha_manager_master` auto_increment = 0;

INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (101,11,1,1,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:12:30',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (102,12,2,2,0,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (103,12,3,3,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
    VALUES (104,14,4,4,1,'運営者 太郎','うんえいしゃ たろう','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444運営者太郎うんえいしゃたろう',196,190,'管理人 花子','2025-11-24 14:40:09',0,0,'','1948-07-28 23:59:59');

DELETE FROM `riyousha_partner_api_master`;
ALTER TABLE `riyousha_partner_api_master` auto_increment = 0;

INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (201,21,1,1,1,'パートナー パウロ聡','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:12:30',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (202,22,2,2,0,'パートナー パウロ聡','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (203,22,3,3,1,'パートナー パウロ聡','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
    VALUES (204,24,4,4,1,'運営者 太郎','うんえいしゃ たろう','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444運営者太郎うんえいしゃたろう',196,190,'管理人 花子','2025-11-24 14:40:09',0,0,'','1948-07-28 23:59:59');
  
  