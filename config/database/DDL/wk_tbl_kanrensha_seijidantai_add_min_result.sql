CREATE TABLE `wk_tbl_kanrensha_seijidantai_add_min_result` (
  `wk_tbl_kanrensha_seijidantai_add_min_result_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `wk_tbl_kanrensha_seijidantai_add_min_id` int DEFAULT NULL COMMENT '関連者政治団体最小登録Id',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`wk_tbl_kanrensha_seijidantai_add_min_result_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
