DELETE FROM `kanrensha_kigyou_dt_address`;
ALTER TABLE `kanrensha_kigyou_dt_address` auto_increment = 0;

DELETE FROM `kanrensha_kigyou_dt_master`;
ALTER TABLE `kanrensha_kigyou_dt_master` auto_increment = 0;


INSERT INTO `kanrensha_kigyou_dt_address` (`kanrensha_kigyou_dt_address_id`,`kanrensha_kigyou_dt_id`,`kigyou_dt_kanrensha_code`,`kanrensha_name`,`is_latest`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)     VALUES 
  (2203,1,'1-2345-67-iRa0ko-l9ElEre','テスト株式会社',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 14:53:32',0,0,'','1948-07-28 23:59:59')
, (2204,1,'200','テスト株式会社',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 14:53:32',0,0,'','1948-07-28 23:59:59')

;

    
INSERT INTO `kanrensha_kigyou_dt_master` (`kanrensha_kigyou_dt_master_id`,`kigyou_dt_kanrensha_code`,`houjin_no`,`is_latest`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (1,'1-2345-67-iRa0ko-l9ElEre','1234567',1,'テスト株式会社','宮崎県架空市橘通東２丁目１０−１','代表者　太郎','テスト株式会社',196,190,'管理人　太郎','2025-12-27 14:51:37',0,0,'','1948-07-28 23:59:59')
, (2,'200','1234567',1,'テスト株式会社','宮崎県架空市橘通東２丁目１０−１　宮崎県庁','代表者　太郎','テスト株式会社',196,190,'管理人　太郎','2025-12-27 14:51:37',0,0,'','1948-07-28 23:59:59')
;

    
DELETE FROM `kanrensha_person_address`;
ALTER TABLE `kanrensha_person_address` auto_increment = 0;

DELETE FROM `kanrensha_person_master`;
ALTER TABLE `kanrensha_person_master` auto_increment = 0;


INSERT INTO `kanrensha_person_address` (`kanrensha_person_address_id`,`kanrensha_person_id`,`person_kanrensha_code`,`kanrensha_name`,`is_latest`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (2203,1,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
 ,(2204,1,'200','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
;



    
INSERT INTO `kanrensha_person_master` (`kanrensha_person_master_id`,`person_kanrensha_code`,`is_latest`,`kanrensha_name`,`all_address`,`person_shokugyou`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (1,'DH-mkzsu-2mMW-rB8Y-Pl4zr',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','素浪人','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
 ,(2,'200',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１　宮崎県庁','素浪人','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
;

    
DELETE FROM `kanrensha_seijidantai_address`;
ALTER TABLE `kanrensha_seijidantai_address` auto_increment = 0;

DELETE FROM `kanrensha_seijidantai_master`;
ALTER TABLE `kanrensha_seijidantai_master` auto_increment = 0;



INSERT INTO `kanrensha_seijidantai_address` (`kanrensha_seijidantai_address_id`,`kanrensha_seijidantai_id`,`seijidantai_kanrensha_code`,`kanrensha_name`,`is_latest`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (2203,1,'984-321V-kZNH-uJUw-Alcr1','ちゃらんぽらん政治団体',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 17:20:40',0,0,'','1948-07-28 23:59:59')
 ,(2204,1,'200','ちゃらんぽらん政治団体',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 17:20:40',0,0,'','1948-07-28 23:59:59')
;



INSERT INTO `kanrensha_seijidantai_master` (`kanrensha_seijidantai_master_id`,`seijidantai_kanrensha_code`,`poli_org_no`,`is_latest`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (1,'984-321V-kZNH-uJUw-Alcr1','98-4321',1,'ちゃらんぽらん政治団体','宮崎県架空市橘通東２丁目１０−１','代表者　太郎','03','ちゃらんぽらん政治団体',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59')
 ,(2,'200','98-4321',1,'ちゃらんぽらん政治団体','宮崎県架空市橘通東２丁目１０−１　宮崎県庁','代表者　太郎','03','ちゃらんぽらん政治団体',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59')
;


    
    
