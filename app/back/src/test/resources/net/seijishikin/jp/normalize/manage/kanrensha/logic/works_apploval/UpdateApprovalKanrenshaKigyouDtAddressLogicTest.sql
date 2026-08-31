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

    
