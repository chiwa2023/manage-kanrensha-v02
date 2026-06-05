CREATE TABLE IF NOT EXISTS `address_rsdt_012041` (
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

DELETE FROM `address_rsdt_012041`;
ALTER TABLE `address_rsdt_012041` auto_increment = 0;

INSERT INTO `address_rsdt_012041` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (207287,'012041','074','1181',1,'北海道旭川市神居町西丘1番地','','0094000','','000010000000000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:21:40',0,0,'','1948-07-28 23:59:59')
  , (207288,'012041','074','1181',1,'北海道旭川市神居町西丘2番地1号','','0094000','','000020000100000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:21:40',0,0,'','1948-07-28 23:59:59')
  , (207289,'012041','074','1181',1,'北海道旭川市神居町西丘2番地2号','','0094000','','000020000200000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:21:40',0,0,'','1948-07-28 23:59:59')
  , (207290,'012041','074','1181',0,'北海道旭川市神居町西丘7番地2号','','0094000','','000070000200000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:21:40',0,0,'','1948-07-28 23:59:59')
  , (207291,'012041','074','1181',0,'北海道旭川市神居町西丘7番地3号','','0094000','','000070000300000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:21:40',0,0,'','1948-07-28 23:59:59')
  , (207300,'012041','078','0186',1,'北海道旭川市神居町西丘8番地1号','','0094000','','000080000100000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
  , (207301,'012041','078','0186',1,'北海道旭川市神居町西丘8番地2号','','0094000','','000080000200000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
  , (207302,'012041','078','0186',1,'北海道旭川市神居町西丘8番地3号','','0094000','','000080000300000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
  , (207303,'012041','078','0186',1,'北海道旭川市神居町西丘9番地1号','','0094000','','000090000100000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
  , (207304,'012041','078','0186',1,'北海道旭川市神居町西丘9番地2号','','0094000','','000090000200000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
;

