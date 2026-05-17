DELETE FROM `riyousha_org_master`;
DELETE FROM `riyousha_org_property`;
DELETE FROM `riyousha_combine_org`;

ALTER TABLE `riyousha_org_master` auto_increment = 0;
ALTER TABLE `riyousha_org_property` auto_increment = 0;
ALTER TABLE `riyousha_combine_org` auto_increment = 0;

INSERT INTO `riyousha_org_master` (`riyousha_org_master_id`,`riyousha_org_master_code`,`riyousha_org_property_id`,`riyousha_org_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES
   (314,214,423,323,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(315,215,424,324,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(316,216,425,325,0,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(317,217,426,326,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ;
INSERT INTO `riyousha_org_master` (`riyousha_org_master_id`,`riyousha_org_master_code`,`riyousha_org_property_id`,`riyousha_org_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
   VALUES (318,216,427,325,1,'政治資金文書作成所','せいじしきんぶんしょさくせいしょ','和歌山県架空市湖畔町100番地の7星形ビル444','和歌山県架空市湖畔町100番地の7星形ビル444政治資金文書作成所せいじしきんぶんしょさくせいしょ',196,190,'管理人　太郎','2026-02-15 09:53:17',0,0,'','1948-07-28 23:59:59');
  
INSERT INTO `riyousha_org_property` (`riyousha_org_property_id`,`riyousha_org_property_code`,`is_latest`,`org_name`,`org_name_kana`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
   (423,323,1,'利用者組織','りようしゃそしき','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  ,(424,324,1,'利用者組織','りようしゃそしき','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  ,(425,325,1,'利用者組織','りようしゃそしき','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  ,(426,326,1,'利用者組織','りようしゃそしき','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  ;
INSERT INTO `riyousha_org_property` (`riyousha_org_property_id`,`riyousha_org_property_code`,`is_latest`,`org_name`,`org_name_kana`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (427,325,1,'政治資金文書作成所','せいじしきんぶんしょさくせいしょ','987','5432','和歌山県架空市湖畔町','100番地の7','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'弱小SNS','http://jakushou.sns.net/','@taro9898',196,190,'管理人　太郎','2026-02-15 09:53:17',0,0,'','1948-07-28 23:59:59');
  
  
INSERT INTO `riyousha_combine_org` (`riyousha_combine_org_id`,`riyousha_combine_org_code`,`is_latest`,`riyousha_role`,`person_code`,`person_riyousha_name`,`org_riyousha_code`,`org_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
   VALUES 
    (754,654,1,'ROLE_manager',246,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(755,654,1,'ROLE_partner_api',190,'管理者太郎',216,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(756,654,1,'ROLE_manager',191,'管理者花子',216,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(757,654,1,'ROLE_partner_api',192,'管理者直子',216,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(758,654,0,'ROLE_manager',193,'管理者次郎',216,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ;
  
  