DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,153,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
;

DELETE FROM `wk_tbl_address_rsdt_mark`;
ALTER TABLE `wk_tbl_address_rsdt_mark` auto_increment = 0;

CREATE TABLE IF NOT EXISTS `address_rsdt_011045` (
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

DELETE FROM `address_rsdt_011045`;
ALTER TABLE `address_rsdt_011045` auto_increment = 0;

INSERT INTO `address_rsdt_011045` (`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (801,'011045','','',1,'北海道石狩郡当別町425番地36号','','0000000','','004250003600000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
   ,(802,'011045','','',1,'北海道石狩郡当別町425番地37号','','1','2','3','4','5','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
   ,(803,'011045','','',1,'北海道石狩郡当別町425番地38号','','0000000','','004250003800000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
   ,(804,'011045','','',1,'北海道石狩郡当別町425番地39号','','0000000','','004250003900000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
   ,(805,'011045','','',1,'北海道石狩郡当別町425番地40号','','0000000','','004250004000000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
   ,(806,'011045','','',1,'北海道石狩郡当別町425番地46号','','0000000','','004250004600000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
   ,(807,'011045','','',1,'北海道石狩郡当別町683番地7号','','0000000','','006830000700000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
   ,(808,'011045','','',1,'北海道石狩郡当別町1114番地7号','三角ビル','11','12','13','14','15','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
   ,(809,'011045','','',1,'北海道石狩郡当別町1114番地8号','','0000000','','011140000800000','','','1947-04-17',NULL,196,190,'管理人　太郎','2026-05-24 19:52:43',0,0,'','1948-07-28 23:59:59')
  ;
     
DELETE FROM `wk_tbl_address_rsdt_change`;
ALTER TABLE `wk_tbl_address_rsdt_change` auto_increment = 0;

INSERT INTO `wk_tbl_address_rsdt_change` (`wk_tbl_address_rsdt_change_id`,`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
      (331,2,'011045','','',1,'北海道石狩郡当別町425番地の37','','1','2','3','4','5','1948-07-28','1948-07-28',196,460,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    , (332,0,'011045','','',0,'北海道石狩郡当別町425番地37号','','9','2','3','4','5','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
-- ここから抽出対象
    , (333,809,'011045','','',1,'北海道石狩郡当別町425番地37号aa','','1','9','3','4','5','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    , (334,0,'011045','','',1,'北海道石狩郡当別町425番地51号','','1','2','9','4','5','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    , (335,0,'011045','','',1,'北海道石狩郡当別町425番地52号','','1','2','3','9','5','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    ;


DELETE FROM `wk_tbl_address_rsdt_delete`;
ALTER TABLE `wk_tbl_address_rsdt_delete` auto_increment = 0;

INSERT INTO `wk_tbl_address_rsdt_delete` (`wk_tbl_address_rsdt_delete_id`,`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
   VALUES 
      (522,801,'011045','','',1,'札幌市豊平区月寒東五条十八丁目aaa17番地11号','','0013018','334','017','556','245','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
    , (523,802,'011045','','',1,'','','0013018','334','017','556','246','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
    , (524,803,'011045','','',1,'','','013018','334','017','556','247','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
    -- 抽出対象外
    , (525,144,'011045','','',1,'','','0013018','1334','017','556','247','1948-07-28','1948-07-28',196,335,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
    , (526,5,'011045','','',0,'','','0013018','334','1017','556','247','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
;
