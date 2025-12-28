TRUNCATE TABLE kanrensha_seijidantai_master;


INSERT INTO kanrensha_seijidantai_master (
  kanrensha_seijidantai_master_id,
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
  7654,
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
  '2022-12-13 12:00:00'
),(
  7655,
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
  '2024-12-13 12:00:00'
),(
  7656,
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
  '2027-12-13 12:00:00'
),(
  7657,
  '3-1310-12-345678-9876543',
  '987654321',
  0,
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
