TRUNCATE TABLE kanrensha_seijidantai_master;

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
  'S-CODE-001',
  '12345',
  1,
  'テスト政治団体',
  '東京都テスト区テスト町1-1',
  '代表テスト',
  'A1',
  'テスト政治団体',
  1,
  1,
  'test-user',
  '2024-01-01 12:00:00'
);
