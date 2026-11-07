DELETE FROM `dump_record`;
ALTER TABLE `dump_record` auto_increment = 0;

INSERT INTO `dump_record` (`dump_record_id`,`task_info_code`,`is_latest`,`start_datetime`,`end_datetime`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
   VALUES 
       (171,315,1,'2022-12-13 14:15:16','2024-07-22 01:02:03',196,190,'管理人　太郎','2026-10-04 14:06:04',0,0,'','1948-07-28 23:59:59')
     , (172,314,1,'2022-12-13 14:15:16','2024-07-22 01:02:03',196,190,'管理人　太郎','2026-10-04 14:06:04',0,0,'','1948-07-28 23:59:59')
     , (173,316,1,'2022-12-13 14:15:16','2024-07-22 01:02:03',196,190,'管理人　太郎','2026-10-04 14:06:04',0,0,'','1948-07-28 23:59:59')
     ;
