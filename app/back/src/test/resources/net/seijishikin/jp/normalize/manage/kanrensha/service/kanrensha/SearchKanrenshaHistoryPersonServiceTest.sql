TRUNCATE TABLE kanrensha_person_history_01;

INSERT INTO kanrensha_person_history_01 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890123-4567895',  0,  'テスト　太郎',  '北海道架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890123-4567896',  1,  'テスト　太郎',  '北海道架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890123-4567897',  1,  'テスト　太郎',  '北海道架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890123-4567898',  1,  'テスト　花子',  '北海道架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_02;

INSERT INTO kanrensha_person_history_02 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890223-4567895',  0,  'テスト　太郎',  '青森県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890223-4567896',  1,  'テスト　太郎',  '青森県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890223-4567897',  1,  'テスト　太郎',  '青森県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890223-4567898',  1,  'テスト　花子',  '青森県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_03;

INSERT INTO kanrensha_person_history_03 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890323-4567895',  0,  'テスト　太郎',  '岩手県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890323-4567896',  1,  'テスト　太郎',  '岩手県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890323-4567897',  1,  'テスト　太郎',  '岩手県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890323-4567898',  1,  'テスト　花子',  '岩手県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;


TRUNCATE TABLE kanrensha_person_history_04;

INSERT INTO kanrensha_person_history_04 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890423-4567895',  0,  'テスト　太郎',  '宮城県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890423-4567896',  1,  'テスト　太郎',  '宮城県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890423-4567897',  1,  'テスト　太郎',  '宮城県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890423-4567898',  1,  'テスト　花子',  '宮城県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_05;

INSERT INTO kanrensha_person_history_05 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890523-4567895',  0,  'テスト　太郎',  '秋田県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890523-4567896',  1,  'テスト　太郎',  '秋田県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890523-4567897',  1,  'テスト　太郎',  '秋田県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890523-4567898',  1,  'テスト　花子',  '秋田県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_06;

INSERT INTO kanrensha_person_history_06 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890623-4567895',  0,  'テスト　太郎',  '山形県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890623-4567896',  1,  'テスト　太郎',  '山形県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890623-4567897',  1,  'テスト　太郎',  '山形県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890623-4567898',  1,  'テスト　花子',  '山形県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_07;

INSERT INTO kanrensha_person_history_07 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890723-4567895',  0,  'テスト　太郎',  '福島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890723-4567896',  1,  'テスト　太郎',  '福島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890723-4567897',  1,  'テスト　太郎',  '福島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890723-4567898',  1,  'テスト　花子',  '福島県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_08;

INSERT INTO kanrensha_person_history_08 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890823-4567895',  0,  'テスト　太郎',  '茨城県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890823-4567896',  1,  'テスト　太郎',  '茨城県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890823-4567897',  1,  'テスト　太郎',  '茨城県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890823-4567898',  1,  'テスト　花子',  '茨城県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_09;

INSERT INTO kanrensha_person_history_09 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-890923-4567895',  0,  'テスト　太郎',  '栃木県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-890923-4567896',  1,  'テスト　太郎',  '栃木県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-890923-4567897',  1,  'テスト　太郎',  '栃木県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-890923-4567898',  1,  'テスト　花子',  '栃木県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_10;

INSERT INTO kanrensha_person_history_10 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891023-4567895',  0,  'テスト　太郎',  '群馬県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891023-4567896',  1,  'テスト　太郎',  '群馬県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891023-4567897',  1,  'テスト　太郎',  '群馬県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891023-4567898',  1,  'テスト　花子',  '群馬県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_11;

INSERT INTO kanrensha_person_history_11 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891123-4567895',  0,  'テスト　太郎',  '埼玉県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891123-4567896',  1,  'テスト　太郎',  '埼玉県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891123-4567897',  1,  'テスト　太郎',  '埼玉県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891123-4567898',  1,  'テスト　花子',  '埼玉県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_12;

INSERT INTO kanrensha_person_history_12 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891223-4567895',  0,  'テスト　太郎',  '千葉県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891223-4567896',  1,  'テスト　太郎',  '千葉県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891223-4567897',  1,  'テスト　太郎',  '千葉県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891223-4567898',  1,  'テスト　花子',  '千葉県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_13;

INSERT INTO kanrensha_person_history_13 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891323-4567895',  0,  'テスト　太郎',  '東京都架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891323-4567896',  1,  'テスト　太郎',  '東京都架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891323-4567897',  1,  'テスト　太郎',  '東京都架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891323-4567898',  1,  'テスト　花子',  '東京都架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_14;

INSERT INTO kanrensha_person_history_14 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891423-4567895',  0,  'テスト　太郎',  '神奈川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891423-4567896',  1,  'テスト　太郎',  '神奈川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891423-4567897',  1,  'テスト　太郎',  '神奈川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891423-4567898',  1,  'テスト　花子',  '神奈川県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

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

TRUNCATE TABLE kanrensha_person_history_16;

INSERT INTO kanrensha_person_history_16 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891623-4567895',  0,  'テスト　太郎',  '富山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891623-4567896',  1,  'テスト　太郎',  '富山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891623-4567897',  1,  'テスト　太郎',  '富山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891623-4567898',  1,  'テスト　花子',  '富山県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_17;

INSERT INTO kanrensha_person_history_17 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891723-4567895',  0,  'テスト　太郎',  '石川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891723-4567896',  1,  'テスト　太郎',  '石川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891723-4567897',  1,  'テスト　太郎',  '石川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891723-4567898',  1,  'テスト　花子',  '石川県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_18;

INSERT INTO kanrensha_person_history_18 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891823-4567895',  0,  'テスト　太郎',  '福井県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891823-4567896',  1,  'テスト　太郎',  '福井県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891823-4567897',  1,  'テスト　太郎',  '福井県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891823-4567898',  1,  'テスト　花子',  '福井県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_19;

INSERT INTO kanrensha_person_history_19 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-891923-4567895',  0,  'テスト　太郎',  '山梨県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-891923-4567896',  1,  'テスト　太郎',  '山梨県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-891923-4567897',  1,  'テスト　太郎',  '山梨県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-891923-4567898',  1,  'テスト　花子',  '山梨県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_20;

INSERT INTO kanrensha_person_history_20 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892023-4567895',  0,  'テスト　太郎',  '長野県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892023-4567896',  1,  'テスト　太郎',  '長野県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892023-4567897',  1,  'テスト　太郎',  '長野県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892023-4567898',  1,  'テスト　花子',  '長野県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_21;

INSERT INTO kanrensha_person_history_21 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892123-4567895',  0,  'テスト　太郎',  '岐阜県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892123-4567896',  1,  'テスト　太郎',  '岐阜県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892123-4567897',  1,  'テスト　太郎',  '岐阜県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892123-4567898',  1,  'テスト　花子',  '岐阜県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_22;

INSERT INTO kanrensha_person_history_22 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892223-4567895',  0,  'テスト　太郎',  '静岡県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892223-4567896',  1,  'テスト　太郎',  '静岡県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892223-4567897',  1,  'テスト　太郎',  '静岡県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892223-4567898',  1,  'テスト　花子',  '静岡県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_23;

INSERT INTO kanrensha_person_history_23 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892323-4567895',  0,  'テスト　太郎',  '愛知県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892323-4567896',  1,  'テスト　太郎',  '愛知県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892323-4567897',  1,  'テスト　太郎',  '愛知県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892323-4567898',  1,  'テスト　花子',  '愛知県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_24;

INSERT INTO kanrensha_person_history_24 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892423-4567895',  0,  'テスト　太郎',  '三重県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892423-4567896',  1,  'テスト　太郎',  '三重県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892423-4567897',  1,  'テスト　太郎',  '三重県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892423-4567898',  1,  'テスト　花子',  '三重県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_25;

INSERT INTO kanrensha_person_history_25 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892523-4567895',  0,  'テスト　太郎',  '滋賀県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892523-4567896',  1,  'テスト　太郎',  '滋賀県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892523-4567897',  1,  'テスト　太郎',  '滋賀県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892523-4567898',  1,  'テスト　花子',  '滋賀県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_26;

INSERT INTO kanrensha_person_history_26 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892623-4567895',  0,  'テスト　太郎',  '京都府架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892623-4567896',  1,  'テスト　太郎',  '京都府架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892623-4567897',  1,  'テスト　太郎',  '京都府架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892623-4567898',  1,  'テスト　花子',  '京都府架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_27;

INSERT INTO kanrensha_person_history_27 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892723-4567895',  0,  'テスト　太郎',  '大阪府架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892723-4567896',  1,  'テスト　太郎',  '大阪府架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892723-4567897',  1,  'テスト　太郎',  '大阪府架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892723-4567898',  1,  'テスト　花子',  '大阪府架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_28;

INSERT INTO kanrensha_person_history_28 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892823-4567895',  0,  'テスト　太郎',  '兵庫県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892823-4567896',  1,  'テスト　太郎',  '兵庫県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892823-4567897',  1,  'テスト　太郎',  '兵庫県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892823-4567898',  1,  'テスト　花子',  '兵庫県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_29;

INSERT INTO kanrensha_person_history_29 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-892923-4567895',  0,  'テスト　太郎',  '奈良県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-892923-4567896',  1,  'テスト　太郎',  '奈良県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-892923-4567897',  1,  'テスト　太郎',  '奈良県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-892923-4567898',  1,  'テスト　花子',  '奈良県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_30;

INSERT INTO kanrensha_person_history_30 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893023-4567895',  0,  'テスト　太郎',  '和歌山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893023-4567896',  1,  'テスト　太郎',  '和歌山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893023-4567897',  1,  'テスト　太郎',  '和歌山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893023-4567898',  1,  'テスト　花子',  '和歌山県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_31;

INSERT INTO kanrensha_person_history_31 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893123-4567895',  0,  'テスト　太郎',  '鳥取県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893123-4567896',  1,  'テスト　太郎',  '鳥取県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893123-4567897',  1,  'テスト　太郎',  '鳥取県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893123-4567898',  1,  'テスト　花子',  '鳥取県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_32;

INSERT INTO kanrensha_person_history_32 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893223-4567895',  0,  'テスト　太郎',  '島根県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893223-4567896',  1,  'テスト　太郎',  '島根県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893223-4567897',  1,  'テスト　太郎',  '島根県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893223-4567898',  1,  'テスト　花子',  '島根県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_33;

INSERT INTO kanrensha_person_history_33 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893323-4567895',  0,  'テスト　太郎',  '岡山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893323-4567896',  1,  'テスト　太郎',  '岡山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893323-4567897',  1,  'テスト　太郎',  '岡山県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893323-4567898',  1,  'テスト　花子',  '岡山県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_34;

INSERT INTO kanrensha_person_history_34 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893423-4567895',  0,  'テスト　太郎',  '広島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893423-4567896',  1,  'テスト　太郎',  '広島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893423-4567897',  1,  'テスト　太郎',  '広島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893423-4567898',  1,  'テスト　花子',  '広島県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_35;

INSERT INTO kanrensha_person_history_35 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893523-4567895',  0,  'テスト　太郎',  '山口県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893523-4567896',  1,  'テスト　太郎',  '山口県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893523-4567897',  1,  'テスト　太郎',  '山口県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893523-4567898',  1,  'テスト　花子',  '山口県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_36;

INSERT INTO kanrensha_person_history_36 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893623-4567895',  0,  'テスト　太郎',  '徳島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893623-4567896',  1,  'テスト　太郎',  '徳島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893623-4567897',  1,  'テスト　太郎',  '徳島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893623-4567898',  1,  'テスト　花子',  '徳島県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_37;

INSERT INTO kanrensha_person_history_37 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893723-4567895',  0,  'テスト　太郎',  '香川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893723-4567896',  1,  'テスト　太郎',  '香川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893723-4567897',  1,  'テスト　太郎',  '香川県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893723-4567898',  1,  'テスト　花子',  '香川県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_38;

INSERT INTO kanrensha_person_history_38 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893823-4567895',  0,  'テスト　太郎',  '愛媛県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893823-4567896',  1,  'テスト　太郎',  '愛媛県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893823-4567897',  1,  'テスト　太郎',  '愛媛県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893823-4567898',  1,  'テスト　花子',  '愛媛県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_39;

INSERT INTO kanrensha_person_history_39 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-893923-4567895',  0,  'テスト　太郎',  '高知県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-893923-4567896',  1,  'テスト　太郎',  '高知県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-893923-4567897',  1,  'テスト　太郎',  '高知県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-893923-4567898',  1,  'テスト　花子',  '高知県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_40;

INSERT INTO kanrensha_person_history_40 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-894023-4567895',  0,  'テスト　太郎',  '福岡県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-894023-4567896',  1,  'テスト　太郎',  '福岡県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-894023-4567897',  1,  'テスト　太郎',  '福岡県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-894023-4567898',  1,  'テスト　花子',  '福岡県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_41;

INSERT INTO kanrensha_person_history_41 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-894123-4567895',  0,  'テスト　太郎',  '佐賀県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-894123-4567896',  1,  'テスト　太郎',  '佐賀県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-894123-4567897',  1,  'テスト　太郎',  '佐賀県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-894123-4567898',  1,  'テスト　花子',  '佐賀県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_42;

INSERT INTO kanrensha_person_history_42 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-894223-4567895',  0,  'テスト　太郎',  '長崎県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-894223-4567896',  1,  'テスト　太郎',  '長崎県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-894223-4567897',  1,  'テスト　太郎',  '長崎県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-894223-4567898',  1,  'テスト　花子',  '長崎県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_43;

INSERT INTO kanrensha_person_history_43 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-894323-4567895',  0,  'テスト　太郎',  '熊本県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-894323-4567896',  1,  'テスト　太郎',  '熊本県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-894323-4567897',  1,  'テスト　太郎',  '熊本県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-894323-4567898',  1,  'テスト　花子',  '熊本県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_44;

INSERT INTO kanrensha_person_history_44 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-894423-4567895',  0,  'テスト　太郎',  '大分県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-894423-4567896',  1,  'テスト　太郎',  '大分県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-894423-4567897',  1,  'テスト　太郎',  '大分県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-894423-4567898',  1,  'テスト　花子',  '大分県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_45;

INSERT INTO kanrensha_person_history_45 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-894523-4567895',  0,  'テスト　太郎',  '宮崎県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-894523-4567896',  1,  'テスト　太郎',  '宮崎県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-894523-4567897',  1,  'テスト　太郎',  '宮崎県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-894523-4567898',  1,  'テスト　花子',  '宮崎県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_46;

INSERT INTO kanrensha_person_history_46 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-894623-4567895',  0,  'テスト　太郎',  '鹿児島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-894623-4567896',  1,  'テスト　太郎',  '鹿児島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-894623-4567897',  1,  'テスト　太郎',  '鹿児島県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-894623-4567898',  1,  'テスト　花子',  '鹿児島県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_47;

INSERT INTO kanrensha_person_history_47 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-894723-4567895',  0,  'テスト　太郎',  '沖縄県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-894723-4567896',  1,  'テスト　太郎',  '沖縄県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-894723-4567897',  1,  'テスト　太郎',  '沖縄県架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-894723-4567898',  1,  'テスト　花子',  '沖縄県架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;

TRUNCATE TABLE kanrensha_person_history_99;

INSERT INTO kanrensha_person_history_99 (  kanrensha_person_history_id,  person_kanrensha_code,  is_latest,  all_name,  all_address,  person_shokugyou,  search_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
   VALUES 
    --最新でない
      (  745,  '2-2345-67-899923-4567895',  0,  'テスト　太郎',  'その他架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  746,  '2-2345-67-899923-4567896',  1,  'テスト　太郎',  'その他架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    , (  747,  '2-2345-67-899923-4567897',  1,  'テスト　太郎',  'その他架空市山麓町',  '無職',  'テスト太郎北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
    --検索語が一致しない
    , (  748,  '2-2345-67-899923-4567898',  1,  'テスト　花子',  'その他架空市山麓町',  '無職',  'テスト花子北海道架空市山麓町無職',  1,  1,  'テストユーザー',  '2022-09-27 12:00:00')
;


