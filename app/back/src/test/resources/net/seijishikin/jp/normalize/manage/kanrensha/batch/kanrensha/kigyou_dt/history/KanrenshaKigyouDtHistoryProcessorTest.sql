TRUNCATE TABLE kanrensha_kigyou_dt_master;

INSERT INTO kanrensha_kigyou_dt_master (
  kigyou_dt_kanrensha_code,
  is_latest,
  kanrensha_name,
  all_address,
  kigyou_dt_delegate,
  compare_name_text,
  insert_user_id,
  insert_user_code,
  insert_user_name,
  insert_timestamp
) VALUES (
  'K-CODE-001',
  1,
  '株式会社テスト',
  '東京都テスト区テスト町1-1',
  '代表テスト',
  '株式会社テスト',
  1,
  1,
  'test-user',
  '2024-01-01 12:00:00'
);
