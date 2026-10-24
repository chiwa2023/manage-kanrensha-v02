CREATE TABLE `user_passkey_credential` (
  `passkey_id` bigint NOT NULL AUTO_INCREMENT COMMENT '内部ID',
  `user_person_code` int NOT NULL COMMENT 'ユーザ人物コード',
  `credential_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'WebAuthnクレデンシャルID (Base64URL)',
  `public_key_cose` blob NOT NULL COMMENT '公開鍵 (COSEフォーマット)',
  `sign_count` bigint NOT NULL DEFAULT 0 COMMENT '署名カウンタ (クローン検知用)',
  `aaguid` varchar(36) DEFAULT NULL COMMENT '認証器GUID',
  `device_name` varchar(100) DEFAULT NULL COMMENT '端末名称 (例: iPhone TouchID, PC Windows Hello)',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
  `last_used_at` datetime DEFAULT NULL COMMENT '最終利用日時',
  PRIMARY KEY (`passkey_id`),
  UNIQUE KEY `uk_credential_id` (`credential_id`),
  KEY `idx_user_person_code` (`user_person_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='WebAuthnパスキークレデンシャル管理テーブル';