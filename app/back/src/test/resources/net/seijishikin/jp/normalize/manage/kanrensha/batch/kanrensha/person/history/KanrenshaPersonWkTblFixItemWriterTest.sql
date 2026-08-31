TRUNCATE TABLE `wk_tbl_kanrensha_person_history`;
ALTER TABLE `wk_tbl_kanrensha_person_history` AUTO_INCREMENT = 1;

TRUNCATE TABLE `kanrensha_person_history_13`;

INSERT INTO `wk_tbl_kanrensha_person_history` (
  `wk_kanrensha_person_history_id`,
  `wk_kanrensha_person_history_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `person_shokugyou`,
  `person_kanrensha_code`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(3001, 3001, 1, 0, 'テスト個人', '東京都千代田区', 'テスト職業', 'TEST-P-CODE', 0, '既存理由', 1, 190, 'gemini-user', NOW());