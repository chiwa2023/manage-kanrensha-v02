CREATE TABLE IF NOT EXISTS `address_rsdt_011037` (
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

DELETE FROM `address_rsdt_011037`;
ALTER TABLE `address_rsdt_011037` auto_increment = 0;

INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (132657,'011037','','',1,'北海道札幌市東区雁来町9番地20号','','0002000','','000090002000000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-22 20:22:14',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (132658,'011037','','',1,'北海道札幌市東区雁来町10番地','','0002000','','000100000000000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-22 20:22:14',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (132659,'011037','','',1,'北海道札幌市東区雁来町10番地1号','','0002000','','000100000100000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-22 20:22:14',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (132660,'011037','','',1,'北海道札幌市東区雁来町11番地1号','','0002000','','000110000100000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-22 20:22:14',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (132665,'011037','','',0,'北海道札幌市東区雁来町15番地','','0002000','','000150000000000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-22 20:22:14',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (84929,'011037','007','0030',1,'北海道札幌市東区東雁来十条一丁目1番地1号','','0070001','001','','001','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 19:33:27',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (84930,'011037','007','0030',1,'北海道札幌市東区東雁来十条一丁目1番地2号','','0070001','001','','002','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 19:33:27',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (84931,'011037','007','0030',1,'北海道札幌市東区東雁来十条一丁目1番地3号','','0070001','001','','003','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 19:33:27',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_rsdt_011037` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (84932,'011037','007','0030',1,'北海道札幌市東区東雁来十条一丁目1番地4号','','0070001','001','','004','','1947-04-17',NULL,196,190,'管理人　太郎','2026-03-23 19:33:27',0,0,'','1948-07-28 23:59:59');
