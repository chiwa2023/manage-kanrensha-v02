TRUNCATE TABLE `wk_tbl_kanrensha_person_master`;
ALTER TABLE `wk_tbl_kanrensha_person_master` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_person_master` (
  `wk_tbl_kanrensha_person_master_id`,
  `wk_tbl_kanrensha_person_master_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `person_shokugyou`,
  `address_postal`,
  `address_block`,
  `address_building`,
  `postalcode1`,
  `postalcode2`,
  `lg_code`,
  `machiaza_id`,
  `blk_id`,
  `prc_id`,
  `rsdt_id`,
  `rsdt2_id`,
  `phon1`,
  `phon2`,
  `phon3`,
  `email`,
  `my_portal_url`,
  `sns_service_name`,
  `sns_account`,
  `is_affected`,
  `is_jusho_format`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(4001, 4001, 1, 0, '斎藤 雄一', '広島県広島市中区基町10-52', 'コンサルタント', '730-0011', '広島市中区基町', '10-52', '730', '0011', '341013', '0001', '0001', '00000000000000001', '0001', '0001', '082', '1234', '5678', 'saito@example.com', 'http://saito.co.jp', 'Twitter', '@saito_y', 1,1, '正)', 1, 190, 'gemini-user', NOW()),
(4002, 4002, 1, 0, '吉田 美咲', '宮城県仙台市青葉区本町3-8-1', '看護師', '980-0014', '仙台市青葉区本町', '3-8-1', '980', '0014', '041011', '0002', '0002', '00000000000000002', '0002', '0002', '022', '2345', '6789', 'yoshida@example.com', 'http://yoshida.co.jp', 'Facebook', 'yoshida.m', 1,1,'正)', 1, 190, 'gemini-user', NOW()),
(4003, 4003, 1, 0, '松本 拓也', '静岡県静岡市葵区追手町9-6', '営業', '420-0853', '静岡市葵区追手町', '9-6', '420', '0853', '221001', '0003', '0003', '00000000000000003', '0003', '0003', '054', '3456', '7890', 'matsumoto@example.com', 'http://matsumoto.co.jp', 'LinkedIn', 'matsumoto_t',0,1, '判定理由', 1, 190, 'gemini-user', NOW()),
(4004, 4004, 0, 0, '井上 あゆみ', '岡山県岡山市北区内山下2-4-6', '企画', '700-0824', '岡山市北区内山下', '2-4-6', '700', '0824', '331018', '0004', '0004', '00000000000000004', '0004', '0004', '086', '4567', '8901', 'inoue@example.com', 'http://inoue.co.jp', 'Instagram', 'inoue_a',1, 1,'正)', 1, 190, 'gemini-user', NOW()),
(4005, 4005, 1, 0, '木村 浩二', '熊本県熊本市中央区水前寺6-18-1', 'マーケティング', '862-0950', '熊本市中央区水前寺', '6-18-1', '862', '0950', '431014', '0005', '0005', '00000000000000005', '0005', '0005', '096', '5678', '9012', 'kimura@example.com', 'http://kimura.co.jp', 'TikTok', 'kimura_k',1,1, '正)', 1, 191, 'gemini-user', NOW());


DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')

