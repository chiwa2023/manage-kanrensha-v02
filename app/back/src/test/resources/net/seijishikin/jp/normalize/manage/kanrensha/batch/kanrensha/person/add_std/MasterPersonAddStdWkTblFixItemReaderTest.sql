TRUNCATE TABLE `wk_tbl_kanrensha_person_master_result`;
ALTER TABLE `wk_tbl_kanrensha_person_master_result` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_master_result` (
  `wk_tbl_kanrensha_person_master_result_id`,
  `wk_tbl_kanrensha_person_master_id`,
  `is_latest`,
  `is_affected`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 4001, 1, 1, 1, 190, 'gemini-user', NOW()),
(2, 4002, 1, 1, 1, 190, 'gemini-user', NOW()),
(3, 4003, 0, 1, 1, 190, 'gemini-user', NOW()),
(4, 4004, 1, 1, 1, 191, 'gemini-user', NOW()),
(5, 4005, 1, 0, 1, 192, 'gemini-user', NOW());
