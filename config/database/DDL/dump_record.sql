CREATE TABLE `dump_record` (
  `dump_record_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `task_info_code` int DEFAULT NULL COMMENT 'タスク情報コード',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `start_datetime` datetime DEFAULT NULL COMMENT '開始日',
  `end_datetime` datetime DEFAULT NULL COMMENT '終了日',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`dump_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
