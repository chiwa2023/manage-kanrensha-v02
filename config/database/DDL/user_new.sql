CREATE TABLE `user_new` (
  `email` varchar(200) NOT NULL COMMENT 'email',
  `regist_code` varchar(200) DEFAULT NULL COMMENT '手入力用認証コード',
  `limit_datetime` datetime DEFAULT NULL COMMENT '手入力用認証コード有効期限日時',
  `verify_token` varchar(200) DEFAULT NULL COMMENT 'URLダイレクトアクセス用認証トークン',
  `verify_limit_date_time` datetime DEFAULT NULL COMMENT 'URLダイレクトアクセス用認証トークン有効期限日時',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
