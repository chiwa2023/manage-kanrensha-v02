TRUNCATE TABLE `wk_tbl_kanrensha_kigyou_dt_master`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_master` AUTO_INCREMENT = 1;
TRUNCATE TABLE `wk_tbl_kanrensha_kigyou_dt_master_result`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_master_result` AUTO_INCREMENT = 1;

INSERT INTO `wk_tbl_kanrensha_kigyou_dt_master` (
  `wk_tbl_kanrensha_kigyou_dt_master_code`,
  `is_latest`,
  `is_finish`,
  `kanrensha_name`,
  `all_address`,
  `kigyou_dt_delegate`,
  `houjin_no`,
  `org_name_kana`,
  `is_shiten`,
  `org_delegate_code`,
  `address_postal`,
  `address_block`,
  `address_building`,
  `postal1`,
  `postal2`,
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
  `houjin_sbts`,
  `is_foreign`,
  `is_affected`,
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1101, 1, 0, '株式会社ABC', '東京都千代田区1-1-1', '代表山田', '1234567890123', 'カブシキガイシャエービーシー', 0, 'D1001', '100-0001', '千代田区', 'ビルディング101', '100', '0001', '131016', '0001', '0001', '0001', '0001', '03', '1234', '5678', 'abc@example.com', 'http://abc.co.jp', 'Twitter', '@abc_official', '001', 0, 0, NULL, 1, 190, 'gemini-user', NOW()),
(1102, 1, 0, '合同会社DEF', '大阪府大阪市2-2-2', '代表鈴木', '2345678901234', 'ゴウドウガイシャディーイーエフ', 0, 'D1002', '530-0001', '大阪市', 'レジデンス202', '530', '0001', '271271', '0002', '0002', '0002', '0002', '06', '9876', '5432', 'def@example.com', 'http://def.co.jp', 'Facebook', 'def_corp', '002', 0, 0, NULL, 1, 190, 'gemini-user', NOW()),
(1103, 1, 0, '有限会社GHI', '愛知県名古屋市3-3-3', '代表高橋', '3456789012345', 'ユウゲンガイシャジーエイチアイ', 0, 'D1003', '450-0001', '名古屋市', 'アパート303', '450', '0001', '231001', '0003', '0003', '0003', '0003', '05', '1111', '2222', 'ghi@example.com', 'http://ghi.co.jp', 'LinkedIn', 'ghi_jp', '003', 0, 0, NULL, 1, 190, 'gemini-user', NOW()),
(1104, 1, 0, '株式会社JKL', '福岡県福岡市4-4-4', '代表田中', '4567890123456', 'カブシキガイシャジェイケーエル', 0, 'D1004', '810-0001', '福岡市', 'オフィス404', '810', '0001', '401301', '0004', '0004', '0004', '0004', '09', '3333', '4444', 'jkl@example.com', 'http://jkl.co.jp', 'Instagram', 'jkl_inc', '001', 0, 0, NULL, 1, 190, 'gemini-user', NOW()),
(1105, 1, 0, '株式会社MNO', '北海道札幌市5-5-5', '代表渡辺', '5678901234567', 'カブシキガイシャエムエヌオー', 0, 'D1005', '060-0001', '札幌市', 'タワー505', '060', '0001', '011001', '0005', '0005', '0005', '0005', '01', '5555', '6666', 'mno@example.com', 'http://mno.co.jp', 'TikTok', 'mno_official', '001', 0, 0, NULL, 1, 216, 'gemini-user', NOW());


INSERT INTO `wk_tbl_kanrensha_kigyou_dt_master_result` (
  `wk_tbl_kanrensha_kigyou_dt_master_id`,
  `is_latest`,
  `is_affected`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(1, 1, 0, 1, 190, 'gemini-user', NOW()),
(2, 1, 1, 1, 190, 'gemini-user', NOW()),
(3, 1, 0, 1, 190, 'gemini-user', NOW()),
(4, 1, 1, 1, 190, 'gemini-user', NOW()),
(5, 1, 0, 1, 216, 'gemini-user', NOW());
