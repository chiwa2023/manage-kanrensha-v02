CREATE TABLE `address_all_city` (
  `address_all_city_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `lg_code` varchar(6) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地方自治体コード',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `address_name` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '住所名',
  `address_name_kana` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '住所名かな',
  `effect_date` date DEFAULT NULL COMMENT '適用開始日',
  `abolish_date` date DEFAULT NULL COMMENT '廃止日',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`address_all_city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
