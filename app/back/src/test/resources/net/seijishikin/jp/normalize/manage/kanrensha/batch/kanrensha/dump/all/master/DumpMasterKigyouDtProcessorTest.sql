DELETE FROM `kanrensha_kigyou_dt_master`;
ALTER TABLE `kanrensha_kigyou_dt_master` auto_increment = 0;

DELETE FROM `kanrensha_kigyou_dt_access`;
ALTER TABLE `kanrensha_kigyou_dt_access` auto_increment = 0;

DELETE FROM `kanrensha_kigyou_dt_property`;
ALTER TABLE `kanrensha_kigyou_dt_property` auto_increment = 0;



INSERT INTO `kanrensha_kigyou_dt_master` (`kanrensha_kigyou_dt_master_id`,`kigyou_dt_kanrensha_code`,`houjin_no`,`is_latest`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1,'1-2345-67-7RJC4W-BLXyCUF','1234567',1,'テスト株式会社','宮崎県架空市橘通東２丁目１０−１','代表者　太郎','テスト株式会社',196,190,'管理人　太郎','2025-12-27 14:51:37',0,0,'','1948-07-28 23:59:59');


INSERT INTO `kanrensha_kigyou_dt_access` (`kanrensha_kigyou_dt_access_id`,`kanrensha_kigyou_dt_id`,`kigyou_dt_kanrensha_code`,`kanrensha_name`,`is_latest`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1,1,'1-2345-67-7RJC4W-BLXyCUF','テスト株式会社',1,'0985','26','7132','test@example.com','https://my-portal/index.html',0,0,'弱小SNS','https://jyakusho-sns/','@taro123456',196,190,'管理人　太郎','2025-12-27 14:51:37',0,0,'','1948-07-28 23:59:59');

INSERT INTO `kanrensha_kigyou_dt_property` (`kanrensha_kigyou_dt_property_id`,`kanrensha_kigyou_dt_id`,`kigyou_dt_kanrensha_code`,`kanrensha_name`,`is_latest`,`houjin_sbts`,`is_foreign`,`org_name_kana`,`is_shiten`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (1,1,'1-2345-67-7RJC4W-BLXyCUF','テスト株式会社',1,'401',1,'テストカブシキガイシャ',1,'P12345',196,190,'管理人　太郎','2025-12-27 14:51:37',0,0,'','1948-07-28 23:59:59');
  