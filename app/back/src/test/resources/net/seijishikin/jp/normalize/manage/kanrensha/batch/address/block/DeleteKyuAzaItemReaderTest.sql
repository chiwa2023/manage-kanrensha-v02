DELETE FROM `address_postal_repair_log`;
ALTER TABLE `address_postal_repair_log` auto_increment = 0;

INSERT INTO `address_postal_repair_log` (`address_postal_repair_log_id`,`is_latest`,`status_text`,`address_postal_id`,`postalcode1`,`postalcode2`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`is_confirm`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
   VALUES 
      -- 最新でない
        (161,0,'以下に',148,'123','4567','01003','2','1',1,1,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
        -- 名称に（が含まれない
      , (162,1,'以下に掲載がない場合',149,'123','4567','13003','以下に掲載がない場合','北海道以下に掲載がない場合',1,1,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
        -- 修正フラグをたてていない
      , (163,1,'以下に',150,'123','4567','01003','適当地域','北海道室蘭市（適当地域）',1,0,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
        -- 地方行政区コードが対象外
      , (164,1,'以下に',151,'123','4567','13003','適当地域','北海道室蘭市（適当地域）',1,1,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
        -- 抽出
      , (165,1,'以下に',152,'123','4567','01003','適当地域','北海道室蘭市（適当地域）',1,1,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
;
