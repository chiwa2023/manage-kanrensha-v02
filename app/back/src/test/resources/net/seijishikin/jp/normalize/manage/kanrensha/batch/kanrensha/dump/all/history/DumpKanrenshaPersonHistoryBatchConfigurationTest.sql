TRUNCATE TABLE kanrensha_person_history_01;

INSERT INTO kanrensha_person_history_01 (
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


DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')

