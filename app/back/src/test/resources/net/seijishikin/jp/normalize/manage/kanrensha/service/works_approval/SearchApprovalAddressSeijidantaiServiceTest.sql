DELETE FROM `kanrensha_kigyou_dt_address`;
ALTER TABLE `kanrensha_kigyou_dt_address` auto_increment = 0;
DELETE FROM `kanrensha_person_address`;
ALTER TABLE `kanrensha_person_address` auto_increment = 0;
DELETE FROM `kanrensha_seijidantai_address`;
ALTER TABLE `kanrensha_seijidantai_address` auto_increment = 0;



INSERT INTO `kanrensha_kigyou_dt_address` (`kanrensha_kigyou_dt_address_id`,`kanrensha_kigyou_dt_id`,`kigyou_dt_kanrensha_code`,`kanrensha_name`,`is_latest`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (100,1,'1-2345-67-iRa0ko-l9ElEre','テスト株式会社',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 14:53:32',0,0,'','1948-07-28 23:59:59')
, (101,1,'1-2345-67-iRa0ko-l9ElEre','テスト株式会社',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,1,1,1,196,190,'管理人　太郎','2025-04-27 14:53:32',0,0,'','1948-07-28 23:59:59')
, (102,1,'1-2345-67-iRa0ko-l9ElEre','テスト株式会社',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,1,0,196,190,'管理人　太郎','2025-04-27 14:53:32',0,0,'','1948-07-28 23:59:59')
, (103,1,'1-2345-67-iRa0ko-l9ElEre','テスト株式会社',0,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-04-27 14:53:32',0,0,'','1948-07-28 23:59:59')
, (104,1,'1-2345-67-iRa0ko-l9ElEre','テスト株式会社',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',0,0,0,0,0,0,196,190,'管理人　太郎','2025-04-27 14:53:32',0,0,'','1948-07-28 23:59:59')

;



INSERT INTO `kanrensha_person_address` (`kanrensha_person_address_id`,`kanrensha_person_id`,`person_kanrensha_code`,`kanrensha_name`,`is_latest`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (200,1,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
, (201,1,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,1,1,1,196,190,'管理人　太郎','2025-04-27 16:30:24',0,0,'','1948-07-28 23:59:59')
, (202,1,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-04-27 16:30:24',0,0,'','1948-07-28 23:59:59')
, (203,1,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',0,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-04-27 16:30:24',0,0,'','1948-07-28 23:59:59')
, (204,1,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',0,0,0,0,0,0,196,190,'管理人　太郎','2025-04-27 16:30:24',0,0,'','1948-07-28 23:59:59')

;


INSERT INTO `kanrensha_seijidantai_address` (`kanrensha_seijidantai_address_id`,`kanrensha_seijidantai_id`,`seijidantai_kanrensha_code`,`kanrensha_name`,`is_latest`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (300,1,'RKO-g6dW-mTZ7-1RGM-iQnzB','ちゃらんぽらん政治団体',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 17:20:40',0,0,'','1948-07-28 23:59:59')
, (301,1,'RKO-g6dW-mTZ7-1RGM-iQnzB','ちゃらんぽらん政治団体',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,1,1,1,196,190,'管理人　太郎','2025-04-27 17:20:40',0,0,'','1948-07-28 23:59:59')
, (302,1,'RKO-g6dW-mTZ7-1RGM-iQnzB','ちゃらんぽらん政治団体',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-04-27 17:20:40',0,0,'','1948-07-28 23:59:59')
, (303,1,'RKO-g6dW-mTZ7-1RGM-iQnzB','ちゃらんぽらん政治団体',0,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-04-27 17:20:40',0,0,'','1948-07-28 23:59:59')
, (304,1,'RKO-g6dW-mTZ7-1RGM-iQnzB','ちゃらんぽらん政治団体',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',0,0,0,0,0,0,196,190,'管理人　太郎','2025-04-27 17:20:40',0,0,'','1948-07-28 23:59:59')

;
