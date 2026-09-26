CREATE TABLE `contact_manager` (
  `contact_manager_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `contact_manager_code` int DEFAULT NULL COMMENT '運営者問い合わせコード',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該非',
  `first_timestamp` datetime DEFAULT NULL COMMENT '初回問い合わせ日時',
  `is_closed` tinyint DEFAULT NULL COMMENT '問い合わせクローズ該非',
  `close_timestamp` datetime DEFAULT NULL COMMENT 'クローズ日時',
  `inquire_user_id` int DEFAULT NULL COMMENT '問い合わせユーザId',
  `inquire_user_code` int DEFAULT NULL COMMENT '問い合わせユーザコード',
  `inquire_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '問い合わせユーザ名称',
  `inquire_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '問い合わせタイトル',
  `inquire_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '問い合わせ内容',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`contact_manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

