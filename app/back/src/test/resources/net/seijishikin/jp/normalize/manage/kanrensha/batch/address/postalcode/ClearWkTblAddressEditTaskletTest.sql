DELETE FROM `wk_tbl_postal_edit`;
ALTER TABLE `wk_tbl_postal_edit` auto_increment = 0;

INSERT INTO `wk_tbl_postal_edit` (`wk_tbl_postal_edit_id`,`is_latest`,`flg_edit`,`is_repair`,`lg_code`,`postalcode5`,`postalcode7`,`pref_name_kana`,`city_name_kana`,`org_name_kana`,`pref_name`,`city_name`,`org_name`,`flg_prop1`,`flg_prop2`,`flg_prop3`,`flg_prop4`,`flg_koushin`,`flg_henkou_riyu`,`address_postal_id`,`is_gyoseiku_data`,`address_postal_irregular_id`,`works_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (326,1,'1',NULL,'04207','982  ','9820046','ミヤギケン','ナトリシ','ソウゴダイ','宮城県','名取市','相互台','0','0','1','0','1','4',0,0,0,'',196,245,'管理人　太郎','2026-05-31 14:10:53',0,0,'','1948-07-28 23:59:59')
  , (327,1,'1',NULL,'04207','982  ','9820048','ミヤギケン','ナトリシ','ソウゴダイヒガシ','宮城県','名取市','相互台東','0','0','1','0','1','4',0,0,0,'',196,190,'管理人　太郎','2026-05-31 14:10:53',0,0,'','1948-07-28 23:59:59')
  , (328,1,'1',NULL,'04207','982  ','9820041','ミヤギケン','ナトリシ','タカダテクマノドウ','宮城県','名取市','高舘熊野堂','0','1','0','0','1','4',0,0,0,'',196,190,'管理人　太郎','2026-05-31 14:10:53',0,0,'','1948-07-28 23:59:59')
  , (329,1,'1',NULL,'04207','982  ','9820044','ミヤギケン','ナトリシ','ナチガオカ','宮城県','名取市','那智が丘','0','0','1','0','1','4',0,0,0,'',196,190,'管理人　太郎','2026-05-31 14:10:53',0,0,'','1948-07-28 23:59:59')
  , (330,1,'1',NULL,'04207','982  ','9820047','ミヤギケン','ナトリシ','ミドリダイ','宮城県','名取市','みどり台','0','0','1','0','1','4',0,0,0,'',196,190,'管理人　太郎','2026-05-31 14:10:53',0,0,'','1948-07-28 23:59:59')
;

