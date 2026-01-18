CREATE TABLE `partner_access_token` (
  `partner_access_token_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルID',
  `user_code` int NOT NULL COMMENT 'ユーザコード',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ユーザ名',
  `access_token_hash` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'アクセストークンハッシュ値',
  `expires_at` datetime NOT NULL COMMENT '有効期限',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '発行日時',
  `last_used_at` datetime DEFAULT NULL COMMENT '最終利用日時',
  `revoked_at` datetime DEFAULT NULL COMMENT '失効日時',
  `ip_address` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IPアドレス',
  PRIMARY KEY (`partner_access_token_id`),
  UNIQUE KEY `access_token_hash` (`access_token_hash`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
