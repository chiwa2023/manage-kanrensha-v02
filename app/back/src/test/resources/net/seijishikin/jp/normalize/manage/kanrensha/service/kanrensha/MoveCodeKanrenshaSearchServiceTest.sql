DELETE FROM `kanrensha_code_move`;
ALTER TABLE `kanrensha_code_move` auto_increment = 0;
  
INSERT INTO `kanrensha_code_move` (`kanrensha_code_move_id`,`kanrensha_code_move_code`,`is_latest`,`move_status`,`kanrensha_kbn`,`origin_kanrensha_code`,`origin_name`,`abolish_kanrensha_code`,`abolish_kanrensha_name`,`move_reason`,`is_abolish_last`,`task_year`,`save_file_storage_id`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
      VALUES 
      -- 最新でない
        (242,100,0,0,1,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,206,194,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      -- 初期値のまま
      , (243,100,1,0,1,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,206,194,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      -- 検索対象
      , (244,100,1,1,2,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,206,194,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      , (245,100,1,2,2,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,206,194,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      , (246,100,1,3,2,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,206,194,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      , (247,400,1,4,2,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,206,194,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      ;
