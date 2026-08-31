DELETE FROM `riyousha_manager_master`;
DELETE FROM `riyousha_person_property`;

ALTER TABLE `riyousha_manager_master` auto_increment = 0;
ALTER TABLE `riyousha_person_property` auto_increment = 0;

DELETE FROM `riyousha_combine_org`;
ALTER TABLE `riyousha_combine_org` auto_increment = 0;

DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;

INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES 
      (649,843,741,1,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:12:30',0,0,'','1948-07-28 23:59:59')
    , (650,12,2,2,0,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59')
    , (651,661,743,3,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59')
    , (652,661,744,4,1,'運営者 太郎','うんえいしゃ たろう','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444運営者太郎うんえいしゃたろう',196,190,'管理人 花子','2025-11-24 14:40:09',0,0,'','1948-07-28 23:59:59')
    ;

INSERT INTO `riyousha_person_property` (`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`last_name`,`first_name`,`middle_name`,`last_name_kana`,`first_name_kana`,`middle_name_kana`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
    (741,1,1,'管理者','花子','マリア','うんえいしゃ','はなこ','まりあ','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:12:29',0,0,'','1948-07-28 23:59:59')
  , (742,2,1,'管理者','花子','マリア','うんえいしゃ','はなこ','まりあ','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59')
  , (743,3,1,'管理者','花子','マリア','うんえいしゃ','はなこ','まりあ','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59')
  , (744,4,1,'管理者','花子','マリア','うんえいしゃ','はなこ','まりあ','987','5432','宮崎県実在市山麓町','3丁目6の9','星形ビル444','123456','2345','3456','4567','5678','6789','567','8901','2345','test@example.com','http://example.com/',1,2,'無名SNS','http://jakushou.sns.net/','@123hanakao',196,190,'管理人　花子','2025-11-24 14:40:08',0,0,'','1948-07-28 23:59:59')
  ;

INSERT INTO `riyousha_combine_org` (`riyousha_combine_org_id`,`riyousha_combine_org_code`,`is_latest`,`riyousha_role`,`person_riyousha_code`,`person_riyousha_name`,`org_riyousha_code`,`org_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
   VALUES 
    (753,654,1,'manager',292,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(754,654,1,'manager',661,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(755,654,1,'manager',661,'管理者太郎',216,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(756,654,1,'partner_api',661,'管理者花子',216,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(757,654,1,'admin',661,'管理者直子',216,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(758,654,0,'manager',661,'管理者次郎',216,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ;

  
INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`kanrensha_code`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (80,'qqq@politician.balanse.report.net',1,'manager','dvsdf',843,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(81,'aaa@politician.balanse.report.net',1,'manager','',244,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(82,'bbb@politician.balanse.report.net',1,'manager','ndfx',661,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(83,'bbb@politician.balanse.report.net',1,'partner_api','ndfx',913,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(84,'bbb@politician.balanse.report.net',1,'kanrensha_person','ndfx',913,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(190,'ccc@politician.balanse.report.net',1,'manager','',0,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;
