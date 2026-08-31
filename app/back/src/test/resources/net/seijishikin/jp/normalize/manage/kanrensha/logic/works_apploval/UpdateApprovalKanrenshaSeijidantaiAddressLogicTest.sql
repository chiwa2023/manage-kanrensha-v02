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


    
    
