DELETE FROM `address_postal_irregular`;
ALTER TABLE `address_postal_irregular` auto_increment = 0;

INSERT INTO `address_postal_irregular` (`address_postal_irregular_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  -- スペースがない
     (640,'060','8515',1,'011011','常盤（その他）','１丁目１マルイト札幌ビル９Ｆ','北海道札幌市中央区北二条西','１丁目１マルイト札幌ビル９Ｆ',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
  -- 最新でない
   , (641,'060','8515',0,'011011','藤野（その他）','１丁目１マルイト札幌ビル９Ｆ','北海道札幌市中央区北二条西','１丁目１マルイト札幌ビル９Ｆ',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
  -- 地方自治体コードが該当しない
   , (642,'060','8515',1,'051011','新都心（次のビルを除く）','１丁目１マルイト札幌ビル９Ｆ','北海道札幌市中央区北二条西','１丁目１マルイト札幌ビル９Ｆ',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   , (643,'060','8515',1,'011011','簾舞（その他）','１丁目１マルイト札幌ビル９Ｆ','北海道札幌市中央区北二条西','１丁目１　マルイト札幌ビル９Ｆ',1,1,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
   ;
   
   