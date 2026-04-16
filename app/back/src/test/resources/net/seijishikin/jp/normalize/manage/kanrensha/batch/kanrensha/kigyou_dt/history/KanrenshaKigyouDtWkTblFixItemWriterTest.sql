TRUNCATE TABLE `wk_tbl_kanrensha_kigyou_dt_history`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_history` AUTO_INCREMENT = 1;

TRUNCATE TABLE `kanrensha_kigyou_dt_history_99`;

INSERT INTO `wk_tbl_kanrensha_kigyou_dt_history` (
  `wk_kanrensha_kigyou_dt_history_id`,
  `wk_kanrensha_kigyou_dt_history_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `kigyou_dt_delegate`,
  `kigyou_dt_kanrensha_code`,
  `org_delegate_code`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1001, 1001, 1, 0, '株式会社テストA', '東京都テスト区1', '代表A', 'KGA001', 'DGA001', 1, '既存理由', 1, 190, 'gemini-user', NOW());
