TRUNCATE TABLE kanrensha_seijidantai_master;
TRUNCATE TABLE kanrensha_seijidantai_history_13;

INSERT INTO kanrensha_seijidantai_history_13 (
  seijidantai_kanrensha_code,
  is_latest,
  all_name,
  all_address,
  org_delegate_name,
  search_text,
  insert_user_id,
  insert_user_code,
  insert_user_name,
  insert_timestamp
) VALUES (
  '3-1310-12-345678-1234567',
  1,
  'テスト政治団体',
  '東京都架空市架空町',
  '代表者A',
  'テスト政治団体東京都架空市架空町代表者A',
  1,
  1,
  'テストユーザー',
  '2023-12-13 12:00:00'
);

INSERT INTO kanrensha_seijidantai_master (
  seijidantai_kanrensha_code,
  poli_org_no,
  is_latest,
  kanrensha_name,
  all_address,
  seijidantai_delegate,
  dantai_kbn,
  compare_name_text,
  insert_user_id,
  insert_user_code,
  insert_user_name,
  insert_timestamp
) VALUES (
  '3-1310-12-345678-9876543',
  '987654321',
  1,
  '同じ名前の政治団体',
  '東京都架空市',
  '代表者B',
  'A1',
  '同じ名前の政治団体',
  1,
  1,
  'テストユーザー',
  '2023-12-13 12:00:00'
);
