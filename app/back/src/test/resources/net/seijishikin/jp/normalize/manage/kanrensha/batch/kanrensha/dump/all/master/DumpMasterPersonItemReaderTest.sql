TRUNCATE TABLE kanrensha_person_master;

INSERT INTO kanrensha_person_master (
  kanrensha_person_master_id,
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
  6788,
  '2-2345-67-890123-9876543',
  1,
  'テスト　次郎',
  '宮崎県架空市',
  '無職',
  'テスト次郎',
  1,
  1,
  'テストユーザー',
  '2022-11-20 12:00:00'
),
(
  6789,
  '2-2345-67-890123-9876543',
  1,
  'テスト　次郎',
  '宮崎県架空市',
  '無職',
  'テスト次郎',
  1,
  1,
  'テストユーザー',
  '2024-11-20 12:00:00'
),
(
  6790,
  '2-2345-67-890123-9876543',
  1,
  'テスト　次郎',
  '宮崎県架空市',
  '無職',
  'テスト次郎',
  1,
  1,
  'テストユーザー',
  '2027-11-20 12:00:00'
),
(
  6791,
  '2-2345-67-890123-9876543',
  0,
  'テスト　次郎',
  '宮崎県架空市',
  '無職',
  'テスト次郎',
  1,
  1,
  'テストユーザー',
  '2023-11-20 12:00:00'
)
;
