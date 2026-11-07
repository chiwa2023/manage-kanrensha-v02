DELETE FROM `kanrensha_code_move`;
ALTER TABLE `kanrensha_code_move` auto_increment = 0;
  
INSERT INTO `kanrensha_code_move` (`kanrensha_code_move_id`,`kanrensha_code_move_code`,`is_latest`,`move_status`,`kanrensha_kbn`,`origin_kanrensha_code`,`origin_name`,`abolish_kanrensha_code`,`abolish_kanrensha_name`,`move_reason`,`is_abolish_last`,`task_year`,`save_file_storage_id`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
      VALUES 
       -- 最新でない
        (242,0,1,0,1,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,208,196,'llll','2024-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
       -- 承認でない
      , (243,0,1,1,1,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,208,196,'llll','2024-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
       -- 期限後に更新
      , (244,0,1,3,2,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,206,194,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
       -- 抽出
      , (245,0,1,3,1,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,207,195,'llll','2024-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      , (246,0,1,3,2,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,207,195,'llll','2024-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      , (247,0,1,3,3,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,207,195,'llll','2024-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      ;

      
DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;


DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;
INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
   (136,320,'サンプルタスク情報',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ;

   