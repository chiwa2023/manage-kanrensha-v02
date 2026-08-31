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
(3001, 3001, 1, 0, '政治団体A', '東京都千代田区1-1', '代表A', 'A1', '11111', 1, '正)', 1, 190, 'gemini-user', NOW()),
(3002, 3002, 1, 0, '政治団体B', '大阪府大阪市2-2', '代表B', 'B1', '22222', 1, '正)', 1, 190, 'gemini-user', NOW()),
(3003, 3003, 1, 0, '政治団体C', '愛知県名古屋市3-3', '代表C', 'C1', '33333', 0, '判定理由', 1, 190, 'gemini-user', NOW()),
(3004, 3004, 0, 0, '政治団体D', '福岡県福岡市4-4', '代表D', 'D1', '44444', 1, '正)', 1, 190, 'gemini-user', NOW()),
(3005, 3005, 0, 1, '政治団体E', '北海道札幌市5-5', '代表E', 'E1', '55555', 0, '正)', 1, 190, 'gemini-user', NOW()),
(3006, 3006, 1, 0, '政治団体F', '沖縄県那覇市6-6', '代表F', 'F1', '66666', 1, '正)', 1, 191, 'gemini-user', NOW());
