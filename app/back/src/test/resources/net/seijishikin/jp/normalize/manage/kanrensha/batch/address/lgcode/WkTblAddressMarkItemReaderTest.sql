DELETE FROM `wk_tbl_address_rsdt_mark`;
ALTER TABLE `wk_tbl_address_rsdt_mark` auto_increment = 0;
      
INSERT INTO `wk_tbl_address_rsdt_mark` (`wk_tbl_address_rsdt_mark_id`,`is_latest`,`wl_rsdt_change_id`,`wl_rsdt_delete_id`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
    VALUES 
        (365,1,100,100,246,217,'管理人　太郎','2025-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
      , (366,0,100,100,196,190,'管理人　太郎','2025-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
      , (367,1,100,100,196,190,'管理人　太郎','2025-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
      , (368,1,100,100,196,190,'管理人　太郎','2025-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ;
      
      