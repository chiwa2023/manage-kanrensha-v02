TRUNCATE TABLE `wk_tbl_kanrensha_seijidantai_master_result`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_master_result` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_seijidantai_master_result` (
  `wk_tbl_kanrensha_seijidantai_master_result_id`,
  `wk_tbl_kanrensha_seijidantai_master_id`,
  `is_latest`,
  `is_affected`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 7001, 1, 1, 1, 190, 'gemini-user', NOW()),
(2, 7002, 1, 1, 1, 190, 'gemini-user', NOW()),
(3, 7003, 0, 1, 1, 190, 'gemini-user', NOW()),
(4, 7004, 1, 1, 1, 191, 'gemini-user', NOW()),
(5, 7005, 1, 0, 1, 192, 'gemini-user', NOW());
