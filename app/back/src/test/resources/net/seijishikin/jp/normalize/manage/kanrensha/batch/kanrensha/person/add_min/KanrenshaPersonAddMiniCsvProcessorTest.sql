TRUNCATE TABLE kanrensha_person_master;
TRUNCATE TABLE kanrensha_person_history_40;

INSERT INTO kanrensha_person_history_40 (
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
  '2-2345-67-890123-4567890',
  1,
  'テスト　太郎',
  '福岡県架空市山麓町',
  '無職',
  'テスト　太郎福岡県架空市山麓町無職',
  1,
  1,
  'テストユーザー',
  '2023-11-20 12:00:00'
);

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
  '2-2345-67-890123-9876543',
  1,
  'テスト　次郎',
  '宮崎県架空市',
  '無職',
  'テスト次郎',
  1,
  1,
  'テストユーザー',
  '2023-11-20 12:00:00'
);
