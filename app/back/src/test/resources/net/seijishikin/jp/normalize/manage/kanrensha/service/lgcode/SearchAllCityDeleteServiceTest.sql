DELETE FROM `address_city_delete`;
ALTER TABLE `address_city_delete` auto_increment = 0;

/*
-- Query: SELECT * FROM test_manage_kanrensha_v02.address_city_delete
LIMIT 0, 100

-- Date: 2026-06-21 16:43
*/
INSERT INTO `address_city_delete` (`address_city_delete_id`,`is_latest`,`lg_code`,`org_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
   VALUES 
     (159,1,'112233','和歌山県特別区実在市山麓区',196,190,'管理人　太郎','2026-06-21 15:28:24',0,0,'','1948-07-28 23:59:59')
   , (160,0,'112233','和歌山県特別区実在市山麓区',196,190,'管理人　太郎','2026-06-21 15:28:24',0,0,'','1948-07-28 23:59:59')
   , (161,1,'112233','和歌山県特別区実在市山麓区',196,190,'管理人　太郎','2026-06-21 15:28:24',0,0,'','1948-07-28 23:59:59')
;
