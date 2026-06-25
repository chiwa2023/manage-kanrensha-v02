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
  , (207289,'012041','074','1181',0,'北海道旭川市神居町西丘2番地2号','','0094000','','000020000200000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:21:40',0,0,'','1948-07-28 23:59:59')
  , (207290,'012041','074','1181',1,'北海道旭川市神居町西丘7番地2号','','0094000','','000070000200000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:21:40',0,0,'','1948-07-28 23:59:59')
  , (207291,'012041','074','1181',0,'北海道旭川市神居町西丘7番地3号','','0094000','','000070000300000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:21:40',0,0,'','1948-07-28 23:59:59')
  , (207300,'012041','078','0186',1,'北海道旭川市神居町西丘8番地1号','','0094000','','000080000100000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
  , (207301,'012041','078','0186',1,'北海道旭川市神居町西丘8番地2号','','0094000','','000080000200000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
  , (207302,'012041','078','0186',1,'北海道旭川市神居町西丘8番地3号','','0094000','','000080000300000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
  , (207303,'012041','078','0186',1,'北海道旭川市神居町西丘9番地1号','','0094000','','000090000100000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
  , (207304,'012041','078','0186',1,'北海道旭川市神居町西丘9番地2号','','0094000','','000090000200000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 21:00:59',0,0,'','1948-07-28 23:59:59')
;

DELETE FROM `address_postal_irregular`;
ALTER TABLE `address_postal_irregular` auto_increment = 0;

INSERT INTO `address_postal_irregular` (`address_postal_irregular_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (1,'060','8613',1,'011011','北一条西','４丁目２－２札幌ノースプラザ１０Ｆ','北海道札幌市中央区北一条西','４丁目２－２札幌ノースプラザ１０Ｆ',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_postal_irregular` (`address_postal_irregular_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (2,'060','8703',1,'011011','北一条西','９丁目１－５（札幌中央郵便局私書箱第２９号）','北海道札幌市中央区北一条西','９丁目１－５（札幌中央郵便局私書箱第２９号）',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_postal_irregular` (`address_postal_irregular_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (3,'060','8537',1,'011011','南一条西','４丁目２０番地','北海道札幌市中央区南一条西','４丁目２０番地',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_postal_irregular` (`address_postal_irregular_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
 VALUES 
     (1254,'330','6000',1,'012041','新都心明治安田生命さいたま新都心ビル（地階・階層不明）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1255,'330','6001',1,'012041','新都心明治安田生命さいたま新都心ビル（１階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1256,'330','6002',1,'012041','新都心明治安田生命さいたま新都心ビル（２階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1257,'330','6003',1,'012041','新都心明治安田生命さいたま新都心ビル（３階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1258,'330','6004',1,'012041','新都心明治安田生命さいたま新都心ビル（４階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1259,'330','6005',1,'012041','新都心明治安田生命さいたま新都心ビル（５階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1260,'330','6006',1,'012041','新都心明治安田生命さいたま新都心ビル（６階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1261,'330','6007',1,'012041','新都心明治安田生命さいたま新都心ビル（７階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1262,'330','6008',1,'012041','新都心明治安田生命さいたま新都心ビル（８階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1263,'330','6009',1,'012041','新都心明治安田生命さいたま新都心ビル（９階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1264,'330','6010',1,'012041','新都心明治安田生命さいたま新都心ビル（１０階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (1265,'330','6011',1,'012041','新都心明治安田生命さいたま新都心ビル（１１階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
	-- 最新でない
   , (1266,'330','6003',0,'011011','新都心明治安田生命さいたま新都心ビル（１２階）','新都心明治安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
	--　ビルが異なる
   , (1267,'330','6003',1,'012041','新都心明治安田生命さいたま新都心ビル（１１階）','大正安田生命さいたま新都心ビル','北海道旭川市神居町西丘','7番地2号',0,0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
  ;
  
