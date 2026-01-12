CREATE TABLE `login_status` (
  `email` varchar(200) COLLATE utf8mb4_0900_bin NOT NULL COMMENT 'メールアドレス',
  `password` varchar(200) COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT 'パスワード',
  `is_success` tinyint DEFAULT NULL COMMENT 'ログイン可否',
  `fail_reason` varchar(200) COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT 'ログイン失敗理由',
  `disabled` tinyint DEFAULT NULL COMMENT '無効状態',
  `disabled_reason` varchar(200) COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '無効状態理由',
  `login_time` datetime DEFAULT NULL COMMENT 'ログイン日時',
  `pass_change_time` datetime DEFAULT NULL COMMENT 'パスワード変更時間',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;
