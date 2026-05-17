DELETE FROM `kanrensha_kigyou_dt_master`;
ALTER TABLE `kanrensha_kigyou_dt_master` auto_increment = 0;

INSERT INTO kanrensha_kigyou_dt_master (kanrensha_kigyou_dt_master_id ,kigyou_dt_kanrensha_code,  is_latest,houjin_no,  kanrensha_name,  all_address,  kigyou_dt_delegate,  compare_name_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
VALUES 
  (140 , '1-2345-67-890123-TXhlkXh',  1,  '1234567890' , '株式会社テスト',  '東京都テスト区テスト町1-1',  '代表テスト',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
-- 最新でない 
, (141 , '1-2345-67-890123-TXhlkXh',  0,  '1234567890' , '株式会社テスト',  '東京都テスト区テスト町1-1',  '代表テスト',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
-- 番号が対象外 
,  (145 , '999-2-67-890123-TXhlkXh', 1,  '1234567890' , '株式会社テスト',  '東京都テスト区テスト町1-1',  '代表者　太郎',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
;

DELETE FROM `kanrensha_person_master`;
ALTER TABLE `kanrensha_person_master` auto_increment = 0;

INSERT INTO `kanrensha_person_master` (`kanrensha_person_master_id`,`person_kanrensha_code`,`is_latest`,`kanrensha_name`,`all_address`,`person_shokugyou`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
   (231,'DH-mkzsu-2mMW-rB8Y-Pl4zr',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','素浪人','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
   -- 最新でない
  ,(232,'DH-mkzsu-2mMW-rB8Y-Pl4zr',0,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','素浪人','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
   -- 番号が該当しない
  ,(235,'111-mkzsu-2mMW-rB8Y-Pl4zr',1,'迂回献金　ミカエル太郎','宮崎県架空市橘通東２丁目１０−１','正義の味方','迂回献金ミカエル太郎',196,190,'管理人　太郎','2025-12-27 16:30:24',0,0,'','1948-07-28 23:59:59')
  ;

DELETE FROM `kanrensha_seijidantai_master`;
ALTER TABLE `kanrensha_seijidantai_master` auto_increment = 0;

INSERT INTO `kanrensha_seijidantai_master` (`kanrensha_seijidantai_master_id`,`seijidantai_kanrensha_code`,`poli_org_no`,`is_latest`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (323,'984-321V-kZNH-uJUw-Alcr1','98-4321',1,'ちゃらんぽらん政治団体','宮崎県架空市橘通東２丁目１０−１','代表者　太郎','04','ちゃらんぽらん政治団体',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59')
    -- 最新でない    
  , (324,'984-321V-kZNH-uJUw-Alcr1','98-4321',0,'ちゃらんぽらん政治団体','宮崎県架空市橘通東２丁目１０−１','代表者　太郎','04','ちゃらんぽらん政治団体',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59')
    -- 番号が該当でない    
  , (329,'111-321V-kZNH-uJUw-Alcr1','98-4321',1,'ちゃらんぽらん政治団体','宮崎県架空市橘通東２丁目１０−１','代表者　太郎','03','ちゃらんぽらん政治団体',196,190,'管理人　太郎','2025-12-27 17:22:43',0,0,'','1948-07-28 23:59:59')
  ;
