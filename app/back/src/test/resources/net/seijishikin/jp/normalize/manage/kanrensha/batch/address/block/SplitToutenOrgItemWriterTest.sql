DELETE FROM `wk_tbl_postal_common`;
ALTER TABLE `wk_tbl_postal_common` auto_increment = 0;

DELETE FROM `address_postal_irregular`;
ALTER TABLE `address_postal_irregular` auto_increment = 0;


INSERT INTO `address_postal_irregular` (`address_postal_irregular_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
     (830,'060','8515',1,'011011','西十九条南（３５〜３８、４１、４２丁目）','北海道帯広市西十九条南','postal','block',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (832,'060','8515',0,'011011','八幡町（１丁目、２丁目）','１丁目１マルイト札幌ビル９Ｆ','北海道札幌市中央区北二条西','１丁目１マルイト札幌ビル９Ｆ',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (833,'060','8515',1,'021011','八幡町（１丁目、２丁目）','１丁目１マルイト札幌ビル９Ｆ','北海道札幌市中央区北二条西','１丁目１マルイト札幌ビル９Ｆ',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (834,'060','8515',1,'021011','八幡町（１丁目、２丁目）','１丁目１マルイト札幌ビル９Ｆ','北海道札幌市中央区北二条西','１丁目１マルイト札幌ビル９Ｆ',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   ;