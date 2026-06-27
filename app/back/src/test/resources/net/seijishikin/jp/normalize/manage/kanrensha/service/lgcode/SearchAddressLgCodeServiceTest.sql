DELETE FROM `address_all_city`;
ALTER TABLE `address_all_city` auto_increment = 0;

-- 履歴
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (238,'032034',0,'イワテケンオオフナトシ','岩手県','','大船渡市','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');

-- 検索語が非該当(県は検索語検索の対象外)
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (239,'032018',1,'イワテケンモリオカシ','岩手県','特別郡','盛岡市','特別区','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');

-- 県コードが非該当
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (240,'042018',1,'イワテケンモリオカシ','岩手県','大手郡','盛岡市','特別区','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');

-- 郡部該当
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (241,'032018',1,'イワテケンモリオカシ','岩手県','大手郡','盛岡市','特別区','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');

-- 市該当
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (242,'032018',1,'イワテケンモリオカシ','岩手県','特別郡','大手市','特別区','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');

-- 特別区該当
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (243,'032026',1,'イワテケンミヤコシ','岩手県','','宮古市','大手区','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');

-- 廃止日決定しているが未来
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (244,'032018',1,'イワテケンモリオカシ','岩手県','特別郡','大手市','特別区','1947-11-13','2999-12-05',196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');
  
-- 発行日null
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (245,'032018',1,'イワテケンモリオカシ','岩手県','特別郡','大手市','特別区',NULL,NULL,196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');
  
-- 発行日未来
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (246,'032018',1,'イワテケンモリオカシ','岩手県','特別郡','大手市','特別区','2999-11-13',NULL,196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');
  
-- 廃止日過去
INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name_kana`,`pref`,`county`,`city`,`ward`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (247,'032018',1,'イワテケンモリオカシ','岩手県','特別郡','大手市','特別区','1947-11-13','1948-12-05',196,190,'管理人　太郎','2026-03-28 17:36:07',0,0,'','1948-07-28 23:59:59');
 

