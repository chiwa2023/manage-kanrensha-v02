DELETE FROM `riyousha_org_master`;

ALTER TABLE `riyousha_org_master` auto_increment = 0;

INSERT INTO `riyousha_org_master` (`riyousha_org_master_id`,`riyousha_org_master_code`,`riyousha_org_property_id`,`riyousha_org_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES
   (314,214,423,323,0,'利用者IT組織','りようしゃそしき','宮崎県架空市山麓町','利用者IT組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(315,215,424,324,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(316,216,425,325,1,'利用者IT組織','りようしゃそしき','宮崎県架空市山麓町','利用者IT組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(317,217,426,326,1,'利用者IT組織','りようしゃそしき','宮崎県架空市山麓町','利用者IT組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ;
