DELETE FROM `kanrensha_seijidantai_master`;
ALTER TABLE `kanrensha_seijidantai_master` auto_increment = 0;

DELETE FROM `kanrensha_seijidantai_access`;
ALTER TABLE `kanrensha_seijidantai_access` auto_increment = 0;

DELETE FROM `kanrensha_seijidantai_property`;
ALTER TABLE `kanrensha_seijidantai_property` auto_increment = 0;


INSERT INTO `kanrensha_seijidantai_master` (`kanrensha_seijidantai_master_id`,`seijidantai_kanrensha_code`,`poli_org_no`,`is_latest`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1,'984-321V-kZNH-uJUw-Alcr1','98-4321',1,'ちゃらんぽらん政治団体','宮崎県架空市橘通東２丁目１０−１','代表者　太郎','03','ちゃらんぽらん政治団体',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59');

  
INSERT INTO `kanrensha_seijidantai_access` (`kanrensha_seijidantai_access_id`,`kanrensha_seijidantai_id`,`seijidantai_kanrensha_code`,`kanrensha_name`,`is_latest`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1,1,'984-321V-kZNH-uJUw-Alcr1','ちゃらんぽらん政治団体',1,'0985','26','7132','test@example.com','https://my-portal/index.html',0,0,'弱小SNS','https://jyakusho-sns/','@taro123456',196,190,'管理人　太郎','2025-12-27 17:20:40',0,0,'','1948-07-28 23:59:59');
  
  
INSERT INTO `kanrensha_seijidantai_property` (`kanrensha_seijidantai_property_id`,`kanrensha_seijidantai_id`,`seijidantai_kanrensha_code`,`kanrensha_name`,`is_latest`,`org_name_kana`,`org_delegate_code`,`account_mgr_code`,`account_mgr_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1,1,'984-321V-kZNH-uJUw-Alcr1','ちゃらんぽらん政治団体',1,'ちゃらんぽらんせいじだんたい','P12345','P98765','会計責任者　花子',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59');
  