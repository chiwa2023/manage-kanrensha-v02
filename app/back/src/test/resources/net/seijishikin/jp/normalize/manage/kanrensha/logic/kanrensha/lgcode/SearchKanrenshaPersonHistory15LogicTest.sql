TRUNCATE TABLE kanrensha_person_history_15;

INSERT INTO kanrensha_person_history_15 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891523-4567895',  0,  'テスト　太郎',  '新潟県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891523-4567896',  1,  'テスト　太郎',  '新潟県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891523-4567897',  1,  'テスト　太郎',  '新潟県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891523-4567898',  1,  'テスト　花子',  '新潟県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')

;

