DELETE FROM `kanrensha_kigyou_dt_master`;
ALTER TABLE `kanrensha_kigyou_dt_master` auto_increment = 0;

INSERT INTO kanrensha_kigyou_dt_master (kanrensha_kigyou_dt_master_id ,kigyou_dt_kanrensha_code,  is_latest,houjin_no,  kanrensha_name,  all_address,  kigyou_dt_delegate,  compare_name_text,  insert_user_id,  insert_user_code,  insert_user_name,  insert_timestamp) 
VALUES 
  (140 , '1-2345-67-890123-TXhlkXh',  1,  '1234567890' , '株式会社テスト',  '東京都テスト区テスト町1-1',  '代表テスト',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
-- 最新でない 
, (141 , '1-2345-67-890123-TXhlkXh',  0,  '1234567890' , '株式会社テスト',  '東京都テスト区テスト町1-1',  '代表テスト',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
--  法人番号が対象外
, (142 , '1-2345-67-890123-TXhlkXh',  1,  '9999999' , '超元素製造組合',  '東京都テスト区テスト町1-1',  '代表テスト',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
-- 名称が対象外 
, (143 , '1-2345-67-890123-TXhlkXh',  1,  '1234567890' , '超元素製造組合',  '東京都テスト区テスト町1-1',  '代表テスト',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
-- 住所が対象外 
,  (144 , '1-2345-67-890123-TXhlkXh', 1,  '1234567890' , '株式会社テスト',  '東京都架空市山麓町1-1',  '代表テスト',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
-- 代表者が対象外 
,  (145 , '1-2345-67-890123-TXhlkXh', 1,  '1234567890' , '株式会社テスト',  '東京都テスト区テスト町1-1',  '代表者　太郎',  '株式会社テスト',  1,  1,  'test-user',  '2024-01-01 12:00:00')
;
