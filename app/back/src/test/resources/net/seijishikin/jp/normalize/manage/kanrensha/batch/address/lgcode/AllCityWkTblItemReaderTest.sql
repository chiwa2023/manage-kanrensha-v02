DELETE FROM `address_all_city`;
ALTER TABLE `address_all_city` auto_increment = 0;

DELETE FROM `wk_tbl_address_city`;
ALTER TABLE `wk_tbl_address_city` auto_increment = 0;

INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name`,`address_name_kana`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES 
     (219,'112233',1,'テスト県テスト郡','てすとけんてすとぐん','2038-01-02','1948-07-28',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(220,'36852',1,'テスト県テスト郡','てすとけんてすとぐん','2038-01-02','1948-07-28',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(221,'66442',1,'テスト県テスト郡','てすとけんてすとぐん','2038-01-02','1948-07-28',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(222,'22344',1,'テスト県テスト郡','てすとけんてすとぐん','2038-01-02','1948-07-28',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ;


INSERT INTO `wk_tbl_address_city` (`wk_tbl_address_city_id`,`is_latest`,`lg_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES 
     (184,1,'11699',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(185,1,'33445',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(186,1,'22344',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(187,1,'112233',244,219,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(188,0,'36852',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ;
