DELETE FROM `address_postal_repair_log`;
ALTER TABLE `address_postal_repair_log` auto_increment = 0;

INSERT INTO `address_postal_repair_log` (`address_postal_repair_log_id`,`is_latest`,`status_text`,`address_postal_id`,`postalcode1`,`postalcode2`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`is_confirm`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
   VALUES 
      -- 最新でない
        (161,0,'以下に',148,'123','4567','01003','2','1',1,1,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
      -- 編集対象の件でない
      , (162,1,'以下に',148,'123','4567','13003','2','1',1,1,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
      , (163,1,'以下に',148,'123','4567','01003','2','1',1,1,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
;
