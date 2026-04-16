TRUNCATE TABLE `wk_tbl_kanrensha_person_history_result`;
ALTER TABLE `wk_tbl_kanrensha_person_history_result` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_history_result` (
  `wk_kanrensha_person_history_result_id`,
  `wk_kanrensha_person_history_id`,
  `is_latest`,
  `is_affected`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 3001, 1, 1, 1, 190, 'gemini-user', NOW()),
(2, 3002, 1, 1, 1, 190, 'gemini-user', NOW()),
(3, 3003, 1, 0, 1, 190, 'gemini-user', NOW()),
(4, 3004, 1, 1, 1, 191, 'gemini-user', NOW());