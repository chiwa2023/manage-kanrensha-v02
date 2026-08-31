DELETE FROM `wk_tbl_postal_common`;
ALTER TABLE `wk_tbl_postal_common` auto_increment = 0;

INSERT INTO `wk_tbl_postal_common` (`wk_tbl_postal_common_id`,`address_postal_id`,`address_postal_irregular_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
     (103,2,3,'4','5',1,'6','7','8',1,'10','11',1,1,196,190,'ユーザ','2022-12-05 12:34:56',0,0,NULL,'1948-07-28 23:59:59')
   , (104,2,3,'4','5',1,'6','7','8',1,'10','11',1,1,196,190,'ユーザ','2022-12-05 12:34:56',0,0,NULL,'1948-07-28 23:59:59')
   , (105,2,3,'4','5',1,'6','7','8',1,'10','11',1,1,196,190,'ユーザ','2022-12-05 12:34:56',0,0,NULL,'1948-07-28 23:59:59')
;

DELETE FROM `address_postal`;
ALTER TABLE `address_postal` auto_increment = 0;

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
      , (165,1,'以下に',152,'123','4567','011029','適当地域','北海道室蘭市（適当地域）',1,1,196,190,'管理人　太郎','2026-03-26 20:59:25',0,0,'','1948-07-28 23:59:59')
;

DROP TABLE IF EXISTS address_rsdt_011029;

CREATE TABLE IF NOT EXISTS `address_rsdt_011029` (
  `address_rsdt_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `lg_code` varchar(8) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地方自治体コード',
  `postalcode1` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '郵便番号1',
  `postalcode2` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '郵便番号2',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `address_block` text COLLATE utf8mb4_bin COMMENT '街区住所',
  `address_building` text COLLATE utf8mb4_bin COMMENT '住所建物',
  `machiaza_id` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '町字Id',
  `blk_id` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '街区Id',
  `prc_id` varchar(17) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地番Id',
  `rsdt_id` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '住居Id',
  `rsdt2_id` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '住居2Id',
  `effect_date` date DEFAULT NULL COMMENT '適用開始日',
  `abolish_date` date DEFAULT NULL COMMENT '廃止日',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`address_rsdt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


INSERT INTO `address_rsdt_011029` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (623,'011029','111','222',1,'北海道札幌市（架空）','bb','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
   ,(624,'011029','111','222',0,'北海道札幌市（実在）','bb','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  ;

  INSERT INTO `address_postal` (`address_postal_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
(152,'005','0861',1,'011011','真駒内（その他）','北海道札幌市南区区真駒内(特殊)',1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
;
