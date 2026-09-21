

DELETE FROM `kanrensha_code_move`;
ALTER TABLE `kanrensha_code_move` auto_increment = 0;
  
INSERT INTO `kanrensha_code_move` (`kanrensha_code_move_id`,`kanrensha_code_move_code`,`is_latest`,`move_status`,`kanrensha_kbn`,`origin_kanrensha_code`,`origin_name`,`abolish_kanrensha_code`,`abolish_kanrensha_name`,`move_reason`,`is_abolish_last`,`task_year`,`save_file_storage_id`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
      VALUES 
      -- 1申請(最新でない)
        (242,1,0,1,1,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,196,190,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      -- 1却下
      , (243,1,1,2,1,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,196,190,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      -- 2申請(最新でない)
      , (244,2,0,1,2,'98765','超元素製造組合','12345','法人株式会社3','テスト',0,2026,45,196,190,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      -- 2承認
      , (245,2,1,3,3,'98765','超元素製造組合','12345','法人株式会社3','テスト',0,2026,45,196,190,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      -- コード条件満たさず
      , (246,11,1,0,3,'aaaaa','超元素製造組合','bbbbb','法人株式会社3','テスト',0,2026,45,207,195,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      -- 3申請
      , (247,3,1,1,2,'12345','超元素製造組合','98765','法人株式会社3','テスト',0,2026,45,196,190,'llll','2026-09-08 06:36:18',0,0,'','1948-07-28 23:59:59')
      ;

