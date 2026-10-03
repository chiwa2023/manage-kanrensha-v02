CREATE TABLE `kanrensha_code_used` (
  `kanrensha_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '併合先コード',
  `kanrensha_kbn` smallint DEFAULT NULL COMMENT '関連者区分',
  PRIMARY KEY (`kanrensha_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin