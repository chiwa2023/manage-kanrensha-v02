CREATE TABLE `user_person` (
  `user_person_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `user_person_code` int DEFAULT NULL COMMENT 'ユーザコード',
  `user_person_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ユーザ名称',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'email',
  `is_alert_task_start` tinyint DEFAULT NULL COMMENT 'タスク開始通知有無',
  `is_alert_task_end` tinyint DEFAULT NULL COMMENT 'タスク終了通知有無',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`user_person_id`),
  KEY `code` (`user_person_code`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
