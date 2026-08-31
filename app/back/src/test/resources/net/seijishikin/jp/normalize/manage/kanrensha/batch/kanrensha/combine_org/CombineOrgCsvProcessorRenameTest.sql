ALTER TABLE `test_manage_kanrensha_v02`.`kanrensha_combine_org_2023` 
     RENAME TO  `test_manage_kanrensha_v02`.`kanrensha_combine_org_2027` ;

DELETE FROM `wk_tbl_kanrensha_combine_org`;
ALTER TABLE `wk_tbl_kanrensha_combine_org` auto_increment = 0;

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


TRUNCATE TABLE kanrensha_seijidantai_master;

INSERT INTO kanrensha_seijidantai_master (
  seijidantai_kanrensha_code,
  poli_org_no,
  is_latest,
  kanrensha_name,
  all_address,
  seijidantai_delegate,
  dantai_kbn,
  compare_name_text,
  insert_user_id,
  insert_user_code,
  insert_user_name,
  insert_timestamp
) VALUES (
  'S-CODE-001',
  '12345',
  1,
  'テスト政治団体',
  '東京都テスト区テスト町1-1',
  '代表テスト',
  'A1',
  'テスト政治団体',
  1,
  1,
  'test-user',
  '2024-01-01 12:00:00'
);

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

DELETE FROM `kanrensha_combine_org_2022`;
ALTER TABLE `kanrensha_combine_org_2022` auto_increment = 0;
INSERT INTO `kanrensha_combine_org_2022` (`kanrensha_combine_org_id`,`kanrensha_combine_org_code`,`is_latest`,`kanrensha_kbn`,`person_kanrensha_code`,`person_name`,`org_kanrensha_code`,`org_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (367,351,1,3,'P-CODE-001','迂回献金　太郎','S-CODE-001','ちゃらんぽらん政治団体',213,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1048-07-29 00:00:00');



