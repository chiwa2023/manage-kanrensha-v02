CREATE TABLE `login_history_2025` (
  `login_history_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルid',
  `email` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'メールアドレス',
  `ip_address` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IPアドレス',
  `user_agent` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'user-agent',
  `attempt_time` datetime DEFAULT NULL COMMENT '試行日時',
  `is_success` tinyint DEFAULT NULL COMMENT 'ログイン成否',
  PRIMARY KEY (`login_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
