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

INSERT INTO `address_rsdt_012041` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (623,'011061','005','0861',1,'旭川市東旭川町豊田1番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (624,'011061','005','0861',1,'旭川市東旭川町豊田2番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (625,'011061','005','0861',1,'旭川市東旭川町豊田3番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (626,'011061','005','0861',1,'旭川市東旭川町豊田4番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (627,'011061','005','0861',1,'旭川市東旭川町豊田5番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (628,'011061','005','0861',1,'旭川市東旭川町豊田6番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (629,'011061','005','0861',1,'旭川市東旭川町豊田7番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (630,'011061','005','0861',1,'旭川市東旭川町豊田8番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (631,'011061','005','0861',1,'旭川市東旭川町豊田9番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (632,'011061','005','0861',1,'旭川市東旭川町豊田10番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  ;
    
  
CREATE TABLE IF NOT EXISTS `address_rsdt_012289` (
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

DELETE FROM `address_rsdt_012289`;

INSERT INTO `address_rsdt_012289` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (891,'011061','005','0861',1,'深川市広里町一丁目2番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (892,'011061','005','0861',1,'深川市広里町一丁目4番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (893,'011061','005','0861',1,'深川市広里町二丁目6番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (894,'011061','005','0861',1,'深川市広里町二丁目8番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (895,'011061','005','0861',1,'深川市広里町三丁目10番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (896,'011061','005','0861',1,'深川市広里町三丁目12番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  , (897,'011061','005','0861',1,'深川市広里町四丁目14番地','','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  ;
  
  
  