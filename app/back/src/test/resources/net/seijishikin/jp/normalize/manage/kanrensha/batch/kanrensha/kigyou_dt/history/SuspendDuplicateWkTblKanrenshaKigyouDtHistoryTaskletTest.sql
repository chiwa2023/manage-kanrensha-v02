TRUNCATE TABLE `wk_tbl_kanrensha_kigyou_dt_history`;

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
  `insert_timestamp`,
  `delete_user_id`,
  `delete_user_code`,
  `delete_user_name`,
  `delete_timestamp`
) VALUES
(1001,1001, 1, 0, '株式会社テスト', '東京都千代田区1-1-1', '代表取締役A', 'C001', 'D001', 0, NULL, 1, 190, 'gemini-user', NOW(),1, 190, 'gemini-user', '1948-07-01 00:00:00'),
(1002,1002, 1, 0, '合同会社サンプル', '大阪府大阪市北区2-2-2', '代表社員B', 'C002', 'D002', 0, NULL, 1, 190, 'gemini-user', NOW(),1, 190, 'gemini-user', '1948-07-01 00:00:00'),
(1003,1003, 1, 0, '有限会社データ', '愛知県名古屋市中村区3-3-3', '取締役C', 'C003', 'D003', 0, NULL, 1, 190, 'gemini-user', NOW(),1, 190, 'gemini-user', '1948-07-01 00:00:00'),
(1004,1004, 1, 0, 'テストシステムズ株式会社', '福岡県福岡市博多区4-4-4', '代表取締役D', 'C004', 'D004', 0, NULL, 1, 190, 'gemini-user', NOW(),1, 190, 'gemini-user', '1948-07-01 00:00:00'),
(1005,1005, 1, 0, '株式会社モックアップ', '北海道札幌市中央区5-5-5', '代表取締役E', 'C005', 'D005', 0, NULL, 1, 190, 'gemini-user', NOW(),1, 190, 'gemini-user', '1948-07-01 00:00:00'),

(5001,1001, 1, 0, '株式会社テスト', '東京都千代田区1-1-1', '代表取締役A', 'C001', 'D001', 0, NULL, 1, 190, 'gemini-user', NOW(),1, 190, 'gemini-user', '1948-07-01 00:00:00'),
(5002,1001, 1, 0, '株式会社テスト', '東京都千代田区1-1-1', '代表取締役A', 'C001', 'D001', 0, NULL, 1, 190, 'gemini-user', NOW(),1, 190, 'gemini-user', '1948-07-01 00:00:00'),
(6001,1002, 1, 0, '合同会社サンプル', '大阪府大阪市北区2-2-2', '代表社員B', 'C002', 'D002', 0, NULL, 1, 190, 'gemini-user', NOW(),1, 190, 'gemini-user', '1948-07-01 00:00:00');
