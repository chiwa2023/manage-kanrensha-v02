CREATE TABLE `user_new` (
  `email` varchar(200) NOT NULL COMMENT 'email',
  `regist_code` varchar(200) DEFAULT NULL COMMENT 'email',
  `limit_datetime` datetime DEFAULT NULL COMMENT '有効期限日時',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
