DELETE FROM `address_all_city`;
ALTER TABLE `address_all_city` auto_increment = 0;

INSERT INTO `address_all_city` (`address_all_city_id`,`lg_code`,`is_latest`,`address_name`,`address_name_kana`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
    VALUES 
     (219,'112233',0,'テスト県テスト郡','てすとけんてすとぐん','2038-01-02',null,196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(220,'368521',1,'和歌山県実在市','わかやまけんじつざいし','1948-07-30',null,196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(221,'695123',0,'テスト県テスト郡','てすとけんてすとぐん','2038-01-02',null,196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(222,'695123',0,'テスト県テスト郡','てすとけんてすとぐん','2038-01-02',null,196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(223,'695123',1,'テスト県テスト郡','てすとけんてすとぐん','2038-01-02',null,196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(224,'827637',1,'山梨県湖畔町','やまなしけんこはんまち','1948-06-29','2050-01-01',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ,(225,'742931',1,'宮崎県山麓町','みやざきけんさんろくまち','1948-06-29','2003-11-25',196,190,'管理人　太郎','2026-02-17 21:58:03',0,0,'','1948-07-28 23:59:59')
    ;

CREATE TABLE IF NOT EXISTS `address_rsdt_827637` (
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

DELETE FROM `address_rsdt_827637`;

INSERT INTO `address_rsdt_827637` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES (623,'21678','111','222',1,'aa','bb','1','2','3','4','5','1948-07-29',NULL,0,0,'a',NULL,0,0,'a',NULL)
  ;


  
