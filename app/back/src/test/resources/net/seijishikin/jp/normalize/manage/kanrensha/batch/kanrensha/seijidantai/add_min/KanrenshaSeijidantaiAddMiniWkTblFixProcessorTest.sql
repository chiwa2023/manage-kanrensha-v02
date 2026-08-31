TRUNCATE TABLE `wk_tbl_kanrensha_seijidantai_add_min`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_add_min` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (
  `wk_tbl_kanrensha_seijidantai_add_min_id`,
  `wk_tbl_kanrensha_seijidantai_add_min_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `seijidantai_delegate`,
  `dantai_kbn`,
  `poli_org_no`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(3001, 3001, 1, 0, '政治団体A', '東京都千代田区1-1', '代表A', 'A1', '11111', 1, '正)', 1, 190, 'gemini-user', NOW());
