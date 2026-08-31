CREATE TABLE `riyousha_combine_org_temp` (
  `riyousha_combine_org_temp_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `riyousha_combine_org_temp_code` int DEFAULT NULL COMMENT '紐づけコード',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `riyousha_role` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '利用者権限',
  `person_code` int DEFAULT NULL COMMENT '個人コード',
  `person_riyousha_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '利用者氏名',
  `org_riyousha_code` int DEFAULT NULL COMMENT '利用者組織コード',
  `org_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '利用者組織名称',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`riyousha_combine_org_temp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
