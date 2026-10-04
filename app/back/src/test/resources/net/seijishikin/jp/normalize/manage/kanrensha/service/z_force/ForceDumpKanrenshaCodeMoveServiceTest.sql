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

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,320,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,320,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
      