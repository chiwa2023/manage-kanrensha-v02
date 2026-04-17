CREATE TABLE `user_password_reset` (
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'メールアドレス',
  `regist_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '認証コード',
  `limit_datetime` datetime DEFAULT NULL COMMENT '認証コード有効期限日時',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
