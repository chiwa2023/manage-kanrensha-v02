CREATE TABLE `sns_service` (
  `sns_service_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `sns_service_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `sns_service_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新フラグ',
  `sns_portal_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT 'ポータルURL',
  `search_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '全文検索用カラム',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`sns_service_id`),
  FULLTEXT KEY `search_text_index` (`search_text`) /*!50100 WITH PARSER `ngram` */ 
) ENGINE=InnoDB AUTO_INCREMENT=499 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin