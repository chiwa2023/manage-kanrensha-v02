TRUNCATE TABLE `wk_tbl_kanrensha_kigyou_dt_history_result`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_history_result` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_kigyou_dt_history_result` (
  `wk_kanrensha_kigyou_dt_history_result_id`,
  `wk_kanrensha_kigyou_dt_history_id`,
  `is_latest`,
  `is_affected`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 1001, 1, 1, 1, 190, 'gemini-user', NOW()),
(2, 1002, 1, 1, 1, 190, 'gemini-user', NOW()),
(3, 1003, 1, 0, 1, 190, 'gemini-user', NOW()),
(4, 1004, 1, 1, 1, 191, 'gemini-user', NOW());
