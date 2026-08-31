DELETE FROM `wk_tbl_address_city`;
ALTER TABLE `wk_tbl_address_city` auto_increment = 0;

DELETE FROM `address_all_city`;
ALTER TABLE `address_all_city` auto_increment = 0;

DELETE FROM `address_city_delete`;
ALTER TABLE `address_city_delete` auto_increment = 0;

DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;


INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`pref`,`county`,`city`,`ward`,`address_name_kana`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES 
     (219,'112233',0,'テスト県','テスト郡','','','てすとけんてすとぐん','2038-01-02','1948-07-28',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(220,'36852',1,'和歌山県','','実在市' ,'','わかやまけんじつざいし','1948-07-30','2038-01-03',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(221,'69512',1,'宮崎県','','架空市','','てすとけんてすとぐん','2038-01-02','1948-07-28',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(222,'69512',1,'宮崎県','','架空市','','てすとけんてすとぐん','2038-01-02','1948-07-28',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(223,'69512',1,'宮崎県','','架空市','','てすとけんてすとぐん','2038-01-02','1948-07-28',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(224,'82763',1,'山梨県','実在郡','','湖畔町','やまなしけんじつざいぐんこはんまち','1948-06-29',null,196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ;

DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;
INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  (136,175,'サンプルタスク情報',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(137,318,'サンプルタスク情報',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(138,319,'サンプルタスク情報',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
