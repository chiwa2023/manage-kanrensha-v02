CREATE TABLE `partner_access_history_2026` (
  `partner_access_history_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `user_code` int DEFAULT NULL COMMENT 'ユーザコード',
  `user_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ユーザ名',
  `access_url` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '接続Url',
  `ip_address` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IPアドレス',
  `user_agent` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ユーザエージェント',
  `attempt_time` datetime DEFAULT NULL COMMENT '接続日時',
  `is_success` tinyint DEFAULT NULL COMMENT '接続成功フラグ',
  PRIMARY KEY (`partner_access_history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
