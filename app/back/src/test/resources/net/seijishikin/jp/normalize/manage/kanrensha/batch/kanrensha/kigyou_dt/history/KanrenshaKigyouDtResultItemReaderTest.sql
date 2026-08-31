TRUNCATE TABLE `wk_tbl_kanrensha_kigyou_dt_history`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_history` AUTO_INCREMENT = 1;

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
(1001, 1001, 1, 0, '株式会社テストA', '東京都テスト区1', '代表A', 'KGA001', 'DGA001', 1, '正)', 1, 190, 'gemini-user', NOW()),
(1002, 1002, 1, 0, '株式会社テストB', '大阪府テスト市2', '代表B', 'KGB002', 'DGB002', 1, '正)', 1, 190, 'gemini-user', NOW()),
(1003, 1003, 1, 0, '株式会社テストC', '愛知県テスト市3', '代表C', 'KGC003', 'DGC003', 0, '判定理由', 1, 190, 'gemini-user', NOW()),
(1004, 1004, 0, 0, '株式会社テストD', '福岡県テスト市4', '代表D', 'KGD004', 'DGD004', 1, '正)', 1, 190, 'gemini-user', NOW()),
(1005, 1005, 1, 1, '株式会社テストE', '北海道テスト市5', '代表E', 'KGE005', 'DGE005', 1, '正)', 1, 190, 'gemini-user', NOW()),
(1006, 1006, 1, 0, '株式会社テストF', '沖縄県テスト市6', '代表F', 'KGF006', 'DGF006', 1, '正)', 1, 191, 'gemini-user', NOW());
