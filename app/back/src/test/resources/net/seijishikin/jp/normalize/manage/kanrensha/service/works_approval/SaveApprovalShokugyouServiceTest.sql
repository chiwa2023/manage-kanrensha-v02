DELETE FROM `kanrensha_person_property`;
ALTER TABLE `kanrensha_person_property` auto_increment = 0;
DELETE FROM `kanrensha_person_master`;
ALTER TABLE `kanrensha_person_master` auto_increment = 0;

INSERT INTO `kanrensha_person_property` (`kanrensha_person_property_id`,`kanrensha_person_id`,`person_kanrensha_code`,`kanrensha_name`,`is_latest`,`is_foreign`,`last_name`,`first_name`,`middle_name`,`last_name_kana`,`first_name_kana`,`middle_name_kana`,`gyoushu`,`yakushoku`,`shokugyou_user_write`,`kigyou_dt_no`,`kigyou_dt_address`,`kigyou_dt_name`,`is_shokyou_edit`,`is_shokyou_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (600,1,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,1,'太郎','迂回献金','ミカエル','たろう','うかいけんきん','みかえる','水産業','一般職員','','1-2345','山梨県実在市湖畔町','ほったらかし農園',1,0,196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
, (601,1,'DH-mkzsu-2mMW-rB8Y-Pl4zr','迂回献金　ミカエル太郎',1,1,'太郎','迂回献金','ミカエル','たろう','うかいけんきん','みかえる','水産業','一般職員','','','','',1,0,196,190,'管理人　太郎','2025-04-27 16:30:24',0,0,'','1948-07-28 23:59:59')
, (602,1,'100','迂回献金　ミカエル太郎',1,1,'太郎','迂回献金','ミカエル','たろう','うかいけんきん','みかえる','農林業','部長','素浪人','1-2345','山梨県実在市湖畔町','ほったらかし農園',1,0,196,190,'管理人　太郎','2025-04-27 16:30:24',0,0,'','1948-07-28 23:59:59')
;


INSERT INTO `kanrensha_person_master` (`kanrensha_person_master_id`,`person_kanrensha_code`,`is_latest`,`kanrensha_name`,`all_address`,`person_shokugyou`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (801,'DH-mkzsu-2mMW-rB8Y-Pl4zr',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','水産業社員・職員','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
, (802,'100',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','素浪人','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
;


