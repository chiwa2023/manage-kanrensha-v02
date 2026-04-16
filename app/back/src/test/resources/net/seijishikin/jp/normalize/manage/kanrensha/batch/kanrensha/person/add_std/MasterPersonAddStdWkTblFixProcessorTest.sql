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
  `judge_reason`,
  `insert_user_id`,
  `insert_user_code`,
  `insert_user_name`,
  `insert_timestamp`
) VALUES
(4001, 4001, 1, 0, '斎藤 雄一', '広島県広島市中区基町10-52', 'コンサルタント', '730-0011', '広島市中区基町', '10-52', '730', '0011', '341013', '0001', '0001', '00000000000000001', '0001', '0001', '082', '1234', '5678', 'saito@example.com', 'http://saito.co.jp', 'Twitter', '@saito_y', 1, '正)', 1, 190, 'gemini-user', NOW());
