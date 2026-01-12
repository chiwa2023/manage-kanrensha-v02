CREATE TABLE `user_person` (
  `user_person_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `user_person_code` int DEFAULT NULL COMMENT 'ユーザコード',
  `user_person_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ユーザ名称',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `email` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'email',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`user_person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
