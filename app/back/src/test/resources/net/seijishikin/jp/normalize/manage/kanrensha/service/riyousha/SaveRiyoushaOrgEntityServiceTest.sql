DELETE FROM `riyousha_org_master`;
DELETE FROM `riyousha_org_property`;

ALTER TABLE `riyousha_org_master` auto_increment = 0;
ALTER TABLE `riyousha_org_property` auto_increment = 0;

INSERT INTO `riyousha_org_master` (`riyousha_org_master_id`,`riyousha_org_master_code`,`riyousha_org_property_id`,`riyousha_org_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES
   (314,214,423,323,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(315,215,424,324,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(316,216,425,325,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(317,217,426,326,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ;

  
INSERT INTO `riyousha_org_property` (`riyousha_org_property_id`,`riyousha_org_property_code`,`is_latest`,`org_name`,`org_name_kana`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
   (423,323,1,'利用者組織','りようしゃそしき','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  ,(424,324,1,'利用者組織','りようしゃそしき','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  ,(425,325,1,'利用者組織','りようしゃそしき','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  ,(426,326,1,'利用者組織','りようしゃそしき','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  ;
