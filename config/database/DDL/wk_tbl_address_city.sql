CREATE TABLE `wk_tbl_address_city` (
  `wk_tbl_address_city_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `lg_code` varchar(6) COLLATE utf8mb4_bin DEFAULT NULL,
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`wk_tbl_address_city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1919 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
