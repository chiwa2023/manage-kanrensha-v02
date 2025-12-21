TRUNCATE TABLE `wk_tbl_kanrensha_seijidantai_history`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_history` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_seijidantai_history` (
  `wk_kanrensha_seijidantai_history_id`,
  `wk_kanrensha_seijidantai_history_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `seijidantai_delegate`,
  `seijidantai_kanrensha_code`,
  `org_delegate_code`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(6001, 6001, 1, 0, '政治団体A', '住所A', '代表A', 'S-CODE-A', 'D-CODE-A', 1, '正)', 1, 190, 'gemini-user', NOW()),
(6002, 6002, 1, 0, '政治団体B', '住所B', '代表B', 'S-CODE-B', 'D-CODE-B', 1, '正)', 1, 190, 'gemini-user', NOW()),
(6003, 6003, 1, 0, '政治団体C', '住所C', '代表C', 'S-CODE-C', 'D-CODE-C', 0, '判定理由', 1, 190, 'gemini-user', NOW()),
(6004, 6004, 0, 0, '政治団体D', '住所D', '代表D', 'S-CODE-D', 'D-CODE-D', 1, '正)', 1, 190, 'gemini-user', NOW()),
(6005, 6005, 1, 1, '政治団体E', '住所E', '代表E', 'S-CODE-E', 'D-CODE-E', 1, '正)', 1, 190, 'gemini-user', NOW()),
(6006, 6006, 1, 0, '政治団体F', '住所F', '代表F', 'S-CODE-F', 'D-CODE-F', 1, '正)', 1, 191, 'gemini-user', NOW());