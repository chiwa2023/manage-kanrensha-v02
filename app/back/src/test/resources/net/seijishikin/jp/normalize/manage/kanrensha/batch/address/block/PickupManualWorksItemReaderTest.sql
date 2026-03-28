DELETE FROM `address_postal`;
ALTER TABLE `address_postal` auto_increment = 0;

INSERT INTO `address_postal` (`address_postal_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (7,'100','6415',1,'131016','丸の内東京ビルディング（１５階）','東京都千代田区丸の内東京ビルディング（１５階）',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_postal` (`address_postal_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (8,'100','6416',1,'131016','丸の内東京ビルディング（１６階）','東京都千代田区丸の内東京ビルディング（１６階）',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59');
INSERT INTO `address_postal` (`address_postal_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
   (243,'074','1271',1,'011011','真駒内（その他）','門静以下に掲載がない場合',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 , (244,'074','1271',1,'011011','真駒内（その他）','深川市広里町（真駒内）',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 , (245,'074','1271',0,'011011','真駒内（その他）','深川市広里町（真駒内）',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 , (246,'074','1271',1,'011011','真駒内（その他）','深川市広里町',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 , (247,'074','1271',1,'131011','真駒内（その他）','深川市広里町（真駒内）',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 ;


