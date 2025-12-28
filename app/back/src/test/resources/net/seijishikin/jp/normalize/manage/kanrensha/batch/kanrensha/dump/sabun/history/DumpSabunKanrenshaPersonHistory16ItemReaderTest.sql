TRUNCATE TABLE kanrensha_person_history_40;

INSERT INTO kanrensha_person_history_16 (
  kanrensha_person_history_id,
  person_kanrensha_code,
  is_latest,
  all_name,
  all_address,
  person_shokugyou,
  search_text,
  insert_user_id,
  insert_user_code,
  insert_user_name,
  insert_timestamp
) VALUES (
  745,
  '2-2345-67-890123-4567890',
  1,
  'テスト　太郎',
  '福岡県架空市山麓町',
  '無職',
  'テスト　太郎福岡県架空市山麓町無職',
  1,
  1,
  'テストユーザー',
  '2022-09-27 12:00:00'
),(
  746,
  '2-2345-67-890123-4567890',
  1,
  'テスト　太郎',
  '福岡県架空市山麓町',
  '無職',
  'テスト　太郎福岡県架空市山麓町無職',
  1,
  1,
  'テストユーザー',
  '2024-11-20 12:00:00'
),(
  747,
  '2-2345-67-890123-4567890',
  0,
  'テスト　太郎',
  '福岡県架空市山麓町',
  '無職',
  'テスト　太郎福岡県架空市山麓町無職',
  1,
  1,
  'テストユーザー',
  '2023-11-20 12:00:00'
);

