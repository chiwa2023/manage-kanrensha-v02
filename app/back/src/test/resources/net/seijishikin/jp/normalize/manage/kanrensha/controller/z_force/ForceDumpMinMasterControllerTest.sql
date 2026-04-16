-- 企業団体マスタ
DELETE FROM `kanrensha_kigyou_dt_master`;
ALTER TABLE `kanrensha_kigyou_dt_master` auto_increment = 0;

INSERT INTO `kanrensha_kigyou_dt_master` (`kanrensha_kigyou_dt_master_id`,`kigyou_dt_kanrensha_code`,`houjin_no`,`is_latest`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (5655,'430','1234567890',1,'ふんだくり企業','和歌山県架空市山麓町','代表者　太郎','ふんだくり企業',216,190,'管理者太郎','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 23:59:59');

INSERT INTO `kanrensha_kigyou_dt_master` (`kanrensha_kigyou_dt_master_id`,`kigyou_dt_kanrensha_code`,`houjin_no`,`is_latest`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (5656,'430','1234567890',1,'ふんだくり企業','和歌山県架空市山麓町','代表者　太郎','ふんだくり企業',216,190,'管理者太郎','2024-12-05 12:34:56',0,0,'ユーザ','1948-07-28 23:59:59');

INSERT INTO `kanrensha_kigyou_dt_master` (`kanrensha_kigyou_dt_master_id`,`kigyou_dt_kanrensha_code`,`houjin_no`,`is_latest`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (5657,'430','1234567890',1,'ふんだくり企業','和歌山県架空市山麓町','代表者　太郎','ふんだくり企業',216,190,'管理者太郎','2027-12-05 12:34:56',0,0,'ユーザ','1948-07-28 23:59:59');

INSERT INTO `kanrensha_kigyou_dt_master` (`kanrensha_kigyou_dt_master_id`,`kigyou_dt_kanrensha_code`,`houjin_no`,`is_latest`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
  (5658,'430','1234567890',0,'ふんだくり企業','和歌山県架空市山麓町','代表者　太郎','ふんだくり企業',216,190,'管理者太郎','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 23:59:59');
  
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
);

TRUNCATE TABLE kanrensha_seijidantai_master;


INSERT INTO kanrensha_seijidantai_master (
  kanrensha_seijidantai_master_id,
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
  7654,
  '3-1310-12-345678-9876543',
  '987654321',
  1,
  '同じ名前の政治団体',
  '東京都架空市',
  '代表者B',
  '03',
  '同じ名前の政治団体',
  1,
  1,
  'テストユーザー',
  '2022-12-13 12:00:00'
),(
  7655,
  '3-1310-12-345678-9876543',
  '987654321',
  1,
  '同じ名前の政治団体',
  '東京都架空市',
  '代表者B',
  '03',
  '同じ名前の政治団体',
  1,
  1,
  'テストユーザー',
  '2024-12-13 12:00:00'
),(
  7656,
  '3-1310-12-345678-9876543',
  '987654321',
  1,
  '同じ名前の政治団体',
  '東京都架空市',
  '代表者B',
  '03',
  '同じ名前の政治団体',
  1,
  1,
  'テストユーザー',
  '2027-12-13 12:00:00'
),(
  7657,
  '3-1310-12-345678-9876543',
  '987654321',
  0,
  '同じ名前の政治団体',
  '東京都架空市',
  '代表者B',
  '03',
  '同じ名前の政治団体',
  1,
  1,
  'テストユーザー',
  '2023-12-13 12:00:00'
);


DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;


DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;
INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_template`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
   (136,314,'サンプルタスク情報',1,'admin,manager','メッセージ','pageUrl','123=4',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(137,315,'サンプルタスク情報',1,'admin,manager','メッセージ','pageUrl','123=4',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(138,316,'サンプルタスク情報',1,'admin,manager','メッセージ','pageUrl','123=4',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ;
 
