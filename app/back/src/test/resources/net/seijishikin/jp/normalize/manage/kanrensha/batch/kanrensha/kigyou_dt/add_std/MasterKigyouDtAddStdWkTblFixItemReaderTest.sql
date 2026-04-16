TRUNCATE TABLE `wk_tbl_kanrensha_kigyou_dt_master_result`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_master_result` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_kigyou_dt_master_result` (
  `wk_tbl_kanrensha_kigyou_dt_master_result_id`,
  `wk_tbl_kanrensha_kigyou_dt_master_id`,
  `is_latest`,
  `is_affected`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 101, 1, 1, 1, 190, 'gemini-user', NOW()),
(2, 102, 1, 1, 1, 190, 'gemini-user', NOW()),
(3, 103, 0, 1, 1, 190, 'gemini-user', NOW()),
(4, 104, 1, 1, 1, 191, 'gemini-user', NOW()),
(5, 105, 1, 0, 1, 192, 'gemini-user', NOW());
