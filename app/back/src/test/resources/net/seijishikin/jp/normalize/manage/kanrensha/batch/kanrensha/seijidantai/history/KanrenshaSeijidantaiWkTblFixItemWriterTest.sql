TRUNCATE TABLE `wk_tbl_kanrensha_seijidantai_history`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_history` AUTO_INCREMENT = 1;

TRUNCATE TABLE `kanrensha_seijidantai_history_13`;

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
(6001, 6001, 1, 0, 'テスト政治団体', '東京都千代田区', '代表テスト', 'S-CODE-TEST', 'D-CODE-TEST', 0, '既存理由', 1, 190, 'gemini-user', NOW());