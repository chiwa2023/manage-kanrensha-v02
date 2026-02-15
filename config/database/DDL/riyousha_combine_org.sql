CREATE TABLE `riyousha_combine_org` (
  `riyousha_combine_org_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `riyousha_combine_org_code` int DEFAULT NULL COMMENT '紐づけコード',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `riyousha_kbn` smallint DEFAULT NULL COMMENT '紐づけ関連者区分',
  `person_riyousha_code` int DEFAULT NULL COMMENT '紐づけコード',
  `person_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '個人氏名',
  `org_riyousha_code` int DEFAULT NULL COMMENT '紐づけコード',
  `org_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '団体代表者名称',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`riyousha_combine_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
