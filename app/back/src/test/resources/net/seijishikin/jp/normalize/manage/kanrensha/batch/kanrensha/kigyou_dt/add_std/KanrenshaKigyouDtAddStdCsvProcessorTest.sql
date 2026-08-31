TRUNCATE TABLE kanrensha_kigyou_dt_master;
TRUNCATE TABLE kanrensha_kigyou_dt_history_40;

INSERT INTO kanrensha_kigyou_dt_history_40 (
  kigyou_dt_kanrensha_code,
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
  '1-2345-67-890123-4567890',
  1,
  'ぼったくり企業',
  '福岡県架空市山麓町',
  '代表者　太郎',
  'ぼったくり企業宮崎県架空市山麓町代表者太郎',
  1,
  1,
  'テストユーザー',
  '2023-11-20 12:00:00'
);

INSERT INTO kanrensha_kigyou_dt_master (
  kigyou_dt_kanrensha_code,
  houjin_no,
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
  '1-2345-67-890123-9876543',
  '9876543210987',
  1,
  'ふんだくり企業',
  '宮崎県架空市',
  '代表者　次郎',
  'ふんだくり企業',
  1,
  1,
  'テストユーザー',
  '2023-11-20 12:00:00'
);
