DELETE FROM `address_postal`;
ALTER TABLE `address_postal` auto_increment = 0;

INSERT INTO `address_postal` (`address_postal_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (7,'100','6415',1,'131016','丸の内東京ビルディング（１５階）','東京都千代田区丸の内東京ビルディング（１５階）',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_postal` (`address_postal_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (8,'100','6416',1,'131016','丸の内東京ビルディング（１６階）','東京都千代田区丸の内東京ビルディング（１６階）',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_postal` (`address_postal_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
   (9,'074','1271',1,'011011','以下に掲載がない場合','北海道深川市以下に掲載がない場合',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 , (10,'074','1271',1,'011011','以下に掲載がない場合','北海道稚内市以下に掲載がない場合',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
  -- 最新でない 
 , (11,'074','1271',0,'011011','以下に掲載がない場合','北海道深川市以下に掲載がない場合',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
  -- 指定文字列が含まれない 
 , (12,'074','1271',1,'011011','以下に掲載がない場合','北海道深川市',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 ;
