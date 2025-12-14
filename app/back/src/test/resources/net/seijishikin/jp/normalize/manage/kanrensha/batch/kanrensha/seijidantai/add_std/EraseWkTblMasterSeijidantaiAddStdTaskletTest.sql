DELETE FROM `wk_tbl_kanrensha_seijidantai_master`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_master` auto_increment = 0;

DELETE FROM `wk_tbl_kanrensha_seijidantai_master_result`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_master_result` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_seijidantai_master` (
  `wk_tbl_kanrensha_seijidantai_master_id`,
  `wk_tbl_kanrensha_seijidantai_master_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `seijidantai_delegate`,
  `dantai_kbn`,
  `poli_org_no`,
  `address_postal`,
  `address_block`,
  `address_building`,
  `postalcode1`,
  `postalcode2`,
  `lg_code`,
  `machiaza_id`,
  `blk_id`,
  `rsdt_id`,
  `rsdt2_id`,
  `phon1`,
  `phon2`,
  `phon3`,
  `email`,
  `my_portal_url`,
  `sns_service_name`,
  `sns_account`,
  `org_name_kana`,
  `org_delegate_code`,
  `account_mgr_code`,
  `account_mgr_name`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 7001, 1, 0, '自由民主党', '東京都千代田区永田町1-11-23', '総裁K', 'A1', '10001', '100-8910', '千代田区永田町', '1-11-23', '100', '8910', '131016', '0001', '0001', '0001', '0001', '03', '3595', '1111', 'ldp@example.com', 'http://www.jimin.jp/', 'Twitter', '@jimin_koho', 'ジユウミンシュトウ', 'D011', 'A001', '会計責任者1', 0, NULL, 1, 190, 'gemini-user', NOW()),
(2, 7002, 1, 0, '立憲民主党', '東京都千代田区平河町2-12-4', '代表L', 'A1', '10002', '100-0010', '千代田区平河町', '2-12-4', '100', '0010', '131016', '0002', '0002', '0002', '0002', '03', '3595', '2222', 'cdp@example.com', 'http://cdp-japan.jp/', 'Facebook', 'cdp.japan', 'リッケンミンシュトウ', 'D012', 'A002', '会計責任者2', 0, NULL, 1, 190, 'gemini-user', NOW()),
(3, 7003, 1, 0, '公明党', '東京都新宿区南元町17', '代表M', 'A1', '10003', '160-0016', '新宿区南元町', '17', '160', '0016', '131041', '0003', '0003', '0003', '0003', '03', '5395', '3333', 'komei@example.com', 'http://www.komei.or.jp/', 'LINE', '@komei_official', 'コウメイトウ', 'D013', 'A003', '会計責任者3', 0, NULL, 1, 190, 'gemini-user', NOW()),
(4, 7004, 1, 0, '日本共産党', '東京都渋谷区千駄ケ谷4-26-7', '委員長N', 'A1', '10004', '151-0051', '渋谷区千駄ケ谷', '4-26-7', '151', '0051', '131131', '0004', '0004', '0004', '0004', '03', '3403', '4444', 'jcp@example.com', 'http://www.jcp.or.jp/', 'YouTube', 'jcptube', 'ニホンキョウサントウ', 'D014', 'A004', '会計責任者4', 0, NULL, 1, 190, 'gemini-user', NOW()),
(5, 7005, 1, 0, '日本維新の会', '大阪府大阪市北区天神橋2丁目北1-21', '代表O', 'A1', '10005', '530-0041', '大阪市北区天神橋', '2丁目北1-21', '530', '0041', '271271', '0005', '0005', '0005', '0005', '06', '6362', '5555', 'ishin@example.com', 'http://o-ishin.jp/', 'Twitter', '@osaka_ishin', 'ニホンイシンノカイ', 'D015', 'A005', '会計責任者5', 0, NULL, 1, 216, 'gemini-user', NOW());

INSERT INTO `wk_tbl_kanrensha_seijidantai_master_result` (`wk_tbl_kanrensha_seijidantai_master_id`, `is_latest`, `is_affected`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 1, 0, 1, 190, 'gemini-user', NOW()),
(2, 1, 1, 1, 190, 'gemini-user', NOW()),
(3, 1, 0, 1, 190, 'gemini-user', NOW()),
(4, 1, 1, 1, 190, 'gemini-user', NOW()),
(5, 1, 0, 1, 216, 'gemini-user', NOW());



