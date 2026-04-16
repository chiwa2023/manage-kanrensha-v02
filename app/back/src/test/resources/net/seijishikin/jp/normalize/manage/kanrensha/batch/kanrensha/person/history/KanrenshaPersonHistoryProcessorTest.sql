TRUNCATE TABLE kanrensha_person_master;

INSERT INTO kanrensha_person_master (
  person_kanrensha_code,
  is_latest,
  kanrensha_name,
  all_address,
  person_shokugyou,
  compare_name_text,
  insert_user_id,
  insert_user_code,
  insert_user_name,
  insert_timestamp
) VALUES (
  'P-CODE-001',
  1,
  'テスト　太郎',
  '東京都テスト区テスト町1-1',
  '会社員',
  'テスト太郎',
  1,
  1,
  'test-user',
  '2024-01-01 12:00:00'
);
