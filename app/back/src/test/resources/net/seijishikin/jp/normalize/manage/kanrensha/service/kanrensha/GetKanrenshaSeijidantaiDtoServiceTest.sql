DELETE FROM `kanrensha_seijidantai_master`;
ALTER TABLE `kanrensha_seijidantai_master` auto_increment = 0;

DELETE FROM `kanrensha_seijidantai_access`;
ALTER TABLE `kanrensha_seijidantai_access` auto_increment = 0;

DELETE FROM `kanrensha_seijidantai_address`;
ALTER TABLE `kanrensha_seijidantai_address` auto_increment = 0;

DELETE FROM `kanrensha_seijidantai_property`;
ALTER TABLE `kanrensha_seijidantai_property` auto_increment = 0;

INSERT INTO `kanrensha_seijidantai_master` (`kanrensha_seijidantai_master_id`,`seijidantai_kanrensha_code`,`poli_org_no`,`is_latest`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1215,'984-321V-kZNH-uJUw-Alcr1','98-4321',1,'ちゃらんぽらん政治団体','宮崎県架空市橘通東２丁目１０−１','代表者　太郎','03','ちゃらんぽらん政治団体',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59');

  
INSERT INTO `kanrensha_seijidantai_access` (`kanrensha_seijidantai_access_id`,`kanrensha_seijidantai_id`,`seijidantai_kanrensha_code`,`kanrensha_name`,`is_latest`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1324,1013,'984-321V-kZNH-uJUw-Alcr1','ちゃらんぽらん政治団体',1,'0985','26','7132','test@example.com','https://my-portal/index.html',0,0,'弱小SNS','https://jyakusho-sns/','@taro123456',196,190,'管理人　太郎','2025-12-27 17:20:40',0,0,'','1948-07-28 23:59:59');

  INSERT INTO `kanrensha_seijidantai_address` (`kanrensha_seijidantai_address_id`,`kanrensha_seijidantai_id`,`seijidantai_kanrensha_code`,`kanrensha_name`,`is_latest`,`postalcode1`,`postalcode2`,`address_postal`,`address_block`,`address_building`,`lg_code`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (1411,1013,'984-321V-kZNH-uJUw-Alcr1','ちゃらんぽらん政治団体',1,'880','8501','宮崎県架空市橘通東','２丁目１０−１','宮崎県庁','131016','324','131','249','136','978',1,1,1,0,0,0,196,190,'管理人　太郎','2025-12-27 17:20:40',0,0,'','1948-07-28 23:59:59')
;
  
INSERT INTO `kanrensha_seijidantai_property` (`kanrensha_seijidantai_property_id`,`kanrensha_seijidantai_id`,`seijidantai_kanrensha_code`,`kanrensha_name`,`is_latest`,`org_name_kana`,`org_delegate_code`,`account_mgr_code`,`account_mgr_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (1565,1013,'984-321V-kZNH-uJUw-Alcr1','ちゃらんぽらん政治団体',1,'ちゃらんぽらんせいじだんたい','P12345','P98765','会計責任者　花子',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59');

DELETE FROM `login_status`;

DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 1;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 80;

INSERT INTO `login_status` (`email`,`password`,`is_success`,`fail_reason`,`disabled`,`disabled_reason`,`login_time`,`pass_change_time`)
  VALUES 
       ('aaa@politician.balanse.report.net','$2a$10$KLZCF1ao2ZOZ1NRTDhfYSuwcY0jHFgUoIOJE9S44z/mxA0WeR3yg6',1,'',0,'','2026-01-05 20:15:48','2026-01-05 20:15:48')
      ,('bbb@politician.balanse.report.net','$2a$10$KLZCF1ao2ZOZ1NRTDhfYSuwcY0jHFgUoIOJE9S44z/mxA0WeR3yg6',1,'',1,'','2000-01-05 20:15:48','2000-01-05 20:15:48')
      ,('nnnn@politician.balanse.report.net','$2a$10$.otLO/4XEMQGv1tVwaJ9ZOYPabzwr5eI6CBGdo0xTyrhvwMRCVx5a',1,'',0,'','2026-05-05 10:43:57','2026-05-05 10:43:57')
      ,('llll@politician.balanse.report.net','$2a$10$Z0pJXYQfXDRSxzg/mutzy.dTUB/.lN5RYWsY43WgRx23e16Vpg7hS',1,'',0,'','2026-05-05 09:40:56','2026-05-05 09:43:43')
      ,('mmmm@politician.balanse.report.net','$2a$10$EvuI7fvM4ejfSghbGEMPoe8GL7wDkVS30X63i8P0v/eYD6BW9Na6O',1,'',0,'','2026-05-05 10:05:06','2026-05-05 10:05:06')
      ;


INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`  ,`is_alert_task_start`  ,`is_alert_task_end`       ,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  (81,80,'aaa',1,'aaa@politician.balanse.report.net',0,0 ,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(82,83,'bbb',1,'bbb@politician.balanse.report.net',1,1 ,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(208,196,'nnnn',1,'nnnn@politician.balanse.report.net',0,0,208,196,'nnnn','2026-05-05 10:43:56',0,0,'','1948-07-28 23:59:59')
  ,(206,194,'llll',1,'llll@politician.balanse.report.net',0,0,206,194,'llll','2026-05-05 09:40:56',0,0,'','1948-07-28 23:59:59')
  ,(207,195,'mmmm',1,'mmmm@politician.balanse.report.net',0,0,207,195,'mmmm','2026-05-05 10:05:06',0,0,'','1948-07-28 23:59:59')
  ;

INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`kanrensha_code`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (1,'aaa@politician.balanse.report.net',1,'manager','dvsdf',244,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(2,'bbb@politician.balanse.report.net',1,'manager','',14,1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(156,'nnnn@politician.balanse.report.net',1,'kanrensha_person','DH-mkzsu-2mMW-rB8Y-Pl4zr',0,208,196,'nnnn','2026-05-05 10:46:28',0,0,'','1948-07-28 23:59:59')
  ,(152,'llll@politician.balanse.report.net',1,'kanrensha_kigyou_dt','1-2345-67-8903cQ-JAQ8qP1',0,206,194,'llll','2026-05-05 09:58:23',0,0,'','1948-07-28 23:59:59')
  ,(154,'mmmm@politician.balanse.report.net',1,'kanrensha_seijidantai','123-4567-0FRi-6kEt-RF77W',0,207,195,'mmmm','2026-05-05 10:07:44',0,0,'','1948-07-28 23:59:59')
  ;
  
    
  