TRUNCATE TABLE `wk_tbl_kanrensha_person_add_min_result`;
ALTER TABLE `wk_tbl_kanrensha_person_add_min_result` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_add_min_result` (
  `wk_tbl_kanrensha_person_add_min_result_id`,
  `wk_tbl_kanrensha_person_add_min_id`,
  `is_latest`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 201, 1, 1, 190, 'gemini-user', NOW()),
(2, 202, 1, 1, 190, 'gemini-user', NOW()),
(3, 203, 0, 1, 190, 'gemini-user', NOW()),
(4, 204, 1, 1, 191, 'gemini-user', NOW()),
(5, 205, 1, 1, 192, 'gemini-user', NOW());
