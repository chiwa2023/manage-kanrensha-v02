CREATE TABLE `address_postal` (
  `address_postal_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `postalcode1` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '郵便番号1',
  `postalcode2` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '郵便番号2',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `lg_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地方自治体コード',
  `address_org` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '原文書住所',
  `address_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '住所郵便番号まで',
  `is_gyoseiku_data` tinyint DEFAULT NULL COMMENT '行政区データ該否',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`address_postal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
