DELETE FROM `riyousha_combine_org`;
ALTER TABLE `riyousha_combine_org` auto_increment = 0;

DELETE FROM `riyousha_combine_org_temp`;
ALTER TABLE `riyousha_combine_org_temp` auto_increment = 0;

  
INSERT INTO `riyousha_combine_org_temp` (`riyousha_combine_org_temp_id`,`riyousha_combine_org_temp_code`,`is_latest`,`riyousha_role`,`person_code`,`person_riyousha_code`,`person_riyousha_name`,`org_riyousha_code`,`org_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
   VALUES 
   -- QueryParamで取得する
    (731,654,1,'manager',186,246,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   -- QueryParamで取得する(最新でない)
    ,(732,654,0,'manager',186,246,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   -- QueryParamで取得する(権限が合わない)
    ,(733,654,1,'kanrensha_person',186,246,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   -- QueryParamで取得する(個人コードが合わない)
    ,(734,654,1,'manager',186,398,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   -- QueryParamで取得する(組織コードが合わない)
    ,(735,654,1,'manager',186,246,'管理者太郎',4755,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   -- QueryParamで取得する(該当するがId順／そもそも登録させちゃダメ?)
    ,(736,654,1,'manager',186,246,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   -- ユーザコードで取得する
   ,(755,654,1,'partner_api',190,315,'管理者 マリア花子',217,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(756,655,1,'manager',190,274,'管理者 マリア花子',217,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(757,656,1,'partner_api',190,318,'運営者 太郎',217,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   -- 最新でないので除外
   ,(758,657,0,'ROLE_manager',190,186,'管理者次郎',217,'政治資金文書製作所',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ;
    
DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
    (421,187,24,'タスク名称2',2026,0,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-06-06 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 最新でない
   ,(422,187,24,'タスク名称2',2026,1,0,1,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-06-07 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 終了すみ
   ,(423,187,24,'タスク名称2',2026,1,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',239,224,'ユーザ','2025-06-08 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 対象ユーザでない
   ,(424,187,24,'タスク名称2',2026,1,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-06-09 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 件数外
   ,(425,187,24,'タスク名称2',2026,1,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-06-10 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 抽出5
   ,(426,187,24,'タスク名称2',2026,1,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-06-11 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 抽出4
   ,(427,187,24,'タスク名称2',2026,1,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-06-12 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 抽出3
   ,(428,187,24,'タスク名称2',2026,1,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-06-13 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 抽出2
   ,(429,187,24,'タスク名称2',2026,1,0,0,0,'2022-12-05 12:34:56','2022-12-06 23:45:12','admin','pass',213,190,'ユーザ','2025-06-14 23:45:12',1,1,'ユーザ','1948-07-29 00:00:00') -- 抽出1
;

DELETE FROM `riyousha_manager_master`;
ALTER TABLE `riyousha_manager_master` auto_increment = 0;

INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (282,272,1,1,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:12:30',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (283,273,2,2,0,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (284,274,3,3,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_manager_master` (`riyousha_manager_master_id`,`riyousha_manager_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
    VALUES (285,275,4,4,1,'運営者 太郎','うんえいしゃ たろう','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444運営者太郎うんえいしゃたろう',196,190,'管理人 花子','2025-11-24 14:40:09',0,0,'','1948-07-28 23:59:59');

DELETE FROM `riyousha_partner_api_master`;
ALTER TABLE `riyousha_partner_api_master` auto_increment = 0;

INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (325,315,1,1,1,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:12:30',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (326,316,2,2,0,'管理者 マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:38:24',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES (327,317,3,3,1,'パートナー マリア花子','うんえいしゃ まりあはなこ','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ',196,190,'管理人 花子','2025-11-24 14:39:09',0,0,'','1948-07-28 23:59:59');
INSERT INTO `riyousha_partner_api_master` (`riyousha_partner_api_master_id`,`riyousha_partner_api_master_code`,`riyousha_person_property_id`,`riyousha_person_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
    VALUES (328,318,4,4,1,'運営者 太郎','うんえいしゃ たろう','宮崎県実在市山麓町3丁目6の9星形ビル444','宮崎県実在市山麓町3丁目6の9星形ビル444運営者太郎うんえいしゃたろう',196,190,'管理人 花子','2025-11-24 14:40:09',0,0,'','1948-07-28 23:59:59');

    
DELETE FROM `riyousha_org_master`;
ALTER TABLE `riyousha_org_master` auto_increment = 0;

INSERT INTO `riyousha_org_master` (`riyousha_org_master_id`,`riyousha_org_master_code`,`riyousha_org_property_id`,`riyousha_org_property_code`,`is_latest`,`all_name`,`all_name_kana`,`address_all`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES
   (314,214,423,323,0,'利用者IT組織','りようしゃそしき','宮崎県架空市山麓町','利用者IT組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(315,215,424,324,1,'利用者組織','りようしゃそしき','宮崎県架空市山麓町','利用者組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(316,216,425,325,1,'利用者IT組織','りようしゃそしき','宮崎県架空市山麓町','利用者IT組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ,(317,217,426,326,1,'政治資金文書製作所','りようしゃそしき','宮崎県架空市山麓町','利用者IT組織宮崎県架空市山麓町',214,190,'test','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
  ;

DELETE FROM `riyousha_combine_org`;
ALTER TABLE `riyousha_combine_org` auto_increment = 0;
  
