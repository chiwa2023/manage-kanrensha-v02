CREATE TABLE `kanrensha_person_history_24` (
  `kanrensha_person_history_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルIdd',
  `person_kanrensha_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '個人関連者コード',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新フラグ',
  `partner_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '関連者個人名',
  `all_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '関連者個人全住所',
  `person_shokugyou` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '関連者個人職業',
  `search_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '検索テキスト',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザーId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '削除ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '削除ユーザコード',
  `delete_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '削除ユーザ名',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '削除日時',
  PRIMARY KEY (`kanrensha_person_history_id`),
  FULLTEXT KEY `search_text_index` (`search_text`) /*!50100 WITH PARSER `ngram` */ 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
