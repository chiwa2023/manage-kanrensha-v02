CREATE TABLE `save_file_storage_2026` (
  `save_file_storage_id` int NOT NULL AUTO_INCREMENT COMMENT 'ファイル保存ストレージId',
  `save_file_storage_code` int DEFAULT NULL COMMENT 'ファイル保存ストレージ同一識別コード',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `child_dir` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '格納子ディレクトリ',
  `file_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ファイル名',
  `regist_time_text` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登録Unix時間',
  `shosho_kbn` smallint DEFAULT NULL COMMENT '書証区分',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`save_file_storage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
