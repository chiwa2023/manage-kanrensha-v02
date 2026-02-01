CREATE TABLE `task_info` (
  `task_info_id` int NOT NULL COMMENT 'タスク情報Id',
  `task_info_code` int DEFAULT NULL COMMENT 'タスク情報コード',
  `task_info_name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'タスク情報名称',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `role_list` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '権限区分',
  `message_template` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'メッセージテンプレート',
  `transfer_pass` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '遷移パス(URL)',
  `param_query` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT 'SNS同一識別コード',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`task_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
